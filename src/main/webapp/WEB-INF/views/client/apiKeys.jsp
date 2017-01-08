<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>API Keys</title>
    <jsp:include page="/WEB-INF/views/client/includes/headerIncludes.jsp" flush="false" />
</head>
<body class="">
<jsp:include page="/WEB-INF/views/client/includes/topNavInclude.jsp" flush="true" >
    <jsp:param name="firstLast" value="${firstLast}" />
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
                <h3>API Keys</h3>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="grid simple ">
                        <div class="grid-body">
                            <table class="table table-striped" >
                                <thead>
                                <tr>
                                    <th>API Key</th>
                                    <th>Secret</th>
                                    <th>Rev Share Percent</th>
                                    <th>Ship Notifications</th>
                                    <th>Created</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="clientApiKeyObject" items="${clientApiKeyObjects}">
                                    <tr>
                                        <td><c:out value="${clientApiKeyObject.key}" /></td>
                                        <td><c:out value="${clientApiKeyObject.secret}" /></td>
                                        <td><c:out value="${clientApiKeyObject.revPercent}" /></td>
                                        <td><c:out value="${clientApiKeyObject.shipNotifications}" /></td>
                                        <td><fmt:formatDate value="${clientApiKeyObject.created}" pattern="dd MMM yyyy"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
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
