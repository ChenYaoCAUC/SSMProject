$(function(){
	refreshByConcern();
});
function refreshByConcern(){
	$.get("/SSMProject/article/refreshByConcern", {
	}, function(result){
		var article = '';
		if(result.status==0){
			for(var i=0;i<result.article.length;i++){
				article +='<div class="card text-center"><div class="card-body"><h5 class="card-title">'+result.article[i].title+'</h5>';
				article +='<p class="card-text">'+result.article[i].content+'</p></div>';
				article +='<div class="card-footer text-muted">'+result.article[i].author+'</br>';
				article +='<button type="button" class="btn btn-outline-info" onclick="ConcernHiddenId(\''+result.article[i].author+'\')">关注<input id="idConcernModel" type="hidden" /></button></div>';
				article +='<class="card-footer"><div class="accordion" id="accordionExample"><h2 class="mb-0">';
				article +='<button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseOne'+result.article[i].id+'" aria-expanded="true" aria-controls="collapseOne">查看评论详情</button>';
				article +='<button type="button" class="btn btn-danger" onclick="likeHiddenId(\''+result.article[i].id+'\')">赞<input id="idLikeModel" type="hidden" /><span id="likecount'+result.article[i].id+'">0</span></button>';
				article +='<button type="button" class="btn btn-link" data-toggle="modal" data-target="#writeModel" onclick="writeHiddenId(\''+result.article[i].id+'\')" >写评论</button></h2></div>';
				article +='<div id="collapseOne'+result.article[i].id+'" class="collapse hide" aria-labelledby="headingOne" data-parent="#accordionExample">';
				article +='<div id="articlecomment'+result.article[i].id+'" class="card-body">暂时没有评论~说点什么吧 ~</div>';
				article +='</div></div>';
		}
			}else{
			article +='<div class="card-body"><h5 class="card-title">标题</h5>';
			article +='<p class="card-text">内容</p></div>';	
		}		
		$("#article").html(article);
		for(var i=0;i<result.article.length;i++) {
				var id = result.article[i].id;
				$.get("/SSMProject/likearticle/select",{
					article_id:id
				},
					function(result){
					if(result.status==0){		
						$("#likecount"+result.article_id).html(result.count);	
					}else{
						alert(result.status);
					}
			});
		
		}
		for(var i=0;i<result.article.length;i++) {
			var articleid = result.article[i].id;
			$.get("/SSMProject/articlecomment/select",{
				id:articleid
				},
			function(result1){
				var articlecomment = '<ul class="list-group">'
			    if(result1.articlecomment.length==0){
			    	}else{			    		
						for(var j=0;j<result1.articlecomment.length;j++){	
							articlecomment +='<li class="list-group-item "><div id="articlecomment'+result1.articlecomment[j].article_id+'">第'+(j+1)+'条评论：'+result1.articlecomment[j].content;
							articlecomment +='<button type="button" class="btn btn-outline-danger" onclick="likeCommentHiddenId(\''+result1.articlecomment[j].comment_id+'\')">赞<span id="likecommentcount'+result1.articlecomment[j].comment_id+'">0</span><input id="idLikeCommentModel" type="hidden" /></div>';
							articlecomment +='<button type="button" class="btn btn-link" onclick="DeleteHiddenId(\''+result1.articlecomment[j].comment_id+'\')">删除<input id="idDeleteModel" type="hidden" /></button></li>';								
						}
						articlecomment+='</ul>';
						$("#collapseOne"+result1.articlecomment[0].article_id).html(articlecomment);	
						for(var j=0;j<result1.articlecomment.length;j++){	
							var comment_id = result1.articlecomment[j].comment_id;
							$.get("/SSMProject/likecomment/select",{
								comment_id:comment_id
								},
								function(result2){
									if(result2.status==0){		
										$("#likecommentcount"+result2.comment_id).html(result2.count);	
									}else{
										alert(result2.status);
									}
							});		
						}
				}				
			});
		}	
		
	});
	$('#upload').attr('src',"/SSMProject/user/getImage?user=chenyao");
}
function writeHiddenId(id) {
	$("#idCommentModel").val(id);
}
function likeHiddenId(id){
	$("#idLikeModel").val(id);
	likearticle();
}
function ConcernHiddenId(author){
	$("#idConcernModel").val(author);
	concernarticle();
}
function DeleteHiddenId(id){
	$("#idDeleteModel").val(id);
	deleteComment();
}
function likeCommentHiddenId(id){
	$("#idLikeCommentModel").val(id);
	likecomment();
}


function writecomment(){
	var content = $("#message-text").val();
	var id = $("#idCommentModel").val();
	if(content==''){
		$("#showResultModalBody").html("评论不能为空！");
 		$("#showResultModal").modal("show");	
		return;
	}
	
	$.get("/SSMProject/articlecomment/insert",{
			article_id:id,
			content:content},
			function(result1){
				var info = '';
				if (result1.status == 0) {
					info = "发布成功！";
			   }else{
					info = "发布失败！";
				}
				$("#showResultModalBody").html(info);
		 		$("#showResultModal").modal("show");
		 		$("#message-text").val('');
		 		refreshByConcern();
			});	
	}
function likearticle(){
	var id = $("#idLikeModel").val();
	$.get("/SSMProject/likearticle/like",{
		article_id:id
	},
		function(result){
		if(result.status==0){}
			$("#showResultModalBody").html(result.reason);
			$("#showResultModal").modal("show");	
			refreshByConcern();
	});
}

function concernarticle(){
	var author = $("#idConcernModel").val();
	$.get("/SSMProject/Concern/concern",{
		wasfuns:author
	},function(result){	
		$("#showResultModalBody").html(result.reason);
 		$("#showResultModal").modal("show");	
	});
}

function deleteComment(){
	var id = $("#idDeleteModel").val();
	var info = '';
	$.get("/SSMProject/articlecomment/delete",{
		comment_id:id
	},function(result){
		if(result.status==0){
			info = "删除成功！";
		}else{
			info = "删除失败！"
		}
		$("#showResultModalBody").html(info);
 		$("#showResultModal").modal("show");
 		refreshByConcern();
	});
}
function likecomment(){
	var id = $("#idLikeCommentModel").val();
	$.get("/SSMProject/likecomment/like",{
		comment_id:id
	},
		function(result){
		$("#showResultModalBody").html(result.reason);
 		$("#showResultModal").modal("show");	
 		refreshByConcern();
	});
	
}
