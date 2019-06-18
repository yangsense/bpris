package com.ai.aris.server.webservice.bean;

/**
 * 操作员与登录科室登录
 * Created by lenovo on 2016/12/19.
 */
public class AiscLoginLocData {
   private  long operatorId  ;
   private  long loginlocId ;
   private  long locId  ;
   private  String orgId  ;


    public long getLocId() {
        return locId;
    }

    public void setLocId(long locId) {
        this.locId = locId;
    }

    public long getLoginlocId() {
        return loginlocId;
    }

    public void setLoginlocId(long loginlocId) {
        this.loginlocId = loginlocId;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
