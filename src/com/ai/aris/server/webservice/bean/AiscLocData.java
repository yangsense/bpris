package com.ai.aris.server.webservice.bean;

/**
 * Created by lenovo on 2016/12/13.
 */

public class AiscLocData {
    private String  locCode ;
    private String  locPhone ;
    private String  locType  ;
    private String  locAddress  ;
    private String  locEndesc ;
    private String  locDesc ;
    private long  serverId  ;
    private long  locId ;
    private String  locIndex ;
    private String  orgId;

    public String getLocAddress() {
        return locAddress;
    }

    public void setLocAddress(String locAddress) {
        this.locAddress = locAddress;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }

    public String getLocDesc() {
        return locDesc;
    }

    public void setLocDesc(String locDesc) {
        this.locDesc = locDesc;
    }

    public String getLocEndesc() {
        return locEndesc;
    }

    public void setLocEndesc(String locEndesc) {
        this.locEndesc = locEndesc;
    }

    public long getLocId() {
        return locId;
    }

    public void setLocId(long locId) {
        this.locId = locId;
    }

    public String getLocIndex() {
        return locIndex;
    }

    public void setLocIndex(String locIndex) {
        this.locIndex = locIndex;
    }

    public String getLocPhone() {
        return locPhone;
    }

    public void setLocPhone(String locPhone) {
        this.locPhone = locPhone;
    }

    public String getLocType() {
        return locType;
    }

    public void setLocType(String locType) {
        this.locType = locType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public long getServerId() {
        return serverId;
    }

    public void setServerId(long serverId) {
        this.serverId = serverId;
    }
}
