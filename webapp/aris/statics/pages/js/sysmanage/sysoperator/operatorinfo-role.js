var roleName;
var sysType = 25
$(function() {
			TableList.init();
			
			//查询事件
			$("#btnQuery").click(function(){
				roleName = $("#roleName").val();
				TableList.init();
			});
			
			//重置事件
			$("#btnReset").click(function(){
				$("#roleName").val("");
			});		

		});

		

var TableList = {
	tree : null,
	init : function() {
		TableList.initEl();
	},
	initEl : function() {
		$("#inputForm").validate({
						rules : {},
						messages : {},
						submitHandler : function() {
							TableList.commit();
						}
					});
		
		TableList.initMenuTree();
		
		$("#closeBt").click(function(){
			TableList.close();
		});
	},
	close:function(){
        closeLayer();
	},
	commit:function(){
		

	// 授权
    $("#roleIds").val("");//先清空一次	
	if (TableList.tree && TableList.tree != null) {
		var roleIds = "";
		var roleNodes = TableList.tree.getCheckedNodes();
		if (roleNodes && roleNodes.length > 0) {
			for (var i = 0; i < roleNodes.length; i++) {
				if(roleNodes[i].id!='-1'){
					roleIds += roleNodes[i].id + ",";
				}
			}
			$("#roleIds").val(roleIds.substring(0,roleIds.length-1));
		} else {
			//alert('请进行授权!');
			//return false;
		}
		if(roleIds==''){
			//alert('请进行授权!');
			//return false;
		}
	} else {
		//alert('请进行授权!');
		//return false;
	}
	var roleIds = $("#roleIds").val();
	var operatorCode = $("#operatorCode").val();
	displayExecuteLayer();
	var url="/sysmanage/sysoperator/setRoles";
	$.appAjax({
			async: true,
			dataType : 'json',
			method : "post",
			data:{"roleIds":roleIds,"operatorCode":operatorCode},
			url: GLOBAL.WEBROOT + url,
			success: function (data) {
				hideExecuteLayer();
				if (data.result=='success') {
                    infoSuccess('温馨提示',"保存成功！");
				} else {
					info('温馨提示','保存失败！' + data.result);
				}
			}
		}
	);
	},
	
	
	// 加载菜单
	initMenuTree : function(node) {
		
		var chkStyle = "checkbox";
		var setting = {
			view : {
				selectedMulti:  false
			},
			edit : {
				enable : false,
				editNameSelectAll : true
			},
			check : {
				enable : true,
				chkStyle : "checkbox",
				checkType : "all"
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
					TableList.tree.checkNode(treeNode, !treeNode.checked, true);
				}
			}
		};

        var operatorCode = $("#operatorCode").val();
        var roleName = $("#roleName").val();
        //回填数据准备
        $.appAjax({
			url : GLOBAL.WEBROOT + "/sysmanage/sysoperator/getExistRoles",
			dataType : 'json',
			method : "post",
			data:{"operatorCode":operatorCode,"roleName":roleName},
			success : function(data) {
				if(data.result=="success"){
					$("#roleIds").val(data.existRole);
				}				
			},
			complete : function() {
			}
		});
        
        //角色初始化
		$.appAjax({
			url : GLOBAL.WEBROOT + "/sysmanage/sysoperator/initRoleTrees",
			dataType : 'json',
			method : "post",
			data:{"roleName":roleName},
			success : function(data) {
				TableList.tree = $.fn.zTree.init($("#roleTree"), setting, data);
				TableList.tree.expandAll(true);
				// 回填
				var roleIds = $("#roleIds").val();
				if(roleIds&&roleIds!=''){
					var idsArray = roleIds.split(",");
					for(var i=0;i<idsArray.length;i++){
							 TableList.tree.checkNode(TableList.tree.getNodeByParam("id",
								idsArray[i]));
					}
				}
				
			},

			complete : function() {
			}
		});

	},
	setCheck : function() {
		var zTree = $.fn.zTree.getZTreeObj("menuTree"),

		type = {
			"Y" : "s",
			"N" : "s"
		};

		zTree.setting.check.chkboxType = type;

	},
	reloadTree:function(node){
		TableList.tree.expandNode(node, true, null, null, null);
	}

};

function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}