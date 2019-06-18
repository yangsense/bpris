package com.ai.aris.server.basecode.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AiscItemMastModel {

    private  float itemmastPrice;
    private  String itemmastEndesc;
    private  String itemmastDesc;
    private  long itemmastIsenhanced;
    private  int itemmastExposurecount;
    private  long itemmastSevice;
    private  long itemmastId;
    private  String itemmastCode;
    private  long ordsubcategoryId;
    private  long ordcategoryId;
    private  float itemmastWeight;
    private String ordsubcategoryDesc;
    private String ordcategoryDesc;
    private String orgId;
    private String orgName;
    private String pyInitial;

    @DictItem(dictType = "ITEMMAST_SERVICE",valueFiled = "itemmastSevice")
    private String itemmastSeviceDesc;
    @DictItem(dictType = "ITEMMAST_ISENHANCED",valueFiled = "itemmastIsenhanced")
    private String itemmastIsenhancedDesc;

    public String getPyInitial() {
		return pyInitial;
	}
	public void setPyInitial(String pyInitial) {
		this.pyInitial = pyInitial;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public long getItemmastIsenhanced() {
		return itemmastIsenhanced;
	}
	public void setItemmastIsenhanced(long itemmastIsenhanced) {
		this.itemmastIsenhanced = itemmastIsenhanced;
	}
	public long getItemmastSevice() {
		return itemmastSevice;
	}
	public void setItemmastSevice(long itemmastSevice) {
		this.itemmastSevice = itemmastSevice;
	}
	public String getOrdsubcategoryDesc() {
		return ordsubcategoryDesc;
	}
	public void setOrdsubcategoryDesc(String ordsubcategoryDesc) {
		this.ordsubcategoryDesc = ordsubcategoryDesc;
	}
	public String getOrdcategoryDesc() {
		return ordcategoryDesc;
	}
	public void setOrdcategoryDesc(String ordcategoryDesc) {
		this.ordcategoryDesc = ordcategoryDesc;
	}
	public long getOrdcategoryId() {
		return ordcategoryId;
	}
	public void setOrdcategoryId(long ordcategoryId) {
		this.ordcategoryId = ordcategoryId;
	}
	public float getItemmastPrice(){
        return itemmastPrice;
    }
    public String getItemmastEndesc(){
        return itemmastEndesc;
    }
    public String getItemmastDesc(){
        return itemmastDesc;
    }
    public int getItemmastExposurecount(){
        return itemmastExposurecount;
    }
    public long getItemmastId(){
        return itemmastId;
    }
    public String getItemmastCode(){
        return itemmastCode;
    }
    public long getOrdsubcategoryId(){
        return ordsubcategoryId;
    }
    public float getItemmastWeight(){
        return itemmastWeight;
    }

    public  void setItemmastPrice(float itemmastPrice){
        this.itemmastPrice = itemmastPrice;
    }
    public  void setItemmastEndesc(String itemmastEndesc){
        this.itemmastEndesc = itemmastEndesc;
    }
    public  void setItemmastDesc(String itemmastDesc){
        this.itemmastDesc = itemmastDesc;
    }
    public  void setItemmastIsenhanced(int itemmastIsenhanced){
        this.itemmastIsenhanced = itemmastIsenhanced;
    }
    public  void setItemmastExposurecount(int itemmastExposurecount){
        this.itemmastExposurecount = itemmastExposurecount;
    }
    public  void setItemmastSevice(int itemmastSevice){
        this.itemmastSevice = itemmastSevice;
    }
    public  void setItemmastId(long itemmastId){
        this.itemmastId = itemmastId;
    }
    public  void setItemmastCode(String itemmastCode){
        this.itemmastCode = itemmastCode;
    }
    public  void setOrdsubcategoryId(long ordsubcategoryId){
        this.ordsubcategoryId = ordsubcategoryId;
    }
    public  void setItemmastWeight(float itemmastWeight){
        this.itemmastWeight = itemmastWeight;
    }

    public String getItemmastSeviceDesc() {
        return itemmastSeviceDesc;
    }

    public void setItemmastSeviceDesc(String itemmastSeviceDesc) {
        this.itemmastSeviceDesc = itemmastSeviceDesc;
    }

    public String getItemmastIsenhancedDesc() {
        return itemmastIsenhancedDesc;
    }

    public void setItemmastIsenhancedDesc(String itemmastIsenhancedDesc) {
        this.itemmastIsenhancedDesc = itemmastIsenhancedDesc;
    }
}
