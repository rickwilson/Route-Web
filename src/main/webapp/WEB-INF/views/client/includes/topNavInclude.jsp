<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse">
    <!-- BEGIN TOP NAVIGATION BAR -->
    <div class="navbar-inner">
        <!-- BEGIN NAVIGATION HEADER -->
        <div class="header-seperation" style="background-color: black;">
            <!-- BEGIN MOBILE HEADER -->
            <ul class="nav pull-left notifcation-center visible-xs visible-sm">
                <li class="dropdown">
                    <a href="#main-menu" data-webarch="toggle-left-side">
                        <div class="iconset top-menu-toggle-white"></div>
                    </a>
                </li>
            </ul>
            <!-- END MOBILE HEADER -->
            <!-- BEGIN LOGO -->
            <a href="/">
                <img src="https://d1pv9ulu41r3n5.cloudfront.net/img/email/route-logo-white-on-black-email.jpg" height="60" />
            </a>
            <!-- END LOGO -->
        </div>
        <!-- END NAVIGATION HEADER -->
        <!-- BEGIN CONTENT HEADER -->
        <div class="header-quick-nav">
            <!-- BEGIN HEADER RIGHT SIDE SECTION -->
            <div class="pull-right">
                <div class="chat-toggler">
                    <div class="user-details">
                        <span class="bold">Welcome ${param.firstLast}</span>
                    </div>
                </div>
                <!-- BEGIN HEADER NAV BUTTONS -->
                <ul class="nav quick-section">
                    <!-- BEGIN SETTINGS -->
                    <li class="quicklinks">
                        <a data-toggle="dropdown" class="dropdown-toggle pull-right" href="#" id="user-options">
                            <div class="iconset top-settings-dark"></div>
                        </a>
                        <ul class="dropdown-menu pull-right" role="menu" aria-labelledby="user-options">
                            <li class="semi-bold">&nbsp;&nbsp;Account Name:</li>
                            <li>&nbsp;&nbsp;&nbsp;<c:out value="${param.accountName}" /></li>
                            <li><span class="semi-bold">&nbsp;&nbsp;Account Type:</span></li>
                            <li>&nbsp;&nbsp;&nbsp;<c:out value="${param.accountType}" /></li>
                            <li><span class="semi-bold">&nbsp;&nbsp;Account Created:</span></li>
                            <li>&nbsp;&nbsp;&nbsp;<c:out value="${param.accountCreated}" /></li>
                            <li class="divider"></li>
                            <li><a href="/client/editMyUser">My User</a></li>
                            <li><a href="/logout"><i class="fa fa-power-off"></i>&nbsp;&nbsp;Sign Out</a></li>
                        </ul>
                    </li>
                    <!-- END SETTINGS -->
                </ul>
            </div>
            <!-- END HEADER RIGHT SIDE SECTION -->
        </div>
        <!-- END CONTENT HEADER -->
    </div>
    <!-- END TOP NAVIGATION BAR -->
</div>
<!-- END HEADER -->