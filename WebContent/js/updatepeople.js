$(function(){
	$.get("/SSMProject/user/getUsername",{
	},
		function(result1){
		 var username = result1.username;
		 var info="";
		 $.get("/SSMProject/user/getInfomation",{
			 user:username
		 },function(result){
			 if(result.status==0){
				 $('#upload1').attr('src',"/SSMProject/user/getImage?user="+username);
				 $('#upload2').attr('src',"/SSMProject/user/getImage?user="+username);
				 info += '<h6>性别：'+result.sex+'</h6>';
				 info += '<h6>个性签名:'+result.introction+'</h6>';
			 }
			 $("#me").html(info);	
		 });
	});
	
});
function updatepeople(){
	var introction = $("#introductioninput").val();
	var sex = $("#sexid").val();
	$.post("/SSMProject/user/updatepeople",{
		introduction:introction,
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
	    	  var result=JSON.parse(result);
	    	  if(result.status==0)
	    	  {
	    		 /* document.getElementById('upload').src = result.avatar;*/
				  $('#upload').attr('src',"/SSMProject/user/getImage?user=");
	    	  } 
	      }   	
	});	
}