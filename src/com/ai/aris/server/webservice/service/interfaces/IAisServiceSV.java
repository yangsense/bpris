package com.ai.aris.server.webservice.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.appframe2.common.DataContainerInterface;
import com.ai.aris.server.basecode.bean.AisPixInfoBean;
import com.ai.aris.server.basecode.bean.AisServiceLogBean;
import com.ai.aris.server.basecode.bean.AiscBodyPart2ItemBean;
import com.ai.aris.server.basecode.bean.AiscBodyPartBean;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscItemMastBean;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCat2LocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryBean;
import com.ai.aris.server.basecode.bean.AiscOrdSubCategoryBean;
import com.ai.aris.server.basecode.bean.AiscRoomBean;
import com.ai.aris.server.basecode.bean.AiscServerInfoBean;
import com.ai.aris.server.interfacereal.bean.AisPatientRealBean;
import com.ai.aris.server.interfacereal.bean.AisStudyinfoRealBean;
import com.ai.aris.server.interfacereal.bean.AiscBodypartRealBean;
import com.ai.aris.server.interfacereal.bean.AiscCareprovRealBean;
import com.ai.aris.server.interfacereal.bean.AiscEquipmentRealBean;
import com.ai.aris.server.interfacereal.bean.AiscItemmastRealBean;
import com.ai.aris.server.interfacereal.bean.AiscLocRealBean;
import com.ai.aris.server.interfacereal.bean.AiscRoomRealBean;
import com.ai.aris.server.interfacereal.bean.QryStudyItemInterfaceBean;
import com.ai.aris.server.interfacereal.bean.QryordsubcateINFBean;
import com.ai.aris.server.webservice.bean.AisPatientInfoData;
import com.ai.aris.server.webservice.bean.AisStudyReportData;
import com.ai.aris.server.workstation.bean.AisPatientInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoBean;
import com.ai.aris.server.workstation.model.AisFilesInfoModel;

public interface IAisServiceSV {
    public AisPixInfoBean[] getAisPixInfoBean(String patientId,String orgId) throws Exception;
    
    public AisPatientInfoBean[] getAisPatientBean(String idNumber) throws Exception;
    
    public boolean insertPatientBean(long id,String patientId,Map map) throws Exception;
    
    public boolean insertPixBean(long id,Map map) throws Exception;
    
    public void writeServiceLog(AisServiceLogBean bean) throws Exception;
    
    public boolean updatePatient(long id,Map map) throws Exception;
    
    public boolean insertStudyInfo(Map map) throws Exception;

    public <T extends DataContainerInterface> void save(T aBean) throws Exception;
    
    public String insertPatientBean(String orgId,AisPatientInfoData bean) throws Exception;
    
    public void insertPixBean(long id,String orgId,AisPatientInfoData bean) throws Exception;
    
    public void updatePatient(long id,AisPatientInfoData bean) throws Exception;
    
    public AiscLocRealBean getLocRealBean(long locId,String orgId) throws Exception;

    public AisStudyItemInfoBean[] getStudyItem(long studyinfoId) throws Exception;
    
    public AisPatientRealBean getRealGlobalId(long locId,String orgId) throws Exception;
    
    public void insertStudyinfoReal(long newStudyInfoId,AisStudyInfoBean bean)throws Exception;
    
    public AisStudyinfoRealBean getStudyinfoRealBean(long studyinfoId,String orgId) throws Exception;
    
    public void insertStudyItem(QryStudyItemInterfaceBean bean)throws Exception;
    
    public AiscCareprovRealBean getCareBean(long careId,String orgId) throws Exception;
    
    public AiscEquipmentRealBean getEquipmentBean(long equipId,String orgId) throws Exception;
    
    public AiscRoomRealBean getRoomReal(long roomId,String orgId) throws Exception;
    
    public AiscItemmastRealBean getStudyItemRealBean(long oldmastId,String orgId) throws Exception;
    
    public AiscBodypartRealBean getBodypartRealBean(long oldmastId,String orgId) throws Exception;
    
    public boolean deleteBusiData(String orgId) throws Exception;
    
    public void deleteStudyinfoReal(String orgId) throws Exception;
    
    public AiscServerInfoBean getServerInfo(AiscServerInfoBean bean) throws Exception;
    
    public AiscRoomBean getRoomInfo(AiscRoomBean bean) throws Exception;
    
    public AiscLocBean getLocInfo(AiscLocBean bean) throws Exception;
    
    public AiscEquipmentBean getEquipment(AiscEquipmentBean bean) throws Exception;
    
    public AiscOrdCategoryBean getCategory(AiscOrdCategoryBean bean) throws Exception;
    
    public QryordsubcateINFBean getAiscOrdSubCategory(AiscOrdSubCategoryBean bean,String orgId) throws Exception;
    
    public long getRealOrdcategoryId(long id,String orgId) throws Exception;
    
    public long getRealOrdSubcategoryId(long id,String orgId) throws Exception;
    
    public long getItemmastId(long id,String orgId) throws Exception;
    
    public AiscItemMastBean getAiscItemMast(AiscItemMastBean bean) throws Exception;
    
    public AiscBodyPartBean getAiscBodyPart(AiscBodyPartBean bean) throws Exception;
    
    public AiscBodyPart2ItemBean getAiscBodyPartItem(AiscBodyPart2ItemBean bean) throws Exception;
    
    public AiscOrdCat2LocBean getAiscOrdcat2loc(AiscOrdCat2LocBean bean) throws Exception;
    
    public AiscCareProvBean getAiscCareprov(AiscCareProvBean bean) throws Exception;
    
    public long getLocId(long id,String orgId) throws Exception;
    
    public long getRoomId(long id,String orgId) throws Exception;
    
    public long getServerId(long id) throws Exception;
    
    public long getBodyCode(long id,String orgId) throws Exception;
    
    public String sendData(String body,String orgId) throws Exception;
    
    public String sendBusiData(String body,String orgId) throws Exception;
    
    public void updateSynMark(List<AisStudyReportData> list)throws Exception;
    
    public String upPatientInfo(AisPatientInfoData patient,String orgId) throws Exception;
    
    public String upStudyInfo(AisStudyInfoBean studyinfo,String orgId) throws Exception;
    
    public void upStudyItemInfo(AisStudyItemInfoBean studyitemInfo,String orgId) throws Exception;
    
    public String upHzloc(String studyinfoId,String orgId,String conlocId,String conorgId,String locId,AiscLocBean aiscloc) throws Exception;
    
    public Map reportBack(String hzOrgId,String studyAccnumber,String hzReportExam,String hzReportResult,String ipport,String reportDoc,String verifyDoc,String studyTime) throws Exception;
    
    public String saveFilePDFUrl(AisFilesInfoModel filemodel) throws Exception;
}
