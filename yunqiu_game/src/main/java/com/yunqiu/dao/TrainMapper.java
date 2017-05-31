package com.yunqiu.dao;

import com.yunqiu.model.Train;
import com.yunqiu.model.TrainMember;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 训练
 */
@Mapper
public interface TrainMapper {
    /**
     * 创建训练
     * @param train
     */
    @Insert({
            "insert into pw_train(train_id,team_id,user_id,train_name,train_time,train_duration,train_site,status,cncel_reason) values",
            "(#{train_id},#{team_id},#{user_id},#{train_name},#{train_time},#{train_duration},#{train_site},#{status},#{cncel_reason})"
    })
    void insertTrain(Train train);

    /**
     * 根据训练id查询训练
     * @param train_id
     * @return
     */
    @Select("select * from pw_train where train_id=#{train_id}")
    Train selectTrainById(@Param("train_id") String train_id);

    /**
     * 根据训练id查询训练，并同时查询球队信息
     * @param train_id
     * @return
     */
    @Select("select team.team_id,team_name,train.* from pw_train AS train JOIN pw_team AS team ON team.team_id=train.team_id where train_id=#{train_id}")
    Map<String,Object> selectTrainAndTeamById(@Param("train_id") String train_id);

    /**
     * 根据年跟月获取训练
     * @param year
     * @param month
     * @return
     */
    @Select("SELECT * FROM pw_train WHERE YEAR(train_time)=#{year} AND month(train_time)=#{month} AND team_id=#{team_id}")
    List<Train> selectTrainByTime(@Param("year") int year,@Param("month") int month,@Param("team_id") String team_id);

    /**
     * 获取用户所有球队id
     * @param user_id
     * @return
     */
    @Select("SELECT team_id FROM pw_team_member where user_id = #{user_id}")
    List<String> selectTeamId(@Param("user_id") String user_id);
    /**
     * 修改训练
     * @param train
     */
    @Update({
            "update pw_train set train_time=#{train_time},train_duration=#{train_duration},train_site=#{train_site},",
            "cncel_reason=#{cncel_reason},status=#{status} where train_id=#{train_id}"
    })
    void updateTrain(Train train);

    /**
     * 添加报名信息
     * @param trainMember
     */
    @Insert({
            "insert into pw_train_member(member_id,user_id,train_id,participate) values",
            "(#{member_id},#{user_id},#{train_id},#{participate})"
    })
    void insertTrainMember(TrainMember trainMember);

    /**
     * 根据用户id跟训练id查询报名信息
     * @param user_id
     * @return
     */
    @Select("select * from pw_train_member where user_id=#{user_id} AND train_id=#{train_id}")
    TrainMember selectTrainMemberByUserIdAndTrainId(@Param("user_id") String user_id,@Param("train_id") String train_id);

    /**
     * 修改报名状态
     * @param train_id
     * @param status
     */
    @Update("update pw_train_member set participate=#{status} where train_id=#{train_id}")
    void updateTrainMember(@Param("train_id") String train_id,@Param("status") int status);

    /**
     * 根据状态统计参与人数
     * @param train_id
     * @param user_id
     * @param status
     * @return
     */
    @Select("SELECT COUNT(member_id) AS number FROM pw_train_member WHERE user_id=#{user_id} AND train_id=#{train_id} AND participate=#{status}")
    int statisticalByTrainIdAndUserIdAndStatus(@Param("train_id") String train_id,@Param("user_id") String user_id,@Param("status") int status);

    /**
     * 获取参与训练的成员信息
     * @param train_id
     * @param status
     * @return
     */
    @Select({
            "SELECT u.user_id,u.nickname,u.portrait,u.position FROM pw_train_member AS tm JOIN pw_users AS u ON ",
            "u.user_id=tm.user_id WHERE tm.train_id=#{train_id} AND tm.participate=#{status}"
    })
    List<Map<String,Object>> userInfo(@Param("train_id") String train_id,@Param("status") int status);


}
