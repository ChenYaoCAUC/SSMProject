function updatepeople(){
	var introction = $("#introductioninput").val();
	var sex = $("#sexid").val();
	$.post("/SSMProject/user/updatepeople",{
		introction:introction ,
		sex:sex
	},function(result){
		if(result.status==0){
			var info = '';
			if (result.status == 0) {
				info = "更新成功！";
			} else{
				info = "更新失败！";
			}
			$("#updateshowResultModalBody").html(info);
	 		$("#updateshowResultModal").modal("show");
		}
	});
}
function uploadimage(){
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
	    	  if(result.status==0)
	    	  {
	    		 /* document.getElementById('upload').src = result.avatar;*/
				  $('#upload').attr('src',result.avatar);
	    	  } 
	      }   	
	});	
}

