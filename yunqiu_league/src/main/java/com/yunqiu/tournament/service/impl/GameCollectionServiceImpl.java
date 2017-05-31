package com.yunqiu.tournament.service.impl;

import com.yunqiu.tournament.dao.GameCollectionMapper;
import com.yunqiu.tournament.model.GameCollection;
import com.yunqiu.tournament.service.GameCollectionService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 收藏比赛数据处理
 */

@Service
public class GameCollectionServiceImpl implements GameCollectionService {
    @Autowired
    private GameCollectionMapper gameCollectionMapper;

    /**
     * 收藏比赛
     * @param user_id
     * @param game_id
     * @return
     */
    @Override
    public Map<String, Object> collect(String user_id, String game_id) {
        try {
            if (Utils.isNull(game_id)){
                return ControllerReturnBase.errorResule(1501,"缺少收藏的比赛id");
            }
            GameCollection db_eventCollection = gameCollectionMapper.selectEventCollection(user_id,game_id);
            if (db_eventCollection != null){
                return ControllerReturnBase.errorResule(1502,"已收藏该比赛，请勿重复收藏");
            }
            GameCollection eventCollection = new GameCollection();
            eventCollection.setCollect_id(Utils.getUUID());
            eventCollection.setUser_id(user_id);
            eventCollection.setGame_id(game_id);
            gameCollectionMapper.insert(eventCollection);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(GameCollectionServiceImpl.class,"收藏比赛（service）时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消收藏
     * @param user_id
     * @param game_id
     * @return
     */
    @Override
    public Map<String, Object> cancelCollect(String user_id, String game_id) {
        try {
            if (Utils.isNull(game_id)){
                return ControllerReturnBase.errorResule(1501,"缺少收藏的比赛id");
            }
            GameCollection db_eventCollection = gameCollectionMapper.selectEventCollection(user_id,game_id);
            if (db_eventCollection == null){
                return ControllerReturnBase.errorResule(1502,"未收藏该比赛");
            }
            gameCollectionMapper.deleteEventCollection(db_eventCollection.getCollect_id());
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(GameCollectionServiceImpl.class,"取消收藏比赛（service）时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
