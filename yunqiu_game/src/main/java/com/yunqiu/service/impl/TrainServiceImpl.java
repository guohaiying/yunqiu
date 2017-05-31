package com.yunqiu.service.impl;

import com.yunqiu.dao.TeamInfoMapper;
import com.yunqiu.dao.TrainMapper;
import com.yunqiu.model.Train;
import com.yunqiu.model.TrainMember;
import com.yunqiu.service.TrainService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 训练
 */

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainMapper trainMapper;
    @Autowired
    private TeamInfoMapper teamInfoMapper;

    /**
     * 创建训练
     * @param train
     * @return
     */
    @Override
    public Map<String, Object> createTrain(Train train) {
        try {
            //验证球队id
            if (Utils.isNull(train.getTeam_id())){
                return ControllerReturnBase.errorResule(1501,"缺少球队id");
            }
            //验证训练时间
            if (train.getTrain_time() == null){
                return ControllerReturnBase.errorResule(1501,"缺少训练开始时间");
            }
            //验证训练时长
            if (train.getTrain_duration() == 0.0){
                return ControllerReturnBase.errorResule(1501,"缺少训练时长");
            }
            //验证场地
            if (Utils.isNull(train.getTrain_site())){
                return ControllerReturnBase.errorResule(1501,"缺少训练场地");
            }
            //训练主题，默认为日常训练
            if (Utils.isNull(train.getTrain_name())){
                train.setTrain_name("日常训练");
            }
            //赋值（系统逻辑参数）
            train.setTrain_id(Utils.getUUID());
            train.setStatus(1);
            trainMapper.insertTrain(train);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TrainServiceImpl.class,"创建训练时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改训练
     * @param train
     * @return
     */
    @Override
    public Map<String, Object> updateTrain(Train train) {
        try {
            Train db_train = trainMapper.selectTrainById(train.getTrain_id());
            //查询训练是否存在
            if (db_train == null){
                return ControllerReturnBase.errorResule(1502,"训练不存在");
            }
            //判断训练当前状态，只有报名中的训练可以修改
            if (db_train.getStatus() != 1){
                return ControllerReturnBase.errorResule(1502,"只有报名中的训练可以修改");
            }
            //修改训练开始时间
            if (train.getTrain_time() != null){
                db_train.setTrain_time(train.getTrain_time());
            }
            //修改训练时长
            if (train.getTrain_duration() > 0){
                db_train.setTrain_duration(train.getTrain_duration());
            }
            //修改训练场地
            if (!Utils.isNull(train.getTrain_site())){
                db_train.setTrain_site(train.getTrain_site());
            }
            //修改训练主题
            if (!Utils.isNull(train.getTrain_name())){
                db_train.setTrain_name(train.getTrain_name());
            }
            trainMapper.updateTrain(db_train);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TrainServiceImpl.class,"修改训练时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消训练
     * @param train_id
     * @param cncel_reason
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> cancelTrain(String train_id, String cncel_reason, String user_id) {
        try {
            //获取训练信息
            Train db_train = trainMapper.selectTrainById(train_id);
            if (db_train == null){
                return ControllerReturnBase.errorResule(1501,"训练不存在");
            }
            //获取球队，用以判断身份
            Map<String,Object> team = teamInfoMapper.selectTeamMemberByTeamIdAndUserId(db_train.getTeam_id(),user_id);
            if (team == null){
                return ControllerReturnBase.errorResule(1502,"无权限修改");
            }
            if ((int)team.get("jurisdiction") == 0){
                return ControllerReturnBase.errorResule(1502,"无权限修改");
            }
            db_train.setCncel_reason(cncel_reason);
            db_train.setStatus(4);
            trainMapper.updateTrain(db_train);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TrainServiceImpl.class,"取消训练时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 报名训练
     * @param train_id
     * @param status
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> participateTrain(String train_id, int status, String user_id) {
        try {
            if (status <=0 && status>=4){
                return ControllerReturnBase.errorResule(1501,"状态错误");
            }
            //验证训练
            Train db_train = trainMapper.selectTrainById(train_id);
            if (db_train == null){
                return ControllerReturnBase.errorResule(1501,"训练不存在");
            }
            //查询是否已有报名信息
            TrainMember trainMember = trainMapper.selectTrainMemberByUserIdAndTrainId(user_id,train_id);
            if (trainMember == null){
                trainMember =new TrainMember();
                trainMember.setMember_id(Utils.getUUID());
                trainMember.setUser_id(user_id);
                trainMember.setTrain_id(train_id);
                trainMember.setParticipate(status);
                trainMapper.insertTrainMember(trainMember);
            }else{
                trainMapper.updateTrainMember(trainMember.getTrain_id(),status);
            }
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(TrainServiceImpl.class,"报名训练时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取训练详情
     * @param train_id
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> trainInfo(String train_id, String user_id) {
        try {
            //获取已报名人数
            int sign_number = trainMapper.statisticalByTrainIdAndUserIdAndStatus(train_id,user_id,1);
            //获取待定人数
            int undetermined_number = trainMapper.statisticalByTrainIdAndUserIdAndStatus(train_id,user_id,2);
            //获取请假人数
            int leave_number = trainMapper.statisticalByTrainIdAndUserIdAndStatus(train_id,user_id,3);
            //获取报名状态
            TrainMember trainMember = trainMapper.selectTrainMemberByUserIdAndTrainId(user_id,train_id);
            int participate=0;
            if(trainMember != null){
                participate = trainMember.getParticipate();
            }
            //获取训练详情
            Map<String,Object> info = trainMapper.selectTrainAndTeamById(train_id);
            //获取已报名成员
            List<Map<String,Object>> sign = trainMapper.userInfo(train_id,1);
            //获取待定成员
            List<Map<String,Object>> undetermined=trainMapper.userInfo(train_id,2);
            //获取请假成员
            List<Map<String,Object>> leave=trainMapper.userInfo(train_id,3);
            //封装返回数据
            Map<String,Object> result = new HashMap<>();
            result.put("sign_number",sign_number);
            result.put("undetermined_number",undetermined_number);
            result.put("leave_number",leave_number);
            result.put("participate",participate);
            result.put("info",info);
            result.put("sign",sign);
            result.put("undetermined",undetermined);
            result.put("leave",leave);
            return ControllerReturnBase.successResule(result);
        }catch (Exception e){
            LoggerUtil.outError(TrainServiceImpl.class,"获取训练详情时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
