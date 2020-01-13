<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/login.js" %>" ></script>
<title>ログイン</title>
</head>
<body>
<header><h1>ログイン</h1></header>
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
<form method="post" action="<%= request.getContextPath() + "/login/check" %>">
<section>
<div><label>ユーザＩＤ</label><input type="text" name="userId" value="<%= request.getAttribute("userId") %>" placeholder="ユーザＩＤを入力" required /></div>
<div><label>パスワード</label><input type="password" name="password" value="" placeholder="パスワードを入力"  required /></div>
<div><input type="submit" value="ログイン"/></div>
</section>
</form>
<footer>copy write ...</footer>
</body>
</html>
