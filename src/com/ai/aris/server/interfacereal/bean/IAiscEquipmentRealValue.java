package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscEquipmentRealValue extends DataStructInterface{

  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_EquipmentRealId = "EQUIPMENT_REAL_ID";
  public final static  String S_OldEquipmentId = "OLD_EQUIPMENT_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getEquipmentId();

public Timestamp getCreateTime();

public long getEquipmentRealId();

public long getOldEquipmentId();

public String getOrgId();


public  void setEquipmentId(long value);

public  void setCreateTime(Timestamp value);

public  void setEquipmentRealId(long value);

public  void setOldEquipmentId(long value);

public  void setOrgId(String value);
}
