<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF8"); %>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="<%= request.getContextPath() + "/js/test.js" %>" ></script>
<title>ログイン</title>
</head>
<body>
<header></header>
<form method="POST" action="<%= request.getContextPath() + "/authorized/login" %>">
<h1>認証</h1>
<div><input type="text" id="user" value="<%= request.getAttribute("userId") %>" placeholder="ユーザＩＤを入力" required /></div>
<div><input type="password" id="password" value="" placeholder="パスワードを入力"  required /></div>
<div><input type="submit" value="ログイン" /></div>
</form>
<footer></footer>
</body>
</html>
