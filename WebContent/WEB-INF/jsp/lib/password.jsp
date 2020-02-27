<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/lib/password.css" %>">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/lib/password.js" %>" ></script>
<title>パスワード変更</title>
</head>
<body>
<jsp:include page="../..//jsp/header.jsp">
    <jsp:param name="title" value="パスワード変更" />
</jsp:include>
<section>
<%
ArrayList<String> errors = (ArrayList<String>)request.getAttribute("errorMessages");
if (errors != null) {
	for (int esize = 0 ; esize < errors.size() ; esize++) {
%>
		<label><%= errors.get(esize) %></label>
<%
	}
}
%>
<%
ArrayList<String> warnings = (ArrayList<String>)request.getAttribute("warningMessages");
if (warnings != null) {
	for (int wsize = 0 ; wsize < warnings.size() ; wsize++) {
%>
		<label><%= warnings.get(wsize) %></label>
<%
	}
}
%>
<%
ArrayList<String> infos = (ArrayList<String>)request.getAttribute("infoMessages");
if (infos != null) {
	for (int isize = 0 ; isize < infos.size() ; isize++) {
%>
		<label><%= infos.get(isize) %></label>
<%
	}
}
%>
</section>
<form method="post" action="<%= request.getContextPath() + "/password/change" %>">
<section>
<br>
<div><label>ユーザＩＤ</label><label><%= request.getAttribute("userId") %></label><input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>"/></div>
<br>
<div><label>パスワード</label><input type="password" name="password" value="<%= request.getAttribute("password") %>" placeholder="パスワードを入力" required /></div>
<br>
<div><label>新パスワード</label><input type="password" name="newPassword" value="" placeholder="新しいパスワードを入力"  required /></div>
<br>
<div><label>新パスワード確認用</label><input type="password" name="confirmPassword" value="" placeholder="確認のため再入力"  required /></div>
<br>
<div><input type="submit" value="パスワード変更"/></div>
</section>
</form>
<footer>copy write ...</footer>
</body>
</html>
