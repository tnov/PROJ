function move(form,url,id) {
	if (id) {
		form.paramEmployeeId.value=id;
	}
	form.action=url;
	form.submit();
}