function doPost(form,url) {
	form.action=url;
	form.submit();
}
function doPostMultipart(form,url) {
	form.enctype="multipart/form-data";
	form.action=url;
	form.submit();
}