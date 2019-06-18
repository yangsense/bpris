package com.ai.aris.server.webservice.bean.aitencent;


/**
 * Created by Walter on 2017/11/14.
 */
public class YLStudyResultResponse {

    /**
     * code : 0
     * message : 错误描述
     * data : {"studyId":"0","result":"诊断信息id","resultMsg":"诊断信息"}
     */

    private String code;
    private String message;
    private Data data; 
    /**
     * studyId : 0
     * result : 诊断信息id
     * resultMsg : 诊断信息
     */

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public static class Data{
        private String accesionNumber;
        private String examDesc;
        private String result;
        private String reportDate;
        private String reportTime;
        private String reportDoc;
		public String getAccesionNumber() {
			return accesionNumber;
		}
		public void setAccesionNumber(String accesionNumber) {
			this.accesionNumber = accesionNumber;
		}
		public String getExamDesc() {
			return examDesc;
		}
		public void setExamDesc(String examDesc) {
			this.examDesc = examDesc;
		}
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
		public String getReportDate() {
			return reportDate;
		}
		public void setReportDate(String reportDate) {
			this.reportDate = reportDate;
		}
		public String getReportTime() {
			return reportTime;
		}
		public void setReportTime(String reportTime) {
			this.reportTime = reportTime;
		}
		public String getReportDoc() {
			return reportDoc;
		}
		public void setReportDoc(String reportDoc) {
			this.reportDoc = reportDoc;
		}

      
    }
   
}
