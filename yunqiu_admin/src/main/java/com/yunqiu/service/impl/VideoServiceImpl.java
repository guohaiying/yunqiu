package com.yunqiu.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yunqiu.dao.UsersMapper;
import com.yunqiu.dao.VideoMapper;
import com.yunqiu.model.AdminUser;
import com.yunqiu.model.PageCrt;
import com.yunqiu.model.Video;
import com.yunqiu.model.WYYEntity;
import com.yunqiu.service.VideoService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import com.yunqiu.util.wangyiyun.CheckSumBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private UsersMapper usersMapper;


    // 查询总条数
    public Integer selectCount(PageCrt page) {
        return videoMapper.selectTotalPage(page);
    }

    //分页查询
    public List<Map> selectPaging(PageCrt page) {
        return videoMapper.selectePaging(page);
    }

    /**
     * 视频添加
     * @return
     */
    @Override
    public Map<String, Object> addVideo(Video video) {
        try {
            //验证参数是否为空
            if(video.getVideoType()==1){//视频
                String vid = video.getVid();
                Gson gson = new Gson();
                String post = CheckSumBuilder.getScreenshots(vid);

                WYYEntity rtn = gson.fromJson(post, new TypeToken<WYYEntity>(){}.getType());
                Map<String, Object> ret = rtn.getRet();
                String code = rtn.getCode();
                if(code.equals("200")){
                    String snapshotUrl = ret.get("snapshotUrl")+"";
                    video.setScreenshots(snapshotUrl);
                }else{
                    return ControllerReturnBase.errorResule(Integer.parseInt(code), "请输入有效视频ID");
                }
            }

            //添加
            String videoId=Utils.getID(22);
            video.setVideoId(videoId);
            video.setCreateTime(new Date());
            int result = videoMapper.insertVideo(video);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(VideoService.class, "球队添加时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 视频修改
     * @return
     */
    @Override
    public Map<String, Object> updateVideo(Video video) {
        try {

            if(video.getVideoType()==1){//视频
                String vid = video.getVid();
                Gson gson = new Gson();
                String post = CheckSumBuilder.getScreenshots(vid);

                WYYEntity rtn = gson.fromJson(post, new TypeToken<WYYEntity>(){}.getType());
                Map<String, Object> ret = rtn.getRet();
                String code = rtn.getCode();
                if(code.equals("200")){
                    String snapshotUrl = ret.get("snapshotUrl")+"";
                    video.setScreenshots(snapshotUrl);
                    String duration = (ret.get("duration")+"").substring(0,(ret.get("duration")+"").indexOf("."));
                    video.setDuration(Integer.parseInt(duration));
                }else{
                    return ControllerReturnBase.errorResule(Integer.parseInt(code), "请输入有效视频ID");
                }
            }

            int result = videoMapper.updateVideo(video);
            if(result<=0){
                return ControllerReturnBase.errorResule(1501, "服务器繁忙 请稍后重试");
            }

            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(VideoService.class, "视频修改时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 视频删除
     * @return
     */
    @Override
    public Map<String, Object> deleteVideo(String videoId,String userName,String password,String userId) {
        try {
            //查询当前登录用户的帐号和密码
            if (Utils.isNull(userName) || Utils.isNull(password)) {
                return ControllerReturnBase.errorResule(1501, "账户或密码未填写");
            }
            //根据账户名查询用户
            AdminUser adminUser = usersMapper.selectUserByUserName(userName);
            if (adminUser == null) {
                return ControllerReturnBase.errorResule(1502, "账户不存在");
            }
            //验证是否是当前登录账户
            if(!adminUser.getAdminId().equals(userId)){
                return ControllerReturnBase.errorResule(1502, "请输入当前登录的帐号");
            }
            //验证密码是否正确
            if (!adminUser.getPassword().equals(Utils.SHAEncrypt(password))) {
                return ControllerReturnBase.errorResule(1502, "密码错误");
            }
            //验证账户是否被冻结
            if (adminUser.getStatus()==2) {
                return ControllerReturnBase.errorResule(1502, "账户被冻结 请联系管理员");
            }

            int result = videoMapper.deleteVideoById(videoId);
            //返回参数封装
            Map<String, String> requst_parm = new HashMap<>();
            requst_parm.put("result", result+"");
            return ControllerReturnBase.successResule(result);
        } catch (Exception e) {
            LoggerUtil.outError(VideoService.class, "视频删除时发生错误", e);
            return ControllerReturnBase.errorResule();
        }
    }



}
