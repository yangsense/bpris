package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AisReportUploadModel {

    private  Timestamp uploadtime;
    private  String patientName;
    private  long id;
    private  String patientId;
    private  String operatorCode;
    private  String fileName;
    private  String operatorName;
    private  long reportId;

//自定义字典

    public Timestamp getUploadtime(){
        return uploadtime;
    }
    public String getPatientName(){
        return patientName;
    }
    public long getId(){
        return id;
    }
    public String getPatientId(){
        return patientId;
    }
    public String getOperatorCode(){
        return operatorCode;
    }
    public String getFileName(){
        return fileName;
    }
    public String getOperatorName(){
        return operatorName;
    }
    public long getReportId(){
        return reportId;
    }

    public  void setUploadtime(Timestamp uploadtime){
        this.uploadtime = uploadtime;
    }
    public  void setPatientName(String patientName){
        this.patientName = patientName;
    }
    public  void setId(long id){
        this.id = id;
    }
    public  void setPatientId(String patientId){
        this.patientId = patientId;
    }
    public  void setOperatorCode(String operatorCode){
        this.operatorCode = operatorCode;
    }
    public  void setFileName(String fileName){
        this.fileName = fileName;
    }
    public  void setOperatorName(String operatorName){
        this.operatorName = operatorName;
    }
    public  void setReportId(long reportId){
        this.reportId = reportId;
    }

//自定义字典
}
