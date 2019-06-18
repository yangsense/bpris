$(function () {

    TableList.init();
    initMeunShrinkTop(); //初始化下拉菜单-顶层
    initMeunShrinkOne();//初始化下拉菜单-第一级
    $("#firstOnclick").click();
});

// 加载菜单树开始
function initMenuTree(node) {
    var chkStyle = "checkbox";
    var setting = {
        view: {
            selectedMulti: false
        },
        edit: {
            enable: false,
            editNameSelectAll: true
            /*,
             showRemoveBtn : TableList.showRemoveBtn,
             showRenameBtn : TableList.showRenameBtn*/
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
                enable: true,
                idKey: "id",
                pIdKey: "pid"
            }
        },
        callback: {
            onClick: function (evt, treeId, treeNode) {
                treeNodeClick(treeId, treeNode);
            }
        }
    };

    $.ajax({
        url: GLOBAL.WEBROOT + "/menu/queryRoleMenu.ajax",
        dataType: 'json',
        method: "post",
        success: function (data) {
            TableList.tree = $.fn.zTree.init($("#menuTree"), setting, data);
            if (node) {
                TableList.tree.expandNode(node, true, null, null, null);
            } else {
                TableList.tree.expandAll(true);  //第一次加载时，菜单全部收起
            }
            TableList.setCheck();

        },

        complete: function () {

        }
    });
}
// 加载菜单树结束

/**
 * 菜单控件处理
 */
// 初始化菜单收缩
function initMeunShrinkTop() {
    $("#menuTree_1_switch").attr("class", "button level1 switch root_open");//顶层+号
    $("#menuTree_1_ico").attr("class", "button level1 switch button ico_open");//顶层文件夹图片
    $("#menuTree_1_ul").attr("style", "display: block");//整个菜单ul块
}
function initMeunShrinkOne() {
    $("#menuTree_2_switch").attr("class", "button level1 switch center_open");//第一个菜单目录
    $("#menuTree_2_ul").attr("style", "display: block");//第一个菜单下级菜单
}
//新增、删除时菜单处理
function initMeunShrinkChange(tId) {
    initMeunShrinkTop();
    $("#" + tId + "_switch").attr("class", "button level1 switch center_open");//第一个菜单目录
    $("#" + tId + "_ico").attr("class", "button level1 switch button ico_open");//顶层文件夹图片
    $("#" + tId + "_ul").attr("style", "display: block");//第一个菜单下级菜单
}


//点击菜单树节点点击事件
var currentSelectedNode = null; //当前选中的菜单节点
function treeNodeClick(treeId, treeNode) {
    currentSelectedNode = treeNode;
    $("#menuId").val(treeNode.id);
    reloadGrid();
}

function reloadGrid() {
    $("#aisccareprovlist").jqGrid('setGridParam', {
        postData: {"menuId": $("#menuId").val()},
        page: 1,
        pageSize: 10
    }).trigger("reloadGrid");
}

//添加菜单目录
function addMenuFolder() {
    if (currentSelectedNode == null) {
        alert("请选择一个树形菜单节点!");
        return false;
    }

    if (currentSelectedNode.pid == null || currentSelectedNode.pid == '-1') {
        alert('请选择一个子系统');
        return;
    }

    //将当前节点作为新建菜单目录的父节点
    var parentMenuId = currentSelectedNode.id;

    //当前用户选择的节点不是子系统（本身也是菜单目录），则把该菜单的父节点作为新建菜单的目录
    if (currentSelectedNode.menuType != '4') {
    }
//操作
    var url;
    url = "/menu/addMenuFolder?parentMenuId=" + parentMenuId + "&menuType=3";
    layer.open({
        type: 2,
        title: "【新增目录】",
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
}


//添加菜单节点或功能点
function addMenuNode() {

    if (currentSelectedNode == null || currentSelectedNode.pid == null
        || currentSelectedNode.pid == '-1' || currentSelectedNode.menuType != '3') {
        alert("请选择一个树形菜单目录节点!");
        return false;
    }

    //将当前节点作为新建菜单目录的父节点
    var parentMenuId = currentSelectedNode.id;

    var url;
    url = "/menu/addMenuFolder?parentMenuId=" + parentMenuId + "&menuType=1";
    layer.open({
        type: 2,
        title: "【新增菜单】",
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
}


//修改菜单节点
function updateMenu() {
    var rowIds = $("#aisccareprovlist").jqGrid('getGridParam','selarrrow');
    if (rowIds.length == 0) {
        alert("请从菜单节点表格中选择一行！");
        return;
    }
    if (rowIds.length > 1) {
        alert("不能同时从菜单节点表格中选择多行！");
        return;
    }
    var rowDatas = $("#aisccareprovlist").getRowData(rowIds[0]);
    var menuId = rowDatas['menuId'];
    var menuType = rowDatas['menuType'];
    var url = "/menu/updateMenu.ajax?menuId=" + menuId;
        layer.open({
        type: 2,
        title: "【修改节点信息】",
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
}

//删除菜单
function delmenu() {
    var rowIds = $("#aisccareprovlist").jqGrid('getGridParam','selarrrow');
    if (rowIds.length == 0) {
        alert("请从菜单节点表格中至少选择一行！");
        return;
    }
    var rowDatas = $("#aisccareprovlist").getRowData(rowIds[0]);
    var ids = rowDatas['menuId'];
    for (i = 1; i < rowIds.length; i++) {
        ids = ids + "," +$("#aisccareprovlist").getRowData(rowIds[i])['menuId'];
    }
    if (confirm('确定要删除吗?')) {
        setTimeout(function () {
            $.ajax({
                url: GLOBAL.WEBROOT + '/menu/delMenu.ajax',
                data: {"menuIds": ids},
                dataType: 'json',
                method: "post",
                success: function (data) {
                    var result = data.result;
                    if (result) {
                        alert(data.message);
                        initMenuTree();
                        $("#menuId").val(currentSelectedNode.id);
                        reloadGrid();
                        initMeunShrinkChange(currentSelectedNode.tId);
                    } else {
                        alert("操作失败，请稍后再试！");
                    }
                },
                complete: function () {
                    doQuery();
                    initMenuTree();
                    $("#menuId").val(currentSelectedNode.id);
                    reloadGrid();
                    initMeunShrinkChange(currentSelectedNode.tId);
                }
            });
        }, 100);

    }

}

function sortMenu() {
    if (currentSelectedNode == null) {
        alert("请选择一个树形菜单上选择一个需要排序的菜单!");
        return false;
    }

    var menuId = currentSelectedNode.id;
    var menuType = currentSelectedNode.menuType;


    var url = "/menu/sortMenu.ajax?parentMenuId=" + menuId+"&menuType="+menuType;
    layer.open({
        type: 2,
        title: "【修改节点信息】",
        area: ['800px', '470px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
}


var TableList = {
    tree: null,
    init: function () {
        TableList.initEl();
    },
    initEl: function () {
        initMenuTree();

        //添加菜单目录
        $("#addMenuDir").click(function () {
            addMenuFolder();
        });

        //添加菜单
        $("#addMenu").click(function () {
            addMenuNode();

        });

        $("#updateMenu").click(function () {
            updateMenu();

        });


        $("#delBt").click(function () {
            delmenu();

        });
        $("#sortBt").click(function () {
            sortMenu();

        });


    },
    show: function () {
        var menu = TableList.getSelectMenu();
        if (menu == null) {
            alert("请选择节点!");
            return false;
        }
        var menuId = menu.id.split(",")[0];
        if (menuId == '-1' || menuId == '-2') {
            alert("根节点为虚拟节点,不可查看!");
            return false;
        }
        var menuId = menu.id.split(",")[0];

        WEB.openWin({
            url: GLOBAL.WEBROOT + '/sysmanage/menuManage!toUpdate.action?searchModel.menuId=' + menuId + "&searchModel.isShow=1",
            title: "【查看】",
            id: 1,
            top: 10,
            width: 500,
            height: 360,
            onClose: function (datas) {
                initMenuTree();
            }
        });

    },


    removeHoverDom: function (treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
    },
    removeHoverDom: function (treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
    },
    showRemoveBtn: function (treeId, treeNode) {
        //return !treeNode.isFirstNode;
        return true;
    },
    showRenameBtn: function (treeId, treeNode) {
        //return !treeNode.isLastNode;
        return true;
    },

    setCheck: function () {
        var zTree = $.fn.zTree.getZTreeObj("menuTree"),

            type = {
                "Y": "s",
                "N": "s"
            };

        zTree.setting.check.chkboxType = type;

    },
    reloadTree: function (node) {
        TableList.tree.expandNode(node, true, null, null, null);
    }

};

$(function () {
    $("#menuId").val("0");
    selectList();
});


function doQuery() {
    $("#menuId").val("0");
    selectList();
}
//查询列表
function selectList() {
    var grid_selector = "#aisccareprovlist";
    var pager_selector = "#aisccareprov_pager";
    var data = $("#menuId").val();
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/menu/queryMenuList.ajax",
        mtype: "post",
        datatype: "json",
        height: '100%',
        width: '100%',
        postData: {"menuId": data},
        colNames: ['menuId','名称', '链接', '顺序号', '类型', '启用状态', '备注'],
        colModel: [
            {name: 'menuId', index: 'menuId', width: 60, sortable: false, align: 'center',hidden:true},
            {name: 'menuName', index: 'menuName', width: 60, sortable: false, align: 'center'},
            {name: 'menuUrl', index: 'menuUrl', sortable: false, align: 'center'},
            {name: 'menuOrder', index: 'menuOrder', sortable: false, align: 'center'},
            {
                name: 'menuType', index: "menuType", sortable: false, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    if (cellvalue == '1') {
                        return "菜单";
                    } else if (cellvalue == '2') {
                        return "功能点";
                    } else if (cellvalue == '3') {
                        return "目录";
                    } else if (cellvalue == '4') {
                        return "子系统";
                    } else if (cellvalue == '5') {
                        return "虚拟目录";
                    }
                    return "虚拟目录";
                }
            },
            {
                name: 'state', index: "state", sortable: false, align: 'center',
                formatter: function (cellvalue, options, rowObject) {
                    if (cellvalue == '1') {
                        return "启用";
                    } else if (cellvalue == '0') {
                        return "禁用";
                    }
                    return "禁用";
                }
            },
            {name: 'menuDesc', index: "menuDesc", sortable: false, align: 'center'}
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
