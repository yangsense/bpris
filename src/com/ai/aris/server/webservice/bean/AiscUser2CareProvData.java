package com.ai.aris.server.webservice.bean;

/**
 * 操作员与医护人员关联
 * Created by lenovo on 2016/12/19.
 */
public class AiscUser2CareProvData {
   private  long operatorId ;
   private  long user2careprovId ;
   private  String operatorCode  ;
   private  long careprovId  ;

    public long getCareprovId() {
        return careprovId;
    }

    public void setCareprovId(long careprovId) {
        this.careprovId = careprovId;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public long getUser2careprovId() {
        return user2careprovId;
    }

    public void setUser2careprovId(long user2careprovId) {
        this.user2careprovId = user2careprovId;
    }
}
