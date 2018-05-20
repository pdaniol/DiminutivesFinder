<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: X230
  Date: 2018-05-09
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page pageEncoding="UTF-8"%>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body bgcolor="#4682b4">

<h3><br><br>Enter text to find diminutives:</h3>

<textarea name="textForSearch" form="usrform" rows="25" cols="100">Enter the text...</textarea>

<form ACTION="http://localhost:2526/test" id="usrform" class="input" METHOD="POST" accept-charset="UTF-8">
    <input type="submit" value="Wyslij">
</form>



</body>
</html>
