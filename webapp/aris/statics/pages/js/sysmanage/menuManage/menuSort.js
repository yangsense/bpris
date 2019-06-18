$(function() {
			InfoBean.init();
		});

var InfoBean = {
	init : function() {
		// 点击行，加深颜色
		// 选中
		InfoBean.loadData();
		$("#closeBt").click(function() {
            closeDiv();

				});
		$("#commitBt").click(function() {

					InfoBean.save();

				});

	},
	// 加载数据
	loadData : function() {
		var parentMenuId = InfoBean.getParam("parentMenuId");
		var menuType = InfoBean.getParam("menuType");

		var url = GLOBAL.WEBROOT + "/menu/getParentMenuList.ajax?parentMenuId="+parentMenuId+"&menuType="+menuType;

		setTimeout(function() {
					$.ajax({
								url : url,
								dataType : 'json',
								method : "post",
								success : function(data) {
									InfoBean.readerData(data);
								},

								complete : function() {

								},
								error : function(XMLResponse) {
									alert('加载数据失败,请重新刷新界面!');
								}
							});
				}, 100);

	},
	readerData : function(data) {
		InfoBean.refreshConfigData(data);
	},
	onmouseover : function(obj) {
		var oldCss = InfoBean.getHexBackgroundColor(obj);
	
		if ('#ff8433' == oldCss) {
			return;
		}
		$(obj).css('background', '#b8e9ed');
	},
	onmouseout : function(obj) {
		// background:this.style.background=\'#E4E4E4\';
		// 如果是选择，则不改变样式
		
		var oldCss = InfoBean.getHexBackgroundColor(obj);
		if ('#ff8433' == oldCss) {
			return;
		}
		$(obj).css('background', '#E4E4E4');
	},
	click : function(obj, goodsId) {
		var oldCss = InfoBean.getHexBackgroundColor(obj);
		
		var selectArray = $("#configTab tr:not(:first)");
		for(var i=0;i<selectArray.length;i++){
			$(selectArray[i]).css('background', '#E4E4E4'); 
		}
		// 未选中
		if ('#ff8433' == oldCss) {
			$(obj).css('background', '#E4E4E4');
			return;
		}
		// 选中
		$(obj).css('background', '#ff8433');
	},

	refreshConfigData : function(configData) {
		var configHtml = "";
		
		if(configData&&configData.length>0){
			for (var i = 0; i < configData.length; i++) {
				var css = 'onmouseover="InfoBean.onmouseover(this);" onmouseout="InfoBean.onmouseout(this);" bgcolor="#E4E4E4" style="cursor:pointer"';
				var ev = ' onclick="InfoBean.click(this,' + configData[i].value
						+ ',false);"  ondblclick="InfoBean.dbClickToLeft(this);"';
				configHtml += '<tr ' + css + ev + ' id="' + configData[i].value
						+ '">';
				configHtml += '<td align="center" valign="middle" nowrap="nowrap"  class="bor bor_b" >'
						+ configData[i].name + '</td>';
				configHtml += '<td align="center" valign="middle" nowrap="nowrap"  class="bor bor_b">'
						+ configData[i].desc + '</td>';
				
				configHtml += '</tr>';
	
			}
		}

		$("#configTab tr:not(:first)").remove();
		$("#configTab").append(configHtml);

	},
	
	toTop : function() {

		// 获取所有选中的
		var selectArray = $("#configTab tr:not(:first)");
		var html = "";
		var obj = null;
		var select = 0;
		var topObj = null;
		for (var i = 0; i < selectArray.length; i++) {
			if ('#ff8433' == InfoBean.getHexBackgroundColor(selectArray[i])) {
				select++;
				obj = selectArray[i];
				// 移动的是第二个一下的
				if (i != 0) {
					topObj = selectArray[i - 1];
				}
			}

		}
		if (select < 1) {
			alert('请选择行!');
			return;
		}
		if (select > 1) {
			alert('只能选择一行!');
			return;
		}
		// 将两个对象调换
		if (topObj != null) {
			$(topObj).before($(obj));

		}

	},
	toDown : function() {

		// 获取所有选中的
		var selectArray = $("#configTab tr:not(:first)");
		var html = "";
		var obj = null;
		var select = 0;
		var downObj = null;
		for (var i = 0; i < selectArray.length; i++) {
			if ('#ff8433' == InfoBean.getHexBackgroundColor(selectArray[i])) {
				select++;
				obj = selectArray[i];
				// 移动的是第二个一下的
				if (i != (selectArray.length - 1)) {
					downObj = selectArray[i + 1];
				}
			}

		}
		if (select < 1) {
			alert('请选择行!');
			return;
		}
		if (select > 1) {
			alert('只能选择一行!');
			return;
		}
		// 将两个对象调换
		if (downObj != null) {
			$(downObj).after($(obj));

		}

	},
	// 提交
	save : function() {
		var selectArray = $("#configTab tr:not(:first)");
		var commitData = [];
		var count = 0;
		for (var i = 0; i < selectArray.length; i++) {
			var obj = {};
			obj.menuId = $(selectArray[i]).attr("id");
			obj.menuOrder = ++count;
			commitData.push(obj);
		}

		var parentMenuId = InfoBean.getParam("parentMenuId");
		var goodsItem = InfoBean.getParam("goodsItem");
		var configSortJson = JSON.stringify(commitData);
		var url = GLOBAL.WEBROOT + "/menu/configSortMenu.ajax";
		var data = {
				"parentMenuId" : parentMenuId,
				"configSortJson" : configSortJson
			};

			setTimeout(function() {
						$.ajax({
									url : url,
									dataType : 'json',
									method : "post",
									data : data,
									success : function(rs) {

										//$.gridUnLoading();
										if (rs.result == true) {
											alert( rs.message);
											closeDiv();
										} else {
											alert( rs.message);
                                            closeDiv();
										}
									},

									complete : function() {

									},
									error : function(rs) {
                                        closeDiv();
										//$.gridUnLoading();
									}
								});
					}, 100);

		

	},
	close : function() {
		WEB.closeWin("1");
	},
	getParam : function(name) {

		name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
		var regexS = "[\\?&]" + name + "=([^&#]*)";
		var regex = new RegExp(regexS);
		var results = regex.exec(window.location.href);
		if (results == null)
			return "";
		else
			return results[1];

	},
	getHexBackgroundColor : function(obj) {
		var rgb = $(obj).css('background-color');
		if (!$.browser.msie) {
			rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
			function hex(x) {
				return ("0" + parseInt(x).toString(16)).slice(-2);
			}
			rgb = "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
		}
		return rgb;
	}

}
function closeDiv(){
    parent.initMenuTree();
    parent.reloadGrid();

    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}