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
	if (typeof(args.error) == "undefined" || args.error == null) {
	} else if(args.error=1){
		$(document).attr("title", "出错了 - zhihu");
		var errorMsg = '<br/><div class="alert alert-danger" role="alert">温馨提示：登录失败，请尝试刷新</div>'
		$("#error").html(errorMsg);
	}	
});