<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<sec:authorize access="isAuthenticated()">
    <% 
        response.sendRedirect("cust-admin/ui/company"); 
    %>
</sec:authorize>


<!DOCTYPE html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Omni Market Tide</title>

    <!-- Bootstrap core CSS -->

    <link href="<%= baseURL %>/assets/css/bootstrap.min.css" rel="stylesheet">

    <link href="<%= baseURL %>/assets/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%= baseURL %>/assets/css/animate.min.css" rel="stylesheet">
    <link href="<%= baseURL %>/assets/css/toastr.min.css" rel="stylesheet" />

    <!-- Custom styling plus plugins -->
    <link href="<%= baseURL %>/assets/css/custom.css" rel="stylesheet">
    <link href="<%= baseURL %>/assets/css/icheck/flat/green.css" rel="stylesheet">
    <link href="<%= baseURL %>/assets/css/pace.css" rel="stylesheet">


    <script src="<%= baseURL %>/assets/js/jquery.min.js"></script>
    <script src="<%= baseURL %>/assets/js/pace.js"></script>


    <!-- Simple javascript toast notifications -->
    <script src="<%= baseURL %>/assets/js/toastr.min.js"></script>

    <!--[if lt IE 9]>
        <script src="../assets/js/ie8-responsive-file-warning.js"></script>
        <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>

<body style="background:#F7F7F7;">
<div class="loading-sec hidden" style="background-position:center 200px;background-size: 43px;"></div>
    
    <div class="">
        <a class="hiddenanchor" id="toregister"></a>
        <a class="hiddenanchor" id="tologin"></a>


        <div id="wrapper">
            <div id="login" class="animate form">
                <section class="login_content">
                    <form method="post"  action="./login" class="form-validation-notify">
                        <h1>CMS Admin Login</h1>
                        <div>
                            <input type="text" name="loginName" class="form-control" placeholder="Username" data-parsley-type="email" data-parsley-required="true" />
                        </div>
                        <div>
                            <input name="password" type="password" class="form-control" placeholder="Password"  data-parsley-required="true" />
                        </div>
                        <div>
                            <input class="btn btn-default submit" value="Login" name="submit" type="submit"/>
                            <!-- <a class="btn btn-default submit" href="index.html">Log in</a> -->
                            <!-- <a class="reset_pass" href="#">Lost your password?</a> -->
                        </div>
                        <div class="clearfix"></div>
                        <div class="separator">

                            
                            <p class="change_link">Forget Password ?
                                <a href="#toregister" class="to_register"> Click here </a>
                            </p>
                            <div class="clearfix"></div>
                            <br />
                            <div>
                                <h1> OMT CMS Instance </h1>

                                <p>&copy; 2015 All Rights Reserved, OMT CMS Instance</p>
                            </div>
                        </div>
                    </form>
                    <!-- form -->
                </section>
                <!-- content -->
            </div>
            <!-- Forget Pasword -->
            <div id="register" class="animate form">
                <section class="login_content">
                    <form  id="forget-pwd" class="form-validation-notify">
                        <h1>Forget password</h1>

                        <div id="resend-pwd">
                            <input name="email" data-api-attr="loginName"  type="email" class="form-control pwd-email" data-parsley-required="true" placeholder="john.doe@hotmail.com" data-parsley-type="email" />
                            <div class="hidden" id="send-mail-pwd" style="margin-bottom:10px;"></div>
                        </div>
                        <div>
                            <!-- <a class="btn btn-default submit" href="index.html">Submit</a> -->
                            <button class="btn btn-default submit" data-attr="update-pwd"  name="submit" type="submit"> Send reset instructions </button>
                        </div>
                        <div class="clearfix"></div>
                        <div class="separator">

                            <p class="change_link">Already a member ?
                                <a href="#tologin" class="to_register"> Log in </a>
                            </p>
                            <div class="clearfix"></div>
                            <br />
                            <div>
                                <h1>OMT CMS Master</h1>

                                <p>&copy; 2015 All Rights Reserved. OMT CMS Master </p>
                            </div>
                        </div>
                    </form>
                </section>
            </div>
            <!-- Forget Password -->

            <!-- Reset Password -->
            <div id="reset-pwd" class="animate form hidden">
                <section class="login_content">
                    <form method="post" id="reset-pssword" class="form-validation-notify">
                        <h1>Reset password</h1>
                        <div class="forgetSecPwdError"><span class="text-danger">The link is not valid </div>
                        <div class="forgetSecPwd hidden">
                            <input name="" type="password" class="form-control" id="new-pwd" placeholder="Enter Password" data-parsley-required="true" />
                        </div>
                        <div class="forgetSecPwd hidden">
                            <input name="" type="password" class="form-control" id="con-new-pwd" placeholder="Enter repeat Password" data-parsley-required="true" />
                        </div>
                        <div class="hidden" id="reset-pwd-sec" style="margin-bottom:10px;"></div>
                        <div class="forgetSecPwd hidden">
                            <button class="btn btn-default submit" data-attr="reset-pwd"  name="" type="submit"> Update </button>
                        </div>
                        <div class="clearfix"></div>
                        <div class="separator">

                            <!-- <p class="change_link">Already a member ?
                                <a href="#tologin" class="to_register"> Log in </a>
                            </p> -->
                            <div class="clearfix"></div>
                            <br />
                            <div>
                                <h1>OMT CMS Master</h1>

                                <p>&copy; 2016 All Rights Reserved. OMT CMS Master </p>
                            </div>
                        </div>
                    </form>
                </section>
            </div>
            <!-- Reset Password -->
            <!-- <div id="register" class="animate form">
                <section class="login_content">
                    <form method="post">
                        <h1>Create Account</h1>

                        <div>
                            <input type="text" name="loginName" class="form-control" placeholder="Username" required="" />
                        </div>
                        <div>
                            <input name="email" type="email" class="form-control" placeholder="Email" required="" />
                        </div>
                        <div>
                            <input  name="password" type="password" class="form-control" placeholder="Password" required="" />
                        </div>
                        <div>
                            <input class="btn btn-default submit"  name="submit" type="submit"/>
                        </div>
                        <div class="clearfix"></div>
                        <div class="separator">

                            <p class="change_link">Already a member ?
                                <a href="#tologin" class="to_register"> Log in </a>
                            </p>
                            <div class="clearfix"></div>
                            <br />
                            <div>
                                <h1>OMT CMS Instance!</h1>

                                <p>&copy; 2015 All Rights Reserved, OMT CMS Instance. </p>
                            </div>
                        </div>
                    </form>
                </section>
            </div> -->
        </div>
    </div>
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
<script type="text/javascript">
    var getUrlParameter = function getUrlParameter(sParam) {
            var sPageURL = decodeURIComponent(window.location.search.substring(1)),
                sURLVariables = sPageURL.split('&'),
                sParameterName,
                i;
        
            for (i = 0; i < sURLVariables.length; i++) {
                sParameterName = sURLVariables[i].split('=');
                if (sParameterName[0] === sParam) {
                    return sParameterName[1] === undefined ? true : sParameterName[1];
                }
            }
    };
    $.putJSON2 = function(url, data, callback) {
        return jQuery.ajax({
            'type': 'PUT',
            'url': url,
            'contentType': 'application/json',
            'data': data ? JSON.stringify(data, null, ' ') : '',// $.toJSON(data),
            'dataType': 'json',
            'success': callback,
            'error': callback,
        });
    };
    $.putJSON = function(url, data, callback) {
        return jQuery.ajax({
            'type': 'PUT',
            'url': url,
            'contentType': 'application/json',
            'data': data ? JSON.stringify(data, null, ' ') : '',// $.toJSON(data),
            'dataType': 'json',
            'success': callback
        });
    };
    $(document).ready(function(){
        var webBaseUrl = '<%= baseURL %>';
        //var OPEN_API_ENDPOINT = webBaseUrl+'/master/oapi/';
        // === Form 1
        $('.to_register').on('click', function(){
            $('#resend-pwd input[data-api-attr=loginName]').val('');
        });
        var emailValid = false;

         //dataString:{loginName:feedAttrs}; 
        $('#forget-pwd').submit(function (e) {
            e.preventDefault();
           validate();
            var feedAttrs = $('#resend-pwd input[type=email]').val();
            var dataString = {loginName:feedAttrs};

           if(emailValid == true){
             // here api call reset pasword 
             $('#resend-pwd #send-mail-pwd').text('');
             $('.loading-sec').removeClass('hidden');
             $.putJSON2(OPEN_API_ENDPOINT+'managers/forgot-password', dataString, putResetPwdCallback);

           };
        });
        var putResetPwdCallback = function(data){
            $('.loading-sec').addClass('hidden');
            //alert('Respons Get');
             console.log(data);
             if(data && data.responseText == "operation-success"){
                $('#resend-pwd > input, .btn[data-attr=update-pwd]').addClass('hidden');
                $('#resend-pwd #send-mail-pwd').removeClass();
                $('#resend-pwd #send-mail-pwd').addClass('text-success');
                $('#resend-pwd #send-mail-pwd').text(UI_MESSAGES.success_msg_send);
             }else{
                $('#resend-pwd #send-mail-pwd').addClass('text-danger');
                $('#resend-pwd #send-mail-pwd').text(UI_MESSAGES.error_wrong_email_admin);
             }
        };
        function validate(){
          var email = $(".pwd-email").val();
          if (validateEmail(email)) {
            emailValid = true;
          } else {
            $('#resend-pwd #send-mail-pwd').removeClass();
            $('#resend-pwd #send-mail-pwd').addClass('text-danger');
            $('#resend-pwd #send-mail-pwd').text(UI_MESSAGES.error_wrong_email);
            $('#resend-pwd .pwd-email').focus();
            emailValid = false;
          }
          return false;
        }

         // === Form 2 =========== Password Reset function here start
        var recoveryId = getUrlParameter ('recovery');
        var emailTockenId = getUrlParameter ('emailTocken');
        if(emailTockenId){
            var emailTockenAttrs = emailTockenId;
            //var dataString = {fpToken:emailTockenAttrs};
            $.putJSON(OPEN_API_ENDPOINT+'managers/verify-fp-token', 
                {"fpToken": emailTockenAttrs}, function(data){
                    putMailTockenCallback(data);
            });
        }else{}
        var tockenEmailId = '',tockenUserId = '';
        var putMailTockenCallback = function(data){
           // alert('Email Tockan Received');
           // console.log(data);
            if(data && data.userId){
                $('#reset-pwd .forgetSecPwdError').addClass('hidden');
                $('#reset-pwd .forgetSecPwd').removeClass('hidden');
                tockenEmailId = data.loginName;
                tockenUserId = data.userId;
            };
        };
        if(recoveryId){
           // alert(emailTokenId);
            $('#wrapper #register').addClass('hidden');
            $('#wrapper #login').addClass('hidden');
            $('#wrapper #reset-pwd').removeClass('hidden');
        }else{

        };
        
        $('#reset-pssword').submit(function (e) {
            e.preventDefault();
           var newPwd = $('#new-pwd').val();
           var conNewPwd = $('#con-new-pwd').val();

           var loginName = tockenEmailId;
           var loginPwd = $('#con-new-pwd').val();
           var fmailTocken = emailTockenId;
           var dataString = {loginName:loginName, password:loginPwd, fpToken:fmailTocken};
           if(newPwd == ''){
                $('#reset-pssword #reset-pwd-sec').removeClass();
                $('#reset-pssword #reset-pwd-sec').addClass('text-danger');
                toastNotifications(UI_MESSAGES.error_pwd);
                $('#reset-pssword #new-pwd').focus();
           }else if(newPwd == conNewPwd){
               // alert('Your  Password Updated Please login');
                // Call api here for confirm pwd
                /*$('#wrapper #register').removeClass('hidden');
                $('#wrapper #login').removeClass('hidden');
                $('#wrapper #reset-pwd').addClass('hidden');*/
               // $.putJSON(OPEN_API_ENDPOINT+'managers/reset-password', dataString, putPwdTockenCallback);
               $('#reset-pssword #reset-pwd-sec').text('');
               $('.loading-sec').removeClass('hidden');
               $.putJSON2(OPEN_API_ENDPOINT+'managers/reset-password', 
                    {"fpToken": fmailTocken, "loginName":tockenEmailId,"userId": tockenUserId, "password":loginPwd}, function(data){
                        putPwdResetCallback(data);
                });


           }else{
                $('#reset-pssword #reset-pwd-sec').removeClass();
                $('#reset-pssword #reset-pwd-sec').addClass('text-danger');
                toastNotifications(UI_MESSAGES.error_pwd_match);
                $('#reset-pssword #new-pwd').focus();
           }
        });
        
         var putPwdResetCallback = function(data){
            $('.loading-sec').addClass('hidden');
            //alert('API TOCKEN SEND');
            if(data && data.userId){

                $('#reset-pssword #new-pwd, #reset-pssword #con-new-pwd, #reset-pssword .btn[data-attr=reset-pwd]').addClass('hidden');
                $('#reset-pssword #reset-pwd-sec').removeClass();
                $('#reset-pssword #reset-pwd-sec').addClass('text-success');
                $('#reset-pssword #reset-pwd-sec').html(UI_MESSAGES.success_msg);
                $('#reset-pssword>h1').text('Password updated');

            }else{

                $('#reset-pssword #reset-pwd-sec').removeClass();
                $('#reset-pssword #reset-pwd-sec').addClass('text-danger');
                $('#reset-pssword #reset-pwd-sec').text("Opps! please try again");
                $('#reset-pssword #new-pwd').focus();


            }

        };
        
        // === Form 2 =========== Password Reset function here end
    });

    function validateEmail(email) { 
        var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }


    if(getUrlParameter('loginError') === 'true'){
        toastNotifications('Wrong credentials, please try again');
    }
    // =====
</script>
</body>

</html>
