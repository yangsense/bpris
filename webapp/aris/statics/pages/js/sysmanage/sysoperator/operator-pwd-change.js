$(function () {
    $("select").select2();
    $("#closeBtn").click(closeLayer);

    $("#saveBtn").click(function(){
        $(this).attr({"disabled":"disabled"});
        $("#editForm").submit();
    });


    $("#editForm").validate({

        // 非空校验
        rules : {
            'newPwd' : {
                required : true
            },
            'newPwdConfirm' : {
                required : true
            }
        },
        messages: {
            'newPwd': {
                required: '输入内容不能为空',
            },
            'newPwdConfirm': {
                required: '输入内容不能为空',
            }
        },
        submitHandler : save
    });
});


function save() {
    displayExecuteLayer();
    var operatorCode = $("#operatorCode").val();
    var newPwd = $("#newPwd").val();
    var newPwdConfirm = $("#newPwdConfirm").val();
    if(newPwd!=newPwdConfirm){
        info('温馨提示','两次输入的新密码不同,请重输!');
        $("#newPwd").val("");
        $("#newPwdConfirm").val("");
        $("#newPwd").focus();
        return false;
    }

    var url = GLOBAL.WEBROOT + "/sysmanage/sysoperator/changeOperatorPwd?operatorCode="+operatorCode+"&newPwd="+newPwd;

    $.ajax({
        type: "POST",
        async: true,
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            hideExecuteLayer();
            if (data.ERRCODE == "0") {
                infoCall('温馨提示',"保存成功！",saveSuccess);
            }
            else {
                info('温馨提示',data.ERRINFO);
            }
        }
    });
}

function saveSuccess(){
    parent.reloadGrid();
    closeLayer();
}
function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}