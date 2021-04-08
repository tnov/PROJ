<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List"%>
<%@ page import="application.emp0001.upload.Emp0001UplForm"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="<%= request.getContextPath() + "/img/favicon.png" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/application/emp0001/emp0001upl.css" %>">


<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/application/emp0001/emp0001upl.js" %>" ></script>
<title>社員情報取込</title>
</head>
<body>
<form id="mainForm" method="post">

<jsp:include page="../../header.jsp">
    <jsp:param name="title" value="社員情報取込" />
</jsp:include>
<jsp:include page="../../message.jsp"/>
<section>
<%
Emp0001UplForm form = (Emp0001UplForm)request.getAttribute("form");
%>
<br>
<div class="itemgroup">
<input class="fileselect" type="file" name="file" size="70" accept=".csv" multiple/>
<br><br>
<input type="hidden" name="buttontype">
<input class="inButton" type="button" onclick="doPostMultipart(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Upl/upload" %>',this)" name="0" value="登録"/>
<input class="inButton" type="button" onclick="doPostMultipart(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Upl/upload" %>',this)" name="1" value="更新"/>
<input class="inButton" type="button" onclick="doPostMultipart(document.getElementById('mainForm'),'<%= request.getContextPath() + "/Emp0001Upl/upload" %>',this)" name="2" value="削除"/>
<input class="inButton" type="button" onclick="move(document.getElementById('mainForm'),'<%= request.getContextPath() + "/menu" %>');" value="戻る"/>
</div>

</section>
</form>
<footer></footer>
</body>
</html>
