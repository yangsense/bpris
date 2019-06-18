<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>登记列表统计</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
         
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/statanalysis/worklistcount.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
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
                <a href="javaScript:void(0);">统计分析</a>
            </li>
            <li class="active">登记列表统计</li>
        </ul>
    </div>
	<!--导航end-->
	
	<div class="q_r_list">
	
	<!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">           
                    <!-- <tr>
                    	<th width="13%" class="r">科室名称：</th>
	                 	<td width="13%">
	                    <select id="locInfo" name="locId" style="width:160px;"  multiply="true" onchange="reloadGrid()">  
	                    </select> 
	                    </td>
	                    <th class="r" width="13%">设备名称 ：</th>
                        <td width="13%">
                            <select id="equipmentId" name="equipmentId" style="width:160px;">
                                <option value="-1">全部</option>
                            </select> 
                        </td> 
                        <th class="r" width="13%" style="padding: 0">申请科室 ：</th>
                        <td width="13%">
                            <select id="studyApplocid" name="studyApplocid" style="width:160px;" onchange="changeCareProv(this)">
                                <option value="-1">请选择</option>
                            </select> 
                        </td>
                        <th class="r" width="13%" style="padding: 0">申请医生 ：</th>
                        <td width="13%">
                            <select id="studyAppdoc" name="studyAppdoc" style="width:160px;">
                                <option value="-1">请选择医生</option>
                            </select> 
                        </td>                       
                    </tr>
                    <tr>
                    <th class="r" width="13%">病人类型 ：</th>
                    <td width="13%">
                        <select id="patienttypeCode" name="patienttypeCode" style="width:160px;">
                            <option value="-1">全部</option>
                        </select> 
                    </td>   
                    <th width="13%" class="r" style="padding: 0">开始日期：</th>
                    <td width="13%">
                        <input id="startTime" name="startTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        
                    </td> 
                    <th width="13%" class="r" style="padding: 0">结束日期：</th>
                    <td>                            
                       <input id="endTime" name="endTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                    </td>   
                    	<td width="18%" colspan="2" align="right">
		                    <a href="javascript:void(0)" onclick="reloadGrid()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span class="icon-search"></span>统计</a>
		                    <a href="javascript:void(0)" onclick="reset()" class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span class="icon-retweet"></span>重置</a>
		                    <a href="javascript:void(0)" onclick="exportWorklistCount()" 
                           		class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-arrow-up"></span>导 出</a>
                        </td>
                    </tr> -->
                    <tr>
						<td>
							科室名称：
							<select id="locInfo" name="locId" style="width:160px;"  multiply="true" onchange="reloadGrid()">  
	                    	</select>
						</td>
						<td>
							设备名称：
							<select id="equipmentId" name="equipmentId" style="width:160px;">
                                <option value="-1">全部</option>
                            </select>
						</td>
						<td>
							申请科室：
							<select id="studyApplocid" name="studyApplocid" style="width:160px;" onchange="changeCareProv(this)">
                                <option value="-1">请选择</option>
                            </select>
						</td>
						<td>
							申请医生：
							<select id="studyAppdoc" name="studyAppdoc" style="width:160px;">
                                <option value="-1">请选择医生</option>
                            </select>
						</td>
	                </tr>
					<tr>
						<td>
							病人类型：
							<select id="patienttypeCode" name="patienttypeCode" style="width:160px;">
                            	<option value="-1">全部</option>
                        	</select>
						</td>
						<td>
							开始日期：
							<input id="startTime" name="startTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</td>
						<td>
							结束日期：
							<input id="endTime" name="endTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</td>
					</tr>
                </table>
                <div>
                   	<span>
						<a href="javascript:void(0)" onclick="exportWorklistCount()" 
                           	class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span
                                class="icon-arrow-up"></span>导 出
                        </a>
                   	</span>
                   	
                   	<span style="float:right;">
						<a href="javascript:void(0)" onclick="reloadGrid()" 
							class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span 
								class="icon-search"></span>查询
						</a>
						<a href="javascript:void(0)" onclick="reset()" 
							class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span 
								class="icon-retweet"></span>重置
						</a>
                   	</span>
                </div>
            </form> 
        </div>
        <!--查询条件 end-->
        <div id="divTotalInfo" style="font-size: 16px"></div>

        <div>
            	<table id="caselist" width="100%" border="0" cellspacing="0" cellpadding="0">
               
            	</table>
            	<div id="caselist_pager"></div>
        	</div>
        </div>
</div>

<!-- 书写报告 begin -->
 
<!-- 书写报告 end -->
 

</body>
</html>
