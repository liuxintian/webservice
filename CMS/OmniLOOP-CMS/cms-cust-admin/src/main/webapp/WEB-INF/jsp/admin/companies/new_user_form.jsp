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
                            <h3>Create new admin user</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel">
                                <div class="x_content">
									<div class="row">
                 <form enctype="multipart/form-data" id="add-executive-form" data-parsley-validate class="form-horizontal form-label-left">
                   		<div class="clearfix">
                     		<div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-name2">Name of the Executive <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="executive-name2" maxlength="80" value="" placeholder="Enter your name" required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-job2">Job Title <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="executive-job2" maxlength="80" value="" placeholder="ex. ceo" required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-email2">Email Address <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="executive-email2" maxlength="80" value="" placeholder="ex. xyz@abc.com" required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-phone2">Phone Number <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="executive-phone2" maxlength="80" value="" placeholder="Enter 10 digit your mobile no." required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-date2">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="executive-date2" value="" placeholder="DD/MM/YYYY" required="required" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-message2">
                                     Notes (20 chars min, 100 max) : <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea id="executive-message2" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-description2">
                                     Description (20 chars min, 400 max) : <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea id="executive-description2" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                             <div class="form-group upload-box-sec">
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-0b">Profile image:
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
										<input id="file-0b" class="file" type="file"  data-min-file-count="1">
                                  </div>
                              </div>
                     	</div>
                    <div>
	                     <button type="submit" class="btn btn-primary">Save changes</button>
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
	<%@ include file="/WEB-INF/jsp/common/common_js_scripts.jspf"%>		
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
	<script type="text/javascript">
        $(document).ready(function () {
            $('#executive-date, #executive-date2').daterangepicker({
                singleDatePicker: true,
                locale: {
				  format: DATE_OUT_FORMAT
				},
                calender_style: "picker_4"
            }, function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });
            
            $.listen('parsley:field:validate', function () {
                validateFront();
            });
            $('#executive-edit-form .btn[type=submit]').on('click', function () {
                $('#executive-edit-form').parsley().validate();
                validateFront();
            });
            var validateFront = function () {
                if (true === $('#executive-edit-form').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            
           
            
        });
        

    </script>		
</body>
</html>