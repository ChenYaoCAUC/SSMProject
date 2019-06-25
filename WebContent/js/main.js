$(function(){
	var info="";
	$.get("/SSMProject/user/getUsername",{
	},
		function(result){
		if(result.status==0){
			info+='<nav class="navbar navbar-expand-lg navbar-light bg-light"><div class="btn-group">';
			info+='<button type="button" class="btn btn-primary" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">我的</button>';
			info+='<div class="dropdown-menu"><a class="dropdown-item" href="/SSMProject/me.html">个人中心</a>';
			info+='<div class="dropdown-divider"></div><a class="dropdown-item" href="#">设置</a>';
			info+='<div class="dropdown-divider"></div>';
			info+='<a class="dropdown-item"  href="/SSMProject/signin.html?logout=1">退出</a></div></div>';
			info+='<div class="collapse navbar-collapse" id="navbarTogglerDemo02"><ul class="navbar-nav mr-auto mt-2 mt-lg-0">';
			info+='<li class="nav-item"><a class="nav-link" href="/SSMProject/hotsearch.html">热搜</a></li>';      
			info+=' <li class="nav-item"><a class="nav-link" href="/SSMProject/articlerecommend.html">推荐</a></li>';       
			info+='<li class="nav-item"><a class="nav-link" href="/SSMProject/notice.html">关注</a></li>';      
			info+=' <li class="nav-item"><a class="btn btn-link" href="/SSMProject/index.html">返回首页</a></li> </ul>';     
			info+='<form class="form-inline my-2 my-lg-0">';        
			info+='<span class="badge badge-pill badge-light">欢迎您'+result.username+'</span>'; 	
			info+='<img id="upload" src="/SSMProject/user/getImage?user='+result.username+'" style="width: 40px; height: 40px; vertical-align: middle;" /></form></div></nav>';
		}
		 $("#navtop").html(info);	
	});
	
});