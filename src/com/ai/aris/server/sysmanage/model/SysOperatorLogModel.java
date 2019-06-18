package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class SysOperatorLogModel {

    private  String operatorCode;
    private  long logId;
    private  String operatorType;
    private  String operatorDesc;
    private  Timestamp operatorDate;

//自定义字典

    public String getOperatorCode(){
        return operatorCode;
    }
    public long getLogId(){
        return logId;
    }
    public String getOperatorType(){
        return operatorType;
    }
    public String getOperatorDesc(){
        return operatorDesc;
    }
    public Timestamp getOperatorDate(){
        return operatorDate;
    }

    public  void setOperatorCode(String operatorCode){
        this.operatorCode = operatorCode;
    }
    public  void setLogId(long logId){
        this.logId = logId;
    }
    public  void setOperatorType(String operatorType){
        this.operatorType = operatorType;
    }
    public  void setOperatorDesc(String operatorDesc){
        this.operatorDesc = operatorDesc;
    }
    public  void setOperatorDate(Timestamp operatorDate){
        this.operatorDate = operatorDate;
    }

//自定义字典
}
