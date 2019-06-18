$(function () {
    $("#closeBtn").click(function(){
        closeDiv();
    });

    $("#changeUserPdForm").validate({

        // 非空校验
        rules : {
            operatorCode:{
                required:true
            },
            operatorName:{
                required:true
            },
            password:{
                required:true
            },
            newPassword:{
                required:true
            }
        },
        errorElement:'em',
        messages: {
        },
        submitHandler : save
    });

    $("#saveBtn").click(function(){
        $("#changeUserPdForm").submit();
    });
});
function save() {
    //验证

    //提交
    var data = $("#changeUserPdForm").serializeObject();
    data = $.toJSON(data);
    var operatorCode = $("#operatorCode").val() ;
    var password = $("#password").val() ;
    var newPassword = $("#newPassword").val() ;
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + "/sysmanage/sysoperator/updateOperatorPwd?operatorCode="+operatorCode+"&newPassword="+newPassword+"&password="+password,
        dataType: 'json',
        data: data,
        success: function (data) {
            if (data.ERRCODE == "0") {
                layer.msg('修改成功', {icon: 1});
                setTimeout(out,1500);
            }else if(data.ERRCODE == "-1"){
            	layer.msg('新密码不能为空!', {icon: 5});
            }else if(data.ERRCODE == "-2"){
            	layer.msg('找不到操作员!'+operatorCode, {icon: 5});
            }else if(data.ERRCODE == "-3"){
            	layer.msg('原密码不正确,请重输!', {icon: 5});
            }else {
                layer.msg('修改密码异常，请联系管理员!', {icon: 5});
            }
        }
    });
}

function out(){
	window.location.href = GLOBAL.WEBROOT+"/bpris/logout.html";
}

function closeDiv(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}