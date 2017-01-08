<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
    <jsp:include page="/WEB-INF/views/client/includes/headerIncludes.jsp" flush="false" />
</head>
<body class="">
<jsp:include page="/WEB-INF/views/client/includes/topNavInclude.jsp" flush="true" >
    <jsp:param name="firstLast" value="${clientDashboardObject.firstLast}" />
    <jsp:param name="accountName" value="${accountName}" />
    <jsp:param name="accountType" value="${accountType}" />
    <jsp:param name="accountCreated" value="${accountCreated}" />
</jsp:include>
<!-- BEGIN CONTENT -->
<div class="page-container row-fluid">
    <jsp:include page="/WEB-INF/views/client/includes/sideMenuInclude.jsp" flush="false" />
    <!-- BEGIN PAGE CONTAINER-->
    <div class="page-content">
        <div class="content">
            <!-- BEGIN PAGE TITLE -->
            <div class="page-title">
                <h3>Add User</h3>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="grid simple">
                        <div class="grid-body no-border">
                            <form:form id="editClientUserObject" commandName="clientUserObject" method="POST" action="/client/saveNewUser" class="form-no-horizontal-spacing">
                                <div class="row">
                                    <div class="col-md-12">
                                        &nbsp;
                                        <div class="row form-row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <form:label for="first" path="first" class="form-label">First Name</form:label>
                                                    <form:input path="first" class="form-control" id="first" name="first" required="required"/>
                                                    <form:errors path="first" class="control-label" style="color: red;" />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <form:label for="last" path="last" class="form-label">Last Name</form:label>
                                                    <form:input path="last" class="form-control" id="last" name="last" required="required"/>
                                                    <form:errors path="last" class="control-label" style="color: red;" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <form:label for="email" path="email" class="form-label">Email Address</form:label>
                                                    <form:input path="email" class="form-control" id="email" name="email" required="required"/>
                                                    <form:errors path="email" class="control-label" style="color: red;" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-row">
                                            <div class="col-md-12">
                                                <div class="form-group">
                                                    <span class="help">Passwords Require: 8-30 characters, uppercase letter, lowercase letter, number, and special character.</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <form:label for="password" path="password" class="form-label">Password</form:label>
                                                    <form:input path="password" class="form-control" id="password" name="password" required="required"/>
                                                    <form:errors path="password" class="control-label" style="color: red;" />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <form:label for="password2" path="password2" class="form-label">Re-enter Password</form:label>
                                                    <form:input path="password2" class="form-control" id="password2" name="password2" required="required"/>
                                                    <form:errors path="password2" class="control-label" style="color: red;" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row form-row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <form:label for="role" path="role" class="form-label">User Role</form:label>
                                                    <form:select path="role" class="form-control" id="role" name="role" items="${clientUserObject.rolesMap}"/>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="form-group pull-right">
                                                    <br><br>
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                    <button class="btn btn-primary btn-cons" type="submit"><i class="icon-ok"></i> Save</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END PAGE CONTAINER -->
</div>
<!-- END CONTENT -->
<jsp:include page="/WEB-INF/views/client/includes/footerIncludes.jsp" flush="false" />
</body>
</html>
