package com.ai.aris.server.webservice.bean;

/**
 * 检查项目
 * Created by lenovo on 2016/12/19.
 */
public class AiscItemMastData {
   private  long  itemmastPrice;
   private  String  itemmastEndesc ;
   private  String  itemmastDesc ;
   private  long  itemmastIsenhanced ;
   private  long  ordcategoryId ;
   private  long  itemmastExposurecount ;
   private  long  itemmastSevice  ;
   private  long  itemmastId ;
   private  String  itemmastCode  ;
   private  long  ordsubcategoryId  ;
   private  long itemmastWeight ;
   private String orgId;

    public String getOrgId() {
	return orgId;
}

public void setOrgId(String orgId) {
	this.orgId = orgId;
}

	public String getItemmastCode() {
        return itemmastCode;
    }

    public void setItemmastCode(String itemmastCode) {
        this.itemmastCode = itemmastCode;
    }

    public String getItemmastDesc() {
        return itemmastDesc;
    }

    public void setItemmastDesc(String itemmastDesc) {
        this.itemmastDesc = itemmastDesc;
    }

    public String getItemmastEndesc() {
        return itemmastEndesc;
    }

    public void setItemmastEndesc(String itemmastEndesc) {
        this.itemmastEndesc = itemmastEndesc;
    }

    public long getItemmastExposurecount() {
        return itemmastExposurecount;
    }

    public void setItemmastExposurecount(long itemmastExposurecount) {
        this.itemmastExposurecount = itemmastExposurecount;
    }

    public long getItemmastId() {
        return itemmastId;
    }

    public void setItemmastId(long itemmastId) {
        this.itemmastId = itemmastId;
    }

    public long getItemmastIsenhanced() {
        return itemmastIsenhanced;
    }

    public void setItemmastIsenhanced(long itemmastIsenhanced) {
        this.itemmastIsenhanced = itemmastIsenhanced;
    }

    public long getItemmastPrice() {
        return itemmastPrice;
    }

    public void setItemmastPrice(long itemmastPrice) {
        this.itemmastPrice = itemmastPrice;
    }

    public long getItemmastSevice() {
        return itemmastSevice;
    }

    public void setItemmastSevice(long itemmastSevice) {
        this.itemmastSevice = itemmastSevice;
    }

    public long getItemmastWeight() {
        return itemmastWeight;
    }

    public void setItemmastWeight(long itemmastWeight) {
        this.itemmastWeight = itemmastWeight;
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
}
