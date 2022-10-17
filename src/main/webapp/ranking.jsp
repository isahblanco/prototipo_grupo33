<%-- 
    Document   : rankings
    Created on : 12 abr. 2021, 12:29:59
    Author     : ignaren, dancruz, mariher
--%>

<%@page import="ssw.model.Client"%>
<%@page import="ssw.model.Recipe"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Fantasty - Home</title>
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
    <% 
        Recipe[] be = (Recipe[]) request.getAttribute("best_scores");
        Recipe[] mw = (Recipe[]) request.getAttribute("most_viewed");
        Recipe[] bn = (Recipe[]) request.getAttribute("brand_new");
    %>
    
        <!-- Rankings-->
        <div class="rankings">
            <!-- Ranking based on the recipes with the most viewed -->
            <label class="ranking-title">Brand new</label>
            <div id="ranking3" class="container">
                <%
                    String name, tags;
                    int id;
                    for(int i=0; i<bn.length; i++) {
                        id = bn[i].getId();
                        name = bn[i].getName();
                        tags = bn[i].getTags();
                %>
                <!-- Recipe card with all his info-->
                <div class="recipe-card">
                    <a href="recipeInfo?id=<%= id%>"><img id="recipe-card-img" src="images?id=<%= id%>"></a>
                    <label id="recipe-card-name"><%= name%></label> 
                    <div id="recipe-card-extras">
                        <label><%= tags%></label>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            <!-- Ranking based on the recipes with the best score -->
            <label class="ranking-title">Best Scores</label>
            <div id="ranking1" class="container">
                <%
                    for(int i=0; i<be.length; i++) {
                        id = be[i].getId();
                        name = be[i].getName();
                        tags = be[i].getTags();
                %>
                <!-- Recipe card with all his info-->
                <div class="recipe-card">
                    <a  href="recipeInfo?id=<%= id%>"><img id="recipe-card-img" src="images?id=<%= id%>"></a>  
                    <label id="recipe-card-name"><%= name%></label>
                    <div id="recipe-card-extras">
                        <label><%= tags%></label>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            <!-- Ranking based on the recipes with the most viewed -->
            <label class="ranking-title">Most viewed</label>
            <div id="ranking2" class="container">
                <%
                    for(int i=0; i<mw.length; i++) {
                        id = mw[i].getId();
                        name = mw[i].getName();
                        tags = mw[i].getTags();
                %>
                <!-- Recipe card with all his info-->
                <div class="recipe-card">
                    <a href="recipeInfo?id=<%= id%>"><img id="recipe-card-img" src="images?id=<%= id%>"></a> 
                    <label id="recipe-card-name"><%= name%></label> 
                    <div id="recipe-card-extras">
                        <label><%= tags%></label>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
            
        </div>
</body>
</html>
