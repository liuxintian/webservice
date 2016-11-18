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
                            <h3>List of menus</h3>
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
                                        <button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-menu-list"> <i class="fa fa-plus"></i>Add new menu</button>
                                    </div>
                                </div>
                                <div class="clearfix"></div>
                                <div class="row" id="remove-search-tools">
                                    <table id="example" class="table table-striped responsive-utilities jambo_table">
                                        <thead class="hidden">
                                            <tr class="headings">
                                                <th class="column-title">Title </th>
                                                <th class="column-title">Menu Name </th>
                                                <th class="column-title">Valid Until </th>
                                                <th class="column-title">Status </th>
                                                <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                                <th class="bulk-actions" colspan="7">
                                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                                </th>
                                            </tr>
                                        </thead>
                                        <thead>
                                            <tr class="headings">
                                                <th class="column-title">Menu Key </th>
                                                <th class="column-title">Menu Title </th>
                                                <th class="column-title">Valid Until </th>
                                                <th class="column-title">Status </th>
                                                <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                                <th class="bulk-actions" colspan="7">
                                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                                </th>
                                            </tr>
                                        </thead>
                                            <tbody id="menus_list"></tbody>
                                   </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                    <!-- <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel">
                                <div class="x_content">
                                    <div class="row">
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-menu-list"> <i class="fa fa-file"></i> New menu list</button>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="row" id="remove-search-tools">
                                    <table id="example" class="table table-striped responsive-utilities jambo_table">
                                        <thead class="hidden">
                                            <tr class="headings">
                                                <th class="column-title">Title </th>
                                                <th class="column-title">Menu Name </th>
                                                <th class="column-title">Valid Until </th>
                                                <th class="column-title">Status </th>
                                                <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                                <th class="bulk-actions" colspan="7">
                                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                                </th>
                                            </tr>
                                        </thead>
                                        <thead>
                                            <tr class="headings">
                                                <th class="column-title">Title </th>
                                                <th class="column-title">Menu Name </th>
                                                <th class="column-title">Valid Until </th>
                                                <th class="column-title">Status </th>
                                                <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                                <th class="bulk-actions" colspan="7">
                                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="even pointer">
                                                <td class=" ">User Edit</td>
                                                <td class=" ">User listing</td>
                                                <td class=" ">26/12/2015</td>
                                                <td class="success-text">Active</td>
                                                <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-list-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                             </tr>
                                            <tr class="odd pointer">
                                                <td class=" ">User Edit</td>
                                                <td class=" ">User listing</td>
                                                <td class=" ">20/12/2015</td>
                                                <td class="danger-text">Inactive</td>
                                                <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-list-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                            </tr>
                                        </tbody>
                                   </table>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> -->
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
    <div class="modal fade add-new-menu-list" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-verify" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add Menu List</h4>
                 </div>
                 <form id="add-menu-list" class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body" method="post">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-name">Menu Name <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="dataMenu" type="text" id="menu-name" maxlength="100" value="" placeholder="ex. title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>


                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-name">Menu Url <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="url" type="text" id="menu-name" maxlength="300" value="" placeholder="ex. title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>

                            <div class="form-group">                        
                                 <label  class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-desc">
                                     Menu Title (5 chars min, 40 max)  <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="desc" id="menu-desc" data-parsley-required="true" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="5" data-parsley-maxlength="40" data-parsley-minlength-message="Come on! You need to enter at least a 5 caracters long Label.." data-parsley-validation-threshold="5"></textarea>
                                 </div>
                             </div>

                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-valid-until">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="menu-valid-until" value="" placeholder="DD/MM/YYYY" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12 new-date-add">
                                 </div>
                            </div>
                            <!-- <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12">Status:</label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 
                                     <div id="" class="btn-group" data-toggle="buttons">
                                         <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-active">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-menu" value="20" checked="" id="status-active"> &nbsp; Active &nbsp;
                                         </label>
                                         <label class="btn btn-default primary-active " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-inactive">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-menu" value="30" id="status-inactive"> Inavtive
                                         </label>
                                     </div>
                                 </div>
                             </div> -->
                             <div class="form-group">                        
                                 <label  class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-note">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="menu-note" class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                            
                             
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button type="submit" data-attr="submit-btn" class="btn btn-primary m-b5">Submit</button>
                    </div>
                </form>
                <div id="sucess-model" class="text-center hidden ">
                    <h4 class="mtb50">Menu record added successfully.</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 

             </div>
         </div>
     </div>
    <!-- new Document model window end  -->
    <!-- edit Document model window  -->
    <div class="modal fade menu-list-edit" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-verify2" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Edit Menu List</h4>
                 </div>
                 <form id="edit-menu-list" class="form-validation-notify form-horizontal form-label-left">
                    <input data-api-attr="id" type="text" value="" class="hidden">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-name2">Menu key (unique) <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="dataMenu" type="text" id="menu-name2" maxlength="100" value="" placeholder="ex. title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>

                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="url">Menu Url <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="url" type="text" id="menu-name" maxlength="300" value="" placeholder="ex. title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>

                            <div class="form-group">                        
                                 <label  class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-desc2">
                                     Menu Title (5 chars min, 40 max)  <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="desc" id="menu-desc2" data-parsley-required="true" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="5" data-parsley-maxlength="40" data-parsley-minlength-message="Come on! You need to enter at least a 5 caracters long Label.." data-parsley-validation-threshold="5"></textarea>
                                 </div>
                             </div>
                             
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-valid-until2">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="menu-valid-until2" value="" placeholder="DD/MM/YYYY" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">
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
                             <div class="form-group">                        
                                 <label  class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-note2">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="menu-note2"  class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                            
                             
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default js-confirmation-status pull-left" data-toggle="confirmation" data-placement="top">Deactivate</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" data-attr="update-btn" class="btn btn-primary m-b5">Update</button>
                    </div>
                </form>
                <div id="update-model" class="text-center hidden ">
                    <h4 class="mtb50">Menu updated successfully.</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-verify2" data-dismiss="modal">Close</button>
                    </div>
                </div>


                <div id="update-model-menu-status" class="text-center hidden ">
                    <h4 class="mtb50">Menu record status updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>



             </div>
         </div>
     </div>
    <!-- edit Document model window end  -->
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
    <!-- Datatables --> 
    <script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
    <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script>

    <!--script>
        $(document).ready(function () {
            $('input.tableflat').iCheck({
                checkboxClass: 'icheckbox_flat-green',
                radioClass: 'iradio_flat-green'
            });
        });        
    </script-->   
    
    <!-- Menu  List api code -->
    <script type="text/template" id="menus_list_template">     


        <$$ _.each(characters, function(menuItems,index){ $$>

            <tr class="pointer">
                <td class=" "><$$= menuItems.dataMenu $$></td>
                <td class=" "><$$= menuItems.desc $$></td>
                <td class=" "><$$= moment(menuItems.validUntil).format(DATE_OUT_FORMAT) $$></td>
                <td>
                    <$$ if(menuItems.status == 20){ $$>
                        <span class="success-text">Active</span>
                    <$$ } else if(menuItems.status == 30){$$>
                        <span class="danger-text">Inactive</span>
                    <$$ } else {$$>
                        <span class="danger-text">Inactive</span>
                    <$$ }$$>
                </td>
                <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-list-edit"> <i class="fa fa-edit"></i> Edit <input type="text" value="<$$= menuItems.id $$>" class="hidden js-irmenu-id" /></button></td>
            </tr>    

        <$$ }); $$>

    </script>






    <script id = "main-list-render">

        var apiEnds = [] , apiRsps = {}, menuTable;
        var getIrMenuItems = function(path) {
            // body...
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };

        var getMenuItems = function(path,id) {
            // body...
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: API_ENDPOINT+path+'/'+id
            });
        };

        var chainedStepTwo = function(inputResponse){
            apiRsps[_.last(apiEnds)] = inputResponse;
            var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
            return getMenuItems('data-menus',compId);
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
          

          var populateIRMenu = function(){
            $('.x_panel').find('.loading-sec').removeClass('hidden');
            getIrMenuItems('data-menus').then(function (adata) {
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                // body...

                injectTpl('#menus_list',adata).then(function () {
                    $('.x_panel').find('.loading-sec').addClass('hidden');
                    // Do something brilliant here!
                    //alert("Do something brilliant here!");
                    // here code data will be loaded than run this code 
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

          $(document).ready(populateIRMenu);

    </script>
    <!-- Menu  List api code end -->



    <script type="text/javascript" id = "modal-popup-render-add">

        $(document).ready(function () {  
            
          
            
            $.listen('parsley:field:validate', function () {
                validateFront();
            });
            
            
            var validateFront = function () {
                
                if (true === $('#add-menu-list').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            
           //  ========================= form  1 here post 
           $('#add-menu-list').submit(function (e){
                e.preventDefault();
                var formValidate = $('#add-menu-list').parsley().validate();
                validateFront();
               
               //var menuAttrs = ['menuItemName','validUntil','status','notes','menuItemDesc'];
               var menuAttrs = ['dataMenu','notes','desc','validUntil','url'];
               var dataString = {};

               _.each(menuAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('#add-menu-list input[name=status-menu][data-api-attr='+key+']:checked').val();  
                        dataString[key] = parseInt(dataString[key], 10);  
                    }else if((key == 'validUntil')  &&  moment($('#add-menu-list .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                        dataString[key] =  moment($('#add-menu-list .form-control[data-api-attr='+key+']').val(),DATE_OUT_FORMAT).valueOf();
                    }else{
                        dataString[key] =  $('#add-menu-list .form-control[data-api-attr='+key+']').val();    
                    }
               });

               console.log(dataString);
                if(formValidate == true){
                    dataString['status'] = 20;
                    console.log('success');
                    $('#add-menu-list').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                   $.postJSON(API_ENDPOINT+'data-menus', dataString, postIRMenuCallback);
                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });
           /* Form post code here end */
            var postIRMenuCallback = function(data){
                if(data){
                    $('#add-menu-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                $('#add-menu-list input[type=text], #add-menu-list textarea').val('');
                $('#add-menu-list').addClass('hidden');
                $('#add-menu-list').next('#sucess-model').removeClass('hidden');
                $('input[data-api-attr=validUntil].new-date-add').val(defaultDateVal);
                datePlugin();
                //alert('Added new Menu'); 

                console.log(data); // show response 
                
                if(!_.isEmpty(menuTable)){
                    //menuTable.destroy();

                    menuTable.fnDestroy();
                    menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();

                }
                clearTpl('#menus_list');
                populateIRMenu();
            };


            $('.js-btn-verify').on('click', function(){
                $('#add-menu-list').removeClass('hidden');
                $('#add-menu-list').next('#sucess-model').addClass('hidden');
            });

            // ============== form 2 put here 
            // ============= Update Form here 
            $(document).on('click', '.js-edit-row' ,function(){
                row_sec = $(this).closest('tr');
                var irMenuId = $(this).find('.js-irmenu-id').val();
                var dataString = '';
                $('#edit-menu-list').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                $.getJSON(API_ENDPOINT+'data-menus/'+irMenuId, dataString, getIrMenuItemsCallback);
            });
            var getIrMenuItemsCallback = function(data){
                if(data){
                    $('#edit-menu-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                }
                console.log(data.docDesc);
                $('#edit-menu-list input[data-api-attr=dataMenu]').val(data.dataMenu);
                $('#edit-menu-list textarea[data-api-attr=desc]').val(data.desc);
                $('#edit-menu-list textarea[data-api-attr=notes]').val(data.notes);
                 $('#edit-menu-list input[data-api-attr=url]').val(data.url);
               // $('#edit-menu-list input[data-api-attr=status]').val(data.status);
               var menuStatusVal = data.status;
                if(menuStatusVal == 20){
                    $('#edit-menu-list input[name=status-menu2][value=20]').prop('checked',true);
                    $('#edit-menu-list input[name=status-menu2][value=30]').prop('checked',false);
                    $('#edit-menu-list input[name=status-menu2]').closest('label').removeClass('active');
                    $('#edit-menu-list input[name=status-menu2][value=20]').closest('label').addClass('active');
                }else if(menuStatusVal == 30){
                    $('#edit-menu-list input[name=status-menu2][value=30]').prop('checked',true);
                    $('#edit-menu-list input[name=status-menu2][value=20]').prop('checked',false);
                    $('#edit-menu-list input[name=status-menu2]').closest('label').removeClass('active');
                    $('#edit-menu-list input[name=status-menu2][value=30]').closest('label').addClass('active');
                };

                if(data.validUntil){
                    $('#edit-menu-list input[data-api-attr=validUntil]').val(
                        moment(data.validUntil).format(DATE_OUT_FORMAT)
                    );
                }else{
                    //TO BE REMOVED LATER
                    $('#edit-menu-list input[data-api-attr=validUntil]').val(
                        moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                    );

                }
             //   $('#edit-menu-list textarea[data-api-attr=validUntil]').val(data.validUntil);
              //  $('#edit-menu-list textarea[data-api-attr=tagRole]').val(data.tagRole);
                $('#edit-menu-list input[data-api-attr=id]').val(data.id);

                datePlugin();


                $('#edit-menu-list input[name=status-menu2]').prop( "disabled", true);  
                $('#edit-menu-list input[name=status-menu2]').addClass('disabled');
                $('#edit-menu-list input[name=status-menu2]').closest('label').addClass('disabled');

                $('#edit-menu-list').removeClass('hidden');
                $('#edit-menu-list').closest('.modal-content').find('#update-model-menu-status').addClass('hidden');
                var statusUpdatePayload = _.extend(_.clone(data), {status: data.status == 30 ? 20 : (data.status == 20 ? 30 : data.status) });
                
                $('.js-confirmation-status').removeClass('disabled').prop( "disabled", false);   
                if(_.contains([20, 30], data.status)){
                    addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'data-menus/'+data.id,statusUpdatePayload,statusUpdateCallback);    
                }else if(!data.status){
                    statusUpdatePayload['status'] = 20;
                    addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'data-menus/'+data.id,statusUpdatePayload,statusUpdateCallback);  
                }else{
                    $('.js-confirmation-status').addClass('disabled').prop( "disabled", true);
                }

                if(data.status != 20){
                    $( "#edit-menu-list [type=submit]" ).prop( "disabled", true).addClass('disabled');
                    $( ".js-confirmation-status" ).html('Activate');
                }else{
                    $( "#edit-menu-list [type=submit]" ).prop( "disabled", false).removeClass('disabled');
                    $( ".js-confirmation-status" ).html('Deactivate');

                }


            }
            $(document).on('click', '.js-remove-row' ,function(){
                $(row_sec).remove();
            });
             $('#edit-menu-list').submit(function (e){
                e.preventDefault();
                var formValidate2 = $('#edit-menu-list').parsley().validate();
                validateFront();
               var irMenuIdPut = $('#edit-menu-list input[data-api-attr=id]').val();
               //var menuAttrs = ['menuItemName','validUntil','status','notes','menuItemDesc'];
               var menuAttrs = ['dataMenu','status','notes','desc','validUntil','url'];
               var dataString = {};

               _.each(menuAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('#edit-menu-list input[name=status-menu2][data-api-attr='+key+']:checked').val();  
                        dataString[key] = parseInt(dataString[key], 10);  
                    }else if(key == 'validUntil' &&
                             moment($('#edit-menu-list .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                        dataString[key] =  moment(new Date($('#edit-menu-list .form-control[data-api-attr='+key+']').val()),DATE_OUT_FORMAT).valueOf();
                    }else{
                        dataString[key] =  $('#edit-menu-list .form-control[data-api-attr='+key+']').val();    
                    }
               });

               console.log(dataString);
                if(formValidate2 == true){
                    dataString = _.omit(dataString, 'status');
                    console.log('success');
                    $('#edit-menu-list').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                   $.putJSON(API_ENDPOINT+'data-menus/'+irMenuIdPut, dataString, putIRMenuCallback);
                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });
            var putIRMenuCallback = function(data){
                if(data){
                    $('#edit-menu-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                console.log('data update');
                $('#edit-menu-list').addClass('hidden');
                $('#edit-menu-list').next('#update-model').removeClass('hidden');
                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }
                clearTpl('#menus_list');
                populateIRMenu();
            }

            // ==================== Update form end here 
            $(document).on('click','.js-btn-verify2', function(){
                $('#edit-menu-list').removeClass('hidden');
                $('#edit-menu-list').next('#update-model').addClass('hidden');

            });

        });
        
        // ==================== GET_API_WRONG_RESPONSE code ====================
         $(document).ajaxError(function() {
            $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_wrong_response);
            if($('.loading-sec').hasClass("hidden")){
                $('.loading-sec').addClass('hidden');
                $('#add-menu-list input, #add-menu-list textarea').val('');
                $('[data-dismiss=modal]').trigger('click');
                $('#failed-model-update').modal('show');
            }
        });
        // ==================== GET_API_WRONG_RESPONSE code end ====================

        var statusUpdateCallback = function(data){
                if(data){
                    $('#edit-menu-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };

                console.log('data update');
                $('#edit-menu-list').addClass('hidden');
                var success_msg = data.status == 30 ? "Menu deactivated successfully" :  "Menu has been activated successfully";
                $('#edit-menu-list').closest('.modal-content').find('#update-model-menu-status .mtb50').html(success_msg);
                $('#edit-menu-list').closest('.modal-content').find('#update-model-menu-status').removeClass('hidden');

                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }

                clearTpl('#menus_list');
                populateIRMenu();
        };


    </script>


</body>
</html>
