package com.yunqiu.service.impl;

import com.yunqiu.dao.GameMemberMapper;
import com.yunqiu.model.GameMember;
import com.yunqiu.model.PageCrt;
import com.yunqiu.service.GameMemberService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app用户管理
 */

@Service
public class GameMemberServiceImpl implements GameMemberService {
    @Autowired
    private GameMemberMapper gameMemberMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return gameMemberMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return gameMemberMapper.selectePaging(page);
    }

    /**
     * 修改
     * @return
     */
    @Override
    public Map<String, Object> updateStatus(GameMember gameMember) {
        try {
            int result = 0;

            GameMember game=gameMemberMapper.findIdGameMember(gameMember);
            if(game==null){
                gameMember.setMatchId(Utils.getID(22));
                result = gameMemberMapper.insertGameMember(gameMember);
                if(result<=0){
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }
            }else{
                result = gameMemberMapper.updateStatus(gameMember);
                if(result<=0){
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            e.printStackTrace();
            LoggerUtil.outError(GameMemberService.class, "球员标签修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


}
