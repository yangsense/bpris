package com.ai.aris.server.basecode.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ai.aris.server.basecode.service.interfaces.IAiscAreaInstitutionSV;
import com.ai.aris.server.common.bean.CommonEngine;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.aris.server.sysmanage.bean.SysOrgEngine;
import com.ai.aris.server.sysmanage.model.SysOrgModel;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import static org.apache.commons.lang.StringUtils.isNotBlank;

public class AiscAreaInstitutionSVImpl implements IAiscAreaInstitutionSV {

    @Override
    public ResultDTO queryPageList(SysOrgModel model, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(!"-1".equals(model.getCityCode())) {
            condition.append(" and CITY_CODE = '"+model.getCityCode()+"' ");
        }
        if(isNotBlank(model.getDistrictCode())) {
            condition.append(" and district_Code = '"+model.getDistrictCode()+"' ");
        }
        if(!"-1".equals(model.getOrgId())&&isNotBlank(model.getOrgId())) {
            condition.append(" and ORG_ID = '"+model.getOrgId()+"' ");
        }
        condition.append(" AND ORG_CLASS_LEVEL IN (2,3) ");
        condition.append(" or org_type_second in ('C2')   ");
        condition.append(" AND DUNS IS NOT NULL ");
        condition.append(" AND CITY_CODE = (select item_no from  sys_dictitem where  dict_name='GB/T2260' and item_level=2)  ");
        int total = SysOrgEngine.getBeansCount(condition.toString(), null);
        SysOrgBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY ORG_ID desc");
            beans = SysOrgEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false);
        }
        Map<String, DictTranslator> dicts = new HashMap<String, DictTranslator>();
        dicts.put("cityCode", new DictTranslator("cityCode","GB/T2260","cityDesc"));
        dicts.put("districtCode", new DictTranslator("districtCode","GB/T2260","districtDesc"));
        resultDTO.setRows(BeanUtils.beanToList(beans, SysOrgModel.class, dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    /**
     * 批量导入机构区域相关信息
     * @throws Exception
     */
//    public void importAreaToOrg() throws Exception{
//    	String sql1 = "delete from AISC_AREAINSTITUTION";
//    	CommonEngine.execute(sql1,null);
//    	
//        String sql = " INSERT INTO AISC_AREAINSTITUTION T\n" +
//                "    SELECT A.ORG_ID,   --机构ID\n" +
//                "    A.ORG_ID,   --机构ID\n" +
//                "    A.ORG_NAME, --机构名称\n" +
//                "    A.CITY_CODE, --城市编码\n" +
//                "            (SELECT B.ITEM_NAME\n" +
//                "                    FROM SYS_DICTITEM B\n" +
//                "                    WHERE A.City_Code = B.ITEM_NO) AS CITY_NAME,  --城市名称\n" +
//                "    A.DISTRICT_CODE,                        --区县编码\n" +
//                "            (SELECT B.ITEM_NAME\n" +
//                "                    FROM SYS_DICTITEM B\n" +
//                "                    WHERE A.DISTRICT_CODE = B.ITEM_NO) AS DISTRICT_NAME,  --区县名称\n" +
//                "          0 AS HASPACS                           --是否上线\n" +
//                "    FROM SYS_ORG A\n" +
//                "    WHERE A.ORG_CLASS_LEVEL IN (2,3)\n" +
//                "    or org_type_second in ('C2') " +
//                "    AND DUNS IS NOT NULL\n" +
//                "    AND CITY_CODE = (select item_no from  sys_dictitem where  dict_name='GB/T2260' and item_level=2) \n" +
//                "    AND NOT EXISTS\n" +
//                "            (SELECT * FROM AISC_AREAINSTITUTION C WHERE C.CONORG_ID = A.ORG_ID)";
//        CommonEngine.execute(sql,null);
//    }

    /**
     * 修改是否上线状态
     * @param id
     * @param status
     * @throws Exception
     */
    public void updateHasPacs(long id, int status)throws Exception{
        if(status==0){
            status=1;
        }else {
            status=0;
        }
        String sql = "UPDATE sys_org  SET HASPACS = "+status+" WHERE ORG_ID = "+id;
        CommonEngine.execute(sql,null);
    }
}







