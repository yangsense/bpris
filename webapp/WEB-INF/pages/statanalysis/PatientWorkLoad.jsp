<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>机构病人检查信息统计</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>

    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/statanalysis/patientWorkLoad.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">

    <script type="text/javascript" src="${ctx}/aris/statics/common/Highcharts-4.1.3/js/highcharts.js"></script>
    <link href="${ctx}/aris/statics/pages/css/charts.css" rel="stylesheet" type="text/css"/>

    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/selectTree.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
</head>
<body>
<div class="wrap100 quickrecharge">
    <!--导航start-->
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb" style="margin-top: 8px;">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="javascript:void(0)">统计分析</a>
            </li>
            <li class="active">机构病人检查信息统计</li>
        </ul>
    </div>
    <!--导航end-->
    <div class="q_r_list">

        <!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
            	<input type="hidden" id="excelOrgId" name="excelOrgId"/>
			    <input type="hidden" id="excelCityCode" name="excelCityCode"/>
			    <input type="hidden" id="excelCounty" name="excelCounty"/>
			    <input type="hidden" id="excelLocCode" name="excelLocCode"/>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                   
                      <tr>
                    	<td width="25%">城市：
                    		<!-- <select id="cityCode"  name="cityCode" class="inputW160" onchange="getCountySelect()">
                    			<option value="-1">请选择城市</option>
                            </select> -->
                            <select id="ccCode1" name="cityCode" class="inputW160" onchange="getOrgSelects()" style="display: none">
                                 <option value="-1">请选择区县</option>
                             </select>
                    	</td>
                    	<td width="25%">区县：
                    		 <select id="countyCode" name="countyCode" class="inputW160" onchange="getOrgSelects()">
                                 <option value="-1">请选择区县</option>
                             </select>
                    	</td>
                    	<td width="25%">机构：
                    		<select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                              <option value="-1">请选择机构</option>
                            </select>
                    	</td>
                    	<td width="25%">报表周期：
                    		<select id="time_type" name="time_type" class="inputW160" onchange="loadTimeType()">
                            	<option value="5">年报表</option>
                            	<option value="4">季度报表</option>
                            	<option value="3">月报表</option>
                            	<option value="2">日期区县报表</option>
                                <option value="1">日报表</option>
                            </select>
                    	</td>
                    </tr>
                    
                     <tr>
                     	<td class="r"><label id="timetype_title" title="日期："></label></td>
                        <%--报表--%>
                        <td width="13%" id="time_type1" style="display: none;" colspan="3">
                            <input id="dateOfDay" name="dateOfDay" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                        <%--日期区间--%>
                        <td width="30%" id="time_type2" style="display: none;" colspan="3">
                            <input id="startDate" name="startDate" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                            <input id="endDate" name="endDate" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                        <%--月--%>
                        <td width="30%" id="time_type3" style="display: none;" colspan="3">
                            <input id="monthDate" name="monthDate"  class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"/>
                        </td>
                        <%--季度--%>
                        <td width="30%" id="time_type4" style="display: none;" colspan="3">
                            <input id="yearDateOfJD" name="yearDateOfJD" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy'})"/>
                            <select id="jd" name="jd">
                                <option value="1">第一季度</option>
                                <option value="2">第二季度</option>
                                <option value="3">第三季度</option>
                                <option value="4">第四季度</option>
                            </select>
                        </td>
                        <%--年--%>
                        <td width="30%" id="time_type5" style="display: none;" colspan="3">
                            <input id="yearDate" name="yearDate" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy'})"/>
                        </td>
                     
                     </tr>
                    
                    
                    
                </table>
            </form>
            
            <div>
                   	<span>
                   		  <a href="javascript:void(0)" onclick="exportStudyInfo()" 
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-arrow-up"></span>导 出</a>
                        
                   	</span>
                   	<span style="float:right;">
                   		 	 <a href="javascript:void(0)" onclick="reloadGrid();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        <a href="javascript:void(0)" onclick="resetform();"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span
                                class="icon-retweet"></span>重 置</a>
                   	</span>
                </div>
            
        </div>
        <!--查询条件 end-->
        <div id="city_div" style="display: none">
            <label id="city_total_label"></label>
            <div  id="chart01"  style="width:70%;height:400px;"></div>
            <table id="citylist" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="city_pager"></div>
        </div>
        <div id="county_div" style="display: none">
            <a class="blue" href="javascript:void(0)" onclick="firstLevel()" id="citylast">返回上一级</a>&nbsp;&nbsp;&nbsp;&nbsp;<label id="county_total_label"></label>
            <table id="aiscserverlist" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="aiscserver_pager"></div>
        </div>
        <div id="loc_div"  style="display: none">
            <a class="blue" href="javascript:void(0)" onclick="secondLevel()">返回上一级</a>&nbsp;&nbsp;&nbsp;&nbsp;<label id="loc_total_label"></label>
            <table id="loclist" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="loc_pager"></div>
        </div>
        <div id="patient_div"  style="display: none">
            <a class="blue" href="javascript:void(0)" onclick="thirdLevel()">返回上一级</a>
            <table id="patientlist" width="100%" border="0" cellspacing="0" cellpadding="0">

            </table>
            <div id="patient_pager"></div>
        </div>
    </div>
    <input type="hidden" id="exportMark"/>
    
</div>

</body>
</html>
