package com.xiaosuokeji.yilan.admin.controller.bulkio;

import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.App;
import com.xiaosuokeji.yilan.admin.model.resource.Category;
import com.xiaosuokeji.yilan.admin.model.resource.Video;
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
 * 视频批量操作
 * Created by xuxiaowei on 2017/8/23.
 */
@Controller("bulkVideoController")
@RequestMapping("/admin")
public class BulkVideoController {

    @RequestMapping(value = "/video/template/download", method = RequestMethod.POST)
    public void downloadTemplate(String[] covers, String[] videos, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = WebUtils.doRawRequest(API.VIDEO_CATEGORY_TREE, new App());
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
                    row.createCell(1).setCellValue("作者");
                    row.createCell(2).setCellValue("来源（本站、外站）");
                    row.createCell(3).setCellValue("封面图");
                    row.createCell(4).setCellValue("链接");
                    row.createCell(5).setCellValue("简介");
                    row.createCell(6).setCellValue("可访问的会员级别（普通、会员）");
                    sheet.setColumnWidth(0, 30 * 256);
                    sheet.setColumnWidth(1, 30 * 256);
                    sheet.setColumnWidth(2, 30 * 256);
                    sheet.setColumnWidth(3, 120 * 256);
                    sheet.setColumnWidth(4, 120 * 256);
                    sheet.setColumnWidth(5, 30 * 256);
                    sheet.setColumnWidth(6, 30 * 256);
                    if (i==0 && j == 0) {
                        for (int k=0; k<videos.length; ++k) {
                            Row dataRow = sheet.createRow(k + 1);
                            String name = videos[k].substring(videos[k].lastIndexOf("_") + 1, videos[k].lastIndexOf("."));
                            name = URLDecoder.decode(name, "UTF-8");
                            dataRow.createCell(0).setCellValue(name);
                            dataRow.createCell(4).setCellValue(videos[k]);
                            for (int n=0; n<covers.length; ++n) {
                                String coverName = covers[n].substring(covers[n].lastIndexOf("_") + 1, covers[n].lastIndexOf("."));
                                if (coverName.equals(name)) {
                                    dataRow.createCell(3).setCellValue(covers[n]);
                                }
                            }
                        }
                    }
                }
            }
        }
        response.setHeader("Content-disposition", "attachment; filename=" +
                URLEncoder.encode("医览网-视频模板.xlsx", "UTF-8"));
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value = "/video/template/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadTemplate(MultipartFile file) throws Exception {
        Workbook wb = WorkbookFactory.create(file.getInputStream());
        List<Video> videos = new ArrayList<>();
        for (int i=0; i<wb.getNumberOfSheets(); ++i) {
            Sheet sheet = wb.getSheetAt(i);
            for (int j=1; j<=sheet.getLastRowNum(); ++j) {
                Row row = sheet.getRow(j);
                Video video = new Video();
                video.setName(row.getCell(0) == null ? "" : row.getCell(0).getStringCellValue());
                video.setAuthor(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());
                video.setOrigin(row.getCell(2) == null ? 0 : row.getCell(2).getStringCellValue().equals("外站") ? 1 : 0);
                video.setCover(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());
                video.setUrl(row.getCell(4) == null ? "" : row.getCell(4).getStringCellValue());
                video.setProfile(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());
                video.setAccessVipLevel(row.getCell(6) == null ? 0 : row.getCell(6).getStringCellValue().equals("会员") ? 1 : 0);
                Category category = new Category();
                category.setId(Long.valueOf(sheet.getSheetName().split("-")[1]) );
                video.setCategory(category);
                videos.add(video);
            }
        }
        Video video = new Video();
        video.setVideos(videos);
        return WebUtils.doRawRequest(API.BULK_VIDEO_IMPORT, video).toString();
    }
}
