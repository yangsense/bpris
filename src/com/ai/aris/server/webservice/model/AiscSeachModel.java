package com.ai.aris.server.webservice.model;

/**
 * Created by lenovo on 2016/12/13.
 */
public class AiscSeachModel {
    private String orgId ;
    private String isQuery ;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {

        this.orgId = orgId;
    }

    public String isQuery() {
        return isQuery;
    }

    public void setQuery(String query) {
        isQuery = query;
    }
}
