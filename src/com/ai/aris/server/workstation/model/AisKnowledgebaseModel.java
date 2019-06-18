package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AisKnowledgebaseModel {

    private  String collectorCode;
    private  Timestamp operateDatetime;
    private  String keydesc;
    private  String casegroupdesc;
    private  long reportId;
    private  long knowledgebaseid;

//自定义字典

    public String getCollectorCode(){
        return collectorCode;
    }
    public Timestamp getOperateDatetime(){
        return operateDatetime;
    }
    public String getKeydesc(){
        return keydesc;
    }
    public String getCasegroupdesc(){
        return casegroupdesc;
    }
    public long getReportId(){
        return reportId;
    }
    public long getKnowledgebaseid(){
        return knowledgebaseid;
    }

    public  void setCollectorCode(String collectorCode){
        this.collectorCode = collectorCode;
    }
    public  void setOperateDatetime(Timestamp operateDatetime){
        this.operateDatetime = operateDatetime;
    }
    public  void setKeydesc(String keydesc){
        this.keydesc = keydesc;
    }
    public  void setCasegroupdesc(String casegroupdesc){
        this.casegroupdesc = casegroupdesc;
    }
    public  void setReportId(long reportId){
        this.reportId = reportId;
    }
    public  void setKnowledgebaseid(long knowledgebaseid){
        this.knowledgebaseid = knowledgebaseid;
    }

//自定义字典
}
