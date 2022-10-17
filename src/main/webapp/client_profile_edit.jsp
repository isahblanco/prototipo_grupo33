<%-- 
    Document   : user
    Created on : 12 abr. 2021, 12:29:32
    Author     : ignaren, dancruz, mariher
--%>

<%@page import="ssw.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Fantasty - Edit Profile</title>
</head>
<body>
<% 
    Object clientObject = request.getSession().getAttribute("client");
    if (clientObject != null) {
        Client client = (Client) clientObject;
%>
<%@ include file="/includes/navigation_menu_logged.jsp" %>
<%
    } else {
%>
<%@ include file="/includes/navigation-menu.jsp" %>
<%
    }
%> 
    <script type="text/javascript" src="scripts/client_profile_edit.js" defer></script>
    <main id="user-info-content">
        <!-- User identification-->
        <div id="user-identification" class="container">
            <!-- Profile information-->
            <img id="user-pic" src="client_image?id=${client.id}">
            <label id="titulo" >${client.getTitle()}   Lvl: ${client.level}</label>
            <form id="user-form" action="edit_profile" method="post" enctype="multipart/form-data">
                <label id="user-new-pic-label"><input id="user-new-pic" name="user-new-pic" type="file">Change profile pic</label>
                <div class="profile-options">
                    <input id="user-name" class="user-text-info" type="text" name="user-name" placeholder="Name" value="${client.name}" pattern="[a-zA-Z]*" required>
                    <input id="user-surname" class="user-text-info" type="text" name="user-surname" placeholder="Surname" value="${client.surnames}" pattern="([a-zA-Z]*( +[a-zA-Z]*)" required>
                    <div id="user-nick-container">
                        <label id="user-nick-label">@</label>
                        <input id="user-nick" class="user-text-info" type="text" name="id" placeholder="@your_id" value="${client.id}" pattern="[a-z0-9_]*" required>
                    </div>
                </div>
                <div class="info-options">
                    <input id="user-correo" class="user-text-info" type="text" name="user-correo" placeholder="Type your email" value="${client.email}" pattern="^[A-Za-z0-9]+([\._-][A-Za-z0-9]+)@([A-Za-z0-9]+[A-Za-z0-9-][A-Za-z0-9]+)(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})">
                    <textarea id="user-biography" type="text" name="user-biography" placeholder="Write something about yourself">${client.biography}</textarea>
                </div>
                <!-- Confirmation of the changes -->
                <input id="user-submit" type="submit" value="Confirm">
            </form>
            
        </div>
        <!-- Extra information-->
        <div id="user-extra-info" class="container">
            <div class="extra">
                <div class="gamificacion">
                    <div class="lvltitle">
                        <label id="nivel" >Lvl: ${client.level}</label>
                        <label id="titulo" >${client.getTitle()}</label>
                    </div>
                    <div id="total-experience">
                        <label id="number-experience">${client.exp}/${client.neededXP}</label>
                        <div id="current-experience" style="width: ${client.getCurrentXP()}%"></div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    </body>
</html>
