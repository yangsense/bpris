package com.ai.aris.server.common.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QryOperatorAscriptionModel {

    private  String operatorCode;
    private  String operatorPsw;
    private  String operatorName;

//自定义字典

    public String getOperatorCode(){
        return operatorCode;
    }
    public String getOperatorPsw(){
        return operatorPsw;
    }
    public String getOperatorName(){
        return operatorName;
    }

    public  void setOperatorCode(String operatorCode){
        this.operatorCode = operatorCode;
    }
    public  void setOperatorPsw(String operatorPsw){
        this.operatorPsw = operatorPsw;
    }
    public  void setOperatorName(String operatorName){
        this.operatorName = operatorName;
    }

//自定义字典
}
