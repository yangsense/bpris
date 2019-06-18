<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>影像质控</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/imageControl.js"></script>
    
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
                <a href="javaScript:void(0);">工作列表</a>
            </li>
            <li class="active">影像质控</li>
        </ul>
    </div>
	<!--导航end-->
	
	<div class="q_r_list">
	<input type="hidden" id="currentLocId" value="${all_cookie_locId }"/>
	<!--查询条件 begin-->
        <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">                     
                    <tr>                                             
                        <th width="13%" class="r">科室名称：</th>
                        <td width="13%">
                            <select id="locId" name="locId" style="width:160px;"  multiply="true" onchange="reloadRisGrid()">  
                          		<option value="-1">--请选择--</option>                             
                         	</select> 
                        </td> 
                        <th width="13%" class="r">日期：</th>
                        <td width="13%">                            
                           <input id="studyStarttime" name="studyStarttime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});setPacsDate(this)"/>
                           <input id="receptiondate" name="receptiondate" type="hidden"/>
                        </td>    
                        <th class="r" width="13%">设备类型 ：</th>
                        <td width="13%">
                            <select id="modalitiesinstudy" name="modalitiesinstudy" style="width:160px;" multiply="true" onchange="reloadPacsGrid()">
                                <option value="-1">--请选择--</option>
                            </select> 
                        </td> 
                        <td  class="r"  >
							<a href="javascript:void(0)" onclick="reloadRisGrid();reloadPacsGrid();return false;" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span class="icon-search"></span>查询</a>
							<a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');" class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span class="icon-retweet"></span>重 置</a>
						</td>                 
                    </tr>
                </table>
            </form>
        <div>
        </div>
        <!--查询条件 end-->
      </div>
	       	<!-- 左侧ris信息start -->
			<div style="width:49%;float:left;margin-top:10px;">
				<div style="background-color: #71C2C2;">RIS信息</div>
	           	<table id="rislist" width="100%" border="0" cellspacing="0" cellpadding="0">
	              
	           	</table>
	           	<div id="rislist_pager"></div>
	       	</div>
	      	<!-- 右侧pacs信息start -->
	      	<div style="width:49%;float:right;margin-top:10px;">
	      		<div style="background-color: #71C2C2;">PACS信息</div>
	           	<table id="pacslist" width="100%" border="0" cellspacing="0" cellpadding="0">
	              
	           	</table>
	           	<div id="pacslist_pager"></div>
	       	</div>
	       	<!-- 右侧pacs信息end -->
	       	<div style="margin-top:10px;float:right;"><a href="javascript:void(0)" onclick="UpdateControlInfo()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>更	正</a></div>
       	</div>
</div>

</body>
</html>
