<%-- 
    Document   : clientProfile
    Created on : 28 abr. 2021, 9:42:48
    Author     : ignaren, dancruz, mariher
--%>

<%@page import="ssw.model.Recipe"%>
<%@page import="ssw.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Fantasty - Profile</title>
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
        Client client = (Client) request.getAttribute("profileVisited");
%>
<%@ include file="/includes/navigation-menu.jsp" %>
<%
    }
%>
<jsp:useBean id="profileVisited" scope="request" class="ssw.model.Client"/>
        <div id="client-profile">
            <div id="client-profile-info">
                <img src="client_image?id=${profileVisited.id}">
                <label id="cp-name">${profileVisited.name} ${profileVisited.surnames} </label>
                <label id="cp-nickname">${profileVisited.nickname}</label>
                <label id="cp-title-exp">${profileVisited.getTitle()} - ${profileVisited.level} LVL</label>
                <% if (!profileVisited.getEmail().equals("")) {%><label id="cp-email">${profileVisited.email}</label><% }%>
                <% if (!profileVisited.getBiography().equals("")) {%><p id="cp-biography">${profileVisited.biography}</p><% }%>
            </div>
            <div id="client-profile-recipes">
                <%
                    Recipe[] clientRecipes = (Recipe[]) request.getAttribute("clientRecipes");
                    if (clientRecipes != null) {
                        for (Recipe recipe : clientRecipes) {
                %>
                            <div class="profile-recipe-card">
                                <a href="recipeInfo?id=<%= recipe.getId()%>"><img class="profile-recipe-card-img" src="images?id=<%= recipe.getId()%>"></a>
                                <label class="profile-recipe-card-name"><%= recipe.getName()%></label>
                                <label class="profile-recipe-card-tags"><%= recipe.getTags()%></label>
                            </div>
                <%
                        }
                    } else {
                %>
                    <p>Aqui irán las recetas del cliente</p>
                <%
                    }
                %>
                
            </div>
        </div>
    </body>
</html>
