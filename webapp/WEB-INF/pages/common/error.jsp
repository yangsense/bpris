<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <%@ include file="/common/meta.jsp" %>
<title>共同异常处理页面</title>
<style type="text/css">
body {
    background: none repeat scroll 0 0 #F4F4F4;
    font-family: "微软雅黑";
    font-size: 12px;
}

.warpDiv{ margin-top: 10px; min-width: 1000px;}
.error_boxdiv {
    background: url("${ctx}/aris/statics/common/images/errow_bj_02.jpg") no-repeat scroll center top transparent !important;
    overflow: hidden;
}
.error_boxdiv1 {
    margin: 150px auto 0;
    width: 535px;
}
.error_boxdivleft { float: left; margin-right: 25px; margin-top: 25px;}
.error_boxdivright {
    float: left;
    width: 355px;
}
.error_wenzi {
    background: url("${ctx}/aris/statics/common/images/errow_yun_06.png") no-repeat scroll 0 0 transparent !important;
    color: #3F3F3F;
    font-family: "微软雅黑";
    font-size: 16px;
    height: 176px;
    padding-left: 22px;
    padding-right: 30px;
    padding-top: 30px;
}
.error_fanhuibut { float: right; margin-right: 60px; width: 84px;}
.error_prompt {
    height: 215px;
}
</style>
</head>
<body style="overflow-x:hidden;overflow-y:hidden">
<div class="warpDiv">
<div class="error_boxdiv">
  <div class="error_boxdiv1">
    <div class="error_prompt">
      <div class="error_boxdivleft"><img src="${ctx}/aris/statics/common/images/errow_pic_06.png" width="148" height="190" /></div>
      <div class="error_boxdivright">
        <div class="error_wenzi">
          <p style="font-family:'宋体'; color:#d7191f; font-size:12px; font-weight:bold;">
          	<c:if test="${errorMsg == null || errorMsg == ''}">
          		系统异常，请稍后再试...
          	</c:if>
          	<c:if test="${errorMsg != null || errorMsg != ''}">
          		${errorMsg}
          	</c:if>
          <br/>
            </p>
          </div>
        </div>
         </div>
         <!-- 
        <div class="error_fanhuibut">
			<input type="button" class="redbutton" value="关　闭" onclick="javascript:window.close()" />
		</div>
		 -->
  </div>
</div>
</div>
<script type="text/javascript">
	function toReturn(){
	}
	
</script>
</body>
</html>