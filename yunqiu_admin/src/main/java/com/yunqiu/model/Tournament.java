package com.yunqiu.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class Tournament implements Serializable {

    private String gameId;//主键
    private String userId;//比赛创建者
    private String leagueId;//关联赛事
    private String gameName;//比赛名称
    private int rounds;//轮次
    private String entryTeamA;//关联球队（A队/发起球队）
    private String entryTeamB;//关联球队（B队/对手球队）
    private int uniformTeamA;//主队队服 1：红 2：蓝 3：白 4：紫 5：橙
    private int uniformTeamB;//客队队服 1：红 2：蓝 3：白 4：紫 5：橙
    private int scoreTeamA;//A队/主队得分
    private int scoreTeamB;//B队/对手得分
    private int spotTeamA;//A队/主队点球数
    private int spotTeamB;//B队/对手点球数
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date gameTime;//比赛时间,yyyy-MM-dd HH:mm
    private double continueTime;//持续时间(单位：小时)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date applyEndTime;//报名截至时间
    private int gameSystem;//赛制 1:3人 2:5人 3:7人 4:8人 5:9人 6：11人
    private String gameSite;//比赛场地
    private double gameCost;//比赛费用，默认0(表示免费)
    private int valueAdded;//增值服务（选够视频/数据服务）0：暂不选购，1：数据 2：视频 3:视频+数据
    private int gameStatus;//比赛状态 0：不需要审核，不需要应战（赛事的比赛） 1 待审核 2：待应战 3：报名中 4：报名结束 5：进行中 6：已结束 7：已取消 8：拒绝比赛 9：拒绝应战
    private int scoreStatus;//	比分录入状态 1：未录入 2：已录入
    private String auditReason;//	管理员审核拒绝理由
    private String refuseReason;//对手拒绝应战理由
    private String cancelReason;//创建者取消理由
    private int classify;//比赛分类：1友谊赛，2赛事比赛，3训练
    private int informVideo;//视频是否通知 1是 2否
    private int showStatus;//是否启用 1是 2否
    private int enteringStatus;//是否是后台录入 1是 2否

    public void setEnteringStatus(int enteringStatus) {
        this.enteringStatus = enteringStatus;
    }
    public int getEnteringStatus() {
        return enteringStatus;
    }

    public void setInformVideo(int informVideo) {
        this.informVideo = informVideo;
    }

    public void setShowStatus(int showStatus) {
        this.showStatus = showStatus;
    }



    public int getInformVideo() {
        return informVideo;
    }

    public int getShowStatus() {
        return showStatus;
    }

    public String getGameId() {
        return gameId;
    }

    public String getUserId() {
        return userId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getGameName() {
        return gameName;
    }

    public int getRounds() {
        return rounds;
    }

    public String getEntryTeamA() {
        return entryTeamA;
    }

    public String getEntryTeamB() {
        return entryTeamB;
    }

    public int getUniformTeamA() {
        return uniformTeamA;
    }

    public int getUniformTeamB() {
        return uniformTeamB;
    }

    public int getScoreTeamA() {
        return scoreTeamA;
    }

    public int getScoreTeamB() {
        return scoreTeamB;
    }

    public int getSpotTeamA() {
        return spotTeamA;
    }

    public int getSpotTeamB() {
        return spotTeamB;
    }

    public Date getGameTime() {
        return gameTime;
    }

    public double getContinueTime() {
        return continueTime;
    }

    public Date getApplyEndTime() {
        return applyEndTime;
    }

    public int getGameSystem() {
        return gameSystem;
    }

    public String getGameSite() {
        return gameSite;
    }

    public double getGameCost() {
        return gameCost;
    }

    public int getValueAdded() {
        return valueAdded;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public int getScoreStatus() {
        return scoreStatus;
    }

    public String getAuditReason() {
        return auditReason;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public int getClassify() {
        return classify;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public void setEntryTeamA(String entryTeamA) {
        this.entryTeamA = entryTeamA;
    }

    public void setEntryTeamB(String entryTeamB) {
        this.entryTeamB = entryTeamB;
    }

    public void setUniformTeamA(int uniformTeamA) {
        this.uniformTeamA = uniformTeamA;
    }

    public void setUniformTeamB(int uniformTeamB) {
        this.uniformTeamB = uniformTeamB;
    }

    public void setScoreTeamA(int scoreTeamA) {
        this.scoreTeamA = scoreTeamA;
    }

    public void setScoreTeamB(int scoreTeamB) {
        this.scoreTeamB = scoreTeamB;
    }

    public void setSpotTeamA(int spotTeamA) {
        this.spotTeamA = spotTeamA;
    }

    public void setSpotTeamB(int spotTeamB) {
        this.spotTeamB = spotTeamB;
    }

    public void setGameTime(Date gameTime) {
        this.gameTime = gameTime;
    }

    public void setContinueTime(double continueTime) {
        this.continueTime = continueTime;
    }

    public void setApplyEndTime(Date applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public void setGameSystem(int gameSystem) {
        this.gameSystem = gameSystem;
    }

    public void setGameSite(String gameSite) {
        this.gameSite = gameSite;
    }

    public void setGameCost(double gameCost) {
        this.gameCost = gameCost;
    }

    public void setValueAdded(int valueAdded) {
        this.valueAdded = valueAdded;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

    public void setScoreStatus(int scoreStatus) {
        this.scoreStatus = scoreStatus;
    }

    public void setAuditReason(String auditReason) {
        this.auditReason = auditReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public void setClassify(int classify) {
        this.classify = classify;
    }
}
