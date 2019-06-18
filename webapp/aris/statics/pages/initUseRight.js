$(function () {
    //登录回调
    loginBack_rightCheck();
});

//用户登陆验证
function saveSecreKey() {
        var url =  GLOBAL.WEBROOT+"/aris/statics/common/images/loading.gif";
        $("#alt_normal").html(" <img src='"+url+"' style='width:16px;height:16px'>校验中,请稍候..");
        $("#verifyForm").submit();
}
//操作成功后返回值的操作
function loginBack_rightCheck() {
    var errorCode = $("#errorCode").val();
    var errorMessage = $("#errorMessage").val();
    if(errorMessage){
        layer.msg(errorMessage,{
            offset:['50%'],
            time: 2000 //2秒关闭（如果不配置，默认是3秒）
        });
       /* layer.msg(errorMessage,{
            offset:['50%'],
            time: 2000 //2秒关闭（如果不配置，默认是3秒）
        },function(){
            if(errorMessage=="秘钥验证成功"){
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        });*/
    }
   /* var styleHtml = '<i></i>'+errorMessage;
    $("#alt_normal").html(styleHtml);*/
}
//重填
function cleanAll() {
    $("#normalRegistName").val("");
    $("#normalPass").val("");
}
