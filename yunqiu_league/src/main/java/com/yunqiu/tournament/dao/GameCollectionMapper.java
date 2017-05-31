package com.yunqiu.tournament.dao;

import com.yunqiu.tournament.model.GameCollection;
import org.apache.ibatis.annotations.*;

/**
 * 比赛收藏
 */
@Mapper
public interface GameCollectionMapper {
    /**
     * 收藏比赛
     * @param gameCollection
     */
    @Insert({
            "insert into pw_game_collection(collect_id,user_id,game_id) values",
            "(#{collect_id},#{user_id},#{game_id})"
    })
    void insert(GameCollection gameCollection);

    /**
     * 根据用户id跟比赛id查询收藏
     * @param user_id
     * @param game_id
     * @return
     */
    @Select("select * from pw_game_collection where user_id=#{user_id} and game_id=#{game_id}")
    GameCollection selectEventCollection(@Param("user_id") String user_id, @Param("game_id") String game_id);

    /**
     * 删除收藏
     * @param collect_id
     */
    @Delete("delete from pw_game_collection where collect_id=#{collect_id}")
    void deleteEventCollection(@Param("collect_id") String collect_id);
}
