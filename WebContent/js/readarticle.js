$(function(){
	refreshByUsername();
});

function refreshByUsername(){
	$.get("/SSMProject/article/refreshByUsername", {
	}, function(result) {
		var article = '';
		if(result.status==0){
			for(var i=0;i<result.article.length;i++){
				article +='<div class="card text-center"><div class="card-body"><h5 class="card-title">'+result.article[i].title+'</h5>';
				article +='<p class="card-text">'+result.article[i].content+'</p></div>';
				article +='<div class="card-footer"><a class="btn btn-link" href="/SSMProject/updatearticle.html?id='+result.article[i].id+'">编辑文章</a></div></div>';
			}	
		}else{
			article +='<div class="card-body"><h5 class="card-title">标题</h5>';
			article +='<p class="card-text">内容</p></div>';	
		}		
		$("#article").html(article);
	});
}