<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="application.emp0001.list.Emp0001LstForm"%>
<%@ page import="application.emp0001.Emp0001DataBean"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp0001/emp0001lst.css" %>">

<!-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp00001/emp0001lst.css" %>"> -->
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp0001/emp0001lst.js" %>" ></script>
<title>社員一覧</title>
</head>
<body>
<jsp:include page="../../header.jsp">
    <jsp:param name="title" value="社員一覧" />
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
Emp0001LstForm form = (Emp0001LstForm)request.getAttribute("form");
%>
<table class="inputtable">
	<tr>
		<td class="label">
			<label>社員ＩＤ</label>
		</td>
		<td>
			<input type="text" name="employeeId" value="<%= form.getEmployeeId() %>" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td class="label">
			<label>社員氏名</label>
		</td>
		<td>
			<input type="text" name="employeeName" value="<%= form.getEmployeeName() %>" placeholder=""  required />
		</td>
	</tr>
	<tr>
		<td class="label">
			<label>性別</label>
		</td>
		<td>
		    <input type="checkbox" name="sex" value="1" <% if(form.getSex() == null || form.getSex().length == 2 || "1".equals(form.getSex()[0])){ %> checked="checked" <% } %>>男性
		    <input type="checkbox" name="sex" value="2" <% if(form.getSex() == null || form.getSex().length == 2 || "2".equals(form.getSex()[0])){ %> checked="checked" <% } %>>女性
		</td>
	</tr>
	<tr>
		<td class="label">
			<label>所属部署</label>
		</td>
		<td>
			<select name="department">
				<%
		        for (Map.Entry<String, String> department : form.getDepartmentMap().entrySet()) {
		        %>
					<option value=<%= department.getKey()%> > <%= department.getValue()%> </option>
				<%
		        }
		         %>
			</select>
		</td>
	</tr>
	<tr>
		<td class="label">
			<label>入社年月日</label>
		</td>
		<td>
			<input type="text" name="joinedYmd" value="<%= form.getJoinedYmd() %>" placeholder=""  required />
		</td>
	</tr>

</table>

<div class="buttonGroup">
	<input class="inButton" type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>','1')" value="検索"/>
	<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Dtl/init" %>');" value="社員登録"/>
	<input class="inButton" type="button" onclick="doPost(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/csv" %>')" value="ＣＳＶ出力"/>
	<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/menu" %>');" value="戻る"/>
</div>
</section>
<section>
<div>
<table class="detail">
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
	<label>入社年月日</Label>
</td>
<td class="list">
	<label>CSV出力</Label>
</td>
</tr>
</thead>
<tbody>
<%
List<Emp0001DataBean> list = form.getResultList();
if (list != null) {
	for (int i = 0 ; i < list.size() ; i++) {
%>
<%
Emp0001DataBean bean = list.get(i);
%>
<tr>
<td class="list">
	<a href="javascript:void(0)" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Dtl/init" %>','<%= bean.getEmployeeId() %>');return false;"><%= bean.getEmployeeId() %></a>
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
<td class="list">
	<input type="checkbox" name="checklist" value=<%= bean.getEmployeeId() %>> <!-- 作成中 -->
</td>


</tr>
<%
	}
}
%>
</tbody>
<tfoot>
<tr>
<td colspan="4">
<% if ("0".equals(form.getCurrentPage()) || "1".equals(form.getCurrentPage())) { %>
<label>先頭</label>
<label>前頁</label>
<% } else { %>
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>','1')" value="先頭"/>
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>','<%= Integer.parseInt(form.getCurrentPage()) - 1 %>')" value="前頁"/>
<% } %>
<label><%= form.getCurrentPage() %></label>/<label><%= form.getPageSize() %></label>
<% if ("0".equals(form.getCurrentPage()) || form.getPageSize().equals(form.getCurrentPage())) { %>
<label>次頁</label>
<label>末尾</label>
<% } else { %>
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>','<%= Integer.parseInt(form.getCurrentPage()) + 1 %>')" value="次頁"/>
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>','<%= form.getPageSize() %>')" value="末尾"/>
<% } %>
</td>
</tr>
</tfoot>
</table>
</div>
<input type="hidden" name="paramEmployeeId" value="" />

<input type="hidden" name="movePath" value="" />
<input type="hidden" name="lineSize" value="<%= form.getLineSize() %>" />
<input type="hidden" name="currentPage" value="<%= form.getCurrentPage() %>" />
<input type="hidden" name="lineLimit" value="<%= form.getLineLimit() %>" />
<input type="hidden" name="pageSize" value="<%= form.getPageSize() %>" />
</section>
</form>
<footer></footer>
</body>
</html>
