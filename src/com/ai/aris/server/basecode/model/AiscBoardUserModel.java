package com.ai.aris.server.basecode.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AiscBoardUserModel {

    private  long userId;
    private  long roomId;
    private  Timestamp createTime;
    private  String userName;
    private  String userCode;
    private  Timestamp authTimme;
    private  String status;
    private  String authLevel;
    private  String orgId;

//自定义字典

    public long getUserId(){
        return userId;
    }
    public long getRoomId(){
        return roomId;
    }
    public Timestamp getCreateTime(){
        return createTime;
    }
    public String getUserName(){
        return userName;
    }
    public String getUserCode(){
        return userCode;
    }
    public Timestamp getAuthTimme(){
        return authTimme;
    }
    public String getStatus(){
        return status;
    }
    public String getAuthLevel(){
        return authLevel;
    }
    public String getOrgId(){
        return orgId;
    }

    public  void setUserId(long userId){
        this.userId = userId;
    }
    public  void setRoomId(long roomId){
        this.roomId = roomId;
    }
    public  void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }
    public  void setUserName(String userName){
        this.userName = userName;
    }
    public  void setUserCode(String userCode){
        this.userCode = userCode;
    }
    public  void setAuthTimme(Timestamp authTimme){
        this.authTimme = authTimme;
    }
    public  void setStatus(String status){
        this.status = status;
    }
    public  void setAuthLevel(String authLevel){
        this.authLevel = authLevel;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }

//自定义字典
}
