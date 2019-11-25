<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="WEB-INF/js/authorized.js" ></script>
<title>認証</title>
</head>
<body>
<form method="POST" action="./authorized/login">
<h1>認証</h1>
<input type="text" id="user" value="" placeholder="ユーザＩＤを入力" required />
<input type="password" id="password" value="" placeholder="パスワードを入力"  required />
<input type="submit" value="ログイン" />
</form>
</body>
</html>
