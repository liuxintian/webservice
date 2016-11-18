<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>
<link href="<%= baseURL %>/assets/css/datatables/tools/css/dataTables.tableTools.css" rel="stylesheet">
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
                            <h3>List of Power users</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel pos-rel">
                                <div class="loading-sec hidden"></div>
                                <div class="x_content">

                                    <div class="row">
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-user-admin"> <i class="fa fa-plus"></i> Add Power User</button>
                                        </div>
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <a href="<%= baseURL %>/cust-admin/ui/poweruser_add" class="btn btn-default pull-right"> <i class="fa fa-file"></i> Import users</a>
                                        </div>
                                        <div class="clearfix"></div>

                                          <div class="x_content" id="remove-search-tools">
                                            <table id="example" class="table table-striped responsive-utilities jambo_table">
                                                <thead>
                                                    <tr class="headings">
                                                        <th>User Name</th>
                                                        <th>User Login/Email </th>
                                                        <th>Status</th>
                                                        <!--th>User Invited</th-->
                                                        <!--th>User Activated</th-->
                                                        <th class="text-center">Role</th>
                                                        <th>Valid Until</th>
                                                        <th class=" no-link last text-center"><span class="nobr">Action</span>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                  <tbody id="powerUser_list"></tbody>
                                                </table>
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
    <%@ include file="/WEB-INF/jsp/common/common_js_scripts.jspf"%>     
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
    
    <!-- Admin User edit model window  -->
    <div class="modal fade document-edit" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-update" data-dismiss="modal">
                        <span aria-hidden="true"><i class="fa fa-times"></i></span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Edit user information </h4>
                 </div>
                 <form  id="power-user-edit" class="form-validation-notify form-horizontal form-label-left">
                 <input data-api-attr="userId" type="text" value=""class="hidden">
                    <div class="modal-body">
                        <div class="clearfix">
                            <input data-api-attr="id" type="text" value=""  class="hidden" />
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-name2">User Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="userName" type="text" disabled="disabled" id="user-name2" maxlength="100" value="" placeholder="ex. john Doe" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="login-name2">Enter Login Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="loginName" disabled="disabled" type="text" id="login-name2" maxlength="100" data-parsley-type="email" value="" placeholder="ex. xyz@omt.com" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="tagRole2">Role 
                                 </label>
                                 <!-- <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="tagRole" type="text" id="tagRole2" maxlength="80" value="" placeholder="ex. HCL" required="required" class="form-control col-md-7 col-xs-12">
                                 </div> -->

                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <select id="tagRole2"  class="form-control col-md-7 col-xs-12 select2-hidden-accessible" multiple="" tabindex="-1" aria-hidden="true"></select>
                                     <input type="text"  data-api-attr="tagRole" class="hidden form-control" />
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-contact2">Contact No <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="userContact" type="text" id="user-contact2" maxlength="80" data-parsley-type="phone" value="" placeholder="(XXX) XXXX XXX" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-date">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="document-date" value="" placeholder="" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="gender2" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" type="radio" name="announcement-status2" value="20"  checked=""> &nbsp; Active &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" type="radio" name="announcement-status2" value="30"> Inactive
                                        </label>
                                    </div>
                                </div>
                            </div>

                            
                            
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-note">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="document-note"  class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-confirmation-status pull-left" data-toggle="confirmation" data-placement="top">Deactivate</button>   
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button data-attr="update-btn" type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>
                <div id="sucess-model-update" class="text-center hidden ">
                    <h4 class="mtb50">User updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 
                <div id="failed-model-update" class="text-center hidden ">
                    <h4 class="mtb50">Opps! something went wrong!</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update" data-dismiss="modal">Close</button>
                    </div>
                </div>

                <div id="sucess-model-status-update" class="text-center hidden ">
                    <h4 class="mtb50">User status updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>

             </div>
         </div>
     </div>
    <!-- Admin User edit model window end  -->
    <!-- New User model window  -->
    <div class="modal fade add-new-user-admin" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close close-model js-close-model" data-dismiss="modal"><span aria-hidden="true">
                         <i class="fa fa-times"></i>
                     </span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add New Power User</h4>
                 </div>
                 <form  id="power-user-add-form" class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                            
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-name">User Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="userName" type="text" id="user-name" maxlength="100" value="" placeholder="ex. john Doe" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="login-name">Enter Login Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="loginName" type="text" id="login-name" maxlength="100" value="" data-parsley-type="email" placeholder="john.doe@hotmail.com" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <!-- <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="tagRole">Role <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="tagRole" type="text" id="tagRole" maxlength="80" value="" placeholder="ex. HCL" required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div> -->
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="tagRole">Role 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <select id="tagRole"  class="form-control col-md-7 col-xs-12 select2-hidden-accessible" multiple="" tabindex="-1" aria-hidden="true"></select>
                                     <input type="text"  data-api-attr="tagRole" class="hidden form-control" />
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-contact">Contact No <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="userContact" type="text" id="user-contact" maxlength="80" data-parsley-type="phone" value="" placeholder="(XXX) XXXX XXX" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-date2">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="document-date2" value="" placeholder="" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12 new-date-add">
                                 </div>
                            </div>
                            <!-- <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="gender" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" type="radio" name="announcement-status" value="20"  checked=""> &nbsp; Active &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" type="radio" name="announcement-status" value="30"> Inactive
                                        </label>
                                    </div>
                                </div>
                            </div> -->

                            
                            
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-note2">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="document-note2"  class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-close-model" data-dismiss="modal">Close</button>
                         <button data-attr="submit-btn" type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
                <div id="sucess-model" class="text-center hidden ">
                    <h4 class="mtb50">Added Power User.</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 
                <div id="failed-model" class="text-center hidden ">
                    <h4 class="mtb50">Opps! something went wrong!</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>

             </div>
         </div>
     </div>
    <!-- New User  model window end  -->
    <!-- Document model remove end  -->
    <div class="modal fade document-remove" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-md">
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true"><i class="fa fa-times"></i></span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Power User remove</h4>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4>Are you sure you want to remove this Power User.</h4>
                            <div class="m-t20 text-center">
                                <button class="btn btn-default" data-dismiss="modal">No</button>
                                <button class="btn btn-danger js-remove-executive" data-dismiss="modal">Yes</button>
                            </div>
                        </div>
                    </div>
             </div>
         </div>
     </div>
    <!-- Document model remove end  -->
    <!-- Datatables --> 
        <script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script>

    <!-- ============================== User Admin  Date List api code ============================== -->
    <script type="text/template" id="powerUser_list_template">     
        <$$ _.each(characters, function(powerUserItems,index){ $$>
            
               <tr class="pointer">
                <td><$$= powerUserItems.userName $$></td>
                <td><$$= powerUserItems.loginName $$></td>
                <td>
                    <$$ if(powerUserItems.status == 20){ $$>
                        <span class="success-text">Active</span>
                    <$$ } else if(powerUserItems.status == 30){$$>
                        <span class="danger-text">Inactive</span>
                    <$$ } else {$$>
                        <span class="danger-text">Inactive</span>
                    <$$ }$$>
                </td>
                <!--td><$$= powerUserItems.userInvited $$></td-->
                <!--td><$$= powerUserItems.userActivated $$></td-->
                <td  class="text-center"><$$= powerUserItems.tagRole $$></td>
                <td><$$= moment(powerUserItems.validUntil).format(DATE_OUT_FORMAT) $$></td>
                <td class="btn-sec text-center"><button type="button" class="btn btn-primary btn-xs js-edit-document" data-toggle="modal" data-target=".document-edit"> 
                    <i class="fa fa-edit"></i> Edit</button> <input type="text" value="<$$= powerUserItems.id $$>" class="thisDocId hidden" />
                </td>
                <!--<td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-remove-sec" data-toggle="modal" data-target=".document-remove"> 
                    <i class="fa fa-remove"></i> Remove</button>
                </td> -->
            </tr>

        <$$ }); $$>
    </script>
    <script id ="main-list-render">

        var apiEnds = [] , apiRsps = {}, menuTable;
        var getComPowerUserItems = function(path) {
            // body...
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };

        var getPowerUserItems = function(path,id) {
            // body...
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: API_ENDPOINT+path+'/'+id
            });
        };

        var chainedStepTwo = function(inputResponse){
            apiRsps[_.last(apiEnds)] = inputResponse;
            var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
            return getPowerUserItems('local-users',compId);
        };

        var injectTpl = function (id,data) {
              var deferred = $.Deferred();
              deferred.done(function(val,data){
                var template_cmp = _.template($(val+"_template").html());
                $(val).html(template_cmp({'characters':data}));
              });

              deferred.resolve(id,data);
              return deferred.promise();
          };
          

          var populatePowerUserMenu = function(){
            $('.x_panel').find('.loading-sec').removeClass('hidden');
            getComPowerUserItems('local-users').then(function (adata) {
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                // body...

                injectTpl('#powerUser_list',adata).then(function () {
                    // Do something brilliant here!
                   //alert("Do something brilliant here!");
                    // here code data will be loaded than run this code 
                    $('.x_panel').find('.loading-sec').addClass('hidden');
                    var asInitVals = new Array();
                    
                    menuTable = $('#example').dataTable({
                        "oLanguage": {
                            "sSearch": "Search all columns:"
                        },
                        "aoColumnDefs": [
                            {
                                'bSortable': false,
                                'aTargets': [0]
                            } //disables sorting for column one
                        ],
                        'iDisplayLength': 12,
                        "sPaginationType": "full_numbers",
                        "dom": 'T<"clear">lfrtip',
                        "tableTools": {
                          //  "sSwfPath": "<?php echo base_url('assets2/js/Datatables/tools/swf/copy_csv_xls_pdf.swf'); ?>"
                        }
                    });
                    $("tfoot input").keyup(function () {
                        /* Filter on the column based on the index of this element's parent <th> */
                        menuTable.fnFilter(this.value, $("tfoot th").index($(this).parent()));
                    });
                    $("tfoot input").each(function (i) {
                        asInitVals[i] = this.value;
                    });
                    $("tfoot input").focus(function () {
                        if (this.className == "search_init") {
                            this.className = "";
                            this.value = "";
                        }
                    });
                    $("tfoot input").blur(function (i) {
                        if (this.value == "") {
                            this.className = "search_init";
                            this.value = asInitVals[$("tfoot input").index(this)];
                        }
                    });
                    
                    // here code data will be loaded 
                });
            }, function(reason) {
                       $('.x_panel').find('.loading-sec').addClass('hidden');
                        //console.log(reason); // Error!
                    });

          };

          var clearTpl = function(id){
            $( id ).empty();
            //$( id ).detach();
          }

          $(document).ready(populatePowerUserMenu);

    </script>
    <!-- ============================== Admin User List api code ============================== -->
    <script type="text/javascript" id = "modal-popup-render-add">

        $(document).ready(function () {
            var uniqueUsername = false;

            $('.js-close-model').on('click', function(){
                $(this).closest('.add-new-user-admin').find('#power-user-add-form input, #power-user-add-form textarea, #power-user-add-form select').val('');
                $(this).closest('.add-new-user-admin').find('.select2-selection__rendered li span.select2-selection__choice__remove').trigger('click');
                $('.select2-dropdown.select2-dropdown--below').remove();
            });

            var valueBlankError = 'Please enter a login name';
            var validateError = 'Please enter a valid login name';

            $("#tagRole").select2({
              tags: true,
              tokenSeparators: [',', ' ']
            });
            $("#tagRole + .select2, #tagRole2 + .select2").css({'width':'100%'});

            
            $('#power-user-add-form input[data-api-attr=loginName]').on('blur', function(){
                var thisEmail = $(this).val();
                var dataString = '';
                dataString = {"users":[
                        {"loginName":thisEmail}
                    ]}
                var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/; 
                if(thisEmail == ''){
                    $(this).addClass('parsley-error');
                    $(this).after('<div class="danger-text">' +valueBlankError+ '</div>');
                }else if(!emailReg.test(thisEmail)){
                    $(this).addClass('parsley-error');
                    $(this).after('<div class="danger-text">'+validateError+'</div>');
                    console.log('Email not validate')
                }else{
                    $(this).removeClass('parsley-error');
                    console.log('email validate');
                    $.putJSON(API_ENDPOINT+'local-users/login-names-inuse', dataString, putChckUserCallback);
                }
            });
            $('#power-user-add-form input[data-api-attr=loginName]').on('focus', function(){
                $(this).next('.danger-text').remove();
            });
            var putChckUserCallback = function(data){
                var userValid = data.users[0].inUse;
                console.log(userValid);
                if(userValid == false){
                    uniqueUsername = false;
                    console.log('Used to this user');
                }else{
                    console.log('Not used to this user');
                    $("#power-user-add-form input[data-api-attr=loginName]").addClass('parsley-error');
                    $("#power-user-add-form input[data-api-attr=loginName]").after('<div class="danger-text">Same login name exists in the system please used another one </div>');
                    uniqueUsername = true;
                }
            }

            $.listen('parsley:field:validate', function () {
                validateFront();
            });
            var validateFront = function () {
                
                if (true === $('#power-user-add-form').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            // ======================================== Form 1 here start
           // Form post code here  
           //$('#power-user-add-form input[data-api-attr=companyName]').val(comInstanceName);
           $('#power-user-add-form').submit(function (e) {
                    e.preventDefault();
                var formValidate = $('#power-user-add-form').parsley().validate();
                validateFront();
               
               if(uniqueUsername == true){
                    alert(UI_MESSAGES.error_unique_name);
                    return false;
               };
                var tagRoleVal = $('#tagRole').val();
                var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                $('#power-user-add-form input[data-api-attr=tagRole]').val(tagRoleVar);

               //var powerAdminAttrs = ['loginName','password', 'name','role'];
               var powerAdminAttrs = ['userName','loginName', 'companyName' ,'userContact','validUntil','notes','tagRole'];
               var dataString = {};
                
                _.each(powerAdminAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('#power-user-add-form input[name=announcement-status][data-api-attr='+key+']:checked').val();
                        dataString[key] = parseInt(dataString[key], 10);    
                    }else if((key == 'validUntil')  &&  moment($('#power-user-add-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                        dataString[key] =  moment($('#power-user-add-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                    }else if(key == 'companyName'){
                        dataString[key] = comInstanceName;
                    }else{
                        dataString[key] =  $('#power-user-add-form .form-control[data-api-attr='+key+']').val();    
                    }
               });

                 

               console.log('form 1'+dataString);

                if(formValidate == true){
                    dataString['status'] = 20;
                    console.log('success');
                    $('#power-user-add-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                        //$.postJSON(API_ENDPOINT+'local-users', dataString, postPowerUserCallback);
                        dataString['userEmail'] = dataString['loginName'];
                        $.postJSON(API_ENDPOINT+'local-users/mutli-users', {"users":[dataString]}, postPowerUserCallback);

                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });
          // Form post code here end 
            var postPowerUserCallback = function(data){
                if(data){
                    $('#power-user-add-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                }
                $('#power-user-add-form input[type=text], #power-user-add-form textarea').val('');
                $('#power-user-add-form').addClass('hidden');
                $('#power-user-add-form').next('#sucess-model').removeClass('hidden');
                $('.add-new-user-admin').find('.close-model.js-close-model').addClass('js-btn-verify');
                $('.add-new-user-admin').find('.close-model.js-close-model').removeClass('js-close-model');
                $('input[data-api-attr=validUntil].new-date-add').val(defaultDateVal);
                    datePlugin();
                //alert('Added new Admin User');
                $("select#tagRole").select2("val", "");
                console.log(data); // show response 
                
                if(!_.isEmpty(menuTable)){
                    //menuTable.destroy();
                    menuTable.fnDestroy();
                    menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();
                }
                clearTpl('#powerUser_list');
                populatePowerUserMenu();
            };


            $(document).on('click', '.js-btn-verify',  function(){
                $('#power-user-add-form').removeClass('hidden');
                $('#power-user-add-form').next('#sucess-model').addClass('hidden');
                $('.add-new-user-admin').find('.close-model.js-btn-verify').addClass('js-close-model');
                $('.add-new-user-admin').find('.close-model.js-btn-verify').removeClass('js-btn-verify');
            });

            // ======================================== Form 2 here start
            // Edit Admin User form post code here 
                $(document).on('click', '.js-edit-document', function(){
                    $('#tagRole2').empty();
                    var dataString = '';
                    var documentId = $(this).next('.thisDocId').val();
                    $('#power-user-edit').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                    $.getJSON(API_ENDPOINT+'local-users/'+documentId, dataString, getPowerUserCallback);
                });
                var getPowerUserCallback = function(data){
                    if(data){
                        $('#power-user-edit').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    }
                    console.log(data.userName);
                    $('#power-user-edit input[data-api-attr=userName]').val(data.userName);
                    $('#power-user-edit input[data-api-attr=loginName]').val(data.loginName);
                    $('#power-user-edit input[data-api-attr=userContact]').val(data.userContact);
                    $('#power-user-edit textarea[data-api-attr=notes]').val(data.notes);
                    $('#power-user-edit input[data-api-attr=tagRole]').val(data.tagRole);
                    $('#power-user-edit input[data-api-attr=id]').val(data.id);
                    $('#power-user-edit input[data-api-attr=tagRole]').val(data.tagRole);
                    // ==============
                    var tagValueRole = $('#power-user-edit input[data-api-attr=tagRole]').val();
                    var tagValueRole2 = tagValueRole.split(',');
                    console.log(tagValueRole2);
                    console.log(tagValueRole2.length);
                    
                    $("#tagRole2").select2({
                      tags: true,
                      tokenSeparators: [',', ' '],
                      data: tagValueRole2
                    });
                    $("#tagRole2").val(tagValueRole2);
                    $("#tagRole2").trigger("change");
                    $('#tagRole2 + span.select2-container').css({"width":"100%"});
                    var tagRoleLength = $('#power-user-edit select#tagRole2').val();
                      if(tagRoleLength.length == 1){
                        if(tagRoleLength == ''){
                            $('#power-user-edit li.select2-selection__choice').remove();
                            $('#tagRole2').empty();
                        }
                    }
                    // =============
                    if(data.validUntil){

                        $('#power-user-edit input[data-api-attr=validUntil]').val(
                            moment(data.validUntil).format(DATE_OUT_FORMAT)
                        );

                    }else{
                        $('#power-user-edit input[data-api-attr=validUntil]').val(
                            moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                        );
                    }
                    // =============
                    var menuStatusVal = data.status;
                    if(menuStatusVal == 20){
                        $('#power-user-edit input[name=announcement-status2][value=20]').prop('checked',true);
                        $('#power-user-edit input[name=announcement-status2][value=30]').prop('checked',false);
                        $('#power-user-edit input[name=announcement-status2]').closest('label').removeClass('active');
                        $('#power-user-edit input[name=announcement-status2][value=20]').closest('label').addClass('active');
                    }else if(menuStatusVal == 30){
                        $('#power-user-edit input[name=announcement-status2][value=30]').prop('checked',true);
                        $('#power-user-edit input[name=announcement-status2][value=20]').prop('checked',false);
                        $('#power-user-edit input[name=announcement-status2]').closest('label').removeClass('active');
                        $('#power-user-edit input[name=announcement-status2][value=30]').closest('label').addClass('active');
                    };
                    // =============
                    datePlugin();

                    $('#power-user-edit input[name=announcement-status2]').prop( "disabled", true);  
                    $('#power-user-edit input[name=announcement-status2]').addClass('disabled');
                    $('#power-user-edit input[name=announcement-status2]').closest('label').addClass('disabled');


                    $('#power-user-edit').removeClass('hidden');
                    $('#power-user-edit').closest('.modal-content').find('#sucess-model-status-update').addClass('hidden');
                    var statusUpdatePayload = _.extend(_.clone(data), {status: data.status == 30 ? 20 : (data.status == 20 ? 30 : data.status) });

                    $('.js-confirmation-status').removeClass('disabled').prop( "disabled", false); 
                    if(_.contains([20, 30], data.status)){
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'local-users/'+data.id,statusUpdatePayload,statusUpdateCallback);    
                    }else if(!data.status){
                        statusUpdatePayload['status'] = 20;
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'local-users/'+data.id,statusUpdatePayload,statusUpdateCallback);  
                    }else{
                        $('.js-confirmation-status').addClass('disabled').prop( "disabled", true);
                    }

                    if(data.status != 20){
                        $( "#power-user-edit [type=submit]" ).prop( "disabled", true).addClass('disabled');
                        $( ".js-confirmation-status" ).html('Activate');
                    }else{
                        $( "#power-user-edit [type=submit]" ).prop( "disabled", false).removeClass('disabled');
                        $( ".js-confirmation-status" ).html('Deactivate');
                    }

                }

                $('#power-user-edit').submit(function (e) {
                    e.preventDefault();
                    var formValidate = $('#power-user-edit').parsley().validate();
                    validateFront();
                    var docListId = $('#power-user-edit input[data-api-attr=id]').val();

                    var tagRoleVal = $('#tagRole2').val();
                    var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                    $('#power-user-edit input[data-api-attr=tagRole]').val(tagRoleVar);
                    //var powerAdminAttrs = ['loginName','password', 'name','role'];
                var powerAdminAttrs =['userName','loginName','companyName','userContact','validUntil','status','notes','tagRole'];
                   var dataString = {};

                       _.each(powerAdminAttrs, function(key){
                            if(key == 'status'){
                                dataString[key] =  $('#power-user-edit .form-control[data-api-attr='+key+']:checked').val();    
                            }else if(key == 'companyName'){
                                dataString[key] = comInstanceName;
                            }else{
                                dataString[key] =  $('#power-user-edit .form-control[data-api-attr='+key+']').val();    
                            }
                       });

                         _.each(powerAdminAttrs, function(key){
                            if(key == 'status'){
                                dataString[key] =  $('#power-user-edit input[name=announcement-status2][data-api-attr='+key+']:checked').val(); 
                                dataString[key] = parseInt(dataString[key], 10);   
                            }else if((key == 'validUntil') &&  moment($('#power-user-edit .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                                dataString[key] =  moment($('#power-user-edit .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                            }else{
                                dataString[key] =  $('#power-user-edit .form-control[data-api-attr='+key+']').val();    
                            }
                       });

                    if(formValidate == true){
                        dataString = _.omit(dataString, 'status');
                        console.log('success');
                        $('#power-user-edit').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                            dataString['userEmail'] = dataString['loginName'];
                            $.putJSON(API_ENDPOINT+'local-users/'+docListId, dataString, putPowerUserCallback);
                    }else{
                      //  alert(UI_MESSAGES.form_validation_error);
                      toastNotifications(UI_MESSAGES.form_validation_error);
                    }
                });
                var putPowerUserCallback = function(data){
                    if(data){
                        $('#power-user-edit').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    };
                    //console.log('data ===' + data.id);
                    $('#power-user-edit').addClass('hidden');
                    $('#power-user-edit').next('#sucess-model-update').removeClass('hidden');


                    if(!_.isEmpty(menuTable)){
                    //menuTable.destroy();

                        menuTable.fnDestroy();
                        menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();
                    }
                    clearTpl('#powerUser_list');
                    populatePowerUserMenu();
                }

                $(document).on('click', '.js-btn-update', function(){
                    $('#power-user-edit').removeClass('hidden');
                    $('#power-user-edit').next('#sucess-model-update').addClass('hidden');
                });

            // Edit Admin User form post code here end 

            // ======================================== Form Remove code
            $('.js-remove-sec').on('click', function(){
                var currentExecutiveBtn = $(this);
                 /*   $('.js-remove-executive').on('click', function(){
                    $(currentExecutiveBtn).parents('.pointer').remove();
                });*/
            });
            // Form Remove Function here end 
        });

        
        $( document ).ajaxError(function() {
          //$( ".log" ).text( "Triggered ajaxError handler." );
        
            $('#power-user-add-form input, #power-user-add-form textarea').val('');
            $('#power-user-add-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');


            $('#power-user-add-form').addClass('hidden');
            
            if($('#failed-model').hasClass( "hidden")){
                $('#failed-model').removeClass('hidden');    
            }
            
            $('.add-new-user-admin').find('.close-model.js-close-model').addClass('js-btn-verify');            
            if($('.add-new-user-admin').find('.close-model.js-close-model').hasClass("js-close-model")){
                $('.add-new-user-admin').find('.close-model.js-close-model').removeClass('js-close-model');
            }
            
            $('#power-user-edit').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
            $('#power-user-edit').addClass('hidden');
            if($('#failed-model-update').hasClass("hidden")){
              $('#failed-model-update').removeClass('hidden');
            }

        });


        var statusUpdateCallback = function(data){

                if(data){
                    $('#power-user-edit').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                console.log('data update');
                $('#power-user-edit').addClass('hidden');
                var success_msg = data.status == 30 ? "User record deactivated successfully" :  "User record activated successfully";
                $('#power-user-edit').closest('.modal-content').find('#sucess-model-status-update .mtb50').html(success_msg);
                $('#power-user-edit').closest('.modal-content').find('#sucess-model-status-update').removeClass('hidden');

                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }
                clearTpl('#powerUser_list');
                populatePowerUserMenu();

        };
    </script>       
</body>
</html>