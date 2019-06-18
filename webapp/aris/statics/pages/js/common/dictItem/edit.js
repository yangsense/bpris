var opType;
$(function () {
    var dictName = $("#dictName").val();
    var itemNo = $("#itemNo").val();

    opType = $("#editForm").attr("opType");
    if(opType=="view"){
        init(dictName,itemNo);
        com.ai.bdx.util.disableAll("editForm");
        $("#saveBtn").hide();
    }else if(opType=="update"){
    	$("#dictName").attr("disabled","disabled");
    	$("#itemNo").attr("disabled","disabled");
        init(dictName,itemNo);
        $("#showText").text("保 存");
    }

    $("select").select2();
    $("#closeBtn").click(closeLayer);

    $("#editForm").validate({

        // 非空校验
        rules : {
            dictName : {
                required : true
            },
            itemNo:{
                required : true
            },
            itemName:{
                required:true
            }

        },
        errorElement:'em',
        messages: {
         /*   orgId: '非空',
            locDesc:'非空',
            ruleStartindex: '非空',
            ruleLen:'必须输入数字',*/
        },
        submitHandler : save
    });


    $("#saveBtn").click(function(){
        $("#editForm").submit();
    });
});


function init(dictName,itemNo) {
    var data = "dictName=" + dictName+"&itemNo="+itemNo;
    $.ajax({
        type: "GET",
        async: false,
        url: GLOBAL.WEBROOT + "/dictItem/getDictItem",
        dataType: 'json',
        data: data,
        success: function (data) {
                setForm(data);
        }
    });
}

function setForm(data) {
    $("#itemName").val(data.itemName);
    $("#parentItemNo").val(data.parentItemNo);
    $("#itemOrder").val(data.itemOrder);
    $("#itemState option").each(function () {
        if ($(this).val() == data.itemState) {
            $(this).attr("selected", true);
        }
    });
    $("#itemLevel").val(data.itemLevel);
    $("#itemDesc").val(data.itemDesc);
}

function save() {
    var data = $("#editForm").serializeObject();
    data = $.toJSON(data);
    var url;
    if(opType){
        url =  GLOBAL.WEBROOT + "/dictItem/updateDictItem";
    }else{
        url =  GLOBAL.WEBROOT + "/dictItem/addDictItem";
    }
    $.ajax({
        type: "POST",
        async: true,
        url: url,
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
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