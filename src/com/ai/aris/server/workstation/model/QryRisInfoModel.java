package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QryRisInfoModel {

    private  Timestamp studyEndtime;
    private  String studyItemdesc;
    private  String patientId;
    private  long studyType;
    private  String studyConsultorg;
    private  String orgId;
    private  Timestamp studyDatetime;
    private  String studyAccnumber;
    private  String patientName;
    private  long studyinfoId;
    private  Timestamp studyOperationtime;
    private  long locId;
    private  Timestamp studyStarttime;

//自定义字典

    public Timestamp getStudyEndtime(){
        return studyEndtime;
    }
    public String getStudyItemdesc(){
        return studyItemdesc;
    }
    public String getPatientId(){
        return patientId;
    }
    public long getStudyType(){
        return studyType;
    }
    public String getStudyConsultorg(){
        return studyConsultorg;
    }
    public String getOrgId(){
        return orgId;
    }
    public Timestamp getStudyDatetime(){
        return studyDatetime;
    }
    public String getStudyAccnumber(){
        return studyAccnumber;
    }
    public String getPatientName(){
        return patientName;
    }
    public long getStudyinfoId(){
        return studyinfoId;
    }
    public Timestamp getStudyOperationtime(){
        return studyOperationtime;
    }
    public long getLocId(){
        return locId;
    }
    public Timestamp getStudyStarttime(){
        return studyStarttime;
    }

    public  void setStudyEndtime(Timestamp studyEndtime){
        this.studyEndtime = studyEndtime;
    }
    public  void setStudyItemdesc(String studyItemdesc){
        this.studyItemdesc = studyItemdesc;
    }
    public  void setPatientId(String patientId){
        this.patientId = patientId;
    }
    public  void setStudyType(long studyType){
        this.studyType = studyType;
    }
    public  void setStudyConsultorg(String studyConsultorg){
        this.studyConsultorg = studyConsultorg;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setStudyDatetime(Timestamp studyDatetime){
        this.studyDatetime = studyDatetime;
    }
    public  void setStudyAccnumber(String studyAccnumber){
        this.studyAccnumber = studyAccnumber;
    }
    public  void setPatientName(String patientName){
        this.patientName = patientName;
    }
    public  void setStudyinfoId(long studyinfoId){
        this.studyinfoId = studyinfoId;
    }
    public  void setStudyOperationtime(Timestamp studyOperationtime){
        this.studyOperationtime = studyOperationtime;
    }
    public  void setLocId(long locId){
        this.locId = locId;
    }
    public  void setStudyStarttime(Timestamp studyStarttime){
        this.studyStarttime = studyStarttime;
    }

//自定义字典
}
