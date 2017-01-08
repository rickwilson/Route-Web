<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weekly Analytics</title>
    <meta http-equiv="refresh" content="1800">
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load('current', {packages: ['corechart', 'bar']});
        google.charts.setOnLoadCallback(drawStacked);

        function drawStacked() {
            // This Week
            var thisWeek = google.visualization.arrayToDataTable([
                ['Tracking', 'Received', { role: 'style' }, 'Short', { role: 'style' }],
                ['Transactions', <c:out value="${transactionsThisWeek}" />, 'color: #1fb6c6', <c:out value="${transactionsSHORTThisWeek}" />, 'color: white; opacity: .1'],
                ['Registrations', <c:out value="${accountsThisWeek}" />, 'color: #1fb6c6', <c:out value="${accountsSHORTThisWeek}" />, 'color: white; opacity: .1']
            ]);

            var options = {
                isStacked: 'percent',
                legend: { position: "none" },
                hAxis: {
                    ticks: [0, .25, .5, .75, 1]
                }
            };
            var chart_this_week = new google.visualization.BarChart(document.getElementById('chart_div_this_week'));
            chart_this_week.draw(thisWeek, options);
            // Last Week
            var lastWeek = google.visualization.arrayToDataTable([
                ['Tracking', 'Received', { role: 'style' }, 'Short', { role: 'style' }],
                ['Transactions', <c:out value="${transactionsLastWeek}" />, 'color: #1fb6c6', <c:out value="${transactionsSHORTLastWeek}" />, 'color: LightPink; opacity: .3'],
                ['Registrations', <c:out value="${accountsLastWeek}" />, 'color: #1fb6c6', <c:out value="${accountsSHORTLastWeek}" />, 'color: LightPink; opacity: .3']
            ]);

            var options = {
                isStacked: 'percent',
                legend: { position: "none" },
                hAxis: {
                    ticks: [0, .25, .5, .75, 1]
                }
            };
            var chart_last_week = new google.visualization.BarChart(document.getElementById('chart_div_last_week'));
            chart_last_week.draw(lastWeek, options);
            // Two Weeks Ago
            var twoWeeksAgo = google.visualization.arrayToDataTable([
                ['Tracking', 'Received', { role: 'style' }, 'Short', { role: 'style' }],
                ['Transactions', <c:out value="${transactionsTwoWeeksAgo}" />, 'color: #1fb6c6', <c:out value="${transactionsSHORTTwoWeeksAgo}" />, 'color: LightPink; opacity: .3'],
                ['Registrations', <c:out value="${accountsTwoWeeksAgo}" />, 'color: #1fb6c6', <c:out value="${accountsSHORTTwoWeeksAgo}" />, 'color: LightPink; opacity: .3']
            ]);

            var options = {
                isStacked: 'percent',
                legend: { position: "none" },
                hAxis: {
                    ticks: [0, .25, .5, .75, 1]
                }
            };
            var chart_two_weeks_ago = new google.visualization.BarChart(document.getElementById('chart_div_two_weeks_ago'));
            chart_two_weeks_ago.draw(twoWeeksAgo, options);
            // Three Weeks Ago
            var threeWeeksAgo = google.visualization.arrayToDataTable([
                ['Tracking', 'Received', { role: 'style' }, 'Short', { role: 'style' }],
                ['Transactions', <c:out value="${transactionsThreeWeeksAgo}" />, 'color: #1fb6c6', <c:out value="${transactionsSHORTThreeWeeksAgo}" />, 'color: LightPink; opacity: .3'],
                ['Registrations', <c:out value="${accountsThreeWeeksAgo}" />, 'color: #1fb6c6', <c:out value="${accountsSHORTThreeWeeksAgo}" />, 'color: LightPink; opacity: .3']
            ]);

            var options = {
                isStacked: 'percent',
                legend: { position: "none" },
                hAxis: {
                    ticks: [0, .25, .5, .75, 1]
                }
            };
            var chart_three_weeks_ago = new google.visualization.BarChart(document.getElementById('chart_div_three_weeks_ago'));
            chart_three_weeks_ago.draw(threeWeeksAgo, options);

        }
    </script>
    <style>
        #topbar{
            position:fixed;
            top:0;
            left:0;
            right:0;
            background-color: black;
        }
        section {
            width: 100%;
            margin: auto;
        }
        div#leftSide {
            width: 50%;
            float: left;
        }
        div#rightSide {
            margin-left: 50%;
        }
    </style>
</head>
<body>
<div id="topbar">
    <a href="/admin/"><img src="https://d1pv9ulu41r3n5.cloudfront.net/img/email/route-logo-white-on-black-email.jpg" width="200" alt="logo" border="0" style="display:inline; border:none; outline:none; text-decoration:none; margin-left: 10px;"></a>
    <span style="color: white; float: right; margin-right: 10px; margin-top: 20px;"><c:out value="${lastUpdated}" /></span>
</div>
<section style="margin-top: 60px;">
    <div id="leftSide">
        <section style="margin-bottom: 20px; text-align: center;">
            <div style="float: left; width: 33%;">
                <div style="width: 95%; height: 10px; border-bottom: 1px solid black; text-align: left; margin-bottom: 15px;">
                    <span style="font-size: 20px; background-color: white; padding: 0 10px;">
                        New Accounts
                    </span>
                </div>
                <span style="font-size: 50px; text-align: center;">
                    <c:out value="${newAccounts}" />
                </span>
            </div>
            <div style="display: inline-block; width: 33%;">
                <div style="width: 95%; height: 10px; border-bottom: 1px solid black; text-align: left; margin-bottom: 15px;">
                    <span style="font-size: 20px; background-color: white; padding: 0 10px;">
                        Active Accounts
                    </span>
                </div>
                <span style="font-size: 50px; text-align: center;">
                    <c:out value="${activeAccounts}" />
                </span>
            </div>
            <div style="float: right; width: 33%;">
                <div style="width: 95%; height: 10px; border-bottom: 1px solid red; text-align: left; margin-bottom: 15px;">
                    <span style="font-size: 20px; background-color: white; padding: 0 10px; color: red">
                        TBD
                    </span>
                </div>
                <span style="font-size: 50px; color: red; text-align: center;">
                    ?
                </span>
            </div>
        </section>
        <div style="width: 95%; height: 10px; border-bottom: 1px solid black; text-align: left; margin-bottom: 15px;">
            <span style="font-size: 20px; background-color: white; padding: 0 10px;">
                This Week
            </span>
        </div>
        <div id="chart_div_this_week"></div>
        <div style="width: 95%; height: 10px; border-bottom: 1px solid black; text-align: left; margin-bottom: 15px;">
            <span style="font-size: 20px; background-color: white; padding: 0 10px;">
                Last Week
            </span>
        </div>
        <div id="chart_div_last_week"></div>
        <div style="width: 95%; height: 10px; border-bottom: 1px solid black; text-align: left; margin-bottom: 15px;">
            <span style="font-size: 20px; background-color: white; padding: 0 10px;">
                Two Weeks Ago
            </span>
        </div>
        <div id="chart_div_two_weeks_ago"></div>

        <div style="width: 95%; height: 10px; border-bottom: 1px solid black; text-align: left; margin-bottom: 15px;">
            <span style="font-size: 20px; background-color: white; padding: 0 10px;">
                Three Weeks Ago
            </span>
        </div>
        <div id="chart_div_three_weeks_ago"></div>
    </div>
    <div id="rightSide">
        <div style="width: 95%; height: 10px; border-bottom: 1px solid black; text-align: left; margin-bottom: 15px;">
            <span style="font-size: 20px; background-color: white; padding: 0 10px;">
                Last 50 Transactions
            </span>
        </div>
        <table style="width: 100%;">
            <c:forEach var="analyticsTransaction" items="${last50Trans}">
                <tr>
                    <td><c:out value="${analyticsTransaction.accountName}" /></td>
                    <td><c:out value="${analyticsTransaction.transactionId}" /></td>
                    <td><c:out value="${analyticsTransaction.status}" /></td>
                    <td><c:out value="${analyticsTransaction.when}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</body>
</html>
