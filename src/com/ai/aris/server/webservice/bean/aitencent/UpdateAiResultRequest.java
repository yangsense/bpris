package com.ai.aris.server.webservice.bean.aitencent;

import java.util.List;

/**
 * Created by Walter on 2017/9/29.
 */
public class UpdateAiResultRequest {

    /**
     * code : 0
     * message : 错误描述
     * studyId : 123123
     * orgId : 1231
     * status : 3
     * result : 3
     * markList : [{"no":"诊断信息id","shape":"诊断信息","shapeDesc":"诊断信息","dcmNoList":"诊断信息","markType":"诊断信息","desc":"诊断信息","seriesNo":"诊断信息","seriesUid":"诊断信息"},{"no":"诊断信息id","shape":"诊断信息","shapeDesc":"诊断信息","dcmNoList":"诊断信息","markType":"诊断信息","desc":"诊断信息","seriesNo":"诊断信息","seriesUid":"诊断信息"}]
     */

    private String code;
    private String message;
    private String studyId;
    private String orgId;
    private String status;
    private String result;
    /**
     * no : 诊断信息id
     * shape : 诊断信息
     * shapeDesc : 诊断信息
     * dcmNoList : 诊断信息
     * markType : 诊断信息
     * desc : 诊断信息
     * seriesNo : 诊断信息
     * seriesUid : 诊断信息
     */

    private List<MarkList> markList;

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

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<MarkList> getMarkList() {
        return markList;
    }

    public void setMarkList(List<MarkList> markList) {
        this.markList = markList;
    }

    public static class MarkListBean {
        private String no;
        private String shape;
        private String shapeDesc;
        private String dcmNoList;
        private String markType;
        private String desc;
        private String seriesNo;
        private String seriesUid;

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public String getShape() {
            return shape;
        }

        public void setShape(String shape) {
            this.shape = shape;
        }

        public String getShapeDesc() {
            return shapeDesc;
        }

        public void setShapeDesc(String shapeDesc) {
            this.shapeDesc = shapeDesc;
        }

        public String getDcmNoList() {
            return dcmNoList;
        }

        public void setDcmNoList(String dcmNoList) {
            this.dcmNoList = dcmNoList;
        }

        public String getMarkType() {
            return markType;
        }

        public void setMarkType(String markType) {
            this.markType = markType;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getSeriesNo() {
            return seriesNo;
        }

        public void setSeriesNo(String seriesNo) {
            this.seriesNo = seriesNo;
        }

        public String getSeriesUid() {
            return seriesUid;
        }

        public void setSeriesUid(String seriesUid) {
            this.seriesUid = seriesUid;
        }
    }
}
