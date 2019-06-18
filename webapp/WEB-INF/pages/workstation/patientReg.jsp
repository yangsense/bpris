<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>登记</title>
    
   

    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
     <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
    
    <link href="${ctx}/aris/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link href="${ctx}/aris/statics/pages/css/index/css/detail.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/inner.css" rel="stylesheet" type="text/css"/>
         
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
    
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>   
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/PYjm.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/jquery.form.min.js"></script>   
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>   
    
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>  
     
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/chosen/chosen.jquery.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/patientReg.js"></script>
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
	
	ul.ztree {margin-top: 10px;border: 1px solid #617775;background: #f0f6e4;width:220px;height:360px;overflow-y:scroll;overflow-x:auto;}
    </style>
     
    
</head>
<body>
<!-- <object ID='readOCX' height='0' CLASSID='CLSID:ED6D80AA-3780-4F28-9F70-813703B0F2F0' codeBase="${ctx}/ReadCardCab.cab#version=1.0.0.1"  width="100%" height="250" ></object> -->
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
            <li class="active">病人登记</li> 
        </ul>
    </div>
	<!--导航end-->
	 
    <input type="hidden" id="studyInfoId" name="studyinfoId" value="${studyinfoId }"/>    
    <form class="form-horizontal" id="patientRegForm" >
    <input type="hidden" name="orgId" id="orgId"/>
    <input type="hidden" name="yuyueTime" id="yuyueTime"/>      
    <input type="hidden" name="locId" id="locId" value="${locId }"/>    
    <input type="hidden" id="eConsultorg" value="${studyConsultorg }"/>   
    <input type="hidden" id="eConsultloc" value="${studyConsultloc }"/>       
    <input type="hidden" name="oldStudyinfoId" value="${studyinfoId }"/>
    <input type="hidden" id="isNew" name="isNew" value="${isNew }"/> 
    <input type="hidden" id="isUrgentOld" name="isUrgentOld" value="${isUrgent }"/> 
    <input type="hidden" id="old_studyAppdoc" name="old_studyAppdoc" value="${studyAppdoc }"/> 
    <input type="hidden" id="patientAgeOld" name="patientAgeOld" value="${patientInfo.patientAge }"/> 
    <input type="hidden" id="isHis" name="isHis"  />
    <input type="hidden" id="hisZydjxh" name="hisZydjxh"  />
    <input type="hidden" id="again" name="again"  value="${again}"/>  
    <input type="hidden" id="studyHisappid" name="studyHisappid"  /> 
        <input type="hidden" id="isConsult" name="isConsult" value="n" /> 
    
    <div class="page-content min_style" style="padding:0px 0px 0px;">
        <div class="row">
            <div class="form-group" id="hisSelectDiv">
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
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                           <button  id="readBtn" type="button" onclick="readCard()">读卡</button>           
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"></label>
                        <div class="col-xs-3">
                        </div> 
                    </div>
            <h1>&nbsp;&nbsp;&nbsp;病人基本信息
<%--	             <div class="btn-group btn-group-xs col-bt-right" role="group" aria-label="Extra-small button group" --%>
<%--                      style='float: right;margin-top: 3px;' >                       --%>
<%--			        <button type="button" class="btn btn-primary" onclick='goBack()'>返回</button>--%>
<%--			     </div>--%>
            </h1>
              
            <div class="col-xs-12 col-sm-12 col-md-12">
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>病人姓名： </label>
                        <div class="col-xs-3">
                           <input type="text" name="patientName" id="patientName" class="inputW160" onblur="replace(this);ename();getAllReg(this);"  onkeyup="ename();"   value="${patientInfo.patientName }" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 拼音： </label>
                        <div class="col-xs-3">
                       		<input name="patientNamepy" id="patientNamepy" class="inputW160"  value="${patientInfo.patientNamepy }"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 病人ID： </label>
                        <div class="col-xs-3">                            
                            <input type="hidden" name="patientGlobalid" id="patientGlobalid" class="inputW160" value="${patientGlobalid }"/>  
                            <input name="patientId" id="patientId" class="inputW160" readonly="readonly" value="${patientId }"/>    
                        </div>
                    </div> 
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"><span class="ret_span">*</span>性别： </label>
                        <div class="col-xs-3" id="sexCol">
                           <select id="patientSex" name="patientSex" class="inputW160" value="${patientInfo.patientSex }" >
                               <option value="-1">请选择</option>
                               <option value="1" <c:if test="${patientInfo.patientSex ==1}">selected</c:if>>男</option>
		                       <option value="2" <c:if test="${patientInfo.patientSex ==2}">selected</c:if>>女</option>
		                       <option value="0" <c:if test="${patientInfo.patientSex ==0}">selected</c:if>>未知</option>	                       
		                   </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 电话： </label>
                        <div class="col-xs-3">
                        	<input type="text" name="patientPhone" id="patientPhone" class="inputW160" value="${patientInfo.patientPhone }"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 手机： </label>
                        <div class="col-xs-3">
                           <input type="text" name="patientMobile" id="patientMobile" class="inputW160" value="${patientInfo.patientMobile }"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right"><span class="ret_span">*</span> 年龄： </label>
                        <div class="col-xs-3">
                           <input type="hidden" name="patientAge" id="patientAge" class="inputW80" value="${fn:substring(studyInfoBean.patientAge, 0, fn:length(studyInfoBean.patientAge)-1)}"/>
                           <input name="patientAgeShow" id="patientAgeShow" class="inputW80" onblur="ages(this)"  value="${fn:substring(studyInfoBean.patientAge, 0, fn:length(studyInfoBean.patientAge)-1)}" 
                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"/>
		                   <select id="patientAgeUnit" name="patientAgeUnit" class="inputW80" >
		                      <option value="1" <c:if test="${fn:substring(studyInfoBean.patientAge, fn:length(studyInfoBean.patientAge)-1,1)=='岁'}">selected</c:if>>岁</option>     
		                      <option value="2" <c:if test="${fn:substring(studyInfoBean.patientAge, fn:length(studyInfoBean.patientAge)-1,1)=='月'}">selected</c:if>>月</option>     
		                      <option value="3" <c:if test="${fn:substring(studyInfoBean.patientAge, fn:length(studyInfoBean.patientAge)-1,1)=='天'}">selected</c:if>>天</option>  
		                      <option value="4" >小时</option>                            
		                   </select> 
		                   
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 生日： </label>
                        <div class="col-xs-3">
                       		<input id="patientDob" name="patientDob" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" onchange="getAge(this)" 
                       		value="<fmt:formatDate value='${patientInfo.patientDob}' pattern='yyyy-MM-dd'/>"  />
                        </div>
                        
                        <label class="col-xs-2 control-label no-padding-right"> 医保号： </label>
                        <div class="col-xs-3">
                        	<input type="text" name="patientMedicareid" id="patientMedicareid" class="inputW160" value="${patientInfo.patientMedicareid }" />
                        </div>
                    </div> 
                                        
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 就诊卡： </label>
                        <div class="col-xs-3">
                           <input type="text" name="patientCardid" id="patientCardid" class="inputW160" value="${patientInfo.patientCardid }" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 身份证： </label>
                        <div class="col-xs-3">
                        	<input type="text" name="patientIdnumber" id="patientIdnumber" class="inputW160" value="${patientInfo.patientIdnumber }" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 备注： </label>
                        <div class="col-xs-3">
                        	<input type="text" name="patientRemark" id="patientRemark" class="inputW160" value="${patientInfo.patientRemark }" />
                        </div> 
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 住址： </label>
                        <div class="col-xs-3">
                           <input type="text" name="patientAddress" id="patientAddress" class="inputW350" value="${patientInfo.patientAddress }" />
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
                    	<label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>病人类型： </label>
                        <div class="col-xs-3">
                           <select id="patienttypeCode" name="patienttypeCode" style="width:160px;" onchange="changeType(this)">
                             <option value='-1'>请选择</option>
                             <c:forEach var="patientType" items="${patientTypes}" >
                               <option value='${patientType.itemNo }' <c:if test="${studyInfoBean.patienttypeCode == patientType.itemNo}">selected</c:if>>${patientType.itemName }</option>
                             </c:forEach>                             
                           </select>
                        </div>                     
                        <label class="col-xs-2 control-label no-padding-right"> 申请医生： </label>
                        <div class="col-xs-3" id="studyAppdoc_div">
                            <select id="studyAppdoc" name="studyAppdoc" style="width:160px;" >
                                <option value='-1'>请选择</option>
                                <!--<c:forEach var="careProvBean" items="${careProvBeans}" >
	                               <option value='${careProvBean.careprovId}' <c:if test="${studyInfoBean.studyAppdoc == careProvBean.careprovId}">selected</c:if>>${careProvBean.careprovName }</option>
	                            </c:forEach>-->  
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 检查号： </label>
                        <div class="col-xs-3">                         
                            <input name="studyAccnumber" id="studyAccnumber" class="inputW160" readonly="readonly" value="${studyAccnumber }"/>  
                        </div>
                    </div>
                     
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right"><span class="ret_span_INP" style="display: none;">*</span><span class="ret_span_OP" style="display: none;">*</span>申请科室： </label>
                        <div class="col-xs-3">
                       		<select id="studyApplocid" name="studyApplocid" style="width:160px;" onchange="changeCareProv(this)">
	                       		<option value='-1'>请选择</option>
	                       		<c:forEach var="studyApploc" items="${studyApplocs}" >
	                               <option value='${studyApploc.locId }' <c:if test="${studyInfoBean.studyApplocid == studyApploc.locId}">selected</c:if>>${studyApploc.locDesc }</option>
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
                        <label class="col-xs-2 control-label no-padding-right"> 检查优先级： </label>
                        <div class="col-xs-3">
                           <select id="studylevelId" name="studylevelId" style="width:160px;">
	                           <option value='-1'>请选择</option>
	                           <c:forEach var="studyLevel" items="${studyLevels}" >
			                           <option value='${studyLevel.itemNo }' <c:if test="${studyInfoBean.studylevelId == studyLevel.itemNo}">selected</c:if>>${studyLevel.itemName }</option>
			                   </c:forEach>                            
                           </select>
                        </div> 
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right"> <span class="ret_span_INP" style="display: none;">*</span>床号： </label>
                        <div class="col-xs-3">
                           <input type="text" name="studyBedno" id="studyBedno" class="inputW160" value="${studyInfoBean.studyBedno}"/> 
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 检查设备： </label>
                        <div class="col-xs-3">
                        	<select id="equipmentId" name="equipmentId" style="width:160px;" onchange="setRoomId(this)">
	                        	<option value='-1'>请选择</option>
	                        	<c:forEach var="equipmentBean" items="${equipmentBeans}" >
		                           <option value='${equipmentBean.equipmentId }' roomId='${equipmentBean.roomId }' <c:if test="${studyInfoBean.equipmentId == equipmentBean.equipmentId}">selected</c:if>>${equipmentBean.equipmentDesc }</option>
		                        </c:forEach>   
                        	</select> 
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 房间： </label>
                        <div class="col-xs-3">
                           <input name="roomId" id="roomId" class="inputW160" readonly="readonly" value="${studyInfoBean.roomId}"/>
                        </div>
                    </div>
                                         
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right"> <span class="ret_span_INP" style="display: none;">*</span><span class="ret_span_HP" style="display: none;">*</span><span id="hpnum">住院号：</span> </label>
                        <div class="col-xs-3">
                        	<input type="text" name="patientInpatientid" id="patientInpatientid" class="inputW160" value="${studyInfoBean.patientInpatientid}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 门诊号： </label>
                        <div class="col-xs-3">
                           <input type="text" name="patientOutpatientid" id="patientOutpatientid" class="inputW160" value="${studyInfoBean.patientOutpatientid}"/>
                        </div>
                        
                        <label class="col-xs-2 control-label no-padding-right"> 收费类型： </label>
                        <div class="col-xs-3">
                        	<select id="paymenttypeId" name="paymenttypeId" style="width:160px;">
                        	<option value='-1'>请选择</option>                        	
                        	<c:forEach var="paymentType" items="${paymentTypes}" >
			                           <option value='${paymentType.itemNo }' <c:if test="${studyInfoBean.paymenttypeId == paymentType.itemNo}">selected</c:if>>${paymentType.itemName }</option>
			                   </c:forEach>   
                        	</select>
                        </div> 
                    </div>  
                    
	                    <div class="form-group">
	                        <!-- 有会诊机构权限才展现 -->
                            <c:if test="${DIAGNOSE_ROLE == 'y'}">
		                        <label class="col-xs-2 control-label no-padding-right"> 诊断机构： </label>
		                        <div class="col-xs-3">
		                           <select id="studyConsultorg" name="studyConsultorg" style="width:160px;" onchange="setLocItem(this)">
		                                <option value="-1">请选择机构</option>
		                            </select> 
		                        </div>
		                        <label class="col-xs-2 control-label no-padding-right"> 诊断科室： </label>
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
                    	<label class="col-xs-2 control-label no-padding-right">是否加急： </label>
                        <div class="col-xs-3">
                           <select id="isUrgent" name="isUrgent" class="inputW160">
                                <option value="0">否</option>
                                <option value="1">是</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 临床诊断： </label>
                        <div class="col-xs-3">
                        	<input type="text" name="studyClinic" id="studyClinic" class="inputW246" value="${studyInfoBean.studyClinic}"/>
                        </div>  
                    </div>                                      
			</div>	
		</div>	
	</div>
	
	<h1>&nbsp;&nbsp;&nbsp;检查项目</h1>
	<div class="fM_listTable2">
              <table id="configTab" width="100%" border="0" cellspacing="0" cellpadding="0">
                <tbody>
                 <tr>
		            <th width="18%" class="c"><span class="ret_span">*</span>检查项目描述</th>  
		            <th width="20%" class="c">检查部位</th>               
		           <!--  <th width="16%" class="c">检查部位</th> -->            
		            <th width="4%" class="c">数量</th>
		            <th width="4%" class="c">价格</th>
		            <th width="8%" class="c">操作</th>
		          </tr>
              </tbody>
         </table>               
     </div>
        <div class="clearfix form-actions">
                        <div class="col-bt-right">
                        	<!-- <button class="btn btn-primary" type="button" id="reset" onclick="com.ai.bdx.util.reset('patientRegForm');">
                                <i class="ace-icon fa fa-check bigger-110 "></i>
                                                                                                          清空
                            </button> -->
                            <button class="btn btn-primary" type="button" onclick="regPrint();">
                                <i class="ace-icon fa fa-check bigger-110 "></i>
                                                                                                        扫描                                                                        
                            </button>
                            <button class="btn btn-primary" type="button" onclick="regPrint();">
                                <i class="ace-icon fa fa-check bigger-110 "></i>
                                                                                                         打印                                                                        
                            </button>
                            <button class="btn btn-primary" type="submit" id="saveBtn" onkeydown="if(event.keyCode==13){return false;}">
                                <i class="ace-icon fa fa-check bigger-110 icon-save"></i>
                                                                                                         保 存
                            </button>
                        </div>
          </div> 
<%--      <div class="clearfix form-actions" >--%>
<%--           <div class="col-bt-right">  --%>
<%--            --%>
<%--                <button class="btn btn-primary" type="submit" id="saveBtn">登记</button>--%>
<%--                <button class="btn btn-pink" type="button" onclick="regPrint();" >打印</button> --%>
<%--           		<button class="btn btn-inverse" type="button" onclick="reloadGrid();" >扫描</button>                   		--%>
<%--                <button class="btn btn-warning" type="button"  id="reset" onclick="com.ai.bdx.util.reset('patientRegForm');"><i class="b_i_refresh"></i>清空</button>--%>
<%--           </div>--%>
<%--       </div>  --%>
	
	</form>	
</div> 
<%--  <div class="peopBox" id="mapBox">
	  	<!--男正面-->
        <div class="man_z" id="maleMapFront">
			<a href="javascript:void(0);" target="_self" class="mz1" title="头" v="头" vid="1"></a>
			<a href="javascript:void(0);" target="_self" class="mz2" title="胸" v="胸" vid="11"></a>
			<a href="javascript:void(0);" target="_self" class="mz3" title="腹" v="腹" vid="13"></a>
			<a href="javascript:void(0);" target="_self" class="mz4" title="上肢" v="上肢" vid="15"></a>
			<a href="javascript:void(0);" target="_self" class="mz5" title="下肢" v="上肢" vid="16"></a>
			<a href="javascript:void(0);" target="_self" class="mz6" title="男性生殖" v="男性生殖" vid="18" t="男性生殖部位"></a>
			<span class="headLink">
				<a href="javascript:void(0);" target="_self" class="" v="头" vid="1">头</a>
				<a href="javascript:void(0);" target="_self" v="脑" vid="9">脑</a>
				<a href="javascript:void(0);" target="_self" v="眼" vid="7" t="眼">眼</a>
				<a href="javascript:void(0);" target="_self" v="咽喉"vid="6">咽喉</a>
				<a href="javascript:void(0);" target="_self" v="眼" vid="4" t="眼">鼻</a>
				<a href="javascript:void(0);" target="_self" v="耳" vid="5" t="耳">耳</a>
				<a href="javascript:void(0);" target="_self" v="口" vid="8" t="口">口</a>
				<a href="javascript:void(0);" target="_self" v="面部" vid="3" t="面部">面部</a>
			</span>
			<i></i>
		</div>
		<!--男正面 end-->
		<!--男背面-->
        <div class="man_b" id="maleMapBack">
			<a href="javascript:void(0)" target="_self" class="mb1" title="颈" vid="10" v="颈"></a>
			<a href="javascript:void(0)" target="_self" class="mb2" title="盆腔" vid="19" v="盆腔"></a>
			<a href="javascript:void(0)" target="_self" class="mb3" title="臀" vid="14" v="臀"></a>
			<a href="javascript:void(0)" target="_self" class="mb4" title="腰" vid="12" v="腰"></a>
			<a href="javascript:void(0)" target="_self" class="mb5" title="骨" vid="17"  v="骨" t="骨骼"></a>
			<a href="javascript:void(0)" target="_self" class="mb6" title="背部" vid="22"  v="背部"></a>
			<a href="javascript:void(0)" target="_self" class="mb7" title="全身" vid="20"  v="全身"></a>
			<a href="javascript:void(0)" target="_self" class="mb71" title="全身" vid="20"  v="全身"></a>
			<i></i>
		</div>
		<!--男背面 end-->
		<!--女正面-->
        <div class="woman_z"  id="femaleMapFront">
			<a href="javascript:void(0)" target="_self" class="wmz1" title="头" vid="1" v="头"></a>
			<a href="javascript:void(0)" target="_self" class="wmz2" title="胸" vid="11" v="胸"></a>
			<a href="javascript:void(0)" target="_self" class="wmz3" title="腹" vid="13" v="腹"></a>
			<a href="javascript:void(0)" target="_self" class="wmz4" title="上肢" vid="15" v="上肢"></a>
			<a href="javascript:void(0)" target="_self" class="wmz5" title="下肢" vid="16" v="下肢"></a>
			<a href="javascript:void(0)" target="_self" class="wmz6" title="女性生殖" vid="24"  v="女性生殖部位" t="女性生殖部位"></a>
			<span class="headLink">
				<a href="javascript:void(0)" target="_self" class="" v="头" vid="1" >头</a>
				<a href="javascript:void(0)" target="_self" vid="9" v="脑">脑</a>
				<a href="javascript:void(0)" target="_self" vid="2" v="眼部" t="眼部">眼</a>
				<a href="javascript:void(0)" target="_self" vid="6" v="咽喉">咽喉</a>
				<a href="javascript:void(0)" target="_self" vid="4" v="鼻部" t="鼻部">鼻</a>
				<a href="javascript:void(0)" target="_self" vid="5" v="耳部" t="耳部">耳</a>
				<a href="javascript:void(0)" target="_self" vid="8" v="口部" t="口部">口</a>
				<a href="javascript:void(0)" target="_self" vid="3" v="面部" t="面部">面部</a>
			</span>
			<i></i>
		</div>
		<!--女正面 end-->
		<!--女背面-->
        <div class="woman_b"  id="femaleMapBack">
			<a href="javascript:void(0)" target="_self" class="wmb1" title="颈" vid="10" v="颈"></a>
			<a href="javascript:void(0)" target="_self" class="wmb2" title="盆腔" vid="19" v="盆腔"></a>
			<a href="javascript:void(0)" target="_self" class="wmb3" title="臀" vid="14" v="臀"></a>
			<a href="javascript:void(0)" target="_self" class="wmb4" title="腰" vid="12" v="腰"></a>
			<a href="javascript:void(0)" target="_self" class="wmb5" title="骨" vid="17" v="骨" t="骨骼"></a>
			<a href="javascript:void(0)" target="_self" class="wmb6" title="背部" vid="22" v="骨"></a>
			<a href="javascript:void(0)" target="_self" class="wmb7" title="全身" vid="20" v="全身"></a>
			<a href="javascript:void(0)" target="_self" class="wmb71" title="全身" vid="20" v="全身"></a>
			<i></i>
		</div>
		<!--女背面 end-->
      </div>--%>

</body>
</html>
