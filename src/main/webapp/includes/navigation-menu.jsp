<%-- 
    Document   : navigation-menu
    Created on : 12 abr. 2021, 12:13:33
    Author     : ignaren, dancruz, mariher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="scripts/navigation_menu.js" defer></script>
<!-- Menu for navigation in the page. Common for every page in the website-->
<div class="upper-menu">
    <!-- Website brand info: logo and name-->
    <img id="logo" src="media/icons/logo2.png">
    <!-- Links for other pages in the website-->
    <div class="links">
        <a href="ranking">Home</a>
        <a href="search.jsp">Search</a>
        <a href ="login?menu=1&redir=new">New recipe</a>
        <a href="login?menu=1&redir=book">Book</a>
    </div>
    <!-- Profile information-->
    <div class="profile">
        <div id="profile-options">
            <a href="login?menu=1&redir=home">Log in</a>
            <a href="register?new=1">Sign up</a>
        </div>
    </div>
</div>
