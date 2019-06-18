var zTree;
var orgId = "";
$(function () {
    orgId = $("#orgId", parent.parent.document).val();

    zTree = $.fn.zTree.init($("#templateTreeSet"), setting, [{
        id: '-1',
        name: '病例夹',
        open: true,
        isParent: true,
        nocheck: true
    }]);
    var treeObj = $.fn.zTree.getZTreeObj("templateTreeSet");
    var nodes = treeObj.getNodes();
    if (nodes.length > 0) {
        treeObj.selectNode(nodes[0]);
        treeObj.reAsyncChildNodes(nodes[0], "refresh");
    }
    
});
var setting = {
    view: {
        selectedMulti: false
    },
    edit: {
        enable: true,
        editNameSelectAll: true,
        removeTitle: '删除',
        renameTitle: '重命名'
    },
    data: {
        keep: {
            parent: true,
            leaf: true
        },
        simpleData: {
            enable: true
        }
    },
    async: {
        enable: true,
        url: GLOBAL.WEBROOT + "/studyReport/initKnowledgeTree.ajax",
        autoParam: ["id"]
    },
    callback: {
        beforeRemove: beforeRemove,//点击删除时触发，用来提示用户是否确定删除
        beforeEditName: beforeEditName,//点击编辑时触发，用来判断该节点是否能编辑
        beforeRename: beforeRename,//编辑结束时触发，用来验证输入的数据是否符合要求
        onRemove: onRemove,//删除节点后触发，用户后台操作
        onRename: onRename,//编辑后触发，用于操作后台
        beforeDrag: beforeDrag,//用户禁止拖动节点
        onClick: clickNode//点击节点触发的事件
    }
};
var paramLeftDirid;
//查询病例库中关联的报告
function getknowledgeReportInfo(node) {
	 paramLeftDirid = node.id;
	 $("#templateName").attr("disabled", true);
     $("#saveBtn").show();
     $("#closeBtn").show();
     $("#templateName").val(node.name);
     $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/studyReport/getKnowLedgeReportInfo.ajax?id=" + node.id,
        success: function (data) {
            var temp = eval(data);
            CKEDITOR.instances.templateExam.setData(temp.templateExam);
            CKEDITOR.instances.templateResult.setData(temp.templateResult);
        }
    });
}

function beforeRemove(e, treeId, treeNode) {
    return confirm("你确定要删除吗？");
}
function onRemove(e, treeId, treeNode) {
    var paramsArray = new Array();
    if (treeNode.pId != null) {
        try {
            var childNodes = zTree.removeChildNodes(treeNode);
            for (var i = 0; i < childNodes.length; i++) {
                paramsArray.push(childNodes[i].id);
            }
        } catch (e) {
        }
        $.ajax({
            type: "GET",
            async: true,
            url: GLOBAL.WEBROOT + "/studyReport/deletebingliNodes.ajax?id=" + treeNode.id,
            dataType: 'json',
            success: function (data) {
                if (data.ERRCODE == "0") {
                    $("#saveBtn").hide();
                    $("#closeBtn").hide();
                    var treeObj = $.fn.zTree.getZTreeObj("templateTreeSet");
                    var nodes = treeObj.getNodes();
                    if (nodes.length > 0) {
                        treeObj.selectNode(nodes[0]);
                        treeObj.reAsyncChildNodes(nodes[0], "refresh");
                    }
                    var eEditor = CKEDITOR.instances.templateExam;
                    var rEditor = CKEDITOR.instances.templateResult;
                    $("#templateName").val("");
                    $("#templateName").attr("disabled", "disabled");
                    resetTemp();
                    eEditor.setReadOnly(true);
                    rEditor.setReadOnly(true);
                    //                alert('删除成功');
                }
                else {
                    layer.msg('删除失败');
                }
            }
        });
    } else {
        zTree = $.fn.zTree.init($("#templateTreeSet"), setting, [{
            id: '-1',
            name: '病例夹',
            open: true,
            isParent: true,
            nocheck: true
        }]);
        var treeObj = $.fn.zTree.getZTreeObj("templateTreeSet");
        var nodes = treeObj.getNodes();
        if (nodes.length > 0) {
            treeObj.selectNode(nodes[0]);
            treeObj.reAsyncChildNodes(nodes[0], "refresh");
        }
        var eEditor = CKEDITOR.instances.templateExam;
        var rEditor = CKEDITOR.instances.templateResult;
        $("#templateName").val("");
        $("#templateName").attr("disabled", "disabled");
        resetTemp();
        eEditor.setReadOnly(true);
        rEditor.setReadOnly(true);
        layer.msg("根节点不允许删除");

    }

//   
//    alert("你点击要删除的节点的名称为："+treeNode.name+"\r\n"+"节点id为："+treeNode.id);

}
function beforeEditName(treeId, treeNode) {
//    if(treeNode.isParent){
//        alert("不准编辑非叶子节点！");
//        return false;
//    }
    return true;
}
function beforeRename(treeId, treeNode, newName, isCancel) {
//    if(newName.length < 1){
//        alert("名称不能少于3个字符！");
//        return false;
//    }
    //未修改不访问后台
    if (newName != treeNode.name) {
        var data = $("#searchForm").serializeObject();
        data.knowledgebaseid = treeNode.id;
        data.casegroupdesc = newName;
        data = $.toJSON(data);
        $.ajax({
            type: "POST",
            async: true,
            url: GLOBAL.WEBROOT + "/studyReport/editbingliNodes.ajax",
            dataType: 'json',
            data: data,
            contentType: 'application/json',
            success: function (data) {
                if (data.ERRCODE == "0") {
                    //                alert('修改成功');
                    return true;
                }
                else {
                    layer.msg('修改失败');
                    return false;
                }
            }
        });
    }
    return true;
}
var newId = 0;
function onRename(e, treeId, treeNode, isCancel) {
//    alert("修改节点的id为："+treeNode.id+"\n修改后的名称为："+treeNode.name);
}

var flag;
var countNum = 0;
function clickNode(e, treeId, treeNode) {
	getknowledgeReportInfo(treeNode); //查询病例库中关联的报告
}

function resetTemp() {
//	$("#templateName").val("");
    var eEditor = CKEDITOR.instances.templateExam;
    eEditor.setData("");
    var rEditor = CKEDITOR.instances.templateResult;
    rEditor.setData("");
}

function save() {
    var eEditor = CKEDITOR.instances.templateExam;
    if (eEditor.getData() == "") {
        layer.msg('请填写病例检查意见', {icon: 5});
        return false;
    }
    var rEditor = CKEDITOR.instances.templateResult;
    if (rEditor.getData() == "") {
        layer.msg('请填写病例诊断结果', {icon: 5});
        return false;
    }
    var data = {
        "knowledgebaseid": paramLeftDirid,
        "templateExam": eEditor.getData(),
        "templateResult": rEditor.getData()
    };
    data = $.toJSON(data);
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/studyReport/updateKnowledgebase",
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            if (data.ERRCODE == "0") {
                layer.msg('操作成功', {icon: 1});
//	                parent.reloadGrid();
//	                setTimeout(closeDiv,1500);
                var treeObj = $.fn.zTree.getZTreeObj("templateTreeSet");
                var nodes = treeObj.getNodes();
                if (nodes.length > 0) {
                    treeObj.selectNode(nodes[0]);
                    treeObj.reAsyncChildNodes(nodes[0], "refresh");
                }
            }
            else {
                layer.msg('新增失败', {icon: 1});
            }
        }
    });
}


function beforeDrag(treeId, treeNodes) {
    return false;
}

