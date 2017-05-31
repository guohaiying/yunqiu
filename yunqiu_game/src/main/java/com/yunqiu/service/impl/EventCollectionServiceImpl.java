package com.yunqiu.service.impl;

import com.yunqiu.dao.EventCollectionMapper;
import com.yunqiu.model.Event;
import com.yunqiu.model.EventCollection;
import com.yunqiu.service.EventCollectionService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.DateUtil;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 11366 on 2017/2/8.
 */

@Service
public class EventCollectionServiceImpl implements EventCollectionService{
    @Autowired
    private EventCollectionMapper eventCollectionMapper;

    /**
     * 收藏比赛
     * @param user_id
     * @param event_id
     * @return
     */
    @Override
    public Map<String, Object> collect(String user_id, String event_id) {
        try {
            if (Utils.isNull(event_id)){
                return ControllerReturnBase.errorResule(1501,"缺少收藏的比赛id");
            }
            EventCollection db_eventCollection = eventCollectionMapper.selectEventCollection(user_id,event_id);
            if (db_eventCollection != null){
                return ControllerReturnBase.errorResule(1502,"已收藏该比赛，请勿重复收藏");
            }
            EventCollection eventCollection = new EventCollection();
            eventCollection.setCollect_id(Utils.getUUID());
            eventCollection.setUser_id(user_id);
            eventCollection.setEvent_id(event_id);
            eventCollectionMapper.insert(eventCollection);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(EventCollectionServiceImpl.class,"收藏比赛（service）时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 取消收藏
     * @param user_id
     * @param event_id
     * @return
     */
    @Override
    public Map<String, Object> cancelCollect(String user_id, String event_id) {
        try {
            if (Utils.isNull(event_id)){
                return ControllerReturnBase.errorResule(1501,"缺少收藏的比赛id");
            }
            EventCollection db_eventCollection = eventCollectionMapper.selectEventCollection(user_id,event_id);
            if (db_eventCollection == null){
                return ControllerReturnBase.errorResule(1502,"未收藏该比赛");
            }
            eventCollectionMapper.deleteEventCollection(db_eventCollection.getCollect_id());
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(EventCollectionServiceImpl.class,"取消收藏比赛（service）时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取收藏的筛选条件
     * @return
     */
    @Override
    public Map<String, Object> collectFiltrateConditions(String user_id) {
        try {
            //返回的结果集定义
            Map<String,Object> result_param = new HashMap<>();
            /*----------------状态筛选条件--------------------*/
            List<Map<String,Object>> status_param = new ArrayList<>();//状态结果集
            //所有状态名称，根据顺序列出
            String[] status_name = {"待审核","待迎战 ","报名中","报名结束","正在进行","已结束","比赛取消","拒绝迎战","审核拒绝"};
            //循环状态名
            for (int i=0;i<status_name.length;i++){
                Map<String,Object> status = new HashMap<>();
                status.put("status_name",status_name[i]);
                status.put("status_value",i+1);
                status_param.add(status);
            }
            result_param.put("status",status_param);
            /*----------------时间筛选条件--------------------*/
            List<Map<String,Object>> time_param = new ArrayList<>();//时间结果集
            //查询用户所有收藏的比赛
            List<Event> collectionEventList = eventCollectionMapper.selectEventCollectionAll(user_id);
            //根据比赛时间，把比赛信息按月分组
            //单条比赛数据
            Event event = null;
            //分组后的结果集
            Map<String,String> group_Event = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<collectionEventList.size();i++){
                //获取到单条比赛信息
                event = collectionEventList.get(i);
                if(!group_Event.containsKey(event.getGame_time())){
                    group_Event.put(DateUtil.DTDateToString(event.getGame_time(),"yyyy年MM月"),DateUtil.DTDateToString(event.getGame_time(),"yyyy-MM"));
                }
            }
            //解析出时间组
            for (String key:group_Event.keySet()){
                Map<String,Object> time = new HashMap<>();
                time.put("time_name",key);
                time.put("time_value",group_Event.get(key));
                time_param.add(time);
            }
            result_param.put("time",time_param);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(EventCollectionServiceImpl.class,"获取收藏筛选条件(service)时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 获取比赛收藏列表
     * @param user_id
     * @param pageNum
     * @param pageSize
     * @param status
     * @param time
     * @return
     */
    @Override
    public Map<String, Object> collectList(String user_id, int pageNum, int pageSize, int status, String time) {
        try {
            //获取总条数
            int focusTotal = eventCollectionMapper.selectEventCollectionTotal(user_id);
            if (focusTotal == 0){
                return ControllerReturnBase.errorResule(1502,"暂无收藏的比赛");
            }
            //计算总页数
            int pageTotal = focusTotal%pageSize == 0 ?focusTotal/pageSize:(focusTotal/pageSize)+1;
            //判断当前页码是否大于总页码
            if (pageNum > pageTotal){
                return ControllerReturnBase.errorResule(1502,"当前页码不能大于总页码");
            }
            //计算起始行索引
            int start_now = (pageNum-1)*pageSize;
            //获取到收藏的比赛
            List<Map<String,Object>> eventList = eventCollectionMapper.selectPagingEventCollection(user_id,start_now,pageSize,status,time);
            /* -------------根据比赛时间，把比赛信息按月分组 */
            //单条比赛数据
            Map<String,Object> event = null;
            //分组后的结果集
            Map<String,List<Map<String,Object>>> groupEvent = new HashMap<>();
            //解析比赛信息列表
            for (int i=0;i<eventList.size();i++){
                //获取到单条比赛信息
                event = eventList.get(i);
                if(groupEvent.containsKey(event.get("game_time"))){
                    groupEvent.get(event.get("game_time")).add(event);
                }else{
                    List<Map<String,Object>> list = new ArrayList<>();
                    list.add(event);
                    groupEvent.put(DateUtil.DTDateToString((Date) event.get("game_time"),"yyyy年MM月"),list);
                }
            }
            //循环解析分组后的比赛
            List<Map<String,Object>> params = new ArrayList<>();
            for (String key:groupEvent.keySet()){
                Map<String,Object> param = new HashMap<>();
                param.put("group",key);
                param.put("children",groupEvent.get(key));
                params.add(param);
            }
            //返回数据
            Map<String,Object> result_param = new HashMap<>();
            result_param.put("total",pageTotal);
            result_param.put("pageSize",pageSize);
            result_param.put("pageNum",pageNum);
            result_param.put("collect",params);
            return ControllerReturnBase.successResule(result_param);
        }catch (Exception e){
            LoggerUtil.outError(EventCollectionServiceImpl.class,"获取收藏比赛列表（service）时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }
}
