<%-- 
    Document   : login
    Created on : 12 abr. 2021, 12:12:43
    Author     : ignaren, mariher, dancruz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Fantasty - Login</title>
</head>
<body>
<%@ include file="/includes/navigation-menu.jsp" %>
<% 
    String error_msg = "";
    boolean error = (boolean) request.getAttribute("error");
    if (error) error_msg = "Username or password not correct.";
%>
    <div id="lr-container">
        <label id="lr-text">Welcome Back!</label>
        <form action="login?menu=0" method="post">
            <input id="login-username-input" name="username" class="lr-input" type="text" placeholder="Username" minlength="4" required>
            <input id="login-password-input" name="password" class="lr-input" type="password" placeholder="Password" minlength="8" maxlength="20" required>
            <label id="lr-error-label"><%= error_msg %></label>
            <input id="login-submit" class="lr-submit" type="submit" name="submit" value="Log in">
            <a  href="register?new=1"><label id="lr-link-text">Create a new Account</label></a>
        </form>
    </div> 
    </body>
</html>
