<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Route</title>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <link rel="shortcut icon" href="/images/register/route-tiny-icon.jpg" />
    <link rel="icon" type="image/png" href="/images/register/route-tiny-icon.jpg">


    <!-- BEGIN PLUGIN CSS -->
    <link href="/css/register/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen" />
    <link href="/css/register/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/register/bootstrap-theme.min.css" rel="stylesheet" type="text/css" />
    <%--<link href="/css/register/font-awesome-4.6.3/css/font-awesome.min.css" rel="stylesheet" type="text/css" />--%>
    <link href="/css/register/animate.min.css" rel="stylesheet" type="text/css" />
    <link href="/css/register/jquery.scrollbar.css" rel="stylesheet" type="text/css" />
    <link href="/css/register/select2.css" rel="stylesheet" type="text/css" media="screen"/>

    <!-- FOR CHARTS -->
    <link rel="stylesheet" href="/css/register/rickshaw.css" type="text/css" media="screen">
    <link rel="stylesheet" href="/css/register/morris.css" type="text/css" media="screen">
    <!-- END CHARTS -->

    <!-- END PLUGIN CSS -->
    <!-- BEGIN CORE CSS FRAMEWORK -->
    <link href="/css/register/webarch.css" rel="stylesheet" type="text/css" />
    <!-- END CORE CSS FRAMEWORK -->

    <link href="/css/register/custom.css" rel="stylesheet" type="text/css" />

    <style>
        .background-img {
            width: 100%;
            background-color: #565656;
            background-size: cover;
            display: block;
            height: 100%;
            left: 0;
            position: fixed;
            right: 0;
            z-index: 1;
        }
    </style>
</head>
<body class="error-body no-top">
<%--<div style="background-color: black"></div>--%>
<div class="background-img"></div>
<div class="modal" id="contact-modal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
        <%--<div class="modal-content" style="border: solid; border-color: white; border-width: 1px;">--%>
            <form:form id="newRegistrationForm" commandName="registerForm" method="POST" action="/register/client/save">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="modal-header" style="background-color:#000000;">
                        <c:if test="${!empty specialAlert}">
                    <div class="alert alert-danger text-center" style="background-color: black">
                        <strong>Limited Time Special!</strong>
                        <br>
                        Sign up by Jan 1 and lock into our special $1.49 price <strong>forever!</strong>
                    </div>
                        </c:if>
                    <%--<h4 class="modal-title">Welcome to--%>
                    <h4 class="modal-title">
                        <img src="/images/register/route-logo-white-on-black-200by50.jpg" class="logo" alt="Route Logo" data-src="/images/register/route-logo-white-on-black-200by50.jpg" data-src-retina="/images/register/route-logo-white-on-black-400by100.jpg"/>
                    </h4>
                </div>
                <div class="modal-body">
                    <div class="row" style="background-color:#FFF;">
                        <h2 class="center-text">Thank you for your interest in Route.</h2>
                        <%--<h4 class="center-text">Our team is excited to show you how we can help protect your customers shipments and earn additional revenue--%>
                            <%--<strong>instantly</strong>.</h4>--%>
                    </div>
                    <br>
                    <div class="row" id="error"><p class="col-md-12 text-danger">
                    </p></div>
                    <br>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <form:label for="companyName" path="companyName">Company</form:label>
                            <form:input path="companyName" class="form-control" id="companyName" name="companyName" value="" placeholder="Enter your company" required="required"/>
                            <form:errors path="companyName" class="control-label" style="color: red;" />
                        </div>
                        <div class="form-group col-md-6">
                            <form:label for="transactions" path="transactions">Transactions (per day)</form:label>
                            <form:select path="transactions" class="form-control select2" id="transactions" name="transactions" items="${transactionMap}" required="required"/>
                            <form:errors path="transactions" class="control-label" style="color: red;" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <form:label for="first" path="first">First Name</form:label>
                            <form:input path="first" class="form-control" id="first" name="first" value="" placeholder="First Name" required="required"/>
                            <form:errors path="first" class="control-label" style="color: red;" />
                        </div>
                        <div class="form-group col-md-6">
                            <form:label for="last" path="last">Last Name</form:label>
                            <form:input path="last" class="form-control" id="last" name="last" value="" placeholder="Last Name" required="required"/>
                            <form:errors path="last" class="control-label" style="color: red;" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <form:label for="email" path="email">Email</form:label>
                            <form:input path="email" class="form-control" id="email" name="email" value="" required="required"/>
                            <form:errors path="email" class="control-label bfh-phone" style="color: red;" />
                        </div>
                        <div class="form-group col-md-6">
                            <form:label for="phone" path="phone">Phone Number</form:label>
                            <form:input path="phone" class="form-control" id="phone" name="phone" value="" placeholder="000-000-0000" required="required"/>
                            <form:errors path="phone" class="control-label" style="color: red;" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <form:label for="password" path="password">Password</form:label>
                            <form:password path="password" class="form-control" id="password" name="password" value="" placeholder="•••••" required="required"/>
                            <form:errors path="password" class="control-label" style="color: red;" />
                        </div>
                        <div class="form-group col-md-6">
                            <form:label for="passwordConfirm" path="passwordConfirm">Password (confirm)</form:label>
                            <form:password path="passwordConfirm" class="form-control" id="passwordConfirm" name="passwordConfirm" value="" placeholder="•••••" required="required"/>
                            <form:errors path="passwordConfirm" class="control-label" style="color: red;" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-6">
                            <form:label for="crm" path="crm">E-Commerce Platform</form:label>
                            <form:select path="crm" class="form-control select2" id="crm" name="crm" items="${crmMap}" required="required"/>
                            <form:errors path="crm" class="control-label" style="color: red;" />
                        </div>
                        <div class="form-group col-md-6">
                            <form:label for="url" path="url">Primary URL</form:label>
                            <form:input path="url" class="form-control" id="url" name="url" value="" placeholder="http://www.mysite.com" required="required"/>
                            <form:errors path="url" class="control-label" style="color: red;" />
                        </div>
                        <div class="form-group col-md-6" id="crm_" style="display:none;">
                            <form:label for="crmOther" path="crmOther">Other E-Commerce Platform</form:label>
                            <form:select path="crmOther" class="form-control select2" id="crmOther" name="crmOther" items="${crmOtherMap}"/>
                            <form:errors path="crmOther" class="control-label" style="color: red;" />
                        </div>
                    </div>
                    <div class="terms" style="display: none;">
                        <div data-toggle="collapse" id="accordion" class="panel panel-group">

                            <div class="panel-heading" style="background-color:#d1dade;">
                                <h4 class="panel-title">
                                    <a href="#collapseOne" data-parent="#accordion" data-toggle="collapse" class="collapsed terms-show">Terms & Conditions</a>
                                </h4>
                            </div>
                            <div class="panel-collapse collapse" id="collapseOne">

                                <div class="panel-body" style="height:200px;overflow:auto;">
                                        ${termsAndConditions}
                                </div>
                            </div>
                        </div>
                        <div style="padding:10px 15px;" title="Please read the terms & conditions">
                            <form:label for="terms" path="terms">
                            <form:checkbox path="terms" id="terms" name="terms" required="required" /> I Accept Terms & Conditions
                            </form:label>
                            <form:errors path="terms" class="control-label" style="color: red;" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" style="background-color:#1fb6c6;">REGISTER</button><div id="form_error" style="text-align: center;"></div>
                </div>
            </form:form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
    // email validation
    var validateEmail = function(elementValue) {
        var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
        return emailPattern.test(elementValue);
    }
    $('#email').keyup(function() {
        var value = $(this).val();
        var valid = validateEmail(value);
        if (!valid) {
            $(".er_email").remove();
            $(this).after("<span class=\"er_email reg_error\" style=\"color:red\">Please enter a valid email</span>");

        } else {
            $(".er_email").remove();
        }
    });
    // phone validation
    $('#phone').keyup(function (e) {
        var tel = '',
                val = this.value.replace(/[^\d]*/g,'').split(''),
                len = val.length;

        for (var i = 0; i < len; i++) {
            if (i == 2) {
                val[i] = val[i] + '-'
            } else if (i == 5) {
                val[i] = val[i] + '-'
            }
            tel = tel + val[i]
        }
        this.value = tel;

        if (validatePhone(this, 'us')) {
            $(".er_phone").remove();
        } else {
            $(".er_phone").remove();
            $(this).after("<span class=\"er_phone contact_error\" style=\"color:red\">Please enter your 10 digit phone number</span>");

        }
    });

    function validatePhone(input, type) {
        var filter, a = input.value;
        var filters = {
            us: /^((\(\d{3}\)\s)|(\d{3}-))(\d{3})-?(\d{4})$/,
            us2: /^(1?(?: |\-|\.)?(?:\(\d{3}\)|\d{3})(?: |\-|\.)?\d{3}(?: |\-|\.)?\d{4})$/
        };
        switch (type) {
            case 'us':
                filter = filters.us;
                break;
            default:
                filter = filters.us;
        }
        if (filter.test(a)) {
            return true;
        } else {
            return false;
        }
    }
    // password guidelines
    $('#password').on('input',function () {
        if(!(this.value).match(new RegExp("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"))) {
            $(".er_password").remove();
            $(this).after("<span class=\"er_password contact_error\" style=\"color:red\">Password must be:<br/> - more than 8 characters<br/> - at least 1 number<br/> - at least 1 upper case character</span>");
        }
        else{
            $(".er_password").remove();
        }
    });

    $('#newRegistrationForm').on('submit',function (e) {
        e.preventDefault();
        if($('.contact_error').length){
            $('#form_error').html('empty');
            $('#form_error').html("<span style=\"color:red\">Please fix all errors to continue</span>");
        }
        else {
            $(this).submit();
        }
    });
</script>
<!-- BEGIN CORE JS FRAMEWORK-->
<script src="/js/register/pace.min.js" type="text/javascript"></script>
<!-- BEGIN JS DEPENDECENCIES-->
<script src="https://use.fontawesome.com/9eb859ba03.js"></script>
<script src="/js/register/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="/js/register/bootstrap.min.js" type="text/javascript"></script>
<script src="/js/register/jqueryblockui.min.js" type="text/javascript"></script>
<script src="/js/register/jquery.unveil.min.js" type="text/javascript"></script>
<script src="/js/register/jquery.scrollbar.min.js" type="text/javascript"></script>
<script src="/js/register/jquery.animateNumbers.js" type="text/javascript"></script>
<script src="/js/register/jquery.validate.min.js" type="text/javascript"></script>
<script src="/js/register/select2.min.js" type="text/javascript"></script>

<script src="/js/register/jquery.inputmask.min.js" type="text/javascript"></script>
<!-- END CORE JS DEPENDECENCIES-->
<!-- BEGIN DATE PICKER -->
<script src="/js/register/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- END DATE PICKER -->

<!-- BEGIN CORE TEMPLATE JS -->
<script src="/js/register/webarch.js" type="text/javascript"></script>
<script src="/js/register/custom_validations.js" type="text/javascript"></script>
<!--<script src="https://app.myecoprotect.com/admin/assets/js/charts.js" type="text/javascript"></script>-->
<!-- END CORE TEMPLATE JS -->

<!-- BEGIN CHART PAGE LEVEL JS -->
<script src="/js/register/raphael-min.js"></script>
<script src="/js/register/d3.v2.js"></script>
<script src="/js/register/rickshaw.min.js"></script>
<!--<script src="https://app.myecoprotect.com/admin/assets/plugins/jquery-morris-chart/js/morris.min.js"></script>-->
<!--<script src="https://app.myecoprotect.com/admin/assets/plugins/jquery-easy-pie-chart/js/jquery.easypiechart.min.js"></script>-->
<script src="/js/register/jquery.flot.js"></script>
<script src="/js/register/jquery.flot.time.min.js"></script>
<script src="/js/register/jquery.flot.selection.min.js"></script>
<script src="/js/register/jquery.flot.animator.min.js"></script>
<script src="/js/register/jquery.flot.orderBars.js"></script>
<script src="/js/register/jquery-sparkline.js"></script>
<script src="/js/register/jquery.easypiechart.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->

<script>
    $('#contact-modal').modal('show');
    $('.wizard-steps .active').removeClass('active');
    $('.step-basic').addClass('active');
    $('#contact-modal').on('hide.bs.modal.prevent', closeModalEvent);
    function closeModalEvent(e) {
        e.preventDefault();
        if ($('#block').is(':checked')) {
            $('#contact-modal').off('hide.bs.modal.prevent');
            $("#contact-modal").modal('hide');
            return true;
        }
        return false;
    }
    $('.select2').select2();

    $('#passwordConfirm').keyup(function(){
        var confirm = $(this).val();
        var password = $('#password').val();
        if(password != confirm){
            $(this).addClass('error').closest('.form-group').addClass('error');
        }else{
            $(this).removeClass('error').closest('.form-group').removeClass('error');
        }
    })

    $('.terms-show').click(function(){
        $('#terms_conditions').attr('disabled', false);
    });


    showHideOtherCrms('');
    $('#crm').on("change", function (e){
        showHideOtherCrms($(this).val());
    });

    function showHideOtherCrms(value) {
        if(value == 'other'){
            console.log('show');
            $('#crm').attr('name', 'crm_');
            $('#crm_').show();
            $('#crm_ select').attr('name', 'crm').attr('required', true);
        } else {
            console.log('hide');
            $('#crm').attr('name', 'crm');
            $('#crm_').hide();
            $('#crm_ select').attr('name', 'crm_').attr('required', false);
        }
    }
</script>    </body>
<script type="text/javascript">
    var APP_URL = '"https:\/\/routeit.cloud"';
    console.log(APP_URL);
</script>
</html>
