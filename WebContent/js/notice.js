$(function(){
	getConcern();
});
function getConcern(){
	$.get("/SSMProject/Concern/concerncount",{},
			function(result){
		var count='';
		count += '<nav aria-label="breadcrumb"><ol class="breadcrumb">';
		count += '<li class="breadcrumb-item active" aria-current="page">您已关注';
		count += result.count+'人</li></ol></nav>';
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
			    concern += '<a href="#" class="btn btn-primary">资料详情</a></div></div></div>';
			}
			$("#concernPeople").html(concern);
		}	
	});
	

}
