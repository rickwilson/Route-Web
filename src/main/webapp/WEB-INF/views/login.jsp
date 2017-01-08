<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route - Login</title>
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
    <c:if test="${!empty successMsg}">
    <div class="row">
        <div class="col-md-10 col-md-offset-1 col-xs-12">
            <br><br>
            <div class="alert alert-success"> <c:out value="${successMsg}" /> </div>
        </div>
    </div>
    </c:if>
    <div class="row login-container column-seperation">
        <div class="col-md-5 col-md-offset-1">
            <h2>
                Welcome to Route!
            </h2>
            <p>
                Route is the World’s leading online order protection company. In partnership with world renowned
                Lloyd’s of London, Route safeguards against any lost, damaged, or stolen items. Online consumers can
                rest assured if they use Route, they’re covered against any loss…its that simple.<br>
                <a href="/register/client">Sign up Now!</a> for a Route account, It's free and easy..
            </p>
        </div>
        <div class="col-md-5">
            <br>
            <c:if test="${param.error != null}">
                <div class="alert alert-error"> Invalid username or password. </div>
            </c:if>
            <form action="<c:url value='/login'/>" class="login-form validate" id="login-form" method="post" name="login-form">
                <div class="row">
                    <div class="form-group col-md-10">
                        <label class="form-label">Email Address</label>
                        <input class="form-control" id="txtusername" name="username" type="email" value="${username}" required>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-10">
                        <label class="form-label">Password</label> <span class="help"></span>
                        <input class="form-control" id="txtpassword" name="password" type="password" value="${password}" required>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group col-md-10">
                        <a href="/register/client">CREATE ACCOUNT</a>
                        <a href="/forgot" style="float: right">FORGOT PASSWORD</a>
                    </div>
                </div>
                <div class="row">
                    &nbsp;
                </div>
                <div class="row">
                    <div class="col-md-10">
                        <button class="btn btn-primary btn-cons pull-right" type="submit">Login</button>
                    </div>
                </div>
            </form>
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
