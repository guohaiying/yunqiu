package com.yunqiu.tournament.timedtask;

import com.yunqiu.tournament.dao.TournamentMapper;
import com.yunqiu.tournament.model.Tournament;
import com.yunqiu.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 初始化项目时添加比赛状态修改定时任务
 */
@Component
public class InitTask implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private TournamentMapper tournamentMapper;
    @Autowired
    private GameTimedTask gameTimedTask;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<Tournament> tournamentList = tournamentMapper.selectTournamentByAll();
        for (Tournament tournament:tournamentList){
            Date endTime = DateUtil.assignTimeLaterDayTime(tournament.getGame_time(),tournament.getContinue_time());//比赛结束时间
            long currnTime = new Date().getTime();//当前时间
            if (tournament.getApply_end_time() == null){
                if (currnTime >= tournament.getGame_time().getTime() && currnTime < endTime.getTime()){
                    //修改为进行中
                    Tournament t = new Tournament();
                    String game_id = tournament.getGame_id();
                    t.setGame_id(game_id);
                    t.setGame_status(5);
                    tournamentMapper.updateGame(t);
                    //修改比赛为已结束状态定时任务
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),endTime,6);
                    //当前时间大于比赛结束时间，修改为已结束
                }else if(currnTime >= endTime.getTime()){
                    //修改为进行中
                    Tournament t = new Tournament();
                    t.setGame_id(tournament.getGame_id());
                    t.setGame_status(6);
                    tournamentMapper.updateGame(t);
                }else{
                    //修改比赛为进行中的定时任务
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),tournament.getGame_time(),5);
                    //修改比赛为已结束状态定时任务
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),endTime,6);
                }
            }else {
                //当前时间大于比赛报名时间 小于比赛开始时间，修改比赛为报名结束状态，并添加定时任务
                if (currnTime >= tournament.getApply_end_time().getTime() && currnTime < tournament.getGame_time().getTime()){
                    //修改为报名结束
                    Tournament t = new Tournament();
                    t.setGame_id(tournament.getGame_id());
                    t.setGame_status(4);
                    tournamentMapper.updateGame(t);
                    //修改比赛为进行中的定时任务
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),tournament.getGame_time(),5);
                    //修改比赛为已结束状态定时任务
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),endTime,6);
                    //当前时间大于比赛开始时间小于比赛结束时间，修改比赛为进行中状态，并添加定时任务
                }else if (currnTime >= tournament.getGame_time().getTime() && currnTime < endTime.getTime()){
                    //修改为进行中
                    Tournament t = new Tournament();
                    t.setGame_id(tournament.getGame_id());
                    t.setGame_status(5);
                    tournamentMapper.updateGame(t);
                    //修改比赛为已结束状态定时任务
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),endTime,6);
                    //当前时间大于比赛结束时间，修改为已结束
                }else if(currnTime >= endTime.getTime()){
                    //修改为进行中
                    Tournament t = new Tournament();
                    t.setGame_id(tournament.getGame_id());
                    t.setGame_status(6);
                    tournamentMapper.updateGame(t);
                }else{
                    //修改为报名已结束状态的定时任务
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),tournament.getApply_end_time(),4);
                    //修改比赛为进行中的定时任务
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),tournament.getGame_time(),5);
                    //修改比赛为已结束状态定时任务
                    gameTimedTask.updateGameStatus(tournament.getGame_id(),endTime,6);
                }
            }

        }

    }
}
