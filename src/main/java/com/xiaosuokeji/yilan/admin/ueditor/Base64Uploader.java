package com.xiaosuokeji.yilan.admin.ueditor;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.xiaosuokeji.yilan.admin.util.OssUtils;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.util.Map;

/**
 * Created by GustinLau on 2017-05-11.
 */
public class Base64Uploader {

    public static State save(String content, Map<String, Object> conf) {
        byte[] data = decode(content);
        long maxSize = ((Long) conf.get("maxSize")).longValue();
        if (!validSize(data, maxSize)) {
            return new BaseState(false, 1);
        } else {
            String suffix = FileType.getSuffix("JPG");
            String savePath = PathFormat.parse((String) conf.get("savePath"), (String) conf.get("filename"));
            savePath = savePath + suffix;

            ByteArrayInputStream is = new ByteArrayInputStream(data);

            Map result = OssUtils.ueditorUpload(is, savePath);
            State storageState = new BaseState((boolean) result.get("status"));
            if (storageState.isSuccess()) {
                storageState.putInfo("url", (String) result.get("url"));
                storageState.putInfo("type", suffix);
                storageState.putInfo("original", "");
            }

            return storageState;
        }
    }

    private static byte[] decode(String content) {
        return Base64.decodeBase64(content);
    }

    private static boolean validSize(byte[] data, long length) {
        return (long) data.length <= length;
    }
}
