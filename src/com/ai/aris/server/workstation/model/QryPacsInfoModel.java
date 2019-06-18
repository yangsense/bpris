package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QryPacsInfoModel {

    private  String modalitiesinstudy;
    private  String patientid;
    private  String receptiondate;
    private  long patientkey;
    private  String patientname;
    private  String accessionnumber;

//自定义字典

    public String getModalitiesinstudy(){
        return modalitiesinstudy;
    }
    public String getPatientid(){
        return patientid;
    }
    public String getReceptiondate(){
        return receptiondate;
    }
    public long getPatientkey(){
        return patientkey;
    }
    public String getPatientname(){
        return patientname;
    }
    public String getAccessionnumber(){
        return accessionnumber;
    }

    public  void setModalitiesinstudy(String modalitiesinstudy){
        this.modalitiesinstudy = modalitiesinstudy;
    }
    public  void setPatientid(String patientid){
        this.patientid = patientid;
    }
    public  void setReceptiondate(String receptiondate){
        this.receptiondate = receptiondate;
    }
    public  void setPatientkey(long patientkey){
        this.patientkey = patientkey;
    }
    public  void setPatientname(String patientname){
        this.patientname = patientname;
    }
    public  void setAccessionnumber(String accessionnumber){
        this.accessionnumber = accessionnumber;
    }

//自定义字典
}
