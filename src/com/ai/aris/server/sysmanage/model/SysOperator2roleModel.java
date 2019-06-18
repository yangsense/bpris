package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class SysOperator2roleModel {

    private  long roleId;
    private  String remarks;
    private  String operatorCode;

//自定义字典

    public long getRoleId(){
        return roleId;
    }
    public String getRemarks(){
        return remarks;
    }
    public String getOperatorCode(){
        return operatorCode;
    }

    public  void setRoleId(long roleId){
        this.roleId = roleId;
    }
    public  void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public  void setOperatorCode(String operatorCode){
        this.operatorCode = operatorCode;
    }

//自定义字典
}
