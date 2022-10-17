<%-- 
    Document   : new-recipe
    Created on : 12 abr. 2021, 12:27:37
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
    <title>Fantasty - Recipe Creation</title>
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
    <main>
        <script type="text/javascript" src="scripts/new_recipe.js" defer></script>
        <div id="new-recipe-container" class="container">
            <!-- Basic info about the recipe -->
            <form id="new-recipe-form" action="newRecipe?create=yes" method="post" enctype="multipart/form-data">
                <div id="new-recipe-basic-info">
                    <input id="nr-name" type="text"  name="name" placeholder="Name (Don`t use symbols)" pattern="[a-zA-Z0-9 ]*" minlength="5" required>
                    <input id="nr-time" type="number"  name="time" placeholder="Time (min)" min="5" required>
                    <input id="nr-visibility" type="checkbox" name="visibilty-checkbox"><label>Public</label>
                    <input id="nr-hidden-visibility" type="hidden" name="visibility" value="inactive">
                </div>
                <!-- Media drap & drop upload -->
                <div id="new-recipe-multimedia">
                    <div id="multimedia-drag-drop">
                        <label id="nr-multimedia-label" for="nr-multimedia">Upload files</label>
                        <input type="file" id="nr-multimedia" name="multimedia" accept="image/*"  multiple="multiple" required/>
                    </div>
                    <img id="nr-multimedia-img">
                </div>
                <!-- Details about the recipe (process, ingredients and tags) -->
                <div id="new-recipe-details">
                    <textarea id="nr-process" name="process" placeholder="Write here the steps in the recipe!" minlength="50" required></textarea>
                    <textarea id="nr-ingredients" name="ingredients" placeholder="Write here the ingredients of the recipe!" required></textarea>
                    <div id="nr-extras">
                        <div id="tags-form">
                            <label id="new-recipe-tags-label" for="new-recipe-tags">Tags (At least one): </label>
                            <select name="new-recipe-tags" id="new-recipe-tags" onchange="addTag(value)">
                                <option value="" disabled selected hidden>Select Tags</option>
                                <!-- Real tags of the recipe -->
                                <option value="Sweet">Sweet</option>
                                <option value="Vegan">Vegan</option>
                                <option value="Italian">Italian</option>
                                <option value="Breakfast">Breakfast</option>
                                <option value="Spanish">Spanish</option>
                                <option value="Meats">Meats</option>
                                <option value="American">American</option>
                                <option value="Fitness">Fitness</option>
                                <option value="Japanese">Japanese</option>
                            </select>
                            <input id="nr-tags" type="hidden" name="selected-tags" >
                        </div>
                        <div id="new-recipe-current-tags"></div>
                    </div>
                </div>
            
                <!-- Confirmation or cancel of the process-->
                <input onclick="window.location.href='ranking'" id="cancel-button" name="cancelar" class="end-process-button" 
                       type ="button" value="Cancelar">
                <input id="confirm-button" name="submit" class="end-process-button" type="submit" value="Aceptar">
           
            </form>
             
        </div>

    </main>
    </body>
</html>
