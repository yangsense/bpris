//初始化
$(function () {
    $("select").select2();
    $("#addForm").validate({
        rules: {
            'orgName': {
                required: true
            },
            'orgCode': {
                required: true
            },
            /* 'cityCode1': {
             required: true
             },*/
            'cityCode': {
                required: true
            }
        },
        messages: {
            'orgName': {
                required: '必填'
            },
            'orgCode': {
                required: '必填'
            },
            /* 'citySelect': {
             required: '必选'
             },*/
            'cityCode': {
                required: '必选'
            }
        },
        invalidHandler : forBack,
        submitHandler: function () {
            var city = $("#city").val();
            if(city && city.length>0){
                saveDocTag();
            }else {
                layer.msg("请选择隶属城市",{icon:5});
                return;
            }
        }
    });
    $("#saveBtn").click(function(){
        $("#addForm").submit();
    });
    document.getElementById("orgName").focus();
    $("#orgTypeFirst").change(function(){
        bindOrgType($("#orgTypeFirst").val(),"2",$("#orgTypeSecond"));
        bindOrgType($("#orgTypeSecond").val(),"3",$("#orgTypeThird"));
    });
    $("#orgTypeSecond").change(function(){
        bindOrgType($("#orgTypeSecond").val(),"3",$("#orgTypeThird"));
    });
});


//提交表单
function submit(){
    $.gridLoading( {
        message : "正在处理..."
    });
    $("#addForm").submit();
}
//校验失败后取消遮挡
function forBack(){
    var iTimeId = window.setInterval(function(){
        $.gridUnLoading();
        window.clearInterval(iTimeId);
    },1000);
}


function closeDiv(){
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
function setOp2BelongOrg(datas,index){
    var orgInfo = datas.split(",");
    //$("#orgId").val(orgInfo[0]);
    $("#parentOrgName").val(orgInfo[1]);
    layer.close(index)
}

//保存
saveDocTag = function () {
    //提交
    var province = $("#province").val();
    var city = $("#city").val();
    var county = $("#county").val();
    var town = $("#town").val();
    var village = $("#village").val();
    var data = $("#addForm").serializeObject();
    data= $.toJSON(data);
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/sysOrg/saveSysOrg?province="+province+"&city="+city+"&county="+county+"&town="+town+"&village="+village,
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            if (data.result == "0") {
                layer.msg('操作成功', {icon: 6});
                parent.doQuery();
                setTimeout(closeDiv,1500);
            }
            else {
                layer.msg('操作失败'+data.message, {icon: 5});
            }
        }
    });
};

//处理机构归属详细地址
function inOrgAddr(){
    alert($("#city1").val());
    $("#orgAddr").val();
}

function bindOrgType(parentItemNo,itemLevel,obj){
    $.ajax({
        type : "POST",
        dataType:"json",
        async : false,
        url : GLOBAL.WEBROOT + '/sys/sys/orgManage!getOrgType.action',
        data:{
            "searchModel.parentItemNo":parentItemNo,
            "searchModel.itemLevel":itemLevel,
        },
        success : function(data) {
            if (data.ERRCODE == "0") {
                var html = "";
                $.each(data.list, function(i, bean) {
                    html+="<option value='"+bean.itemNo+"'>"+bean.itemName+"</option>";
                });
                obj.html(html);
            } else {
                alert("操作失败！" + data.ERRINFO);
            }
        }
    });
}