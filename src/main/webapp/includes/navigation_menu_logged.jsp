<%-- 
    Document   : navigation-menu
    Created on : 12 abr. 2021, 12:13:33
    Author     : ignaren, dancruz, mariher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="scripts/navigation_menu.js" defer></script>
<script type="text/javascript" src="scripts/navigation_menu_logged.js" defer></script>
<!-- Menu for navigation in the page. Common for every page in the website-->
<div class="upper-menu">
    <!-- Website brand info: logo and name-->
    <img id="logo" src="media/icons/logo2.png">
    <!-- Links for other pages in the website-->
    <div class="links">
        <a href="ranking">Home</a>
        <a href="search.jsp">Search</a>
        <a href ="newRecipe">New recipe</a>
        <a href="book">Book</a>
    </div>
    <!-- Profile information-->
    <%@ include file="/includes/profile-menu.jsp" %>
    <div class="profile">
        <img id="profile-pic" src="client_image?id=${client.id}">
    </div>
</div>
