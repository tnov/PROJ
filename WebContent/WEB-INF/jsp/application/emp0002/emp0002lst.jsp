<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.util.Map"%>
<%@ page import="application.emp0002.list.Emp0002LstForm"%>
<%@ page import="application.emp0002.Emp0002DataBean"%>
<%@ page import="application.emp0002.Emp0002Constants"%>

<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp0002/emp0002lst.css" %>">

<!-- <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp00002/emp0002lst.css" %>"> -->
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp0002/emp0002lst.js" %>" ></script>
<title>顧客一覧</title>
</head>
<body>
<form id="mainForm" method="post">
<jsp:include page="../../header.jsp">
	<jsp:param name="title" value="顧客一覧" />
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
Emp0002LstForm form = (Emp0002LstForm)request.getAttribute("form");
%>
<br>
<table class="inputtable">
	<tr>
		<td class="label inputTd">
			<label>顧客ＩＤ</label>
		</td>
		<td class="inputTd">
			<input type="text" name="customerId" id="customerId" value="<%= form.getCustomerId() %>" placeholder="" required />
		</td>
	</tr>
	<tr>
		<td class="label inputTd">
			<label>顧客名称</label>
		</td>
		<td class="inputTd">
			<input type="text" name="customerName" id="customerName" value="<%= form.getCustomerName() %>" placeholder=""  required />
		</td>
	</tr>
	<tr>
		<td class="label inputTd">
			<label>業務種別</label>
		</td>
		<td class="inputTd">
			<label><input type="checkbox" name="development" id="development" value="1" <% if("1".equals(form.getDevelopment())){ %> checked="checked" <% } %>>開発</label>
			<label><input type="checkbox" name="maintenance" id="maintenance" value="1" <% if("1".equals(form.getMaintenance())){ %> checked="checked" <% } %>>保守</label>
			<label><input type="checkbox" name="operation" id="operation" value="1" <% if("1".equals(form.getOperation())){ %> checked="checked" <% } %>>運用</label>
			<label><input type="checkbox" name="infrastructure" id="infrastructure" value="1" <% if("1".equals(form.getInfrastructure())){ %> checked="checked" <% } %>>インフラ</label>
		</td>
	</tr>
	<tr>
		<td class="label inputTd">
			<label>契約有無</label>
		</td>
		<td class="inputTd">
			<label><input type="checkbox" name="agreeStatus" id="agreeStatusNoAgree" value="0" <% if(form.getAgreeStatus() != null){if(Arrays.asList(form.getAgreeStatus()).contains("0")){ %> checked="checked" <% }} %>>未契約</label>
			<label><input type="checkbox" name="agreeStatus" id="agreeStatusAgree" value="1" <% if(form.getAgreeStatus() != null){if(Arrays.asList(form.getAgreeStatus()).contains("1")){ %> checked="checked" <% }} %>>契約中</label>
			<label><input type="checkbox" name="agreeStatus" id="agreeStatusAgreeFin" value="2" <% if(form.getAgreeStatus() != null){if(Arrays.asList(form.getAgreeStatus()).contains("2")){ %> checked="checked" <% }} %>>契約終了</label>
		</td>
	</tr>
	<tr>
		<td class="label inputTd">
			<label>契約開始日</label>
		</td>
		<td class="inputTd">
			<input class="agreeYmd" type="date" name="agreeYmdFrom" id="agreeYmdFrom" value="<%= form.getAgreeYmdFrom() %>" placeholder=""  required />
			～
			<input class="agreeYmd" type="date" name="agreeYmdTo" id="agreeYmdTo" value="<%= form.getAgreeYmdTo() %>" placeholder=""  required />

		</td>
	</tr>

	<tr>
		<td  colspan="2"><br>
			<input class="inButton" type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Lst/search" %>','1')" value="検索"/>
			<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Dtl/init" %>');" value="社員登録"/>
			<input class="inButton" type="button" onclick="doPost(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Lst/csv" %>')" value="ＣＳＶ出力" id="csvButton" />
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
<td class="list customerId">
	<label>顧客ＩＤ</Label>
</td>
<td class="list customerName">
	<label>顧客氏名</Label>
</td>
<td class="list tel">
	<label>電話番号</Label>
</td>
<td class="list agreeStatus">
	<label>契約状況</Label>
</td>
<td class="list agreeYmd">
	<label>契約開始日</Label>
</td>
<td class="list csvCheck">
	<label>CSV出力</Label>
</td>
</tr>
</thead>
<tbody>
<%
List<Emp0002DataBean> list = form.getResultList();
if (list != null) {
	for (int i = 0 ; i < list.size() ; i++) {
%>
<%
Emp0002DataBean bean = list.get(i);
%>
<tr>
<td class="list">
	<a href="javascript:void(0)" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Dtl/init" %>','<%= bean.getCustomerId() %>');return false;"><%= bean.getCustomerId() %></a>
</td>
<td class="list">
	<label><%= bean.getCustomerName() %></label>
</td>
<td class="list">
	<label><%= bean.getTel() %></label>
</td>
<td class="list">
	<label><%= Emp0002Constants.AGREE_STATUS_MAP.get(bean.getAgreeStatus()) %></label>
</td>
<td class="list">
	<label><%if(bean.getAgreeYmd() != null){ %><%= bean.getAgreeYmd() %> <% } %></label>
</td>
<td class="list">
	<input type="checkbox" name="checklist" value=<%= bean.getCustomerId() %>>
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
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Lst/search" %>','1')" value="先頭"/>
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Lst/search" %>','<%= Integer.parseInt(form.getCurrentPage()) - 1 %>')" value="前頁"/>
<% } %>
<label><%= form.getCurrentPage() %></label>/<label><%= form.getPageSize() %></label>
<% if ("0".equals(form.getCurrentPage()) || form.getPageSize().equals(form.getCurrentPage())) { %>
<input type="button" disabled="disabled" value="次頁"/>
<input type="button" disabled="disabled" value="末尾"/>
<% } else { %>
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Lst/search" %>','<%= Integer.parseInt(form.getCurrentPage()) + 1 %>')" value="次頁"/>
<input type="button" onclick="search(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0002Lst/search" %>','<%= form.getPageSize() %>')" value="末尾"/>
<% } %>
</td>
</tr>
</tfoot>
</table>
</div>
<input type="hidden" name="paramCustomerId" value="" />

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
