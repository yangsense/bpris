package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class SysOfficeModel {

    private  String createPerson;
    private  String state;
    private  String officeCode;
    private  Timestamp createTime;
    private  String orgCode;
    private  String officeName;
    private  String officeId;

//自定义字典

    public String getCreatePerson(){
        return createPerson;
    }
    public String getState(){
        return state;
    }
    public String getOfficeCode(){
        return officeCode;
    }
    public Timestamp getCreateTime(){
        return createTime;
    }
    public String getOrgCode(){
        return orgCode;
    }
    public String getOfficeName(){
        return officeName;
    }
    public String getOfficeId(){
        return officeId;
    }

    public  void setCreatePerson(String createPerson){
        this.createPerson = createPerson;
    }
    public  void setState(String state){
        this.state = state;
    }
    public  void setOfficeCode(String officeCode){
        this.officeCode = officeCode;
    }
    public  void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }
    public  void setOrgCode(String orgCode){
        this.orgCode = orgCode;
    }
    public  void setOfficeName(String officeName){
        this.officeName = officeName;
    }
    public  void setOfficeId(String officeId){
        this.officeId = officeId;
    }

//自定义字典
}
