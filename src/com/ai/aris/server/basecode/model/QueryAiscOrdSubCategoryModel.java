package com.ai.aris.server.basecode.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QueryAiscOrdSubCategoryModel {

    private  String countyCode;
    private  String cityCode;
    private  long ordcategoryId;
    private  String ordsubcategoryCode;
    private  String ordsubcategoryEndesc;
    private  String ordsubcategoryDesc;
    private  long ordsubcategoryId;
    private  String ordsubcategoryNote;
    private  String orgId;
    private  String ordcategoryDesc;

//自定义字典
    private String cityCode2;
	public String getCityCode2() {
		return cityCode2;
	}
	public void setCityCode2(String cityCode2) {
		this.cityCode2 = cityCode2;
	}
	public String getCountyCode2() {
		return countyCode2;
	}
	public void setCountyCode2(String countyCode2) {
		this.countyCode2 = countyCode2;
	}
	public String getOrgId2() {
		return orgId2;
	}
	public void setOrgId2(String orgId2) {
		this.orgId2 = orgId2;
	}
	public String getSelectType() {
		return selectType;
	}
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	private String countyCode2;
    private String orgId2;
	private String selectType;

    private String ordsubcategoryCode2;

    public String getOrdsubcategoryCode2() {
		return ordsubcategoryCode2;
	}
	public void setOrdsubcategoryCode2(String ordsubcategoryCode2) {
		this.ordsubcategoryCode2 = ordsubcategoryCode2;
	}
	public String getCountyCode(){
        return countyCode;
    }
    public String getCityCode(){
        return cityCode;
    }
    public long getOrdcategoryId(){
        return ordcategoryId;
    }
    public String getOrdsubcategoryCode(){
        return ordsubcategoryCode;
    }
    public String getOrdsubcategoryEndesc(){
        return ordsubcategoryEndesc;
    }
    public String getOrdsubcategoryDesc(){
        return ordsubcategoryDesc;
    }
    public long getOrdsubcategoryId(){
        return ordsubcategoryId;
    }
    public String getOrdsubcategoryNote(){
        return ordsubcategoryNote;
    }
    public String getOrgId(){
        return orgId;
    }
    public String getOrdcategoryDesc(){
        return ordcategoryDesc;
    }

    public  void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public  void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public  void setOrdcategoryId(long ordcategoryId){
        this.ordcategoryId = ordcategoryId;
    }
    public  void setOrdsubcategoryCode(String ordsubcategoryCode){
        this.ordsubcategoryCode = ordsubcategoryCode;
    }
    public  void setOrdsubcategoryEndesc(String ordsubcategoryEndesc){
        this.ordsubcategoryEndesc = ordsubcategoryEndesc;
    }
    public  void setOrdsubcategoryDesc(String ordsubcategoryDesc){
        this.ordsubcategoryDesc = ordsubcategoryDesc;
    }
    public  void setOrdsubcategoryId(long ordsubcategoryId){
        this.ordsubcategoryId = ordsubcategoryId;
    }
    public  void setOrdsubcategoryNote(String ordsubcategoryNote){
        this.ordsubcategoryNote = ordsubcategoryNote;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setOrdcategoryDesc(String ordcategoryDesc){
        this.ordcategoryDesc = ordcategoryDesc;
    }

//自定义字典
}
