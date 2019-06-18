/**
  * Copyright 2017 bejson.com 
  */
package com.ai.aris.server.webservice.bean.aitencent;
import java.util.List;

/**
 * Auto-generated: 2017-10-11 9:4:54
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private String studyId;
    private List<MarkList> markList;
    private int result;
    public void setStudyId(String studyId) {
         this.studyId = studyId;
     }
     public String getStudyId() {
         return studyId;
     }

    public void setMarkList(List<MarkList> markList) {
         this.markList = markList;
     }
     public List<MarkList> getMarkList() {
         return markList;
     }

    public void setResult(int result) {
         this.result = result;
     }
     public int getResult() {
         return result;
     }

}