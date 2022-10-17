<%-- 
    Document   : register
    Created on : 12 abr. 2021, 12:29:51
    Author     : ignaren, dancruz, mariher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Fantasty - Register</title>
</head>
<body>
<%@ include file="/includes/navigation-menu.jsp" %>
<script type="text/javascript" src="scripts/register.js" defer></script>
<% 
    String error_msg = (String) request.getAttribute("error");    
%>
        <div id="lr-container">
            <form action="register?new=0" method="post" class="profile-options">
                <label id='lr-text'>Register</label>
                <label class="lr-tip">- Username: only use letters and "-"</label>
                <label class="lr-tip">- Passwords: at least 1 letter, 1 capital letter, 1 non-alphanumerical symbol and 1 digit.</label>
                <input id="register-username-input" name="username" class="lr-input" type="text" placeholder="Username" minlength="4" pattern="[a-z0-9_]*" required>
                <input id="register-password-input" name="password" class="lr-input" type="password" placeholder="Password" minlength="8" maxlength="20" required>
                <input id="register-password2-input" name="password2" class="lr-input" type="password" placeholder="Confirm your password" minlength="8" required>
                <label id="lr-error-label"><%= error_msg %></label>
                <input id="register-submit" name="submit" class="lr-submit" type="submit" value="Register">
                <a  href="login?menu=1"><label id="lr-link-text">Do you already have an account?</label></a>
            </form> 
        </div> 
    </body>
</html>
