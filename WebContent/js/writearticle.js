$(function(){
	$('#upload').attr('src',"/SSMProject/user/getImage?user=chenyao");
});
function writeArticle(){
	var title = $("#texttitle").val();
	var content = $("#textcontent").val();
	// Ajax POST方法
	$.post("/SSMProject/article/insert", {
		title:title,
		content:content
	}, function(result) {
		var info = '';
		if (result.status == 0) {
			info = "发布成功！";
		} else{
			info = "发布失败！";
		}
		$("#showResultModalBody").html(info);
 		$("#showResultModal").modal("show");
	});
}
