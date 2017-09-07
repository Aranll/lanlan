package com.xiaosuokeji.yilan.admin.controller.bulkio;

import com.xiaosuokeji.framework.xsjframework.util.Base64Util;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.App;
import com.xiaosuokeji.yilan.admin.model.resource.Category;
import com.xiaosuokeji.yilan.admin.model.user.User;
import com.xiaosuokeji.yilan.admin.util.OssUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import com.xiaosuokeji.yilan.server.model.resource.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量操作程序<br/>
 * Created by xuxiaowei on 2017/8/11.
 */
@Controller("bulkAppController")
@RequestMapping("/admin")
public class BulkAppController {

    @RequestMapping(value = "/app/image/download", method = RequestMethod.GET)
    public void downloadImage(Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        App app = new App();
        app.setId(id);
        JSONObject picture = WebUtils.doRawRequest(API.PICTURE_GET, app);
        if (picture.getJSONObject("data").getInt("accessVipLevel") == 1) {
            User param = new User();
            param.setId("d8955e74638a471b884211949c8b8e5e");
            JSONObject user = WebUtils.doRawRequest(API.USER_GET, param);
            if (user.getJSONObject("data").getInt("vipLevel") == 0) {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=utf-8");
                response.getWriter().write("您不是会员，请充值");
                return;
            }
        }
        response.setContentType("image/jpg");
        //response.setHeader("Content-Disposition", "attachment;filename=" + "201708140845161502714716183.jpg");
        OssUtils.fileDownload("picture/201708140845161502714716183.jpg", response.getOutputStream());
    }

    @RequestMapping(value = "/app/template/download", method = RequestMethod.POST)
    public void downloadTemplate(String[] qrCodes, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = WebUtils.doRawRequest(API.APP_CATEGORY_TREE, new App());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        Workbook wb = new XSSFWorkbook();
        for (int i=0; i<jsonArray.length(); ++i) {
            JSONObject category = jsonArray.getJSONObject(i);
            if(category.has("children")) {
                JSONArray children = category.getJSONArray("children");
                for (int j=0; j<children.length(); ++j) {
                    JSONObject item = children.getJSONObject(j);
                    Sheet sheet = wb.createSheet(item.getString("name") + "-" + String.valueOf(item.getLong("id")));
                    Row row = sheet.createRow(0);
                    row.createCell(0).setCellValue("名称");
                    row.createCell(1).setCellValue("二维码");
                    row.createCell(2).setCellValue("开发者");
                    sheet.setColumnWidth(0, 30 * 256);
                    sheet.setColumnWidth(1, 120 * 256);
                    sheet.setColumnWidth(2, 30 * 256);
                    if (i==0 && j == 0) {
                        for (int k=0; k<qrCodes.length; ++k) {
                            Row dataRow = sheet.createRow(k + 1);
                            String name = qrCodes[k].substring(qrCodes[k].lastIndexOf("_") + 1, qrCodes[k].lastIndexOf("."));
                            name = URLDecoder.decode(name, "UTF-8");
                            dataRow.createCell(0).setCellValue(name);
                            dataRow.createCell(1).setCellValue(qrCodes[k]);
                        }
                    }
                }
            }
        }
        response.setHeader("Content-disposition", "attachment; filename=" +
                URLEncoder.encode("医览网-程序模板.xlsx", "UTF-8"));
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value = "/app/template/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadTemplate(MultipartFile file) throws Exception {
        Workbook wb = WorkbookFactory.create(file.getInputStream());
        List<App> apps = new ArrayList<>();
        for (int i=0; i<wb.getNumberOfSheets(); ++i) {
            Sheet sheet = wb.getSheetAt(i);
            for (int j=1; j<=sheet.getLastRowNum(); ++j) {
                Row row = sheet.getRow(j);
                App app = new App();
                app.setName(row.getCell(0) == null ? "" : row.getCell(0).getStringCellValue());
                app.setQrCode(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());
                app.setDeveloper(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());
                Category category = new Category();
                category.setId(Long.valueOf(sheet.getSheetName().split("-")[1]) );
                app.setCategory(category);
                apps.add(app);
            }
        }
        App app = new App();
        app.setApps(apps);
        return WebUtils.doRawRequest(API.BULK_APP_IMPORT, app).toString();
    }
}
