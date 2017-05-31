package com.yunqiu.service;

import com.yunqiu.model.GameMember;
import com.yunqiu.model.PageCrt;

import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/1/14.
 */
public interface GameMemberService {
    Integer selectCount(PageCrt page);

    List<Map> selectPaging(PageCrt page);

    Map<String, Object> updateStatus(GameMember gameMember);

}
