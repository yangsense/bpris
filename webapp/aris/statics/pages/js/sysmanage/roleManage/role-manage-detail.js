$(function() {
    $("#sysType").select2();
    InfoBean.init();

});

var InfoBean = {
    tree : null,
    init : function() {
        InfoBean.initEl();
        InfoBean.initButton();
        InfoBean.initElBind();
    },
    initButton:function () {
        var type = $("#dataType").val();
        if(type=="query"){
            $("#saveBtn").hide();
            $("#roleName").attr("readonly","true");
            $("#sysType").attr("readonly","true");
            $("#roleDes").attr("readonly","true");
            document.getElementById("btnCheck").style.display="none";
        }
        if(type=="update"){
            $("#roleName").attr("readonly","true");
            $("#showText").text("保 存");
            document.getElementById("btnCheck").style.display="none";
        }
    },
    initForm : function() {
        $("#inputForm").validate({
            rules : {},
            submitHandler : InfoBean.commit

        });
    },
    eachChildNodes:function(node,array){
        //var array = [];
        var data = {};
        data.menuId = node.id;
        data.parentMenuId = node.pid;
        data.menuName = node.name;
        //data.isParent = node.isParent;
        array.push(data);
//		alert(node.children);
        if (node.children == undefined){
            return;
        }

        for (var i = 0; i < node.children.length; i++) {
            InfoBean.eachChildNodes(node.children[i], array);
        }
    },
    commit : function() {
        var roleName = $("#roleName").val();
        if (roleName == '' || roleName.replace(/^\s+|\s+$/g, "") == '') {
            alert('请填写角色名称!');
            return false;
        }

        if (roleName.length > 50) {
            alert('角色名称不可超过50个字符!');
            return false;
        }
        var roleDes = $("#roleDes").val();
        if (roleDes != '' && roleDes.replace(/^\s+|\s+$/g, "") != '') {
            if (roleDes.length > 250) {
                alert('角色描述不可超过250个字符!');
                return false;
            }

        }
        var nodes = InfoBean.tree.getNodes();
        var array = [];
        if(nodes&&nodes.length>0){
            for(var i=0;i<nodes.length;i++){
                InfoBean.eachChildNodes(nodes[i],array);
            }
            var menuIds = "";
            for(var i=0;i<array.length;i++){
                var menu = array[i];
                if(menu.menuId!="-1"&&menu.menuId!="-2"){
                    menuIds+=menu.menuId+",";
                }

            }
            if(menuIds!=""){
                $("#sysRole2menuList").val(menuIds.substr(0, menuIds.length - 1));
            }else {
                $("#sysRole2menuList").val("");
            }
        }else{
            $("#sysRole2menuList").val("");
        }
        var menuNodes = InfoBean.tree.getCheckedNodes();
        if (menuNodes && menuNodes.length > 0) {
            var menuIds = "";
            for (var i = 0; i < menuNodes.length; i++) {
                var id = menuNodes[i].id;
                if(id!="-1"&&id!="-2"){
                    menuIds += menuNodes[i].id+ ",";
                }
            }
            $("#role2menuList").val(menuIds.substr(0, menuIds.length - 1));// 角色对应的菜单

        }else{
            $("#role2menuList").val("");// 角色对应的菜单
        }
      save();
    },
    initElBind : function() {
        $("#btnCheck").click(function() {
            InfoBean.checkRoleName();

        });
        $("#closeBtn").click(function(){
            closeDiv();
        });
        $("#saveBtn").click(function(){
            InfoBean.commit();
        });
    },
    close : function() {
        WEB.closeWin("1");
    },
    initEl : function() {
        InfoBean.initMenuTree();
    },
    checkRoleName : function() {
        var roleName = $("#roleName").val();
        if (roleName == '' || roleName.replace(/^\s+|\s+$/g, "") == '') {
            alert('请填写角色名称!');
            return;
        }
        var data = {
            "roleName":roleName,
            "roleId":$("#roleId").val()

        }
        $.ajax({
            url : GLOBAL.WEBROOT+"/roleManageController/checkRoleName.ajax",
            dataType : 'json',
            method : "post",
            data:data,
            success : function(data) {
                alert(data.message);

            },
            complete : function() {
            }
        });

    },
    initMenuTree : function() {

        var chkStyle = "checkbox";
        var setting = {
            view : {
                showLine : true,
                selectedMulti : true
            },
            check : {
                enable : true,
                chkStyle : "checkbox",
                chkboxType : {
                    "Y" : "ps",
                    "N" : "ps"
                }
            },
            data : {
                keep : {
                    leaf : false,
                    parent : false
                },
                key : {
                    checked : "checked",
                    children : "children",
                    name : "name"
                },
                simpleData : {
                    enable : true,
                    idKey : "id",
                    pIdKey : "pid"
                }
            },
            callback : {
                onClick : function(evt, treeId, treeNode) {
                    InfoBean.tree.checkNode(treeNode, !treeNode.checked, true);
                }
            }
        };
        var sysType = $("#sysType").val();
        $.ajax({
            url : GLOBAL.WEBROOT + "/roleManageController/queryRoleMenu.ajax",
            dataType : 'json',
            method : "post",
            data:{"sysType":sysType,"roleId":$("#roleId").val()},
            success : function(data) {
                InfoBean.tree = $.fn.zTree.init($("#menuTree"),
                    setting, data.menuTree);
                InfoBean.tree.expandAll(true);
                $("#role2menuList").val(data.menuIds);
                // 回填
                var type = $("#dataType").val();
                if(type=="query" || type=="update"){
                    var menus = $("#role2menuList").val().split("_");
                    if (menus && menus.length > 0) {
                        for (var i = 0; i < menus.length; i++) {
                            InfoBean.tree.checkNode(InfoBean.tree.getNodeByParam("id", menus[i]));
                        }
                    }
                }
            },
            complete : function() {
            }
        });
    }
};
function closeDiv(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
function save() {
    //提交
    var data = $("#inputForm").serializeObject();
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/roleManageController/saveRoleManage.ajax",
        data: data,
        success: function (data) {
            if (data.result == "0") {
                layer.msg('操作成功', {icon: 6});
                parent.reloadGrid();
                setTimeout(closeDiv,1500);
            }
            else {
                layer.msg('操作失败'+data.message, {icon: 5});
            }
        }
    });
}
