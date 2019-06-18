<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/worklistDetail.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="studyInfoForm" >
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 状态： </label>
                        <div class="col-xs-3">
                            <input  class="inputW160" type="text" id="studystatusCode" name="studystatusCode" value="${studyInfoBean.studystatusCode}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 有图像： </label>
                        <div class="col-xs-3">
                           <c:if test="${studyInfoBean.studyHaveimage==0}">
                              <input  class="inputW160" type="text" id="studyHaveimage" name="studyHaveimage" value="无"/>
                           </c:if> 
                           <c:if test="${studyInfoBean.studyHaveimage==1}">
                              <input  class="inputW160" type="text" id="studyHaveimage" name="studyHaveimage" value="有"/>
                           </c:if>                           
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 有报告： </label>
                        <div class="col-xs-3">
                           <c:if test="${studyInfoBean.studyHavereport==0}">
                              <input  class="inputW160" type="text" id="studyHavereport" name="studyHavereport" value="无"/>
                           </c:if> 
                           <c:if test="${studyInfoBean.studyHavereport==1}">
                              <input  class="inputW160" type="text" id="studyHavereport" name="studyHavereport" value="有"/>
                           </c:if>  
                         </div>
                        <label class="col-xs-2 control-label no-padding-right"> 病人编号： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="patientId" name="patientId" value="${studyInfoBean.patientId}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 病人ID： </label>
                        <div class="col-xs-3">
                       		<input  class="inputW160" type="text" id="patientGlobalid" name="patientGlobalid" value="${studyInfoBean.patientGlobalid}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 病人姓名： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="patientName" name="patientName" value="${studyInfoBean.patientName}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 年龄： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="patientAge" name="patientAge" value="${studyInfoBean.patientAge}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 性别： </label>
                        <div class="col-xs-3">
                            <c:if test="${studyInfoBean.patientSex=='1'}">
                            <input  class="inputW160" type="text" id="patientSex" name="patientSex" value="男"/>
                            </c:if>
                            <c:if test="${studyInfoBean.patientSex=='2'}">
                            <input  class="inputW160" type="text" id="patientSex" name="patientSex" value="女"/>
                            </c:if>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 生日： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="patientDob" name="patientDob" value="${studyInfoBean.patientDob}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 检查项目： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="studyItemdesc" name="studyItemdesc" value="${studyInfoBean.studyItemdesc}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 申请机构： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="orgName" name="orgName" value="${studyInfoBean.orgName}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 申请科室： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="locDesc" name="locDesc" value="${studyInfoBean.locDesc}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 申请医生： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="studyAppdoc" name="studyAppdoc" value="${studyInfoBean.studyAppdoc}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 登记时间： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="studyOperationtime" name="studyOperationtime"  value="<fmt:formatDate value='${studyInfoBean.studyOperationtime}' pattern='yyyy-MM-dd HH:mm:ss'/>"     />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 预约时间： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="studyDatetime" name="studyDatetime" value="<fmt:formatDate value='${studyInfoBean.studyDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 检查开始时间： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="studyStarttime" name="studyStarttime" value="<fmt:formatDate value='${studyInfoBean.studyStarttime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right"> 检查结束时间： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="studyEndtime" name="studyEndtime" value="<fmt:formatDate value='${studyInfoBean.studyEndtime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 报告时间： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="reportDatetime" name="reportDatetime" value="${studyInfoBean.reportDatetime}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right"> 报告医生： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="reportDoctorname" name="reportDoctorname" value="${studyInfoBean.reportDoctorname}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"> 审核医生： </label>
                        <div class="col-xs-3">
                        	<input  class="inputW160" type="text" id="reportVerifydoctorname" name="reportVerifydoctorname" value="${studyInfoBean.reportVerifydoctorname}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                   		 <label class="col-xs-2 control-label no-padding-right"> 二次审核医生： </label>
                        <div class="col-xs-3">
                           <input  class="inputW160" type="text" id="reportFinaldoctorname" name="reportFinaldoctorname" value="${studyInfoBean.reportFinaldoctorname}"/>
                        </div>
                    </div>  
                                       
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">                            
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
