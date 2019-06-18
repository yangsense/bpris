package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AisFilesInfoModel {

    private  int filePrinted;
    private  String fileDesc;
    private  String fileType;
    private  long studyinfoId;
    private  long miId;
    private  long fileId;
    private  int fileArchive;
    private  float fileSize;
    private  String filePath;

//自定义字典

    public int getFilePrinted(){
        return filePrinted;
    }
    public String getFileDesc(){
        return fileDesc;
    }
    public String getFileType(){
        return fileType;
    }
    public long getStudyinfoId(){
        return studyinfoId;
    }
    public long getMiId(){
        return miId;
    }
    public long getFileId(){
        return fileId;
    }
    public int getFileArchive(){
        return fileArchive;
    }
    public float getFileSize(){
        return fileSize;
    }
    public String getFilePath(){
        return filePath;
    }

    public  void setFilePrinted(int filePrinted){
        this.filePrinted = filePrinted;
    }
    public  void setFileDesc(String fileDesc){
        this.fileDesc = fileDesc;
    }
    public  void setFileType(String fileType){
        this.fileType = fileType;
    }
    public  void setStudyinfoId(long studyinfoId){
        this.studyinfoId = studyinfoId;
    }
    public  void setMiId(long miId){
        this.miId = miId;
    }
    public  void setFileId(long fileId){
        this.fileId = fileId;
    }
    public  void setFileArchive(int fileArchive){
        this.fileArchive = fileArchive;
    }
    public  void setFileSize(float fileSize){
        this.fileSize = fileSize;
    }
    public  void setFilePath(String filePath){
        this.filePath = filePath;
    }

//自定义字典
}
