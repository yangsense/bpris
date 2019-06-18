$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    }); 
	$("#quoteReportBtn").click(function(){
		quoteReport();
	}); 
      
	CKEDITOR.on('instanceReady',function(event){
		 editor=event.editor; 
		 editor.setReadOnly(true); //只读
	})
	
});
//关闭
function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
//报告内容引用
function quoteReport(){
	var eEditor = CKEDITOR.instances.reportExam;      
	var reportExam = eEditor.getData();
	var rEditor = CKEDITOR.instances.reportResult; 
    var reportResult = rEditor.getData();
	
    var peEditor = parent.CKEDITOR.instances.reportExam;
    peEditor.setData(reportExam);
    var prEditor = parent.CKEDITOR.instances.reportResult; 
    prEditor.setData(prEditor);    
    
	closeDiv();
}