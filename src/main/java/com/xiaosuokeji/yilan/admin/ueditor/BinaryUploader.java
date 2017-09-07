package com.xiaosuokeji.yilan.admin.ueditor;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.xiaosuokeji.yilan.admin.util.OssUtils;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by GustinLau on 2017-05-11.
 */
public class BinaryUploader {

    public static final State save(HttpServletRequest request, Map<String, Object> conf) {
        MultipartFile multipartFile = null;
        boolean isAjaxUpload = request.getHeader("X_Requested_With") != null;
        if (!ServletFileUpload.isMultipartContent(request)) {
            return new BaseState(false, 5);
        } else {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            if (isAjaxUpload) {
                upload.setHeaderEncoding("UTF-8");
            }

            try {
                MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
                multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());

                if (multipartFile == null) {
                    return new BaseState(false, 7);
                } else {
                    String savePath = (String) conf.get("savePath");
                    String originFileName = multipartFile.getOriginalFilename();
                    String suffix = FileType.getSuffixByFilename(originFileName);
                    originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
                    savePath = savePath + suffix;
                    if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
                        return new BaseState(false, 8);
                    } else {
                        savePath = PathFormat.parse(savePath, originFileName);
                        InputStream is = multipartFile.getInputStream();

                        Map result = OssUtils.ueditorUpload(is, savePath);
                        State storageState = new BaseState((boolean) result.get("status"));
                        is.close();
                        if (storageState.isSuccess()) {
                            storageState.putInfo("url", (String) result.get("url"));
                            storageState.putInfo("type", suffix);
                            storageState.putInfo("original", originFileName + suffix);
                        }

                        return storageState;
                    }
                }
            } catch (IOException e) {
                return new BaseState(false, 4);
            }
        }
    }

    private static boolean validType(String type, String[] allowTypes) {
        List<String> list = Arrays.asList(allowTypes);
        return list.contains(type);
    }
}
