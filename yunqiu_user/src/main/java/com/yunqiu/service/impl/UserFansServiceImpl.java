package com.yunqiu.service.impl;

import com.yunqiu.dao.UserFansMapper;
import com.yunqiu.model.UserFans;
import com.yunqiu.service.UserFansService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/2/7.
 */

@Service
public class UserFansServiceImpl implements UserFansService {
    @Autowired
    private UserFansMapper userFansMapper;

    /**
     * 新增关注
     * @param user_id
     * @param focus
     * @return
     */
    @Override
    public Map<String, Object> attention(String user_id, String focus) {
        try {
            if (Utils.isNull(focus)){
                return ControllerReturnBase.errorResule(1501,"缺少被关注的用户id");
            }
            UserFans db_userFans = userFansMapper.selectFans(user_id,focus);
            if (db_userFans != null){
                return ControllerReturnBase.errorResule(1502,"已关注该用户，请勿重复关注");
            }
            UserFans userFans = new UserFans();
            userFans.setFans_id(Utils.getUUID());
            userFans.setUser_id(user_id);
            userFans.setFocus(focus);
            userFansMapper.insertUserFans(userFans);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(UserFansServiceImpl.class,"添加关注用户（service）时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消关注
     * @param user_id
     * @param focus
     * @return
     */
    @Override
    public Map<String, Object> cancelAttention(String user_id, String focus) {
        try {
            if (Utils.isNull(focus)){
                return ControllerReturnBase.errorResule(1501,"缺少被关注的用户id");
            }
            UserFans db_userFans = userFansMapper.selectFans(user_id,focus);
            if (db_userFans == null){
                return ControllerReturnBase.errorResule(1502,"未关注该用户");
            }
            userFansMapper.deleteFans(db_userFans.getFans_id());
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(UserFansServiceImpl.class,"取消关注用户（service）时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取粉丝列表
     * @param pageNum
     * @param pageSize
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> fansList(int pageNum, int pageSize, String user_id) {
        try {
            if (pageNum <= 0 || pageSize <= 0){
                return ControllerReturnBase.errorResule(1501,"页码或者每页条数不可为0");
            }
            //获取总条数
            int fansTotal = userFansMapper.selectFansTotal(user_id);
            if (fansTotal == 0){
                return ControllerReturnBase.errorResule(1502,"暂无粉丝");
            }
            //计算总页数
            int pageTotal = fansTotal%pageSize == 0 ?fansTotal/pageSize:(fansTotal/pageSize)+1;
            //判断当前页码是否大于总页码
            if (pageNum > pageTotal){
                return ControllerReturnBase.errorResule(1502,"当前页码不能大于总页码");
            }
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            //分页查询粉丝列表
            List<Map<String,Object>> fans_list = userFansMapper.selectPagingFans(user_id,start_now,pageSize);
            //返回数据
            Map<String,Object> result_param = new HashMap<>();
            result_param.put("total",pageTotal);
            result_param.put("pageSize",pageSize);
            result_param.put("pageNum",pageNum);
            result_param.put("fans",fans_list);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(UserFansServiceImpl.class,"获取粉丝列表(service)时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取关注球员列表
     * @param pageNum
     * @param pageSize
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> focusList(int pageNum, int pageSize, String user_id) {
        try {
            if (pageNum <= 0 || pageSize <= 0){
                return ControllerReturnBase.errorResule(1501,"页码或者每页条数不可为0");
            }
            //获取总条数
            int focusTotal = userFansMapper.selectFocusTotal(user_id);
            if (focusTotal == 0){
                return ControllerReturnBase.errorResule(1502,"没有关注的球员");
            }
            //计算总页数
            int pageTotal = focusTotal%pageSize == 0 ?focusTotal/pageSize:(focusTotal/pageSize)+1;
            //判断当前页码是否大于总页码
            if (pageNum > pageTotal){
                return ControllerReturnBase.errorResule(1502,"当前页码不能大于总页码");
            }
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            //分页查询粉丝列表
            List<Map<String,Object>> focus_list = userFansMapper.selectPagingFocus(user_id,start_now,pageSize);
            //返回数据
            Map<String,Object> result_param = new HashMap<>();
            result_param.put("total",pageTotal);
            result_param.put("pageSize",pageSize);
            result_param.put("pageNum",pageNum);
            result_param.put("focus",focus_list);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(UserFansServiceImpl.class,"获取关注球员列表(service)时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
