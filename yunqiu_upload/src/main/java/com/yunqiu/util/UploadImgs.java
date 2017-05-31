package com.yunqiu.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * 图片上传
 * @author 武尊
 * @time 2016-12-31
 * @version 1.0
 */
public class UploadImgs {

    /**
     * 上传
     * @return
     **/
    public static String upload(MultipartFile file){
        try {
            // 获取图片的文件名
            String reqult_fileName = file.getOriginalFilename();
            // 获取图片的扩展名
            String extensionName = reqult_fileName.substring(reqult_fileName.lastIndexOf(".") + 1);
            // 新的图片文件名 = 获取时间戳+"."图片扩展名
            String fileName = Utils.getUUID()+ "." + extensionName;
            //文件存储路径
            String str = DateUtil.getStringTime("yyyyMMdd");
            String contexPath = "/var/local/yunqiu/upload/static/uploads/images/"+str+"/";
            File tempFile = new File(contexPath);
            if (!tempFile.exists()){
                tempFile.mkdirs();
            }
            BufferedOutputStream stream  = new BufferedOutputStream(new FileOutputStream(new File(contexPath+fileName)));
            stream.write(file.getBytes());
            stream.close();
            return "/uploads/images/"+str+"/"+fileName;
        }catch (Exception e){
            LoggerUtil.outError(UploadImgs.class,"图片保存时发生错误",e);
            return null;
        }
    }

}
