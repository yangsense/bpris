package com.ai.aris.server.webservice.bean.aitencent;

import java.util.List;

/**
 * Created by Walter on 2017/9/29.
 */
public class UploadDicomRequest {

    /**
     * studyId : XX
     * studyName : XX
     * studyType : XX
     * patientName : XX
     * seriesNo : XX
     * uploadDone : XX
     * images : [{"imageId":"xx","url":"xx","descPostion":"xx","content":"xx"}]
     */

    private String studyId;
    private String studyName;
    private String studyType;
    private String patientName;
    private String seriesNo;
    private String uploadDone;
    /**
     * imageId : xx
     * url : xx
     * descPostion : xx
     * content : xx
     */

    private List<ImagesBean> images;

    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getStudyName() {
        return studyName;
    }

    public void setStudyName(String studyName) {
        this.studyName = studyName;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getSeriesNo() {
        return seriesNo;
    }

    public void setSeriesNo(String seriesNo) {
        this.seriesNo = seriesNo;
    }

    public String getUploadDone() {
        return uploadDone;
    }

    public void setUploadDone(String uploadDone) {
        this.uploadDone = uploadDone;
    }

    public List<ImagesBean> getImages() {
        return images;
    }

    public void setImages(List<ImagesBean> images) {
        this.images = images;
    }

    public static class ImagesBean {
        private String imageId;
        private String url;
        private String descPosition;
        private String content;

        public String getImageId() {
            return imageId;
        }

        public void setImageId(String imageId) {
            this.imageId = imageId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescPosition() {
			return descPosition;
		}

		public void setDescPosition(String descPosition) {
			this.descPosition = descPosition;
		}

		public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
