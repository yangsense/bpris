package com.ai.aris.server.basecode.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AiscOrdSubCategoryModel {

    private  long ordcategoryId;
    private  String ordsubcategoryCode;
    private  String ordsubcategoryEndesc;
    private  String ordsubcategoryDesc;
    private  long ordsubcategoryId;
    private  String ordsubcategoryNote;
    private String ordcategoryDesc ;
    
    
    
    
    public String getOrdcategoryDesc() {
		return ordcategoryDesc;
	}
	public void setOrdcategoryDesc(String ordcategoryDesc) {
		this.ordcategoryDesc = ordcategoryDesc;
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

}
