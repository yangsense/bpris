$(function () {
    $("select").select2();
});
//关闭弹窗
function closewin1() {
    WEB.closeWin("1");
}
//保存
saveDocTag = function () {
    var options = {
        dataType: 'json',
        success: function (data) {
            var result = data.result;
            var message = data.message;
            alert(message);
            if (result) {
                var frameId = WEB.getTabSelectFrame().id;
                //alert(frameId);
                window.parent.frames[frameId].updateOrgTreeOnSelectedNode(data.id, data.name);
                closewin1();
            } else {
                $.gridUnLoading();
            }
        },
        error: function () {
            alert("操作失败！");
            $.gridUnLoading();
        }
    };

    $.gridLoading({
        message: "正在处理..."
    });
    $("#addForm").ajaxSubmit(options);
};


function addDocTag() {
    var parentOrgId = $("#parentOrgId").val();
    var parentOrgLevel = $("#parentOrgLevel").val();
    if (parentOrgId == '') {
        alert("请在组织树上选择上级组织！");
        return;
    }
    //首页面添加的组织机构等级从1开始，在详细页添加的组织等级==当前页等级+1
    if(parentOrgId == '-1'){
        parentOrgLevel = 1;
    }
    var params = "?parentOrgId=" + parentOrgId +"&orgLevel="+ parentOrgLevel;
    var url = GLOBAL.WEBROOT + "/sysOrg/addOrgDetail.html"+ params;
    index = parent.layer.open({
        type: 2,
        title: "【新增】组织配置",
        area: ['900px', '600px'],
        fix: false, //不固定
        maxmin: true,
        content: url
    });
}

function updateDocTag() {
    var orgId = $("#orgId").val();
    var parentOrgId = $("#parentOrgId").val();
    var params = "?orgId=" + orgId + "&parentOrgId=" + parentOrgId;
    var url = GLOBAL.WEBROOT + "/sysOrg/updateOrgDetail.html"+ params;
    index = parent.layer.open({
        type: 2,
        title: "【修改】组织配置",
        area: ['900px', '600px'],
        fix: false, //不固定
        maxmin: true,
        content: url
    });
}

/**  删除机构*/
function delOne(){
    var orgId = $("#orgId").val();
    var parentOrgId = $("#parentOrgId").val();
    //确认有没有子孙机构
    var vFlag = true ;
    var url = GLOBAL.WEBROOT + "/sysOrg/queryOrgSon.ajax?orgId="+ orgId;
    $.ajax({
        url: url,
        async: false,
        type : "post",
        success: function (data) {
            var result = data.result;
            if (result) {
                if(data.total>0){
                    layer.msg('当前机构有下有子孙机构，不能删除！', {icon: 5});
                    vFlag = false;
                }
            } else {
                layer.msg('操作失败，请稍后再试！', {icon: 5});
                vFlag = false;
            }
            if (vFlag && confirm('确定要【删除】所选择的组织吗？')) {
                setTimeout(function () {
                    $.ajax({
                        url: GLOBAL.WEBROOT + "/sysOrg/delOrgDetail.ajax?orgIdsToDel="+ orgId,
                        type : "post",
                        success: function (data) {
                            var result = data.result;
                            if (result) {
                                deleteOrg(orgId);
                                parent.initOrgTree();
                                layer.msg('删除成功', {icon: 6});
                            } else {
                                layer.msg('操作失败，请稍后再试！', {icon: 5});
                            }
                        }
                    });

                }, 300);
            }
        }
    });
}

function deleteOrg(ids) {
    var pNodes = parent.orgTree.getSelectedNodes();
    if (pNodes != null && pNodes.length == 1 && ids != null && ids.length > 0) {
        for (var i = 0; i < ids.length; i++) {
            var node2Del = parent.orgTree.getNodeByParam("id", ids[i], pNodes[0]);
            if (node2Del != null)
                parent.orgTree.removeNode(node2Del);
        }
    }
}
