var orgCode = top.org.orgCode;
var rootOrgId = top.rootOrgId;
var operatorName = top.user.operatorName;
var operatorCode = top.user.operatorCode;
var userStation = top.userStation;
$(function () {
    var stationCode = $("#id").val();
    init(stationCode);
    $("select").select2();
    $("#closeBtn").click(closeLayer);

    $("#saveBtn").click(function(){
        $(this).attr({"disabled":"disabled"});
        $("#editForm").submit();
    });


    $("#editForm").validate({

        // 非空校验
        rules : {
            'stationCode' : {
                required : true
            },
            'stationName' : {
                required : true
            }
        },
        messages: {
            'stationCode': {
                required: '输入内容不能为空',
            },
            'stationName': {
                required: '输入内容不能为空',
            }
        },
        submitHandler : save
    });
});

//新增
function init(id) {
    if (id){
        edit(id);
    }
}

function edit(id) {
    var data = "id=" + id;
    $.ajax({
        type: "GET",
        async: false,
        url: GLOBAL.WEBROOT + "/sysmanage/sysstation/getStation",
        cache:false,
        dataType: 'json',
        data: data,
        success: function (data) {
            if (data.ERRCODE == "0") {
                setForm(data);
            }
            else {
                info('温馨提示',data.ERRINFO);
            }
        }
    });
}

function setForm(data) {
    $("#stationCode").val(data.stationcode);
    $("#stationCode").attr("readonly","readonly")
    $("#stationName").val(data.stationname);
    $("#remark").text(data.remark);
}

function save() {
    displayExecuteLayer();
    var data = $("#editForm").serializeObject();
    data = $.toJSON(data);
    var url;
    if($('#stationCode').attr("readonly")=='readonly'){//修改
        url = GLOBAL.WEBROOT + "/sysmanage/sysstation/updateStation";
    }else{
        url = GLOBAL.WEBROOT + "/sysmanage/sysstation/insertStation";
    }
    $.ajax({
        type: "POST",
        async: true,
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        data: data,
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