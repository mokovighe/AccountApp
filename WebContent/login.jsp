<%@ page import="java.sql.Connection" %>
<%@ page import="com.ebuka.dbWork.DBManager" %>
<%@ page import="java.sql.DriverManager" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="generator" content="Mobirise v3.5.1, mobirise.com">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link rel="shortcut icon" href="assets/images/logo.png" type="image/x-icon">
 <meta name="description" content="">
 
 <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:700,400&amp;subset=cyrillic,latin,greek,vietnamese">
 <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
 <link rel="stylesheet" href="assets/animate.css/animate.min.css">
 <link rel="stylesheet" href="assets/mobirise/css/style.css">
 <link rel="stylesheet" href="assets/mobirise/css/mbr-additional.css" type="text/css">
<title>Login</title>
</head>
<body>

<section class="mbr-navbar mbr-navbar--freeze mbr-navbar--absolute mbr-navbar--sticky mbr-navbar--auto-collapse" id="ext_menu-0">
    <div class="mbr-navbar__section mbr-section">
        <div class="mbr-section__container container">
            <div class="mbr-navbar__container">
                <div class="mbr-navbar__column mbr-navbar__column--s mbr-navbar__brand">
                    <span class="mbr-navbar__brand-link mbr-brand mbr-brand--inline">
                        
                        <span class="mbr-brand__name"><a class="mbr-brand__name text-white" href="#">COMPANY-NAME</a></span>
                    </span>
                </div>
                <div class="mbr-navbar__hamburger mbr-hamburger"><span class="mbr-hamburger__line"></span></div>
                
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
                	<h3>Login with username and password</h3>
                	<br><span style="color:red;">${message}</span><br><br>
<form action="Login" method="post">

<strong>UserName</strong>:<input type="text" name="username"><br><br>
<strong>Password</strong>:<input type="password" name="passw"><br><br>
<input type="submit" name="submit" value="Login">
</form>

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