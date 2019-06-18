$(function () {
    initOrgTree();
    docTagManageInitGrid();
});
function doQuery() {
    var data = $("#searchForm").serializeObject()
    $("#docTagManageTable").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

var orgTree;
function initOrgTree() {
    var setting = {
        view: {
            showLine: true,
            selectedMulti: false
        },
        data: {
            keep: {
                leaf: false,
                parent: false
            },
            key: {
                checked: "checked",
                children: "children",
                name: "name"
            },
            simpleData: {
                enable: false,
                idKey: "id",
                pIdKey: "pid"
            }
        },
        async: {
            enable: true,
            url:GLOBAL.WEBROOT + "/sysOrg/getOrgTreeByOperator.ajax",
            autoParam:["id"]
        },
        callback: {
            onClick: function (evt, treeId, treeNode) {
                onClickTreeNode(treeNode);
            }
        }
    };
    orgTree = $.fn.zTree.init($("#orgTree"), setting, [ {id : '-1',name : '组织机构', open : true,isParent : true ,nocheck:true} ]);
    var treeObj = $.fn.zTree.getZTreeObj("orgTree");
    var nodes = treeObj.getNodes();
    if (nodes.length>0) {
        treeObj.reAsyncChildNodes(nodes[0], "refresh");
    }
}
function docTagManageInitGrid() {
    var grid_selector = "#docTagManageTable";
    var pager_selector = "#aisccareprov_pager";
    var data = $("#orgName").val();
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/sysOrg/queryOrgList.ajax",
        mtype: "post",
        datatype: "json",
        height: '100%',
        width: '100%',
        postData: {"menuId": data},
        colNames: ['机构名称', '机构代码', '状态',  '备注','orgId'],
        colModel: [
            {name: 'orgName', index: 'orgName',  sortable: false, align: 'center'},
            {name: 'orgCode', index: 'orgCode', sortable: false, align: 'center'},
            {
                name: 'state', index: "state", sortable: false, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    if (cellvalue == '1') {
                        return "正常";
                    } else {
                        return "失效";
                    }
                }
            },
            {name: 'remarks', index: "remarks", sortable: false, align: 'center'},
            {name: 'orgId', index: "orgId", sortable: false, align: 'center',hidden:true}
        ],
        viewrecords: false,
        rowNum: 10,
        rowList: [10, 15, 20, 30],
        multiselect: true,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}
function orgCutNetSet() {
    var checkimei = $("#docTagManageTable").getCheckedIds();
    var checkimei_lehgth = checkimei.length;
    if (checkimei_lehgth != 1) {
        alert("请选择一个组织");
    } else {
        WEB.openWin({
            url: 'content/sys/org/org-cutnetset.jsp?orgId=' + checkimei[0],
            title: "个性监控时间段设置",
            id: 2,
            top: 10,
            width: 650,
            height: 470,
            onClose: function (datas) {
            }
        });
    }
}

function policePhoneInfoCutNetSet() {
    WEB.openWin({
        url: 'policemanage/policephoneinfo!queryPolicePhoneInfoCutNetSetInit.action',
        title: "公共监控时间段设置",
        id: 2,
        top: 10,
        width: 650,
        height: 470,
        onClose: function (datas) {
        }
    })
}
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
    layer.open({
        type: 2,
        title: "【新增】组织配置",
        area: ['900px', '600px'],
        fix: false, //不固定
        maxmin: true,
        content: url
    });
}

function updateDocTagRightTable() {
    var checkRowId=$('#docTagManageTable').jqGrid('getGridParam','selarrrow');

    if (checkRowId.length == 0) {
        layer.msg('请选择要修改的组织!', {icon: 5});
        return;
    } else if (checkRowId.length > 1) {
        layer.msg('请仅选择一个要修改的组织!', {icon: 5});
        return;
    } else if (checkRowId.length  == 1) {
        var orgId = $("#docTagManageTable").jqGrid('getRowData',checkRowId).orgId;
        var parentOrgId = $("#parentOrgId").val();
        var params = "?orgId=" + orgId + "&parentOrgId=" + parentOrgId;
        var url = GLOBAL.WEBROOT + "/sysOrg/updateOrgDetail.html"+ params;
        layer.open({
            type: 2,
            title: "【修改】组织配置",
            area: ['900px', '600px'],
            fix: false, //不固定
            maxmin: true,
            content: url
        });
    }
}

/**  删除机构*/
function delOne(){
    var checkRowId=$('#docTagManageTable').jqGrid('getGridParam','selarrrow');

    if (checkRowId.length == 0) {
        layer.msg('请选择要修改的组织!', {icon: 5});
        return;
    }else {
        var orgIdsToDel = new Array();
        $(checkRowId).each(function (i, o) {
            orgIdsToDel.push($("#docTagManageTable").jqGrid('getRowData',o).orgId);
        });
        setTimeout(function () {
            $.ajax({
                url: GLOBAL.WEBROOT + "/sysOrg/delOrgDetail.ajax?orgIdsToDel=" + checkRowId,
                type: "post",
                success: function (data) {
                    var result = data.result;
                    if (result) {
                        deleteOrg(checkRowId);
                        layer.msg('删除成功', {icon: 6});
                        setTimeout(doQuery(),600);
                    } else {
                        layer.msg('操作失败，请稍后再试！', {icon: 5});
                    }
                }
            });
        }, 300);
    }
}

/** 点击树节点*/
function onClickTreeNode(treeNode) {
    orgTree.expandNode(treeNode, true);
    $("#parentOrgId").val(treeNode.id);
    $("#orgName").val();
    doQuery();
    queryOrgInfo(treeNode.id);
}

function updateOrgTreeOnSelectedNode(id, name) {
    var pNodes = orgTree.getSelectedNodes();
    if (pNodes != null && pNodes.length == 1) {
        var node2Update = orgTree.getNodeByParam("id", id, pNodes[0]);
        if (node2Update == null) { // add
            orgTree.addNodes(pNodes[0], {id: id, name: name, pid: pNodes[0].id});
        } else { // update
            node2Update.name = name;
            orgTree.updateNode(node2Update);
        }
    }
}

function deleteOrg(ids) {
    var pNodes = orgTree.getSelectedNodes();
    if (pNodes != null && pNodes.length == 1 && ids != null && ids.length > 0) {
        for (var i = 0; i < ids.length; i++) {
            var node2Del = orgTree.getNodeByParam("id", ids[i], pNodes[0]);
            if (node2Del != null)
                orgTree.removeNode(node2Del);
        }
    }
}

function queryOrgInfo(orgId){
    if(orgId != -1){
        // 点击非根节点时显示该节点的基本信息
        var parentOrgId = $("#parentOrgId").val();
        var params = "?orgId=" + orgId + "&parentOrgId=" + parentOrgId;
        var url = GLOBAL.WEBROOT + "/sysOrg/viewOrgDetail.html"+ params;
        $("#queryBox").attr("style","display:none");
        $("#bottomDiv").attr("style","display:none");
        $("#orgDetailHide").attr("style","display:block");
        $("#orgDetailHide").attr("src",url);
    }else{
        // 点击根节点时显示列表页面
        displayOrgList();
    }
}

//隐藏显示处理
function displayOrgList(){
    $("#orgDetailHide").attr("style","display:none");
    $("#queryBox").attr("style","display:block");
    $("#bottomDiv").attr("style","display:block");
}





