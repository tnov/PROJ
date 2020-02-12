function move(form,url) {
	form.backMode.value="1";
	form.action=url;
	form.submit();
}