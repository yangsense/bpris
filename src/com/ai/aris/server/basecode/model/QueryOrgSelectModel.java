package com.ai.aris.server.basecode.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QueryOrgSelectModel {

    private  String countyCode;
    private  String cityCode;
    private  String orgName;
    private  String orgId;

//自定义字典

    public String getCountyCode(){
        return countyCode;
    }
    public String getCityCode(){
        return cityCode;
    }
    public String getOrgName(){
        return orgName;
    }
    public String getOrgId(){
        return orgId;
    }

    public  void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public  void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public  void setOrgName(String orgName){
        this.orgName = orgName;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }

//自定义字典
}
