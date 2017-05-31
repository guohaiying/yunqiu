package com.yunqiu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by 11366 on 2017/1/2.
 */
public interface UploadService {
    Map<String,Object> single(MultipartFile file);
    Map<String,Object> more(MultipartFile[] files_list);
}
