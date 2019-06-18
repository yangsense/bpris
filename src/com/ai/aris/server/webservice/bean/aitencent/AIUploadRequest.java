package com.ai.aris.server.webservice.bean.aitencent;

import java.util.List;

/**
 * Created by Walter on 2017/9/27.
 */
public class AIUploadRequest {

    /**
     * studyId : xx
     * studyName :  xx
     * studyDate :  xx
     * studyType :  xx
     * patientName :  xx
     * patientId :  xx
     * patientGender :  xx
     * patientBirthday :  xx
     * images : [{"imageId":" xx ","url":" xx ","descPosition":" xx ","content":" xx "},{"imageId":" xx ","url":" xx ","descPosition":" xx ","content":" xx "},{"imageId":" xx ","url":" xx ","descPosition":" xx ","content":" xx "}]
     */

    private String studyId;
    private String studyName;
    private long studyDate;
    private String studyType;
    private String patientName;
    private String patientId;
    private String patientGender;
    private String patientBirthday;
    /**
     * imageId :  xx
     * url :  xx
     * descPosition :  xx
     * content :  xx
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

    public long getStudyDate() {
		return studyDate;
	}

	public void setStudyDate(long studyDate) {
		this.studyDate = studyDate;
	}


    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

	public String getStudyType() {
		return studyType;
	}

	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}

	public String getPatientGender() {
		return patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientBirthday() {
        return patientBirthday;
    }

    public void setPatientBirthday(String patientBirthday) {
        this.patientBirthday = patientBirthday;
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
