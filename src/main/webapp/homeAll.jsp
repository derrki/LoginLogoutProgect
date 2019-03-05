<%@page import="com.bimdog.mainclasses.User"%>
<%@ page import="java.util.ArrayList" %>
<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Home Page</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700&amp;subset=cyrillic-ext" rel="stylesheet">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%User user = (User) session.getAttribute("User"); %>
<% ArrayList eList = (ArrayList) session.getAttribute("ListUser");%>
<h3>Hi <%=user.getName() %></h3>
<strong>Your login</strong>: <%=user.getLogin() %><br>
<strong>Your Country</strong>: <%=user.getCountry() %><br>
<br>
<form action="Logout" method="post">
    <input type="submit" value="Logout" >
</form>
<table>
    <%
        for(int i=0; i<eList.size();i++){%>
    <tr>
        <td><%= eList.get(i) %></td>
    </tr>
    <%}%>
</table>
</body>
</html>