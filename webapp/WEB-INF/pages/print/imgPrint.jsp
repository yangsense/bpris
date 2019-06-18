<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %> 
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery.jqprint-0.3.js"></script>
    
	<script type="text/javascript" language="javascript">   

	/**
	*  未捕获引发SecurityError：无法读取“HTMLIFrameElement”的“contentDocument”属性：
	*  访问一帧的起源“ftp://10.63.90.29”阻止与来源“http://127.0.0.1:8080”的框架。
	*  帧请求获得了“HTTP”的协议，被访问的框架有“FTP”的协议。协议必须匹配。 iFrameHeight用不了。
	*/
	
	function iFrameHeight() {   
		var ifm= document.getElementById("iframepage");   
		var subWeb = document.frames["iframepage"].document;   
		if(ifm != null && subWeb != null) {
		   ifm.height = subWeb.body.scrollHeight;
		   ifm.width = subWeb.body.scrollWidth;
		}   
	 }
 
	 
	function logout(){
        //if(confirm("确定要退出吗？")){
                 var browserName=navigator.appName;
                 if (browserName=="Netscape"){
                       window.open('', '_self', '');
                       window.close();
                 }
                 if (browserName=="Microsoft Internet Explorer") { 
                       window.parent.opener = "whocares"; 
                       window.parent.close(); 
                 }
        //}
}

	function print(){
		$("#pInfo").jqprint();	
		
		//上传所有影像到服务器
		
		
		
	}   
	</script>
</head>

<body class="body" style="margin-left:10px;">
<input type="hidden" id="reportId" value="${reportId }"/>

<div class="page-content" style="text-align:right">
       <button type="button" class="btn btn-primary" onclick="print()">打 印</button>
       <button type="button" class="btn btn-pink"  onclick="logout()">关 闭</button> 
 </div>
<div  id="pInfo" style="text-align:center">    
	 ${imgHtml}
</div>
 
 <div class="page-content" style="text-align:right">
       <button type="button" class="btn btn-primary" onclick="print()">打 印</button>
       <button type="button" class="btn btn-pink" onclick="logout()">关 闭</button> 
 </div>  

   
 </body>
</html>
