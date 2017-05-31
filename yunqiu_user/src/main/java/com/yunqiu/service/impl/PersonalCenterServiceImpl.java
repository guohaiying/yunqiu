package com.yunqiu.service.impl;

import com.yunqiu.dao.*;
import com.yunqiu.general.redis.RedisClient;
import com.yunqiu.general.token.Tokens;
import com.yunqiu.model.UserAuths;
import com.yunqiu.model.UserCloudData;
import com.yunqiu.model.UserFans;
import com.yunqiu.model.Users;
import com.yunqiu.service.PersonalCenterService;
import com.yunqiu.service.RegisterService;
import com.yunqiu.util.ControllerReturnBase;
import com.yunqiu.util.DateUtil;
import com.yunqiu.util.LoggerUtil;
import com.yunqiu.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心Service
 */
@Service
public class PersonalCenterServiceImpl implements PersonalCenterService{

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private UserAuthsMapper authsMapper;
    @Autowired
    private Tokens tokens;
    @Autowired
    private RedisClient redisClient;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private UserFansMapper userFansMapper;
    @Autowired
    private GeneralMapper generalMapper;
    @Autowired
    private UserCloudDataMapper userCloudDataMapper;

    /**
     * 修改用户资料
     * @param users
     * @return
     */
    @Override
    public Map<String, Object> modifyInfo(Users users) {
        try {
            //验证用户id是否为空
            if (Utils.isNull(users.getUser_id())){
                return ControllerReturnBase.errorResule(1501,"缺少用户id");
            }
            //验证用户是否存储
            Users db_users = usersMapper.selectUsersByUserId(users.getUser_id());
            if (db_users == null){
                return ControllerReturnBase.errorResule(1501,"用户不存在");
            }
            //昵称不为null，则修改昵称
            if (!Utils.isNull(users.getNickname())){
                db_users.setNickname(users.getNickname());
            }
            //头像不为空，则修改头像
            if(!Utils.isNull(users.getPortrait())){
                db_users.setPortrait(users.getPortrait());
            }
            //身高不为0，则修改身高
            if (users.getStature() != 0){
                db_users.setStature(users.getStature());
            }
            //体重不为0，则修改体重
            if (users.getWeight() != 0){
                db_users.setWeight(users.getWeight());
            }
            //性别不为0，则修改性别
            if (users.getSex() != 0){
                db_users.setSex(users.getSex());
            }
            //出生年月不为null，则修改出生年月跟年龄
            if (users.getBirthday() != null){
                //判断日期是否为有效日期
                Calendar cal = Calendar.getInstance();
                if (cal.before(users.getBirthday())){
                    return ControllerReturnBase.errorResule(1501,"出生日期不能大于当前日期");
                }
                db_users.setBirthday(users.getBirthday());
                db_users.setAge(DateUtil.getAge(users.getBirthday()));
            }
            //球龄不为0，则修改球龄
            if(users.getVeteran() != 0){
                db_users.setVeteran(users.getVeteran());
            }
            //惯用脚不为0，则修改
            if (users.getFoot() != 0){
                db_users.setFoot(users.getFoot());
            }
            //擅长位置
            if (!Utils.isNull(users.getPosition())){
                db_users.setPosition(users.getPosition());
            }
            //个人标签
            if (!Utils.isNull(users.getLabel())){
                db_users.setLabel(users.getLabel());
            }
            //省
            if (!Utils.isNull(users.getProvince())){
                db_users.setProvince(users.getProvince());
            }
            //市
            if (!Utils.isNull(users.getCity())){
                db_users.setCity(users.getCity());
            }
            //县
            if (!Utils.isNull(users.getArea())){
                db_users.setArea(users.getArea());
            }
            usersMapper.updateUsersByUserId(db_users);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(UserServiceImpl.class,"修改个人资料时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改密码
     * @param new_password
     * @param worn_password
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> updatePassword(String new_password, String worn_password, String user_id,int type) {
        try {
            //判断参数
            if (Utils.isNull(new_password) || Utils.isNull(user_id)){
                return ControllerReturnBase.errorResule(1501,"参数有误");
            }
            if (type != 1 && type != 2){
                return ControllerReturnBase.errorResule(1501,"业务类型错误");
            }
            //获取用户资料
            UserAuths userAuths = authsMapper.selectUserAuthsByUserIdAndType(user_id,1);
            if (userAuths == null){
                return ControllerReturnBase.errorResule(1502,"账户不存在");
            }
            //当type为1时，需要判断原始密码是否正确
            if (type == 1){
                String password = userAuths.getCredential();
                if (!password.equals(Utils.SHAEncrypt(worn_password))){
                    return ControllerReturnBase.errorResule(1502,"原始密码错误");
                }
            }
            //修改密码
            authsMapper.updateCredential(Utils.SHAEncrypt(new_password),user_id,1);
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterServiceImpl.class,"修改密码时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询绑定的账户（手机号、微信、QQ）
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> selectBound(String user_id) {
        try {
            if (Utils.isNull(user_id)){
                return ControllerReturnBase.errorResule(1501,"缺少用户id");
            }
            List<Map<String,Object>> userAuthsList = authsMapper.selectUserAuthsByUserId(user_id);
            return ControllerReturnBase.successResule(userAuthsList);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterServiceImpl.class,"查询绑定的账户信息发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 注销登录
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> logout(String user_id) {
        try {
            boolean boo = tokens.logout(user_id);
            if (boo){
                return ControllerReturnBase.errorResule(1502,"注销失败");
            }
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterServiceImpl.class,"注销登录时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询用户基础资料
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> selectInfo(String user_id) {
        try {
            Users users = usersMapper.selectUsersByUserId(user_id);
            if (users == null){
                return ControllerReturnBase.errorResule(1502,"用户不存在");
            }
            return ControllerReturnBase.successResule(users);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterServiceImpl.class,"查询个人基本资料时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 修改手机号码
     * @param worn_phone
     * @param new_phone
     * @param verifyCode
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> updatePhone(String worn_phone, String new_phone, String verifyCode, String user_id) {
        try {
            //验证参数
            if (Utils.isNull(worn_phone) || Utils.isNull(new_phone) || Utils.isNull(user_id)){
                return ControllerReturnBase.errorResule(1501,"缺少参数");
            }
            //验证验证码
            String redisKey = new_phone+"_"+6;
            String redisCode = redisClient.getString(redisKey);
            if (redisCode == null){
                return ControllerReturnBase.errorResule(1501,"请获取验证码");
            }
            if (!redisCode.equals(verifyCode)){
                return ControllerReturnBase.errorResule(1501,"验证码错误");
            }
            //获取账户
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(worn_phone,1);
            if (userAuths == null){
                return ControllerReturnBase.errorResule(1502,"账户不存在");
            }
            if (user_id.equals(userAuths.getUser_id())){
                return ControllerReturnBase.errorResule(1502,"与登录账户不匹配");
            }
            //修改手机号
            authsMapper.updatePhone(new_phone,userAuths.getUser_id());
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterServiceImpl.class,"修改手机号时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询是否已创建密码
     * @param user_id
     * @return
     */
    @Override
    public Map<String, Object> selectPassword(String user_id) {
        try {
            UserAuths userAuths = authsMapper.selectUserAuthsByUserIdAndType(user_id,1);
            if (userAuths == null){
                return ControllerReturnBase.errorResule(1502,"账户不存在");
            }
            Map<String,Object> requst_parm = new HashMap<>();
            if (userAuths.getCredential() == null){
                requst_parm.put("status",1);
            }else {
                requst_parm.put("status",2);
            }
            return ControllerReturnBase.successResule(requst_parm);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterServiceImpl.class,"查询是否已创建密码时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 解除第三方绑定
     * @param user_id
     * @param identifier
     * @param type
     * @return
     */
    @Override
    public Map<String, Object> unwrap(String user_id, String identifier, int type) {
        try {
            if (Utils.isNull(identifier)){
                return ControllerReturnBase.errorResule(1501,"openid不可为空");
            }
            if (type != 2 && type != 3){
                return ControllerReturnBase.errorResule(1501,"业务类型错误");
            }
            UserAuths userAuths = authsMapper.selectUserAuthsByUserIdAndType(user_id,type);
            if (userAuths == null){
                return ControllerReturnBase.errorResule(1501,"账户不存在");
            }
            if (!identifier.equals(userAuths.getIdentifier())){
                return ControllerReturnBase.errorResule(1501,"openid不匹配");
            }
            authsMapper.deleteUserAuthsById(userAuths.getAuth_id());
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterServiceImpl.class,"第三方账户解除时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 绑定第三方账户
     * @param user_id
     * @param identifier
     * @param type
     * @return
     */
    @Override
    public Map<String, Object> bound(String user_id, String identifier, int type,String ip) {
        try {
            //验证参数
            if (Utils.isNull(identifier)){
                return ControllerReturnBase.errorResule(1501,"openid不可为空");
            }
            if (type != 2 && type != 3){
                return ControllerReturnBase.errorResule(1501,"业务类型错误");
            }
            //判断该openid是否已绑定了手机号
            UserAuths userAuths = authsMapper.selectUserAuthsByIdentifier(identifier,type);
            if (userAuths != null){
                return ControllerReturnBase.errorResule(1502,"该账户已绑定手机号，请先解绑");
            }
            //判断该账户是否已经绑定了该类型的第三方账户
             userAuths = authsMapper.selectUserAuthsByUserIdAndType(user_id,type);
            if (userAuths != null){
                return ControllerReturnBase.errorResule(1502,"已绑定该类型账户，请先解绑");
            }
            //添加绑定
            int bool = registerService.register(identifier,null,ip,type,user_id);
            if (bool != 1){
                return ControllerReturnBase.errorResule(1502,"绑定失败");
            }
            return ControllerReturnBase.successResule();
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterServiceImpl.class,"绑定第三方账户时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }

    /**
     * 查询用户详细信息
     * @param user_id
     * @param operate_user
     * @return
     */
    @Override
    public Map<String, Object> selectUserInfo(String user_id, String operate_user) {
        try {
            //返回结果集
            Map<String,Object> param_userInfo = new HashMap<>();
            //获取用户基础信息
            Users db_users = usersMapper.selectUsersByUserId(user_id);
            //查询云五数据
            UserCloudData userCloudData = userCloudDataMapper.selectUserCloudDataByUserId(user_id);
            //存储到结果集
            param_userInfo.put("capacity",userCloudData);
            //判断当前是查询自己的资料还是他人的资料,分别封装基础信息
            Map<String,Object> param_basis = null;
            if (operate_user.equals(user_id)){
                param_userInfo.put("type",1);
                //查询自己的资料
                param_basis = new HashMap<>();
                param_basis.put("user_id",db_users.getUser_id());
                param_basis.put("nickname",db_users.getNickname());
                param_basis.put("portrait",db_users.getPortrait());
                //计算战力值
                param_basis.put("power",userCloudData.getPower());
                param_basis.put("gains",userCloudData.getGains());
                //查询关注的人数
                int focus = userFansMapper.selectFocusTotal(user_id);
                param_basis.put("focus",focus);
                //查询粉丝数
                int fans = userFansMapper.selectFansTotal(user_id);
                param_basis.put("fans",fans);
                //查询收藏
                int collect = generalMapper.selectGameCollection(user_id);
                param_basis.put("collect",collect);
                //存储到结果集
                param_userInfo.put("basis",param_basis);
            }else{
                param_userInfo.put("type",2);
                //查询他人的资料
                param_basis = new HashMap<>();
                param_basis.put("user_id",db_users.getUser_id());
                param_basis.put("nickname",db_users.getNickname());
                param_basis.put("portrait",db_users.getPortrait());
                param_basis.put("province",db_users.getProvince());
                param_basis.put("city",db_users.getCity());
                param_basis.put("area",db_users.getArea());
                param_basis.put("age",db_users.getAge());
                param_basis.put("position",db_users.getPosition());
                param_basis.put("label",db_users.getLabel());
                //关注状态 1：未关注  2：已关注
                UserFans userFans = userFansMapper.selectFans(operate_user,user_id);
                if (userFans == null){
                    param_basis.put("focus_status",1);
                }else {
                    param_basis.put("focus_status",2);
                }
                //存储到结果集
                param_userInfo.put("basis",param_basis);
            }
            //个人数据计算
            Map<String,Object> param_personalData = new HashMap<>();
            //参加联赛个数
            int leagueNumber = generalMapper.selectLeagueNumber(user_id);
            param_personalData.put("league_number",leagueNumber);
            //获取所有比赛
            List<Map<String,Object>> game_list = generalMapper.selectGameAll(user_id);
            int game_total = game_list.size();//比赛总场数
            //参加比赛总场数
            param_personalData.put("game_number",game_total);
            int victory = 0;//胜
            int flat = 0;//平
            int lose = 0;//负
            int no_entry = 0;//未录入
            //解析比赛，计算胜平负数据
            for (int item = 0;item<game_total;item++){
                Map<String,Object> game = game_list.get(item);
                if ((int)game.get("score_status") == 0){
                    //未录入数据
                    no_entry = no_entry+1;
                }else if ((int)game.get("score_teamA") == (int)game.get("score_teamB")){
                    //打平数据
                    flat = flat+1;
                }else {
                    Map<String,Object> game_member = generalMapper.selectGameMember(user_id,(String) game.get("game_id"));
                    if (game_member != null){
                        if (game_member.get("team_id").equals(game.get("entry_teamA"))){
                            //用户报名的A队比赛，并且A队得分大于B队得分，胜场+1,否则负场+1
                            if ((int)game.get("score_teamA") > (int)game.get("score_teamB")){
                                victory = victory+1;
                            }else{
                                lose = lose+1;
                            }
                        }else{
                            //用户报名的B队比赛，并且B队得分大于A队得分，胜场+1,否则负场+1
                            if ((int)game.get("score_teamA") < (int)game.get("score_teamB")){
                                victory = victory+1;
                            }else{
                                lose = lose+1;
                            }
                        }
                    }
                }
            }
            //胜场
            param_personalData.put("victory",victory);
            //负场
            param_personalData.put("lose",lose);
            //平场
            param_personalData.put("flat",flat);
            //未录入
            param_personalData.put("no_entry",no_entry);
            //设置比赛数据
            List<Map<String,Object>> game_grand = generalMapper.selectGameGrandByUserId(user_id);
            for (int item = 0;item < game_grand.size(); item++){
                Map<String,Object> grand = game_grand.get(item);
                if ((int)grand.get("type") == 1){
                    //进球
                    param_personalData.put("goal",grand.get("number"));
                }else if ((int)grand.get("type") == 6){
                    //助攻
                    param_personalData.put("assists",grand.get("number"));
                }else if ((int)grand.get("type") == 3){
                    //红牌
                    param_personalData.put("red_card",grand.get("number"));
                }else if ((int)grand.get("type") == 4){
                    //黄牌
                    param_personalData.put("yellow_card",grand.get("number"));
                }
            }
            //存储到结果集
            param_userInfo.put("personal_data",param_personalData);

            return ControllerReturnBase.successResule(param_userInfo);
        }catch (Exception e){
            LoggerUtil.outError(PersonalCenterServiceImpl.class,"查询用户详细信息时发生错误",e);
            return ControllerReturnBase.errorResule();
        }
    }


}
