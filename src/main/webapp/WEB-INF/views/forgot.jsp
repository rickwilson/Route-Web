<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route - Reset Password</title>
    <jsp:include page="/WEB-INF/views/client/includes/headerIncludes.jsp" flush="false" />
    <script src='https://www.google.com/recaptcha/api.js'></script>
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
<div class="container">
    <div class="row login-container column-seperation">
        <div class="col-md-5 col-md-offset-1">
            <h2>
                Reset Your Password
            </h2>
            <p>
                Forgot your password? Then you're in the right place. Complete this form and we will send a link
                to your email address on file. When your password link arrives, be sure to click it right
                away, as the link will expire in a few hours.
                <br><br>
                If you don't receive an email right away, check your email spam folder(s). If you don't receive any
                email from us at all, it either means we don't have your email address in our
                system or it was misspelled. Your account administrator can also change your password for you.
            </p>
        </div>
        <div class="col-md-5">
            <br>
            <c:choose>
                <c:when test="${!empty actionSuccess}">
                    <div class="alert alert-success"> Check your email. You should receive an email shortly, with a link to reset your password. </div>
                </c:when>
                <c:otherwise>
                    <c:if test="${!empty noCaptcha}" >
                        <div class="alert alert-error"> Captcha Issue: Make sure to mark the checkbox labeled <span class="semi-bold">I am not a robot</span>. </div>
                    </c:if>
                    <form action="resetEmail" class="login-form validate" id="reset-email-form" method="post" name="reset-email-form">
                        <div class="row">
                            <div class="form-group col-md-10">
                                <label class="form-label">Email Address</label>
                                <input class="form-control" id="email" name="email" type="email" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="control-group col-md-10">
                                <div class="g-recaptcha" data-sitekey="${site_key}"></div>
                            </div>
                        </div>
                        <div class="row">
                            &nbsp;
                        </div>
                        <div class="row">
                            <div class="col-md-10">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button class="btn btn-primary btn-cons pull-right" type="submit">Send Reset Password Email</button>
                            </div>
                        </div>
                    </form>
                </c:otherwise>
            </c:choose>
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
