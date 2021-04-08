<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="<%= request.getContextPath() + "/img/favicon.png" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/lib/password.css" %>">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/common.js" %>" ></script>
<script type="text/javascript" src="<%= request.getContextPath() + "/js/lib/password.js" %>" ></script>
<title>パスワード変更</title>
</head>
<body>
<jsp:include page="../../jsp/header.jsp">
    <jsp:param name="title" value="パスワード変更" />
</jsp:include>
<jsp:include page="../message.jsp"/>
<form method="post" action="<%= request.getContextPath() + "/password/change" %>">
<section>
<br>
<div><label>ユーザＩＤ</label><label><%= request.getAttribute("userId") %></label><input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>"/></div>
<br>
<div><label>パスワード</label><input type="password" name="password" value="<%= request.getAttribute("password") %>" placeholder="パスワードを入力" required /></div>
<br>
<div><label>新パスワード</label><input type="password" name="newPassword" value="" placeholder="新しいパスワードを入力"  required /></div>
<br>
<div><label>新パスワード確認用</label><input type="password" name="confirmPassword" value="" placeholder="確認のため再入力"  required /></div>
<br>
<div><input type="submit" value="パスワード変更"/></div>
</section>
</form>
</body>
</html>
