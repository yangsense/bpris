<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>书写报告</title>

    <script type="text/javascript" src="${ctx}/aris/statics/common/js/main.js"></script>    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
	<link href="${ctx}/aris/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link href="${ctx}/aris/statics/pages/css/index/css/detail.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/inner.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
         
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/studyReport.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>		
	<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
		
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/config.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery.jqprint-0.3.js"></script>
    <style type="text/css">
     .table td{
     word-wrap:break-word;
     word-break:break-all; 
     }
     .row{
      margin-left:-5px;
     }
     .disabled{
	  color:gray
	 }
<%--     --%>
<%--     body {--%>
<%--	    margin:0;--%>
<%--	    padding:40px;--%>
<%--	    background:#fff;--%>
<%--	    font:80% Arial, Helvetica, sans-serif;--%>
<%--	    color:#555;--%>
<%--	    line-height:180%;--%>
<%--	}--%>
<%--	--%>
<%--	h1{--%>
<%--	    font-size:180%;--%>
<%--	    font-weight:normal;--%>
<%--	    color:#555;--%>
<%--	}--%>
<%--	h2{--%>
<%--	    clear:both;--%>
<%--	    font-size:160%;--%>
<%--	    font-weight:normal;--%>
<%--	    color:#555;--%>
<%--	    margin:0;--%>
<%--	    padding:.5em 0;--%>
<%--	}--%>
<%--	a{--%>
<%--	    text-decoration:none;--%>
<%--	    color:#f30;    --%>
<%--	}--%>
<%--	p{--%>
<%--	    clear:both;--%>
<%--	    margin:0;--%>
<%--	    padding:.5em 0;--%>
<%--	}--%>
	pre{
	    display:block;
	    font:100% "Courier New", Courier, monospace;
	    padding:10px;
	    border:1px solid #bae2f0;
	    background:#e3f4f9;    
	    margin:.5em 0;
	    overflow:auto;
	    width:800px;
	}
	
<%--	img{border:none;}--%>
<%--	ul,li{--%>
<%--	    margin:0;--%>
<%--	    padding:0;--%>
<%--	}--%>
<%--	li{--%>
<%--	    list-style:none;--%>
<%--	    float:left;--%>
<%--	    display:inline;--%>
<%--	    margin-right:10px;--%>
<%--	}--%>
	
	.tooltipa {
    position: relative;
    display: inline-block;
    border-bottom: 1px dotted black; /* 悬停元素上显示点线 */
}
 

.tooltipa .tooltiptexta {
    visibility: hidden;
    width: 100%;
    background-color: #C1FFC1;
    color: black;
    text-align: center;
    padding: 5px 0;
    border-radius: 6px;
    position: absolute;
    z-index: 1;
}
.tooltipa:hover .tooltiptexta {
    visibility: visible;
}
	
	
<%--
	preview{
	    position:absolute;
	    border:1px solid #ccc;
	    background:#333;
	    padding:5px;
	    display:none;
	    color:#fff;
	    }--%>
 
    </style>
</head>
<body>

<input type="hidden" id="AUDIT_REPORT_BUTTON" value="${AUDIT_REPORT_BUTTON}"/>
<input type="hidden" id="FINAL_AUDIT_REPORT" value="${FINAL_AUDIT_BUTTON}"/>
<input type="hidden" id="DIAGNOSE_ROLE" value="${DIAGNOSE_ROLE}"/>
<input type="hidden" id="CONSULT_ROLE" value="${CONSULT_ROLE}"/>
<input type="hidden" id="studystatusCode" value="${regInfoBean.studystatusCode }"/>
<input type="hidden" id="existingStudyConsultorg" value="${regInfoBean.studyConsultorg }"/>
<input type="hidden" id="existingStudyConsultloc" value="${regInfoBean.studyConsultloc }"/>
<input type="hidden" id="patientGlobalid" value="${regInfoBean.patientGlobalid }"/>
<input type="hidden" id="studyAccnumber" value="${regInfoBean.studyAccnumber }"/>
<input type="hidden" id="patientId" value="${regInfoBean.patientId}"/>
<input type="hidden" id="patientName" value="${regInfoBean.patientName}"/>
<input type="hidden" id="itemBodyInfo" value="${itemBodyInfo }"/>
<input type="hidden" id="isReadonly" value="${isReadonly }"/> 
<input type="hidden" id="reportDocId" value="${reportDocId }"/>
<input type="hidden" id="reportVerifydocId" value="${reportVerifydocId }"/>
<input type="hidden" id="reportFinalDocId" value="${reportFinalDocId}"/>  
<input type="hidden" id="careId" value="${careId }"/> 
<input type="hidden" id="patientIds" value="${patientIds }"/>
<input type="hidden" id="ispositive" value="${ispositive }"/> 
<input type="hidden" id="modalityId" value="${modalityId }"/>
<input type="hidden" id="studyTypeMy" value="${regInfoBean.studyType}"/>
<div class="thickdiv" style="display: none; height: 100%;"></div> 
<div class="wrap100 quickrecharge " id="bodyContent">
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
                <a onclick="javaScript:goBack();" id="workbtnback">工作列表</a>
            </li>
            <li class="active">书写报告</li> 
        </ul>
    </div>
	<!--导航end-->
	 
        <form class="form-horizontal" role="form" id="reportForm">
        <!-- 审核成功状态 -->
        <input type="hidden" id="auditStatus"/> 
        <!-- 报告单状态 -->
        <input type="hidden" id="reportIscompleted" value="${reportBean.reportIscompleted }"/> 
        <input type="hidden" id="rtype" value="${rtype }"/> 
        <input type="hidden" id="templatecontentId"/>
        <input type="hidden" id="reportId" value="${reportBean.reportId }"/>
        <input type="hidden" id="studyinfoId" name="studyinfoId" value="${studyinfoId }"/>
        <input type="hidden" id="patientInpatientId" value="${regInfoBean.patientInpatientid}"/>
        <input type="hidden" id="pacsViewPath" value="${pacsViewPath }"/>
        <input type="hidden" id="locId" value="${locId }"/>
        <input type="hidden" id="patientIdnumber" value="${regInfoBean.patientIdnumber }"/>
        <input type="hidden" id="patienttypeCode" value="${regInfoBean.patienttypeCode }"/>
        <input type="hidden" id="healthIp" value="${healthIp }"/>
        <input type="hidden" id="ON_OFF" value="${ON_OFF }"/>
        <div class="r_list" style="margin-left:24px;margin-right:12px;"> 
            
            <div class="row report m-t-20">

                <div class="col-lg-3 report-left ">
                    <ul class="nav nav-tabs">
                        <li name="tag" role="presentation" class="active" onclick="changeLabel(this,1)">
                        	<a href="javaScript:void(0);" flag="1"> 公共模板 </a>
                        </li>
                        <li name="tag" role="presentation" onclick="changeLabel(this,2)">
                        	<a href="javaScript:void(0);" flag="2"> 私有模板 </a>
                        </li>
                        <li name="tag" role="presentation" onclick="changeLabel(this,3)">
                        	<a href="javaScript:void(0);" > 科室短语 </a>
                        </li>
                        <li name="tag" role="presentation" onclick="changeLabel(this,4)">
                        	<a href="javaScript:void(0);" > 相关检查 </a>
                        </li>
                        <li name="tag" role="presentation" onclick="changeLabel(this,6)">
                       	<a href="javaScript:void(0);" > 影像浏览 </a>
                       </li>
						<li name="tag" role="presentation" onclick="changeLabel(this,7)">
							<a href="javaScript:void(0);" > 病历夹 </a>
						</li>
                    </ul>
                    <!-- 左侧树列表 -->
					<div id="templateTree" region="west" split="true" title="&nbsp;" maximizable="false"
					     class="ztree layout-body panel-body" style="border:1px solid #ddd; padding:5px;height: 436px;"> 		
					                
                    </div>
                    <div id="fudong"  style="background-color:#F5FFFA;z-index:21;word-break:break-all;position:absolute;display:none;border:1px solid silver;width: 300px;height: 250px;font-size: 16px;overflow:hidden;" ><b>检查所见：</b><span style="font-size: 14px;" id="spansj"></span><br /><b>诊断意见：</b><span style="font-size: 14px;" id="spanyj"></span></div>
                    <!-- 科室短语 -->
                    <div id="phrase" region="west" 
					     class="leftContent" style="display:none;border:1px solid #ddd;"> 						    					     			     				                   
                    </div>                    
                    <input id="dinput" style="display:none;width:260px;margin:5px 5px 5px 5px"/>
                     
                    <div class="btn-group" role="group" aria-label="..." id="dbutton" style="display:none;margin-left:20px;">
					  <button type="button" class="btn btn-primary" onclick="upPhrase('add')">增加</button>
					  <button type="button" class="btn btn-primary" onclick="upPhrase('del')">删除</button>
					  <button type="button" class="btn btn-primary" onclick="upPhrase('update')">修改</button>
					</div>
                    <!-- 相关检查 -->
                    <div id="relCheck"  class="leftContent" style="display:none;border:1px solid #ddd;"> 
                       <div class="panel panel-default"> 
						  <!-- Table -->
						  <table class="table" id="checkTb">
						     <tr id="checkTr">  
						      <th style="width:20%;">检查号</th>
		                      <th style="width:20%;">项目名称</th>
		                      <th style="width:30%;">检查时间</th>
		                      <th>机构名称</th> 
		                     </tr>		                     
						  </table>
					   </div> 					     			     				                   
                    </div> 
                    <!-- 影像浏览 -->
                    <div id="imageView"  class="leftContent" style="display:none;border:1px solid #ddd;"> 
                        <div class="row" id="imgrow">
                        	  	
							        
						</div> 					     			     				                   
                    </div> 
                    	
                    <div class="row text-center" style="margin-top:20px;">
                        <div class="">
                        	<input type="checkbox" name="templateRadio" value="0" checked style="width:8%" >追加</input>
	                        <input type="radio" name="reportIspositive" value="0" checked style="width:10%" > 阳性</input>
	                        <input type="radio" name="reportIspositive" value="1"  style="width:10%"> 阴性</input>
	                        <input type="radio" name="reportIspositive" value="2" style="width:10%"> 未知 </input>
                        </div>
                    </div>                   
	                    
                    <div >
                        <h6>临床诊断</h6>
                        <textarea rows="5" cols="5" class="w-b-100" style="border:solid 1px #999; border-radius:20px; resize:none;">${regInfoBean.studyClinic }</textarea>
                    </div>
                    <c:if test="${DIAGNOSE_ROLE==true}">
	                    <div class="form-group " style="margin-top:5px;">
		                        <label class="col-xs-2 control-label no-padding-right"> 会诊机构： </label>
		                        <div class="col-xs-3">
		                           <select id="studyConsultorg" name="studyConsultorg" style="width:160px;" onchange="setLocItem(this)">
		                                <option value="-1">请选择机构</option>
		                            </select> 
		                        </div> 
		                 </div> 
		                 <div class="form-group "> 
		                        <label class="col-xs-2 control-label no-padding-right"> 会诊科室： </label>
		                        <div class="col-xs-3">
		                        	<select id="studyConsultloc" name="studyConsultloc" style="width:160px;" >
			                       		<option value='-1'>请选择</option>    
		                       		</select>
		                        </div> 
		                        <label class="col-xs-2 control-label no-padding-right"></label>
		                        <div class="col-xs-3">
		                            
		                        </div> 
		                 </div> 
                     </c:if>
                </div>
                
                <div class="col-lg-9 report-right">

                    <div class="fM_listTable3">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>			                    
			                    <th colspan="" class="l n_b_b"> 
                                   <!--<c:if test="${CONSULT_ROLE == true && regInfoBean.studyType==1&& (regInfoBean.studystatusCode=='AppVerify'||regInfoBean.studystatusCode=='STUDYED')}">  
	                                   <a href="javascript:void(0)" id="consultStartBtn"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success">
		                                      	<span class="icon-save"></span>会诊开始
		                               </a>	
	                               </c:if>-->                               
                                   <c:if test="${WRITING_REPORT_BUTTON  == true}"> 
	                                   <a href="javascript:void(0)" id="saveBtn"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success">
	                                      	<span class="icon-save"></span>保存
	                                   </a>
                                   </c:if>
                                   <c:if test="${AUDIT_REPORT_BUTTON == true}">
				                       <a href="javascript:void(0)" id="checkBtn" style="display: none;" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                          	<span class=""></span>审核
				                       </a>
			                       </c:if>
			                       <c:if test="${FINAL_AUDIT_BUTTON == true}">
				                       <a href="javascript:void(0)" id="finalCheckBtn" style="display: none;"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                          	<span class=""></span>二次审核
				                       </a>
			                       </c:if>
			                       <c:if test="${CONSULT_ROLE == true&&regInfoBean.studyType==1}">
				                       <a href="javascript:void(0)" id="infoView"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>资料浏览
				                       </a>
				                   </c:if>
			                       <c:if test="${VIEW_IMAGE == true}">
				                       <a href="javascript:void(0)" id="pacsViewBtn"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>调阅影像
				                       </a>
				                   </c:if>
				                   <c:if test="${VIEW_IMAGE == true}">
				                       <a href="javascript:void(0)" id="pacsViewAll"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>所有影像
				                       </a>
				                   </c:if>
				                   <c:if test="${PRINT_VIEW == true}">
				                       <a href="javascript:void(0)" id="printBtn"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>打印
				                       </a>
			                       </c:if>
			                       <c:if test="${STRAIGHT_PRINT == true}">
				                       <a href="javascript:void(0)" id="directPrint"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>直接打印
				                       </a>
			                       </c:if>
			                       <c:if test="${VIEW_APPLY == true}"> 
			                       <a href="javascript:void(0)" id="viewAppBtn"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
			                       		<span class=""></span>调阅申请单
			                       </a>	
			                       </c:if>		                       
			                       <c:if test="${PUBLIC_TEMPLATE_MANAGE == true}"> 
				                       <a href="javascript:void(0)" id="templateBtn" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>公共模板维护
				                       </a> 
									   <a href="javascript:void(0)" id="templateBtnImport" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>公共模板导入
				                       </a>
			                       </c:if>
			                       <c:if test="${PRIVATE_TEMPLATE_MANAGE == true}"> 
				                       <a href="javascript:void(0)" id="private_templateBtn" style="display:none;" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
					                       		<span class=""></span>私有模板维护
					                   </a> 
				                       <a href="javascript:void(0)" id="private_templateBtnImport" style="display:none;" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
					                       		<span class=""></span>私有模板导入
					                   </a>
				                   </c:if> 
				                   <c:if test="${GET_HEALTH == true}"> 
					                   <a href="javascript:void(0)" id="getHealthBtn" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
					                       		<span class=""></span>健康档案调阅
					                   </a> 
					               </c:if>
					               <c:if test="${VIEW_IMGPRINT == true}"> 
					               <a href="javascript:void(0)" id="imgprint_btn"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>影像打印
				                   </a>
				                   </c:if>
									<a href="javascript:void(0)" id="pre_edit"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
										<span class=""></span>上次编辑内容
									</a>
									<a href="javascript:void(0)" id="reportStandUp"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
										<span class=""></span>挂起
									</a>
									<a href="javascript:void(0)" id="reportContrast"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
										<span class=""></span>报告对比
									</a>
									<a href="javascript:void(0)" id="collectionCaseBtn" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
										<span class=""></span>收藏病例
									</a>
									<a href="javascript:void(0)" id="bingliUpdate" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
					                       		<span class=""></span>病例修改
					                </a>
									<c:if test="${FILE_UPLOAD == true}">
									<a href="javascript:void(0)" id="fileUpload" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
										<span class=""></span>文件上传
									</a>
									</c:if>
									<c:if test="${AI_MANAGE == true}">
									<a href="javascript:void(0)" id="aiBtn" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
										<span class=""></span>AI结果
									</a>
									</c:if>
				                   <a href="javaScript:goBack();" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>返回
				                   </a>                     
			                    </th>   
                                
                            </tr>
                        </table>
                    </div>

                    <div class="report-right-content">
                     
                        <div class="tooltipa"><div style="overflow: auto;"><h6><span style="text-align:center;" > 
                        	 ID：<a href="javascript:void(0)" onclick="patientReg()">${regInfoBean.patientId}</a>  &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.patientName }  &nbsp;&nbsp;&nbsp; 
                             ${regInfoBean.patientSex }   &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.patientAge}    &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.applocname}    &nbsp;&nbsp;&nbsp;
                             <span id="tips">住院号：</span>${regInfoBean.patientInpatientid}	  &nbsp;&nbsp;&nbsp;
                                                   床位：${regInfoBean.studyBedno}    &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.studydesc}	  &nbsp;&nbsp;&nbsp;  
                             <span id="bodyitem"></span>	  &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.orgName}       &nbsp;&nbsp;&nbsp;
                             </span></h6></div>
                             
                             <span style="text-align:center;font-size: 16px;" class="tooltiptexta"> 
                        	 ID：${regInfoBean.patientId}  &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.patientName }  &nbsp;&nbsp;&nbsp; 
                             ${regInfoBean.patientSex }   &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.patientAge}    &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.applocname}    &nbsp;&nbsp;&nbsp;
                             <span id="tips1">住院号：</span>${regInfoBean.patientInpatientid}	  &nbsp;&nbsp;&nbsp;
                                                   床位：${regInfoBean.studyBedno}    &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.studydesc}	  &nbsp;&nbsp;&nbsp;  
                             <span id="bodyitem1"></span>	  &nbsp;&nbsp;&nbsp;
                             ${regInfoBean.orgName}       &nbsp;&nbsp;&nbsp;
                             </span></div>
                         
                        <h6>检查所见</h6> 
                        <div class="report-show" id="editorExam">
                             <textarea id="reportExam" cols="5" rows="18" isRich="true" name="reportExam" class="ckeditor">
                             </textarea> 
                        </div>
                        <h6>诊断意见</h6> 
                        <div class="report-show" id="editorResult">
                            <textarea id="reportResult" cols="5" rows="5" isRich="true" name="reportResult" class="ckeditor"> 
                            </textarea>
                        </div>
                        <h6>备注</h6> 
                        <div class="report-show" id="editorRemark">
                            <textarea id="reportRemark" cols="50" rows="3" name="reportRemark" style="font-family: Arial, Verdana, sans-serif;font-size: 24px;color: #222;">${oldreportRemark}</textarea>
                        </div>
                        
                        <!-- <div class="fM_listTable3">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>			                    
			                    <th colspan="" class="l n_b_b"> 
                                   <c:if test="${CONSULT_ROLE == true && regInfoBean.studyType==2}">  
	                                   <a href="javascript:void(0)" id="consultStartBtn1"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success">
		                                      	<span class="icon-save"></span>会诊开始
		                               </a>	
	                               </c:if>                               
                                   <c:if test="${AUDIT_REPORT_BUTTON == true}"> 
	                                   <a href="javascript:void(0)" id="saveBtn1"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success">
	                                      	<span class="icon-save"></span>保存
	                                   </a>
                                   </c:if>
                                   <c:if test="${WRITING_REPORT_BUTTON == true}">
				                       <a href="javascript:void(0)" id="checkBtn1"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                          	<span class=""></span>审核
				                       </a>
			                       </c:if>
			                       <c:if test="${VIEW_IMAGE == true}"> 
				                       <a href="javascript:void(0)" id="pacsViewBtn1"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>调阅影像
				                       </a>
				                   </c:if>
			                       <a href="javascript:void(0)" id="printBtn1"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
			                       		<span class=""></span>打印报告
			                       </a>
			                       <a href="javascript:void(0)" id="directPrint1"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
			                       		<span class=""></span>直接打印
			                       </a>
			                       <c:if test="${VIEW_APPLY == true}"> 
			                       <a href="javascript:void(0)" id="viewAppBtn1"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
			                       		<span class=""></span>调阅申请单
			                       </a>	
			                       </c:if>		                       
			                       <c:if test="${PUBLIC_TEMPLATE_MANAGE == true}"> 
				                       <a href="javascript:void(0)" id="templateBtn1" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>公共模板维护
				                       </a> 
									   <a href="javascript:void(0)" id="templateBtnImport1" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>公共模板导入
				                       </a>
			                       </c:if>
			                       <a href="javascript:void(0)" id="private_templateBtn" style="display:none;" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>私有模板维护
				                   </a> 
			                       <a href="javascript:void(0)" id="private_templateBtnImport" style="display:none;" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>私有模板导入
				                   </a> 
				                   <c:if test="${GET_HEALTH == true}"> 
					                   <a href="javascript:void(0)" id="getHealthBtn1" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
					                       		<span class=""></span>健康档案调阅
					                   </a> 
					               </c:if>
					               <c:if test="${VIEW_IMGPRINT == true}"> 
					               <a href="javascript:void(0)" id="imgprint_btn"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>影像打印
				                   </a>
				                   </c:if>
				                   <a href="javaScript:goBack();" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
				                       		<span class=""></span>返回
				                   </a>                     
			                    </th>   
                                
                            </tr>
                        </table>
                    </div>-->
                    </div>
                </div>
            </div>
    <!--右侧内容 end-->
</div>
</form>
</div> 

</body>
</html>
