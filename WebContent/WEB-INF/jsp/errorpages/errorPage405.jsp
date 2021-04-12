<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true" %>

<% response.setStatus(405); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="<%= request.getContextPath() + "/img/favicon.png" %>">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() + "/css/common.css" %>">
    <title>405 error</title>
  </head>
  <body>
    Method Not Allowed!!!
  </body>
</html>