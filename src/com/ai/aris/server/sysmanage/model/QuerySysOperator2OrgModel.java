package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QuerySysOperator2OrgModel {

    private  String operatorCode;
    private  String stationCode;
    private  String orgName;
    private  String orgType;
    private  String stationName;
    private  String parentOrgId;
    private  String orgLevel;
    private  String orgId;

//自定义字典

    public String getOperatorCode(){
        return operatorCode;
    }
    public String getStationCode(){
        return stationCode;
    }
    public String getOrgName(){
        return orgName;
    }
    public String getOrgType(){
        return orgType;
    }
    public String getStationName(){
        return stationName;
    }
    public String getParentOrgId(){
        return parentOrgId;
    }
    public String getOrgLevel(){
        return orgLevel;
    }
    public String getOrgId(){
        return orgId;
    }

    public  void setOperatorCode(String operatorCode){
        this.operatorCode = operatorCode;
    }
    public  void setStationCode(String stationCode){
        this.stationCode = stationCode;
    }
    public  void setOrgName(String orgName){
        this.orgName = orgName;
    }
    public  void setOrgType(String orgType){
        this.orgType = orgType;
    }
    public  void setStationName(String stationName){
        this.stationName = stationName;
    }
    public  void setParentOrgId(String parentOrgId){
        this.parentOrgId = parentOrgId;
    }
    public  void setOrgLevel(String orgLevel){
        this.orgLevel = orgLevel;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }

//自定义字典
}
