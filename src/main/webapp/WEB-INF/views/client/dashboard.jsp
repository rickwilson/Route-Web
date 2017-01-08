<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard</title>
    <jsp:include page="/WEB-INF/views/client/includes/headerIncludes.jsp" flush="false" />
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.charts.load('current', {'packages':['corechart']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ['Month', 'Revenue', { role: 'style' }],
                ['${clientDashboardObject.month12Name}',  ${clientDashboardObject.month12Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month11Name}',  ${clientDashboardObject.month11Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month10Name}',  ${clientDashboardObject.month10Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month9Name}',  ${clientDashboardObject.month9Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month8Name}',  ${clientDashboardObject.month8Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month7Name}',  ${clientDashboardObject.month7Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month6Name}',  ${clientDashboardObject.month6Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month5Name}',  ${clientDashboardObject.month5Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month4Name}',  ${clientDashboardObject.month4Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month3Name}',  ${clientDashboardObject.month3Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month2Name}',  ${clientDashboardObject.month2Value}, 'color: #1fb6c6'],
                ['${clientDashboardObject.month1Name}',  ${clientDashboardObject.month1Value}, 'color: #1fb6c6']
            ]);

            var options = {
                legend: {position: "none"},
                vAxis: {minValue: 0}
            };

            var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
            chart.draw(data, options);
        }
    </script>
    <script type="text/javascript">
        google.charts.load('upcoming', {'packages':['geochart']});
        google.charts.setOnLoadCallback(drawRegionsMap);

        function drawRegionsMap() {

            var data = google.visualization.arrayToDataTable([
                ['Country', 'Shipments']
                <c:forEach var="shipmentPerCountry" items="${clientDashboardObject.shipmentsPerCountry}">
                    ,['${shipmentPerCountry.key}', ${shipmentPerCountry.value}]
                </c:forEach>
            ]);

            var options = {
                colorAxis: {colors: ['#bdeff5', '#11656e']}
            };

            var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

            chart.draw(data, options);
        }
    </script>
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
        <!-- BEGIN PORTLET -->
        <div class="clearfix"></div>
        <div class="content sm-gutter">
            <!-- BEGIN DASHBOARD TILES -->
            <c:if test="${not empty message}">
                <div class="alert alert-success">
                    <c:out value="${message}" />
                </div>
            </c:if>
            <div class="row">
                <div class="col-md-4 col-vlg-3 col-sm-6">
                    <div class="tiles blue m-b-10">
                        <div class="tiles-body">
                            <div class="heading" style="color: #292b2e"> REVENUE </div>
                            <div class="widget-stats">
                                <div class="wrapper transparent">
                                    <span class="item-title">Year</span> <span class="item-count semi-bold"><fmt:formatNumber type="currency" value="${clientDashboardObject.revYearFormatted}" /></span>
                                </div>
                            </div>
                            <div class="widget-stats ">
                                <div class="wrapper transparent">
                                    <span class="item-title">Month</span> <span class="item-count semi-bold"><fmt:formatNumber type="currency" value="${clientDashboardObject.revMonthFormatted}" /></span>
                                </div>
                            </div>
                            <div class="widget-stats">
                                <div class="wrapper last">
                                    <span class="item-title">Day</span> <span class="item-count semi-bold"><fmt:formatNumber type="currency" value="${clientDashboardObject.revTodayFormatted}" /></span>
                                </div>
                            </div>
                            <div class="description">
                                <a href="/client/report/revenue?span=y"><button type="button" class="btn btn-white btn-xs btn-mini">Year Details</button></a>
                                <a href="/client/report/revenue"><button type="button" class="btn btn-white btn-xs btn-mini">Month Details</button></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-vlg-3 col-sm-6">
                    <div class="tiles blue m-b-10">
                        <div class="tiles-body">
                            <div class="heading" style="color: #292b2e"> TRANSACTIONS </div>
                            <div class="widget-stats">
                                <div class="wrapper transparent">
                                    <span class="item-title">Year</span> <span class="item-count animate-number semi-bold" data-value="${clientDashboardObject.tranYear}" data-animation-duration="700">0</span>
                                </div>
                            </div>
                            <div class="widget-stats ">
                                <div class="wrapper transparent">
                                    <span class="item-title">Month</span> <span class="item-count animate-number semi-bold" data-value="${clientDashboardObject.tranMonth}" data-animation-duration="700">0</span>
                                </div>
                            </div>
                            <div class="widget-stats">
                                <div class="wrapper last">
                                    <span class="item-title">Day</span> <span class="item-count animate-number semi-bold" data-value="${clientDashboardObject.tranToday}" data-animation-duration="700">0</span>
                                </div>
                            </div>
                            <div class="description">
                                <a href="/client/report/transaction?span=y"><button type="button" class="btn btn-white btn-xs btn-mini">Year Details</button></a>
                                <a href="/client/report/transaction"><button type="button" class="btn btn-white btn-xs btn-mini">Month Details</button></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-vlg-3 col-sm-6">
                    <div class="tiles blue m-b-10">
                        <div class="tiles-body">
                            <div class="heading" style="color: #292b2e"> AVG DELIVERY DAYS </div>
                            <div class="widget-stats">
                                <div class="wrapper transparent">
                                    <span class="item-title">Year</span> <span class="item-count animate-number semi-bold" data-value="${clientDashboardObject.averageDeliveryDaysYear}" data-animation-duration="700">0</span>
                                </div>
                            </div>
                            <div class="widget-stats ">
                                <div class="wrapper transparent">
                                    <span class="item-title">Month</span> <span class="item-count animate-number semi-bold" data-value="${clientDashboardObject.averageDeliveryDaysMonth}" data-animation-duration="700">0</span>
                                </div>
                            </div>
                            <div class="widget-stats">
                                <div class="wrapper last">
                                    <span class="item-title">Day</span> <span class="item-count animate-number semi-bold" data-value="${clientDashboardObject.averageDeliveryDaysToday}" data-animation-duration="700">0</span>
                                </div>
                            </div>
                            <div class="description">
                                <a href="/client/report/shipping?span=y"><button type="button" class="btn btn-white btn-xs btn-mini">Year Details</button></a>
                                <a href="/client/report/shipping"><button type="button" class="btn btn-white btn-xs btn-mini">Month Details</button></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 col-vlg-3 visible-xlg visible-sm col-sm-6">
                    <div class="tiles blue m-b-10">
                        <div class="tiles-body">
                            <div class="heading" style="color: #292b2e"> UPTAKE RATE </div>
                            <div class="widget-stats">
                                <div class="wrapper transparent">
                                    <span class="item-title">Year</span> <span class="item-count animate-number semi-bold" data-value="${clientDashboardObject.uptakeCountYear}" data-animation-duration="700">0</span>
                                </div>
                            </div>
                            <div class="widget-stats ">
                                <div class="wrapper transparent">
                                    <span class="item-title">Month</span> <span class="item-count animate-number semi-bold" data-value="${clientDashboardObject.uptakeCountMonth}" data-animation-duration="700">0</span>
                                </div>
                            </div>
                            <div class="widget-stats">
                                <div class="wrapper last">
                                    <span class="item-title">Day</span> <span class="item-count animate-number semi-bold" data-value="${clientDashboardObject.uptakeCountToday}" data-animation-duration="700">0</span>
                                </div>
                            </div>
                            <div class="description">
                                &nbsp;
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END DASHBOARD TILES -->
            <!-- Revenue Graph -->
            <div class="row">
                <div class="col-md-12">
                    <div class="grid simple">
                        <div class="grid-title no-border">
                            <h4><span class="semi-bold">Revenue</span></h4>
                        </div>
                        <div class="grid-body no-border">
                            <div id="chart_div" style="width: 100%; height: 150px;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END Revenue Graph -->
            <!-- Shipping Boxes -->
            <div class="row 2col">
                <div class="col-md-3 col-sm-6 m-b-10">
                    <div class="tiles" style="background-color: #d1dade;">
                        <div class="tiles-body">
                            <div class="tiles-title" style="color: #292b2e"> <c:out value="${clientDashboardObject.totalDelivered}" /> Shipments </div>
                            <div class="heading" style="color: #292b2e"> Delivered <span class="animate-number" data-value="${clientDashboardObject.percentDelivered}" data-animation-duration="700">0</span>% </div>
                            <div class="progress transparent progress-small no-radius">
                                <div class="progress-bar progress-bar-white animate-progress-bar" data-percentage="${clientDashboardObject.percentDelivered}%"></div>
                            </div>
                            <div class="description" style="color: #292b2e">
                                Delivered
                                <br><br>
                                <a href="/client/report/shipping"><button type="button" class="btn btn-white btn-xs btn-mini">Details</button></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 m-b-10">
                    <div class="tiles" style="background-color: #d1dade;">
                        <div class="tiles-body">
                            <div class="tiles-title" style="color: #292b2e"> <c:out value="${clientDashboardObject.totalInTransit}" /> Shipments </div>
                            <div class="heading" style="color: #292b2e"> In Transit <span class="animate-number" data-value="${clientDashboardObject.percentInTransit}" data-animation-duration="700">0</span>% </div>
                            <div class="progress transparent progress-small no-radius">
                                <div class="progress-bar progress-bar-white animate-progress-bar" data-percentage="${clientDashboardObject.percentInTransit}%" ></div>
                            </div>
                            <div class="description" style="color: #292b2e">
                                Out For Delivery | In Transit
                                <br><br>
                                <a href="/client/report/shipping"><button type="button" class="btn btn-white btn-xs btn-mini">Details</button></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 m-b-10">
                    <div class="tiles" style="background-color: #d1dade;">
                        <div class="tiles-body">
                            <div class="tiles-title" style="color: #292b2e"> <c:out value="${clientDashboardObject.totalReady}" /> Shipments </div>
                            <div class="heading" style="color: #292b2e"> Ready <span class="animate-number" data-value="${clientDashboardObject.percentReady}" data-animation-duration="700">0</span>% </div>
                            <div class="progress transparent progress-white progress-small no-radius">
                                <div class="progress-bar progress-bar-white animate-progress-bar" data-percentage="${clientDashboardObject.percentReady}%" ></div>
                            </div>
                            <div class="description" style="color: #292b2e">
                                Info Received | Pending
                                <br><br>
                                <a href="/client/report/shipping"><button type="button" class="btn btn-white btn-xs btn-mini">Details</button></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 col-sm-6 m-b-10">
                    <div class="tiles" style="background-color: #d1dade;">
                        <div class="tiles-body">
                            <div class="tiles-title" style="color: #292b2e"> <c:out value="${clientDashboardObject.totalFailed}" /> Shipments </div>
                            <div class="row-fluid">
                                <div class="heading" style="color: #292b2e"> Exception <span class="animate-number" data-value="${clientDashboardObject.percentFailed}" data-animation-duration="700">0</span>% </div>
                                <div class="progress transparent progress-white progress-small no-radius">
                                    <div class="progress-bar progress-bar-white animate-progress-bar" data-percentage="${clientDashboardObject.percentFailed}%"></div>
                                </div>
                                <div class="description" style="color: #292b2e">
                                    Exception | Failed | Expired
                                    <br><br>
                                    <a href="/client/report/shipping"><button type="button" class="btn btn-white btn-xs btn-mini">Details</button></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END Shipping Boxes -->
            <!-- Map -->
            <div class="row">
                <div class="col-md-12">
                    <div class="grid simple">
                        <div class="grid-title no-border">
                            <h4><span class="semi-bold">Shipping Tracking</span></h4>
                        </div>
                        <div class="grid-body no-border">
                            <div id="regions_div" style="width: 100%; height: 500px;"></div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- END Map -->
        </div>
    </div>
    <!-- END PAGE CONTAINER -->
</div>
<!-- END CONTENT -->
<jsp:include page="/WEB-INF/views/client/includes/footerIncludes.jsp" flush="false" />
</body>
</html>