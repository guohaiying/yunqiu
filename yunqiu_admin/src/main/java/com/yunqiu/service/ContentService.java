package com.yunqiu.service;

import com.yunqiu.model.Banner;
import com.yunqiu.model.Content;
import com.yunqiu.model.PageCrt;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface ContentService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> addContent(Content content);

    Map<String, Object> updatContent(Content content);

    Map<String, Object> deleteContent(String content_id);


}
