<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ page import="application.emp0002.detail.Emp0002DtlForm"%>
<%@ page import="application.CommonConstants"%>
<%@ page import="application.emp0002.detail.Emp0002DtlConstants"%>

<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="<%= request.getContextPath() + "/img/favicon.png" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp0002/emp0002dtl.css" %>">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp0002/emp0002dtl.js" %>" ></script>
<title>顧客情報編集</title>
</head>
<body>
<form id="mainForm" method="post">
<jsp:include page="../../header.jsp">
    <jsp:param name="title" value="顧客情報編集" />
</jsp:include>
<jsp:include page="../../message.jsp"/>
<section>
<%
Emp0002DtlForm form = (Emp0002DtlForm)request.getAttribute("form");
%>
<br>
<table class="inputtable">
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.CUSTOMER_ID %><%=CommonConstants.REQUIRED %></label>
		</td>
		<td>
			<input type="text" name="customerId" value="<%= form.getCustomerId() %>" style="ime-mode: inactive" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.CUSTOMER_NAME %><%=CommonConstants.REQUIRED %></label>
		</td>
		<td>
			<input type="text" name="customerName" value="<%= form.getCustomerName() %>"  style="ime-mode: active" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.ZIP_CODE %></label>
		</td>
		<td>
			<input class="inputZip" type="text" name="zipCode" maxlength="7" value="<%= form.getZipCode() %>" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.ADDRESS %></label>
		</td>
		<td>
			<input type="text" name="address" value="<%= form.getAddress() %>" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.LIAISON %></label>
		</td>
		<td>
			<input type="text" name="liaison" value="<%= form.getLiaison() %>" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.STAFF %></label>
		</td>
		<td>
			<input type="text" name="staff" value="<%= form.getStaff() %>" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.TEL %></label>
		</td>
		<td>
			<input class="inputTel"  type="text" name="tel" maxlength="11" value="<%= form.getTel() %>" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.DEVELOPMENT %></label>
		</td>
		<td>
			<input type="radio" name="developmentFlg" value="1" <% if("1".equals(form.getDevelopmentFlg())){ %> checked <% } %>>有
			<input type="radio" name="developmentFlg" value="0" <% if("0".equals(form.getDevelopmentFlg())){ %> checked <% } %>>無
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.MAINTENANCE %></label>
		</td>
		<td>
			<input type="radio" name="maintenanceFlg" value="1" <% if("1".equals(form.getMaintenanceFlg())){ %> checked <% } %>>有
			<input type="radio" name="maintenanceFlg" value="0" <% if("0".equals(form.getMaintenanceFlg())){ %> checked <% } %>>無
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.OPERATION %></label>
		</td>
		<td>
			<input type="radio" name="operationFlg" value="1" <% if("1".equals(form.getOperationFlg())){ %> checked <% } %>>有
			<input type="radio" name="operationFlg" value="0" <% if("0".equals(form.getOperationFlg())){ %> checked <% } %>>無
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.INFRASTRUCTURE %></label>
		</td>
		<td>
			<input type="radio" name="infrastructureFlg" value="1" <% if("1".equals(form.getInfrastructureFlg())){ %> checked <% } %>>有
			<input type="radio" name="infrastructureFlg" value="0" <% if("0".equals(form.getInfrastructureFlg())){ %> checked <% } %>>無
		</td>
	</tr>
	<tr>
		<td>
			<label><%=Emp0002DtlConstants.AGREE_STATUS %></label>
		</td>
		<td>
			<select name="agreeStatus">
				<%
				for (Map.Entry<String, String> agreeStatus : form.getAgreeMap().entrySet()) {
				%>
					<option value=<%= agreeStatus.getKey()%> <% if(agreeStatus.getKey().equals(form.getAgreeStatus())) { %> selected <% ;} %> > <%= agreeStatus.getValue()%> </option>
				<%
				}
		         %>
			</select>
		</td>
	</tr>
	<tr>
		<td  colspan="2"><br>
			<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Lst/search" %>');" value="戻る"/>
			<input class="inButton" type="button" onclick="doPost(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Dtl/save" %>')" value="<% if(form.getParamCustomerId() == ""){%>登録<%}else{%>更新<% } %>"/>
		</td>
	</tr>
	<tr>
		<td  colspan="2"><br><br>
			<label><%=Emp0002DtlConstants.MATTER_INFO %></label>
		</td>
	</tr>
	<tr>
		<td  colspan="2">
			<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Mtr/regist" %>');" value="追加"/>
		</td>
	</tr>
</table>
<input type="hidden" name="paramCustomerId" value="<%= form.getParamCustomerId() %>" placeholder="" required />
<input type="hidden" name="backMode" value="<%= form.getBackMode() %>" placeholder="" required />
<input type="hidden" name="mode" id="mode" value="<%= form.getMode() %>" placeholder="" required />
</section>
</form>
<footer></footer>
</body>
</html>
