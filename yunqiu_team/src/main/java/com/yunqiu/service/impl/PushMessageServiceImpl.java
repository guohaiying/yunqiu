package com.yunqiu.service.impl;

import cn.jpush.api.push.model.audience.Audience;
import com.yunqiu.dao.GeneralMapper;
import com.yunqiu.dao.MessageMapper;
import com.yunqiu.dao.TeamMapper;
import com.yunqiu.dao.TeamMemberMapper;
import com.yunqiu.general.jpush.JpushClient;
import com.yunqiu.model.Message;
import com.yunqiu.model.Team;
import com.yunqiu.model.TeamMember;
import com.yunqiu.service.PushMessageService;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 推送数据处理
 * @author 武尊
 * @time 2017-03-25
 */
@Service
public class PushMessageServiceImpl implements PushMessageService {
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TeamMemberMapper teamMemberMapper;
    @Autowired
    private GeneralMapper generalMapper;
    @Autowired
    private MessageMapper messageMapper;

    /**
     * 申请加入球队时给球队管理员推送消息
     * @param user_id
     * @param team_id
     * @return
     */
    @Override
    public boolean applyJoinTeamPush(String user_id, String team_id) {
        try {
            //获取到球员资料
            Map<String,Object> userInfo = generalMapper.selectUsersByUserId(user_id);
            //获取球队资料
            Team team = teamMapper.selectTeamInfo(team_id);
            //获取球队管理员
            List<TeamMember> teamMembers = teamMemberMapper.getTeamAdmin(team_id);
            //通知消息
            String alert = "球员"+userInfo.get("nickname")+"申请加入"+team.getTeam_name()+"球队，快去审核吧";
            //别名获取
            List<String> alias = new ArrayList<>();
            for (int index=0;index<teamMembers.size();index++){
                alias.add(teamMembers.get(index).getUser_id());
            }
            //推送消息
            boolean isPush = JpushClient.pushByAliasAndAll(alert,null, Audience.alias(alias));
            //存储消息
            for (int index=0;index<alias.size();index++){
                Message message = new Message();
                message.setMessage_id(Utils.getUUID());
                message.setIs_look(1);
                message.setMessage(alert);
                message.setMessage_title("申请加入");
                message.setPush_time(new Date());
                message.setMessage_type(1);
                message.setType_id(team_id);
                message.setSpecific_type(1);
                message.setSpecific_id(team_id);
                message.setUser_id(alias.get(index));
                messageMapper.insert(message);
            }
            return isPush;
        }catch (Exception e){
            LoggerUtil.outError(PushMessageServiceImpl.class,"申请加入球队时给球队管理员推送消息发送错误",e);
            return false;
        }
    }

    /**
     * 设置球员身份跟权限时给被设置的球员推送消息
     * @param user_id
     * @param team_id
     * @param identity 	身份 1：队长 2：副队长 3：领队 4：教练 5：队员 6:外援 7:啦啦队
     * @param jurisdiction 	权限 0:无管理权限 1:超级管理员 2：普通管理员
     * @return
     */
    @Override
    public boolean updateIdentityPush(String user_id, String team_id, int identity, int jurisdiction) {
        try {
            //获取到球员资料
            Map<String,Object> userInfo = generalMapper.selectUsersByUserId(user_id);
            //获取球队资料
            Team team = teamMapper.selectTeamInfo(team_id);
            //通知消息
            String alert = null;
            //消息
            Message message = null;
            //发送修改权限的推送
            if (jurisdiction == 0){
                alert = "你在"+team.getTeam_name()+"球队的管理权限被取消，快去看看吧";
            }else if (jurisdiction == 2){
                alert = "你在"+team.getTeam_name()+"球队被授权为管理员，快去看看吧";
            }
            JpushClient.pushByAliasAndAll(alert,null,Audience.alias(user_id));
            //存储消息
            message = new Message();
            message.setMessage_id(Utils.getUUID());
            message.setIs_look(1);
            message.setMessage(alert);
            message.setMessage_title("管理权限变更");
            message.setPush_time(new Date());
            message.setMessage_type(1);
            message.setType_id(team_id);
            message.setSpecific_type(2);
            message.setSpecific_id(team_id);
            message.setUser_id(user_id);
            messageMapper.insert(message);
            //发送修改身份的推送
            String[] identity_name = {"","队长","副队长","领队","教练","队员","外援","啦啦队"};
            alert = "你被任命为"+team.getTeam_name()+"球队的"+identity_name[identity]+"，快去查看吧";
            JpushClient.pushByAliasAndAll(alert,null,Audience.alias(user_id));
            //存储消息
            message = new Message();
            message.setMessage_id(Utils.getUUID());
            message.setIs_look(1);
            message.setMessage(alert);
            message.setMessage_title("任命通知");
            message.setPush_time(new Date());
            message.setMessage_type(1);
            message.setType_id(team_id);
            message.setSpecific_type(3);
            message.setSpecific_id(team_id);
            message.setUser_id(user_id);
            messageMapper.insert(message);
            return true;
        }catch (Exception e){
            LoggerUtil.outError(PushMessageServiceImpl.class,"修改球员身份权限时给被设置球员推送消息发生错误",e);
            return false;
        }
    }

    /**
     * 邀请球员加入球队时推送消息
     * @param team_id 球队id
     * @param user_id 用户id
     * @return
     */
    @Override
    public boolean insertInvitationPush(String team_id, String user_id) {
        try {
            //获取到球员资料
            Map<String,Object> userInfo = generalMapper.selectUsersByUserId(user_id);
            //获取球队资料
            Team team = teamMapper.selectTeamInfo(team_id);
            //通知消息
            String alert = team.getTeam_name()+"邀请您加入，一起驰骋球场，快去查看吧";
            //推送消息
            boolean isPush = JpushClient.pushByAliasAndAll(alert,null, Audience.alias(user_id));
            Message message = new Message();
            message.setMessage_id(Utils.getUUID());
            message.setIs_look(1);
            message.setMessage(alert);
            message.setMessage_title("邀请加入");
            message.setPush_time(new Date());
            message.setMessage_type(3);
            message.setType_id(user_id);
            message.setSpecific_type(4);
            message.setSpecific_id(team_id);
            message.setUser_id(user_id);
            messageMapper.insert(message);
            return isPush;
        }catch (Exception e){
            LoggerUtil.outError(PushMessageServiceImpl.class,"邀请球员加入球队时推送消息发送错误",e);
            return false;
        }
    }
}
