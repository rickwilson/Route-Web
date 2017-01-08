<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>System Admin Dashboard</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="//cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawStacked);
        function drawStacked() {
            var data = google.visualization.arrayToDataTable([
                ['Day',  'Transactions', { role: 'style' }]
                <c:forEach var="dayOfTransactions" items="${last60DaysTransactions}">
                ,['${dayOfTransactions[1]}', ${dayOfTransactions[0]}, 'color: blue']
                </c:forEach>
            ]);

            var options = {
                title: 'Route Transactions - Last 60 Days',
                legend: { position: "none" },
                isStacked: true
            };

            var chart = new google.visualization.ColumnChart(document.getElementById('route_orders_chart_div'));
            chart.draw(data, options);
        }
    </script>
    <%--<link href="/css/admin/nav.css" rel="stylesheet" type="text/css" />--%>
</head>
<body>
<jsp:include page="/WEB-INF/views/admin/nav2.jsp" flush="true" >
    <jsp:param name="isDeveloper" value="${isDeveloper}" />
</jsp:include>
    <div class="container">
        <h3>System Admin Dashboard</h3>
        <div class="row">
            <div id="route_orders_chart_div" style="height: 300px;"></div>
        </div>
    </div>
</body>
</html>
