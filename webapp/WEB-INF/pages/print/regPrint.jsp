<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/print/regPrint.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery.jqprint-0.3.js"></script>
    
</head>
 <body class="body">
<div class="main-content" >
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12" id="pInfo">
               <table border="0" cellspacing="0" cellpadding="0"  align="center">
                <tbody>  
                <tr>
                  <td >
                     <font size="3">病人ID：${regInfoBean.patientId}</font>
                </td>
                </tr>
                <tr>
                  <td >
                     <font size="3">姓名：${regInfoBean.patientName }   性别：${regInfoBean.patientSex }  </font>
                </td>
                <tr>
                <tr>
                  <td ><font size="2">年龄：${regInfoBean.patientAge }  检查号：${regInfoBean.studyAccnumber }</font></td>
                </tr>
                <tr>
                  <td ><font size="1">检查项目：${regInfoBean.studyitemDesc}</font></td>
                </tr> 
                <tr>
                  <td> <font size="1">${regInfoBean.orgName }</font></br><img style="vertical-align:middle;" width="220px" height="50px" src="${ctx}/aris/common/checkCode/barCode?ids=${regInfoBean.studyAccnumber }" /></td>
                </tr>
              </tbody>
              </table>
			</div> 
       
		</div>	
		<div class="col-bt-center">
               <button type="button" class="btn btn-primary" id="printBtn">打 印</button>
               <button type="button" class="btn btn-pink" id="closeBtn">关 闭</button> 
        </div>
	</div> 
</div>

 </body>
</html>
