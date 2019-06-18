<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
     <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    
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
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>   
    
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>  
     
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/chosen/chosen.jquery.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/updatePatientInfo.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/patientReg.js"></script>
</head>
 <body class="body">
 <input type="hidden" id="patientAgeOld" name="patientAgeOld" value="${patBean.patientAge }"/> 
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form"  id="patientForm">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>病人ID： </label>
                        <div class="col-xs-3">
                            <input  class="inputW160" type="text" id="patientId" name="patientId" value="${patBean.patientId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>病人姓名： </label>
                        <div class="col-xs-3">
                       	   <input type="text" name="patientName" id="patientName" class="inputW160" onblur="replace(this);ename();" onkeypress="ename();"   value="${patBean.patientName }" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>姓名拼音： </label>
                        <div class="col-xs-3">
                            <input  class="inputW160" type="text" id="patientNamepy" name="patientNamepy" value="${patBean.patientNamepy}" />                      
                         </div>
                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>性别： </label>
                        <div class="col-xs-3">
                        	<select id="patientSex" name="patientSex" class="inputW160" value="${patBean.patientSex }" >
                               <option value="-1">请选择</option>
                               <option value="1" <c:if test="${patBean.patientSex ==1}">selected</c:if>>男</option>
		                       <option value="2" <c:if test="${patBean.patientSex ==2}">selected</c:if>>女</option>		                       
		                   </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>年龄： </label>
                        <div class="col-xs-3">
                       	   <input type="hidden" name="patientAge" id="patientAge" class="inputW80" value="${fn:substring(patBean.patientAge, 0, fn:length(patBean.patientAge)-1)}"/>
                           <input name="patientAgeShow" id="patientAgeShow" class="inputW80" onblur="ages(this)"  value="${fn:substring(patBean.patientAge, 0, fn:length(patBean.patientAge)-1)}" 
                           onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"  onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'0')}else{this.value=this.value.replace(/\D/g,'')}"/>
		                   <select id="patientAgeUnit" name="patientAgeUnit" class="inputW80" >
		                      <option value="1" <c:if test="${fn:substring(patBean.patientAge, fn:length(patBean.patientAge)-1,1)=='岁'}">selected</c:if>>岁</option>     
		                      <option value="2" <c:if test="${fn:substring(patBean.patientAge, fn:length(patBean.patientAge)-1,1)=='月'}">selected</c:if>>月</option>     
		                      <option value="3" <c:if test="${fn:substring(patBean.patientAge, fn:length(patBean.patientAge)-1,1)=='天'}">selected</c:if>>天</option>  
		                      <option value="4" >小时</option>                            
		                   </select> 
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>生日： </label>
                        <div class="col-xs-3">
                        	<input id="patientDob" name="patientDob" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  readonly="readonly" onchange="getAge(this)" 
                       		value="<fmt:formatDate value='${patBean.patientDob}' pattern='yyyy-MM-dd'/>"  />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 电话： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="patientPhone" name="patientPhone" value="${patBean.patientPhone}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 手机： </label>
                        <div class="col-xs-3">
                            <input  class="inputW160" type="text" id="patientMobile" name="patientMobile" value="${patBean.patientMobile}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 医保号： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="patientMedicareid" name="patientMedicareid" value="${patBean.patientMedicareid}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 就诊卡： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="patientCardid" name="patientCardid" value="${patBean.patientCardid}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 身份证： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="patientIdnumber" name="patientIdnumber" value="${patBean.patientIdnumber}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 住址： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="patientAddress" name="patientAddress" value="${patBean.patientAddress}"/>
                        </div>
                    </div>
                    
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">  
                        	<button class="btn btn-info" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                             	   修  改
                            </button>                          
                            <button class="btn btn-info" type="button" id="closeBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                             	   关  闭
                            </button>
                        </div>
                    </div>
                </form>
			</div>
		</div>	
	</div>
</div>
 </body>
</html>
