<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="<%= request.getContextPath() + "/img/favicon.png" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/lib/login.css" %>">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/lib/login.js" %>" ></script>
<title>ログイン</title>
</head>
<body>
<form method="post" action="<%= request.getContextPath() + "/login/check" %>">
<jsp:include page="../../jsp/header.jsp">
    <jsp:param name="title" value="ログイン" />
</jsp:include>
<jsp:include page="../message.jsp"/>
<section>
<br>
<br>
<div><label>ユーザＩＤ</label><input type="text" name="userId" value="<%= request.getAttribute("userId") %>" placeholder="ユーザＩＤを入力" required /></div>
<br>
<div><label>パスワード</label><input type="password" name="password" value="" placeholder="パスワードを入力"  required autocomplete="off"/></div>
<br>
<div class="loginbutton"><input type="submit" value="ログイン"/></div>

</section>
</form>
</body>
</html>
