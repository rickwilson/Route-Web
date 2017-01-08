<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Setup ACH</title>
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
                <div class="alert alert-error" id="failMsg" style="display: none;">
                    Unexpected exit from payment setup. Press the 'Setup Payment Link' button to try again.
                </div>
                <h3>Billing Setup <img src="https://d1pv9ulu41r3n5.cloudfront.net/img/stripe/powered_by_stripe_dark_solid.png" /></h3>
            </div>
            <div class="row-fluid">
                <p>Note: Connecting your bank account is done via your bank’s secure login page powered by Stripe Payments. No banking information is stored by Route.</p>
                <form:form id="finishAch" method="POST" action="/client/finishAch" class="form-no-horizontal-spacing">
                    <input type="hidden" id="acht" name="acht" value="${acht}">
                    <input type="hidden" id="mai" name="mai">
                    <input type="hidden" id="pt" name="pt">
                    <div class="form-group">
                        <label for="ptn" class="form-label">Alias</label>
                        <input id="ptn" name="ptn" class="form-control" required="required" type="text" value="${paymentTypeName}"/>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form:form>
                <br>
                <div class="span12">
                    <button id='linkButton' class="btn btn-primary btn-cons">Setup Payment With Stripe</button>
                    <script src="https://cdn.plaid.com/link/stable/link-initialize.js"></script>
                    <script>
                        var linkHandler = Plaid.create({
                            selectAccount: true,
                            env: '${plaid_link_env_stage}',
                            clientName: '${account_identifier_for_plaid}',
                            key: '${public_plaid_key}',
                            product: 'auth',
                            onLoad: function() {
                                // The Link module finished loading.
                            },
                            onSuccess: function(public_token, metadata) {
                                document.getElementById("pt").value = public_token;
                                document.getElementById("pt").value = public_token;
                                document.getElementById("mai").value = metadata.account_id;
                                document.getElementById("finishAch").submit();
                            },
                            onExit: function(err, metadata) {
                                document.getElementById('failMsg').style.display = 'block';
                                if (err != null) {
                                    alert("err");
                                }
                            },
                        });
                        document.getElementById('linkButton').onclick = function() {
                            linkHandler.open();
                        };
                    </script>
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
