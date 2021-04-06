<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="application.emp0001.list.Emp0001LstForm"%>
<%@ page import="application.emp0001.Emp0001DataBean"%>
<%@ page import="application.emp0001.Emp0001Constants"%>

<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="<%= request.getContextPath() + "/img/favicon.png" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp0001/emp0001lst.css" %>">

<!-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp00001/emp0001lst.css" %>"> -->
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp0001/emp0001lst.js" %>" ></script>
<title>社員一覧</title>
</head>
<body>
<form id="mainForm" method="post">
<jsp:include page="../../header.jsp">
	<jsp:param name="title" value="社員一覧" />
</jsp:include>
<section>
<div class= "messageGroup">
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
</div>
</section>
<%
Emp0001LstForm form = (Emp0001LstForm)request.getAttribute("form");
%>
<br>
<table class="inputtable">
	<tr>
		<td class="label inputTd">
			<label>社員ＩＤ</label>
		</td>
		<td class="inputTd">
			<input type="text" name="employeeId" id="employeeId" value="<%= form.getEmployeeId() %>" style="ime-mode: inactive" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td class="label inputTd">
			<label>社員氏名</label>
		</td>
		<td class="inputTd">
			<input type="text" name="employeeName" id="employeeName" value="<%= form.getEmployeeName() %>" style="ime-mode: active" placeholder=""  required />
		</td>
	</tr>
	<tr>
		<td class="label inputTd">
			<label>性別</label>
		</td>
		<td class="inputTd">
			<input type="checkbox" name="sex" id="sexMen" value="0" <% if(form.getSex() == null || form.getSex().length == 2 || "0".equals(form.getSex()[0])){ %> checked="checked" <% } %>>男性
			<input type="checkbox" name="sex" id="sexWomen" value="1" <% if(form.getSex() == null || form.getSex().length == 2 || "1".equals(form.getSex()[0])){ %> checked="checked" <% } %>>女性
		</td>
	</tr>
	<tr>
		<td class="label inputTd">
			<label>所属部署</label>
		</td>
		<td class="inputTd">
			<select name="departmentId" id="departmentId">
				<option value=""></option>
				<%
				for (Map.Entry<String, String> department : form.getDepartmentMap().entrySet()) {
				%>
					<option value=<%= department.getKey()%> <% if(department.getKey().equals(form.getDepartmentId())) { %> selected <% ;} %> > <%= department.getValue()%> </option>
				<%
				}
				 %>
			</select>
		</td>
	</tr>
	<tr>
		<td class="label inputTd">
			<label>入社年月日</label>
		</td>
		<td class="inputTd">
			<input class="joinedYmd" type="date" name="joinedYmdFrom" id="joinedYmdFrom" value="<%= form.getJoinedYmdFrom() %>" placeholder=""  required />
			～
			<input class="joinedYmd" type="date" name="joinedYmdTo" id="joinedYmdTo" value="<%= form.getJoinedYmdTo() %>" placeholder=""  required />

		</td>
	</tr>

	<tr>
		<td  colspan="2"><br>
			<input class="inButton" type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>','1')" value="検索"/>
			<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Dtl/init" %>');" value="社員登録"/>
			<input class="inButton" type="button" onclick="doPost(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/csv" %>')" value="ＣＳＶ出力" id="csvButton" />
			<input class="inButton" type="button" onclick="clearItem()" value="クリア"  />
			<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/menu" %>');" value="戻る"/>
		</td>
	</tr>
</table>
<section>
<div id="resultDisp">
<table>
<thead>
<tr>
<td class="list employeeId">
	<label>社員ＩＤ</Label>
</td>
<td class="list employeeName">
	<label>社員氏名</Label>
</td>
<td class="list sex">
	<label>性別</Label>
</td>
<td class="list joinedYmd">
	<label>入社年月日</Label>
</td>
<td class="list csvCheck">
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
	<label><%= Emp0001Constants.SELECTION_MAP.get(bean.getSex())%></label>
</td>
<td class="list">
	<label><%if(bean.getJoinedYmd() != null){ %><%= bean.getJoinedYmd() %> <% } %></label>
</td>
<td class="list">
	<input type="checkbox" name="checklist" value=<%= bean.getEmployeeId() %>>
</td>


</tr>
<%
	}
}
%>
</tbody>
<br>
<tfoot aligne="">
<tr><td></td></tr>
<tr>
<td colspan="5" valign="bottom">
<% if ("0".equals(form.getCurrentPage()) || "1".equals(form.getCurrentPage())) { %>
<input type="button" disabled="disabled" value="先頭"/>
<input type="button" disabled="disabled" value="前頁"/>
<% } else { %>
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>','1')" value="先頭"/>
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>','<%= Integer.parseInt(form.getCurrentPage()) - 1 %>')" value="前頁"/>
<% } %>
<label><%= form.getCurrentPage() %></label>/<label><%= form.getPageSize() %></label>
<% if ("0".equals(form.getCurrentPage()) || form.getPageSize().equals(form.getCurrentPage())) { %>
<input type="button" disabled="disabled" value="次頁"/>
<input type="button" disabled="disabled" value="末尾"/>
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
<input type="hidden" name="pageSize" id="pageSize" value="<%= form.getPageSize() %>" />
</section>
</form>
<footer></footer>
</body>
</html>
