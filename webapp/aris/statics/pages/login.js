$(function () {
    /*登陆*/
//    $("#loginSubmit").click(function () {
//        login($(this).attr("name"));
//      
//    });
    
  //登录回调
    loginBack();

    //解决ie下不支持placeholder（占位符）问题
	placeholder();
	
	//图片验证码切换
	$("[name='imageCheckCode']").click(function(){
  		var url = $(this).attr("src");
  		if(url.indexOf("?") != -1){
  			$(this).attr("src",$(this).attr("src") + "&nocache=" + new Date().getTime());
  		}else{
      		$(this).attr("src",$(this).attr("src") + "?nocache=" + new Date().getTime());
  		}
	});
	if(GLOBAL.SYSTEM==-1){
		$("#hostitle").text("医院归属SYS_PREFIX未配置");	
	}else{
		$("#hostitle").text(GLOBAL.SYSTEM);	
	}
	
});
 
 

//解决ie下不支持placeholder（占位符）问题
function placeholder(){
	var doc=document,inputs=doc.getElementsByTagName('input'),supportPlaceholder='placeholder'in doc.createElement('input'),placeholder=function(input){var text=input.getAttribute('placeholder'),defaultValue=input.defaultValue;
    if(defaultValue==''){
        input.value=text}
        input.onfocus=function(){
            if(input.value===text){this.value=''}};
            input.onblur=function(){if(input.value===''){this.value=text}}};
            if(!supportPlaceholder){
                for(var i=0,len=inputs.length;i<len;i++){var input=inputs[i],text=input.getAttribute('placeholder');
                if(input.type==='text'&&text){placeholder(input)}}}
}

// 电话号码验证
function checkMobile(str) {
   return new RegExp("^0?(13|15|18|14|17)[0-9]{9}$").test(str);
}

//用户登陆验证
function login(tab) {
	var loginFlag = true;
    var accountName = $("div#" + tab + " input[name='accountName']").val();//用户名
    var password = $("div#" + tab + " input[name='passwd']").val();//密码
    if(GLOBAL.SYSTEM==""||GLOBAL.SYSTEM==-1){
    	$("#alt_" + tab).html("<i></i>医院归属SYS_PREFIX未配置，请配置后登陆");
    	return false;
    }
    //用户名密码验证
    if (accountName == "" || accountName=="用户名/邮箱/手机号") {
        $("#alt_" + tab).html("<i></i>请输入用户名!");
        $("div#" + tab + " input[name='accountName']").focus();
        loginFlag = false;
        return false;
    }
    if (password == "") {
        $("#alt_" + tab).html("<i></i>请输入密码!");
        $("div#" + tab + " input[name='passwd']").focus()
        loginFlag = false;
        return false;
    }

    var checkCode = $("div#" + tab + " input[name='imageCode']").val();//验证码

    if( checkCode==""){
        $("#alt_" + tab).html("<i></i>请输入验证码!");
        $("div#" + tab + " input[name='imageCode']").focus();
        loginFlag = false;
        return false;
    }   
    if(loginFlag){ 
    	var url =  GLOBAL.WEBROOT+"/aris/statics/common/images/loading.gif";
        $("#alt_" + tab).html(" <img src='"+url+"' style='width:16px;height:16px'>校验中,请稍候..");     
        $("#loginForm").submit();
        
    }  
 
}

//操作成功后返回值的操作
function loginBack() {
    var errorCode = $("#errorCode").val(); 
    console.log("errorCode===>",errorCode);
    if(errorCode == "0" || errorCode == ""){
        return;
    }
    var tab = getSelectedTab();
    switch (errorCode) {
        case "-2" :
        {
        	$("#alt_normal").html("<i></i>验证码错误,请重新输入!");
            break; 
        } 
        case "-1" :
        {
        	$("#alt_normal").html("<i></i>用户名或密码错误!"); 
            break;
        } 
        case "-3" :
        {
        	$("#alt_normal").html("<i></i>服务异常,请稍后再试!"); 
            break;
        }
        case "-4" :
        {
        	$("#alt_normal").html("<i></i>登录异常,请稍后再试!"); 
            break;
        }
        case "-5" :
        {
          /*  $("#alt_normal").html("<i></i>很抱歉，您的秘钥已过期，请使用新秘钥激活后使用!");*/
            //window.location.href=GLOBAL.WEBROOT+"/bpris/initUseRight.html";
            var index = layer.open({
                type: 2,
                title:"秘钥激活",
                area: ['500px', '300px'],
                fix: false, //不固定
                maxmin: false,
                content: GLOBAL.WEBROOT + "/bpris/initUseRight.html",
            });
            break;
        }
        case "-6" :
        {
        	$("#alt_normal").html("<i></i>用户已被停用!"); 
            break;
        }
        default  :
        {
        	$("#alt_normal").html("<i></i>系统忙,请稍候再试!错误代码:" + errorCode);  
        }
    }
}

//回车键盘监听事件
//document.onkeydown = keyListener;
//function keyListener(e) {
//    e = e ? e : event;
//    if (e.keyCode == 13) {
//
//        login(getSelectedTab());
//    }
//}
/*获取被选中的tab的名称*/
function getSelectedTab() {
    var tabSelected = "";
    var tabs = $(".fl loginList");
    for (i = 0; i < tabs.length; i++) {
        if (tabs[i].style.display == "" || tabs[i].style.display == "block") {
            tabSelected = tabs[i].id;
        }
    }
    return tabSelected;
}

//重填
function cleanAll() {
    $("#normalRegistName").val("");
    $("#normalPass").val("");
//    $("#savePass").attr("checked", false);
}

//设置cookie
function setCookie(O,o,l,I){
   var i="",c="";
   if(l!=null){
     i=new Date((new Date).getTime()+l*3600000);
     i="; expires="+i.toGMTString();
   }
   if(I!=null){
     c=";domain="+I;
   }
   document.cookie=O+"="+escape(o)+i+c;
 }
 
 //获得cookie
function getCookie(l){
  var i="",I=l+"=";
  if(document.cookie.length>0){
     offset=document.cookie.indexOf(I);
     if(offset!=-1){
        offset+=I.length;
        end=document.cookie.indexOf(";",offset);
        if(end==-1)end=document.cookie.length;
        i=unescape(document.cookie.substring(offset,end));
      }
   };
   return i;
}
 
//从cookie中取值回填
$(function(){ 
	if(getCookie("user") !="" && getCookie("pwd") !=""){
		$("#chkUserPsw").attr("checked",true); 
	    $("#normal").find("input[name=accountName]").val(getCookie("user"));
	    $("#normal").find("input[name=passwd]").val(getCookie("pwd"));
	}else{
		$("#chkUserPsw").attr("checked",false); 
	}	
  
});
