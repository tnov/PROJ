<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="application.emp0001.Emp0001DataBean"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<!-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp00001/emp0001lst.css" %>"> -->
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<!-- <script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp00001/emp0001lst.js" %>" ></script> -->
<title>社員一覧</title>
</head>
<body>
<header><h1>社員一覧</h1></header>
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
<form id="mainForm" method="post">
<section>
<div><label>社員ＩＤ</label><input type="text" name="employeeId" value="<%= request.getAttribute("employeeId") %>" placeholder="" required /></div>
<div><label>社員氏名</label><input type="text" name="employeeName" value="<%= request.getAttribute("employeeName") %>" placeholder=""  required /></div>
<div><label>性別</label><input type="text" name="sex" value="<%= request.getAttribute("sex") %>" placeholder=""  required /></div>
<div><label>入社日</label><input type="text" name="joinedYmd" value="<%= request.getAttribute("joinedYmd") %>" placeholder=""  required /></div>
<div><label>退職日</label><input type="text" name="retiredYmd" value="<%= request.getAttribute("retiredYmd") %>" placeholder=""  required /></div>
<div><input type="button" onclick="doPost(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>')" value="検索"/></div>
</section>
<section>
<div>
<table class="list">
<thead>
<tr>
<td class="list">
	<label>社員ＩＤ</Label>
</td>
<td class="list">
	<label>社員氏名</Label>
</td>
<td class="list">
	<label>性別</Label>
</td>
<td class="list">
	<label>入社日</Label>
</td>
</tr>
</thead>
<tbody>
<%
ArrayList<Emp0001DataBean> list = (ArrayList<Emp0001DataBean>)request.getAttribute("list");
if (list != null) {
	for (int i = 0 ; i < list.size() ; i++) {
%>
<%
Emp0001DataBean bean = list.get(i);
%>
<tr>
<td class="list">
	<label><%= bean.getEmployeeId() %></label>
</td>
<td class="list">
	<label><%= bean.getEmployeeName() %></label>
</td>
<td class="list">
	<label><%= bean.getSex() %></label>
</td>
<td class="list">
	<label><%= bean.getJoinedYmd() %></label>
</td>
</tr>
<%
	}
}
%>
</tbody>
</table>
</div>
</section>
<section>
<div><input type="button" onclick="" value="新規"/></div>
<div><input type="button" onclick="" value="ＣＳＶ"/></div>
<div><input type="button" onclick="" value="戻る"/></div>
</section>
</form>
<footer></footer>
</body>
</html>
