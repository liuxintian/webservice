<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>

</head>
<body class="nav-md">
    <div class="container body">
        <div class="main_container">
            <div class="col-md-3 left_col">
                <div class="left_col scroll-view">
					<%@ include file="/WEB-INF/jsp/common/header_left_nav.jspf"%>
                </div>
            </div>
					<%@ include file="/WEB-INF/jsp/common/header_top_nav.jspf"%>
					            <!-- page content -->
            <div class="right_col" role="main">

                                <div class="">
                    <div class="page-title">
                        <div class="title_left">
                            <h3>User Admin  edit</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel pos-rel">
                                <div class="loading-sec hidden"></div>
                                <div class="x_content">
									<div class="row">
                 <form id="admin-user-form" class="form-validation-notify form-horizontal form-label-left">
                   		<div class="clearfix">
                     		<div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="login-name">Login ID <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="loginName" type="text" id="login-name" maxlength="80" value="" data-parsley-required="true" disabled class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-name">User Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="name" type="text" id="user-name" maxlength="80" value="" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label for="password" class="control-label col-md-3">New Password <span class="required">*</span></label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input id="password" data-api-attr="password" type="password" value="" name="password" data-validate-length="6,8" class="form-control col-md-7 col-xs-12" data-parsley-required="true">
                                 </div>
                             </div>
                             <div class="form-group">
                                 <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Confirm new password  <span class="required">*</span></label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input id="password2" data-api-attr="confPassword" type="password" value="" name="password2" data-validate-linked="password" class="form-control col-md-7 col-xs-12" data-parsley-required="true">
                                 </div>
                             </div>
                     	</div>
                    <div class="text-center">
	                     <button type="submit" class="btn btn-primary pl50 pr50" data-attr="update-btn">Update</button>
                 	</div>
                </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
					
					<%@ include file="/WEB-INF/jsp/common/common_footer.jspf"%>
             </div>
            <!-- /page content -->
		        </div>

    </div>
	<div id="custom_notifications" class="custom-notifications dsp_none">
        <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
        </ul>
        <div class="clearfix"></div>
        <div id="notif-group" class="tabbed_notifications"></div>
    </div>
    <!-- new Document model window  -->
    <span class="hidden js-pwd-update" data-toggle="modal" data-target=".pwdUpdate-sucess"> </span>
    <div class="modal fade pwdUpdate-sucess" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg">
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Password Update</h4>
                 </div>
                <div class="modal-body">
                <h4 class="mtb50 text-center">Your password has been updated.</h4>
                 
                </div>
                <div class="modal-footer">
                     <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>
         </div>
    </div>
    <!-- new Document model window end  -->
    <!-- Feed Ajex Error Model -->
     <div class="modal fade ajex-error-modal" id="failed-model-update" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-md">
             <div class="modal-content">

                 <div class="modal-header no-border">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4 class="mtb50"><span id="ajex-wrong-msg">Opps! something went wrong</span></h4>
                            <div class="m-t20 text-right">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
             </div>
         </div>
     </div>
     <!-- Feed Ajex Error Model -->
	<%@ include file="/WEB-INF/jsp/common/common_js_scripts.jspf"%>		
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
	<script type="text/javascript">
        $(document).ready(function () {

            var dataString = '';
            $('.x_panel .loading-sec').removeClass('hidden');
            $.getJSON(API_ENDPOINT+'managers/'+CURRENT_ADMIN_USER_ID, function(data){
                console.log(data.loginName);
                if(data){
                    $('.x_panel .loading-sec').addClass('hidden');
                }
                $('#admin-user-form #login-name').val(data.loginName);
                $('#admin-user-form #user-name').val(data.name);
            });
            /*$('#valid-until').daterangepicker({
            	minDate: new Date(),
                singleDatePicker: true,
                calender_style: "picker_4"
            }, function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });*/
            
            $.listen('parsley:field:validate', function () {
                validateFront();
            });
            $('#admin-user-form').submit(function (e){
                e.preventDefault();
                validateFront();
                var formValidate = $('#admin-user-form').parsley().validate();
                if(formValidate == true){
                    var confPwd = confirmPassword();
                    if(confPwd == true){
                        var feedName = $('#admin-user-form #user-name').val();
                        var feedPwd = $('#admin-user-form #password2').val();
                        var feedLoginId = $('#admin-user-form #login-name').val();
                        var dataString = {loginName:feedLoginId,name:feedName, password:feedPwd};
                        $('.x_panel .loading-sec').removeClass('hidden');
                        $.putJSON(API_ENDPOINT+'managers/'+CURRENT_ADMIN_USER_ID, dataString, function(data){
                            if(data){
                                $('.x_panel .loading-sec').addClass('hidden');
                            }
                            $('.js-pwd-update').trigger( "click" );
                            $('#admin-user-form #password2').val('');
                            $('#admin-user-form #password').val('');
                        });
                    }else{

                    }
                }else{
                    //alert(UI_MESSAGES.form_validation_error);
                    toastNotifications(UI_MESSAGES.form_validation_error);
                }

            });
            
            
            var validateFront = function () {
            	
                if (true === $('#admin-user-form').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            function confirmPassword() {
                var pass1 = document.getElementById("password").value;
                var pass2 = document.getElementById("password2").value;
                var ok = true;
                if (pass1 != pass2) {
                    toastNotifications(UI_MESSAGES.error_pwd_match);
                    document.getElementById("password").style.borderColor = "#E34234";
                    document.getElementById("password2").style.borderColor = "#E34234";
                    ok = false;
                }
                else {
                  //  alert("Passwords Match!!!");
                	document.getElementById("password").style.borderColor = "#DDE2E8";
                    document.getElementById("password2").style.borderColor = "#DDE2E8";
                }
                return ok;
            };
           
            
        });

        // ==================== GET_API_WRONG_RESPONSE code ====================
         $(document).ajaxError(function() {
            $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_wrong_response);
          //  if($('.loading-sec').hasClass("hidden")){
                $('.loading-sec').addClass('hidden');
            //    $('#add-feed-list input, #add-feed-list textarea, #add-feed-list select').val('');
            //    $('[data-dismiss=modal]').trigger('click');
                $('#failed-model-update').modal('show');
           // }
        });
        // ==================== GET_API_WRONG_RESPONSE code end ====================
    </script>		
</body>
</html>
