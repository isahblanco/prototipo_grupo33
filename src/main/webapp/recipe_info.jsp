<%-- 
    Document   : recipe-info
    Created on : 12 abr. 2021, 12:27:47
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
    <title>Fantasty - Recipe Details</title>
</head>
<body>
<% 
    Object clientObject = request.getSession().getAttribute("client");
    if (clientObject != null) {
        Client client = (Client) clientObject;
%>
<%@ include file="/includes/navigation_menu_logged.jsp" %>
<script type="text/javascript" src="scripts/recipe_info.js" defer></script>
<%
    } else {
%>
<%@ include file="/includes/navigation-menu.jsp" %>
<%
    }
%>
    <jsp:useBean id="recipe"        scope="request" class="ssw.model.Recipe"/>
    <%
        Recipe[] recommendations = (Recipe[]) request.getAttribute("recommendations");
    %>
    <!-- Recipe information-->
        <div id="recipe-page" data-recipe="${recipe.id}" data-client="${client.id}">
            <div id="recipe-information" class="container">
                <!-- Main info about the recipe-->
                <div id="recipe-basic-information">
                    <label id="recipe-name">${recipe.name}</label>
                    <%
                    if (clientObject != null) {
                    %>
                        <button id="recipe-save-button">Save</button>
                    <%
                    }
                    %>
                    <div id="recipe-score-place">
                        <label id="recipe-score-label">score</label>
                        <div id="recipe-score-modification">
                        
                            <%
                        if (clientObject != null) {
                         %>
                            <button>▲</button>
                            <%
                        }
                        %>
                            <label id="recipe-score">${recipe.score}</label>
                            <%
                        if (clientObject != null) {
                         %>
                            <button>▼</button>
                            <%
                        }
                        %>
                        </div>
                    </div>
                    <div id="recipe-tags">
                        <label>${recipe.tags}</label>
                    </div>
                    <a href="profile_view?profile=${recipe.creator.id}"><img id="profile-pic" src="client_image?id=${recipe.creator.id}"></a>
                </div>
                
                <!-- Secondary info about the recipe -->
                <div id="recipe-secondary-information">
                    <!-- Media related to the recipe-->
                    <div id="recipe-media">
                        <img id="recipe-main-media" src="images?id=${recipe.id}">
                    </div>
                    <!-- Textual information about the process of elaborating the recipe -->
                    <div id="recipe-description">
                        <div id="recipe-ingredients">
                            <h1>Ingredients</h1>
                            <% 
                               String[] ingredients = recipe.getIngredients().split("\n");
                               for(int ingredient=0; ingredient<ingredients.length; ingredient++){
                            %>
                                <ul><%= ingredients[ingredient]%></ul>
                            <%
                               }
                            %>
                        </div>
                        <div id="recipe-process">
                            <h1>Process (${recipe.preparationTime} min)</h1>
                            <p>${recipe.steps}</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Suggestions-->
            <div id="suggestions" class="container">
                <% 
                    int numberRecommendations = (recommendations.length > 4) ? 4 : recommendations.length;
                    for(int i=0; i<numberRecommendations; i++){
                %>
                <a href="recipeInfo?id=<%= recommendations[i].getId()%>"><img class="recipe-suggestion" src="images?id=<%= recommendations[i].getId()%>"></a>
                <%
                    }
                %>
            </div>
        </div>
        </main>
    </body>
</html>
