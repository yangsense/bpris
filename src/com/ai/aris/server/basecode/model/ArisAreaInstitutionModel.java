package com.ai.aris.server.basecode.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class ArisAreaInstitutionModel {

    private  String cityDesc;
    private  String cityCode;

//自定义字典

    public String getCityDesc(){
        return cityDesc;
    }
    public String getCityCode(){
        return cityCode;
    }

    public  void setCityDesc(String cityDesc){
        this.cityDesc = cityDesc;
    }
    public  void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }

//自定义字典
}
