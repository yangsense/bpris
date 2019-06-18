<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <script src="${ctx}/aris/statics/common/js/jquery-1.11.2.js"></script>
    <link href="${ctx}/aris/statics/pages/css/index/css/login.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/aris/statics/pages/css/index/css/icon.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/layer/layer.js"></script>
    <script language="javascript" src="${ctx}/aris/statics/pages/login.js?_dc=${staticVersion}"></script>
    <title>影像云平台</title>
    <script language="javascript">
        var GLOBAL = {
            WEBROOT: "${ctx}",
            SYSTEM : "${system}"
        };

        // 在被嵌套时就跳到上级
        if (top != window){
		    top.location.href = window.location.href;
	     }
	     if(GLOBAL.SYSTEM==""){
	     	window.location.href = GLOBAL.WEBROOT+"/bpris/login.html";
	     }

    </script>
    <style>
        body, html {
        	background: #2d3a4b;
        	font-family:Microsoft YaHei;
            /* background: #eefdf9; */
            
        }
    </style>
</head>

<body>

<!--top beign-->
<!-- <div class="wrap100 loginTopBg"> -->
	<!-- 登录logo -->
    <%-- <div class="wrap"><img src="${ctx}/aris/statics/pages/css/index/images/food_logo.png"></div> --%>
    
    

<!-- </div> -->
<!--top end-->

<!--loginContent begin-->

<%-- <div class="videocontainer">
	<video class="fullscreenvideo" poster="${ctx}/aris/statics/pages/css/index/img/login_bg.jpg" id="bgvid" playsinline="" autoplay="" muted="" loop="">
		<source src="${ctx}/aris/statics/pages/css/index/img/login_bg.mp4" type="video/mp4">
	</video>
</div> --%>

<div class="wrap loginContent">
    <%-- <div class="fl leftAdver"><img src="${ctx}/aris/statics/pages/css/index/images/leftAdver1.jpg"/></div> --%>
    <!--login begin-->
    <input type="hidden" name="errorCode" id="errorCode" value="${errorCode}"/>
    <input type="hidden" name="errorMessage" id="errorMessage" value="${errorMessage}"/>

    <form id="loginForm" action="${ctx}/bpris/index.html" method="post" onsubmit="return login('normal')">

        <div class="fl loginList" id="normal">
            <!-- <h5>登 录 (<span id="hostitle">未配置</span>)</h5> -->
            <h5>欢    迎    登     录 </h5>
            <ul>
                <li>
                	<i class="login_icon1"></i>
                     <input name="accountName" type="text" id="normalRegistName"
                       placeholder="请输入用户名" value="${registName}" class="login_input" size="35" autocomplete="off"/>
                </li>
                <li>
                <i class="login_icon2"></i>
                      <input name="passwd" type="password" id="normalPass" placeholder="请输入密码" class="login_input" size="35"/>
                </li>
            </ul>
            <ul class="prompt" id="alt_normal"> ${param.info}</ul>
            <div class="clear"></div>

            <div class="btn">
                <button type="submit" id="loginSubmit" name="normal">登 录</button>
                <!-- <button type="reset" onclick="cleanAll()"><i class="reset_icon"></i>重 置</button> -->
            </div>
        </div>
    </form>
    <!--login end-->

    <div class="cl"></div>
    <!--清除漂浮-->
</div>
<!--loginContent end-->


<!--copyright begin-->
<!--copyright end-->

</body>
</html>
