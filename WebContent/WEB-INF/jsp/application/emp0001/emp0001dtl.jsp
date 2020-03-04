<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="application.emp0001.detail.Emp0001DtlForm"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp0001/emp0001dtl.css" %>">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp0001/emp0001dtl.js" %>" ></script>
<title>社員詳細</title>
</head>
<body>
<jsp:include page="../../header.jsp">
    <jsp:param name="title" value="社員詳細" />
</jsp:include>
<section>
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
<form id="mainForm" method="post">
<section>
<%
Emp0001DtlForm form = (Emp0001DtlForm)request.getAttribute("form");
%>
<div>
	<table class="inputtable">
		<tr>
			<td>
				<label>社員ＩＤ</label>
			</td>
			<td>
				<input type="text" name="employeeId" value="<%= form.getEmployeeId() %>" placeholder="" required />
			</td>
	</tr>
		<tr>
			<td>
<label>社員氏名</label>
			</td>
			<td>
<input type="text" name="employeeName" value="<%= form.getEmployeeName() %>" placeholder=""  required />
			</td>
	</tr>
		<tr>
			<td>
<label>生年月日</label>
			</td>
			<td>
<input type="text" name="birthYmd" value="<%= form.getBirthYmd() %>" placeholder=""  required />
			</td>
	</tr>
		<tr>
			<td>
<label>性別</label>
			</td>
			<td>
<input type="radio" name="sex" value="1" checked="<%= "1".equals(form.getSex())?"checked":"" %>" /><label>男性</label><input type="radio" name="sex" value="2" checked="<%= "1".equals(form.getSex())?"checked":"" %>"><label>女性</label>
			</td>
	</tr>
		<tr>
			<td>
<label>郵便番号</label>
			</td>
			<td>
<input type="text" name="zipCode" value="<%= form.getZipCode() %>" placeholder=""  required />
			</td>
	</tr>
		<tr>
			<td>
<label>住所</label>
			</td>
			<td>
<input type="text" name="address" value="<%= form.getAddress() %>" placeholder=""  required />
			</td>
	</tr>
		<tr>
			<td>
<label>入社日</label>
			</td>
			<td>
<input type="date" name="joinedYmd" value="<%= form.getJoinedYmd() %>" placeholder=""  required />
			</td>
	</tr>
		<tr>
			<td>
<label>退職日</label>
			</td>
			<td>
<input type="date" name="retireYmd" value="<%= form.getRetireYmd() %>" placeholder=""  required />
			</td>
	</tr>
		<tr>
			<td>
<label>所属ＩＤ</label>
			</td>
			<td>
<input type="text" name="departmentId" value="<%= form.getDepartmentId() %>" placeholder=""  required />
			</td>
	</tr>
		<tr>
			<td>
<label>認証</label>
			</td>
			<td>
<input type="text" name="authorized" value="<%= form.getAuthorized() %>" placeholder=""  required />
			</td>
	</tr>
		<tr>
			<td>
<input type="button" onclick="doPost(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Dtl/save" %>')" value="保存"/>
<input type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>');" value="戻る"/>
			</td>
	</tr>
</table>
</div>
<input type="hidden" name="paramEmployeeId" value="<%= form.getParamEmployeeId() %>" placeholder=""  required />
<input type="hidden" name="backMode" value="<%= form.getBackMode() %>" placeholder=""  required />
<input type="hidden" name="mode" value="<%= form.getMode() %>" placeholder=""  required />
</section>
</form>
<footer></footer>
</body>
</html>
