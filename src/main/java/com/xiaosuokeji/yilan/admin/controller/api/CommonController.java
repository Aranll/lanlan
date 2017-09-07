package com.xiaosuokeji.yilan.admin.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by GustinLau on 2017-05-03.
 */
@Controller("adminApiCommonController")
@RequestMapping("/admin/api")
public class CommonController {

    /**
     * UEditor 入口
     *
     * @return
     */
    @RequestMapping("/ueditor/controller")
    public String controller() {
        return "common/_ueditor";
    }


//    /**
//     * 文件上传
//     *
//     * @param folders
//     * @param files
//     * @param response
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
//    @ResponseBody
//    public String fileUpload(String[] folders, MultipartFile[] files, HttpServletResponse response) throws Exception {
//        response.setContentType("application/json;charset=UTF-8");
//        return GsonUtils.toJson(OssUtils.fileUpload(folders, files));
//    }
}
