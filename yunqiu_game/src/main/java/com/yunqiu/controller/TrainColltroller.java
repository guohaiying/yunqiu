package com.yunqiu.controller;

import com.yunqiu.model.Train;
import com.yunqiu.service.TrainService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 训练
 */

@RestController
@RequestMapping("/train")
@EnableAutoConfiguration
public class TrainColltroller {
    @Autowired
    private TrainService trainService;

    /**
     * 创建训练
     * @param train
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/createTrain",method = RequestMethod.POST)
    public Map<String,Object> createTrain(Train train, HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        train.setUser_id(user_id);
        return trainService.createTrain(train);
    }

    /**
     * 修改训练
     * @param train
     * @return
     */
    @RequestMapping(value = "/inte/updateTrain",method = RequestMethod.POST)
    public Map<String,Object> updateTrain(Train train){
        return trainService.updateTrain(train);
    }

    /**
     * 取消训练
     * @param train_id
     * @param cncel_reason
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/cancelTrain",method = RequestMethod.POST)
    public Map<String,Object> cancelTrain(@RequestParam("train_id") String train_id,@RequestParam("cncel_reason") String cncel_reason,
                                          HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return trainService.cancelTrain(train_id,cncel_reason,user_id);
    }

    /**
     * 报名参与训练
     * @param train_id
     * @param status
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/participateTrain",method = RequestMethod.POST)
    public Map<String,Object> participateTrain(@RequestParam("train_id") String train_id,@RequestParam("status") int status,
                                               HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return trainService.participateTrain(train_id,status,user_id);
    }

    /**
     * 查询训练详情
     * @param train_id
     * @param request
     * @return
     */
    @RequestMapping(value = "/inte/trainInfo",method = RequestMethod.GET)
    public Map<String,Object> trainInfo(@Param("train_id") String train_id,HttpServletRequest request){
        String user_id = request.getHeader("user_id");
        return trainService.trainInfo(train_id,user_id);
    }
}
