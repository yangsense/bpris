package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QuerySysStationModel {

    private  String stationCode;
    private  String stationName;
    private  String remark;
    private  long sysType;

//自定义字典

    public String getStationCode(){
        return stationCode;
    }
    public String getStationName(){
        return stationName;
    }
    public String getRemark(){
        return remark;
    }
    public long getSysType(){
        return sysType;
    }

    public  void setStationCode(String stationCode){
        this.stationCode = stationCode;
    }
    public  void setStationName(String stationName){
        this.stationName = stationName;
    }
    public  void setRemark(String remark){
        this.remark = remark;
    }
    public  void setSysType(long sysType){
        this.sysType = sysType;
    }

//自定义字典
}
