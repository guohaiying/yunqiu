package com.yunqiu.service.impl;

import com.yunqiu.dao.UserCloudDataMapper;
import com.yunqiu.model.UserCloudData;
import com.yunqiu.service.UserCloudDataService;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 11366 on 2017/3/8.
 */

@Service
public class UserCloudDataServiceImpl implements UserCloudDataService {
    @Autowired
    private UserCloudDataMapper userCloudDataMapper;
    /**
     * 初始化云五数据
     */
    @Override
    public boolean initialization(String user_id) {
        try {
            UserCloudData userCloudData = new UserCloudData();
            userCloudData.setCloud_id(Utils.getID(22));
            userCloudData.setUser_id(user_id);
            userCloudData.setAttack_gross(Utils.getRandowByScope());
            userCloudData.setAttack_gains(0);
            userCloudData.setDefensive_gross(Utils.getRandowByScope());
            userCloudData.setDefensive_gains(0);
            userCloudData.setPhysical_gross(Utils.getRandowByScope());
            userCloudData.setPhysical_gains(0);
            userCloudData.setTechnology_gross(Utils.getRandowByScope());
            userCloudData.setTechnology_gains(0);
            userCloudData.setAggressive_gross(Utils.getRandowByScope());
            userCloudData.setAggressive_gains(0);
            userCloudData.setPower(Utils.getRandowByScope());
            userCloudData.setGains(0);
            userCloudDataMapper.insert(userCloudData);
            return true;
        }catch (Exception e){
            LoggerUtil.outError(UserCloudDataServiceImpl.class,"初始化云五数据时发生错误",e);
            return false;
        }
    }
}
