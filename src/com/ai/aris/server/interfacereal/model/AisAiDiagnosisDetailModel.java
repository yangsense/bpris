package com.ai.aris.server.interfacereal.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AisAiDiagnosisDetailModel {

    private  String no;
    private  long marktype;
    private  long shape;
    private  String description;
    private  long studyDetailId;
    private  long seriesno;
    private  String shapedesc;
    private  String seriesuid;
    private  String globalStudyId;
    private  String dcmnolist;

//自定义字典

    public String getNo(){
        return no;
    }
    public long getMarktype(){
        return marktype;
    }
    public long getShape(){
        return shape;
    }
    public String getDescription(){
        return description;
    }
    public long getStudyDetailId(){
        return studyDetailId;
    }
    public long getSeriesno(){
        return seriesno;
    }
    public String getShapedesc(){
        return shapedesc;
    }
    public String getSeriesuid(){
        return seriesuid;
    }
    public String getGlobalStudyId(){
        return globalStudyId;
    }

    public  void setNo(String no){
        this.no = no;
    }
    public  void setMarktype(long marktype){
        this.marktype = marktype;
    }
    public  void setShape(long shape){
        this.shape = shape;
    }
    public  void setDescription(String description){
        this.description = description;
    }
    public  void setStudyDetailId(long studyDetailId){
        this.studyDetailId = studyDetailId;
    }
    public  void setSeriesno(long seriesno){
        this.seriesno = seriesno;
    }
    public  void setShapedesc(String shapedesc){
        this.shapedesc = shapedesc;
    }
    public  void setSeriesuid(String seriesuid){
        this.seriesuid = seriesuid;
    }
    public  void setGlobalStudyId(String globalStudyId){
        this.globalStudyId = globalStudyId;
    }
	public String getDcmnolist() {
		return dcmnolist;
	}
	public void setDcmnolist(String dcmnolist) {
		this.dcmnolist = dcmnolist;
	}

//自定义字典
}
