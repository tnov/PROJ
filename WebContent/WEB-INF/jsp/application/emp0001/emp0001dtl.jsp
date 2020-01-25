<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<!-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp0001/emp0001dtl.css" %>"> -->
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp0001/emp0001dtl.js" %>" ></script>
<title>社員詳細</title>
</head>
<body>
<header><h1>社員詳細</h1></header>
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
<div><label>生年月日</label><input type="text" name="birthYmd" value="<%= request.getAttribute("birthYmd") %>" placeholder=""  required /></div>
<div><label>性別</label><input type="text" name="sex" value="<%= request.getAttribute("sex") %>" placeholder=""  required /></div>
<div><label>郵便番号</label><input type="text" name="zipCode" value="<%= request.getAttribute("zipCode") %>" placeholder=""  required /></div>
<div><label>住所</label><input type="text" name="address" value="<%= request.getAttribute("address") %>" placeholder=""  required /></div>
<div><label>入社日</label><input type="text" name="joinedYmd" value="<%= request.getAttribute("joinedYmd") %>" placeholder=""  required /></div>
<div><label>退職日</label><input type="text" name="retireYmd" value="<%= request.getAttribute("retireYmd") %>" placeholder=""  required /></div>
<div><label>所属ＩＤ</label><input type="text" name="departmentId" value="<%= request.getAttribute("departmentId") %>" placeholder=""  required /></div>
<div><label>認証</label><input type="text" name="authorized" value="<%= request.getAttribute("authorized") %>" placeholder=""  required /></div>
<div><input type="button" onclick="doPost(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Dtl/save" %>')" value="保存"/></div>
<div><input type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/init" %>');" value="戻る"/></div>
<input type="hidden" name="paramEmployeeId" value="<%= request.getAttribute("paramEmployeeId") %>" placeholder=""  required />
<input type="hidden" name="mode" value="<%= request.getAttribute("mode") %>" placeholder=""  required />
</section>
</form>
<footer></footer>
</body>
</html>
