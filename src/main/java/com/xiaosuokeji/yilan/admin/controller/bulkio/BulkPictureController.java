package com.xiaosuokeji.yilan.admin.controller.bulkio;

import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.App;
import com.xiaosuokeji.yilan.admin.model.resource.Category;
import com.xiaosuokeji.yilan.admin.model.resource.Picture;
import com.xiaosuokeji.yilan.admin.model.user.User;
import com.xiaosuokeji.yilan.admin.util.OssUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
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
 * 批量操作图片
 * Created by xuxiaowei on 2017/8/22.
 */
@Controller("bulkPictureController")
@RequestMapping("/admin")
public class BulkPictureController {

    @RequestMapping(value = "/picture/template/download", method = RequestMethod.POST)
    public void downloadTemplate(String[] pictures, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = WebUtils.doRawRequest(API.PICTURE_CATEGORY_TREE, new Picture());
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
                    row.createCell(1).setCellValue("链接");
                    row.createCell(2).setCellValue("可访问的会员级别（普通、会员）");
                    sheet.setColumnWidth(0, 30 * 256);
                    sheet.setColumnWidth(1, 120 * 256);
                    sheet.setColumnWidth(2, 30 * 256);
                    if (i==0 && j == 0) {
                        for (int k=0; k<pictures.length; ++k) {
                            Row dataRow = sheet.createRow(k + 1);
                            String name = pictures[k].substring(pictures[k].lastIndexOf("_") + 1, pictures[k].lastIndexOf("."));
                            name = URLDecoder.decode(name, "UTF-8");
                            dataRow.createCell(0).setCellValue(name);
                            dataRow.createCell(1).setCellValue(pictures[k]);
                        }
                    }
                }
            }
        }
        response.setHeader("Content-disposition", "attachment; filename=" +
                URLEncoder.encode("医览网-图片模板.xlsx", "UTF-8"));
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value = "/picture/template/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadTemplate(MultipartFile file) throws Exception {
        Workbook wb = WorkbookFactory.create(file.getInputStream());
        List<Picture> pictures = new ArrayList<>();
        for (int i=0; i<wb.getNumberOfSheets(); ++i) {
            Sheet sheet = wb.getSheetAt(i);
            for (int j=1; j<=sheet.getLastRowNum(); ++j) {
                Row row = sheet.getRow(j);
                Picture picture = new Picture();
                picture.setName(row.getCell(0) == null ? "" : row.getCell(0).getStringCellValue());
                picture.setUrl(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());
                picture.setAccessVipLevel(row.getCell(2) == null ? "0" : row.getCell(2).getStringCellValue().equals("会员") ? "1" : "0");
                Category category = new Category();
                category.setId(Long.valueOf(sheet.getSheetName().split("-")[1]) );
                picture.setCategory(category);
                pictures.add(picture);
            }
        }
        Picture picture = new Picture();
        picture.setPictures(pictures);
        return WebUtils.doRawRequest(API.BULK_PICTURE_IMPORT, picture).toString();
    }
}
