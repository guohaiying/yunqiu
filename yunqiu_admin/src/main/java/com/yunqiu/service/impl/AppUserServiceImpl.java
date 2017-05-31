package com.yunqiu.service.impl;

import com.yunqiu.dao.AppUsersMapper;
import com.yunqiu.dao.JoinTeamMapper;
import com.yunqiu.dao.TeamMemberMapper;
import com.yunqiu.model.*;
import com.yunqiu.service.AppUserService;
import com.yunqiu.service.UserCloudDataService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app用户管理
 */

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUsersMapper usersMapper;

    @Autowired
    private JoinTeamMapper jointeamMapper;

    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Autowired
    private UserCloudDataService userCloudDataService;

    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return usersMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return usersMapper.selectePaging(page);
    }

    /**
     * 用户添加
     * @return
     */
    @Override
    public Map<String, Object> addUser(Map<String, Object> dataMap ) {
        try {

            int count = usersMapper.selectByPhone(dataMap.get("identifier")+"");
            if(count>0){
                return ControllerReturnBase.errorResule(1501, "该账户已经注册  请勿重复注册");
            }

            //添加用户基本资料
            AppUser user = new AppUser();
            String userId=Utils.getID(22);
            user.setUserId(userId);
            user.setNickname(dataMap.get("nickname")+"");
            if (Utils.isNull(dataMap.get("identifier")+"")) {
                user.setStandby("1");
            }else{
                user.setStandby("2");
            }
            user.setStatus("1");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
            String dstr="1987-01-01";
            Date date=sdf.parse(dstr);
            user.setBirthday(date);

            int age = this.getAge(date);
            user.setAge(age);

            int result = usersMapper.insertUsers(user);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //添加用户账户
            UserAuths userAuth = new UserAuths();
            String authId = Utils.getID(22);
            userAuth.setAuthId(authId);
            userAuth.setUserId(userId);
            userAuth.setIdentityType(1);
            userAuth.setCredential(Utils.SHAEncrypt("352416"));
            userAuth.setIdentifier(dataMap.get("identifier")+"");
            userAuth.setRegistration_time(new Date());
            userAuth.setRegistrationIp(dataMap.get("ip")+"");

            result = usersMapper.insertUsersAuth(userAuth);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }
            //初始化云五数据
            userCloudDataService.initialization(userId);

            if (!Utils.isNull(dataMap.get("teamId")+"")) {
                //球队加入申请
                JoinTeam joinTeam = new JoinTeam();
                String joinId = Utils.getID(22);
                joinTeam.setJoinId(joinId);
                joinTeam.setUserId(userId);
                joinTeam.setTeamId(dataMap.get("teamId")+"");
                joinTeam.setApplyTime(new Date());
                int type = Integer.parseInt(dataMap.get("type")+"");
                joinTeam.setStatus(type);
                if(type == 2){//直接加入
                    joinTeam.setAuditTime(new Date());
                }
                result =  jointeamMapper.insertJoinTeam(joinTeam);
                if(result<=0){
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }

                if(type == 2){//直接加入
                    TeamMember teamMember = new TeamMember();
                    String memberId = Utils.getID(22);
                    teamMember.setMemberId(memberId);
                    teamMember.setUserId(userId);
                    teamMember.setTeamId(dataMap.get("teamId")+"");
                    teamMember.setIdentity(5);
                    teamMember.setJurisdiction(0);
                    teamMember.setEnqueueTime(new Date());
                    teamMember.setJerseyNo(Integer.parseInt(dataMap.get("jerseyNo")+""));
                    teamMember.setStatus(1);
                    result =  teamMemberMapper.insertTeamMeaMember(teamMember);
                    if(result<=0){
                        return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                    }
                }
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(AppUserService.class, "用户添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户修改
     * @return
     */
    @Override
    public Map<String, Object> updateUser(AppUser user,String credential,String identifier) {
        try {
            //修改密码和手机号
            int result = usersMapper.upPassword(Utils.SHAEncrypt(credential),user.getUserId(),identifier);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //修改基本数据
            result = usersMapper.updateUser(user);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(AppUserService.class, "用户修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 用户删除
     * @return
     */
    @Override
    public Map<String, Object> deleteUser(String adminId) {
        try {
            int result = usersMapper.deleteUserById(adminId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(AppUserService.class, "用户删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }



    /**
     * 查询所有用户
     * @return
     */
    @Override
    public Map<String, Object> getAllUser() {
        try {
            List<AppUser> map = usersMapper.getAllUser();
            return ControllerReturnBase.successResule(map);
        } catch (Exception e) {
            LoggerUtil.outError(AppUserService.class, "查询用户时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    public static int getAge(Date birthDate) {

        if (birthDate == null)
            throw new RuntimeException("出生日期不能为null");

        int age = 0;

        Date now = new Date();

        SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
        SimpleDateFormat format_M = new SimpleDateFormat("MM");

        String birth_year = format_y.format(birthDate);
        String this_year = format_y.format(now);

        String birth_month = format_M.format(birthDate);
        String this_month = format_M.format(now);

        // 初步，估算
        age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);

        // 如果未到出生月份，则age - 1
        if (this_month.compareTo(birth_month) < 0)
            age -= 1;
        if (age < 0)
            age = 0;
        return age;
    }

}
