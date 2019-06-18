package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscEquipmentValue extends DataStructInterface{

  public final static  String S_EquipmentCode = "EQUIPMENT_CODE";
  public final static  String S_EquipmentPhone = "EQUIPMENT_PHONE";
  public final static  String S_ModalityEnabled = "MODALITY_ENABLED";
  public final static  String S_EquipmentIp = "EQUIPMENT_IP";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_ModalitySupportchinese = "MODALITY_SUPPORTCHINESE";
  public final static  String S_ModalitySupportid = "MODALITY_SUPPORTID";
  public final static  String S_ModalityId = "MODALITY_ID";
  public final static  String S_ModalityWorklist = "MODALITY_WORKLIST";
  public final static  String S_EquipmentPort = "EQUIPMENT_PORT";
  public final static  String S_OrdsubcategoryId = "ORDSUBCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_EquipmentManufacturer = "EQUIPMENT_MANUFACTURER";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_EquipmentStartdate = "EQUIPMENT_STARTDATE";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_EquipmentEndesc = "EQUIPMENT_ENDESC";
  public final static  String S_EquipmentAe = "EQUIPMENT_AE";
  public final static  String S_ModalitySupportsexage = "MODALITY_SUPPORTSEXAGE";


public String getEquipmentCode();

public String getEquipmentPhone();

public int getModalityEnabled();

public String getEquipmentIp();

public long getRoomId();

public int getModalitySupportchinese();

public int getModalitySupportid();

public long getModalityId();

public int getModalityWorklist();

public long getEquipmentPort();

public long getOrdsubcategoryId();

public String getOrgId();

public long getEquipmentId();

public String getEquipmentManufacturer();

public String getEquipmentDesc();

public Timestamp getEquipmentStartdate();

public long getLocId();

public String getEquipmentEndesc();

public String getEquipmentAe();

public int getModalitySupportsexage();


public  void setEquipmentCode(String value);

public  void setEquipmentPhone(String value);

public  void setModalityEnabled(int value);

public  void setEquipmentIp(String value);

public  void setRoomId(long value);

public  void setModalitySupportchinese(int value);

public  void setModalitySupportid(int value);

public  void setModalityId(long value);

public  void setModalityWorklist(int value);

public  void setEquipmentPort(long value);

public  void setOrdsubcategoryId(long value);

public  void setOrgId(String value);

public  void setEquipmentId(long value);

public  void setEquipmentManufacturer(String value);

public  void setEquipmentDesc(String value);

public  void setEquipmentStartdate(Timestamp value);

public  void setLocId(long value);

public  void setEquipmentEndesc(String value);

public  void setEquipmentAe(String value);

public  void setModalitySupportsexage(int value);
}
