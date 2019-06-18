package com.ai.aris.server.webservice.bean;

/**
 * 检查大类与科室关联
 * Created by lenovo on 2016/12/19.
 */
public class AiscOrdCat2LocData {


   private long ordcatId  ;
   private long locId  ;
   private long ordcat2locId  ;
   private String orgId ;


    public long getLocId() {
        return locId;
    }

    public void setLocId(long locId) {
        this.locId = locId;
    }

    public long getOrdcat2locId() {
        return ordcat2locId;
    }

    public void setOrdcat2locId(long ordcat2locId) {
        this.ordcat2locId = ordcat2locId;
    }

    public long getOrdcatId() {
        return ordcatId;
    }

    public void setOrdcatId(long ordcatId) {
        this.ordcatId = ordcatId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
