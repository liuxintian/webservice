<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>
<link href="<%= baseURL %>/assets/css/datatables/tools/css/dataTables.tableTools.css" rel="stylesheet">


<link href="<%= baseURL %>/assets/css/summernote.css" rel="stylesheet">
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
                            <h3>Send email to share holders</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel pos-rel">
                                <div class="loading-sec hidden"></div>
                                <div class="x_content">
                                    <div class="row">
                                        <div class="text-right col-sm-offset-6 col-md-offset-6 col-md-6 col-sm-6 col-xs-6">
                                            <div id="mail-section" class="btn-group" data-toggle="buttons">
                                                <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="testMail">
                                                     <input class="form-control email-mode" data-api-attr="" type="radio" name="mail-send" value="" checked="" id="testMail"> &nbsp; Test &nbsp;
                                                </label>
                                                <label class="btn btn-default primary-active " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="mannualMail">
                                                     <input class="form-control email-mode" data-api-attr="" type="radio" name="mail-send" value="" id="mannualMail"> Live
                                                </label>
                                            </div>
                                        </div>
                                        <div class="col-md-12 col-sm-12 col-xs-12 m-t20">
                                            <form id="mass-emails" class="form-horizontal form-label-left">
                                                <div class="form-group">
                                                     <label class="control-label col-md-2 col-sm-2 col-xs-12" for="to-email">To: </label>
                                                     <div class="col-md-8 col-sm-8 col-xs-12">
                                                         <input data-api-attr="" type="text" id="to-email"  value="" data-parsley-required="true" placeholder="" class="form-control col-md-7 col-xs-12">
                                                     </div>
                                                </div>
                                                <div class="form-group">
                                                     <label class="control-label col-md-2 col-sm-2 col-xs-12" for="subject">Subject: </label>
                                                     <div class="col-md-8 col-sm-8 col-xs-12">
                                                         <input data-api-attr="" data-parsley-required="true" type="text" id="subject"  value="" placeholder="" class="form-control col-md-7 col-xs-12  email-subject">
                                                     </div>
                                                </div>
                                                <div class="form-group">
                                                     <label class="control-label col-md-2 col-sm-2 col-xs-12" for="message-email">Message: </label>
                                                     <div class="col-md-8 col-sm-8 col-xs-12">
                                                     <div class="" id="message-email">
                                                         <div id="mass_email_tiles"></div>
                                                     </div>
                                                         <!-- <input data-api-attr="" type="text" id="message-email"  value="" placeholder="" class="form-control col-md-7 col-xs-12"> -->
                                                     </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="col-sm-offset-2 col-md-offset-2 col-md-8 col-sm-8 col-sm-8 text-right">
                                                        <!--button type="button" class="btn btn-success js-save-email">Save</button-->
                                                        <button type="submit" class="btn btn-lg btn-primary  btn-block js-send-email">Send Email</button>
                                                    </div>
                                                </div>
                                            </form>
                                            
                                        </div>
                                            
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

    <div id="confirm-mass-emails" class="modal fade document-remove " tabindex="-1" role="dialog" aria-hidden="true">
         
         <div class="modal-dialog modal-md js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Confirmation for sending email</h4>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4>Are you sure you want to send the email now.</h4>
                            <div class="m-t20 text-center">
                                <button class="btn btn-default" data-dismiss="modal">No</button>
                                <button class="btn btn-danger js-fire-send-email">Yes</button>
                            </div>
                        </div>
                    </div>
             </div>
         </div>
     </div>

    <div id="success-mass-emails" class="modal fade document-remove " tabindex="-1" role="dialog" aria-hidden="true">


         <div class="modal-dialog modal-md js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Content submitted successfully, email confirmation</h4>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4>Content submitted successfully, emails will be sent shortly</h4>
                            <div class="m-t20 text-center">
                                <!--button class="btn btn-default" data-dismiss="modal">No</button>
                                <button class="btn btn-danger js-fire-send-email">Yes</button-->
                                <button id="email-sent-success" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
             </div>
         </div>


     </div>


    <%@ include file="/WEB-INF/jsp/common/common_js_scripts.jspf"%>     
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
    <script src="<%= baseURL %>/assets/js/summernote.min.js"></script>
    <script type="text/template" id="mass_email_tiles_template">     

                
        <h4 class="h4">Heading 4</h4>

        <strong>Getting started:</strong> Customize your template by clicking on the style editor tabs up above. Set your fonts, colors, and styles. After setting your styling is all done you can click here in this area, delete the text, and start adding your own awesome content!
        <br />
        <br />
        After you enter your content, highlight the text you want to style and select the options you set in the style editor in the "styles" drop down box. Want to <a href="http://www.mailchimp.com/kb/article/im-using-the-style-designer-and-i-cant-get-my-formatting-to-change" target="_blank">get rid of styling on a bit of text</a>, but having trouble doing it? Just use the "remove formatting" button to strip the text of any formatting and reset your style.

    </script>
    <script type="text/javascript">
        var injectTpl = function (id,data) {
          var deferred = $.Deferred();
          deferred.done(function(val,data){
            var template_cmp = _.template($(val+"_template").html());
            $(val).html(template_cmp({'characters':data}));
          });
          deferred.resolve(id,data);
          return deferred.promise();
        };
        var populateEmailContent = function(){
            injectTpl('#mass_email_tiles',{}).then(function () {});
        };

                
        $(document).ready(function() {
            $.listen('parsley:field:validate', function () {
                validateFront();
            });
            var validateFront = function () {
                if (true === $('#mass-emails').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            $('.js-save-email').on('click', function(){
                var formValidate = $('#mass-emails').parsley().validate();
                validateFront();

                if(formValidate == true){
                   // $('#add-executive-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                   // $.postJSON(API_ENDPOINT+'executives', dataString, postExecutivesCallback);
                }else{
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }
            });



            populateEmailContent();
            $('.email-mode').on('change', function(){
                if ($('#mannualMail').is(':checked')) {
                    $('#to-email').attr('disabled','disabled');
                    $('#to-email').removeAttr("data-parsley-required");
                }else{
                    $('#to-email').removeAttr('disabled','disabled');
                    $('#to-email').attr("data-parsley-required",'true');
                }
            });
            $('#message-email').summernote({
                height: 400        
            });
            
            $('#mass-emails').submit(function (e){
                e.preventDefault();
                //var formValidate = $('#add-document-form').parsley().validate();
                var formValidate = $('#mass-emails').parsley().validate();
                validateFront();
                if(!formValidate){
                    toastNotifications(UI_MESSAGES.form_validation_error);
                }else{
                    $('#confirm-mass-emails').modal('show');
                }
            });

            $('.js-fire-send-email').on('click', function(e){
                 e.preventDefault();
                //$(currentExecutiveBtn).closest('.well.profile_view').parent().remove();
                $('#confirm-mass-emails').find('.js-loading-bar').find('.loading-sec').removeClass('hidden');

                var dataString = {
                  "companyId": comInstId,
                  "content": $('#message-email').summernote('code'),
                  "subject" : $('.email-subject').val()
                  //"content": JSON.stringify($('#message-email').summernote('code'))
                };
                if(!$('#to-email').attr('disabled')){
                    var emailIds = $('#to-email').val().split(";");
                    var emailAccts = emailIds.map(function(obj,idx){ 
                       return { "name": "Test email account"+(idx+1), "email": $.trim(obj)};
                    });
                    dataString.mailIds = emailAccts;
                };
                console.log(dataString);
                $.postJSON(API_ENDPOINT+'local-users/send-mail', dataString, postSendEmailCallback);

            });


            var postSendEmailCallback = function (argument) {
                // body...
                $('#confirm-mass-emails').find('.js-loading-bar').find('.loading-sec').addClass('hidden');
                $('#confirm-mass-emails').modal('hide');
                $('#success-mass-emails').modal('show');
            };
            
            $('#email-sent-success').on('click', function(e){
                //$('#confirm-mass-emails').find('.js-loading-bar').find('.loading-sec').addClass('hidden');
                $('#success-mass-emails').modal('hide');
                window.location.reload(); 
            });


        });
    </script>
</body>
</html>
