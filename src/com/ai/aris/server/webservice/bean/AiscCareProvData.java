package com.ai.aris.server.webservice.bean;

/**
 * 医护人员维护
 * Created by lenovo on 2016/12/19.
 */
public class AiscCareProvData {
    private long careprovTypeid  ;
    private String careprovName ;
    private String orgId ;
    private long locId  ;
    private String careprovCode  ;
    private long careprovId  ;

    public String getCareprovCode() {
        return careprovCode;
    }

    public void setCareprovCode(String careprovCode) {
        this.careprovCode = careprovCode;
    }

    public long getCareprovId() {
        return careprovId;
    }

    public void setCareprovId(long careprovId) {
        this.careprovId = careprovId;
    }

    public String getCareprovName() {
        return careprovName;
    }

    public void setCareprovName(String careprovName) {
        this.careprovName = careprovName;
    }

    public long getCareprovTypeid() {
        return careprovTypeid;
    }

    public void setCareprovTypeid(long careprovTypeid) {
        this.careprovTypeid = careprovTypeid;
    }

    public long getLocId() {
        return locId;
    }

    public void setLocId(long locId) {
        this.locId = locId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
