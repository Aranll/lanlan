package com.xiaosuokeji.yilan.admin.controller.bulkio;

import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.App;
import com.xiaosuokeji.yilan.admin.model.resource.Category;
import com.xiaosuokeji.yilan.admin.model.resource.Book;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
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
 * 批量操作图书
 * Created by xuxiaowei on 2017/8/23.
 */
@Controller("bulkBookController")
@RequestMapping("/admin")
public class BulkBookController {

    @RequestMapping(value = "/book/template/download", method = RequestMethod.POST)
    public void downloadTemplate(String[] covers, String[] books, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject jsonObject = WebUtils.doRawRequest(API.BOOK_CATEGORY_TREE, new App());
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
                    row.createCell(2).setCellValue("出版社");
                    row.createCell(3).setCellValue("出版时间(格式：2000年1月1号)");
                    row.createCell(4).setCellValue("封面图");
                    row.createCell(5).setCellValue("链接");
                    row.createCell(6).setCellValue("简介");
                    row.createCell(7).setCellValue("可访问的会员级别（普通、会员）");
                    sheet.setColumnWidth(0, 30 * 256);
                    sheet.setColumnWidth(1, 30 * 256);
                    sheet.setColumnWidth(2, 30 * 256);
                    sheet.setColumnWidth(3, 30 * 256);
                    sheet.setColumnWidth(4, 120 * 256);
                    sheet.setColumnWidth(5, 120 * 256);
                    sheet.setColumnWidth(6, 30 * 256);
                    sheet.setColumnWidth(7, 30 * 256);
                    CellStyle cellStyle = wb.createCellStyle();
                    DataFormat dataFormat = wb.createDataFormat();
                    cellStyle.setDataFormat(dataFormat.getFormat("text"));
                    sheet.setDefaultColumnStyle(3, cellStyle);
                    if (i==0 && j == 0) {
                        for (int k=0; k<books.length; ++k) {
                            Row dataRow = sheet.createRow(k + 1);
                            String name = books[k].substring(books[k].lastIndexOf("_") + 1, books[k].lastIndexOf("."));
                            name = URLDecoder.decode(name, "UTF-8");
                            dataRow.createCell(0).setCellValue(name);
                            dataRow.createCell(5).setCellValue(books[k]);
                            for (int n=0; n<covers.length; ++n) {
                                String coverName = covers[n].substring(covers[n].lastIndexOf("_") + 1, covers[n].lastIndexOf("."));
                                if (coverName.equals(name)) {
                                    dataRow.createCell(4).setCellValue(covers[n]);
                                }
                            }
                        }
                    }
                }
            }
        }
        response.setHeader("Content-disposition", "attachment; filename=" +
                URLEncoder.encode("医览网-图书模板.xlsx", "UTF-8"));
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value = "/book/template/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadTemplate(MultipartFile file) throws Exception {
        Workbook wb = WorkbookFactory.create(file.getInputStream());
        List<Book> books = new ArrayList<>();
        for (int i=0; i<wb.getNumberOfSheets(); ++i) {
            Sheet sheet = wb.getSheetAt(i);
            for (int j=1; j<=sheet.getLastRowNum(); ++j) {
                Row row = sheet.getRow(j);
                Book book = new Book();book.setName(row.getCell(0) == null ? "" : row.getCell(0).getStringCellValue());
                book.setAuthor(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());
                book.setPublisher(row.getCell(2) == null ? "" : row.getCell(2).getStringCellValue());
                book.setPublishDate(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());
                book.setCover(row.getCell(4) == null ? "" : row.getCell(4).getStringCellValue());
                book.setPdf(row.getCell(5) == null ? "" : row.getCell(5).getStringCellValue());
                book.setProfile(row.getCell(6) == null ? "" : row.getCell(6).getStringCellValue());
                book.setAccessVipLevel(row.getCell(7) == null ? 0 : row.getCell(7).getStringCellValue().equals("会员") ? 1 : 0);
                Category category = new Category();
                category.setId(Long.valueOf(sheet.getSheetName().split("-")[1]) );
                book.setCategory(category);
                books.add(book);
            }
        }
        Book book = new Book();
        book.setBooks(books);
        return WebUtils.doRawRequest(API.BULK_BOOK_IMPORT, book).toString();
    }
}
