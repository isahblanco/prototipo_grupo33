<%-- 
    Document   : book
    Created on : 12 abr. 2021, 12:30:10
    Author     : ignaren, dancruz, mariher
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="ssw.model.Recipe"%>
<%@page import="ssw.model.Client"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Fantasty - Book</title>
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
<script type="text/javascript" src="scripts/book.js" defer></script>
    <!-- Recipes -->  
    <% 
        ArrayList<Recipe> myRecipes = (ArrayList<Recipe>)request.getAttribute("myRecipes");
        ArrayList<Recipe> recipesBook = (ArrayList<Recipe>)request.getAttribute("recipesBook");
    %>
    
    <main class="container">
        <div id="book-button-container">
            <button id="my-recipes-button" class="book-button">My Recipes</button>
            <button id="saved-recipes-button" class="book-button">Saved Recipes</button>
        </div>
        <div id="book-pages-container">
            <div id="my-recipes-page" class="book-pages not-selected">
                <%
                    String name, tags;
                    int id;
                    int i;
                    for(i=0; i<myRecipes.size(); i++) {
                        id = myRecipes.get(i).getId();
                        name = myRecipes.get(i).getName();
                        tags = myRecipes.get(i).getTags();
                %>
                <!-- Recipe card with all his info-->
                <div class="book-recipe-card">
                    <a href="recipeInfo?id=<%= id%>"><img id="recipe-card-img" src="images?id=<%= id%>"></a>
                    <label id="recipe-card-name"><%= name%></label>
                    <a href="modify_recipe?id=<%= id%>" class="recipe-card-modify-button recipe-card-button"><img src="media/icons/editar.png"></a>
                    <a id="book-own-recipe-card-deletion" class="recipe-card-delete-button recipe-card-button" data-id="<%= id%>"><img src="media/icons/bin.png"></a> 
                </div>               
                <%
                    }
                %>
            </div>
            <div id="saved-recipes-page" class="book-pages selected">    
                <% 
                    for(i=0; i<recipesBook.size(); i++) {
                        id = recipesBook.get(i).getId();
                        name = recipesBook.get(i).getName();
                        tags = recipesBook.get(i).getTags();
                %>
                <!-- Recipe card with all his info-->
                <div class="book-recipe-card">
                    <a  href="recipeInfo?id=<%= id%>"><img id="recipe-card-img" src="images?id=<%= id%>"></a>
                    <label id="recipe-card-name"><%= name%></label>
                    <a id="book-saved-recipe-card-deletion" class="recipe-card-delete-button recipe-card-button" data-id="<%= id%>"><img src="media/icons/bin.png"></a>  
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </main>
    </body>
</html>
