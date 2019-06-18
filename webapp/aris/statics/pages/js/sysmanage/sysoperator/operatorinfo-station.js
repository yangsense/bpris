var orgName;
$(function() {
    		var flag = initStation();
			TableList.init();
			
			$("#station").change(function(){
				TableList.init();
			});
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
    $("#closeBt").click(function(){
        closeLayer();

    });
});

function initStation(){
	var flag;
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + "/sysmanage/sysoperator/getAllOrgs",
        success: function (data) {
            var stationBeans = data.stationBeans;
            if (stationBeans&&stationBeans.length>0) {

				var stationSelect = $("#station");
				stationSelect.empty();
				for (var i = 0; i < stationBeans.length; i++) {
					stationSelect.append("<option value='" + stationBeans[i].stationCode + "'>" + stationBeans[i].stationName + "</option>");
				}
				stationSelect.val(stationBeans[0].stationCode);
				stationSelect.select2();
            }
            flag = true;
        }
    });
    return flag;
}

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
	if (TableList.tree && TableList.tree != null) {
		var orgIds = "";
		var orgNodes = TableList.tree.getCheckedNodes();
		if (orgNodes && orgNodes.length > 0) {
			for (var i = 0; i < orgNodes.length; i++) {
				if(orgNodes[i].id!='-1'){
					orgIds += orgNodes[i].id + ",";
				}
			}
			$("#orgIds").val(orgIds.substring(0,orgIds.length-1));
		} else {
			alert('请进行授权!');
			return false;
		}
		if(orgIds==''){
			alert('请进行授权!');
			return false;
		}
	} else {
		alert('请进行授权!');
		return false;
	}
        var orgIds = $("#orgIds").val();
        var operatorCode = $("#operatorCode").val();
        var stationCode = $("#station").val();
	displayExecuteLayer();
	var url="/sysmanage/sysoperator/saveOperator2Org";
	$.appAjax({
			async: true,
			dataType : 'json',
			method : "post",
			data:{"orgIds":orgIds,"operatorCode":operatorCode,"stationCode":stationCode},
			url: GLOBAL.WEBROOT + url,
			success: function (data) {
				hideExecuteLayer();
				if (data.result=='success') {
                    infoCall('温馨提示',"保存成功！",saveSuccess);
				} else {
					info('温馨提示','保存失败！' + data.result);
				}
			}
		}
	);

	},
	
	
	// 加载菜单
	initMenuTree : function(node) {
		//已有组织机构回填		
        var stationCode = $("#station").val();
        var operatorCode = $("#operatorCode").val();
		var orgName = $("#orgName").val();
        //回填数据准备
        $.appAjax({
			url : GLOBAL.WEBROOT + "/sysmanage/sysoperator/getExistOrgs",
			dataType : 'json',
			method : "post",
			data:{"stationCode":stationCode,"operatorCode":operatorCode,"orgName":orgName },
			success : function(data) {
				if(data.result=="success"){
					$("#orgIds").val(data.orgIds);
				}				
			},
			complete : function() {
			}
		});
        
		
		var chkStyle = "checkbox";
		var setting = {
			view : {
				/*addHoverDom : TableList.removeHoverDom,
				removeHoverDom : TableList.removeHoverDom,*/
				selectedMulti:  false
			},
			edit : {
				enable : false,
				editNameSelectAll : true
				/*,
				showRemoveBtn : TableList.showRemoveBtn,
				showRenameBtn : TableList.showRenameBtn*/
			},
			check : {
				enable : true,
				chkStyle : "checkbox",
				chkboxType: {"Y": "", "N": ""}//不级联父节点选择  
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
					url:GLOBAL.WEBROOT + "/sysmanage/sysoperator/initOrgTree?stationCode="+stationCode+"&orgNameParam="+encodeURI(encodeURI(orgName)), //归属机构不需要选择岗位
					autoParam:["id"]
			},
			callback : {
				onClick : function(evt, treeId, treeNode) {
					TableList.tree.checkNode(treeNode, !treeNode.checked, true);
				},
			    onAsyncSuccess:function(event, treeId, treeNode, msg) {
					var selectNodes = TableList.tree.getCheckedNodes(true);
					if (!selectNodes||selectNodes.length==0) {//无选中项
						var oldOrgId = $("#orgIds").val();
						if (oldOrgId && oldOrgId.length > 0 && oldOrgId != null && oldOrgId != "") {
							var idsArray = oldOrgId.split(",");
							for(var i=0;i<idsArray.length;i++){
								var oldSelectTreeNode = TableList.tree.getNodeByParam("id", idsArray[i]);								 
								TableList.tree.checkNode(oldSelectTreeNode);
							}
							
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

function saveSuccess(){
    parent.reloadGrid();
    closeLayer();
}