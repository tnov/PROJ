<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="lib.common.menu.HierarchyBean"%>
<%@ page import="lib.common.menu.MenuBean"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="<%= request.getContextPath() + "/img/favicon.png" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/lib/menu.css" %>">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/lib/menu.js" %>" ></script>
<title>メニュー</title>
</head>
<body>
<form id="mainForm" method="post" action="<%= request.getContextPath() + "/menu/move" %>">
<jsp:include page="../../jsp/header.jsp">
    <jsp:param name="title" value="メニュー" />
</jsp:include>
<div class="container">
<%
List<HierarchyBean> hierarchyList = (List<HierarchyBean>)request.getAttribute("list");
if (hierarchyList != null) {
	for (int i = 0 ; i < hierarchyList.size() ; i++) {
		HierarchyBean hierarchyBean = hierarchyList.get(i);

		if(hierarchyBean.getDispFlg().equals("1")){
%>
	<div class="tile" style="visibility:hidden">
<%
		}else{
	%>
		<div class="tile">

<%
		}
	%>
		<div class="groupname"><label><%= hierarchyBean.getHierarchyName() %></label></div>
		<div class="list">
<%
		List<MenuBean> menuList = hierarchyBean.getMenuList();
		if (menuList != null) {
			for (int j = 0 ; j < menuList.size() ; j++) {
				MenuBean menuBean = menuList.get(j);
%>
			<div class="item">
				<div style="" class="link" onclick="move(document.getElementById('mainForm'),'<%= menuBean.getMenuPath() %>');">
					<li class="menuname"><label><%= menuBean.getMenuName() %></label></li>
					<input type="hidden" name="menuPath" value="<%= request.getContextPath() + menuBean.getMenuPath() %>"/>
				</div>
			</div>
<%
			}
		}
%>
		</div>
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
