$(function () {
//    $("#casegroupdesc").select2();
//    $("#keydesc").select2();
    $("#closeBtn").click(function () {
        closeDiv();
    });
    $("#saveBtn").click(function () {
        saveMessage();
    });
    //initCasegroup();
})

//function initCasegroup() {
//    //初始化操作员编辑框
//    $.ajax({
//        type: "POST",
//        async: false,
//        cache: false,
//        url: GLOBAL.WEBROOT + "/studyReport/getCasegroup",
//        success: function (data) {
//            var jsonUser = eval(data.casegroup);
//            var cashboxownercodeSelect = $("#casegroupdesc");
//            cashboxownercodeSelect.empty();
//            cashboxownercodeSelect.append("<option value='-1'>请选择</option>");
//            if (jsonUser) {
//                for (var i = 0; i < jsonUser.length; i++) {
//                    cashboxownercodeSelect.append("<option value='" + jsonUser[i].casegroupdesc + "'>" + jsonUser[i].casegroupdesc + "</option>");
//                }
//            }
//            $("#casegroupdesc").select2();
//        }
//    });
//}

//function selectKeydesc() {
//    var s1 = $("#casegroupdesc option:selected");
//    var dataSore = s1.val();
//    $.ajax({
//        type: "POST",
//        async: true,
//        dataType: "json",
//        url: GLOBAL.WEBROOT + "/studyReport/getKeydesc",
//        data: {"dataSore": dataSore},
//        success: function (data) {
//            var statusSelect = "";
//            var jsonStatus = eval(data.keydesc);
//            statusSelect = $("#keydesc");
//            statusSelect.empty();
//            statusSelect.append("<option value='-1'>请选择</option>");
//            if (jsonStatus) {
//                for (var i = 0; i < jsonStatus.length; i++) {
//                    statusSelect.append("<option value='" + jsonStatus[i].keydesc + "'>" + jsonStatus[i].keydesc + "</option>");
//                }
//            }
//            $("#keydesc").select2();
//        }
//    });
//}
function saveMessage() {
    var reportId = $("#reportId").val();
    var operatorId = $("#operatorId").val();
//    var s1 = $("#casegroupdesc option:selected");
//    var casegroupdesc = s1.val();
//    var s2 = $("#keydesc option:selected");
//    var keydesc = s2.val();
    var keydesc;
//    if(keydesc != "-1"){
//        $("#casegroupdesc1").val("");
//        $("#keydesc1").val("");
//    }
//    if($("#keydesc").val() != ""){
//        if($("#casegroupdesc1").val() == ""){
//            alert("请输入部位！");
//            return;
//        }
//    }
    if($("#keydesc").val() != ""){
        keydesc = $("#keydesc").val();
    }else{
        alert("请输入收藏标题！");
        return;
    }
    $.ajax({
        type: "POST",
        async: false,
        cache: false,
        url: GLOBAL.WEBROOT + "/studyReport/saveAisKnowledgebase.ajax?reportId="+reportId+"&operatorId="+operatorId+"&keydesc="+encodeURI(encodeURI(keydesc)),
        success: function (data) {
            if (data.ERRCODE == "0") {
                layer.msg('操作成功', {icon: 1});
                setTimeout( function() {closeDiv();}, 1000);
                parent.initKnowledgeTree();
                $.each(parent.$("li[name=tag]"), function (i, obj) {
                	parent.$("li[name=tag]").eq(i).removeClass("active");
                });
                parent.$("li[name=tag]").eq(5).attr("class", "active");
            }else{
                alert(data.ERRINFO);
            }
        }
    });
}
function closeDiv(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}