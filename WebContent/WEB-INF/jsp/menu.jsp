<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="lib.common.menu.MenuBean"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/lib/menu.js" %>" ></script>
<title>メニュー</title>
</head>
<body>
<header></header>
<form id="mainForm" method="post" action="<%= request.getContextPath() + "/menu/move" %>">
<header><h1>メニュー</h1></header>
<div class="menuItem">
<%
ArrayList<MenuBean> list = (ArrayList<MenuBean>)request.getAttribute("list");
if (list != null) {
	for (int i = 0 ; i < list.size() ; i++) {
%>
<%
MenuBean bean = list.get(i);
%>
	<div style="" class="link" onclick="move(document.getElementById('mainForm'),'<%= bean.getMenuPath() %>');">
	<label><%= bean.getMenuNo() %></label>
	<label><%= bean.getMenuId() %></label>
	<label><%= bean.getMenuName() %></label>
	<input type="hidden" name="menuPath" value="<%= request.getContextPath() + bean.getMenuPath() %>"/>
</div>
<%
	}
}
%>
</div>
<input type="hidden" name="menuMovePath" value=""/>
</form>
<footer></footer>
</body>
</html>
