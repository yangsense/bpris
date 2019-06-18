var orgName =""
$(function() {
	//查询列表初始化
	TableList.init();

	//查询事件
	$("#btnQuery").click(function(){
		orgName = $("#orgName").val();
		TableList.init();
	});

	//重置事件
	$("#btnReset").click(function(){
		$("#orgName").val("");
	});
    $("#queryBox").flexSearch($.formParams("#inputForm"));
});
var orgInfo = "";
var TableList = {
	tree : null,
	init : function() {
		TableList.initEl();
	},
	initEl : function() {
		//提交
		$("#inputForm").validate({
						rules : {},
						messages : {},
						submitHandler : function() {
							TableList.commit();
						}
					});
		
		TableList.initMenuTree();
		
		$("#closeBt").click(function(){
			closeLayer();
			
		});

	},
	close:function(){
        closeLayer();
	},
	commit:function(){
		

	// 授权
	if (TableList.tree && TableList.tree != null) {
		
		var orgId = "";
		var orgName = "";
		var orgCode = "";
		var orgNodes = TableList.tree.getCheckedNodes();
		if (orgNodes && orgNodes.length > 0) {
			if(orgNodes[0].id!='-1'){
				orgId += orgNodes[0].id;
				orgName += orgNodes[0].name; 
				orgCode += orgNodes[0].code; 
			}
			orgInfo =  orgId + "," + orgName + ","+orgCode;
						
		} else {
			alert('请进行授权!');
			return false;
		}
		if(orgId==''){
			alert('请进行授权!');
			return false;
		}
	} else {
		alert('请进行授权!');
		return false;
	}
		setParantOrg(orgInfo);
	},
	
	
	// 加载菜单
	initMenuTree : function(node) {
		var setting = {
			view : {
				selectedMulti:  false
			},
			edit : {
				enable : false,
				editNameSelectAll : true
			},
			check : { 
				enable: true,
				chkStyle: "radio",
				radioType: "all"  
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
			async: {
			enable: true,
				url:GLOBAL.WEBROOT + "/sysmanage/sysoperator/initOrgTreeByCurrentOrgId?orgNameParam="+encodeURI(encodeURI(orgName))+"&currentOrgId="+parent.parent.parent.$("#currentOrgId").val(), //归属机构不需要选择岗位
				autoParam:["id"]
		    },
			callback : {
				onClick : function(evt, treeId, treeNode) {
					TableList.tree.checkNode(treeNode, !treeNode.checked, true);
				},
				onAsyncSuccess:function(event, treeId, treeNode, msg) {
                    var currentOrgId= parent.parent.parent.$("#currentOrgId").val();
                    if(currentOrgId && currentOrgId.length>0 && currentOrgId!="-1") {
                        var currentNode = TableList.tree.getNodeByParam("id", currentOrgId);
                        TableList.tree.checkNode(currentNode);
                    }
					//回显处理
					var selectNodes = TableList.tree.getCheckedNodes(true);
					if (!selectNodes||selectNodes.length==0) {//无选中项
						var oldOrgId = $("#orgIds").val();
						if (oldOrgId && oldOrgId.length > 0 && oldOrgId != null && oldOrgId != "") {
							var oldSelectTreeNode = TableList.tree.getNodeByParam("id", oldOrgId);
							if(oldSelectTreeNode)//修改时
								TableList.tree.checkNode(oldSelectTreeNode);
						}
					}
			   }
			}
		};

		TableList.tree = $.fn.zTree.init($("#roleTree"), setting, [ {id : '-1',name : '组织机构', open : true,isParent : true ,nocheck:true} ]);
		var treeObj = $.fn.zTree.getZTreeObj("roleTree");
		var nodes = treeObj.getNodes();
		if (nodes.length>0) {
			treeObj.reAsyncChildNodes(nodes[0], "refresh");
		}



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
function setParantOrg(datas){
    var index = parent.layer.getFrameIndex(window.name);
    parent.setOp2BelongOrg(datas,index);

}