$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    }); 
    
	//只读
	com.ai.bdx.util.disableAll("studyInfoForm");
 
});
 
function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

 