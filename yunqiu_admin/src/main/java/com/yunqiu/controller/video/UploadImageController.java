package com.yunqiu.controller.video;

import com.yunqiu.controller.common.BaseController;
import com.yunqiu.service.VideoService;
import com.yunqiu.util.JSONKit;
import com.yunqiu.util.youpaiyun.CheckSign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/upload")
@EnableAutoConfiguration
public class UploadImageController extends BaseController {
    @Autowired
    private VideoService videoService;

    JSONKit json= new JSONKit();

    @ResponseBody
    @RequestMapping("/upload")
    public String upload(HttpServletResponse response,  @RequestParam("file") MultipartFile file, HttpSession httpSession) {

        File f = null;
        try {
            f= File.createTempFile("tmp", null);
            file.transferTo(f);
            f.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = CheckSign.uploadImage(f);

        return fileName;
    }


    @ResponseBody
    @RequestMapping("/upload2")
    public String upload2(HttpServletResponse response,  @RequestParam("file2") MultipartFile file2, HttpSession httpSession) {

        File f = null;
        try {
            f= File.createTempFile("tmp", null);
            file2.transferTo(f);
            f.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = CheckSign.uploadImage(f);

        return fileName;
    }



}
