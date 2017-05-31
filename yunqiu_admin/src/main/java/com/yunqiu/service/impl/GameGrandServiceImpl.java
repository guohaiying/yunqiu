package com.yunqiu.service.impl;

import com.yunqiu.dao.GameGrandMapper;
import com.yunqiu.dao.GameMemberMapper;
import com.yunqiu.dao.UserCloudDataMapper;
import com.yunqiu.model.GameGrand;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.UserCloudData;
import com.yunqiu.service.GameGrandService;
import com.yunqiu.service.PlaceService;
import com.yunqiu.service.TeamCloudDataService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameGrandServiceImpl implements GameGrandService {
    @Autowired
    private GameGrandMapper gameGrandMapper;

    @Autowired
    private GameMemberMapper gameMemberMapper;

    @Autowired
    private UserCloudDataMapper userCloudDataMapper;

    @Autowired
    private TeamCloudDataService teamCloudDataService;

    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return gameGrandMapper.selectTotalPage(page);
    }

    // 查询总条数
    public Integer selectCountLie(PageCrt page) {
        return gameGrandMapper.selectTotalPageLie(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return gameGrandMapper.selectePaging(page);
    }
    //分页查询
    public List<Map> selectPagingLie(PageCrt page) {
        return gameGrandMapper.selectePagingLie(page);
    }


    /**
     * 比赛数据添加
     * @return
     */
    @Override
    public Map<String, Object> addGameGrand(GameGrand gameGrand){
        try {
            int result =0;
            result = gameGrandMapper.deleteGameGrandById2(gameGrand.getParentId());
            String grandId=Utils.getID(22);
            gameGrand.setGrandId(grandId);
            result = gameGrandMapper.insertGameGrand(gameGrand);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            return ControllerReturnBase.successResule(grandId);
        } catch (Exception e) {
            LoggerUtil.outError(PlaceService.class, "场地添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 比赛数据添加
     * @return
     */
    @Override
    public Map<String, Object> addGameGrand2(GameGrand gameGrand,int gltype,String gluserId){
        try {
            String grandId=Utils.getID(22);
            gameGrand.setGrandId(grandId);
            int result = gameGrandMapper.insertGameGrand(gameGrand);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }else{
                String grandId2=Utils.getID(22);
                gameGrand.setGrandId(grandId2);
                gameGrand.setType(gltype);
                gameGrand.setUserId(gluserId);
                gameGrand.setParentId(grandId);
                result = gameGrandMapper.insertGameGrand(gameGrand);
                if(result<=0){
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }
            }

            return ControllerReturnBase.successResule(grandId);
        } catch (Exception e) {
            LoggerUtil.outError(PlaceService.class, "场地添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 比赛数据修改
     * @return
     */
    @Override
    public Map<String, Object> updateGameGrand(GameGrand gameGrand,int gltype,String gluserId) {
        try {
            int result = gameGrandMapper.updateGameGrand(gameGrand);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }else{
                gameGrand.setUserId(gluserId);
                gameGrand.setType(gltype);
                gameGrand.setParentId(gameGrand.getGrandId());
                result = gameGrandMapper.updateGameGrand2(gameGrand);
                /*if(result<=0){
                    return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
                }*/
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PlaceService.class, "场地修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 删除比赛数据
     * @return
     */
    @Override
    public Map<String, Object> deleteGameGrandById( String grandId){
        try {
            int result = gameGrandMapper.deleteGameGrandById(grandId);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }else{
                result = gameGrandMapper.deleteGameGrandById2(grandId);
            }
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(PlaceService.class, "场地删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 删除
     * @return
     */
    @Override
    public Integer deleteGameGrandByGameId( String gameId){
        int result = gameGrandMapper.deleteGameGrandByGameId(gameId);
        return result;
    }


    @Override
    public  Map<String, Object> upUserCloudData(String gameId){
        try{
            //1.获取元数据
            List<Map> map = gameGrandMapper.getAllUser(gameId);
            for(int i=0;i<map.size();i++){
                //元数据
                String y_grandId = map.get(i).get("grandId")+"";//主键
                String y_gameIdn = map.get(i).get("gameIdn")+"";//比赛id
                String y_userId = map.get(i).get("userId")+"";//用户id
                String y_teamId = map.get(i).get("teamId")+"";//球队id
                String y_type = map.get(i).get("type")+"";//类型
                int y_age = 0;
                if(Utils.isNull(map.get(i).get("age")+"")){
                    y_age = (int)map.get(i).get("age");//年龄
                }

                double y_weight = 0.0;
                if(Utils.isNull(map.get(i).get("weight")+"")){
                    y_weight = Double.parseDouble(map.get(i).get("weight")+"");//体重
                }
                double y_stature = 0.0;
                if(Utils.isNull(map.get(i).get("weight")+"")){
                    y_stature = this.formatDouble1((int)map.get(i).get("stature")/100);//身高
                }
                int y_playerGoals = Integer.parseInt(map.get(i).get("playerGoals")+"");//球员进球
                int y_teamGoals = Integer.parseInt(map.get(i).get("teamGoals")+"");//球队进球
                int y_playerAssists = Integer.parseInt(map.get(i).get("playerAssists")+"");//球员助攻
                int y_teamAssists = Integer.parseInt(map.get(i).get("teamAssists")+"");//球队助攻
                int y_playerShots = Integer.parseInt(map.get(i).get("playerShots")+"");//球员射门次数
                int y_teamShots = Integer.parseInt(map.get(i).get("teamShots")+"");//球队射门次数
                int y_playerShooting = Integer.parseInt(map.get(i).get("playerShooting")+"");//球员射正次数
                int y_teamShooting = Integer.parseInt(map.get(i).get("teamShooting")+"");//球队射正次数
                int y_playerSteals = Integer.parseInt(map.get(i).get("playerSteals")+"");//球员抢断次数
                int y_teamSteals = Integer.parseInt(map.get(i).get("teamSteals")+"");//球队抢断次数
                int y_playerSiege = Integer.parseInt(map.get(i).get("playerSiege")+"");//球员解围次数
                int y_teamSiege = Integer.parseInt(map.get(i).get("teamSiege")+"");//球队解围次数
                int y_playerSave = Integer.parseInt(map.get(i).get("playerSave")+"");//球员扑救次数
                int y_teamSave = Integer.parseInt(map.get(i).get("teamSave")+"");//球队扑救次数
                int y_playerFree = Integer.parseInt(map.get(i).get("playerFree")+"");//球员任意球
                int y_teamFree = Integer.parseInt(map.get(i).get("teamFree")+"");//球队任意球
                int y_playerCorner = Integer.parseInt(map.get(i).get("playerCorner")+"");//球员角球
                int y_teamCorner = Integer.parseInt(map.get(i).get("teamCorner")+"");//球队角球
                int y_playerThreatpass = Integer.parseInt(map.get(i).get("playerThreatpass")+"");//球员威胁传球
                int y_teamThreatpass = Integer.parseInt(map.get(i).get("teamThreatpass")+"");//球队威胁传球
                int y_playerSuper = Integer.parseInt(map.get(i).get("playerSuper")+"");//球员过人
                int y_teamSuper = Integer.parseInt(map.get(i).get("teamSuper")+"");//球队过人
                int y_playerFoul = Integer.parseInt(map.get(i).get("playerFoul")+"");//球员犯规
                int y_teamFoul = Integer.parseInt(map.get(i).get("teamFoul")+"");//球队犯规
                int y_playerYellow = Integer.parseInt(map.get(i).get("playerYellow")+"");//球员黄牌
                int y_teamYellow = Integer.parseInt(map.get(i).get("teamYellow")+"");//球队黄牌
                int y_playerRed = Integer.parseInt(map.get(i).get("playerRed")+"");//球员红牌
                int y_teamRed = Integer.parseInt(map.get(i).get("teamRed")+"");//球队红牌
                int y_teamNet = Integer.parseInt(map.get(i).get("teamNet")+"");//比赛净胜球数

                /*int y_age = 28;//年龄
                double y_weight = 90;//体重
                double y_stature = 1.8;//身高
                int y_playerGoals = 1;//球员进球
                int y_teamGoals = 4;//球队进球
                int y_playerAssists = 2;//球员助攻
                int y_teamAssists = 4;//球队助攻
                int y_playerShots = 3;//球员射门次数
                int y_teamShots = 7;//球队射门次数
                int y_playerShooting = 2;//球员射正次数
                int y_teamShooting = 4;//球队射正次数
                int y_playerSteals = 2;//球员抢断次数
                int y_teamSteals = 6;//球队抢断次数
                int y_playerSiege = 1;//球员解围次数
                int y_teamSiege = 4;//球队解围次数
                int y_playerSave = 1;//球员扑救次数
                int y_teamSave = 1;//球队扑救次数
                int y_playerFree = 3;//球员任意球
                int y_teamFree = 7;//球队任意球
                int y_playerCorner = 2;//球员角球
                int y_teamCorner = 3;//球队角球
                int y_playerThreatpass = 4;//球员威胁传球
                int y_teamThreatpass =11;//球队威胁传球
                int y_playerSuper = 3;//球员过人
                int y_teamSuper = 14;//球队过人
                int y_playerFoul = 7;//球员犯规
                int y_teamFoul = 13;//球队犯规
                int y_playerYellow = 2;//球员黄牌
                int y_teamYellow = 3;//球队黄牌
                int y_playerRed = 1;//球员红牌
                int y_teamRed = 2;//球队红牌
                int y_teamNet =2;//比赛净胜球数*/


                //阶段1数据
                double o_BMI = this.formatDouble1((y_weight/(y_stature*y_stature)));   //BMI：球员体重/(球员身高*球员身高)
                double o_playerShootOnTarget = 0;
                if(y_playerShots!=0){
                    double t = (float)y_playerShooting/y_playerShots;
                    o_playerShootOnTarget = t*100;   //球员射正率：球员射正次数/球员射门次数
                }
                double o_teamShootOnTarget = 0;
                if(y_teamShots!=0){
                    double t = (float)y_teamShooting/y_teamShots;
                    o_teamShootOnTarget = t*100;   //球队射正率：球队射正次数/球队射门次数
                }
                double o_tournamentRate = 0.5;     //比赛频率：1
                int o_tournamentWeight = 0;     //比赛权重
                if(y_teamNet>=3){
                    o_tournamentWeight=106;
                }else if(y_teamNet==2) {
                    o_tournamentWeight = 103;
                }else if(y_teamNet==1){
                    o_tournamentWeight=100;
                }else if(y_teamNet==0){
                    o_tournamentWeight=97;
                }else if(y_teamNet==-1){
                    o_tournamentWeight=95;
                }else if(y_teamNet==-2){
                    o_tournamentWeight=93;
                }else if(y_teamNet<=-3){
                    o_tournamentWeight=91;
                }

                //阶段2数据
                //1.身体云五
                //年龄
                int t_age = 0;
                if(y_age>=25 && y_age<=28){
                    t_age = 100;
                }else if(y_age>=24 && y_age<=28){
                    t_age = 95;
                }else if(y_age>=23 && y_age<=31){
                    t_age = 90;
                }else if(y_age>=22 && y_age<=32){
                    t_age = 85;
                }else if(y_age>=21 && y_age<=33){
                    t_age = 80;
                }else if(y_age>=20 && y_age<=34){
                    t_age = 75;
                }else if(y_age>=19 && y_age<=35){
                    t_age = 70;
                }else if(y_age>=18 && y_age<=37){
                    t_age = 65;
                }else if(y_age>=17 && y_age<=40){
                    t_age = 60;
                }else if(y_age>=15 && y_age<=43){
                    t_age = 55;
                }else if(y_age>=13 && y_age<=46){
                    t_age = 50;
                }else if(y_age>=11 && y_age<=50){
                    t_age = 45;
                }else if(y_age>=9 && y_age<=55){
                    t_age = 40;
                }else if(y_age>=7 && y_age<=60){
                    t_age = 35;
                }else{
                    t_age = 30;
                }
                //BMI
                int t_BMI = 0;
                if(o_BMI>=21.5 && o_BMI<=22.5){
                    t_BMI = 100;
                }else if(o_BMI>=21 && o_BMI<=23){
                    t_BMI = 95;
                }else if(o_BMI>=20.5 && o_BMI<=23.5){
                    t_BMI = 90;
                }else if(o_BMI>=20 && o_BMI<=24){
                    t_BMI = 85;
                }else if(o_BMI>=19.5 && o_BMI<=24.5){
                    t_BMI = 80;
                }else if(o_BMI>=19 && o_BMI<=25){
                    t_BMI = 75;
                }else if(o_BMI>=18.5 && o_BMI<=25.5){
                    t_BMI = 70;
                }else if(o_BMI>=18 && o_BMI<=26){
                    t_BMI = 65;
                }else if(o_BMI>=17.5 && o_BMI<=26.5){
                    t_BMI = 60;
                }else if(o_BMI>=17 && o_BMI<=27){
                    t_BMI = 55;
                }else if(o_BMI>=16.5 && o_BMI<=28){
                    t_BMI = 50;
                }else if(o_BMI>=16 && o_BMI<=29){
                    t_BMI = 45;
                }else if(o_BMI>=15.5 && o_BMI<=30){
                    t_BMI = 40;
                }else if(o_BMI>=15 && o_BMI<=31){
                    t_BMI = 35;
                }else{
                    t_BMI = 30;
                }
                //比赛频率
                int t_tournamentRate = 0;
                if(o_tournamentRate>=14){
                    t_tournamentRate = 100;
                }else if(o_tournamentRate>=13){
                    t_tournamentRate = 95;
                }else if(o_tournamentRate>=12){
                    t_tournamentRate = 90;
                }else if(o_tournamentRate>=11){
                    t_tournamentRate = 85;
                }else if(o_tournamentRate>=10){
                    t_tournamentRate = 80;
                }else if(o_tournamentRate>=9){
                    t_tournamentRate = 75;
                }else if(o_tournamentRate>=8){
                    t_tournamentRate = 70;
                }else if(o_tournamentRate>=7){
                    t_tournamentRate = 65;
                }else if(o_tournamentRate>=6){
                    t_tournamentRate = 60;
                }else if(o_tournamentRate>=5){
                    t_tournamentRate = 55;
                }else if(o_tournamentRate>=4){
                    t_tournamentRate = 50;
                }else if(o_tournamentRate>=3){
                    t_tournamentRate = 45;
                }else if(o_tournamentRate>=2){
                    t_tournamentRate = 40;
                }else if(o_tournamentRate>=1){
                    t_tournamentRate = 35;
                }else{
                    t_tournamentRate = 30;
                }
                //1.进攻云五
                //球队进球数
                int t_teamGoals = 0;
                if(y_teamGoals>=3){
                    t_teamGoals=100;
                }else if(y_teamGoals==2){
                    t_teamGoals=80;
                }else if(y_teamGoals==1){
                    t_teamGoals=70;
                }else if(y_teamGoals==0){
                    t_teamGoals=30;
                }
                //进球得分
                int t_goalScoring = 0;
                double jinqiushu = 0;
                if(y_teamGoals!=0){
                    double t = (float)y_playerGoals/y_teamGoals;
                    jinqiushu = t*100;
                }
                if(jinqiushu>=100){
                    t_goalScoring= t_teamGoals/100*100 ;
                }else if(jinqiushu>=90){
                    t_goalScoring= t_teamGoals/100*95 ;
                }else if(jinqiushu>=80){
                    t_goalScoring= t_teamGoals/100*90 ;
                }else if(jinqiushu>=70){
                    t_goalScoring= t_teamGoals/100*85 ;
                }else if(jinqiushu>=60){
                    t_goalScoring= t_teamGoals/100*80 ;
                }else if(jinqiushu>=50){
                    t_goalScoring= t_teamGoals/100*75 ;
                }else if(jinqiushu>=40){
                    t_goalScoring= t_teamGoals/100*70 ;
                }else if(jinqiushu>=35){
                    t_goalScoring= t_teamGoals/100*65 ;
                }else if(jinqiushu>=30){
                    t_goalScoring= t_teamGoals/100*60 ;
                }else if(jinqiushu>=25){
                    t_goalScoring= t_teamGoals/100*55 ;
                }else if(jinqiushu>=20){
                    t_goalScoring= t_teamGoals/100*50 ;
                }else if(jinqiushu>=15){
                    t_goalScoring= t_teamGoals/100*45 ;
                }else if(jinqiushu>=10){
                    t_goalScoring= t_teamGoals/100*40 ;
                }else if(jinqiushu>=5){
                    t_goalScoring= t_teamGoals/100*35 ;
                }else{
                    t_goalScoring= 30 ;
                }

                //助攻
                int t_teamAssists = 0;
                if(y_teamAssists>=3){
                    t_teamAssists=100;
                }else if(y_teamAssists==2){
                    t_teamAssists=80;
                }else if(y_teamAssists==1){
                    t_teamAssists=70;
                }else if(y_teamAssists==0){
                    t_teamAssists=30;
                }
                //助攻得分
                int t_assistPoint = 0;
                double zhugongshu = 0;
                if(y_teamAssists!=0){
                    zhugongshu =((float)y_playerAssists/y_teamAssists)*100;
                }
                if(zhugongshu >= 100){
                    t_assistPoint= t_teamAssists/100*100;
                }else if(zhugongshu >= 90){
                    t_assistPoint= t_teamAssists/100*95;
                }else if(zhugongshu >= 80){
                    t_assistPoint= t_teamAssists/100*90;
                }else if(zhugongshu >= 70){
                    t_assistPoint= t_teamAssists/100*85;
                }else if(zhugongshu >= 60){
                    t_assistPoint= t_teamAssists/100*80;
                }else if(zhugongshu >= 50){
                    t_assistPoint= t_teamAssists/100*75;
                }else if(zhugongshu >= 40){
                    t_assistPoint= t_teamAssists/100*70;
                }else if(zhugongshu >= 35){
                    t_assistPoint= t_teamAssists/100*65;
                }else if(zhugongshu >= 30){
                    t_assistPoint= t_teamAssists/100*60;
                }else if(zhugongshu >= 25){
                    t_assistPoint= t_teamAssists/100*55;
                }else if(zhugongshu >= 20){
                    t_assistPoint= t_teamAssists/100*50;
                }else if(zhugongshu >= 15){
                    t_assistPoint= t_teamAssists/100*45;
                }else if(zhugongshu >= 10){
                    t_assistPoint= t_teamAssists/100*40;
                }else if(zhugongshu >= 5){
                    t_assistPoint= t_teamAssists/100*35;
                }else{
                    t_assistPoint= 30;
                }

                //射门次数得分
                int t_shootCount = 0;
                double shemen = 0;
                if(y_teamShots!=0){
                    shemen =((float)y_playerShots/y_teamShots)*100;
                }
                if(shemen>=70){
                    t_shootCount = 100;
                }else if(shemen>=60){
                    t_shootCount = 95;
                }else if(shemen>=50){
                    t_shootCount = 90;
                }else if(shemen>=40){
                    t_shootCount = 85;
                }else if(shemen>=30){
                    t_shootCount = 75;
                }else if(shemen>=20){
                    t_shootCount = 55;
                }else if(shemen>=5){
                    t_shootCount = 50;
                }else{
                    t_shootCount = 30;
                }
                //射正率得分
                int t_shooting = 0;
                if(o_playerShootOnTarget>=90){
                    t_shooting = 100;
                }else if(o_playerShootOnTarget>=80){
                    t_shooting = 95;
                }else if(o_playerShootOnTarget>=70){
                    t_shooting = 90;
                }else if(o_playerShootOnTarget>=60){
                    t_shooting = 85;
                }else if(o_playerShootOnTarget>=50){
                    t_shooting = 80;
                }else if(o_playerShootOnTarget>=45){
                    t_shooting = 75;
                }else if(o_playerShootOnTarget>=40){
                    t_shooting = 70;
                }else if(o_playerShootOnTarget>=35){
                    t_shooting = 65;
                }else if(o_playerShootOnTarget>=30){
                    t_shooting = 60;
                }else if(o_playerShootOnTarget>=25){
                    t_shooting = 55;
                }else if(o_playerShootOnTarget>=20){
                    t_shooting = 50;
                }else if(o_playerShootOnTarget>=15){
                    t_shooting = 45;
                }else if(o_playerShootOnTarget>=10){
                    t_shooting = 40;
                }else if(o_playerShootOnTarget>=5){
                    t_shooting = 35;
                }else{
                    t_shooting = 30;
                }

                //3.防守得分
                //抢断得分
                int t_steal = 0;
                double qiangduan = 0;
                if(y_teamSteals!=0){
                    qiangduan = ((float)y_playerSteals/y_teamSteals)*100;
                }
                if(qiangduan>=50) {
                    t_steal=100;
                }else if(qiangduan>=40){
                    t_steal=95;
                }else if(qiangduan>=35){
                    t_steal=85;
                }else if(qiangduan>=30){
                    t_steal=80;
                }else if(qiangduan>=25){
                    t_steal=65;
                }else if(qiangduan>=20){
                    t_steal=60;
                }else if(qiangduan>=10){
                    t_steal=50;
                }else if(qiangduan>=5){
                    t_steal=40;
                }else{
                    t_steal=30;
                }

                //解围得分
                int t_debarrass = 0;
                double jieweishu = 0;
                if(y_teamSiege!=0){
                    jieweishu = ((float)y_playerSiege/y_teamSiege)*100;
                }
                if(jieweishu>=60){
                    t_debarrass=100;
                }else if(jieweishu>=50){
                    t_debarrass=95;
                }else if(jieweishu>=40){
                    t_debarrass=85;
                }else if(jieweishu>=30){
                    t_debarrass=70;
                }else if(jieweishu>=20){
                    t_debarrass=60;
                }else if(jieweishu>=10){
                    t_debarrass=55;
                }else if(jieweishu>=5){
                    t_debarrass=50;
                }else{
                    t_debarrass=30;
                }
                //扑救得分
                int t_save = 0;
                if(y_playerSave>=10){
                    t_save= 100;
                }else if(y_playerSave==9){
                    t_save= 95;
                }else if(y_playerSave==8){
                    t_save= 90;
                }else if(y_playerSave==7){
                    t_save= 85;
                }else if(y_playerSave==6){
                    t_save= 80;
                }else if(y_playerSave==5){
                    t_save= 75;
                }else if(y_playerSave==4){
                    t_save= 70;
                }else if(y_playerSave==3){
                    t_save= 65;
                }else if(y_playerSave==2){
                    t_save= 60;
                }else if(y_playerSave==1){
                    t_save= 55;
                }else{
                    t_save=30;
                }

                //4.技术得分
                //任意球
                int t_freeKick = 0;
                if(y_teamGoals>=3){
                    t_freeKick=100;
                }else if(y_teamGoals==2){
                    t_freeKick=80;
                }else if(y_teamGoals==1){
                    t_freeKick=70;
                }else if(y_teamGoals==0){
                    t_freeKick=30;
                }
                //任意球得分
                int t_freeKickScore = 0;
                double renyiqiu = 0;
                if(y_teamFree!=0){
                    renyiqiu = ((float)y_playerFree/y_teamFree)*100;
                }
                if(renyiqiu>=100){
                    t_freeKickScore = t_freeKick/100*100;
                }else if(renyiqiu>=90){
                    t_freeKickScore = t_freeKick/100*95;
                }else if(renyiqiu>=80){
                    t_freeKickScore = t_freeKick/100*90;
                }else if(renyiqiu>=70){
                    t_freeKickScore = t_freeKick/100*85;
                }else if(renyiqiu>=60){
                    t_freeKickScore = t_freeKick/100*80;
                }else if(renyiqiu>=50){
                    t_freeKickScore = t_freeKick/100*75;
                }else if(renyiqiu>=40){
                    t_freeKickScore = t_freeKick/100*70;
                }else if(renyiqiu>=35){
                    t_freeKickScore = t_freeKick/100*65;
                }else if(renyiqiu>=30){
                    t_freeKickScore = t_freeKick/100*60;
                }else if(renyiqiu>=25){
                    t_freeKickScore = t_freeKick/100*55;
                }else if(renyiqiu>=20){
                    t_freeKickScore = t_freeKick/100*50;
                }else if(renyiqiu>=15){
                    t_freeKickScore = t_freeKick/100*45;
                }else if(renyiqiu>=10){
                    t_freeKickScore = t_freeKick/100*40;
                }else if(renyiqiu>=5){
                    t_freeKickScore = t_freeKick/100*35;
                }else{
                    t_freeKickScore = 30;
                }
                //角球
                int t_corner = 0;
                if(y_teamCorner>=3){
                    t_corner=100;
                }else if(y_teamCorner==2){
                    t_corner=80;
                }else if(y_teamCorner==1){
                    t_corner=70;
                }else if(y_teamCorner==0){
                    t_corner=30;
                }
                //角球得分
                int t_cornerScore = 0;
                double jiaoqiushu = 0;
                if(y_teamCorner!=0){
                    jiaoqiushu = ((float)y_playerCorner/y_teamCorner)*100;
                }
                if(jiaoqiushu>=100){
                    t_cornerScore = t_corner/100*100;
                }else if(jiaoqiushu>=90){
                    t_cornerScore = t_corner/100*95;
                }else if(jiaoqiushu>=80){
                    t_cornerScore = t_corner/100*90;
                }else if(jiaoqiushu>=70){
                    t_cornerScore = t_corner/100*85;
                }else if(jiaoqiushu>=60){
                    t_cornerScore = t_corner/100*80;
                }else if(jiaoqiushu>=50){
                    t_cornerScore = t_corner/100*75;
                }else if(jiaoqiushu>=40){
                    t_cornerScore = t_corner/100*70;
                }else if(jiaoqiushu>=35){
                    t_cornerScore = t_corner/100*65;
                }else if(jiaoqiushu>=30){
                    t_cornerScore = t_corner/100*60;
                }else if(jiaoqiushu>=25){
                    t_cornerScore = t_corner/100*55;
                }else if(jiaoqiushu>=20){
                    t_cornerScore = t_corner/100*50;
                }else if(jiaoqiushu>=15){
                    t_cornerScore = t_corner/100*45;
                }else if(jiaoqiushu>=10){
                    t_cornerScore = t_corner/100*40;
                }else if(jiaoqiushu>=5){
                    t_cornerScore = t_corner/100*35;
                }else{
                    t_cornerScore = 30;
                }
                //威胁传球得分
                int t_threatpass = 0;
                double weixie = 0;
                if(y_teamThreatpass!=0){
                    weixie = ((float)y_playerThreatpass/y_teamThreatpass)*100;
                }
                if(weixie>=80){
                    t_threatpass = 100;
                }else if(weixie>=70){
                    t_threatpass = 95;
                }else if(weixie>=60){
                    t_threatpass = 90;
                }else if(weixie>=50){
                    t_threatpass = 85;
                }else if(weixie>=45){
                    t_threatpass = 80;
                }else if(weixie>=40){
                    t_threatpass = 75;
                }else if(weixie>=35){
                    t_threatpass = 70;
                }else if(weixie>=30){
                    t_threatpass = 65;
                }else if(weixie>=25){
                    t_threatpass = 60;
                }else if(weixie>=20){
                    t_threatpass = 55;
                }else if(weixie>=15){
                    t_threatpass = 50;
                }else if(weixie>=10){
                    t_threatpass = 45;
                }else if(weixie>=5){
                    t_threatpass = 40;
                }else{
                    t_threatpass = 30;
                }
                //过人得分
                int t_Super = 0;
                double guoren = 0;
                if(y_teamSuper!=0){
                    guoren = ((float)y_playerSuper/y_teamSuper)*100;
                }
                if(guoren>=70){
                    t_Super = 100;
                }else if(guoren>=60){
                    t_Super = 95;
                }else if(guoren>=50){
                    t_Super = 90;
                }else if(guoren>=45){
                    t_Super = 85;
                }else if(guoren>=40){
                    t_Super = 80;
                }else if(guoren>=35){
                    t_Super = 75;
                }else if(guoren>=30){
                    t_Super = 70;
                }else if(guoren>=25){
                    t_Super = 65;
                }else if(guoren>=20){
                    t_Super = 60;
                }else if(guoren>=15){
                    t_Super = 55;
                }else if(guoren>=10){
                    t_Super = 50;
                }else if(guoren>=5){
                    t_Super = 45;
                }else{
                    t_Super = 30;
                }
                //5.侵略性云五
                //犯规得分
                int t_foul = 0;
                double fangui = 0;
                if(y_teamFoul!=0){
                    fangui = ((float)y_playerFoul/y_teamFoul)*100;
                }
                if(fangui>=40){
                    t_foul= 100;
                }else if(fangui>=30){
                    t_foul= 95;
                }else if(fangui>=20){
                    t_foul=75;
                }else if(fangui>=10){
                    t_foul=55;
                }else if(fangui>=5){
                    t_foul=50;
                }else{
                    t_foul=30;
                }
                //黄牌得分
                int t_yellow = 0;
                double huanpai = 0;
                if(y_teamYellow!=0){
                    huanpai = ((float)y_playerYellow/y_teamYellow)*100;
                }
                if(huanpai>=40){
                    t_yellow=100;
                }else if(huanpai>=30){
                    t_yellow=95;
                }else if(huanpai>=20){
                    t_yellow=75;
                }else if(huanpai>=10){
                    t_yellow=55;
                }else if(huanpai>=5){
                    t_yellow=50;
                }else{
                    t_yellow=30;
                }
                //红牌得分
                int t_red = 0;
                double hongpai = 0;
                if(y_teamRed!=0){
                    hongpai = ((float)y_playerRed/y_teamRed)*100;
                }
                if(hongpai>=50){
                    t_red=100;
                }else if(hongpai>=40){
                    t_red=95;
                }else if(hongpai>=30){
                    t_red=85;
                }else if(hongpai>=20){
                    t_red=75;
                }else if(hongpai>=5){
                    t_red=50;
                }else{
                    t_red=30;
                }

                //阶段3
                //身体 身体得分：年龄得分*20%+BMI得分*30%+比赛频率*50%
                double f_body = t_age*0.2 + t_BMI*0.3 + t_tournamentRate*0.5;
                //进攻得分：进球得分*30%+助攻得分*20%+射门次数得分*30%+射正率得分*20%
                double f_attack = t_goalScoring*0.3+t_assistPoint*0.2+t_shootCount*0.3+t_shooting*0.2;
                //防守得分：抢断得分*50%+解围得分*50%
                double f_steal = t_steal*0.5 + t_debarrass*0.5;
                //技术得分：任意球得分*30%+角球得分*10%+威胁传球得分*30%+过人得分*30%
                double f_skill = t_freeKickScore*0.3 + t_cornerScore*0.1 + t_threatpass*0.3 + t_Super*0.3;
                //侵略性得分：犯规得分*50%+黄牌得分*20%+红牌得分*30%
                double f_aggressivity = t_foul*0.5 + t_yellow*0.2 + t_red*0.3;

                //5.生成最新的球员云五值
                //封装最后的云五
                double body = 0.0;
                double attack = 0.0;
                double steal= 0.0;
                double skill = 0.0;
                double aggressivity= 0.0;

                //查询用户的第几场比赛
                int count = gameMemberMapper.getUserGameCount(y_userId);
                if(count<=1){//比赛后云五值：注册后生成的各项云五值*50%+第1场比赛产生的云五值*50%
                    UserCloudData cloudData = userCloudDataMapper.selectUserCloudDataByUserId(y_userId);
                    body = cloudData.getPhysical_gross()*0.5 + f_body*0.5;
                    attack = cloudData.getAttack_gross()*0.5 + f_attack*0.5;
                    steal = cloudData.getDefensive_gross()*0.5 + f_steal*0.5;
                    skill = cloudData.getTechnology_gross()*0.5 + f_skill*0.5;
                    aggressivity = cloudData.getAggressive_gross()*0.5 + f_aggressivity*0.5;
                }else if (count==2){//比赛后云五值：第1场比赛之后的各项云五值*40%+第2场比赛产生的云五值*60%
                    UserCloudData cloudData = userCloudDataMapper.selectUserCloudDataByUserId(y_userId);
                    body = cloudData.getPhysical_gross()*0.4 + f_body*0.6;
                    attack = cloudData.getAttack_gross()*0.4 + f_attack*0.6;
                    steal = cloudData.getDefensive_gross()*0.4 + f_steal*0.6;
                    skill = cloudData.getTechnology_gross()*0.4 + f_skill*0.6;
                    aggressivity = cloudData.getAggressive_gross()*0.4 + f_aggressivity*0.6;
                }else if (count==3){//比赛后云五值：第2场比赛之后的各项云五值*30%+第3场比赛产生的云五值*70%
                    UserCloudData cloudData = userCloudDataMapper.selectUserCloudDataByUserId(y_userId);
                    body = cloudData.getPhysical_gross()*0.3 + f_body*0.7;
                    attack = cloudData.getAttack_gross()*0.3 + f_attack*0.7;
                    steal = cloudData.getDefensive_gross()*0.3 + f_steal*0.7;
                    skill = cloudData.getTechnology_gross()*0.3 + f_skill*0.7;
                    aggressivity = cloudData.getAggressive_gross()*0.3 + f_aggressivity*0.7;
                }else if (count>=4){//比赛后云五值：上一场比赛之后的各项云五值*20%+本场比赛产生的云五值*80%
                    UserCloudData cloudData = userCloudDataMapper.selectUserCloudDataByUserId(y_userId);
                    body = cloudData.getPhysical_gross()*0.2 + f_body*0.8;
                    attack = cloudData.getAttack_gross()*0.2 + f_attack*0.8;
                    steal = cloudData.getDefensive_gross()*0.2 + f_steal*0.8;
                    skill = cloudData.getTechnology_gross()*0.2 + f_skill*0.8;
                    aggressivity = cloudData.getAggressive_gross()*0.2 + f_aggressivity*0.8;
                }

                //修改云五数据表的数据
                UserCloudData userCloudData = new UserCloudData();
                userCloudData.setUser_id(y_userId);
                userCloudData.setPhysical_gross((int)body);
                userCloudData.setAttack_gross((int)attack);
                userCloudData.setDefensive_gross((int)steal);
                userCloudData.setTechnology_gross((int)skill);
                userCloudData.setAggressive_gross((int)aggressivity);

                userCloudDataMapper.updateUserCloudData(userCloudData);
            }

            teamCloudDataService.computeCloudDataByGame(gameId);

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", "1");
            return ControllerReturnBase.successResule(requst_parm);
        }catch (Exception e){
            LoggerUtil.outError(GameGrandService.class, "更新云五数据时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }


    /**
     * 保留一位小数，四舍五入的一个老土的方法
     * @param d
     * @return
     */
    public static double formatDouble1(double d) {
        return (double)Math.round(d*10)/10;
    }


}
