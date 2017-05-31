package com.yunqiu.service.impl;

import com.yunqiu.dao.TeamMemberMapper;
import com.yunqiu.dao.TestMapper;
import com.yunqiu.model.Team;
import com.yunqiu.model.TeamMember;
import com.yunqiu.model.UserAuths;
import com.yunqiu.model.UserCloudData;
import com.yunqiu.service.TeamService;
import com.yunqiu.service.TestService;
import com.yunqiu.util.DateUtil;
import com.yunqiu.util.ReadExcel;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * Created by 11366 on 2017/3/10.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private TeamService teamService;
    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Override
    public void test() {
        try {
            File file = new File("C:\\Users\\11366\\Desktop\\3.xls");
            String[][] result = ReadExcel.getData(file, 1);
            int rowLength = result.length;
            System.out.println(rowLength);
            for (int i = 0; i < rowLength; i++) {
                //获取到用户id
                String userId = Utils.getUUID();
                //初始化用户资料
                Date birthday = DateUtil.DTStringToDate(result[i][5],"yyyy-MM-dd");
                testMapper.insertUsers(userId,birthday,1,new Date(),
                        Integer.parseInt(result[i][6]),Double.parseDouble(result[i][7]),DateUtil.getAge(birthday),result[i][1]);
                //初始化云五
                this.userCloudData(userId,birthday);
                //添加账户
                this.register(result[i][10],userId);
                //查询球队
                Team team = testMapper.selectTeam(result[i][2]);
                if (team == null){
                    //球队不存在，创建球队
                    team = new Team();
                    team.setTeam_name(result[i][2]);
                    team.setBadge("/uploads/images/20170310/"+result[i][4]);
                    team.setTeam_type(3);
                    team.setEstablish_time(new Date());
                    team.setProvince("北京市");
                    team.setCity("北京市");
                    team.setArea("海淀区");
                    team.setHome("国青乐动新街口足球场");
                    teamService.insertTeam(team,userId);
                }else {
                    //球队存在，添加球队成员
                    TeamMember teamMember = new TeamMember();
                    teamMember.setMember_id(Utils.getUUID());
                    teamMember.setUser_id(userId);
                    teamMember.setTeam_id(team.getTeam_id());
                    teamMember.setIdentity(5);
                    teamMember.setJurisdiction(0);
                    teamMember.setEnqueue_time(new Date());
                    teamMember.setJersey_no(Integer.parseInt(result[i][0]));
                    teamMember.setPlace(result[i][8]);
                    teamMember.setStatus(1);
                    teamMemberMapper.insertTeamMember(teamMember);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updatePassword() {
        try {
            File file = new File("C:\\Users\\11366\\Desktop\\4.xls");
            String[][] result = ReadExcel.getData(file, 0);
            int rowLength = result.length;
            System.out.println(rowLength);
            for (int i = 0; i < rowLength; i++) {
                String phone = result[i][0];
                System.out.println("phone="+phone);
                String password = phone.substring(phone.length()-6,phone.length());
                UserAuths userAuths = testMapper.selectUserAuthsByIdentifier(phone,1);
                System.out.println("object="+userAuths.toString());
                testMapper.updateCredential(Utils.SHAEncrypt(password),userAuths.getUser_id(),1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //添加账户
    public void register(String phone,String userid){
        int type =1;
        String password = "";
        if (Utils.isNull(phone)){
            phone = "11";
            type = 4;
        }else {
            password = phone.substring(phone.length()-6,phone.length());
        }
        UserAuths userAuths = new UserAuths();
        userAuths.setAuth_id(Utils.getUUID());
        userAuths.setUser_id(userid);
        userAuths.setRegistration_time(new Date());
        userAuths.setLogin_time(new Date());
        userAuths.setRegistration_ip("0.0.0.0");
        userAuths.setIdentity_type(type);
        userAuths.setIdentifier(phone);
        if (type == 1){
            userAuths.setCredential(Utils.SHAEncrypt(password));
        }
        testMapper.insertUserAuths(userAuths);
    }

    //用户云五
    public void userCloudData(String userId, Date birthday){
        int age = DateUtil.getAge(birthday);
        int min = 50;
        int max = 70;
        if (age>18 && age<35){
            min = 60;
            max = 70;
        }
        UserCloudData userCloudData = new UserCloudData();
        userCloudData.setCloud_id(Utils.getUUID());
        userCloudData.setUser_id(userId);
        userCloudData.setAttack_gross(Utils.getRandowByScope(max,min));
        userCloudData.setAttack_gains(0);
        userCloudData.setDefensive_gross(Utils.getRandowByScope(max,min));
        userCloudData.setDefensive_gains(0);
        userCloudData.setPhysical_gross(Utils.getRandowByScope(max,min));
        userCloudData.setPhysical_gains(0);
        userCloudData.setTechnology_gross(Utils.getRandowByScope(max,min));
        userCloudData.setTechnology_gains(0);
        userCloudData.setAggressive_gross(Utils.getRandowByScope(max,min));
        userCloudData.setAggressive_gains(0);
        userCloudData.setPower(Utils.getRandowByScope(max,min));
        userCloudData.setGains(0);
        testMapper.insert(userCloudData);
    }
}
