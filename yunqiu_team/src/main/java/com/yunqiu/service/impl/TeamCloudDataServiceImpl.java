package com.yunqiu.service.impl;

import com.yunqiu.dao.GeneralMapper;
import com.yunqiu.dao.TeamCloudDataMapper;
import com.yunqiu.model.TeamCloudData;
import com.yunqiu.service.TeamCloudDataService;
import com.yunqiu.util.DateUtil;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11366 on 2017/3/8.
 */

@Service
public class TeamCloudDataServiceImpl implements TeamCloudDataService {
    @Autowired
    private TeamCloudDataMapper teamCloudDataMapper;
    @Autowired
    private GeneralMapper generalMapper;

    /**
     * 初始化云五数据(创建球队时使用)
     */
    @Override
    public boolean initialization(String team_id) {
        try {
            TeamCloudData teamCloudData = new TeamCloudData();
            teamCloudData.setCloud_id(Utils.getUUID());
            teamCloudData.setTeam_id(team_id);
            teamCloudData.setAttack_gross(Utils.getRandowByScope());
            teamCloudData.setAttack_gains(0);
            teamCloudData.setDefensive_gross(Utils.getRandowByScope());
            teamCloudData.setDefensive_gains(0);
            teamCloudData.setPhysical_gross(Utils.getRandowByScope());
            teamCloudData.setPhysical_gains(0);
            teamCloudData.setTechnology_gross(Utils.getRandowByScope());
            teamCloudData.setTechnology_gains(0);
            teamCloudData.setAggressive_gross(Utils.getRandowByScope());
            teamCloudData.setAggressive_gains(0);
            teamCloudData.setMean_power(Utils.getRandowByScope());
            teamCloudData.setGains(0);
            teamCloudData.setCount_time(new Date());
            teamCloudDataMapper.insert(teamCloudData);
            return true;
        }catch (Exception e){
            LoggerUtil.outError(TeamCloudDataServiceImpl.class,"初始化云五数据时发生错误",e);
            return false;
        }
    }

    /**
     * 录入比赛站报时计算云五值
     * @param game_id
     * @return
     */
    @Override
    public boolean computeCloudDataByGame(String game_id) {
        try {
            //判断赛事是否存在
            Map<String,Object> game = generalMapper.selectGameByGameId(game_id);
            if (game == null){
                return false;
            }
            String teamA_id = (String) game.get("entry_teamA");
            String teamB_id = (String) game.get("entry_teamB");
            /**
             * #############################################################################
             * 计算元数据(比赛全项数据)
             * #############################################################################
             */
            List<Map<String,Object>> entire_list = generalMapper.selectEntireGrand(game_id);
            int entire_number = entire_list.size();
            //循环解析元数据，获取AB球队数据
            //射正次数
            int teamA_shotOnTarget = 0;
            int teamB_shotOnTarget = 0;
            //射门次数
            int teamA_shoot = 0;
            int teamB_shoot = 0;
            //进球数
            int teamA_goal = 0;
            int teamB_goal = 0;
            //抢断数
            int teamA_steal = 0;
            int teamB_steal = 0;
            //扑救数
            int teamA_diving = 0;
            int teamB_diving = 0;
            //威胁传球
            int teamA_threat = 0;
            int teamB_threat = 0;
            //过人
            int teamA_surpass = 0;
            int teamB_surpass = 0;
            //犯规
            int teamA_foul = 0;
            int teamB_foul = 0;
            //黄牌
            int teamA_yellowCard = 0;
            int teamB_yellowCard = 0;
            //循环解析全项数据
            for (int item=0;item<entire_number;item++){
                Map<String,Object> entire_one = entire_list.get(item);
                //获取射正次数
                if ((int)entire_one.get("type") == 8){
                    if ((int)entire_one.get("attribution") == 1){
                        //A队射正次数
                        teamA_shotOnTarget = (int)entire_one.get("total_number");
                    }else {
                        //B队射正次数
                        teamB_shotOnTarget = (int)entire_one.get("total_number");
                    }
                    //获取射门次数
                }else if ((int)entire_one.get("type") == 7){
                    if ((int)entire_one.get("attribution") == 1){
                        //A队射门次数
                        teamA_shoot = (int)entire_one.get("total_number");
                    }else {
                        //B队射门次数
                        teamB_shoot = (int)entire_one.get("total_number");
                    }
                }else if ((int)entire_one.get("type") == 1){
                    if ((int)entire_one.get("attribution") == 1){
                        //A队进球数
                        teamA_goal = (int)entire_one.get("total_number");
                    }else {
                        //B队进球数
                        teamB_goal = (int)entire_one.get("total_number");
                    }
                }else if ((int)entire_one.get("type") == 13){
                    if ((int)entire_one.get("attribution") == 1){
                        //A队抢断数
                        teamA_steal = (int)entire_one.get("total_number");
                    }else {
                        //B队抢断数
                        teamB_steal = (int)entire_one.get("total_number");
                    }
                }else if ((int)entire_one.get("type") == 15){
                    if ((int)entire_one.get("attribution") == 1){
                        //A队扑救数
                        teamA_diving = (int)entire_one.get("total_number");
                    }else {
                        //B队扑救数
                        teamB_diving = (int)entire_one.get("total_number");
                    }
                }else if ((int)entire_one.get("type") == 10){
                    if ((int)entire_one.get("attribution") == 1){
                        //A队威胁传球
                        teamA_threat = (int)entire_one.get("total_number");
                    }else {
                        //B队威胁传球
                        teamB_threat = (int)entire_one.get("total_number");
                    }
                }else if ((int)entire_one.get("type") == 9){
                    if ((int)entire_one.get("attribution") == 1){
                        //A队过人
                        teamA_surpass = (int)entire_one.get("total_number");
                    }else {
                        //B队过人
                        teamB_surpass = (int)entire_one.get("total_number");
                    }
                }else if ((int)entire_one.get("type") == 9){
                    if ((int)entire_one.get("attribution") == 1){
                        //A队犯规
                        teamA_foul = (int)entire_one.get("total_number");
                    }else {
                        //B队犯规
                        teamB_foul = (int)entire_one.get("total_number");
                    }
                }else if ((int)entire_one.get("type") == 9){
                    if ((int)entire_one.get("attribution") == 1){
                        //A队黄牌
                        teamA_yellowCard = (int)entire_one.get("total_number");
                    }else {
                        //B队黄牌
                        teamB_yellowCard = (int)entire_one.get("total_number");
                    }
                }

            }
            /**
             * #############################################################################
             * 计算球队射正率（球队射正次数/球队射门次数），保留两位小数
             * #############################################################################
             */
            //A队射正率
            int teamA_targetAate = 0;
            //B队射正率
            int teamB_targetAate = 0;
            //计算A球队射正率
            if (teamA_shotOnTarget != 0){
                teamA_targetAate =(int)((new BigDecimal((float)teamA_shotOnTarget/teamA_shoot).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
            }
            //计算B球队射正率
            if (teamB_shotOnTarget != 0){
                teamB_targetAate =(int)((new BigDecimal((float)teamB_shotOnTarget/teamB_shoot).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
            }
            /**
             * #############################################################################
             * 计算比赛频率（暂时给固定值5）
             * #############################################################################
             */
            //A队比赛频率
            double teamA_gameFrequency = 5;
            //B队比赛频率
            double teamB_gameFrequency = 5;
            /**
             * #############################################################################
             * 计算云五各项得分(该计算得出的不是云五值，只是用于云五值计算的参数)
             * #############################################################################
             */
            //身体得分
            int teamA_bodyScore = this.computeBodyScore(teamA_gameFrequency);
            int teamB_bodyScore = this.computeBodyScore(teamB_gameFrequency);
            //进攻得分
            int total_goal = teamA_goal+teamB_goal;//AB两队总进球数
            int totalShoot = teamA_shoot+teamB_shoot;//AB两队总射门数
            Map<String,Object> teamA_aAttackScore = this.computeAttackScore(teamA_goal,total_goal,teamA_shoot,totalShoot,teamA_targetAate);
            Map<String,Object> teamB_aAttackScore = this.computeAttackScore(teamB_goal,total_goal,teamB_shoot,totalShoot,teamA_targetAate);
            //防守得分
            int totalSteal = teamA_steal+teamB_steal;
            Map<String,Object> teamA_DefenseScore = this.computeDefenseScore(teamB_goal,total_goal,teamA_steal,totalSteal,teamA_diving,teamB_shotOnTarget);
            Map<String,Object> teamB_DefenseScore = this.computeDefenseScore(teamA_goal,total_goal,teamB_steal,totalSteal,teamB_diving,teamA_shotOnTarget);
            //技术得分
            int totalThreat = teamA_threat+teamB_threat;
            int totalSurpass = teamA_surpass+teamB_surpass;
            Map<String,Object> teamA_technicScore = this.computeTechnicScore(teamA_threat,totalThreat,teamA_surpass,totalSurpass);
            Map<String,Object> teamB_technicScore = this.computeTechnicScore(teamB_threat,totalThreat,teamB_surpass,totalSurpass);
            //侵略得分
            int totalFoul = teamA_foul+teamB_foul;
            int totalYellowCard = teamA_yellowCard+teamB_yellowCard;
            Map<String,Object> teamA_invadeScore = this.computeInvadeScore(teamA_foul,totalFoul,teamA_yellowCard,totalYellowCard);
            Map<String,Object> teamB_invadeScore = this.computeInvadeScore(teamB_foul,totalFoul,teamB_yellowCard,totalYellowCard);
            /**
             * #############################################################################
             * 计算临时云五值
             * #############################################################################
             */
            //身体临时云五值
            double teamA_temporary_bodyCloudData = teamA_bodyScore*1.0;
            double teamB_temporary_bodyCloudData = teamB_bodyScore*1.0;
            //进攻临时云五值
            double teamA_temporary_aAttackCloudData = ((double)teamA_aAttackScore.get("goalScore")*0.5)+
                    ((double)teamA_aAttackScore.get("shootScore")*0.3)+((double)teamA_aAttackScore.get("targetAateScore")*0.2);
            double teamB_temporary_aAttackCloudData =  ((double)teamB_aAttackScore.get("goalScore")*0.5)+
                    ((double)teamB_aAttackScore.get("shootScore")*0.3)+((double)teamB_aAttackScore.get("targetAateScore")*0.2);
            //防守临时云五值
            double teamA_temporary_defenseCloudData =((double)teamA_DefenseScore.get("stealScore")*0.3)+
                    ((double)teamA_DefenseScore.get("divingScore")*0.2)+((double)teamA_DefenseScore.get("goalScore")*0.5);
            double teamB_temporary_defenseCloudData =((double)teamB_DefenseScore.get("stealScore")*0.3)+
                    ((double)teamB_DefenseScore.get("divingScore")*0.2)+((double)teamB_DefenseScore.get("goalScore")*0.5);
            //技术临时云五值
            double teamA_temporary_technicCloudData = ((double)teamA_technicScore.get("threatScore")*0.5)+
                    ((double)teamA_technicScore.get("surpassScore")*0.5);
            double teamB_temporary_technicCloudData = ((double)teamB_technicScore.get("threatScore")*0.5)+
                    ((double)teamB_technicScore.get("surpassScore")*0.5);
            //侵略临时云五值
            double teamA_temporary_invadeCloudData = ((double)teamA_invadeScore.get("foulScore")*0.6)+
                    ((double)teamA_invadeScore.get("yellowCardScore")*0.4);
            double teamB_temporary_invadeCloudData = ((double)teamB_invadeScore.get("foulScore")*0.6)+
                    ((double)teamB_invadeScore.get("yellowCardScore")*0.4);
            /**
             * #############################################################################
             * 合成之前的云五值，生成新的云五值
             * #############################################################################
             */
            TeamCloudData teamACloudData = this.computeCloudData(teamA_id,game_id,teamA_temporary_bodyCloudData,teamA_temporary_aAttackCloudData,
                    teamA_temporary_defenseCloudData,teamA_temporary_technicCloudData,teamA_temporary_invadeCloudData);
            TeamCloudData teamBCloudData = this.computeCloudData(teamB_id,game_id,teamB_temporary_bodyCloudData,teamB_temporary_aAttackCloudData,
                    teamB_temporary_defenseCloudData,teamB_temporary_technicCloudData,teamB_temporary_invadeCloudData);
            return true;
        }catch (Exception e){
            LoggerUtil.outError(TeamCloudDataServiceImpl.class,"录入比赛战报时计算云五值发生错误",e);
            return false;
        }
    }

    /**
     * 计算新的云五值
     * @param team_id  球队id
     * @param game_id  比赛id
     * @param bodyCloudData  身体临时云五
     * @param aAttackCloudData 进攻临时云五
     * @param defenseCloudData 防守临时云五
     * @param technicCloudData 技术临时云五
     * @param invadeCloudData 侵略临时云五
     * @return
     */
    public TeamCloudData computeCloudData(String team_id,String game_id,double bodyCloudData,double aAttackCloudData,
                                          double defenseCloudData,double technicCloudData,double invadeCloudData){
        String current_date = DateUtil.getStringTime("yyyy-MM-dd HH:mm:ss");//当前日期
        String anciently_date = DateUtil.foregroundDay(-30);//当前日期前30天的日期
        //获取最近30天已结束的比赛
        List<Map<String,Object>> team_gameList = generalMapper.selectGameByTeamIdAndTime(team_id,current_date,anciently_date);
        //计算当前次数
        int team_intUse = 0;
        for (int item = 0;item < team_gameList.size();item++){
            team_intUse = team_intUse+1;
            if (game_id.equals(team_gameList.get(item).get("game_id"))){
                break;
            }
            if (team_intUse >= 4){
                break;
            }
        }
        //获取原始云五值
        TeamCloudData db_teamCloudData = teamCloudDataMapper.selectUserCloudDataByUserIdOne( team_id);
        //计算出生产新的云五值
        int attack_gross =0;//进攻
        int attack_gains =0;
        int defensive_gross =0;//防守
        int defensive_gains =0;
        int physical_gross =0;//体能
        int physical_gains =0;
        int technology_gross =0;//技术
        int technology_gains =0;
        int aggressive_gross =0;//侵略
        int aggressive_gains =0;
        int mean_power =0;//战力
        int gains =0;
        if (team_intUse == 1){
            attack_gross = (int) aAttackCloudData;
            defensive_gross = (int) defenseCloudData;
            physical_gross = (int) bodyCloudData;
            technology_gross = (int) technicCloudData;
            aggressive_gross = (int) invadeCloudData;
        }else if (team_intUse == 2){
            attack_gross = (int) ((db_teamCloudData.getAttack_gross()*0.5)+(aAttackCloudData*0.5));
            defensive_gross = (int) ((db_teamCloudData.getDefensive_gross()*0.5)+(defenseCloudData*0.5));
            physical_gross = (int) ((db_teamCloudData.getPhysical_gross()*0.5)+(bodyCloudData*0.5));
            technology_gross = (int) ((db_teamCloudData.getTechnology_gross()*0.5)+(technicCloudData*0.5));
            aggressive_gross = (int) ((db_teamCloudData.getAggressive_gross()*0.5)+(invadeCloudData*0.5));
        }else if (team_intUse == 3){
            attack_gross = (int) ((db_teamCloudData.getAttack_gross()*0.6)+(aAttackCloudData*0.4));
            defensive_gross = (int) ((db_teamCloudData.getDefensive_gross()*0.6)+(defenseCloudData*0.4));
            physical_gross = (int) ((db_teamCloudData.getPhysical_gross()*0.6)+(bodyCloudData*0.4));
            technology_gross = (int) ((db_teamCloudData.getTechnology_gross()*0.6)+(technicCloudData*0.4));
            aggressive_gross = (int) ((db_teamCloudData.getAggressive_gross()*0.6)+(invadeCloudData*0.4));
        }else if (team_intUse >= 4){
            attack_gross = (int) ((db_teamCloudData.getAttack_gross()*0.7)+(aAttackCloudData*0.3));
            defensive_gross = (int) ((db_teamCloudData.getDefensive_gross()*0.7)+(defenseCloudData*0.3));
            physical_gross = (int) ((db_teamCloudData.getPhysical_gross()*0.7)+(bodyCloudData*0.3));
            technology_gross = (int) ((db_teamCloudData.getTechnology_gross()*0.7)+(technicCloudData*0.3));
            aggressive_gross = (int) ((db_teamCloudData.getAggressive_gross()*0.7)+(invadeCloudData*0.3));
        }
        attack_gains = db_teamCloudData.getAttack_gross() - attack_gross;
        defensive_gains = db_teamCloudData.getDefensive_gross() - defensive_gross;
        physical_gains = db_teamCloudData.getPhysical_gross() - physical_gross;
        technology_gains = db_teamCloudData.getTechnology_gross() - technology_gross;
        aggressive_gains = db_teamCloudData.getAggressive_gross() - aggressive_gross;
        //计算战力值
        mean_power = (int) ((attack_gross*0.25) + (defensive_gross*0.25) + (technology_gross*0.2)+
                (aggressive_gross*0.1) + (physical_gross*0.2));
        gains = db_teamCloudData.getMean_power() - mean_power;

        TeamCloudData teamCloudData = new TeamCloudData();
        teamCloudData.setCloud_id(Utils.getUUID());
        teamCloudData.setTeam_id(team_id);
        teamCloudData.setAttack_gross(attack_gross);
        teamCloudData.setAttack_gains(attack_gains);
        teamCloudData.setDefensive_gross(defensive_gross);
        teamCloudData.setDefensive_gains(defensive_gains);
        teamCloudData.setPhysical_gross(physical_gross);
        teamCloudData.setPhysical_gains(physical_gains);
        teamCloudData.setTechnology_gross(technology_gross);
        teamCloudData.setTechnology_gains(technology_gains);
        teamCloudData.setAggressive_gross(aggressive_gross);
        teamCloudData.setAggressive_gains(aggressive_gains);
        teamCloudData.setMean_power(mean_power);
        teamCloudData.setGains(gains);
        teamCloudData.setCount_time(new Date());
        return teamCloudData;
    }
    /**
     * 计算身体得分
     * @param gameFrequency 己方比赛频率
     * @return
     */
    private int computeBodyScore(double gameFrequency){
        int bodyScore = 30;
        if (gameFrequency >= 1 && gameFrequency < 1.5){
            bodyScore = 35;
        }else if (gameFrequency >= 1.5 && gameFrequency < 2){
            bodyScore = 40;
        }else if (gameFrequency >= 2 && gameFrequency < 2.5){
            bodyScore = 45;
        }else if (gameFrequency >= 2.5 && gameFrequency < 3){
            bodyScore = 50;
        }else if (gameFrequency >= 3 && gameFrequency < 3.5){
            bodyScore = 55;
        }else if (gameFrequency >= 3.5 && gameFrequency < 4){
            bodyScore = 60;
        }else if (gameFrequency >= 4 && gameFrequency < 5){
            bodyScore = 65;
        }else if (gameFrequency >= 5 && gameFrequency < 6){
            bodyScore = 70;
        }else if (gameFrequency >= 6 && gameFrequency < 7){
            bodyScore = 75;
        }else if (gameFrequency >= 7 && gameFrequency < 8){
            bodyScore = 80;
        }else if (gameFrequency >= 8 && gameFrequency < 8.5){
            bodyScore = 85;
        }else if (gameFrequency >= 8.5 && gameFrequency < 9){
            bodyScore = 90;
        }else if (gameFrequency >= 9 && gameFrequency < 9.5){
            bodyScore = 95;
        }else if (gameFrequency >= 9.5){
            bodyScore = 100;
        }
        return bodyScore;
    }

    /**
     * 计算进攻得分
     * @param goal 己方进球数
     * @param totalGoal AB两对进球总数
     * @param shoot 己方射门次数
     * @param totalShoot AB两队射门总数
     * @param targetAate 己方射正率
     * @return
     */
    private Map<String,Object> computeAttackScore(int goal,int totalGoal,int shoot,int totalShoot,int targetAate){
        //进球权重
        double goalWeight = 0.3;
        if (goal >= 3){
            goalWeight = 1.0;
        }else if (goal == 2){
            goalWeight = 0.75;
        }else if (goal == 1){
            goalWeight = 0.6;
        }
        //进球比率
        int goalRatio = 0;
        if (goal != 0){
            goalRatio = (int)((new BigDecimal((float)goal/totalGoal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
        }
        //进球得分
        double goalScore = 30;
        if (goalRatio >= 90){
            goalScore = 100*goalWeight;
        }else if (goalRatio >= 80 && goalRatio < 90){
            goalScore = 95 * goalWeight;
        }else if (goalRatio >= 75 && goalRatio < 80){
            goalScore = 90 * goalWeight;
        }else if (goalRatio >= 70 && goalRatio < 75){
            goalScore = 85 * goalWeight;
        }else if (goalRatio >= 65 && goalRatio < 70){
            goalScore = 80 * goalWeight;
        }else if (goalRatio >= 60 && goalRatio < 65){
            goalScore = 75 * goalWeight;
        }else if (goalRatio >= 55 && goalRatio < 60){
            goalScore = 70 * goalWeight;
        }else if (goalRatio >= 50 && goalRatio < 55){
            goalScore = 65 * goalWeight;
        }else if (goalRatio >= 45 && goalRatio < 50){
            goalScore = 60 * goalWeight;
        }else if (goalRatio >= 40 && goalRatio < 45){
            goalScore = 55 * goalWeight;
        }else if (goalRatio >= 35 && goalRatio < 40){
            goalScore = 50 * goalWeight;
        }else if (goalRatio >= 30 && goalRatio < 35){
            goalScore = 45 * goalWeight;
        }else if (goalRatio >= 25 && goalRatio < 30){
            goalScore = 40 * goalWeight;
        }else if (goalRatio >= 20 && goalRatio < 25){
            goalScore = 35 * goalWeight;
        }
        //射门比率
        double shootRatio = 0;
        if (shoot != 0){
            shootRatio = (int)((new BigDecimal((float)shoot/totalShoot).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
        }
        //射门得分
        double shootScore = 30;
        if (shootRatio >= 90){
            shootScore = 100;
        }else if (shootRatio >= 80 && shootRatio < 90){
            shootScore = 95;
        }else if (shootRatio >= 75 && shootRatio < 80){
            shootScore = 90;
        }else if (shootRatio >= 70 && shootRatio < 75){
            shootScore = 85;
        }else if (shootRatio >= 65 && shootRatio < 70){
            shootScore = 80;
        }else if (shootRatio >= 60 && shootRatio < 65){
            shootScore = 75;
        }else if (shootRatio >= 55 && shootRatio < 60){
            shootScore = 70;
        }else if (shootRatio >= 50 && shootRatio < 55){
            shootScore = 65;
        }else if (shootRatio >= 45 && shootRatio < 50){
            shootScore = 60;
        }else if (shootRatio >= 40 && shootRatio < 45){
            shootScore = 55;
        }else if (shootRatio >= 35 && shootRatio < 40){
            shootScore = 50;
        }else if (shootRatio >= 30 && shootRatio < 35){
            shootScore = 45;
        }else if (shootRatio >= 25 && shootRatio < 30){
            shootScore = 40;
        }else if (shootRatio >= 20 && shootRatio < 25){
            shootScore = 35;
        }
        //射正率得分
        double targetAateScore = 30;
        if (targetAate >= 90){
            targetAateScore = 100;
        }else if (targetAate >= 80 && targetAate < 90){
            targetAateScore = 95;
        }else if (targetAate >= 70 && targetAate < 80){
            targetAateScore = 90;
        }else if (targetAate >= 60 && targetAate < 70){
            targetAateScore = 85;
        }else if (targetAate >= 50 && targetAate < 60){
            targetAateScore = 80;
        }else if (targetAate >= 45 && targetAate < 50){
            targetAateScore = 75;
        }else if (targetAate >= 40 && targetAate < 45){
            targetAateScore = 70;
        }else if (targetAate >= 35 && targetAate < 40){
            targetAateScore = 65;
        }else if (targetAate >= 30 && targetAate < 35){
            targetAateScore = 60;
        }else if (targetAate >= 25 && targetAate < 30){
            targetAateScore = 55;
        }else if (targetAate >= 20 && targetAate < 25){
            targetAateScore = 50;
        }else if (targetAate >= 15 && targetAate < 20){
            targetAateScore = 45;
        }else if (targetAate >= 10 && targetAate < 15){
            targetAateScore = 40;
        }else if (targetAate >= 5 && targetAate < 10){
            targetAateScore = 35;
        }
        //封装进攻得分
        Map<String,Object> aAttackScore = new HashMap<>();
        aAttackScore.put("goalScore",goalScore);
        aAttackScore.put("shootScore",shootScore);
        aAttackScore.put("targetAateScore",targetAateScore);
        return aAttackScore;
    }

    /**
     * 计算防守得分
     * @param goal 对方进球数
     * @param totalGoal 双方进球总数
     * @param steal 已方抢断数
     * @param totalSteal 双方抢断总数
     * @param diving 己方补救次数
     * @param shotOnTarget 对方射正次数
     * @return
     */
    private Map<String,Object> computeDefenseScore(int goal,int totalGoal,int steal,int totalSteal,int diving,int shotOnTarget){
        //敌方进球权重
        double goalWeight = 1.0;
        if (goal >= 2){
            goalWeight = 0.8;
        }else if (goal == 1){
            goalWeight = 0.9;
        }
        //敌方进球比率
        int goalRatio = 0;
        if (goal != 0){
            goalRatio = (int)((new BigDecimal((float)goal/totalGoal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
        }
        //敌方进球得分
        double goalScore = 30 * goalWeight;
        if (goalRatio <= 15){
            goalScore = 100*goalWeight;
        }else if (goalRatio <= 20 && goalRatio > 15){
            goalScore = 95 * goalWeight;
        }else if (goalRatio <= 25 && goalRatio > 20){
            goalScore = 90 * goalWeight;
        }else if (goalRatio <= 30 && goalRatio > 25){
            goalScore = 85 * goalWeight;
        }else if (goalRatio <= 35 && goalRatio > 30){
            goalScore = 80 * goalWeight;
        }else if (goalRatio <= 40 && goalRatio > 35){
            goalScore = 75 * goalWeight;
        }else if (goalRatio <= 45 && goalRatio > 40){
            goalScore = 70 * goalWeight;
        }else if (goalRatio <= 50 && goalRatio > 45){
            goalScore = 65 * goalWeight;
        }else if (goalRatio <= 55 && goalRatio > 50){
            goalScore = 60 * goalWeight;
        }else if (goalRatio <= 60 && goalRatio > 55){
            goalScore = 55 * goalWeight;
        }else if (goalRatio <= 65 && goalRatio > 60){
            goalScore = 50 * goalWeight;
        }else if (goalRatio <= 70 && goalRatio > 65){
            goalScore = 45 * goalWeight;
        }else if (goalRatio <= 75 && goalRatio > 70){
            goalScore = 40 * goalWeight;
        }else if (goalRatio <= 80 && goalRatio > 75){
            goalScore = 35 * goalWeight;
        }
        //抢断比率
        double stealRatio = 0;
        if (steal != 0){
            stealRatio = (int)((new BigDecimal((float)steal/totalSteal).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
        }
        //抢断得分
        double stealScore = 0;
        if (stealRatio >= 90){
            stealScore = 100;
        }else if (stealRatio >= 80 && stealRatio < 90){
            stealScore = 95;
        }else if (stealRatio >= 75 && stealRatio < 80){
            stealScore = 90;
        }else if (stealRatio >= 70 && stealRatio < 75){
            stealScore = 85;
        }else if (stealRatio >= 65 && stealRatio < 70){
            stealScore = 80;
        }else if (stealRatio >= 60 && stealRatio < 65){
            stealScore = 75;
        }else if (stealRatio >= 55 && stealRatio < 60){
            stealScore = 70;
        }else if (stealRatio >= 50 && stealRatio < 55){
            stealScore = 65;
        }else if (stealRatio >= 45 && stealRatio < 50){
            stealScore = 60;
        }else if (stealRatio >= 40 && stealRatio < 45){
            stealScore = 55;
        }else if (stealRatio >= 35 && stealRatio < 40){
            stealScore = 50;
        }else if (stealRatio >= 30 && stealRatio < 35){
            stealScore = 45;
        }else if (stealRatio >= 25 && stealRatio < 30){
            stealScore = 40;
        }else if (stealRatio >= 20 && stealRatio < 25){
            stealScore = 35;
        }else {
            stealScore = 30;
        }
        //扑救比率
        double divingRatio = 0;
        if (diving != 0){
            divingRatio = (int)((new BigDecimal((float)diving/shotOnTarget).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
        }
        //扑救得分
        double divingScore = 0;
        if (divingRatio >= 90){
            divingScore = 100;
        }else if (divingRatio >= 80 && divingRatio < 90){
            divingScore = 95;
        }else if (divingRatio >= 75 && divingRatio < 80){
            divingScore = 90;
        }else if (divingRatio >= 70 && divingRatio < 75){
            divingScore = 85;
        }else if (divingRatio >= 65 && divingRatio < 70){
            divingScore = 80;
        }else if (divingRatio >= 60 && divingRatio < 65){
            divingScore = 75;
        }else if (divingRatio >= 55 && divingRatio < 60){
            divingScore = 70;
        }else if (divingRatio < 55 && divingRatio >= 50){//调换前后顺序，区分去掉黄线
            divingScore = 65;
        }else if (divingRatio >= 45 && divingRatio < 50){
            divingScore = 60;
        }else if (divingRatio >= 40 && divingRatio < 45){
            divingScore = 55;
        }else if (divingRatio >= 35 && divingRatio < 40){
            divingScore = 50;
        }else if (divingRatio >= 30 && divingRatio < 35){
            divingScore = 45;
        }else if (divingRatio >= 25 && divingRatio < 30){
            divingScore = 40;
        }else if (divingRatio < 25 && divingRatio >= 20){//调换前后顺序，区分去掉黄线
            divingScore = 35;
        }else {
            divingScore = 30;
        }
        //封装防守得分
        Map<String,Object> defenseScore = new HashMap<>();
        defenseScore.put("goalScore",goalScore);
        defenseScore.put("stealScore",stealScore);
        defenseScore.put("divingScore",divingScore);
        return defenseScore;
    }

    /**
     * 计算技术得分
     * @param threat 己方威胁传球数
     * @param totalThreat 双方总威胁传球数
     * @param surpass 己方过人数
     * @param totalSurpass 双方过人总数
     * @return
     */
    private Map<String,Object> computeTechnicScore(int threat,int totalThreat,int surpass,int totalSurpass){
        //威胁传球比率
        double threatRatio = 0;
        if (threat != 0){
            threatRatio = (int)((new BigDecimal((float)threat/totalThreat).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
        }
        //威胁传球得分
        double threatScore = 30;
        if (threatRatio >= 85){
            threatScore = 100;
        }else if (threatRatio >= 80 && threatRatio < 85){
            threatScore = 95;
        }else if (threatRatio >= 75 && threatRatio < 80){
            threatScore = 90;
        }else if (threatRatio >= 70 && threatRatio < 75){
            threatScore = 85;
        }else if (threatRatio >= 65 && threatRatio < 70){
            threatScore = 80;
        }else if (threatRatio >= 60 && threatRatio < 65){
            threatScore = 75;
        }else if (threatRatio >= 55 && threatRatio < 60){
            threatScore = 70;
        }else if (threatRatio >= 50 && threatRatio < 55){
            threatScore = 65;
        }else if (threatRatio >= 45 && threatRatio < 50){
            threatScore = 60;
        }else if (threatRatio >= 40 && threatRatio < 45){
            threatScore = 55;
        }else if (threatRatio >= 35 && threatRatio < 40){
            threatScore = 50;
        }else if (threatRatio >= 30 && threatRatio < 35){
            threatScore = 45;
        }else if (threatRatio < 30 && threatRatio >= 25){//调换前后顺序，区分去掉黄线
            threatScore = 40;
        }else if (threatRatio >= 20 && threatRatio < 25){
            threatScore = 35;
        }
        //过人比率
        double surpassRatio = 0;
        if (surpass != 0){
            surpassRatio = (int)((new BigDecimal((float)surpass/totalSurpass).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
        }
        //过人得分
        double surpassScore = 30;
        if (surpassRatio >= 85){
            surpassScore = 100;
        }else if (surpassRatio >= 80 && surpassRatio < 85){
            surpassScore = 95;
        }else if (surpassRatio >= 75 && surpassRatio < 80){
            surpassScore = 90;
        }else if (surpassRatio >= 70 && surpassRatio < 75){
            surpassScore = 85;
        }else if (surpassRatio >= 65 && surpassRatio < 70){
            surpassScore = 80;
        }else if (surpassRatio >= 60 && surpassRatio < 65){
            surpassScore = 75;
        }else if (surpassRatio >= 55 && surpassRatio < 60){
            surpassScore = 70;
        }else if (surpassRatio >= 50 && surpassRatio < 55){
            surpassScore = 65;
        }else if (surpassRatio >= 45 && surpassRatio < 50){
            surpassScore = 60;
        }else if (surpassRatio >= 40 && surpassRatio < 45){
            surpassScore = 55;
        }else if (surpassRatio >= 35 && surpassRatio < 40){
            surpassScore = 50;
        }else if (surpassRatio < 35 && surpassRatio >= 30){//调换前后顺序，区分去掉黄线
            surpassScore = 45;
        }else if (surpassRatio < 30 && surpassRatio >= 25){//调换前后顺序，区分去掉黄线
            surpassScore = 40;
        }else if (surpassRatio >= 20 && surpassRatio < 25){
            surpassScore = 35;
        }
        //封装技术得分
        Map<String,Object> technicScore = new HashMap<>();
        technicScore.put("threatScore",threatScore);
        technicScore.put("surpassScore",surpassScore);
        return technicScore;
    }

    /**
     * 计算侵略得分
     * @param foul  己方犯规数
     * @param totalFoul 双方犯规总数
     * @param yellowCard 己方黄牌数
     * @param totalYellowCard 双方黄牌数
     * @return
     */
    private Map<String,Object> computeInvadeScore(int foul,int totalFoul,int yellowCard,int totalYellowCard){
        //犯规比率
        double foulRatio = 0;
        if (foul != 0){
            foulRatio = (int)((new BigDecimal((float)foul/totalFoul).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
        }
        //犯规得分
        double foulScore = 30;
        if (foulRatio >= 71){
            foulScore = 100;
        }else if (foulRatio >= 68 && foulRatio < 71){
            foulScore = 95;
        }else if (foulRatio >= 65 && foulRatio < 68){
            foulScore = 90;
        }else if (foulRatio >= 62 && foulRatio < 65){
            foulScore = 85;
        }else if (foulRatio >= 59 && foulRatio < 62){
            foulScore = 80;
        }else if (foulRatio >= 56 && foulRatio < 59){
            foulScore = 75;
        }else if (foulRatio >= 53 && foulRatio < 56){
            foulScore = 70;
        }else if (foulRatio >= 50 && foulRatio < 53){
            foulScore = 65;
        }else if (foulRatio < 50 && foulRatio >= 45){//调换前后顺序，区分去掉黄线
            foulScore = 60;
        }else if (foulRatio >= 40 && foulRatio < 45){
            foulScore = 55;
        }else if (foulRatio < 40 && foulRatio >= 35){//调换前后顺序，区分去掉黄线
            foulScore = 50;
        }else if (foulRatio < 35 && foulRatio >= 30){//调换前后顺序，区分去掉黄线
            foulScore = 45;
        }else if (foulRatio >= 25 && foulRatio < 30 ){
            foulScore = 40;
        }else if (foulRatio < 25 && foulRatio >= 20){//调换前后顺序，区分去掉黄线
            foulScore = 35;
        }
        //黄牌比率
        double yellowCardRatio = 0;
        if (yellowCard != 0){
            yellowCardRatio = (int)((new BigDecimal((float)yellowCard/totalYellowCard).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
        }
        //黄牌得分
        double yellowCardScore = 30;
        if (yellowCardRatio >= 85){
            yellowCardScore = 100;
        }else if (yellowCardRatio >= 80 && yellowCardRatio < 85){
            yellowCardScore = 95;
        }else if (yellowCardRatio >= 75 && yellowCardRatio < 80){
            yellowCardScore = 90;
        }else if (yellowCardRatio >= 70 && yellowCardRatio < 75){
            yellowCardScore = 85;
        }else if (yellowCardRatio >= 65 && yellowCardRatio < 70){//调换前后顺序，区分去掉黄线
            yellowCardScore = 80;
        }else if (yellowCardRatio < 65 && yellowCardRatio >= 60){
            yellowCardScore = 75;
        }else if (yellowCardRatio >= 55 && yellowCardRatio < 60){
            yellowCardScore = 70;
        }else if (yellowCardRatio >= 50 && yellowCardRatio < 55){
            yellowCardScore = 65;
        }else if (yellowCardRatio >= 45 && yellowCardRatio < 50){
            yellowCardScore = 60;
        }else if (yellowCardRatio < 45 && yellowCardRatio >= 40){//调换前后顺序，区分去掉黄线
            yellowCardScore = 55;
        }else if (yellowCardRatio >= 35 && yellowCardRatio < 40){
            yellowCardScore = 50;
        }else if (yellowCardRatio >= 30 && yellowCardRatio < 35){
            yellowCardScore = 45;
        }else if (yellowCardRatio >= 25 && yellowCardRatio < 30){
            yellowCardScore = 40;
        }else if (yellowCardRatio < 25 && yellowCardRatio >= 20){//调换前后顺序，区分去掉黄线
            yellowCardScore = 35;
        }
        //封装侵略得分
        Map<String,Object> invadeScore = new HashMap<>();
        invadeScore.put("foulScore",foulScore);
        invadeScore.put("yellowCardScore",yellowCardScore);
        return invadeScore;
    }

    /**
     * 球队成员增减时计算云五值
     * @param team_id
     * @return
     */
    @Override
    public boolean computeCloudDataByMember(String team_id) {
        return false;
    }
}
