<%-- 
    Document   : search
    Created on : 12 abr. 2021, 12:29:43
    Author     : ignaren, dancruz, mariher
--%>

<%@page import="ssw.model.Client"%>
<%@page import="ssw.model.Recipe"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <title>Fantasty - Search</title>
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
    <script type="text/javascript" src="scripts/search.js" defer></script>
        <!--Search bar of the page-->
        <div class="search-bar">
            <input type="text" id="search-input" name="search-input" placeholder="What are you looking for?" value="">
            <!--Tags-->
            <div class="tags" >
                <button id="filter-breakfast" name="tag" value="breakfast" class="tag-item"><a>Breakfast</a></button>
                <button id="filter-american" name="tag"  value="american" class="tag-item"><a>American</a></button>
                <button id="filter-sweet" name="tag" value="sweet" class="tag-item"><a>Sweet</a></button>
                <button id="filter-vegan" name="tag" value="vegan" class="tag-item"><a>Vegan</a></button>
                <button id="filter-spanish" name="tag" value="spanish" class="tag-item"><a>Spanish</a></button>
                <button id="filter-meats" name="tag" value="meats" class="tag-item"><a>Meats</a></button>
                <button id="filter-fitness" name="tag" value="fitness" class="tag-item"><a>Fitness</a></button>
                <button id="filter-japanese" name="tag" value="japanese" class="tag-item"><a>Japanese</a></button>
                <button id="filter-italian"  name="tag" value="italian" class="tag-item"><a>Italian</a></button>
            </div>
        </div>
        <!-- Results -->
        <div id="search-results"></div>
        <div id="search-results-end"></div>
    </body>
</html>
