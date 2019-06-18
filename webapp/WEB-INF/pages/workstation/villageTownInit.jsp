<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>检查登记</title>
	<!-- 检查登记 -->
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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/villageTowninit.js"></script>
    <!-- 盖掉bootstrap样式 -->
    <style type="text/css">
    /*
    #myul{ width:65%;}
    #myul li { list-style:none;border:1px solid #96C2F1; padding:1px;}
    #myul li span{ list-style:none; background:#B2D3F5; width:65%; height:20px; display:block; padding-left:20px;  }
    #myul li span.hand{ cursor:pointer;background:#B2D3F5 url(img/right.gif) no-repeat 5px center;}
    #myul li span.current{ background:#b2d300 url(img/down.gif) no-repeat 5px center; }
    #myul li div{ background:#EFF7FF;width:65%; padding:0px 5px 5px 5px; }
    */
    label.help-block-new {
	    display: block;
	    margin-top: -30px;
	    margin-left: 180px;
	    margin-bottom: 10px;
	    color: #ff7777
    }    
    .main-content h1{ 
    background:#cfdcd9; 
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
<body style="margin-top:-10px;">
<!-- <object ID='readOCX' height='0' CLASSID='CLSID:ED6D80AA-3780-4F28-9F70-813703B0F2F0' codeBase="${ctx}/ReadCardCab.cab#version=1.0.0.1"  width="100%" height="250" ></object> -->
<div class="thickdiv" style="display: none; height: 100%;"></div>
<div class="main-content" style="padding-left: 5px;">
    <div class="mainLeft" style="float: left; width: 37%; height: 100%; border-right: solid 1px #ddd; ">
         <div class="LeftTop" style="width: 100%; min-height: 35%; border-bottom: solid 1px #ddd; ">
         	<form id="searchForm" action="#">
	         <table border="0" cellspacing="0" cellpadding="0" style="width: 100%;">
	         	<!-- <tr>
	         		<th>
	         			<select id="qType" name="qType" class="inputW120" >
                           		<option value="1">登记号</option>
                                <option value="2">检查号</option>
                        </select>
                    </th>
                    <td width="20%">
                        <input name="qValue" id="qValue" class="inputW120"/> 
                    </td> 
                    <th>设备类型<b>:</b></th>
                    <td>
                        <select id="modalitiesinstudy" name="modalitiesinstudy" class="inputW120">
                                 <option value="-1">全部</option>         
                        </select>
                    </td>
                </tr>
	         	<tr >
	         		<th >检查日期<b>:</b></th>
	         		<td colspan="2">
		         		<input id="startDate" name="startDate" class="inputW80" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	                       --
	                    <input id="endDate" name="endDate" class="inputW80" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	         		</td>
	         		<td>
	         			<a href="javascript:void(0)" onclick="refreshGrid1()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span class="icon-search"></span>查询</a>
	         		</td>
	         	</tr> -->
	         	
	         	<tr>
	         		<td>
	         			<select id="qType" name="qType" class="inputW120" >
                           		<option value="1">登记号</option>
                                <option value="2">检查号</option>
                        </select>
                         : 
                        <input name="qValue" id="qValue" class="inputW120"/>
	         		</td>
	         		<td>
	         			设备类型 : 
	         			<select id="modalitiesinstudy" name="modalitiesinstudy" class="inputW120">
                                 <option value="-1">全部</option>         
                        </select>
	         		</td>
	         	</tr>
	         	<tr>
	         		<td>
	         			检查日期 : 
	         			<input id="startDate" name="startDate" class="inputW80" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	                       --
	                    <input id="endDate" name="endDate" class="inputW80" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
	         		</td>
	         		<td style="float: right; margin: 4px 22px 4px 0;">
	         			<a href="javascript:void(0)" onclick="refreshGrid1()" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success">
	         				<span class="icon-search"></span>查询
         				</a>
	         		</td>
	         	</tr>
	         	
	         </table>
	         </form>
         </div>
         <div class="LeftBottom" style="width: 100%; min-height: 70%; ">
         	<div> 
            	<table id="studylist" width="100%" border="0" cellspacing="0" cellpadding="0">
               
            	</table>
            	<div id="studylist_pager"></div>
        	</div>
         </div>
    </div>
    <!-- 右上登记信息start -->
    
    <div class="mainRight" style="float: right; width: 63%; height: 100%;">
    <input type="hidden" id="studyinfoId" name="studyinfoId" value="${studyinfoId }"/>   
    <input type="hidden" id="consultRole" name="consultRole" value="${CONSULT_ROLE }"/>  
    <input type="hidden" id="hzisFlag" name="hzisFlag" value="${HZ_IS_FLAG}"/>  
    	<form class="form-horizontal" id="patientRegForm" >
    	<input type="hidden" name="yuyueTime" id="yuyueTime"/> 
    	<input type="hidden" id="isNew" name="isNew" value="work2"/>  
    	<input type="hidden" name="orgId" id="orgId"/>
    	<input type="hidden" name="patientkey" id="patientkey"/>
    	<input type="hidden" name="locId" id="locId" value="${locId}"/>
    	<input type="hidden" id="isConsult" name="isConsult" value="n" /> 
		 <ul id="myul">
			 <!-- <li><span>折叠特效三级</span> -->
	         <div class="LeftTop hid" style="width: 100%; min-height: 70%; border-bottom: solid 1px #ddd;">
	         	<div class="page-content min_style" style="padding:0px 0px 0px;">
			        <div class="row">
			            <h1>&nbsp;&nbsp;&nbsp;病人基本信息
			            </h1>
			            <div class="col-xs-12 col-sm-12 col-md-12">
			                    <div class="form-group">
			                    	<label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>姓名： </label>
			                        <div class="col-xs-3">
			                           <input type="text" name="patientName" id="patientName" class="inputW160" onblur="replace(this);ename();"  value="${patientInfo.patientName }" />
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
			                           <input name="patientAgeShow" id="patientAgeShow" class="inputW80" onblur="ages(this)" value="${fn:substring(studyInfoBean.patientAge, 0, fn:length(studyInfoBean.patientAge)-1)}" 
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
			                    	<label class="col-xs-2 control-label no-padding-right"> <span class="ret_span">*</span>类型： </label>
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
			                    	<label class="col-xs-2 control-label no-padding-right"><span class="ret_span_INP" style="display: none;">*</span><span class="ret_span_OP" style="display: none;">*</span>科室： </label>
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
			                        <label class="col-xs-2 control-label no-padding-right"> 优先级： </label>
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
			                        <label class="col-xs-2 control-label no-padding-right"><span class="ret_span">*</span> 设备： </label>
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
				                        
				                    </div>  
			                    
			                    
			                    <div class="form-group">
			                    	<label class="col-xs-2 control-label no-padding-right">预约时间：</label>
			                        <div class="col-xs-4">
			                            <input name="periodDate" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${periodDate}"/>
			                            <select name="period" style="width:80px;" >
				                       		<c:forEach var="periodlist" items="${periods}" >
				                               <option value='${periodlist.periodStarttime }' <c:if test="${ periodlist.periodStarttime == periodStartTime}">selected</c:if>>${periodlist.periodDesc }</option>
				                             </c:forEach>  
			                       		</select>
			                        </div>
			                        <label class="col-xs-3 control-label no-padding-right"></label> 
			                    	<label class="col-xs-2 control-label no-padding-right">是否加急： </label>
			                        <div class="col-xs-3">
			                           <select id="isUrgent" name="isUrgent" class="inputW160">
			                                <option value="0">否</option>
			                                <option value="1">是</option>
			                            </select>
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
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
					            <th width="20%" class="c"><span class="ret_span">*</span>检查项目描述</th>  
					            <th width="46%" class="c">检查部位</th>               
					           <!--  <th width="16%" class="c">检查部位</th> -->            
					            <th width="2%" class="c">数量</th>
					            <th width="2%" class="c">价格</th>
					            <th width="30%" class="c">操作</th>
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
			                            <button class="btn btn-primary" type="button" id="printBtn" onclick="regPrint();">
			                                <i class="ace-icon fa fa-check bigger-110 "></i>
			                                                                                                         打印                                                                        
			                            </button>
			                            <button class="btn btn-primary" type="submit" id="saveBtn" onkeydown="if(event.keyCode==13){return false;}">
			                                <i class="ace-icon fa fa-check bigger-110 icon-save"></i>
			                                                                                                         保 存
			                            </button>
			                        </div>
			          </div> 
	         </div>
	         </li>
         </ul>
         </form>
         <!-- 右上登记信息end -->
         <div class="LeftBottom" style="width: 100%; height: 30%;">
         	<div> 
         		<form id="downRightForm" action="#"></form>
            	<table id="rightdownlist" width="100%" border="0" cellspacing="0" cellpadding="0">
               
            	</table>
            	<div id="rightdownlist_pager"></div>
        	</div>
         </div>
    </div>
    

</body>
</html>
