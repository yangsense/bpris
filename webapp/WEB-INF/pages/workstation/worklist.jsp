<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>首页</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" > 
     <link href="${ctx}/aris/statics/pages/css/index/css/manage.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
   
    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
         
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="aris/statics/pages/js/workstation/worklist.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <style type="text/css">
<%--      .ui-jqgrid-btable .ui-widget-content.ui-state-highlight {--%>
<%--       background-color: #c4efc9--%>
<%--      }--%>
    </style>
        
</head>
<script>
function resize() {
var width = $(document).width() ; //获取浏览器宽度
$("table").width(width) ; //设置table宽度
}
</script>
<body>
<div class="wrap100 quickrecharge">	
	<div class="q_r_list">
	<!--查询条件 begin-->
        <div class="listTable">
            <input type="hidden" id="currentLocId" value="${cookie_locId }"/>
            <input type="hidden" id="AUDIT_REPORT_BUTTON" value="${AUDIT_REPORT_BUTTON }"/>
            <input type="hidden" id="pacsViewPath" value="${pacsViewPath }"/> 
            <input type="hidden" id="diagnoseRole" name="diagnoseRole" value="${DIAGNOSE_ROLE }"/> 
            <input type="hidden" id="consultRole" name="consultRole" value="${CONSULT_ROLE }"/>
            <input type="hidden" id="hzsqRole" name="hzsqRole" value="${HZSQ_ROLE }"/>  
            <input type="hidden" id="locType" name="locType" value="${locType }"/>  
            <input type="hidden" id="messageFlag" name="messageFlag" /> 
            <input type="hidden" id="userId" name="userId" value="${userId }"/> 
            <input type="hidden" id="careId" name="careId" value="${careId }"/> 
            <input type="hidden" id="oldstarttime" name="oldstarttime" value="${oldstarttime}"/> 
            <input type="hidden" id="oldendtime" name="oldendtime" value="${oldendtime}"/> 
            <input type="hidden" id="oldstudytype" name="oldstudytype" value="${oldstudytype}"/> 
            <input type="hidden" id="oldstudystutascode" name="oldstudystutascode" value="${oldstudystutascode}"/> 
            <input type="hidden" id="oldstudyAccnum" name="oldstudyAccnum" value="${oldstudyAccnum}"/> 
            <input type="hidden" id="oldqType" name="oldqType" value="${oldqType}"/> 
            <input type="hidden" id="oldModalityId" name="oldModalityId" value="${oldModalityId}"/> 
            <input type="hidden" id="oldCheckTimeMark" name="oldCheckTimeMark" value="${oldCheckTimeMark}"/> 
            <input type="hidden" id="oldqValue" name="oldqValue" value="${oldqValue}"/> 
            <input type="hidden" id="hzisFlag" name="hzisFlag" value="${HZ_IS_FLAG}"/>  
            <input type="hidden" id="YPatientIdFlag" name="YPatientIdFlag" value="${PATIENTID_FLAG}"/> 
             
            <form id="searchForm" action="#">
                <table  border="0" cellspacing="0" cellpadding="0" style="width: 100%;" id="toptable">
                    <tr>
                        <td>
                        	检查科室 : 
                        	<select id="locInfo" name="locId"   multiply="true" onchange="setLocCookie()">                               
                            </select>
                        </td>
                        <td>
                        	<select id="qType" name="qType" style="width:80px;" >
                           		<option value="1">病人ID</option>
                                <option value="2">一卡通ID</option>
                                <option value="3">病人姓名</option>
                                <option value="7">医院病人ID</option>
                            </select> : 
                            <input name="qValue" id="qValue" class="inputW120"/>
                        </td>
                        <td>
                        	检查编号 : <input id="studyAccnumber" name="studyAccnumber" class="inputW120"/>
                        </td>
                        <td>
                        	检查方式 : 
                        	<select id="studyType" name="studyType"   multiply="true" class="inputW120">
                                <option value="-1">请选择</option>
                                <option value="0">检查医嘱</option> 
                                <option value="1">远程诊断</option>
                            </select> 
                        </td>
                    </tr>
                    
                    <tr>
                    	<td width="2%">
                        	设备类型 : 
                        	<select id="modalityId" name="modalityId" class="inputW120">
                                 <option value="-1">全部</option>         
                            </select>
                        </td>
                        <td width="2%">
                        	检查状态 : 
                        	<select id="studyStatus" name="studystatusCode" style="width:120px;" class="js-example-basic-multiple" multiple="multiple">
                                <option value="-1">请选择状态</option>
                            </select>
                        </td>
                        <td width="5%">
                        	<input type="checkbox" id="isDate" value="1" checked = "checked">
                        	检查日期 : 
                        	<input id="startTime" name="startTime" class="inputW120" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                             --
                            <input id="endTime" name="endTime" class="inputW120" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        </td>
                        <td width="3%">
                        	<a href="javascript:void(0)" onclick="reloadGrid(0)" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success" style="margin-top: -6px;"><span class="icon-search"></span>查询</a>
                       		<a href="javascript:void(0)" onclick="reset()" class="fm-button ui-state-default ui-corner-all fm-button-icon-left ui-search btn btn-sm btn-gray" style="margin-top: -5px;"><span class="icon-retweet"></span>重 置</a>
                        </td>
                    </tr>
                </table>
            </form>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                	<!-- <th style="text-align: left;font-size: 16px;">总数<a href="javascript:void(0)" id="total"></a> 已审核<a href="javascript:void(0)" id="verifyNum"></a> 已录入<a href="javascript:void(0)" id="haverptNum"></a> 已登记<a href="javascript:void(0)" id="arriveNum"></a> 会诊申请<a href="javascript:void(0)" id="hzNum"></a> 已打印<a href="javascript:void(0)" id="printNum"></a></th> -->
                    <th class="r n_b_b">
                    
						<c:if test="${STUDY_REG == 'y'}">
                       		<a href="javascript:void(0)" onclick="patientReg()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>登记</a>
                        </c:if>
                    	<c:if test="${CANCELREG == 'y'}">
                    		<a href="javascript:void(0)" onclick="patientRegCancel()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>取消登记</a>
                        </c:if>
                    	<c:if test="${HZSQ_ROLE == 'y'}">
                    		<a href="javascript:void(0)" onclick="consultPatientReg()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>会诊申请</a>
                    		<!-- <a href="javascript:void(0)" onclick="imageData(1,'')" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>影像资料</a> -->
                        </c:if>
                        <c:if test="${CONSULT_ROLE == 'y'}">
                       		<a href="javascript:void(0)" onclick="consultReport()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>会诊开始</a>
                        </c:if>
                        <c:if test="${WRITING_REPORT_BUTTON == 'y'}">
                       		<a href="javascript:void(0)" onclick="writeReprot(1)" id="writeReport" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>书写报告</a>
                        </c:if>
                        <c:if test="${AUDIT_REPORT_BUTTON == 'y'}">
                         	<a href="javascript:void(0)" onclick="writeReprot(2)" id="auditReport" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>审核报告</a>
                        </c:if>
                        <c:if test="${QUERY_CHECK == 'y'}">
                       		<a href="javascript:void(0)" onclick="showGrid()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>查看检查信息</a>
                        </c:if>
                    	<c:if test="${READ_CARD == 'y'}">
                       		<a href="javascript:void(0)" onclick="readCard()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>读卡</a>
                        </c:if>
	                    <c:if test="${VIEW_IMAGE == 'y'}">
	                       <a href="javascript:void(0)" onclick="pacsView(1,'')" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>浏览影像</a>
	                    </c:if>
	                    <c:if test="${VIEW_IMAGE == 'y'}">
	                       <a href="javascript:void(0)" onclick="lookAllView(1)" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>所有影像</a>
	                    </c:if>
	                    
	                   
	                    <c:if test="${VIDEO_CONSULTATION == 'y'}">
	                   		<a href="javascript:void(0)" onclick="videoConsultation()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>视频会诊</a>
	                    </c:if>
	                    <c:if test="${IMAGE_QC == 'y'}">
	                 		<a href="javascript:void(0)" onclick="imageQryControl()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>影像质控</a>
	                    </c:if>
                    </th>
                </tr>
            </table>
        </div>
        <!--查询条件 end-->
			<div>
				<div id="fudong"  style="margin-left:10%;margin-right:10%;background-color:#F8F8FF;z-index:21;word-break:break-all;position:absolute;border:1px solid silver;height: 100px;font-size: 16px;overflow:auto; display: none;" ><b>检查所见：</b><span style="font-size: 16px;" id="spansj"></span><br /><b>诊断意见：</b><span style="font-size: 16px;" id="spanyj"></span></div> 
            	<table id="worklist" width="100%" border="0" cellspacing="0" cellpadding="0">
               
            	</table>
            	<div id="worklist_pager"></div>
        	</div>
        	
        </div>
        
        
        <!--提示start-->
<div class="notice-float" id="notice-tip"  style="display:none">
	
    <div class="floatTitleBg orange222">
    	<h2 class="fl" style="margin-top:7px;"><i></i>提示</h2>
        <div class="fr closemessage"><a href="javascript:void(0);" onclick="closeTip();">X</a></div>
    </div>
    
    <div class="floatContent" id="floatContent">
    
        <h5>
        	<img src="${ctx}/aris/statics/pages/css/index/images/prompt-icon.png" alt="提示" width="50"  onclick=""/> <br />
        <a href="javascript:void(0);" onclick="" id="cNum" style="color:#f60;">您有<span class="ret_span">3</span> 条会诊申请，请及时处理!</a> 
        <br/>
        <a href="javascript:void(0);" onclick="" id="dNum" style="color:#f60;">您有<span class="ret_span">1</span> 条诊断申请，请及时处理!</a></h5>
        
        <div class="cl"></div>
              
 	</div>   
    
</div>
<!--提示 end-->
</div> 



</body>
</html>
