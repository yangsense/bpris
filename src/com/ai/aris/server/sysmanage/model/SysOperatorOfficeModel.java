package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class SysOperatorOfficeModel {

    private  String operatorCode;
    private  Timestamp createTimes;
    private  String state;
    private  String id;
    private  String relationId;
    private  String orgId;
    private  String officeId;

//自定义字典

    public String getOperatorCode(){
        return operatorCode;
    }
    public Timestamp getCreateTimes(){
        return createTimes;
    }
    public String getState(){
        return state;
    }
    public String getId(){
        return id;
    }
    public String getRelationId(){
        return relationId;
    }
    public String getOrgId(){
        return orgId;
    }
    public String getOfficeId(){
        return officeId;
    }

    public  void setOperatorCode(String operatorCode){
        this.operatorCode = operatorCode;
    }
    public  void setCreateTimes(Timestamp createTimes){
        this.createTimes = createTimes;
    }
    public  void setState(String state){
        this.state = state;
    }
    public  void setId(String id){
        this.id = id;
    }
    public  void setRelationId(String relationId){
        this.relationId = relationId;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setOfficeId(String officeId){
        this.officeId = officeId;
    }

//自定义字典
}
