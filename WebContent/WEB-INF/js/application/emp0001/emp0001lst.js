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