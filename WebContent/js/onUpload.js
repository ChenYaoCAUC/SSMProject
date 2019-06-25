$(function(){
	$('#upload').attr('src',"/SSMProject/user/getImage?user=chenyao");
});
function onUpload(){
	var formData = new FormData(document.getElementById("uploadForm"));
	$.ajax({
	      type:"POST",
	      url:"/SSMProject/user/upload",
	      data:formData,
	      mimeType:"multipart/form-data",
	      contentType: false,
	      cache: false,
	      processData: false,
	      success: function (result) {
	    	 var result=JSON.parse(result);
	     	 if(result.status == 0) {
	     		alert(result.reason);
	     	 } else {
	     		 alert(result.reason);
	     	 }
	      }
	});
	
}
