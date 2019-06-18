<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>工作列表</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/pages/js/common/common.js"></script>

    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/conorganizationcheck.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
     <style type="text/css">
      .ui-jqgrid-btable .ui-widget-content.ui-state-highlight {
       background-color: #c4efc9
      }
    </style>
    
</head>
<body>
<input type="hidden" id="currentLocId" value="${cookie_locId }"/>
<div class="wrap100 quickrecharge">
    <!--导航start-->
<%--	<div class="breadcrumbs" id="breadcrumbs">--%>
<%--        <script type="text/javascript">--%>
<%--            try {--%>
<%--                ace.settings.check('breadcrumbs', 'fixed')--%>
<%--            } catch (e) {--%>
<%--            }--%>
<%--        </script>--%>
<%----%>
<%--        <ul class="breadcrumb" style="margin-top: 8px;">--%>
<%--            <li>--%>
<%--                <i class="icon-home home-icon"></i>--%>
<%--                <a href="javaScript:void(0);">RIS工作站</a>--%>
<%--            </li>--%>
<%--            <li class="active">检查技师</li>--%>
<%--        </ul>--%>
<%--    </div>--%>
	<!--导航end-->
	
	<div class="q_r_list">
	
	<!--查询条件 begin-->
    <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>

						<input type="hidden" id="currentLocId" value="${cookie_locId }"/>
						<th class="r" width="10%">科室名称：</th>
						<td width="20%">
							<select id="locInfo" name="locId" style="width:160px;"   onchange="setLocCookie()" >
								<option value="-1">请选择</option>
							</select>
						</td>

                        <th class="r" width="10%">检查状态 ：</th>
                        <td colspan="3">
                            <select id="studyStatus" name="studystatusCode" style="width:160px;">
                                <option value="-1">请选择</option>
                            </select> 
                        </td>
                    </tr>
                    <tr>                         
                        <th  class="r">开始日期：</th>
                        <td width="25%">
                            <input id="startTime" name="startTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                            
                        </td> 
                        <th  class="r">结束日期：</th>
                        <td >
                           <input id="endTime" name="endTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                        <th></th>
						<td  class="r"  >
							<a href="javascript:void(0)" onclick="reloadGrid();return false;" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span class="icon-search"></span>查询</a>
							<a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');" class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray"><span class="icon-retweet"></span>重 置</a>
						</td>
					</tr>
                </table>
            </form>
           
        </div> 
        <!--查询条件 end-->
			<div>
            	<table id="worklist" width="100%" border="0" cellspacing="0" cellpadding="0">
               
            	</table>
            	<div id="worklist_pager"></div>
        	</div>
        	<c:if test="${VERIFYROLE == 'y'}">
        	<div class="listTable">
				<table style="height:58px;">
					<tr>
						<th width="12%" class="r" >会诊医生：</th>
						<td width="15%"><select id="mainDoc" name="mainDoc" style="width:160px;" > <option value="-1">请选择</option> </select></td>
						
						<th width="12%" class="r">会诊开始时间：</th>
						<td width="15%"><input id="startHzTime" name="startHzTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
					</tr>
				</table>
			</div>
			</c:if> 
			<input type="hidden" id="studyinfoId" name ="studyinfoId" value=""/>
			<input type="hidden" id="statusCode" name ="statusCode" value=""/>
			<input type="hidden" id="patientId" name ="patientId" value=""/>
			<input type="hidden" id="studyAccnumber" name ="studyAccnumber" value=""/>
			
			<div style="text-align:right;margin-top:10px;" class="r n_b_b"> 
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                    <th class="r n_b_b">&nbsp;</th>
	                    <td class="n_b_b">&nbsp;</td>
	                    <th class="r n_b_b">&nbsp;</th>
	                    <td colspan="3">
	                       <a href="javascript:void(0)" onclick="pdfView();" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>电子病例</a>
 	                       <a href="javascript:void(0)" onclick="pacsView();" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>影像资料</a>
 	                       <c:if test="${VERIFYROLE == 'y'}">
	                       <a href="javascript:void(0)" onclick="hzApply();" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>会诊审核</a>
	                       </c:if>
 	                       <a href="javascript:void(0)" onclick="refuseApply();" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>退回申请</a>
	                   </td>
	                </tr>
	            </table>
            
            
			</div>
        </div>
</div>

<!-- 书写报告 begin -->
 
<!-- 书写报告 end -->
 

</body>
</html>
