package com.ai.aris.server.webservice.bean;

/**
 * 检查大类
 * Created by lenovo on 2016/12/19.
 */
public class AiscOrdCategoryData {
    private  String ordcategoryCode;
    private  long ordcategoryId;
    private  String ordcategoryEndesc  ;
    private  String ordcategoryDesc ;
    private String orgId;

    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrdcategoryCode() {
        return ordcategoryCode;
    }

    public void setOrdcategoryCode(String ordcategoryCode) {
        this.ordcategoryCode = ordcategoryCode;
    }

    public String getOrdcategoryDesc() {
        return ordcategoryDesc;
    }

    public void setOrdcategoryDesc(String ordcategoryDesc) {
        this.ordcategoryDesc = ordcategoryDesc;
    }

    public String getOrdcategoryEndesc() {
        return ordcategoryEndesc;
    }

    public void setOrdcategoryEndesc(String ordcategoryEndesc) {
        this.ordcategoryEndesc = ordcategoryEndesc;
    }

    public long getOrdcategoryId() {
        return ordcategoryId;
    }

    public void setOrdcategoryId(long ordcategoryId) {
        this.ordcategoryId = ordcategoryId;
    }
}
