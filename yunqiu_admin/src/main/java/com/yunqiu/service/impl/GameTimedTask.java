package com.yunqiu.service.impl;

import com.yunqiu.dao.TournamentMapper;
import com.yunqiu.model.Tournament;
import com.yunqiu.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 比赛定时任务
 */

@Service
public class GameTimedTask {

    @Autowired
    TournamentMapper tournamentMapper;
    //定时器存储
    private Map<String,Timer> timerMap = new HashMap<>();
    /**
     * 修改比赛状态
     * @param game_id
     * @param time
     */
    public void updateGameStatus(String game_id, Date time,int game_status){
        try {
            Timer timer = new Timer(true);
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("定时任务执行---------------------------修改的比赛为==="+game_id+"---修改为的状态==="+game_status);
                    Tournament tournament = new Tournament();
                    tournament.setGameId(game_id);
                    tournament.setGameStatus(game_status);
                    tournamentMapper.updateGame(tournament);
                }
            };
            long taskTime = time.getTime() - new Date().getTime();
            System.out.println("=========================="+taskTime+"==============执行定时任务=====比赛id===="+game_id+"===修改为的状态==="+game_status);
            timer.schedule(task,taskTime);
            timerMap.put(game_id+"_"+game_status,timer);
        }catch (Exception e){
            LoggerUtil.outError(GameTimedTask.class,"修改比赛状态的定时任务发生错误",e);
        }
    }

    /**
     * 停止修改比赛状态的定时任务
     * @param game_id
     */
    public void deleteGameTask(String game_id){
        try {
            int[] status = {4,5,6};
            for (int index = 0;index<status.length;index++){
                String key = game_id+"_"+status[0];
                Timer timer = timerMap.get(key);
                if (timer != null){
                    timer.cancel();
                    timerMap.remove(key);
                }
            }
        }catch (Exception e){
            LoggerUtil.outError(GameTimedTask.class,"停止修改比赛状态的定时任务时发生错误",e);
        }
    }
}
