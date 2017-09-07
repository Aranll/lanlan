package com.xiaosuokeji.yilan.admin.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.xiaosuokeji.yilan.admin.constant.CommonConstants;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.awt.image.RenderedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by LeShang on 2016/4/14.
 */
public class OssUtils {


    /**
     * 文件上传
     *
     * @param folders 指定文件夹s
     * @param files   文件s
     * @return
     * @throws Exception
     */
    public static Map fileUpload(String folders[], MultipartFile[] files) throws Exception {
        return fileUpload(folders, files, true);
    }

    public static Map fileUpload(String folders[], MultipartFile[] files, Boolean withSize) throws Exception {
        OSSClient client = new OSSClient(CommonConstants.OSS_END_POINT, CommonConstants.OSS_ACCESS_KEY_ID, CommonConstants.OSS_ACCESS_KEY_SECRET);
        Map returnMap = new HashMap<>();
        try {
            Map data = new HashMap<>();
            int sum = folders.length;
            int total = 0;
            InputStream inputStream;
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < sum; i++) {
                String newName = rename(files[i].getOriginalFilename());//新文件名
                inputStream = files[i].getInputStream();

                client.putObject(CommonConstants.OSS_BUCKET_NAME, folders[i] + "/" + newName, inputStream);
                String url = CommonConstants.OSS_DOMAIN + "/" + folders[i] + "/" + newName;
                if (withSize) {
                    //获取图片的高和宽并且拼接在图片的url
                    JSONObject info = WebUtils.doHttpRequest(url + "?x-oss-process=image/info", HttpMethod.GET);
                    url += "?x-oss-process=image/resize,w_" + info.getJSONObject("ImageWidth").getString("value") + ",h_" +
                            info.getJSONObject("ImageHeight").getString("value");
                }
                if (list.add(url))
                    total++;
            }
            data.put("total", total);
            data.put("list", list);

            returnMap.put("code", 200);
            returnMap.put("data", data);
            returnMap.put("status", true);
        } finally {
            client.shutdown();
            return returnMap;
        }
    }

    public static List<String> fileUpload(String folder, MultipartFile[] files) throws Exception {
        OSSClient client = new OSSClient(CommonConstants.OSS_END_POINT, CommonConstants.OSS_ACCESS_KEY_ID, CommonConstants.OSS_ACCESS_KEY_SECRET);
        Map returnMap = new HashMap<>();
        try {
            int sum = files.length;
            InputStream inputStream;
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < sum; i++) {
                String newName = rename(files[i].getOriginalFilename());//新文件名
                inputStream = files[i].getInputStream();

                client.putObject(CommonConstants.OSS_BUCKET_NAME, folder + "/" + newName, inputStream);
                String url = CommonConstants.OSS_DOMAIN + "/" + folder + "/" + newName;
                list.add(url);
            }
            return list;
        } finally {
            client.shutdown();
        }
    }

    public static Map ueditorUpload(InputStream inputStream, String name) {
        OSSClient client = new OSSClient(CommonConstants.OSS_END_POINT, CommonConstants.OSS_ACCESS_KEY_ID, CommonConstants.OSS_ACCESS_KEY_SECRET);
        Map map = new HashMap<>();
        map.put("status", false);
        try {
            client.putObject(CommonConstants.OSS_BUCKET_NAME, name.replaceFirst("/", ""), inputStream);
            map.put("url", CommonConstants.OSS_DOMAIN + name);
            map.put("status", true);
        } finally {
            client.shutdown();
            return map;
        }
    }

    public static void fileDownload(String fileName, OutputStream out) throws Exception {
        OSSClient client = new OSSClient(CommonConstants.OSS_END_POINT, CommonConstants.OSS_ACCESS_KEY_ID, CommonConstants.OSS_ACCESS_KEY_SECRET);
        InputStream in = null;
        try {
            OSSObject ossObject = client.getObject(CommonConstants.OSS_BUCKET_NAME, fileName);
            in = ossObject.getObjectContent();
            byte[] buf = new byte[1024];
            int size;
            while ((size = in.read(buf, 0, buf.length)) != -1) {
                out.write(buf, 0, size);
            }
            in.close();
        } finally {
            if (in !=null) in.close();
            client.shutdown();
        }
    }

    /**
     * 去掉url的参数
     *
     * @param url
     * @return
     */
    public static String imageUrlReplace(String url) {
        if (url != null) {
            StringBuilder resetedUrl = new StringBuilder();
            if (url.contains(",")) {
                String urls[] = url.split(",");
                for (String u : urls) {
                    if (u.contains("?"))
                        resetedUrl.append(u.substring(0, u.indexOf("?"))).append(",");
                    else
                        resetedUrl.append(u).append(",");
                }
                resetedUrl.subSequence(0, resetedUrl.length() - 1);
            } else {
                if (url.contains("?"))
                    resetedUrl.append(url.substring(0, url.indexOf("?")));
                else
                    resetedUrl.append(url);
            }

            return resetedUrl.toString();
        } else {
            return "";
        }
    }


    /**
     * 文件重命名
     *
     * @param originalFilename 文件原始名称
     * @return 重命名后文件名
     */
    private static String rename(String originalFilename) {
        int index = originalFilename.lastIndexOf(".");       //.的位置
        String suffix = originalFilename.substring(index, originalFilename.length());//文件后缀
        Date date = new Date();
        String name_1 = new SimpleDateFormat("yyyyMMddhhmmss").format(date);
        String name_2 = String.valueOf(date.getTime());
        return name_1 + name_2 + suffix;            //新文件名
    }


}


