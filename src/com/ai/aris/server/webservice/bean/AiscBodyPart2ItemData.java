package com.ai.aris.server.webservice.bean;

/**
 * 检查项目部位对应
 * Created by lenovo on 2016/12/19.
 */
public class AiscBodyPart2ItemData {

   private  long itemmastId  ;
   private  long bodypart2Id  ;
   private  String bodypartCode ;
   private  String orgId  ;


    public long getBodypart2Id() {
        return bodypart2Id;
    }

    public void setBodypart2Id(long bodypart2Id) {
        this.bodypart2Id = bodypart2Id;
    }

    public String getBodypartCode() {
        return bodypartCode;
    }

    public void setBodypartCode(String bodypartCode) {
        this.bodypartCode = bodypartCode;
    }

    public long getItemmastId() {
        return itemmastId;
    }

    public void setItemmastId(long itemmastId) {
        this.itemmastId = itemmastId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
