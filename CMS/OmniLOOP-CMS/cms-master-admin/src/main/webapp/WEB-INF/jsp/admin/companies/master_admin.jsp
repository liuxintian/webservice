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
                            <h3>List of User Admin</h3>
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
                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-user-admin"> <i class="fa fa-plus"></i> Add New Admin</button>
                                        	<button type="button" class="btn btn-default refresh-admins-list" > <i class="fa fa-refresh"></i> Refresh List</button>
                                        </div>
                                        <div class="clearfix"></div>

                                          <div class="x_content" id="remove-search-tools">
                                            <table id="example" class="table table-striped responsive-utilities jambo_table">
                                                <thead>
                                                    <tr class="headings">
                                                        <th>User Name</th>
                                                        <th>User Login ID </th>
                                                        <th>Role</th>
                                                        <th>Status</th>
                                                        <th class=" no-link last text-center"><span class="nobr">Action</span>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                  <tbody id="managers_list"></tbody>
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
                     <button type="button" class="close js-btn-update" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">User information edit</h4>
                 </div>
                 <form  id="admin-user-edit-form" class="form-validation-notify form-horizontal form-label-left">
                 <input data-api-attr="userId" type="text" value=""class="hidden">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-name2">User Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="name" type="text" id="user-name2" maxlength="100" value="" placeholder="ex. john Doe" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="login-name2">Enter Login Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input disabled="disabled" data-api-attr="loginName" type="text" id="login-name2" maxlength="100" value="" data-parsley-required="true" placeholder="ex. johnDoe"  class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password2">Password <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="password" type="text" id="password2" readonly maxlength="80" value="" placeholder="john Doe" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>

                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12">Status:</label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 
                                     <div id="" class="btn-group" data-toggle="buttons">
                                         <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-active2">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-menu2" value="20" checked="" id="status-active2"> &nbsp; Active &nbsp;
                                         </label>
                                         <label class="btn btn-default primary-active " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-inactive2">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-menu2" value="30" id="status-inactive2"> Inavtive
                                         </label>
                                     </div>
                                 </div>
                             </div>

                            <!-- <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-role2">Role<span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="role" type="text" id="user-role2" maxlength="80" value="" placeholder="enter Your role" required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div> -->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default js-confirmation-status pull-left" data-toggle="confirmation" data-placement="top">Deactivate</button>
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button data-attr="update-btn" type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>
                <div id="sucess-model-update" class="text-center hidden ">
                    <h4 class="mtb50">User record updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 
                <div id="update-model-admin-status" class="text-center hidden ">
                    <h4 class="mtb50">User record status updated successfully</h4>
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
                     <button type="button" class="close js-btn-verify" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add New User</h4>
                 </div>
                 <form id="admin-user-add-form" class="form-validation-notify  form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-name">User Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="name" type="text" id="user-name" maxlength="100" value="" placeholder="ex. john Doe" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="login-name">Enter Login Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="loginName" type="text" id="login-name" maxlength="100" value="" data-parsley-required="true" placeholder="john.doe@hotmail.com" data-parsley-type="email" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="password">Password <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="password" type="text" id="password" maxlength="80" value="" placeholder="john Doe" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <!-- <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-role">Role<span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="role" type="text" id="user-role" maxlength="80" value="" placeholder="enter Your role" required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div> -->
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button data-attr="submit-btn" type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
                <div id="sucess-model" class="text-center hidden ">
                    <h4 class="mtb50">User record added successfully</h4>
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
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Admin User remove</h4>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4>Are you sure you want to remove this Admin User.</h4>
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
    <!-- Datatables --> 
        <script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script>

    <!-- ============================== User Admin  Date List api code ============================== -->
    <script type="text/template" id="managers_list_template">     
        <$$ _.each(characters, function(managerItems,index){ $$>
            
               <tr class="pointer">
                <td><$$= managerItems.name $$></td>
                <td><$$= managerItems.loginName $$></td>
                <td>
                    <$$ if(managerItems.status == 20){ $$>
                        <span class="success-text">Active</span>
                    <$$ } else if(managerItems.status == 30){$$>
                        <span class="danger-text">Inactive</span>
                    <$$ } else {$$>
                        <span class="danger-text">Inactive</span>
                    <$$ }$$>
                </td>
                <td><$$= managerItems.role $$></td>
                <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-document" data-toggle="modal" data-target=".document-edit"> <i class="fa fa-edit"></i> Edit</button> <input type="text" value="<$$= managerItems.userId $$>" class="thisDocId hidden" /></td>
              <!--  <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-remove-sec" data-toggle="modal" data-target=".document-remove"> <i class="fa fa-remove"></i> Remove</button></td> -->
            </tr>

        <$$ }); $$>
    </script>
    <script id ="main-list-render">

        var apiEnds = [] , apiRsps = {}, menuTable;
        var getComManagerItems = function(path) {
            // body...
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };

        var getManagerItems = function(path,id) {
            // body...
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: API_ENDPOINT+path+'/'+id
            });
        };

        var chainedStepTwo = function(inputResponse){
            apiRsps[_.last(apiEnds)] = inputResponse;
            var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
            return getManagerItems('managers',compId);
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
          

          var populateAdminUserMenu = function(){
            $('.x_panel').find('.loading-sec').removeClass('hidden');
            getComManagerItems('managers').then(function (adata) {
                // body...
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                injectTpl('#managers_list',adata).then(function () {
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

          $(document).ready(populateAdminUserMenu);

    </script>
    <!-- ============================== Admin User List api code ============================== -->
    <script type="text/javascript" id = "modal-popup-render-add">

        $(document).ready(function () {
            $('#document-date2, #document-date').daterangepicker({
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
            var validateFront = function () {
                
                if (true === $('#admin-user-add-form').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            // ======================================== Form 1 here start
           // Form post code here  
           $('#admin-user-add-form').submit(function (e){
                e.preventDefault();
                var formValidate = $('#admin-user-add-form').parsley().validate();
                validateFront();
               
               //var userAdminAttrs = ['loginName','password', 'name','role'];
               var userAdminAttrs = ['loginName','password', 'name'];
               var dataString = {};

               _.each(userAdminAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('#admin-user-add-form .form-control[data-api-attr='+key+']:checked').val();    
                    }else{
                        dataString[key] =  $('#admin-user-add-form .form-control[data-api-attr='+key+']').val();    
                    }
               });

               console.log('form 1'+dataString);

                if(formValidate == true){
                    console.log('success');
                    $('#admin-user-add-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                        dataString['status'] = 20;
                        $.postJSON(API_ENDPOINT+'managers', dataString, postCompanyDocuCallback);
                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });
          // Form post code here end 
            var postCompanyDocuCallback = function(data){
                if(data){
                    $('#admin-user-add-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                $('#admin-user-add-form input, #admin-user-add-form textarea').val('');
                $('#admin-user-add-form').addClass('hidden');
                $('#admin-user-add-form').next('#sucess-model').removeClass('hidden');
                //alert('Added new Admin User');

                console.log(data); // show response 
                
                if(!_.isEmpty(menuTable)){
                    //menuTable.destroy();

                    menuTable.fnDestroy();
                    menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();

                }
                clearTpl('#managers_list');
                populateAdminUserMenu();
            };
            $('.js-btn-verify').on('click', function(){
                $('#admin-user-add-form').removeClass('hidden');
                $('#admin-user-add-form').next('#sucess-model').addClass('hidden');
            });

            $('.refresh-admins-list').on('click', function(){
                if(!_.isEmpty(menuTable)){

                    menuTable.fnDestroy();
                    menuTable = null;

                }
                clearTpl('#managers_list');
                populateAdminUserMenu();
            });

            // ======================================== Form 2 here start
            // Edit Admin User form post code here 
                $(document).on('click', '.js-edit-document', function(){
                    var dataString = '';
                    var documentId = $(this).next('.thisDocId').val();
                    $('#admin-user-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                    $.getJSON(API_ENDPOINT+'managers/'+documentId, dataString, getCompanyDocuCallback);
                });
                var getCompanyDocuCallback = function(data){
                    console.log(data.name);
                    if(data){
                        $('#admin-user-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    }
                    $('#admin-user-edit-form input[data-api-attr=name]').val(data.name);
                    $('#admin-user-edit-form input[data-api-attr=loginName]').val(data.loginName);
                    $('#admin-user-edit-form input[data-api-attr=password]').val(data.password);
                    //$('#admin-user-edit-form input[data-api-attr=role]').val(data.role);
                    $('#admin-user-edit-form input[data-api-attr=userId]').val(data.userId);


                    var menuStatusVal = data.status;
                    if(menuStatusVal == 20){
                        $('#admin-user-edit-form input[name=status-menu2][value=20]').prop('checked',true);
                        $('#admin-user-edit-form input[name=status-menu2][value=30]').prop('checked',false);
                        $('#admin-user-edit-form input[name=status-menu2]').closest('label').removeClass('active');
                        $('#admin-user-edit-form input[name=status-menu2][value=20]').closest('label').addClass('active');
                    }else if(menuStatusVal == 30){
                        $('#admin-user-edit-form input[name=status-menu2][value=30]').prop('checked',true);
                        $('#admin-user-edit-form input[name=status-menu2][value=20]').prop('checked',false);
                        $('#admin-user-edit-form input[name=status-menu2]').closest('label').removeClass('active');
                        $('#admin-user-edit-form input[name=status-menu2][value=30]').closest('label').addClass('active');
                    };

                    //$('#admin-user-edit-form input[data-api-attr=id]').val(data.id);

                    $('#admin-user-edit-form input[name=status-menu2]').prop( "disabled", true);  
                    $('#admin-user-edit-form input[name=status-menu2]').addClass('disabled');
                    $('#admin-user-edit-form input[name=status-menu2]').closest('label').addClass('disabled');

                    $('#admin-user-edit-form').removeClass('hidden');
                    $('#admin-user-edit-form').closest('.modal-content').find('#update-model-admin-status').addClass('hidden');
                    var statusUpdatePayload = _.extend(_.clone(data), {status: data.status == 30 ? 20 : (data.status == 20 ? 30 : data.status) });
                    statusUpdatePayload = _.omit(statusUpdatePayload,['companyId','companyName','dateOfBirth','emailValid','fpToken','mailVerToken','password','phoneValid','role','title','type','userContact','userEmail','userInvited','userId']);
                    
                    $('.js-confirmation-status').removeClass('disabled').prop( "disabled", false);  
                    if(_.contains([20, 30], data.status)){
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'managers/'+data.userId,statusUpdatePayload,statusUpdateCallback);    
                    }else if(!data.status){
                        statusUpdatePayload['status'] = 20;
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'managers/'+data.id,statusUpdatePayload,statusUpdateCallback);  
                    }else{
                        $('.js-confirmation-status').addClass('disabled').prop( "disabled", true);
                    }

                    if(data.status != 20){
                        $( "#admin-user-edit-form [type=submit]" ).prop( "disabled", true).addClass('disabled');
                        $( ".js-confirmation-status" ).html('Activate');
                    }else{
                        $( "#admin-user-edit-form [type=submit]" ).prop( "disabled", false).removeClass('disabled');
                        $( ".js-confirmation-status" ).html('Deactivate');
                    }

                }
                $('#admin-user-edit-form').submit(function (e){
                    e.preventDefault();
                    var formValidate = $('#admin-user-edit-form').parsley().validate();
                    validateFront();
                    var docListId = $('#admin-user-edit-form input[data-api-attr=userId]').val();
                    //var userAdminAttrs = ['loginName','password', 'name','role'];
                var userAdminAttrs =['loginName','password', 'name'];
                   var dataString = {};

                       _.each(userAdminAttrs, function(key){
                            if(key == 'status'){
                                dataString[key] =  $('#admin-user-edit-form .form-control[data-api-attr='+key+']:checked').val();    
                            }else{
                                dataString[key] =  $('#admin-user-edit-form .form-control[data-api-attr='+key+']').val();    
                            }
                       });
                    if(formValidate == true){
                        dataString = _.omit(dataString, 'status');
                        console.log('success');
                        $('#admin-user-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                            $.putJSON(API_ENDPOINT+'managers/'+docListId, dataString, putUserAdminCallback);
                    }else{
                       // alert(UI_MESSAGES.form_validation_error);
                       toastNotifications(UI_MESSAGES.form_validation_error);
                    }
                });
                var putUserAdminCallback = function(data){
                    if(data){
                        $('#admin-user-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    };
                    //console.log('data ===' + data.id);
                    $('#admin-user-edit-form').addClass('hidden');
                    $('#admin-user-edit-form').next('#sucess-model-update').removeClass('hidden');

                    if(!_.isEmpty(menuTable)){

                        menuTable.fnDestroy();
                        menuTable = null;
                    }
                    clearTpl('#managers_list');
                    populateAdminUserMenu();
                }

                $(document).on('click', '.js-btn-update', function(){
                    $('#admin-user-edit-form').removeClass('hidden');
                    $('#admin-user-edit-form').next('#sucess-model-update').addClass('hidden');
                });

            // Edit Admin User form post code here end 

            // ======================================== Form Remove code
            $('.js-remove-sec').on('click', function(){
                var currentExecutiveBtn = $(this);
                    /*$('.js-remove-executive').on('click', function(){
                    $(currentExecutiveBtn).parents('.pointer').remove();
                });*/
            });
            // Form Remove Function here end 
        });

        // ==================== GET_API_WRONG_RESPONSE code ====================
         $(document).ajaxError(function(event, jqxhr, settings, thrownError) {
            var userDuplicateError = settings.url.endsWith('/api/managers');
            if(jqxhr.status == 400 && userDuplicateError == true){
                $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_duplicate_user_name);
            }else{
                $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_wrong_response);
            }

            if($('.loading-sec').hasClass("hidden")){
                $('.loading-sec').addClass('hidden');
                $('#admin-user-add-form input').val('');
                $('[data-dismiss=modal]').trigger('click');
                $('#failed-model-update').modal('show');
            }
        });
        // ==================== GET_API_WRONG_RESPONSE code end ====================

        var statusUpdateCallback = function(data){

                if(data){
                    $('#admin-user-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                console.log('data update');
                $('#admin-user-edit-form').addClass('hidden');
                var success_msg = data.status == 30 ? "User deactivated successfully" :  "User activated successfully";
                $('#admin-user-edit-form').closest('.modal-content').find('#update-model-admin-status .mtb50').html(success_msg);
                $('#admin-user-edit-form').closest('.modal-content').find('#update-model-admin-status').removeClass('hidden');

                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }

                clearTpl('#managers_list');
                populateAdminUserMenu();

        };

    </script>       
</body>
</html>
