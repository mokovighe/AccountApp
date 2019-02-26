<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.ebuka.model.UserModel"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="shortcut icon" href="<c:url value='/assets/images/logo.png'/>" type="image/x-icon">
 <meta name="description" content="">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese" />   
<link rel="stylesheet" type="text/css" href="<c:url value='/assets/bootstrap/css/bootstrap.min.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/assets/animate.css/animate.min.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/assets/mobirise/css/style.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/assets/mobirise/css/mbr-additional.css'/>" />
<title>All Users</title>
</head>
<body>
<%
			UserModel currentUser = (UserModel) (session.getAttribute("currentSessionUser"));
		%>
	
	<section class="mbr-navbar mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--sticky mbr-navbar--auto-collapse" id="ext_menu-0">
    <div class="mbr-navbar__section mbr-section">
        <div class="mbr-section__container container">
            <div class="mbr-navbar__container">
                <div class="mbr-navbar__column mbr-navbar__column--s mbr-navbar__brand">
                    <span class="mbr-navbar__brand-link mbr-brand mbr-brand--inline">
                        
                        <span class="mbr-brand__name"><a class="mbr-brand__name text-white" href="welcome.jsp">Hi, <%= currentUser.getUsername() %> !</a></span>
                    </span>
                </div>
                <div class="mbr-navbar__hamburger mbr-hamburger"><span class="mbr-hamburger__line"></span></div>
                <div class="mbr-navbar__column mbr-navbar__menu">
                    <nav class="mbr-navbar__menu-box mbr-navbar__menu-box--inline-right">
                        <div class="mbr-navbar__column">
                            <ul class="mbr-navbar__items mbr-navbar__items--right float-left mbr-buttons mbr-buttons--freeze mbr-buttons--right btn-decorator mbr-buttons--active">
                            <li class="mbr-navbar__item">
                            <a class="mbr-buttons__link btn text-white" href="${pageContext.request.contextPath}/UserView">USERS</a>
                            </li>
                            </ul>                            
                            <ul class="mbr-navbar__items mbr-navbar__items--right mbr-buttons mbr-buttons--freeze mbr-buttons--right btn-inverse mbr-buttons--active"><li class="mbr-navbar__item">
                           
                            <form action="${pageContext.request.contextPath}/Logout" method="post">
    	<input class="mbr-buttons__btn btn btn-danger" type="submit" value="Logout" />
	</form>
                            </li></ul>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="mbr-box mbr-section mbr-section--relative mbr-section--fixed-size mbr-section--full-height mbr-section--bg-adapted mbr-parallax-background" id="header4-0" style="background:#ffffff;">
    <div class="mbr-box__magnet mbr-box__magnet--sm-padding mbr-box__magnet--center-center mbr-after-navbar">
        
        <div class="mbr-box__container mbr-section__container container">
            <div class="mbr-box mbr-box--stretched">
            <div class="mbr-box__magnet mbr-box__magnet--center-center">
                <div class="row">
                	<h3>User Lists</h3>
                	
                	<table class="blueTable" style="border: 1px solid #1C6EA4; background-color: #EEEEEE; width: 100%; text-align: left; border-collapse: collapse;">
					<tr>
						<th style="border: 1px solid #AAAAAA;padding: 0px 1px;">ID</th>
						<th style="border: 1px solid #AAAAAA;padding: 0px 1px;">User Name</th>
						<th style="border: 1px solid #AAAAAA;padding: 0px 1px;">Surname</th>
						<th style="border: 1px solid #AAAAAA;padding: 0px 1px;">Other Names</th>
						<th style="border: 1px solid #AAAAAA;padding: 0px 1px;">Role</th>
					</tr>
					
					<c:forEach var="list" items="${lists}">
						<tr>
							<td style="border: 1px solid #AAAAAA;padding: 0px 1px; font-size: 13px;">${list.getId()}</td>
							<td style="border: 1px solid #AAAAAA;padding: 0px 1px; font-size: 13px;">${list.getUsername()}</td>
							<td style="border: 1px solid #AAAAAA;padding: 0px 1px; font-size: 13px;">${list.getSurname()}</td>
							<td style="border: 1px solid #AAAAAA;padding: 0px 1px; font-size: 13px;">${list.getOthernames()}</td>
							<td style="border: 1px solid #AAAAAA;padding: 0px 1px; font-size: 13px;">${list.getRole()}</td>
						</tr>
					</c:forEach>
				</table>
                </div>
            </div></div>
        </div>
        
    </div>
</section>

<footer class="mbr-section mbr-section--relative mbr-section--fixed-size" id="footer1-0" style="background-color: rgb(68, 68, 68);">
    
    <div class="mbr-section__container container">
        <div class="mbr-footer mbr-footer--wysiwyg row" style="padding-top: 15.900000000000006px; padding-bottom: 15.900000000000006px;">
            <div class="col-sm-12">
                <p class="mbr-footer__copyright">Copyright (c) 2019 Company Name.</p>
            </div>
        </div>
    </div>
</footer>


  <script src="assets/web/assets/jquery/jquery.min.js"></script>
  <script src="assets/bootstrap/js/bootstrap.min.js"></script>
  <script src="assets/smooth-scroll/SmoothScroll.js"></script>
  <script src="assets/jarallax/jarallax.js"></script>
  <script src="assets/mobirise/js/script.js"></script>
</body>
</html>