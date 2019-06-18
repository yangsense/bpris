package com.ai.aris.server.webservice;

import com.ai.aris.server.basecode.bean.AiscServerInfoBean;
import com.ai.aris.server.webservice.bean.*;

/**
 * Created by lenovo on 2016/12/19.
 */
public enum DataEnum {
	SEVERINFO("服务器信息",AiscServerInfoData.class, "SEVERINFO"),
    LOC("科室信息", AiscLocData.class,"LOC"),
    EQUIPMENT("设备信息",AiscEquipmentData.class,"EQUIPMENT"),
    ROOM("房间信息",AiscRoomData.class,"ROOM"),
    ORDCATEGORY("检查大类",AiscOrdCategoryData.class,"ORDCATEGORY"),
    ORDSUBCATEGORY("检查子类",AiscOrdSubCategoryData.class,"ORDSUBCATEGORY"),
    ITEMMAST("检查项目",AiscItemMastData.class,"ITEMMAST"),
    BODYPART("检查部位",AiscBodyPartData.class,"BODYPART"),
    BODYPART2ITEM("检查项目部位对应",AiscBodyPart2ItemData.class,"BODYPART2ITEM"),
    ORDCAT2LOC("检查大类与科室关联",AiscOrdCat2LocData.class,"ORDCAT2LOC"),
    CAREPROV("医护人员维护",AiscCareProvData.class,"CAREPROV"),
    LOGINLOC("操作员与登录科室登录",AiscLoginLocData.class, "LOGINLOC"),
    USER2CAREPROV("操作员与医护人员关联",AiscUser2CareProvData.class, "USER2CAREPROV");
    
        // 成员变量
        private String dataName;
//        private  Class beanClz;
        private Class dataClz;
        private String interfaceName;
        // 构造方法
        private DataEnum(String dataName,Class dataClz,String interfaceName) {
        this.dataName = dataName;
        this.dataClz = dataClz;
        this.interfaceName = interfaceName;
      }
    
    public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Class getDataClz() {
        return dataClz;
    }

    public void setDataClz(Class dataClz) {
        this.dataClz = dataClz;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
