function move(form,url) {
	form.menuMovePath.value=url;
	form.submit();
}

function doPost(form,url) {
	form.action=url;
	form.submit();
}