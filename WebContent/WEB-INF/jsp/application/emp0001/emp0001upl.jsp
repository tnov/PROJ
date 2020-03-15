<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="application.emp0001.upload.Emp0001UplForm"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp0001/emp0001upl.css" %>">


<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp0001/emp0001upl.js" %>" ></script>
<title>社員情報取込</title>
</head>
<body>
<form id="mainForm" method="post">

<jsp:include page="../../header.jsp">
    <jsp:param name="title" value="社員情報取込" />
</jsp:include><section>
<%
List<String> errors = (List<String>)request.getAttribute("errorMessages");
if (errors != null) {
	for (int esize = 0 ; esize < errors.size() ; esize++) {
%>
		<label><%= errors.get(esize) %></label>
<%
	}
}
%>
<%
List<String> warnings = (List<String>)request.getAttribute("warningMessages");
if (warnings != null) {
	for (int wsize = 0 ; wsize < warnings.size() ; wsize++) {
%>
		<label><%= warnings.get(wsize) %></label>
<%
	}
}
%>
<%
List<String> infos = (List<String>)request.getAttribute("infoMessages");
if (infos != null) {
	for (int isize = 0 ; isize < infos.size() ; isize++) {
%>
		<label><%= infos.get(isize) %></label>
<%
	}
}
%>
</section>
<section>
<%
Emp0001UplForm form = (Emp0001UplForm)request.getAttribute("form");
%>
<br>
<div class="itemgroup">
<input type="file" name="file" accept=".csv" multiple/>
<br><br>
<input type="hidden" name="buttontype">
<input class="inButton" type="button" onclick="doPostMultipart(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Upl/upload" %>',this)" name="0" value="登録"/>
<input class="inButton" type="button" onclick="doPostMultipart(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Upl/upload" %>',this)" name="1" value="更新"/>
<input class="inButton" type="button" onclick="doPostMultipart(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Upl/upload" %>',this)" name="2" value="削除"/>
<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>');" value="戻る"/>
</div>

</section>
</form>
<footer></footer>
</body>
</html>
