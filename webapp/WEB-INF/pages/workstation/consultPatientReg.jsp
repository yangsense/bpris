<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>会诊申请</title>
    
   

    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
     <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
    
    <link href="${ctx}/aris/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link href="${ctx}/aris/statics/pages/css/index/css/detail.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/inner.css" rel="stylesheet" type="text/css"/>
         
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>   
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/PYjm.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/jquery.form.min.js"></script>   
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/jquery.validate.min.js"></script>   
    
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>  
     
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/chosen/chosen.jquery.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/consultPatientReg.js"></script>
    
    <!-- 盖掉bootstrap样式 -->
    <style type="text/css">
    label.help-block-new {
	    display: block;
	    margin-top: -30px;
	    margin-left: 180px;
	    margin-bottom: 10px;
	    color: #ff7777
    }    
    .main-content h1{ 
    background:#B9F1DE; 
    border-top-left-radius:10px; 
    border-top-right-radius:10px; 
    height:34px; 
    color:#09245F; 
    font-size:16px; 
    text-indent:10px; 
    line-height:34px;}
    
    /*库存数量详情 表单 begin*/
	.main-content .fM_listTable2{ margin:auto; background:#fff; width:100%; border-collapse: separate; border-left: 1px solid #eee; border-top: 1px solid #eee; overflow-x: auto; clear:both; font-size:14px;margin-bottom:20px; }
	.main-content .fM_listTable2 th{border-bottom: 1px solid #eee; border-right: 1px solid #eee;height: 18px;padding: 10px 10px;text-align: center; background:#f8f8f8; color:#777; font-weight:700; border-bottom: 1px solid #eee; }
	.main-content .fM_listTable2 td{border-bottom: 1px solid #eee; border-right: 1px solid #eee; line-height: 22px; padding: 10px 3px; /*text-align:center;*/ vertical-align: top; color:#444;}
	 
	.main-content .col-bt-right{text-align:right;margin-right:20px;}
	.col-xs-2{width:9%;}
	.col-xs-3{width:24%;}
	.row{margin-right:0px;margin-left:0px;}
    </style>
     
    
</head>
<body>

<div class="thickdiv" style="display: none; height: 100%;"></div>
<div class="main-content">
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
                <a href="javaScript:goBack();">工作列表</a>
            </li>
            <li class="active">会诊申请</li> 
        </ul>
    </div>
	<!--导航end-->
	 
    <input type="hidden" id="studyInfoId" name="studyinfoId" value="${studyinfoId }"/>    
    <form class="form-horizontal" id="patientRegForm">
    <input type="hidden" name="orgId" id="orgId"/>
    <input type="hidden" name="yuyueTime" id="yuyueTime"/>      
    <input type="hidden" name="locId" id="locId" value="${locId }"/>    
    <input type="hidden" id="eConsultorg" value="${studyConsultorg }"/>   
    <input type="hidden" id="eConsultloc" value="${studyConsultloc }"/>       
    <input type="hidden" name="oldStudyinfoId" value="${studyinfoId }"/>
    <!-- <input type="hidden" name="patientId" value="${patientId }"/>
    <input type="hidden" name="studyAccnumber" value="${studyAccnumber }"/> -->
    <input type="hidden" id="studystatusCode" name="studystatusCode" value="${studystutascode}"/>
    <input type="hidden" id="studyhaveimage" name="studyhaveimage" value="${studyhaveimage}"/>  
    <input type="hidden" id="isNew" name="isNew" value="${isNew }"/> 
    <input type="hidden" id="isHis" name="isHis"  /> 
    <input type="hidden" id="isConsult" name="isConsult" value="y" /> 
    
    
    <div class="page-content min_style" style="padding:0px 0px 0px;">
        <div class="row">
            <!-- <div class="form-group" id="hisSelectDiv">
                        <label class="col-xs-2 control-label no-padding-right"> 查询条件： </label>
                        <div class="col-xs-3">                            
                            <select id="queryKey" name="queryKey" style="width:160px;" >
                             <option value='-1'>请选择</option>
                             <c:forEach var="queryKey" items="${queryKey}" >
                               <option value='${queryKey.itemNo }' >${queryKey.itemName }</option>
                             </c:forEach>                             
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 查询值： </label>
                        <div class="col-xs-3">
                           <input name="queryValue" id="queryValue" class="inputW160" />
                           <button  id="add" type="button" onclick="getHisStudyInfo()">HIS获取</button> 
                        </div>
                        <label class="col-xs-2 control-label no-padding-right">   </label>
                        <div class="col-xs-3">
                                                
                        </div> 
                    </div> -->
            <h1>&nbsp;&nbsp;&nbsp;病人基本信息
<%--	             <div class="btn-group btn-group-xs col-bt-right" role="group" aria-label="Extra-small button group" --%>
<%--                      style='float: right;margin-top: 3px;' >                       --%>
<%--			        <button type="button" class="btn btn-primary" onclick='goBack()'>返回</button>--%>
<%--			     </div>--%>
            </h1>
              
            <div class="col-xs-12 col-sm-12 col-md-12">
                    
                
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 病人ID： </label>
                        <div class="col-xs-3">                            
                            <input type="hidden" name="patientGlobalid" id="patientGlobalid" class="inputW160" value="${patientGlobalid }"/>  
                            <input name="patientId" id="patientId" class="inputW160" readonly="readonly" value="${patientId }"/>    
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>病人姓名： </label>
                        <div class="col-xs-3">
                           <input name="patientName" id="patientName" class="inputW160" onblur="replace(this);ename();getAllReg(this);"   value="${patientInfo.patientName }" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 拼音： </label>
                        <div class="col-xs-3">
                       		<input name="patientNamepy" id="patientNamepy" class="inputW160" readonly="readonly" value="${patientInfo.patientNamepy }"/>
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right">  性别： </label>
                        <div class="col-xs-3" id="sexCol">
                           <select id="patientSex" name="patientSex" class="inputW160" value="${patientInfo.patientSex }" >
                               <option value="1" <c:if test="${patientInfo.patientSex ==1}">selected</c:if>>男</option>
		                       <option value="2" <c:if test="${patientInfo.patientSex ==2}">selected</c:if>>女</option>		                       
		                   </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>生日： </label>
                        <div class="col-xs-3">
                       		<input id="patientDob" name="patientDob" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" onchange="getAge(this)" 
                       		value="<fmt:formatDate value='${patientInfo.patientDob}' pattern='yyyy-MM-dd'/>"  />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 年龄： </label>
                        <div class="col-xs-3">
                           <input type="hidden" name="patientAge" id="patientAge" class="inputW80" value="${fn:substring(patientInfo.patientAge, 0, fn:length(patientInfo.patientAge)-1)}"/>
                           <input name="patientAgeShow" id="patientAgeShow" class="inputW80" onchange="ages(this)"  value="${fn:substring(patientInfo.patientAge, 0, fn:length(patientInfo.patientAge)-1)}" />
		                   <select id="patientAgeUnit" name="patientAgeUnit" class="inputW80" >
		                      <option value="1" <c:if test="${fn:substring(patientInfo.patientAge, fn:length(patientInfo.patientAge)-1,1)=='岁'}">selected</c:if>>岁</option>     
		                      <option value="2" <c:if test="${fn:substring(patientInfo.patientAge, fn:length(patientInfo.patientAge)-1,1)=='月'}">selected</c:if>>月</option>     
		                      <option value="3" <c:if test="${fn:substring(patientInfo.patientAge, fn:length(patientInfo.patientAge)-1,1)=='天'}">selected</c:if>>天</option>                       
		                   </select> 
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 电话： </label>
                        <div class="col-xs-3">
                        	<input name="patientPhone" id="patientPhone" class="inputW160" value="${patientInfo.patientPhone }"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 手机： </label>
                        <div class="col-xs-3">
                           <input name="patientMobile" id="patientMobile" class="inputW160" value="${patientInfo.patientMobile }"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 身份证： </label>
                        <div class="col-xs-3">
                        	<input name="patientIdnumber" id="patientIdnumber" class="inputW160" value="${patientInfo.patientIdnumber }" />
                        </div>
                        <!-- <label class="col-xs-2 control-label no-padding-right"> 医保号： </label>
                        <div class="col-xs-3">
                        	<input name="patientMedicareid" id="patientMedicareid" class="inputW160" value="${patientInfo.patientMedicareid }" />
                        </div> -->
                    </div> 
                                        
                    <div class="form-group">
                        <!--<label class="col-xs-2 control-label no-padding-right"> 就诊卡： </label>
                        <div class="col-xs-3">
                           <input name="patientCardid" id="patientCardid" class="inputW160" value="${patientInfo.patientCardid }" />
                        </div> -->
                        <label class="col-xs-2 control-label no-padding-right"> 住址： </label>
                        <div class="col-xs-3">
                           <input name="patientAddress" id="patientAddress" class="inputW246" value="${patientInfo.patientAddress }" />
                        </div> 
                        <label class="col-xs-5 control-label no-padding-right"> 备注： </label>
                        <div class="col-xs-3">
                        	<input name="patientRemark" id="patientRemark" class="inputW160" value="${patientInfo.patientRemark }" />
                        </div> 
                    </div>
			</div>
			   
		</div>	
	</div>
	
	<div class="page-content min_style" style="padding:0px 0px 0px;">
        <div class="row">            
            <h1>&nbsp;&nbsp;&nbsp;检查信息</h1>
            <div class="col-xs-12 col-sm-12 col-md-12">
                
                    <div class="form-group">                        
                        <label class="col-xs-2 control-label no-padding-right"> 检查号： </label>
                        <div class="col-xs-3">                         
                            <input name="studyAccnumber" id="studyAccnumber" class="inputW160" readonly="readonly" value="${studyAccnumber }"/>  
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>病人类型： </label>
                        <div class="col-xs-3">
                           <select id="patienttypeCode" name="patienttypeCode" style="width:160px;" onchange="changeType(this)">
                             <option value='-1'>请选择</option>
                             <c:forEach var="patientType" items="${patientTypes}" >
                               <option value='${patientType.itemNo }' <c:if test="${studyInfoBean.patienttypeCode == patientType.itemNo}">selected</c:if>>${patientType.itemName }</option>
                             </c:forEach>                             
                           </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 申请科室： </label>
                        <div class="col-xs-3">
                       		<select id="studyApplocid" name="studyApplocid" style="width:160px;" onchange="changeCareProv(this)">
	                       		<option value='-1'>请选择</option>
	                       		<c:forEach var="studyApploc" items="${studyApplocs}" >
	                               <option value='${studyApploc.locId }' <c:if test="${studyInfoBean.studyApplocid == studyApploc.locId}">selected</c:if>>${studyApploc.locDesc }</option>
	                            </c:forEach>       
                       		</select>
                        </div>
                    </div>
                     
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 申请医生： </label>
                        <div class="col-xs-3" id="studyAppdoc_div">
                            <select id="studyAppdoc" name="studyAppdoc" style="width:160px;" >
                                <option value='-1'>请选择</option>
                                <c:forEach var="careProvBean" items="${careProvBeans}" >
	                               <option value='${careProvBean.careprovId }' <c:if test="${studyInfoBean.studyAppdoc == careProvBean.careprovId}">selected</c:if>>${careProvBean.careprovName }</option>
	                            </c:forEach>  
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 病区： </label>
                        <div class="col-xs-3" id="studyWard_div">
                        	<select id="studyWard" name="studyWard" style="width:160px;">
	                        	<option value="-1">请选择</option>
	                        	<c:forEach var="studyWard" items="${studyWards}" >
	                               <option value='${studyWard.locId }' <c:if test="${studyInfoBean.studyWard == studyWard.locId}">selected</c:if>>${studyWard.locDesc }</option>
	                            </c:forEach>   
                        	</select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 床号： </label>
                        <div class="col-xs-3">
                           <input name="studyBedno" id="studyBedno" class="inputW160" value="${studyInfoBean.studyBedno}"/> 
                        </div>
                    </div>
                                       
                    
	                    <div class="form-group">
	                    	<!-- 有会诊机构权限才展现 -->
                    		<c:if test="${HZSQ_ROLE == 'y'}">
		                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>会诊机构： </label>
		                        <div class="col-xs-3">
		                           <select id="studyConsultorg" name="studyConsultorg" style="width:160px;" onchange="setLocItem(this)">
		                                <option value="-1">请选择机构</option>
		                            </select> 
		                        </div>
		                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>会诊科室： </label>
		                        <div class="col-xs-3">
		                        	<select id="studyConsultloc" name="studyConsultloc" style="width:160px;" >
			                       		<option value='-1'>请选择</option>    
		                       		</select>
		                        </div> 
		                    </c:if>
	                        <label class="col-xs-2 control-label no-padding-right">预约时间：</label>
	                        <div class="col-xs-3">
	                            <input name="periodDate" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${periodDate}"/>
	                            <select name="period" style="width:80px;" >
		                       		<c:forEach var="periodlist" items="${periods}" >
		                               <option value='${periodlist.periodStarttime }' <c:if test="${ periodlist.periodStarttime == periodStartTime}">selected</c:if>>${periodlist.periodDesc }</option>
		                             </c:forEach>  
	                       		</select>
	                        </div> 
	                 </div>                         
                    
                     <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right">  临床诊断： </label>
                        <div class="col-xs-3" id="studyAppdoc_div">
                           <input name="studyClinic" id="studyClinic" class="inputW160" value="${studyInfoBean.studyClinic}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 备注： </label>
                        <div class="col-xs-3" id="studyWard_div">
                        	<input name="studyRemark" id="studyRemark" class="inputW160" value="${studyInfoBean.studyRemark}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 实时交互会诊： </label>
                        <div class="col-xs-3">
                            <input name="StudyConsulttype" type="checkbox" value="1" class="inputW50"/>                        
                         </div>
                    </div>
                        
                    <div class="form-group" style="margin-left: 12px;">
                    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
                    		<c:forEach items="${fileBean}" var="file">
		                    	<tr>
									<td width="30%"><a href="javascript:void(window.open('${prePath}${file.filePath}'))">${file.filePath}</a></td>
									<td width="30%"><input type="button" value="下载" onclick="downloadFile('${file.filePath}')">&nbsp;&nbsp;&nbsp;<input type="button" value="删除" onclick="deleteFile('${file.fileId}')"></td>
									<td width="40%"></td>
								</tr>
							</c:forEach>
						</table>
                	</div>                             
			</div>	
		</div>	
	</div>	
	 
     <div class="clearfix form-actions">
          <div class="col-bt-right">
          	<a href="javascript:void(0)" id="savebtn" onclick="saveConsult()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>申请保存</a>
          	<a href="javascript:void(0)" id="cancelbtn" onclick="cancelHzrecord()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>申请取消</a>
          	<a href="javascript:void(0)" id="imgviewbtn" onclick="imageView()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>查看影像资料</a>
          	<a href="javascript:void(0)" id="imagebtn" onclick="imageData()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>影像资料获取</a>
          	<a href="javascript:void(0)" id="medicalRecordbtn" onclick="medicalRecordUpload()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>电子病历上传</a>
          	<a href="javascript:void(0)" id="submitbtn" onclick="sbmitInfo()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>提交申请</a>
          </div>
     </div>       
	
	</form>	
</div> 
 

</body>
</html>
