package com.ai.aris.server.basecode.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QueryCountysModel {

    private  String countyCode;
    private  String cityCode;
    private  String countyDesc;

//自定义字典

    public String getCountyCode(){
        return countyCode;
    }
    public String getCityCode(){
        return cityCode;
    }
    public String getCountyDesc(){
        return countyDesc;
    }

    public  void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public  void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public  void setCountyDesc(String countyDesc){
        this.countyDesc = countyDesc;
    }

//自定义字典
}
