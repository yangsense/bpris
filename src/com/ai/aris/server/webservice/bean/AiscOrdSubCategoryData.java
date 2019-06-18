package com.ai.aris.server.webservice.bean;

/**
 * 检查子类
 * Created by lenovo on 2016/12/19.
 */
public class AiscOrdSubCategoryData {
    private  long ordcategoryId ;
    private  String ordsubcategoryCode ;
    private  String ordsubcategoryEndesc;
    private  String ordsubcategoryDesc;
    private  long ordsubcategoryId  ;
    private  String ordsubcategoryNote ;



    public String getOrdsubcategoryCode() {
        return ordsubcategoryCode;
    }

    public void setOrdsubcategoryCode(String ordsubcategoryCode) {
        this.ordsubcategoryCode = ordsubcategoryCode;
    }

    public String getOrdsubcategoryDesc() {
        return ordsubcategoryDesc;
    }

    public void setOrdsubcategoryDesc(String ordsubcategoryDesc) {
        this.ordsubcategoryDesc = ordsubcategoryDesc;
    }

    public String getOrdsubcategoryEndesc() {
        return ordsubcategoryEndesc;
    }

    public void setOrdsubcategoryEndesc(String ordsubcategoryEndesc) {
        this.ordsubcategoryEndesc = ordsubcategoryEndesc;
    }

    public long getOrdcategoryId() {
        return ordcategoryId;
    }

    public void setOrdcategoryId(long ordcategoryId) {
        this.ordcategoryId = ordcategoryId;
    }

    public long getOrdsubcategoryId() {
        return ordsubcategoryId;
    }

    public void setOrdsubcategoryId(long ordsubcategoryId) {
        this.ordsubcategoryId = ordsubcategoryId;
    }

    public String getOrdsubcategoryNote() {
        return ordsubcategoryNote;
    }

    public void setOrdsubcategoryNote(String ordsubcategoryNote) {
        this.ordsubcategoryNote = ordsubcategoryNote;
    }
}
