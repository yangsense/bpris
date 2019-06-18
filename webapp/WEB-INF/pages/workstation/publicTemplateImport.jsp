<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/messages_cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/templateimport.js"></script>
</head>
<style type="text/css">
.load2 {padding:15px 0;text-align:center;font-size:14px;background:#ffffff;height: 25px;}
@-webkit-keyframes loading {
  from {opacity: 1;}
  to {opacity: 0.2;}
}
.loading {display: inline-block;margin-right:20px;position: relative;width: 12px;height:12px;vertical-align:7px;}
.loading span {display:inline-block;width: 20%;height: 40%;color:#ff7800;background: #ff7800;position: absolute;left: 100%;top: 100%;opacity: 0;-webkit-animation: loading 0.9s linear infinite;-webkit-border-radius: 30px;}
.loading .bar1 {-webkit-transform:rotate(0deg) translate(0, -142%); -webkit-animation-delay: 0s;color:#ff7800;}
.loading .bar2 {-webkit-transform:rotate(30deg) translate(0, -142%); -webkit-animation-delay: -0.9167s;color:#ff7800;}
.loading .bar3 {-webkit-transform:rotate(60deg) translate(0, -142%); -webkit-animation-delay: -0.833s;color:#ff7800;}
.loading .bar4 {-webkit-transform:rotate(90deg) translate(0, -142%); -webkit-animation-delay: -0.75s;color:#ff7800;}
.loading .bar5 {-webkit-transform:rotate(120deg) translate(0, -142%); -webkit-animation-delay: -0.667s;color:#ff7800;}
.loading .bar6 {-webkit-transform:rotate(150deg) translate(0, -142%); -webkit-animation-delay: -0.5833s;color:#ff7800;}
.loading .bar7 {-webkit-transform:rotate(180deg) translate(0, -142%); -webkit-animation-delay: -0.5s;color:#ff7800;}
.loading .bar8 {-webkit-transform:rotate(210deg) translate(0, -142%); -webkit-animation-delay: -0.41667s;}
.loading .bar9 {-webkit-transform:rotate(240deg) translate(0, -142%); -webkit-animation-delay: -0.333s;color:#ff7800;}
.loading .bar10 {-webkit-transform:rotate(270deg) translate(0, -142%); -webkit-animation-delay: -0.25s;color:#ff7800;}
.loading .bar11 {-webkit-transform:rotate(300deg) translate(0, -142%); -webkit-animation-delay: -0.1667s;color:#ff7800;}
.loading .bar12 {-webkit-transform:rotate(330deg) translate(0, -142%); -webkit-animation-delay: -0.0833s;color:#ff7800;}
</style>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form method="post" name="uploadTemplateForm" id="uploadTemplateForm"  action="../Aris/TemplateUploadFile"
						enctype="multipart/form-data" class="form-horizontal" onsubmit="return checkForm()">	
					<input type="hidden" id="importType" name="importType" value="${importType}">
					<input type="hidden" id="locId" name="locId" value="${locId}">	
					<input type="hidden" id="orgId" name="orgId" value="${orgId}">
					<input type="hidden" id="operatorId" name="operatorId" value="${operatorId}">
					<input type="file" name="file"  onchange="checkAndSubmit(this)">	
					<input type="submit" id="saveImport" value="提交">
				</form>
			</div>
		</div>	
	</div>
	
	<div class="load2" id="loadDiv" style="display:none;">
	<label>正在导入</label>
						<span class="loading">
						<span class="bar1"></span>
						<span class="bar2"></span>
						<span class="bar3"></span>
						<span class="bar4"></span>
						<span class="bar5"></span>
						<span class="bar6"></span>
						<span class="bar7"></span>
						<span class="bar8"></span>
						<span class="bar9"></span>
						<span class="bar10"></span>
						<span class="bar11"></span>
						<span class="bar12"></span>
    					
					</div>	
</div>
 </body>
</html>
