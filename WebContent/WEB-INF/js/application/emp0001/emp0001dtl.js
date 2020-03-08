window.onload = function() {

	// 新規登録時はパスワード項目を非表示
	if(document.getElementById('mode').value == '0'){
		document.getElementById("password").style.display="none";
		document.getElementById("passwordChk").style.display="none";
	}
}

function move(form,url) {
	form.backMode.value="1";
	form.action=url;
	form.submit();
}