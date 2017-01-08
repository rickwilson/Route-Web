<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transaction Detail</title>
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
                <h3><span class="semi-bold">Transaction</span> <c:out value="${clientTransactionDetailObject.transactionId}" /></h3>
            </div>
            <!-- END PAGE TITLE -->
            <!-- BEGIN PlACE PAGE CONTENT HERE -->
            <div class="row">
                <div class="col-md-6">
                    <div class="grid simple">
                        <div class="grid-body no-border">
                            <h4><span class="semi-bold">Order ID:</span> <c:out value="${clientTransactionDetailObject.orderId}" /></h4>
                            <h5><span class="semi-bold">Transaction Status:</span> <span class="label ${clientTransactionDetailObject.orderStateColor}"><c:out value="${clientTransactionDetailObject.orderState}" /></span></h5>
                            <h5><span class="semi-bold">Shipping Status:</span> <span class="label ${clientTransactionDetailObject.shippingTagColor}"><c:out value="${clientTransactionDetailObject.shippingTag}" /></span></h5>
                            <div class="row">
                                <div class="col-md-6">
                                    <span class="semi-bold">Order Date:</span> <c:out value="${clientTransactionDetailObject.orderDate}" /><br>
                                    <span class="semi-bold">Order Value:</span> <c:out value="${clientTransactionDetailObject.orderValue}" /><br>
                                    <span class="semi-bold">Name:</span> <c:out value="${clientTransactionDetailObject.name}" /><br>
                                    <span class="semi-bold">Phone:</span> <c:out value="${clientTransactionDetailObject.phone}" /><br>
                                    <span class="semi-bold">Email:</span> <c:out value="${clientTransactionDetailObject.email}" /><br>
                                    <span class="semi-bold">City:</span> <c:out value="${clientTransactionDetailObject.city}" /><br>
                                    <span class="semi-bold">Province:</span> <c:out value="${clientTransactionDetailObject.province}" /><br>
                                    <span class="semi-bold">Country:</span> <c:out value="${clientTransactionDetailObject.country}" /><br>
                                </div>
                                <div class="col-md-6">
                                    <span class="semi-bold">Tracking Number:</span> <c:out value="${clientTransactionDetailObject.trackingId}" /><br>
                                    <span class="semi-bold">Carrier:</span> <c:out value="${clientTransactionDetailObject.courierName}" /><br>
                                    <span class="semi-bold">Shipped Date:</span> <c:out value="${clientTransactionDetailObject.shipDate}" /><br>
                                    <span class="semi-bold">Delivered Date:</span> <c:out value="${clientTransactionDetailObject.deliveredDate}" /><br>
                                    <hr>
                                    <span class="semi-bold">APIKey:</span> <c:out value="${clientTransactionDetailObject.apiKey}" /><br>
                                    <span class="semi-bold">Insurance Term Price:</span> <c:out value="${clientTransactionDetailObject.insPriceTerm}" /><br>
                                    <span class="semi-bold">Insurance Base Price:</span> <c:out value="${clientTransactionDetailObject.insPriceBase}" /><br>
                                    <span class="semi-bold">Revenue Share:</span> <c:out value="${clientTransactionDetailObject.revShareBase}" /><br>
                                    <span class="semi-bold">Created:</span> <c:out value="${clientTransactionDetailObject.created}" /><br>
                                </div>
                            </div>
                            <hr>
                            <c:choose>
                                <c:when test="${empty clientTransactionDetailObject.products}">
                                    <tr>
                                        <td colspan="2">No Products Recorded</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="product" items="${clientTransactionDetailObject.products}">
                                        <tr>
                                            <div class="panel-group" id="accordion" data-toggle="collapse">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">
                                                            <a class="collapsed" data-toggle="collapse" data-parent="#accordion" href="#${product.productId}">
                                                                <c:out value="${product.productName}" />
                                                            </a>
                                                        </h4>
                                                    </div>
                                                    <div id="${product.productId}" class="panel-collapse collapse">
                                                        <div class="panel-body">
                                                            <span class="semi-bold">Product Name:</span> <c:out value="${product.productName}" /><br>
                                                            <span class="semi-bold">Product ID:</span> <c:out value="${product.productId}" /><br>
                                                            <span class="semi-bold">Quantity:</span> <c:out value="${product.quantity}" /><br>
                                                            <span class="semi-bold">Price:</span> <c:out value="${product.termTotalPrice}" /><br>
                                                            <span class="semi-bold">Description:</span> <c:out value="${product.description}" /><br>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active">
                            <a href="#shippingLog" role="tab" data-toggle="tab">Shipping History</a>
                        </li>
                        <li>
                            <a href="#transactionLog" role="tab" data-toggle="tab">Transaction History</a>
                        </li>
                    </ul>
                    <div class="tools"> <a href="javascript:;" class="collapse"></a> <a href="#grid-config" data-toggle="modal" class="config"></a> <a href="javascript:;" class="reload"></a> <a href="javascript:;" class="remove"></a> </div>
                    <div class="tab-content">
                        <div class="tab-pane active" id="shippingLog">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="grid-body">
                                        <table class="table table-striped" >
                                            <thead>
                                            <tr>
                                                <th>Date</th>
                                                <th>Status</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:choose>
                                                <c:when test="${empty clientTransactionDetailObject.shippingTrackingDetails}">
                                                    <tr>
                                                        <td colspan="2">No Shipping History Yet</td>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach var="shippingTrackingDetail" items="${clientTransactionDetailObject.shippingTrackingDetails}">
                                                        <tr>
                                                                <td><fmt:formatDate value="${shippingTrackingDetail.created}" pattern="dd MMM yyyy"/></td>
                                                                <td><c:out value="${shippingTrackingDetail.tag}" /></td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="transactionLog">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="grid-body">
                                        <table class="table table-striped" >
                                            <thead>
                                            <tr>
                                                <th>Date</th>
                                                <th>Status</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:choose>
                                                <c:when test="${empty clientTransactionDetailObject.transactionDetails}">
                                                    <tr>
                                                        <td colspan="2">No Transaction History Yet</td>
                                                    </tr>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach var="transactionDetail" items="${clientTransactionDetailObject.transactionDetails}">
                                                        <tr>
                                                            <td><fmt:formatDate value="${transactionDetail.created}" pattern="dd MMM yyyy"/></td>
                                                            <td><c:out value="${transactionDetail.shortNote}" /></td>
                                                        </tr>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
                <!-- END PLACE PAGE CONTENT HERE -->
        </div>
    </div>
    <!-- END PAGE CONTAINER -->
</div>
<!-- END CONTENT -->
<jsp:include page="/WEB-INF/views/client/includes/footerIncludes.jsp" flush="false" />
</body>
</html>
