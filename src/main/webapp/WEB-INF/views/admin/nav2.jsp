<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/admin/">Dashboard</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/admin/viewAccounts">Accounts</a></li>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Reports<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="/admin/transactionsByAccount">Transactions By Account</a></li>
                        <li><a href="/analytics/weekly">Analytics (Week)</a></li>
                    </ul>
                </li>
                <c:if test="${isDeveloper}">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Developer<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/developer/healthLinks">Health URLs</a></li>
                            <li><a href="/developer/makeMD5">Make MD5</a></li>
                            <li><a href="/admin/tracking/">Track Shipping</a></li>
                            <li><a href="/admin/tracking/allCouriers">All Shipping Couriers</a></li>
                            <li><a href="/autoLeads/addUrl">Add URLs</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">LimeLight<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/viewAllLimeLightOrders">Orders</a></li>
                            <li><a href="/admin/viewAllLimeLightInitializers">Initializers</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Konnektive<span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/viewAllKonnektiveTransactions">Transactions</a></li>
                            <li><a href="/admin/viewAllKonnektiveOrders">Orders</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
            </ul>
        </div>
    </div>
</nav>