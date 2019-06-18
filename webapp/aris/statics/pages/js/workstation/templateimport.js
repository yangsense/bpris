$(function () {
	
	$("#saveImport").click(function(){
		$("#uploadTemplateForm").submit();
	});
});

function checkForm(){
	var lDiv = document.getElementById("loadDiv");
	if(lDiv.style.display=='none'){
	    lDiv.style.display='block';
	    $("#saveImport").hide();
	}
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function checkAndSubmit(me){
	me.blur();
	var str=me.value;
	str=str.substr(str.lastIndexOf(".")+1); 
    if(!str.startWith('xls')){
    	alert("只可导入excel文件");
    	return;
    }
//    else{	
//		saveImport();
//	}
}

String.prototype.startWith=function(s){
  if(s==null||s==""||this.length==0||s.length>this.length)
   return false;
  if(this.substr(0,s.length)==s)
     return true;
  else
     return false;
  return true;
 }