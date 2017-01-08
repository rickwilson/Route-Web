<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route - Password Changed</title>
    <jsp:include page="/WEB-INF/views/client/includes/headerIncludes.jsp" flush="false" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="error-body">
<!-- BEGIN HEADER -->
<div style="background-color: black;">
    &nbsp;
    <a href="/">
        <img src="https://d1pv9ulu41r3n5.cloudfront.net/img/email/route-logo-white-on-black-email.jpg" height="60" />
    </a>
</div>
<!-- END HEADER -->
<!-- BEGIN CONTAINER -->
<div class="error-wrapper container">
    <div class="row">

    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-offset-1 col-xs-10">
            <div class="error-container" >
                <div class="error-main">
                    <div class="error-number"> Success </div>
                    <div class="error-description" > Password Changed </div>
                    <div class="error-description-mini"> You have successfully changed your password. Click the button below to log in. </div>
                    <br>
                    <br>
                    <a href="/"><button class="btn btn-primary btn-cons" type="button">Take me to my Dashboard...</button></a>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- END CONTAINER -->
<jsp:include page="/WEB-INF/views/client/includes/footerIncludes.jsp" flush="false" />
<!-- BEGIN Page Level JS-->
<script src="https://s3-us-west-2.amazonaws.com/route-assets/assets/js/login.js" type="text/javascript"></script>
<!-- END Page Level JS-->
</body>
</html>
