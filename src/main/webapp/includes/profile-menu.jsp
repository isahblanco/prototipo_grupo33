<%-- 
    Document   : profile-menu
    Created on : 27 abr. 2021, 22:13:49
    Author     : ignaren, dancruz, mariher
--%>

<div id="profile-menu" class="hidden">
    <div id="pm-basic-info">
        <img id="pm-pic" src="client_image?id=${client.id}">
        <div id="pm-data">
            <label id="pm-name"><%= client.getName()%> <%= client.getSurnames()%></label>
            <label id="pm-title"><%= client.getTitle()%></label>
        </div>
    </div>
    <div id="pm-secondary-info">
        <a href="profile_view?self=true">View profile</a>
        <a href="client_profile_edit.jsp">Edit profile</a>
        <a href="logout">Log out</a>
    </div>
</div>
