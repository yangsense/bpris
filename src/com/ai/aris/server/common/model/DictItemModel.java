package com.ai.aris.server.common.model;
import java.sql.Timestamp;

public class DictItemModel{

    private  String itemDesc;
    private  String parentItemNo;
    private  long itemOrder;
    private  String itemNamePy;
    private  String mappingDictName;
    private  long itemLevel;
    private  String itemState;
    private  String createUser;
    private  String dictName;
    private  String itemName;
    private  String mappingItemNo;
    private  String itemNamePycap;
    private  Timestamp createDate;
    private  String itemNo;

    public String getItemDesc(){
        return itemDesc;
    }
    public String getParentItemNo(){
        return parentItemNo;
    }
    public long getItemOrder(){
        return itemOrder;
    }
    public String getItemNamePy(){
        return itemNamePy;
    }
    public String getMappingDictName(){
        return mappingDictName;
    }
    public long getItemLevel(){
        return itemLevel;
    }
    public String getItemState(){
        return itemState;
    }
    public String getCreateUser(){
        return createUser;
    }
    public String getDictName(){
        return dictName;
    }
    public String getItemName(){
        return itemName;
    }
    public String getMappingItemNo(){
        return mappingItemNo;
    }
    public String getItemNamePycap(){
        return itemNamePycap;
    }
    public Timestamp getCreateDate(){
        return createDate;
    }
    public String getItemNo(){
        return itemNo;
    }

    public  void setItemDesc(String itemDesc){
        this.itemDesc = itemDesc;
    }
    public  void setParentItemNo(String parentItemNo){
        this.parentItemNo = parentItemNo;
    }
    public  void setItemOrder(long itemOrder){
        this.itemOrder = itemOrder;
    }
    public  void setItemNamePy(String itemNamePy){
        this.itemNamePy = itemNamePy;
    }
    public  void setMappingDictName(String mappingDictName){
        this.mappingDictName = mappingDictName;
    }
    public  void setItemLevel(long itemLevel){
        this.itemLevel = itemLevel;
    }
    public  void setItemState(String itemState){
        this.itemState = itemState;
    }
    public  void setCreateUser(String createUser){
        this.createUser = createUser;
    }
    public  void setDictName(String dictName){
        this.dictName = dictName;
    }
    public  void setItemName(String itemName){
        this.itemName = itemName;
    }
    public  void setMappingItemNo(String mappingItemNo){
        this.mappingItemNo = mappingItemNo;
    }
    public  void setItemNamePycap(String itemNamePycap){
        this.itemNamePycap = itemNamePycap;
    }
    public  void setCreateDate(Timestamp createDate){
        this.createDate = createDate;
    }
    public  void setItemNo(String itemNo){
        this.itemNo = itemNo;
    }

}
