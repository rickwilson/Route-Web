<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Accounts</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/views/admin/nav2.jsp" flush="true" >
    <jsp:param name="isDeveloper" value="${isDeveloper}" />
</jsp:include>
    <div class="container">
        <h3>View Accounts</h3>
        <div class="row">
            <table class="table table-striped">
                <tr>
                    <th> Account Id </th>
                    <th> Account Name </th>
                    <th> Account Type </th>
                    <th> API Keys </th>
                    <%--<th> Add Keys </th>--%>
                    <th> Active </th>
                    <th> Created </th>
                    <th> Deactivated </th>
                    <th>Ghost</th>
                </tr>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                            <%--<td><a href="/admin/viewAccount?id=${account.id}"><c:out value="${account.id}" /></a></td>--%>
                        <td><c:out value="${account.id}" /></td>
                        <td><c:out value="${account.name}" /></td>
                        <td><c:out value="${account.type}" /></td>
                        <td>
                            <c:forEach var="key" items="${accountApiKeys[account.id]}">
                                <%--<a href="/admin/addCrmAccess?apiKey=${key.apiKey}"><c:out value="${key.apiKey}"/></a>--%>
                                <c:out value="${key.apiKey}"/><br>
                            </c:forEach>
                        </td>
                            <%--<td><a href="/admin/addNewApiKey?accountId=${account.id}">Add Another API Key</a></td>--%>
                        <td><c:out value="${account.active}" /></td>
                        <td><c:out value="${account.created}" /></td>
                        <td><c:out value="${account.deactivated}" /></td>
                        <td><a href="/admin/ghost?aId=${account.id}">GHOST</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</body>
</html>
