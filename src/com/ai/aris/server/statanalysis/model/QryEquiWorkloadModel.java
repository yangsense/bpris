package com.ai.aris.server.statanalysis.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QryEquiWorkloadModel {

    private  String studyEndtime;
    private  long equipmentId;
    private  String studyDatetime;
    private  String cStudyDatetime;
    private  long studyitemNumber;
    private  String equipmentDesc;
    private  long locId;
    private  String studyStarttime;
    private  long studyitemPrice;
    private String orgId;
    private String zb;
    private long modalityId;
    
	public long getModalityId() {
		return modalityId;
	}
	public void setModalityId(long modalityId) {
		this.modalityId = modalityId;
	}
	public String getZb() {
		return zb;
	}
	public void setZb(String zb) {
		this.zb = zb;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getStudyEndtime() {
		return studyEndtime;
	}
	public void setStudyEndtime(String studyEndtime) {
		this.studyEndtime = studyEndtime;
	}
	public long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(long equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getStudyDatetime() {
		return studyDatetime;
	}
	public void setStudyDatetime(String studyDatetime) {
		this.studyDatetime = studyDatetime;
	}
	public String getcStudyDatetime() {
		return cStudyDatetime;
	}
	public void setcStudyDatetime(String cStudyDatetime) {
		this.cStudyDatetime = cStudyDatetime;
	}
	public long getStudyitemNumber() {
		return studyitemNumber;
	}
	public void setStudyitemNumber(long studyitemNumber) {
		this.studyitemNumber = studyitemNumber;
	}
	public String getEquipmentDesc() {
		return equipmentDesc;
	}
	public void setEquipmentDesc(String equipmentDesc) {
		this.equipmentDesc = equipmentDesc;
	}
	public long getLocId() {
		return locId;
	}
	public void setLocId(long locId) {
		this.locId = locId;
	}
	public String getStudyStarttime() {
		return studyStarttime;
	}
	public void setStudyStarttime(String studyStarttime) {
		this.studyStarttime = studyStarttime;
	}
	public long getStudyitemPrice() {
		return studyitemPrice;
	}
	public void setStudyitemPrice(long studyitemPrice) {
		this.studyitemPrice = studyitemPrice;
	}



//自定义字典
}
