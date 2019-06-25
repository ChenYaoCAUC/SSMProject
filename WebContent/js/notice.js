$(function(){
	getConcern();
});

function getConcern(){
	$.get("/SSMProject/Concern/concerncount",{},
			function(result){
		var count='';
		count +='<ul class="list-group"><li class="list-group-item d-flex justify-content-between align-items-center">这是关注';
	    count +='<span class="badge badge-primary badge-pill">您已关注：'+result.count+'人</span></li></ul>';
		$("#countConcern").html(count);
	});
	$.get("/SSMProject/Concern/selectwasfuns",{},
	function(result){
		var concern = '';
		if(status == 0)
		{
			for(var i=0;i<result.user.length;i++)
			{
				concern += '<div class="col-sm-6"><div class="card"><div class="card-body">';
			    concern += '<h5 class="card-title">'+result.user[i]+'</h5>';
			    concern += '<a href="/SSMProject/concerninfo.html?user='+result.user[i]+'" class="btn btn-primary">资料详情</a></div></div></div>';
			}
			$("#concernPeople").html(concern);
		}	
	});

}
