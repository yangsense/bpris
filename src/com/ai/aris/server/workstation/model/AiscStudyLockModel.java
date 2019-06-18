package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AiscStudyLockModel {

    private  Timestamp startTime;
    private  Timestamp endTime;
    private  String lockStatus;
    private  String lockIp;
    private  String userId;
    private  String userName;
    private  long studyinfoId;

//自定义字典

    public Timestamp getStartTime(){
        return startTime;
    }
    public Timestamp getEndTime(){
        return endTime;
    }
    public String getLockStatus(){
        return lockStatus;
    }
    public String getLockIp(){
        return lockIp;
    }
    public String getUserId(){
        return userId;
    }
    public String getUserName(){
        return userName;
    }
    public long getStudyinfoId(){
        return studyinfoId;
    }

    public  void setStartTime(Timestamp startTime){
        this.startTime = startTime;
    }
    public  void setEndTime(Timestamp endTime){
        this.endTime = endTime;
    }
    public  void setLockStatus(String lockStatus){
        this.lockStatus = lockStatus;
    }
    public  void setLockIp(String lockIp){
        this.lockIp = lockIp;
    }
    public  void setUserId(String userId){
        this.userId = userId;
    }
    public  void setUserName(String userName){
        this.userName = userName;
    }
    public  void setStudyinfoId(long studyinfoId){
        this.studyinfoId = studyinfoId;
    }

//自定义字典
}
