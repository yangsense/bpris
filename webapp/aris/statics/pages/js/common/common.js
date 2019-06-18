//设置cookies
function setCookies(name, value, Hours) {
    var exp = new Date();
    exp.setTime(exp.getTime() + Hours * 60 * 60 * 1000);
    document.cookie = name + "=" + escape(value) + ";path=/;expires=" + exp.toGMTString()+";";
}
//获取cookies
function getCookies(name) {
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]);
    return null
}

//删除cookies
function delCookies(name){

    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookies(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}