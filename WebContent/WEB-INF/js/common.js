function doPost(form,url) {
	form.action=url;
	form.submit();
}
function doPostMultipart(form,url,btn) {
	form.enctype="multipart/form-data";
	form.action=url;
	form.buttontype.value = btn.name;
	form.submit();
}