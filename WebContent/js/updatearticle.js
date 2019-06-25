function getArgs(){
    var args = {};
    var match = null;
    var search = decodeURIComponent(location.search.substring(1));
    var reg = /(?:([^&]+)=([^&]+))/g;
    while((match = reg.exec(search))!==null){
        args[match[1]] = match[2];
    }
    return args;
}
$(function(){
	$('#upload').attr('src',"/SSMProject/user/getImage?user=chenyao");
	var args = getArgs();
	if (typeof(args.id) == "undefined" || args.id == null) {
	} else{
		$.get("/SSMProject/article/refreshByUuid", {
			id:args.id
		}, function(result) {
			if(result.status==0){
				$("#texttitle").val(result.article[0].title);
				$("#textcontent").val(result.article[0].content);
			}else{
			}		
		});
	}	
});
function updateArticle(){
	var title = $("#texttitle").val();
	var content = $("#textcontent").val();
	var args = getArgs();
	// Ajax POST方法
	if(typeof(args.id)=="undefined"||args.id == null){}
	else{
		$.post("/SSMProject/article/update", {
		title:title,
		content:content,
		id:args.id
		}, function(result) {
			var info = '';
			if (result.status == 0) {
				info = "发布成功！";
			} else{
				info = result.reason;
			}
			$("#showResultModalBody").html(info);
 			$("#showResultModal").modal("show");
		});
	}
}
