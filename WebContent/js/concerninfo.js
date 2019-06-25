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
	var args = getArgs();
	if (typeof(args.user) == "undefined" || args.user == null) {
		var errorMsg = '<br/><div class="alert alert-danger" role="alert">温馨提示：无法查询该用户信息！</div>';
		$("#error").html(errorMsg);
	} else{
		var info="";
		 $.get("/SSMProject/user/getInfomation",{
			 user:args.user
		 },function(result){
			 if(result.status==0){
				 $('#upload1').attr('src',"/SSMProject/user/getImage?user="+args.user);
				 info += '<h6>性别：'+result.sex+'</h6>';
				 info += '<h6>个性签名:'+result.introction+'</h6>';
			 }
			 $("#me").html(info);	
		 });
	}	
});