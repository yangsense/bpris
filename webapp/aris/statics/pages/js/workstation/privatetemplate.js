var zTree;
var orgId = "";
$(function () {
    orgId = $("#orgId", parent.parent.document).val();
    //初始化私有模板树
    var type = "";
    if ($("#templateType").val() == "1") {
        type = "public";
    } else if ($("#templateType").val() == "2") {
        type = "private";
    }

    zTree = $.fn.zTree.init($("#templateTreeSet"), setting, [{
        id: '-2',
        name: '模板',
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
    $("#addParent").bind("click", {isParent: true}, add);
    $("#addLeaf").bind("click", {isParent: false}, add);

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
        url: GLOBAL.WEBROOT + "/studyReport/initTree.ajax?orgId=" + $("#orgId", parent.parent.document).val() + "&flag=" + 'private' + "&locId=" + parent.$("#locId").val() + "&modalityId=",
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
            url: GLOBAL.WEBROOT + "/studyReport/deletezNodes.ajax?id=" + treeNode.id + "&childs=" + paramsArray,
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
                    //                alert('删除成功');
                }
                else {
                    layer.msg('删除失败');
                }
            }
        });
    } else {
        zTree = $.fn.zTree.init($("#templateTreeSet"), setting, [{
            id: '-2',
            name: '私有模板',
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
        data.templatecontentId = treeNode.id;
        data.templateName = newName;
        data = $.toJSON(data);
        $.ajax({
            type: "POST",
            async: true,
            url: GLOBAL.WEBROOT + "/studyReport/editNodes.ajax",
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

var newCount = 1;
function add(e) {
//获取节点seq
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + "/studyReport/getNodeIndex",
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            addNewNodes(data.nodeId, e);
        }
    });
};
var treeObjs;
function addNewNodes(nodeId, e) {
    var zTree = $.fn.zTree.getZTreeObj("templateTreeSet"),
        isParent = e.data.isParent,

        nodes = zTree.getSelectedNodes(),
        treeNode = nodes[0];
//if (treeNode) {
//	treeNode = zTree.addNodes(treeNode, {id:nodeId, pId:treeNode.id, isParent:isParent, name:"new node" + (newCount++)});
//} else {
//	treeNode = zTree.addNodes(null, {id:nodeId, pId:0, isParent:isParent, name:"私有模板"});
//}

    if (treeNode) {
//	zTree.editName(treeNode[0]);

        var dataObj = $("#searchForm").serializeObject();
        dataObj.templatedirId = nodeId;
        dataObj.templatedirPdirid = treeNode.id;
        dataObj.templateName = "new node";
        dataObj.locId = $("#locId", parent.document).val();
        dataObj.orgId = $("#orgId", parent.parent.document).val();
        dataObj.isDirectory = isParent == true ? 1 : 0;
        dataObj.modalityId = $("#modalityId", parent.document).val();
        dataObj = $.toJSON(dataObj);

        $.ajax({
            type: "POST",
            async: true,
            url: GLOBAL.WEBROOT + "/studyReport/addNodes.ajax?dataType=" + $("#templateDetaiForm").attr("dataType"),
            dataType: 'json',
            data: dataObj,
            contentType: 'application/json',
            success: function (data) {
                try {
                    if (data.ERRCODE == "0") {
                        //                alert('修改成功');
                        newId = data.ID;

                        //     		$("#templatecontentId").val("");

                        var eEditor = CKEDITOR.instances.templateExam;
                        var rEditor = CKEDITOR.instances.templateResult;
                        if (isParent) {
                            $("#templateName").attr("disabled", "disabled");
                            $("#templateMethod").attr("disabled", "disabled");
                            resetTemp();
                            eEditor.setReadOnly(true);
                            rEditor.setReadOnly(true);
                            $("#saveBtn").hide();
                            $("#closeBtn").hide();
                        } else {
                            $("#templateName").attr("disabled", false);
                            $("#templateMethod").attr("disabled", false);
                            eEditor.setReadOnly(false);
                            rEditor.setReadOnly(false);
                            $("#saveBtn").show();
                            $("#closeBtn").show();
                            paramLeftDirid = nodeId;
//	     	    	var treeObj = $.fn.zTree.getZTreeObj("templateTreeSet");
//	     	    	var node = zTree.getNodeByParam("id",treeNode[0].id);
//	     	    	treeObj.selectNode(node);
                        }
                        if (!((!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) || treeNode.open)) {
                            zTree.expandNode(treeNode, true);
                        }

                        treeNode = zTree.addNodes(treeNode, {
                            id: nodeId,
                            pId: treeNode.id,
                            isParent: isParent,
                            name: "new node"
                        });
                        var treeObj = "";
                        if (treeNode) {
                            treeObj = $.fn.zTree.getZTreeObj("templateTreeSet");
                            var node = zTree.getNodeByParam("id", nodeId);
                            treeObj.selectNode(node);
                        } else {
                            layer.msg("叶子节点被锁定，无法增加子节点");
                        }

                        //重置
                        $("#templatecontentId").val("");
                        //treeObjs = $.fn.zTree.getZTreeObj("templateTreeSet");
                        //var nodes = treeObj.getNodes();
                        //treeObjs.selectNode(nodes[0]);
                        //treeObjs.reAsyncChildNodes(nodes[0], "refresh");

                    } else {
                        layer.msg('异常失败');
                        treeObjs = $.fn.zTree.getZTreeObj("templateTreeSet");
                        var nodes = treeObj.getNodes();
                        treeObjs.selectNode(nodes[0]);
                        treeObjs.reAsyncChildNodes(nodes[0], "refresh");
                    }
                } catch (error) {
                } finally {
                    var treeObjs = $.fn.zTree.getZTreeObj("templateTreeSet");
                    var treeObj = $.fn.zTree.getZTreeObj("templateTreeSet");
                    var nodes = treeObj.getNodes();
                    treeObjs.selectNode(nodes[0]);
                    treeObjs.reAsyncChildNodes(nodes[0], "refresh");
                }
            }


        });


    } else {
        treeNode = zTree.addNodes(null, {id: nodeId, pId: 0, isParent: isParent, name: "私有模板"});
        layer.msg("叶子节点被锁定，无法增加子节点");
    }
}

var paramLeftDirid;
var flag;
var countNum = 0;
function clickNode(e, treeId, treeNode) {
    //加载右侧私有模板
    var eEditor = CKEDITOR.instances.templateExam;
    var rEditor = CKEDITOR.instances.templateResult;

    if (!treeNode.isParent) {
        $("#templateName").attr("disabled", false);
        $("#templateMethod").attr("disabled", false);
        eEditor.setReadOnly(false);
        rEditor.setReadOnly(false);
        $("#saveBtn").show();
        $("#closeBtn").show();
        $.ajax({
            type: "POST",
            async: true,
            url: GLOBAL.WEBROOT + "/studyReport/aiscTemplateDetail?dirId=" + treeNode.id,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                if (data.templateBean != null) {
                    $("#templateName").val(data.templateBean.templateName);
                    $("#templateMethod").val(data.templateBean.templateMethod);
                    $("#templatecontentId").val(data.templateBean.templatecontentId);
                    $('input[name="ispositive"][value="' + data.templateBean.ispositive + '"]').attr("checked", 'checked');
                    var eEditor = CKEDITOR.instances.templateExam;
                    eEditor.setData(data.templateBean.templateExam);
                    var rEditor = CKEDITOR.instances.templateResult;
                    rEditor.setData(data.templateBean.templateResult);
                } else {
                    $("#templateName").val(treeNode.name);
                    $("#templatecontentId").val("");
                    resetTemp();
                }
            }
        });
    } else {
        $("#templateName").attr("disabled", "disabled");
        $("#templateMethod").attr("disabled", "disabled");
        eEditor.setReadOnly(true);
        rEditor.setReadOnly(true);
        $("#saveBtn").hide();
        $("#closeBtn").hide();
        resetTemp();
    }
    paramLeftDirid = treeNode.id;
    flag = treeNode.isParent;

}

function resetTemp() {
//	$("#templateName").val("");
    $("#templateMethod").val("");
    $('input[name="ispositive"][value="0"]').attr("checked", 'checked');
    var eEditor = CKEDITOR.instances.templateExam;
    eEditor.setData("");
    var rEditor = CKEDITOR.instances.templateResult;
    rEditor.setData("");
}

function save() {
    //提交
//	    var data = $("#templateDetaiForm").serializeObject();
//	    data = $.toJSON(data);
//	    alert(data);
    if ($("#templateName").val() == "") {
        layer.msg('请填写私有模板名称', {icon: 5});
        return false;
    }
//		if($("#templateMethod").val()==""){
//			layer.msg('请填写检查方法', {icon: 5});  
//	    	return false;
//		}
    var eEditor = CKEDITOR.instances.templateExam;
    if (eEditor.getData() == "") {
        layer.msg('请填写检查所见', {icon: 5});
        return false;
    }
    var rEditor = CKEDITOR.instances.templateResult;
    if (rEditor.getData() == "") {
        layer.msg('请填写诊断意见', {icon: 5});
        return false;
    }
    var data = {
        "templatedirId": paramLeftDirid,
        "templateName": $("#templateName").val(),
        templateMethod: $("#templateMethod").val(),
        "templateExam": eEditor.getData(),
        "templateResult": rEditor.getData(),
        templatecontentId: $("#templatecontentId").val(),
        ispositive: $('input[name="ispositive"]:checked ').val()
    };
    data = $.toJSON(data);
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/studyReport/saveTemplateDetail",
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            if (data.ERRCODE == "0") {
                layer.msg('操作成功', {icon: 1});
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


