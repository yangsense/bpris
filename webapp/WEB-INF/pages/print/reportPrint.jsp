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
	
	window.onload = function() {
	    var iframe = document.getElementById("iframepage");
	    iframe.style.width = window.innerWidth + 'px';
	    iframe.style.height = window.innerHeight + 'px';
	};
	
	 
	function logout(){
        //if(confirm("确定要退出吗？")){
                 var browserName=navigator.appName;
                 if (browserName=="Netscape"){
                       window.open('', '_self', '');
                       window.close();
                 }
                 if (browserName=="Microsoft Internet Explorer") { 
                       window.parent.opener = "whocares"; 
                       window.close(); 
                 }
        //}
}

	function print(){
		//document.getElementById("iframepage").contentWindow.document.getElementById("trc1").style.height="307";
		//document.getElementById("iframepage").contentWindow.document.getElementById("trc2").style.height="151";
		//$("#pInfo").jqprint();	
		//WebBrowser.ExecWB(6,6); 
		if(BrowserType()=="IE7"||BrowserType()=="IE8"){
			document.all.ShowForm.ExecWB(6, 6);
			setTimeout(function(){WebBrowser.execwb(45,1);},5000);
		}else if(BrowserType()=="IE9"||BrowserType()=="IE10"||BrowserType()=="IE11"){
			$("#pInfo").jqprint();
		}else{
		    $("#pInfo").jqprint();
		}
		
		//记录打印次数及打印状态
		$.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT+"/studyReport/setReportPrintInfo.ajax?reportId="+$("#reportId").val(),
	        success: function (data) {
	       		//logout();	
	        }
	    });
	}  
	//判断当前浏览类型  
  function BrowserType()  
  {  
      var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
      var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器  
      var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器  
      var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1 && !isIE; //判断是否IE的Edge浏览器  
      var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器  
      var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1; //判断是否Safari浏览器  
      var isChrome = userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1; //判断Chrome浏览器  
  
      if (isIE)   
      {  
           var reIE = new RegExp("MSIE (\\d+\\.\\d+);");  
           reIE.test(userAgent);  
           var fIEVersion = parseFloat(RegExp["$1"]);  
           if(fIEVersion == 7)  
           { return "IE7";}  
           else if(fIEVersion == 8)  
           { return "IE8";}  
           else if(fIEVersion == 9)  
           { return "IE9";}  
           else if(fIEVersion == 10)  
           { return "IE10";}  
           else if(fIEVersion == 11)  
           { return "IE11";}  
           else  
           { return "0"}//IE版本过低  
       }//isIE end  
         
       if (isFF) {  return "FF";}  
       if (isOpera) {  return "Opera";}  
       if (isSafari) {  return "Safari";}  
       if (isChrome) { return "Chrome";}  
       if (isEdge) { return "Edge";}  
   }//myBrowser() end  
     
   //判断是否是IE浏览器  
   function isIE()  
   {  
      var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
      var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器  
      if(isIE)  
      {  
          return "1";  
      }  
      else  
      {  
          return "-1";  
      }  
   }  
     
     
   //判断是否是IE浏览器，包括Edge浏览器  
   function IEVersion()  
   {  
      var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
      var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器  
	  var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1 && !isIE; //判断是否IE的Edge浏览器  
      if(isIE)  
      {  
           var reIE = new RegExp("MSIE (\\d+\\.\\d+);");  
           reIE.test(userAgent);  
           var fIEVersion = parseFloat(RegExp["$1"]);  
           if(fIEVersion == 7)  
           { return "IE7";}  
           else if(fIEVersion == 8)  
           { return "IE8";}  
           else if(fIEVersion == 9)  
           { return "IE9";}  
           else if(fIEVersion == 10)  
           { return "IE10";}  
           else if(fIEVersion == 11)  
           { return "IE11";}  
           else  
           { return "0"}//IE版本过低  
      }  
	  else if(isEdge)  
	  {  
		    return "Edge";  
	  }  
      else  
      {  
          return "-1";//非IE  
      }  
   }  
	</script>
</head>
<style media=print>
	.page-content{display:none;}
	.tbc{width: 679px;border: 0;align:center;}
	.trc1{height: 307px;}
	.trc2{width: 151px;}
</style>
<body class="body" style="margin-left:10px;"  >
<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height="0" id="WebBrowser" width="0"></OBJECT>
<input type="hidden" id="reportId" value="${reportId }"/>

<div class="page-content" style="text-align:right">
       <button type="button" class="btn btn-primary" onclick="print()">打 印</button>
       <button type="button" class="btn btn-pink"  onclick="logout()">关 闭</button> 
 </div>
<div  id="pInfo" style="text-align:center">    
<!-- 滚动条适应ie8 -->
	 <iframe id="iframepage" name="ShowForm" width="100%"  style="border:0px;width:100%;height:700px;" frameborder="0"  marginheight="0" marginwidth="0"  src="${filePath}"></iframe> 
</div>
 
 <div class="page-content" style="text-align:right">
       <button type="button" class="btn btn-primary" onclick="print()">打 印</button>
       <button type="button" class="btn btn-pink" onclick="logout()">关 闭</button> 
 </div>  

   
 </body>
</html>
