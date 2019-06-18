var orgCode = top.org.orgCode;
var rootOrgId = top.rootOrgId;
var operatorName = top.user.operatorName;
var operatorCode = top.user.operatorCode;
var userStation = top.userStation;

var isUpdate = 0;
$(function () {
    initQuery();
    var operatorCode = $("#operatorCodeId").val();
    if(operatorCode){  //修改
        isUpdate = 1;

    }
    if(isUpdate){
        $("#pswDiv").hide();
        init(operatorCode);
        var flag = selectOffice();
        selectOfficeInit();
    }


    $("select").select2();
    $("#closeBtn").click(closeLayer);

    $("#saveCheck").click(checkOperatorExists);
    $("#saveBtn").click(function(){
        saveOperator();
    });
});

function initQuery() {
    //初始化字典值
    $.ajax({
        type: "POST",
        async:false,
        url: GLOBAL.WEBROOT + "/dictItem/getDictItemCondition",
        data:{"operatorState":"OPERATOR_STATE"},
        datatype: "json",
        success: function (data) {
            var jsonOperatorState = eval(data.operatorState);
            var operatorStateSelect = $("#operatorState");
            operatorStateSelect.empty();
            for (var i = 0; i < jsonOperatorState.length; i++) {
                operatorStateSelect.append("<option value='" + jsonOperatorState[i].itemno + "'>" + jsonOperatorState[i].itemname + "</option>");
            }
            $("#operatorState").val(1);
            $('#operatorState').select2();
        }
    });
}

//新增
function init(id) {
    var flag;
    if (id){
        flag = edit(id);
    }
    return flag;
}

function edit(id) {
    var flag;
    var data = "operatorCode=" + id;
    $.ajax({
        type: "GET",
        async: false,
        url: GLOBAL.WEBROOT + "/sysmanage/sysoperator/getAddOperator.ajax",
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
            flag = true;
        }
    });
    return flag;
}

function setForm(data) {
    var sysOperatorBean = data.sysOperatorBean;
    $("#operatorCode").val(sysOperatorBean.operatorCode);
    $("#operatorCode").attr("readonly","readonly");
    $("#operatorName").val(sysOperatorBean.operatorName);
    $("#telNo").val(sysOperatorBean.telNo);
    $("#operatorState option").each(function () {
        if ($(this).val() == sysOperatorBean.operatorState) {
            $(this).attr("selected", true);
        }
    });
    $("#orgId").val(data.opBelongOrgId);
    $("#orgName").val(data.opBelongOrgName);
    $("#remarks").val(sysOperatorBean.remarks);
}

function saveSuccess(){
    parent.reloadGrid();
    closeLayer();
}
function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

//操作员归属组织选择
function addOp2BelongOrg(){
    var orgId = $("#orgId").val();
    var  url = GLOBAL.WEBROOT + "/sysmanage/sysoperator/page/operatorinfo-belong-org.html?id="+orgId;
    layer.open({
        type: 2,
        title:"【新增】操作员机构信息",
        area: ['500px', '460px'],
        fix: false, //不固定
        maxmin: true,
        content: url,
        closeBtn: 0
    });
}
/**
 * 选择机构
 * */
function setOp2BelongOrg(datas,index){
    var orgInfo = datas.split(",");
    $("#orgId").val(orgInfo[0]);
    $("#orgName").val(orgInfo[1]);
    layer.close(index)
    selectOffice();
}
/**
 * 选择科室
 * */
function selectOffice(){
    return;
    var flag;
    var orgId = $("#orgId").val();
    document.getElementById('officeName').innerHTML="";
    $.ajax({
        type: "POST",
        async: false,
        dataType:"json",
        url: GLOBAL.WEBROOT + "/sysmanage/sysoperator/getOfficeName?orgId="+orgId,
        data: {"searchModel.orgId": orgId},
        success: function (data) {
            if(data.ERRCODE == "0"){
                var jsonStatus = data.officeBeanList;
                var statusSelect = $("#officeName");
                for (var i = 0; i < jsonStatus.length; i++) {
                    statusSelect.append("<option value='" + jsonStatus[i].officeid + "'>" + jsonStatus[i].officename + "</option>");
                }
                $("#officeName").select2();
            }
            flag = true;
        }
    });
    return true;
}

function selectOfficeInit(){
    return;
    var operatorCode = $("#operatorCodeId").val();
    $.ajax({
        type: "POST",
        async: false,
        dataType:"json",
        url: GLOBAL.WEBROOT + "/sysmanage/sysoperator/getOfficeInit",
        data: { "operatorCode": operatorCode},
        success: function (data) {
            if(data.ERRCODE == "0"){
                var jsonStatus = data.officeBeanList;
                if(jsonStatus){
                    var arrayObj = new Array();
                    for (var i = 0; i < jsonStatus.length; i++) {
                        arrayObj[i] = jsonStatus[i].officeid;
                    }
                    $("#officeName").val(arrayObj).trigger('change');
                }
            }
        }
    });
}

function saveOperator(){
    var val = $("#operatorCode").val();
    if(val == null || val==""){
        info('温馨提示',"工号不能为空");
        return;
    }
    var val2 = $("#operatorName").val();
    if(val2 == null || val2==""){
        info('温馨提示',"姓名不能为空  ");
        return;
    }

    if(isUpdate == 0){
        var val3 = $("#newPwd").val();
        var val31 = $("#operatorPsw").val();
        if(val3 == null || val3==""){
            info('温馨提示',"密码不能为空  ");
            return;
        }
        if(val31 == null || val31==""){
            info('温馨提示',"确认密码不能为空  ");
            return;
        }
        if(val3!=val31){
            info('温馨提示',"两次输入密码不相同 ");
            return;
        }
    }
    var val4 = $("#orgName").val();
    if(val4 == null || val4==""){
        info('温馨提示',"机构不能为空  ");
        return;
    }
    var url="/sysmanage/sysoperator/saveOperator";
    var formData = $("#inputForm").serializeObject();
    formData.isUpdate = isUpdate;
    var data = $.toJSON(formData);
    displayExecuteLayer();
    $.ajax({
            type: "POST",
            async: true,
            dataType: 'json',
            cache:false,
            timeout:20000,
            data:data,
            contentType: 'application/json',
            url: GLOBAL.WEBROOT + url,
            success: function (data) {
                hideExecuteLayer();
                if (data.result=='success') {
                    infoCall('温馨提示',"保存成功！",saveSuccess);
                } else {
                    info('温馨提示','保存失败！' + data.result);
                }
            }
        }

    );
}

function checkOperatorExists() {
    var operatorCode = $("#operatorCode").val();
    if ("" == operatorCode || operatorCode.indexOf(" ") > -1 || !checkOperCode(operatorCode)) {
        info('温馨提示',"工号输入有误,请重新输入！工号可以由字母、数字、下划线组成，长度为6-16位！");
        $("#operatorCode").focus();
        return;
    }
    var url="/sysmanage/sysoperator/checkOperatorExists?operatorCode="+operatorCode;
    $.ajax({
            type: "POST",
            async: true,
            dataType: 'json',
            cache:false,
            timeout:20000,
            contentType: 'application/json',
            url: GLOBAL.WEBROOT + url,
            success: function (data) {
                if (data.result=='success') {
                    info('温馨提示',"恭喜您,该工号可以使用!");
                    $("#operatorCode").focus();
                } else {
                    info('温馨提示',data.result);
                    $("#operatorCode").focus();
                }
            }
        }

    );
}

//用户名可以由字母、数字、下划线和中文组成，以中文或字母开头，长度为6-16位
function checkOperCode(operatorCode){
    var reg=/^[a-zA-Z0-9_]{6,16}$/;
    if(reg.test(operatorCode)){
        return true;
    }else {
        return false;
    }
}