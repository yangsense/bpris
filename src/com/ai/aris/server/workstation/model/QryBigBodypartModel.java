package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QryBigBodypartModel {

    private  String bodypartEndesc;
    private  long itemmastId;
    private  long bodypartPid;
    private  String bodypartType;
    private  long bodypartOrder;
    private  String bodypartDesc;
    private  String bodypartCode;
    private  String orgId;

//自定义字典

    public String getBodypartEndesc(){
        return bodypartEndesc;
    }
    public long getItemmastId(){
        return itemmastId;
    }
    public long getBodypartPid(){
        return bodypartPid;
    }
    public String getBodypartType(){
        return bodypartType;
    }
    public long getBodypartOrder(){
        return bodypartOrder;
    }
    public String getBodypartDesc(){
        return bodypartDesc;
    }
    public String getBodypartCode(){
        return bodypartCode;
    }
    public String getOrgId(){
        return orgId;
    }

    public  void setBodypartEndesc(String bodypartEndesc){
        this.bodypartEndesc = bodypartEndesc;
    }
    public  void setItemmastId(long itemmastId){
        this.itemmastId = itemmastId;
    }
    public  void setBodypartPid(long bodypartPid){
        this.bodypartPid = bodypartPid;
    }
    public  void setBodypartType(String bodypartType){
        this.bodypartType = bodypartType;
    }
    public  void setBodypartOrder(long bodypartOrder){
        this.bodypartOrder = bodypartOrder;
    }
    public  void setBodypartDesc(String bodypartDesc){
        this.bodypartDesc = bodypartDesc;
    }
    public  void setBodypartCode(String bodypartCode){
        this.bodypartCode = bodypartCode;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }

//自定义字典
}
