<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>报告浏览器</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
     <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link href="${ctx}/aris/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link href="${ctx}/aris/statics/pages/css/index/css/detail.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/inner.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>   
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/jquery.form.min.js"></script>   
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>   
    
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>  
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/chosen/chosen.jquery.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/reportBrowse.js"></script>
</head>
<style>
.reportBrow{}
.reportBrow textarea{ border:none;}
.reportBrow .reportBrow-left{background: #fff;  border:1px solid #ddd; padding: 5px 0 10px 0;width:32%;float:left;}
.reportBrow .reportBrow-left a{color: #666; font-size: 14px;}
.reportBrow .reportBrow-left ul li.active{font-weight: 700;}
.nav>li>a{padding:10px 5px;}
.reportBrow .reportBrow-left h6{background:#fff; line-height:30px; font-weight:500; height:30px;font-size:14px; border-bottom:1px solid #dbdbdb; text-indent: 10px; font-weight: 700;}

.reportBrow .reportBrow-left dl{ height: 400px; overflow: auto;}
.reportBrow .reportBrow-left dl h5{background:#fff; line-height:30px; font-weight:500; height:30px;font-size:14px; border-bottom:1px solid #dbdbdb; text-indent: 30px;}
.reportBrow .reportBrow-left dl h5 a{ color:#333; display:block; text-decoration: none;}
.reportBrow .reportBrow-left dl h5 a:hover,.reportBrow .reportBrow-left dl h5 a.current{ color:#333; display:block ;background-color:#eee;;}
.reportBrow .reportBrow-left dl h5 i{ font-style:normal;}
.reportBrow .reportBrow-left dl dt{ margin-left:15px;}
.reportBrow .reportBrow-left dl dd{ margin-left:15px;}
.reportBrow .reportBrow-left dl dt a{ display:block; width:100%; border-bottom:1px solid #dbdbdb; color:#333;  height:34px; font-size:14px; line-height:34px; text-indent:30px; color:#666; font-weight: 500;}
.reportBrow .reportBrow-left dl dt a:hover,.reportBrow .reportBrow-left dl dt a.current{ background-color:#eee; }
.reportBrow .reportBrow-left dl dd a{ display:block; width:100%; border-bottom:1px solid #dbdbdb; color:#333; background-position: 30px 10px; height:34px; font-size:14px; line-height:34px; text-indent:40px; color:#666;}
.reportBrow .reportBrow-left dl dd a:hover,.reportBrow .reportBrow-left dl dd a.current{ background-color:#eee;}


.reportBrow .reportBrow-right{width:66%;float:right;}
.reportBrow .reportBrow-right .reportBrow-right-content{ background: #fff; border:1px solid #ddd; }
.reportBrow .reportBrow-right .reportBrow-right-content h4{background:#fff; line-height:50px; font-weight:500; height:50px;font-size:16px; border-bottom:1px solid #dbdbdb; text-indent: 10px; font-weight: 700;}
.reportBrow .reportBrow-right .reportBrow-right-content h6{background:#fff; line-height:30px; font-weight:500; height:30px;font-size:14px; border-bottom:1px solid #dbdbdb; text-indent: 10px; font-weight: 700;}
 
.reportBrow .reportBrow-center{}
.reportBrow .reportBrow-center .reportBrow-right-content{ background: #fff; border:1px solid #ddd; }
.reportBrow .reportBrow-center .reportBrow-right-content h4{background:#fff; line-height:50px; font-weight:500; height:50px;font-size:16px; border-bottom:1px solid #dbdbdb; text-indent: 10px; font-weight: 700;}
.reportBrow .reportBrow-center .reportBrow-right-content h6{background:#fff; line-height:30px; font-weight:500; height:30px;font-size:14px; border-bottom:1px solid #dbdbdb; text-indent: 10px; font-weight: 700;}
 
</style>
<script type="text/javascript">

$(document).ready(function() {

   CKEDITOR.config.readOnly = true;

});

</script>
<body>
<form id="searchForm" action="#">
<input type="hidden" id="patientId" value="${reportBean.patientId}"/>
<input type="hidden" id="patientOutpatientid" value="${reportBean.patientOutpatientid}"/>
<input type="hidden" id="patientInpatientid" value="${reportBean.patientInpatientid}"/>
<input type="hidden" id="orgId" value="${reportBean.orgId}"/>
</form>
<div  id="bodyContent">
        <form class="form-horizontal" role="form" id="reportBrowForm">
        <!-- 审核成功状态 -->
        <input type="hidden" id="auditStatus"/> 
        <!-- 报告单状态 -->
        <div class="r_list" style="margin-left:24px;margin-right:12px;"> 
            
            <div class="reportBrow m-t-20">

                <div class="col-lg-6 reportBrow-left " style="padding:5px;height: 600px;">
                    <table id="reportlist" width="100%" border="0" cellspacing="0" cellpadding="0">
		   
					</table>
					<div id="report_pager"></div>
                </div>
                
                <div class="col-lg-6 reportBrow-right">
                    <div class="reportBrow-right-content">
                        <h6><span style="text-align:center;"> 
                        	 ID：${reportBean.patientId}  &nbsp;&nbsp;&nbsp;
                                                    姓名：${reportBean.patientName}	  &nbsp;&nbsp;&nbsp;
                                                   性别：${reportBean.patientSex}    &nbsp;&nbsp;&nbsp;
                                                   年龄： ${reportBean.patientAge}	  &nbsp;&nbsp;&nbsp;  
                                                   机构名称：<span id="orgName">${reportBean.orgName}</span>       &nbsp;&nbsp;&nbsp;
                             </span></h6>
                        <h6>检查所见</h6> 
                        <div class="reportBrow-show" id="editorExam">
                             <textarea id="reportExam" cols="5" rows="5" isRich="true" name="reportExam" class="ckeditor" >
                             ${reportBean.reportExam}
                             </textarea> 
                        </div>
                        <h6>诊断意见</h6> 
                        <div class="reportBrow-show" id="editorResult">
                            <textarea id="reportResult" cols="5" rows="5" isRich="true" name="reportResult" class="ckeditor"> 
                            ${reportBean.reportResult}
                            </textarea>
                        </div>
                        <h6>备注</h6> 
                        <div class="reportBrow-show" >
                            <textarea id="reportRemark" cols="50" rows="3" name="reportRemark" style="font-family: Arial, Verdana, sans-serif;font-size: 24px;color: #222;">${reportBean.reportRemark}</textarea>
                        </div>
                    </div>
                    	<table width="100%" style="margin-top: 5px;">
                    		<tr style="font-family: Arial, Verdana, sans-serif;font-size: 24px;color: #222;">
                    			<td>报告医生：<span id="reportDoctorname">${reportBean.reportDoctorname}</span></td>
                    			<td>审核医生：<span id="reportVerifydoctorname">${reportBean.reportVerifydoctorname}</span></td>
                    			<td>报告日期：<span id="reportDatetime"><fmt:formatDate value="${reportBean.reportDatetime}" pattern="yyyy-MM-dd hh:mm:ss"/></span></td>
                    		</tr>
                    	</table>
                </div>
            </div>
    <!--右侧内容 end-->
</div>
</form>
</div> 
</body>
</html>
