function signup() {
	// jQuery方法取出DOM对象的value值
	var u = $("#username").val();
	var p = $("#password").val();
	if (u == "" || p == "") {
		alert("请完善注册信息");
		return;
	}
	// Ajax POST方法
	$.post("/SSMProject/user/signup", {
		username: u,
		password: p
	}, function(result) {
		alert(result.reason);
		if (result.status == 0) {
			window.location.href="/SSMProject/signin.html";
		} 
	});
}