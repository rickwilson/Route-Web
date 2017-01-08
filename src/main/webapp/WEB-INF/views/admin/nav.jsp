<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="z-index: 100;"><ul>
    <li><a class="active" href="/admin/">Dashboard</a></li>
    <li class="dropdown">
        <a href="#" class="dropbtn">Account</a>
        <div class="dropdown-content">
            <a href="/admin/viewAccounts">View All Accounts</a>
        </div>
    </li>
    <li class="dropdown">
        <a href="#" class="dropbtn">Reports</a>
        <div class="dropdown-content">
            <a href="/analytics/weekly">Analytics (Week)</a>
        </div>
    </li>
    <c:if test="${isDeveloper}">
        <li class="dropdown">
            <a href="#" class="dropbtn">Shipping</a>
            <div class="dropdown-content">
                <a href="/admin/tracking/">Track</a>
                <a href="/admin/tracking/allCouriers">All Couriers</a>
            </div>
        </li>
        <li class="dropdown">
            <a href="#" class="dropbtn">Developers</a>
            <div class="dropdown-content">
                <a href="/developer/healthLinks">Health URLs</a>
                <a href="/developer/makeMD5">Make MD5</a>
            </div>
        </li>
        <li class="dropdown">
            <a href="#" class="dropbtn">Auto Leads</a>
            <div class="dropdown-content">
                <a href="/autoLeads/addUrl">Add URLs</a>
            </div>
        </li>
        <li class="dropdown">
            <a href="#" class="dropbtn">LimeLight</a>
            <div class="dropdown-content">
                <a href="#">Transactions</a>
                <a href="/admin/viewAllLimeLightOrders">Orders</a>
                <a href="/admin/viewAllLimeLightInitializers">Initializers</a>
            </div>
        </li>
        <li class="dropdown">
            <a href="#" class="dropbtn">Konnektive</a>
            <div class="dropdown-content">
                <a href="/admin/viewAllKonnektiveTransactions">Transactions</a>
                <a href="/admin/viewAllKonnektiveOrders">Orders</a>
                <a href="#">Initializers</a>
            </div>
        </li>
    </c:if>
    <li style="float:right"><a href="/logout">Logout</a></li>
</ul></div>
