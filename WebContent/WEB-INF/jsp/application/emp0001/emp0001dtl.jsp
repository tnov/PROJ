<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="application.emp0001.detail.Emp0001DtlForm"%>
<%@ page import="application.CommonConstants"%>
<%@ page import="application.emp0001.detail.Emp0001DtlConstants"%>

<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="<%= request.getContextPath() + "/img/favicon.png" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp0001/emp0001dtl.css" %>">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp0001/emp0001dtl.js" %>" ></script>
<title>社員情報編集</title>
</head>
<body>
<form id="mainForm" method="post">
<jsp:include page="../../header.jsp">
    <jsp:param name="title" value="社員情報編集" />
</jsp:include>
<jsp:include page="../../message.jsp"/>
<section>
<%
Emp0001DtlForm form = (Emp0001DtlForm)request.getAttribute("form");
%>
<br>
<table class="inputtable">
	<tr>
		<td>
			<label><%=Emp0001DtlConstants.EMPLOYEE_ID %><%=CommonConstants.REQUIRED %></label>
		</td>
		<td>
			<input type="text" name="employeeId" value="<%= form.getEmployeeId() %>" style="ime-mode: inactive" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0001DtlConstants.EMPLOYEE_NAME %><%=CommonConstants.REQUIRED %></label>
		</td>
		<td>
			<input type="text" name="employeeName" value="<%= form.getEmployeeName() %>"  style="ime-mode: active" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0001DtlConstants.SEX %></label>
		</td>
		<td>
			<input type="radio" name="sex" value="0" <% if("0".equals(form.getSex())){ %> checked <% } %>>男性
			<input type="radio" name="sex" value="1" <% if("1".equals(form.getSex())){ %> checked <% } %>>女性
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0001DtlConstants.BIRTH_YMD %><%=CommonConstants.REQUIRED %></label>
		</td>
		<td>
			<input class="inputDate" type="date" name="birthYmd" maxlength="8" value="<%= form.getBirthYmd() %>"  placeholder="" required />
			<!--/*
			&nbsp;
			<%=CommonConstants.DISP_FORMAT_YYYYMMDD %>
			*/-->
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0001DtlConstants.DEPARTMENT_NAME %></label>
		</td>
		<td>
			<select name="departmentId">
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
		<td>
			<label><%=Emp0001DtlConstants.JOINED_YMD %></label>
		</td>
		<td>
			<input class="inputDate" type="date" name="joinedYmd" maxlength="8" value="<%= form.getJoinedYmd() %>" placeholder="" required />
			<!--/*
			&nbsp;
			<%=CommonConstants.DISP_FORMAT_YYYYMMDD %>
			*/-->
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0001DtlConstants.RETIRE_YMD %></label>
		</td>
		<td>
			<input class="inputDate" type="date" name="retireYmd" maxlength="8" value="<%= form.getRetireYmd() %>" placeholder="" required />
			<!--/*
			&nbsp;
			<%=CommonConstants.DISP_FORMAT_YYYYMMDD %>
			*/-->
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0001DtlConstants.ZIP_CODE %></label>
		</td>
		<td>
			<input class="inputZip" type="text" name="zipCode" maxlength="7" value="<%= form.getZipCode() %>" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0001DtlConstants.ADDRESS %></label>
		</td>
		<td>
			<input class="active" type="text" name="address" value="<%= form.getAddress() %>" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0001DtlConstants.TEL %></label>
		</td>
		<td>
			<input class="inputTel" type="text" name="tel" maxlength="12" value="<%= form.getTel() %>"  placeholder="" required />
		</td>
	</tr>
	<tr id="password">
		<td>
			<label><%=Emp0001DtlConstants.PASSWORD %><%=CommonConstants.REQUIRED %></label>
		</td>
		<td>
			<input type="password" name="password" value="<%= form.getPassword() %>" placeholder="" required autocomplete="new-password"/>
		</td>
	</tr>
	<tr id="passwordChk">
		<td>
			<label><%=Emp0001DtlConstants.PASSWORD_CHK %><%=CommonConstants.REQUIRED %></label>
		</td>
		<td>
			<input type="password" name="passwordChk" value="<%= form.getPasswordChk() %>" placeholder="" required autocomplete="new-password"/>
		</td>
	</tr>
	<tr>
		<td  colspan="2"><br>
			<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Lst/search" %>');" value="戻る"/>
			<input class="inButton" type="button" onclick="doPost(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Dtl/save" %>')" value="保存"/>
		</td>
	</tr>
</table>
<input type="hidden" name="paramEmployeeId" value="<%= form.getParamEmployeeId() %>"/>
<input type="hidden" name="backMode" value="<%= form.getBackMode() %>"/>
<input type="hidden" name="mode" id="mode" value="<%= form.getMode() %>"/>
</section>
</form>
<footer></footer>
</body>
</html>
