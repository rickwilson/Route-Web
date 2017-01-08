<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Revenue Report</title>
    <jsp:include page="/WEB-INF/views/client/includes/headerIncludes.jsp" flush="false" />

    <script type="text/javascript">
        this.globalUrl = '/client/report/revenue';
        this.globalSpan = '${span}';
        this.globalSort = '${sort}';
        this.globalDir = '${dir}';
        this.globalSearch = '${search}';
    </script>
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
            <div class="page-title">
                <h3>Reports - <span class="semi-bold">Revenue</span></h3>
            </div>
            <div class="row-fluid">
                <div class="span12">
                    <div class="grid simple ">
                        <div class="grid-title">
                            <select name="spanInput" id="spanInput" onchange="changeSpan();">
                                <option value="m"<c:if test="${span == 'm'}"> SELECTED</c:if>>Last 30 Days</option>
                                <option value="h"<c:if test="${span == 'h'}"> SELECTED</c:if>>Last 6 Months</option>
                                <option value="y"<c:if test="${span == 'y'}"> SELECTED</c:if>>Last 12 Months</option>
                                <option value="16"<c:if test="${span == '16'}"> SELECTED</c:if>>2016</option>
                            </select>
                            <div class="tools hidden-xs">
                                <input name="searchInput" id="searchInput" type="text" placeholder="Search" value="${search}" onkeypress="changeSearch(event);">
                                <select name="searchTypeInput" id="searchTypeInput" onchange="changeSearchType();">
                                    <option value="1"<c:if test="${searchType == '1'}"> SELECTED</c:if>>In OrderId</option>
                                    <option value="2"<c:if test="${searchType == '2'}"> SELECTED</c:if>>In First</option>
                                    <option value="3"<c:if test="${searchType == '3'}"> SELECTED</c:if>>In Last</option>
                                    <option value="4"<c:if test="${searchType == '4'}"> SELECTED</c:if>>In Province</option>
                                    <option value="5"<c:if test="${searchType == '5'}"> SELECTED</c:if>>In Country</option>
                                </select>
                            </div>
                        </div>
                        <div class="grid-body">
                            <table class="table table-striped" >
                                <thead>
                                <tr>
                                    <th><a href="javascript:void(0);" onclick="changeSort(1);">Order Date</a></th>
                                    <th><a href="javascript:void(0);" onclick="changeSort(2);">Order ID</a></th>
                                    <th><a href="javascript:void(0);" onclick="changeSort(3);">Customer</a></th>
                                    <th><a href="javascript:void(0);" onclick="changeSort(4);">Province</a></th>
                                    <th><a href="javascript:void(0);" onclick="changeSort(5);">Country</a></th>
                                    <th><a href="javascript:void(0);" onclick="changeSort(6);">Order Value</a></th>
                                    <th><a href="javascript:void(0);" onclick="changeSort(7);">Route Share</a></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="clientReportItemObject" items="${clientReportItemObjects}">
                                    <tr>
                                        <td><c:out value="${clientReportItemObject.orderDate}" /></td>
                                        <td><a href="/client/transactionDetail?tId=${clientReportItemObject.transactionId}"><c:out value="${clientReportItemObject.orderId}" /></a></td>
                                        <td><c:out value="${clientReportItemObject.customerName}" /></td>
                                        <td><c:out value="${clientReportItemObject.customerProvence}" /></td>
                                        <td><c:out value="${clientReportItemObject.customerCountry}" /></td>
                                        <td><c:out value="${clientReportItemObject.orderValue}" /></td>
                                        <td><c:out value="${clientReportItemObject.revShareFormatted}" /></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div>
                                <br>
                                <c:set var="maxCount" value="${(pageNumber*50)+50}"/>
                                <c:if test="${maxCount > totalRows}">
                                    <c:set var="maxCount" value="${totalRows}"/>
                                </c:if>
                                Showing <span class="semi-bold">${(pageNumber*50)+1} to ${maxCount}</span> of <c:out value="${totalRows}" /> entries&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                                    <c:choose>
                                        <c:when test="${loop.index-1 != pageNumber}">
                                            <a href="javascript:void(0);" onclick="changePage(${loop.index-1});"> ${loop.index} </a>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="semi-bold"> ${loop.index} </span>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </div>
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
<script type="text/javascript">
    function changeSearch(event) {
        var lookingForEnter = event.which || event.keyCode;
        if(lookingForEnter == '13') { //Pressed the 'enter' key
            var form = document.createElement('form');
            form.setAttribute('method', 'POST');
            form.setAttribute('action', this.globalUrl);

            var requestSpan = document.createElement('input');
            requestSpan.setAttribute('name', 'span');
            requestSpan.setAttribute('type', 'hidden');
            requestSpan.setAttribute('value', this.globalSpan);
            form.appendChild(requestSpan);

            var requestSearch = document.createElement('input');
            requestSearch.setAttribute('name', 'search');
            requestSearch.setAttribute('type', 'hidden');
            requestSearch.setAttribute('value', document.getElementById("searchInput").value);
            form.appendChild(requestSearch);

            var requestSearchType = document.createElement('input');
            requestSearchType.setAttribute('name', 'searchType');
            requestSearchType.setAttribute('type', 'hidden');
            requestSearchType.setAttribute('value', document.getElementById("searchTypeInput").value);
            form.appendChild(requestSearchType);

            var security = document.createElement('input');
            security.setAttribute('name', '${_csrf.parameterName}');
            security.setAttribute('type', 'hidden');
            security.setAttribute('value', '${_csrf.token}');
            form.appendChild(security);
            document.body.appendChild(form);
            form.submit();
        }
    }
    function changeSearchType() {
        var form = document.createElement('form');
        form.setAttribute('method', 'POST');
        form.setAttribute('action', this.globalUrl);

        var requestSpan = document.createElement('input');
        requestSpan.setAttribute('name', 'span');
        requestSpan.setAttribute('type', 'hidden');
        requestSpan.setAttribute('value', this.globalSpan);
        form.appendChild(requestSpan);

        var requestSearch = document.createElement('input');
        requestSearch.setAttribute('name', 'search');
        requestSearch.setAttribute('type', 'hidden');
        requestSearch.setAttribute('value', document.getElementById("searchInput").value);
        form.appendChild(requestSearch);

        var requestSearchType = document.createElement('input');
        requestSearchType.setAttribute('name', 'searchType');
        requestSearchType.setAttribute('type', 'hidden');
        requestSearchType.setAttribute('value', document.getElementById("searchTypeInput").value);
        form.appendChild(requestSearchType);

        var security = document.createElement('input');
        security.setAttribute('name', '${_csrf.parameterName}');
        security.setAttribute('type', 'hidden');
        security.setAttribute('value', '${_csrf.token}');
        form.appendChild(security);
        document.body.appendChild(form);
        form.submit();
    }
    function changeSpan() {
        var form = document.createElement('form');
        form.setAttribute('method', 'POST');
        form.setAttribute('action', this.globalUrl);

        var requestSpan = document.createElement('input');
        requestSpan.setAttribute('name', 'span');
        requestSpan.setAttribute('type', 'hidden');
        var spanInputObject = document.getElementById("spanInput");
        requestSpan.setAttribute('value', spanInputObject.options[spanInputObject.selectedIndex].value);
        form.appendChild(requestSpan);

        var requestSearch = document.createElement('input');
        requestSearch.setAttribute('name', 'search');
        requestSearch.setAttribute('type', 'hidden');
        requestSearch.setAttribute('value', this.globalSearch);
        form.appendChild(requestSearch);

        var requestSearchType = document.createElement('input');
        requestSearchType.setAttribute('name', 'searchType');
        requestSearchType.setAttribute('type', 'hidden');
        requestSearchType.setAttribute('value', document.getElementById("searchTypeInput").value);
        form.appendChild(requestSearchType);

        var security = document.createElement('input');
        security.setAttribute('name', '${_csrf.parameterName}');
        security.setAttribute('type', 'hidden');
        security.setAttribute('value', '${_csrf.token}');
        form.appendChild(security);
        document.body.appendChild(form);
        form.submit();
    }
    function changePage(clickedPage) {
        var form = document.createElement('form');
        form.setAttribute('method', 'POST');
        form.setAttribute('action', this.globalUrl);

        var requestSpan = document.createElement('input');
        requestSpan.setAttribute('name', 'span');
        requestSpan.setAttribute('type', 'hidden');
        requestSpan.setAttribute('value', this.globalSpan);
        form.appendChild(requestSpan);

        var requestSearch = document.createElement('input');
        requestSearch.setAttribute('name', 'search');
        requestSearch.setAttribute('type', 'hidden');
        requestSearch.setAttribute('value', this.globalSearch);
        form.appendChild(requestSearch);

        var requestSearchType = document.createElement('input');
        requestSearchType.setAttribute('name', 'searchType');
        requestSearchType.setAttribute('type', 'hidden');
        requestSearchType.setAttribute('value', document.getElementById("searchTypeInput").value);
        form.appendChild(requestSearchType);

        var requestSort = document.createElement('input');
        requestSort.setAttribute('name', 'sort');
        requestSort.setAttribute('type', 'hidden');
        requestSort.setAttribute('value', this.globalSort);
        form.appendChild(requestSort);

        var requestDir = document.createElement('input');
        requestDir.setAttribute('name', 'dir');
        requestDir.setAttribute('type', 'hidden');
        requestDir.setAttribute('value', this.globalDir);
        form.appendChild(requestDir);

        var requestPage = document.createElement('input');
        requestPage.setAttribute('name', 'pageNumber');
        requestPage.setAttribute('type', 'hidden');
        requestPage.setAttribute('value', clickedPage);
        form.appendChild(requestPage);

        var security = document.createElement('input');
        security.setAttribute('name', '${_csrf.parameterName}');
        security.setAttribute('type', 'hidden');
        security.setAttribute('value', '${_csrf.token}');
        form.appendChild(security);
        document.body.appendChild(form);
        form.submit();
    }

    function changeSort(clickedColumn) {
        var form = document.createElement('form');
        form.setAttribute('method', 'POST');
        form.setAttribute('action', this.globalUrl);

        var requestSpan = document.createElement('input');
        requestSpan.setAttribute('name', 'span');
        requestSpan.setAttribute('type', 'hidden');
        requestSpan.setAttribute('value', this.globalSpan);
        form.appendChild(requestSpan);

        var requestSearch = document.createElement('input');
        requestSearch.setAttribute('name', 'search');
        requestSearch.setAttribute('type', 'hidden');
        requestSearch.setAttribute('value', this.globalSearch);
        form.appendChild(requestSearch);

        var requestSearchType = document.createElement('input');
        requestSearchType.setAttribute('name', 'searchType');
        requestSearchType.setAttribute('type', 'hidden');
        requestSearchType.setAttribute('value', document.getElementById("searchTypeInput").value);
        form.appendChild(requestSearchType);

        var requestSort = document.createElement('input');
        requestSort.setAttribute('name', 'sort');
        requestSort.setAttribute('type', 'hidden');
        requestSort.setAttribute('value', clickedColumn);
        form.appendChild(requestSort);

        var newDir = 0;
        if(this.globalSort == clickedColumn && this.globalDir == 0) {
            newDir = 1;
        }
        var requestDir = document.createElement('input');
        requestDir.setAttribute('name', 'dir');
        requestDir.setAttribute('type', 'hidden');
        requestDir.setAttribute('value', newDir);
        form.appendChild(requestDir);

        var security = document.createElement('input');
        security.setAttribute('name', '${_csrf.parameterName}');
        security.setAttribute('type', 'hidden');
        security.setAttribute('value', '${_csrf.token}');
        form.appendChild(security);
        document.body.appendChild(form);
        form.submit();
    }
</script>
</body>
</html>
