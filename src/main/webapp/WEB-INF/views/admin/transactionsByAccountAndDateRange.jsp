<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Transactions By Account</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css" />
</head>
<body>

<jsp:include page="/WEB-INF/views/admin/nav2.jsp" flush="true" >
    <jsp:param name="isDeveloper" value="${isDeveloper}" />
</jsp:include>

<div class="container">
    <h3>Transactions By Account</h3>
    <div class="row">
        <form action="/admin/transactionsByAccount" method="post" name="dateRangeForm">
            <span style="float: right"><input type="text" id="daterange" name="dateRangeVar" size="40" />&nbsp;&nbsp;<input type="submit" /></span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

    </div>
    <div class="row">&nbsp;</div>
    <div class="row">
        <table class="table table-striped">
            <tr>
                <th> Account Name </th>
                <th> Transactions </th>
            </tr>
            <c:forEach var="accountCount" items="${transactionsByAccount}">
                <tr>
                    <td><c:out value="${accountCount[0]}" /></td>
                    <td><c:out value="${accountCount[1]}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script type="text/javascript">
    $('#daterange').daterangepicker({
        "ranges": {
            "Today": [
                moment().format('L'),
                moment().format('L')
            ],
            "Yesterday": [
                moment().subtract(1,'days').format('L'),
                moment().subtract(1,'days').format('L')
            ],
            "This Week": [
                moment().startOf('week').format('L'),
                moment().endOf('week').format('L')
            ],
            "Last Week": [
                moment().subtract(1, 'weeks').startOf('week').format('L'),
                moment().subtract(1, 'weeks').endOf('week').format('L')
            ],
            "This Month": [
                moment().startOf('month').format('L'),
                moment().endOf('month').format('L')
            ],
            "Last Month": [
                moment().subtract(1, 'months').startOf('month').format('L'),
                moment().subtract(1, 'months').endOf('month').format('L')
            ]
        },
        "alwaysShowCalendars": true,
        "opens": "left",
        <c:choose>
        <c:when test="${empty startDateVar}">
        "startDate": moment().subtract(1,'days').format('L'),
        "endDate": moment().subtract(1,'days').format('L')
        </c:when>
        <c:otherwise>
        "startDate": "${startDateVar}",
        "endDate": "${endDateVar}"
        </c:otherwise>
        </c:choose>
    });
</script>

</body>
</html>
