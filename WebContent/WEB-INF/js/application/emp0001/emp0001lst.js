function move(form,url,id) {
	if (id) {
		form.paramEmployeeId.value=id;
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

	document.getElementById('employeeId').value = "";
	document.getElementById('employeeName').value = "";

	var sexMenObj = document.getElementById('sexMen');
	sexMenObj.checked = true;
	var sexWomenObj = document.getElementById('sexWomen');
	sexWomenObj.checked = true;

	document.getElementById('departmentId').options[0].selected = true;

	document.getElementById('joinedYmdFrom').value = "";
	document.getElementById('joinedYmdTo').value = "";
}

window.onload = function() {

	// 検索結果が0件の場合はCSV出力ボタンを非活性にする
	const button = document.getElementById("csvButton");
	if(document.getElementById('pageSize').value == '0'){
		button.disabled = true;
	}else{
		button.disabled = false;
	}

}