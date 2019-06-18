package com.ai.aris.web.controller.sysmanage;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.publicsv.interfaces.ISysManagePublicSV;
import com.ai.aris.server.sysmanage.bean.*;
import com.ai.aris.server.sysmanage.model.OrgManageSearchModel;
import com.ai.aris.server.sysmanage.service.impl.SysOrgSVImpl;
import com.ai.aris.server.sysmanage.service.interfaces.ISysOrgSV;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.util.cache.DictCache;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by liym5 on 2018/8/3.
 */
@Controller
@RequestMapping("sysOrg")
public class SysOrgController {
    private Log logger = LogFactory.getLog(RoleManageController.class);
    private ISysManagePublicSV sv = (ISysManagePublicSV)ServiceFactory.getService(ISysManagePublicSV.class);
    private ISysOrgSV sysOrgSV = (ISysOrgSV) ServiceFactory.getService(ISysOrgSV.class);
    private ISysManagePublicSV sysManagePublicSV = (ISysManagePublicSV)ServiceFactory.getService(ISysManagePublicSV.class);

    @RequestMapping("index.html")
    public String index(HttpServletRequest request, Model model) throws Exception {
        return "sysmanage/sysorg/orgManageInit";
    }
    @RequestMapping("/getOrgTreeByOperator.ajax")
    @ResponseBody
    public JSONArray getOrgTreeByOperator(HttpServletRequest request, Model model,String id) {
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        JSONArray array = new JSONArray();
        try {
            SysOrgBean[] opOrgs = sv.getOperatorRelateOrgInfoByOperatorCode(operatorCode, ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG, true);
            Set<String> opOrgIds = new HashSet<String>();
            for (ISysOrgValue opOrg : opOrgs) {
                opOrgIds.add(opOrg.getOrgId());
            }
            //针对首次初始化时id=-1的处理： 顶层为-1的工号，返父节点为-1的org, 顶层非-1时，返当前org
            if("-1".equals(id)){
                SysOperator2OrgBean sysOperator2org = sv.getSysOperator2org(operatorCode,ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG);
                id = sysOperator2org.getOrgId();
                QueryOrgTreeBean[] OrgTrees = null;
                if("-1".equals(id) ){
                    OrgTrees = sysOrgSV.getOrgTreeData(id);
                }else{
                    OrgTrees = sysOrgSV.getOrgTreeDataById(id);
                }

                Map<String, SysOrgSVImpl.OrgNode> orgMap = new HashMap<String, SysOrgSVImpl.OrgNode>();
                List<SysOrgSVImpl.OrgNode> orgNodes = new ArrayList<SysOrgSVImpl.OrgNode>();
                JSONObject obj = null;
                for (QueryOrgTreeBean org : OrgTrees) {
                    obj = new JSONObject();
                    obj.put("id", org.getOrgId());
                    obj.put("name", org.getOrgName());
                    obj.put("isParent", org.getChildren()==0? false : true);
                    obj.put("parentOrgId", org.getParentOrgId());
                    array.add(obj);
                }
            } else{
                array = sysOrgSV.getOperatorOrgTreeJson(opOrgIds, id);
            }
        } catch (Exception e) {

            logger.error("取操作员组织树失败.", e);
        }
        return  array;
    }
    @RequestMapping("/queryOrgList.ajax")
    @ResponseBody
    public JSONObject queryOrgList(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "rows", defaultValue = "10") int rows, String orgName,HttpServletRequest request) {
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        ResultDTO result = new ResultDTO(page, rows);
        try {
            result = sysOrgSV.queryPageList(orgName, result,operatorCode);
        }catch (Exception e){
            logger.info("queryOrgList.ajax error,error info:",e);
        }
        return AjaxUtil.jqGridJson(result);
    }
    @RequestMapping("viewOrgDetail.html")
    public String viewOrgDetail(HttpServletRequest request, Model model,String orgId,String parentOrgId) throws Exception {
        SysOrgBean bean = new SysOrgBean();
        bean.setOrgId(orgId);
        bean.setParentOrgId(parentOrgId);
        if(bean != null){
            try {
                bean = sysOrgSV.getOrgBeanByOrgId(bean.getOrgId());
                model.addAttribute("provinceValue1", bean.getProvinceCode()==null?"61":bean.getProvinceCode());
                model.addAttribute("cityValue1", bean.getCityCode()==null?"6104":bean.getCityCode());
                model.addAttribute("countyValue1", bean.getDistrictCode());
                model.addAttribute("townValue1", bean.getStreetCode());
                model.addAttribute("villageValue1", bean.getVillageCode());
                //政府办机构隶属行政区划-地址下拉框信息加载
                String divisionCode = bean.getSubordinateDivisionCode();
                if("".equals(divisionCode) || divisionCode == null){
                    divisionCode="6101";
                }
                DictBean dBean =  DictCache.getDictItemByDictNameAndItemNo("GB/T2260", divisionCode);
                String village = "";
                String town = "";
                String county = "";
                String city = "";
                String province = bean.getProvinceCode()==null?"61":bean.getProvinceCode();
                if(dBean!=null){
                    if(dBean.getItemLevel() == 5){
                        village = divisionCode;
                        DictBean townDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", dBean.getParentItemNo()) ;
                        town = townDictBean.getItemNo();
                        DictBean countyDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", townDictBean.getParentItemNo()) ;
                        county = countyDictBean.getItemNo();
                        DictBean cityDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", countyDictBean.getParentItemNo()) ;
                        city = cityDictBean.getItemNo();
                        DictBean provinceDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", cityDictBean.getParentItemNo()) ;
                        province = provinceDictBean.getItemNo();
                    }else if(dBean.getItemLevel() == 4){
                        town = divisionCode;
                        DictBean countyDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", dBean.getParentItemNo()) ;
                        county = countyDictBean.getItemNo();
                        DictBean cityDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", countyDictBean.getParentItemNo()) ;
                        city = cityDictBean.getItemNo();
                        DictBean provinceDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", cityDictBean.getParentItemNo()) ;
                        province = provinceDictBean.getItemNo();
                    }else if(dBean.getItemLevel() == 3){
                        county = divisionCode;
                        DictBean cityDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", dBean.getParentItemNo()) ;
                        city = cityDictBean.getItemNo();
                        DictBean provinceDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", cityDictBean.getParentItemNo()) ;
                        province = provinceDictBean.getItemNo();
                    }else if(dBean.getItemLevel() == 2){
                        city = divisionCode;
                        DictBean provinceDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", dBean.getParentItemNo()) ;
                        if(provinceDictBean != null){
                            province = provinceDictBean.getItemNo();
                        }else {
                            province = "61";
                        }
                    }
                }
                if (StringUtils.isBlank(province)) {
                    province = "61";
                }
                model.addAttribute("bean", bean);
                model.addAttribute("provinceValue", province);
                model.addAttribute("cityValue", city);
                model.addAttribute("countyValue",county);
                model.addAttribute("townValue", town);
                model.addAttribute("villageValue", village);

                if(!"".equals(bean.getProvinceCode())&& null != bean.getProvinceCode()){
                    model.addAttribute("provinceName", getAreaName("GB/T2260",bean.getProvinceCode()));
                }
                if(!"".equals(bean.getCityCode())&& null != bean.getCityCode()){
                    model.addAttribute("cityName", getAreaName("GB/T2260",bean.getCityCode()));
                }
                if(!"".equals(bean.getDistrictCode())&& null != bean.getDistrictCode()){
                    model.addAttribute("countyName", getAreaName("GB/T2260",bean.getDistrictCode()));
                }
                if(!"".equals(bean.getStreetCode()) && null != bean.getStreetCode()){
                    model.addAttribute("townName", getAreaName("GB/T2260",bean.getStreetCode()));
                }
                if(!"".equals(bean.getVillageCode()) && null != bean.getVillageCode()){
                    model.addAttribute("villageName", getAreaName("GB/T2260",bean.getVillageCode()));
                }
                //其它字典信息加载
                DictBean[] managementCodeBean = DictCache.getDictItemsByDictName("JGFLGLDM"); //机构分类管理代码
                DictBean[] economicTypeCodeBean = DictCache.getDictItemsByDictName("JJLX"); //经济类型代码
                List<IDictValue> orgTypeFirstList = DictCache.getDictItemByDictNameAndAndLevel("JGLB", "1") ; //机构类别（一级）
                List<IDictValue> orgTypeSecondList = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("JGLB",bean.getOrgTypeFirst(), "2") ; //机构类别（二级）
                List<IDictValue> orgTypeThirdList = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("JGLB",bean.getOrgTypeSecond(), "3") ; //机构类别（三级）
                DictBean[] orgClassLevelBean = DictCache.getDictItemsByDictName("YYJB"); //医院等级（级）
                DictBean[] orgClassGradeBean = DictCache.getDictItemsByDictName("YYDJ"); //	医院等级（等）
                DictBean[] organizerTypeCodeBean = DictCache.getDictItemsByDictName("ZBDWLB"); //设置/主办单位类别
                DictBean[] subordinateRelationshipCodeBean = DictCache.getDictItemsByDictName("JGLSGX"); //政府办机构隶属关系代码
                DictBean[] subordinateDivisionCodeBean = DictCache.getDictItemsByDictName("GB/T2260"); //政府办机构隶属行政区划代码
                OrgManageSearchModel searchModel = new OrgManageSearchModel();
                searchModel.setManagementCodeBean(managementCodeBean);
                searchModel.setEconomicTypeCodeBean(economicTypeCodeBean);
                searchModel.setOrgTypeFirstList(orgTypeFirstList);
                searchModel.setOrgTypeSecondList(orgTypeSecondList);
                searchModel.setOrgTypeThirdList(orgTypeThirdList);
                searchModel.setOrgClassLevelBean(orgClassLevelBean);
                searchModel.setOrgClassGradeBean(orgClassGradeBean);
                searchModel.setOrganizerTypeCodeBean(organizerTypeCodeBean);
                searchModel.setSubordinateRelationshipCodeBean(subordinateRelationshipCodeBean);
                searchModel.setSubordinateDivisionCodeBean(subordinateDivisionCodeBean);
                model.addAttribute("searchModel",searchModel);
            } catch (Exception e) {
                logger.error("取组织信息失败. orgId=" + bean.getOrgId(), e);
            }
        }
        return "sysmanage/sysorg/orgmanage-show";
    }
    @RequestMapping("updateOrgDetail.html")
    public String updateOrgDetail(HttpServletRequest request, Model model,String parentOrgId,String orgId) throws Exception {
        SysOrgBean bean = new SysOrgBean();
        bean.setOrgId(orgId);
        bean.setParentOrgId(parentOrgId);
        if(bean != null){
            try {
                bean = sysOrgSV.getOrgBeanByOrgId(bean.getOrgId());
                //所属区域-地址下拉框信息加载
                model.addAttribute("provinceValue1", bean.getProvinceCode()==null?"61":bean.getProvinceCode());
                model.addAttribute("cityValue1", bean.getCityCode()==null?"6104":bean.getCityCode());
                model.addAttribute("countyValue1", bean.getDistrictCode());
                model.addAttribute("townValue1", bean.getStreetCode());
                model.addAttribute("villageValue1", bean.getVillageCode());
                //政府办机构隶属行政区划-地址下拉框信息加载
                String divisionCode = bean.getSubordinateDivisionCode();
                String village = "";
                String town = "";
                String county = "";
                String city = "";
                String province = bean.getProvinceCode()==null?"61":bean.getProvinceCode();
                if(!"".equals(divisionCode) && null != divisionCode){
                    DictBean dBean =  DictCache.getDictItemByDictNameAndItemNo("GB/T2260", divisionCode);
                    if(dBean != null){
                        if(dBean.getItemLevel() == 5){
                            village = divisionCode;
                            DictBean townDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", dBean.getParentItemNo()) ;
                            town = townDictBean.getItemNo();
                            DictBean countyDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", townDictBean.getParentItemNo()) ;
                            county = countyDictBean.getItemNo();
                            DictBean cityDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", countyDictBean.getParentItemNo()) ;
                            city = cityDictBean.getItemNo();
                            DictBean provinceDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", cityDictBean.getParentItemNo()) ;
                            province = provinceDictBean.getItemNo();
                        }else if(dBean.getItemLevel() == 4){
                            town = divisionCode;
                            DictBean countyDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", dBean.getParentItemNo()) ;
                            county = countyDictBean.getItemNo();
                            DictBean cityDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", countyDictBean.getParentItemNo()) ;
                            city = cityDictBean.getItemNo();
                            DictBean provinceDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", cityDictBean.getParentItemNo()) ;
                            province = provinceDictBean.getItemNo();
                        }else if(dBean.getItemLevel() == 3){
                            county = divisionCode;
                            DictBean cityDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", dBean.getParentItemNo()) ;
                            city = cityDictBean.getItemNo();
                            DictBean provinceDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", cityDictBean.getParentItemNo()) ;
                            province = provinceDictBean.getItemNo();
                        }else if(dBean.getItemLevel() == 2){
                            city = divisionCode;
                            DictBean provinceDictBean = DictCache.getDictItemByDictNameAndItemNo("GB/T2260", dBean.getParentItemNo()) ;
                            if(provinceDictBean != null){
                                province = provinceDictBean.getItemNo();
                            }else {
                                province = "61";
                            }
                        }
                    }
                }
                if (StringUtils.isBlank(province)) {
                    province = "61";
                }
                model.addAttribute("provinceValue", province);
                model.addAttribute("cityValue", city);
                model.addAttribute("countyValue",county);
                model.addAttribute("townValue", town);
                model.addAttribute("villageValue", village);


                //其它字典信息加载
                DictBean[] managementCodeBean = DictCache.getDictItemsByDictName("JGFLGLDM"); //机构分类管理代码
                DictBean[] economicTypeCodeBean = DictCache.getDictItemsByDictName("JJLX"); //经济类型代码
                List<IDictValue> orgTypeFirstList = DictCache.getDictItemByDictNameAndAndLevel("JGLB", "1") ; //机构类别（一级）
                List<IDictValue> orgTypeSecondList = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("JGLB",bean.getOrgTypeFirst(), "2") ; //机构类别（二级）
                List<IDictValue> orgTypeThirdList = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("JGLB",bean.getOrgTypeSecond(), "3") ; //机构类别（三级）
                DictBean[] orgClassLevelBean = DictCache.getDictItemsByDictName("YYJB"); //医院等级（级）
                DictBean[] orgClassGradeBean = DictCache.getDictItemsByDictName("YYDJ"); //	医院等级（等）
                DictBean[] organizerTypeCodeBean = DictCache.getDictItemsByDictName("ZBDWLB"); //设置/主办单位类别
                DictBean[] subordinateRelationshipCodeBean = DictCache.getDictItemsByDictName("JGLSGX"); //政府办机构隶属关系代码
                DictBean[] subordinateDivisionCodeBean = DictCache.getDictItemsByDictName("GB/T2260"); //政府办机构隶属行政区划代码
                OrgManageSearchModel searchModel = new OrgManageSearchModel();
                searchModel.setManagementCodeBean(managementCodeBean);
                searchModel.setEconomicTypeCodeBean(economicTypeCodeBean);
                searchModel.setOrgTypeFirstList(orgTypeFirstList);
                searchModel.setOrgTypeSecondList(orgTypeSecondList);
                searchModel.setOrgTypeThirdList(orgTypeThirdList);
                searchModel.setOrgClassLevelBean(orgClassLevelBean);
                searchModel.setOrgClassGradeBean(orgClassGradeBean);
                searchModel.setOrganizerTypeCodeBean(organizerTypeCodeBean);
                searchModel.setSubordinateRelationshipCodeBean(subordinateRelationshipCodeBean);
                searchModel.setSubordinateDivisionCodeBean(subordinateDivisionCodeBean);
                String  parentOrgName = sysOrgSV.getOrgBeanByOrgId(bean.getParentOrgId()).getOrgName();
                model.addAttribute("bean", bean);
                model.addAttribute("searchModel",searchModel);
                model.addAttribute("parentOrgName",parentOrgName);
            } catch (Exception e) {
                logger.error("取组织信息失败. orgId=" + bean.getOrgId(), e);
            }
        }
        return "sysmanage/sysorg/orgmanage-edit";
    }
    public String getAreaName(String dictName,String itemNo){
        DictBean provinceDictBean = DictCache.getDictItemByDictNameAndItemNo(dictName,itemNo);
        if(provinceDictBean != null){
            return provinceDictBean.getItemName();
        }else {
            return "陕西省";
        }
    }
    @RequestMapping("/saveSysOrg")
    @ResponseBody
    public JSONObject saveSysOrg(@RequestBody SysOrgBean bean, String province, String city, String county, String town, String village, HttpServletRequest request) {
        OrgManageSearchModel searchModel = new OrgManageSearchModel();
        searchModel.setProvinceCode(province);
        searchModel.setCityCode(city);
        searchModel.setDistrictCode(county);
        searchModel.setStreetCode(town);
        searchModel.setVillageCode(village);
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        String result = "0";
        String message = "修改成功!";
        JSONObject jsonObj = new JSONObject();
        try {
            sysOrgSV.updateOrgInfo(operatorCode, bean, searchModel);
        } catch (Exception e) {
            logger.error("保存组织信息异常，异常信息：" + e.getMessage());
            result = "-1";
            message = e.getMessage();
        }
        //传新增结果到前台
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return  jsonObj;
    }
    @RequestMapping("addOrgDetail.html")
    public String addOrgDetail(HttpServletRequest request, Model model,String parentOrgId,long orgLevel) throws Exception {
        //地址信息
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        SysOrgBean bean = new SysOrgBean();
        SysOrgBean sysOrg = sysManagePublicSV.getOperatorBelongOrg(operatorCode);
        if(sysOrg!=null){
            model.addAttribute("provinceValue1", bean.getProvinceCode()==null?"61":bean.getProvinceCode());
            model.addAttribute("cityValue1", bean.getCityCode()==null?"6104":bean.getCityCode());
        }
        //取机构相关信息
        DictBean[] managementCodeBean = DictCache.getDictItemsByDictName("JGFLGLDM"); //机构分类管理代码
        DictBean[] economicTypeCodeBean = DictCache.getDictItemsByDictName("JJLX"); //经济类型代码
        List<IDictValue> orgTypeFirstList = DictCache.getDictItemByDictNameAndAndLevel("JGLB", "1") ; //机构类别（一级）
        List<IDictValue> orgTypeSecondList = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("JGLB","A", "2") ; //机构类别（二级）
        List<IDictValue> orgTypeThirdList = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("JGLB","A1", "3") ; //机构类别（三级）
        DictBean[] orgClassLevelBean = DictCache.getDictItemsByDictName("YYJB"); //医院等级（级）
        DictBean[] orgClassGradeBean = DictCache.getDictItemsByDictName("YYDJ"); //	医院等级（等）
        DictBean[] organizerTypeCodeBean = DictCache.getDictItemsByDictName("ZBDWLB"); //设置/主办单位类别
        DictBean[] subordinateRelationshipCodeBean = DictCache.getDictItemsByDictName("JGLSGX"); //政府办机构隶属关系代码
        DictBean[] subordinateDivisionCodeBean = DictCache.getDictItemsByDictName("GB/T2260"); //政府办机构隶属行政区划代码
        OrgManageSearchModel searchModel = new OrgManageSearchModel();
        searchModel.setManagementCodeBean(managementCodeBean);
        searchModel.setEconomicTypeCodeBean(economicTypeCodeBean);
        searchModel.setOrgTypeFirstList(orgTypeFirstList);
        searchModel.setOrgTypeSecondList(orgTypeSecondList);
        searchModel.setOrgTypeThirdList(orgTypeThirdList);
        searchModel.setOrgClassLevelBean(orgClassLevelBean);
        searchModel.setOrgClassGradeBean(orgClassGradeBean);
        searchModel.setOrganizerTypeCodeBean(organizerTypeCodeBean);
        searchModel.setSubordinateRelationshipCodeBean(subordinateRelationshipCodeBean);
        searchModel.setSubordinateDivisionCodeBean(subordinateDivisionCodeBean);

        //父级别
        bean.setOrgLevel(orgLevel);
        //父节点
        bean.setParentOrgId(parentOrgId);
        String parentOrgName = "";
        if (StringUtils.isNotBlank(bean.getParentOrgId())) {
            parentOrgName = sysOrgSV.getOrgBeanByOrgId(bean.getParentOrgId()).getOrgName();
        }
        model.addAttribute("bean", bean);
        model.addAttribute("searchModel",searchModel);
        model.addAttribute("parentOrgName",parentOrgName);
        return "sysmanage/sysorg/orgmanage-add";
    }
    @RequestMapping("/saveAddSysOrg.ajax")
    @ResponseBody
    public JSONObject saveAddSysOrg(@RequestBody SysOrgBean bean, String province, String city, String county, String town, String village, HttpServletRequest request) {
        OrgManageSearchModel searchModel = new OrgManageSearchModel();
        searchModel.setProvinceCode(province);
        searchModel.setCityCode(city);
        searchModel.setDistrictCode(county);
        searchModel.setStreetCode(town);
        searchModel.setVillageCode(village);
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        String result = "0";
        String message = "修改成功!";
        JSONObject jsonObj = new JSONObject();
        String orgId = "";
        try {
            SysOrgBean orgBean = sysOrgSV.checkOrgBean(bean);
            if (orgBean != null) {
                message = "保存失败,【机构名称或代码重复!】";
                result = "-1";
            }else{
                orgId = sysOrgSV.insertOrgInfo(operatorCode, bean, searchModel);
            }
        } catch (Exception e) {
            logger.error("保存组织信息异常，异常信息：" + e.getMessage());
            result = "-1";
            message = e.getMessage();
        }
        //传新增结果到前台
        jsonObj.put("id", orgId);
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        jsonObj.put("name", bean.getOrgName());
        return  jsonObj;
    }
    @RequestMapping("/checkDunsUID.ajax")
    @ResponseBody
    public JSONObject checkDunsUID(String duns,String type) {
        Boolean result = true;
        String message = "校验通过!";
        JSONObject jsonObj = new JSONObject();
        try {
            result = sysOrgSV.checkDunsUID(duns,type);
        } catch (Exception e) {
            logger.error("保存组织信息异常，异常信息：" + e.getMessage());
            result = false;
            message = e.getMessage();
        }
        //传新增结果到前台
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return  jsonObj;
    }
    @RequestMapping("/queryOrgSon.ajax")
    @ResponseBody
    public JSONObject queryOrgSon(String orgId) {
        Boolean result = true;
        String message = "校验通过!";
        int total = 0;
        JSONObject jsonObj = new JSONObject();
        try {
            total = sysOrgSV.getSubOrgCountByOrgId(orgId);
        } catch (Exception e) {
            logger.error("保存组织信息异常，异常信息：" + e.getMessage());
            result = false;
            message = e.getMessage();
        }
        //传新增结果到前台
        jsonObj.put("total", total);
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return  jsonObj;
    }
    @RequestMapping("/delOrgDetail.ajax")
    @ResponseBody
    public JSONObject delOrgDetail( HttpServletRequest request,String orgIdsToDel) {
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        Boolean result = true;
        String message = "删除成功!";
        JSONObject jsonObj = new JSONObject();
        try {
            sysOrgSV.deleteOrg(operatorCode, orgIdsToDel);
        } catch (Exception e) {
            logger.error("保存组织信息异常，异常信息：" + e.getMessage());
            result = false;
            message = e.getMessage();
        }
        //传新增结果到前台
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return  jsonObj;
    }
}
