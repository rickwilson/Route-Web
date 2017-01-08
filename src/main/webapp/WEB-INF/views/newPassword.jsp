<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route - Change Password</title>
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
<div class="container">
    <div class="row login-container column-seperation">
        <div class="col-md-5 col-md-offset-1">
            <h2>
                Reset Your Password
            </h2>
            <p>
                You are here because you requested your password be changed. Enter a new password on the right and
                click the password. If you have issues, you can always re-request your password be reset and we will
                email you anew link. Your Account administrator can also reset your password for you.
            </p>
        </div>
        <div class="col-md-5">
            <form:form id="changePasswordForm" commandName="resetPasswordObject" method="POST" action="/d$621*sdfgs|2357!fgds975$j89!8ad79af/${resetPasswordObject.token}" class="form-no-horizontal-spacing">
                <div class="row form-row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <span class="help">Passwords Require: 8-30 characters, uppercase letter, lowercase letter, number, and special character.</span>
                        </div>
                    </div>
                </div>
                <div class="row form-row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <form:label for="password1" path="password1" class="form-label">Password</form:label>
                            <form:password path="password1" class="form-control" id="password1" name="password1" required="required"/>
                            <form:errors path="password1" class="control-label" style="color: red;" />
                        </div>
                    </div>
                </div>
                <div class="row form-row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <form:label for="password2" path="password2" class="form-label">Re-enter Password</form:label>
                            <form:password path="password2" class="form-control" id="password2" name="password2" required="required"/>
                            <form:errors path="password2" class="control-label" style="color: red;" />
                        </div>
                    </div>
                </div>
                <div class="row form-row">
                    <div class="col-md-12">
                        <div class="form-group pull-right">
                            <br><br>
                            <input type="hidden" name="key" value="${resetPasswordObject.key}" />
                            <input type="hidden" name="email" value="${resetPasswordObject.email}" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                            <button class="btn btn-primary btn-cons" type="submit"><i class="icon-ok"></i> Change Password</button>
                        </div>
                    </div>
                </div>
            </form:form>
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
