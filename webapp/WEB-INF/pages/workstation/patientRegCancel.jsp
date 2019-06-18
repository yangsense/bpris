<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/meta.jsp"%>
<title>病人登记</title>



<link type="text/css" rel="stylesheet"
	href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css" />
<script type="text/javascript"
	src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>

<link type="text/css" rel="stylesheet"
	href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css" />
<script type="text/javascript"
	src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>

<link href="${ctx}/aris/statics/pages/css/index/css/public.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/aris/statics/pages/css/index/css/detail.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/aris/statics/pages/css/index/css/icon.css"
	rel="stylesheet" type="text/css" />
<link href="${ctx}/aris/statics/pages/css/index/css/inner.css"
	rel="stylesheet" type="text/css" />

<script type="text/javascript"
	src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
<script type="text/javascript"
	src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
<script type="text/javascript"
	src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${ctx}/aris/statics/common/plugins/PYjm.js"></script>
<script type="text/javascript"
	src="${ctx}/aris/statics/ace/plugins/jquery/jquery.form.min.js"></script>
<script type="text/javascript"
	src="${ctx}/aris/statics/ace/plugins/jquery/jquery.validate.min.js"></script>

<script type="text/javascript"
	src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>

<script type="text/javascript"
	src="${ctx}/aris/statics/ace/plugins/chosen/chosen.jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/aris/statics/pages/js/workstation/patientRegCancel.js"></script>

<!-- 盖掉bootstrap样式 -->
<style type="text/css">
label.help-block-new {
	display: block;
	margin-top: -30px;
	margin-left: 180px;
	margin-bottom: 10px;
	color: #ff7777
}

.main-content h1 {
	background: #B9F1DE;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	height: 34px;
	color: #09245F;
	font-size: 16px;
	text-indent: 10px;
	line-height: 34px;
}

/*库存数量详情 表单 begin*/
.main-content .fM_listTable2 {
	margin: auto;
	background: #fff;
	width: 100%;
	border-collapse: separate;
	border-left: 1px solid #eee;
	border-top: 1px solid #eee;
	overflow-x: auto;
	clear: both;
	font-size: 14px;
	margin-bottom: 20px;
}

.main-content .fM_listTable2 th {
	border-bottom: 1px solid #eee;
	border-right: 1px solid #eee;
	height: 18px;
	padding: 10px 10px;
	text-align: center;
	background: #f8f8f8;
	color: #777;
	font-weight: 700;
	border-bottom: 1px solid #eee;
}

.main-content .fM_listTable2 td {
	border-bottom: 1px solid #eee;
	border-right: 1px solid #eee;
	line-height: 22px;
	padding: 10px 3px; /*text-align:center;*/
	vertical-align: top;
	color: #444;
}

.main-content .col-bt-right {
	text-align: right;
	margin-right: 20px;
}

.col-xs-2 {
	width: 9%;
}

.col-xs-3 {
	width: 24%;
}

.row {
	margin-right: 0px;
	margin-left: 0px;
}
</style>


</head>
<body>

	<div class="thickdiv" style="display: none; height: 100%;"></div>
	<div class="main-content">
		<input type="hidden" id="studyinfoId" name="studyinfoId"
			value="${studyinfoId }" />
		<form class="form-horizontal" id="patientRegForm">
			<input type="hidden" name="orgId" id="orgId" /> <input type="hidden"
				name="locId" id="locId" value="${locId }" /> <input type="hidden"
				id="eConsultorg" value="${studyConsultorg }" /> <input
				type="hidden" id="eConsultloc" value="${studyConsultloc }" /> <input
				type="hidden" id="isNew" name="isNew" value="${isNew }" />
			<div class="page-content min_style" style="padding:0px 0px 0px;">
				<div class="row">

					<h1>病人基本信息</h1>

					<div class="col-xs-12 col-sm-12 col-md-12">

						<div class="form-group">
							<label class="col-xs-2 control-label no-padding-right">
								病人ID： </label>
							<div class="col-xs-3">
								<input type="hidden" name="patientId" id="patientId"
									class="inputW160" value="${patientId }" /> <input
									name="patientGlobalid" id="patientGlobalid" class="inputW160"
									readonly="readonly" value="${patientGlobalid }" />
							</div>
							<label class="col-xs-2 control-label no-padding-right">病人姓名：
							</label>
							<div class="col-xs-3">
								<input name="patientName" id="patientName" class="inputW160"
									onblur="replace(this);ename();getAllReg(this);"
									value="${patientInfo.patientName }"
									<c:if test="${isNew == 'n'}">readonly="readonly"</c:if> />
							</div>
							<label class="col-xs-2 control-label no-padding-right">
								拼音： </label>
							<div class="col-xs-3">
								<input name="patientNamepy" id="patientNamepy" class="inputW160"
									readonly="readonly" value="${patientInfo.patientNamepy }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2 control-label no-padding-right">
								性别： </label>
							<div class="col-xs-3" id="sexCol">
								<select id="patientSex" name="patientSex" class="inputW160"
									value="${patientInfo.patientSex }"
									<c:if test="${isNew == 'n'}">disabled="disabled"</c:if>>
									<option value="1"
										<c:if test="${patientInfo.patientSex ==1}">selected</c:if>>男</option>
									<option value="2"
										<c:if test="${patientInfo.patientSex ==2}">selected</c:if>>女</option>
								</select>
							</div>
							<label class="col-xs-2 control-label no-padding-right">
								生日： </label>
							<div class="col-xs-3">
								<input id="patientDob" name="patientDob" class="inputW160"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
									onchange="getAge(this)"
									value="<fmt:formatDate value='${patientInfo.patientDob}' pattern='yyyy-MM-dd'/>"
									<c:if test="${isNew == 'n'}">readonly="readonly"</c:if> />
							</div>
							<label class="col-xs-2 control-label no-padding-right">
								年龄： </label>
							<div class="col-xs-3">
								<input type="hidden" name="patientAge" id="patientAge"
									class="inputW80"
									value="${fn:substring(patientInfo.patientAge, 0, fn:length(patientInfo.patientAge)-1)}" />
								<input name="patientAgeShow" id="patientAgeShow"
									class="inputW80"
									value="${fn:substring(patientInfo.patientAge, 0, fn:length(patientInfo.patientAge)-1)}"
									<c:if test="${isNew == 'n'}">readonly="readonly"</c:if> /> <select
									id="patientAgeUnit" name="patientAgeUnit" class="inputW80"
									<c:if test="${isNew == 'n'}">disabled="disabled"</c:if>>
									<option value="1"
										<c:if test="${fn:substring(patientInfo.patientAge, fn:length(patientInfo.patientAge)-1,1)=='岁'}">selected</c:if>>岁</option>
									<option value="2"
										<c:if test="${fn:substring(patientInfo.patientAge, fn:length(patientInfo.patientAge)-1,1)=='月'}">selected</c:if>>月</option>
									<option value="3"
										<c:if test="${fn:substring(patientInfo.patientAge, fn:length(patientInfo.patientAge)-1,1)=='天'}">selected</c:if>>天</option>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="col-xs-2 control-label no-padding-right">
								电话： </label>
							<div class="col-xs-3">
								<input name="patientPhone" id="patientPhone" class="inputW160"
									value="${patientInfo.patientPhone }" />
							</div>
							<label class="col-xs-2 control-label no-padding-right">
								手机： </label>
							<div class="col-xs-3">
								<input name="patientMobile" id="patientMobile" class="inputW160"
									value="${patientInfo.patientMobile }" />
							</div>
							<label class="col-xs-2 control-label no-padding-right">
								医保号： </label>
							<div class="col-xs-3">
								<input name="patientMedicareid" id="patientMedicareid"
									class="inputW160" value="${patientInfo.patientMedicareid }"
									<c:if test="${isNew == 'n'}">readonly="readonly"</c:if> />
							</div>
						</div>

						<div class="form-group">
							<label class="col-xs-2 control-label no-padding-right">
								就诊卡： </label>
							<div class="col-xs-3">
								<input name="patientCardid" id="patientCardid" class="inputW160"
									value="${patientInfo.patientCardid }"
									<c:if test="${isNew == 'n'}">readonly="readonly"</c:if> />
							</div>
							<label class="col-xs-2 control-label no-padding-right">
								身份证： </label>
							<div class="col-xs-3">
								<input name="patientIdnumber" id="patientIdnumber"
									class="inputW160" value="${patientInfo.patientIdnumber }"
									<c:if test="${isNew == 'n'}">readonly="readonly"</c:if> />
							</div>
							<label class="col-xs-2 control-label no-padding-right">
								备注： </label>
							<div class="col-xs-3">
								<input name="patientRemark" id="patientRemark" class="inputW160"
									value="${patientInfo.patientRemark }"
									<c:if test="${isNew == 'n'}">readonly="readonly"</c:if> />
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="clearfix form-actions">
				<div class="col-bt-right">
					<button class="btn btn-primary" type="button" id="saveBtn">
						<i class="ace-icon fa fa-check bigger-110 icon-save"></i> 确定
					</button>
					<button class="btn btn-gray" type="button" id="reset"
						onclick="closeDiv();">
						<i class="ace-icon fa fa-check bigger-110 icon-remove"></i> 取消
					</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
