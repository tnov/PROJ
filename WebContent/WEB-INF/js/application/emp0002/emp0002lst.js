function move(form,url,id) {
	if (id) {
		form.paramCustomerId.value=id;
	}
	form.action=url;
	form.submit();
}

function search(form,url,page) {
	form.currentPage.value=page;
	doPost(form,url,page);
}


// クリアボタン押下
function clearItem() {

	document.getElementById('customerId').value = "";
	document.getElementById('customerName').value = "";

	var developmentObj = document.getElementById('development');
	developmentObj.checked = true;
	var maintenanceObj = document.getElementById('maintenance');
	maintenanceObj.checked = true;
	var operationObj = document.getElementById('operation');
	operationObj.checked = true;
	var infrastructureObj = document.getElementById('infrastructure');
	infrastructureObj.checked = true;

	var agreeStatusNoAgreeObj = document.getElementById('agreeStatusNoAgree');
	agreeStatusNoAgreeObj.checked = true;
	var agreeStatusAgreeObj = document.getElementById('agreeStatusAgree');
	agreeStatusAgreeObj.checked = true;
	var agreeStatusAgreeFinObj = document.getElementById('agreeStatusAgreeFin');
	agreeStatusAgreeFinObj.checked = true;

	document.getElementById('agreeYmdFrom').value = "";
	document.getElementById('agreeYmdTo').value = "";
}

window.onload = function() {

	// 検索結果が0件の場合
	const button = document.getElementById("csvButton");
	if(document.getElementById('pageSize').value == '0'){
		button.disabled = true;
		document.getElementById("resultDisp").style.display="none";
	}else{
		button.disabled = false;
		document.getElementById("resultDisp").style.display="block";
	}

}