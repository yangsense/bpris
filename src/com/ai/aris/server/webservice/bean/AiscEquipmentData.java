package com.ai.aris.server.webservice.bean;

import java.sql.Timestamp;
import java.util.*;

/**
 * 设备信息同步
 * Created by lenovo on 2016/12/19.
 */
public class AiscEquipmentData {
    private String equipmentCode;
    private String equipmentPhone;
    private long modalityEnabled ;
    private String equipmentIp ;
    private long roomId ;
    private long modalitySupportchinese;
    private long modalityId ;
    private long modalityWorklist;
    private long equipmentPort ;
    private String orgId;
    private long equipmentId ;
    private String equipmentManufacturer;
    private String equipmentDesc;
    private Timestamp equipmentStartdate ;
    private long locId;
    private String equipmentEndesc;
    private String equipmentAe;
    private long modalitySupportsexage;
    private long ordsubcategoryId;
    private long modalitySupportid;
    
    public long getOrdsubcategoryId() {
		return ordsubcategoryId;
	}

	public void setOrdsubcategoryId(long ordsubcategoryId) {
		this.ordsubcategoryId = ordsubcategoryId;
	}

	public long getModalitySupportid() {
		return modalitySupportid;
	}

	public void setModalitySupportid(long modalitySupportid) {
		this.modalitySupportid = modalitySupportid;
	}

	public Timestamp getEquipmentStartdate() {
		return equipmentStartdate;
	}

	public void setEquipmentStartdate(Timestamp equipmentStartdate) {
		this.equipmentStartdate = equipmentStartdate;
	}

	public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getEquipmentAe() {
        return equipmentAe;
    }

    public void setEquipmentAe(String equipmentAe) {
        this.equipmentAe = equipmentAe;
    }

    public String getEquipmentDesc() {
        return equipmentDesc;
    }

    public void setEquipmentDesc(String equipmentDesc) {
        this.equipmentDesc = equipmentDesc;
    }

    public String getEquipmentEndesc() {
        return equipmentEndesc;
    }

    public void setEquipmentEndesc(String equipmentEndesc) {
        this.equipmentEndesc = equipmentEndesc;
    }

    public long getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(long equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentIp() {
        return equipmentIp;
    }

    public void setEquipmentIp(String equipmentIp) {
        this.equipmentIp = equipmentIp;
    }

    public String getEquipmentManufacturer() {
        return equipmentManufacturer;
    }

    public void setEquipmentManufacturer(String equipmentManufacturer) {
        this.equipmentManufacturer = equipmentManufacturer;
    }

    public String getEquipmentPhone() {
        return equipmentPhone;
    }

    public void setEquipmentPhone(String equipmentPhone) {
        this.equipmentPhone = equipmentPhone;
    }

    public long getEquipmentPort() {
        return equipmentPort;
    }

    public void setEquipmentPort(long equipmentPort) {
        this.equipmentPort = equipmentPort;
    }

    public long getLocId() {
        return locId;
    }

    public void setLocId(long locId) {
        this.locId = locId;
    }

    public long getModalityEnabled() {
        return modalityEnabled;
    }

    public void setModalityEnabled(long modalityEnabled) {
        this.modalityEnabled = modalityEnabled;
    }

    public long getModalityId() {
        return modalityId;
    }

    public void setModalityId(long modalityId) {
        this.modalityId = modalityId;
    }

    public long getModalitySupportchinese() {
        return modalitySupportchinese;
    }

    public void setModalitySupportchinese(long modalitySupportchinese) {
        this.modalitySupportchinese = modalitySupportchinese;
    }

    public long getModalitySupportsexage() {
        return modalitySupportsexage;
    }

    public void setModalitySupportsexage(long modalitySupportsexage) {
        this.modalitySupportsexage = modalitySupportsexage;
    }

    public long getModalityWorklist() {
        return modalityWorklist;
    }

    public void setModalityWorklist(long modalityWorklist) {
        this.modalityWorklist = modalityWorklist;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
