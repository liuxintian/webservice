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

                <div class="right_col_content">

                    <div class="row top_tiles" style="margin: 10px 0;">
                        <div class="col-md-3 col-sm-3 col-xs-6 tile">
                            <span>Total Anouncements</span>
                            <h2>97</h2>
                            <!-- <span class="sparkline_one" style="height: 160px;">
                                <canvas width="200" height="60" style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
                            </span> -->
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-6 tile">
                            <span>Total Documents</span>
                            <h2>89</h2>
                            <!-- <span class="sparkline_one" style="height: 160px;">
                                <canvas width="200" height="60" style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
                            </span> -->
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-6 tile">
                            <span>Total Executives</span>
                            <h2>31</h2>
                            <!-- <span class="sparkline_two" style="height: 160px;">
                                <canvas width="200" height="60" style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
                            </span> -->
                        </div>
                        <div class="col-md-3 col-sm-3 col-xs-6 tile">
                            <span>Total Users</span>
                            <h2>9,809</h2>
                            <!-- <span class="sparkline_one" style="height: 160px;">
                                <canvas width="200" height="60" style="display: inline-block; vertical-align: top; width: 94px; height: 30px;"></canvas>
                            </span> -->
                        </div>
                    </div>
                    <br />


                    <div class="row">
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="x_panel fixed_height_320">
                                <div class="x_title">
                                    <h2>Application Devices <small>Sessions</small></h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <h4>App Versions</h4>
                                    <div class="widget_summary">
                                        <div class="w_left w_25">
                                            <span>1.5.2</span>
                                        </div>
                                        <div class="w_center w_55">
                                            <div class="progress">
                                                <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 66%;">
                                                    <span class="sr-only">60% Complete</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="w_right w_20">
                                            <span>3k</span>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>

                                    <div class="widget_summary">
                                        <div class="w_left w_25">
                                            <span>1.5.3</span>
                                        </div>
                                        <div class="w_center w_55">
                                            <div class="progress">
                                                <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 45%;">
                                                    <span class="sr-only">60% Complete</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="w_right w_20">
                                            <span>2k</span>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="widget_summary">
                                        <div class="w_left w_25">
                                            <span>1.5.4</span>
                                        </div>
                                        <div class="w_center w_55">
                                            <div class="progress">
                                                <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 25%;">
                                                    <span class="sr-only">60% Complete</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="w_right w_20">
                                            <span>2k</span>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="widget_summary">
                                        <div class="w_left w_25">
                                            <span>1.5.5</span>
                                        </div>
                                        <div class="w_center w_55">
                                            <div class="progress">
                                                <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 5%;">
                                                    <span class="sr-only">60% Complete</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="w_right w_20">
                                            <span>1k</span>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="widget_summary">
                                        <div class="w_left w_25">
                                            <span>0.1.5.6</span>
                                        </div>
                                        <div class="w_center w_55">
                                            <div class="progress">
                                                <div class="progress-bar bg-green" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 2%;">
                                                    <span class="sr-only">60% Complete</span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="w_right w_20">
                                            <span>1k</span>
                                        </div>
                                        <div class="clearfix"></div>
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="x_panel fixed_height_320">
                                <div class="x_title">
                                    <h2>Daily active users <small>Sessions</small></h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">

                                    <table class="tile" style="width:100%">
                                        <tr>
                                            <th style="width:37%;">
                                                <span>Top 5</span>
                                            </th>
                                            <th>
                                                <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
                                                    <span class="hidden-small">Disbursement</span>
                                                </div>
                                                <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                                                    <span class="hidden-small">Progress</span>
                                                </div>
                                            </th>
                                        </tr>
                                        <tr>
                                            <td>
                                                <canvas id="canvas1" height="140" width="140" style="margin: 15px 10px 10px 0"></canvas>
                                            </td>
                                            <td>
                                                <table class="tile_info">
                                                    <tr>
                                                        <td>
                                                            <p><i class="fa fa-square blue"></i>IOS </p>
                                                        </td>
                                                        <td>30%</td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <p><i class="fa fa-square green"></i>Android </p>
                                                        </td>
                                                        <td>10%</td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <p><i class="fa fa-square purple"></i>Blackberry </p>
                                                        </td>
                                                        <td>20%</td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <p><i class="fa fa-square aero"></i>Symbian </p>
                                                        </td>
                                                        <td>15%</td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <p><i class="fa fa-square red"></i>Others </p>
                                                        </td>
                                                        <td>30%</td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="x_panel fixed_height_320">
                                <div class="x_title">
                                    <h2>Daily active users <small>Sessions</small></h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="dashboard-widget-content">
                                        <ul class="quick-list">
                                            <li><i class="fa fa-calendar-o"></i><a href="#">Settings</a>
                                            </li>
                                            <li><i class="fa fa-bars"></i><a href="#">Subscription</a>
                                            </li>
                                            <li><i class="fa fa-bar-chart"></i><a href="#">Auto Renewal</a> </li>
                                            <li><i class="fa fa-line-chart"></i><a href="#">Achievements</a>
                                            </li>
                                            <li><i class="fa fa-bar-chart"></i><a href="#">Auto Renewal</a> </li>
                                            <li><i class="fa fa-line-chart"></i><a href="#">Achievements</a>
                                            </li>
                                            <li><i class="fa fa-area-chart"></i><a href="#">Logout</a>
                                            </li>
                                        </ul>

                                        <div class="sidebar-widget">
                                            <h4>Profile Completion</h4>
                                            <canvas width="150" height="80" id="foo" class="" style="width: 160px; height: 100px;"></canvas>
                                            <div class="goal-wrapper">
                                                <!-- <span class="gauge-value pull-left">$</span> -->
                                                <span id="gauge-text" class="gauge-value pull-left">3,200</span>
                                                <span id="goal-text" class="goal-value pull-right">5,000</span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        
                        <div class="col-md-4 col-sm-6 col-xs-12 widget_tally_box">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>User Uptake</h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                        </li>
                                        <li class="dropdown">
                                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                                            <ul class="dropdown-menu" role="menu">
                                                <li><a href="#">Settings 1</a>
                                                </li>
                                                <li><a href="#">Settings 2</a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a class="close-link"><i class="fa fa-close"></i></a>
                                        </li>
                                    </ul>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">

                                    <!-- <div id="graph_bar" style="width:100%; height:200px;"></div> -->
                                    <div class="col-xs-12 bg-white progress_summary">

                                        <div class="row">
                                            <div class="progress_title">
                                                <span class="left">Escudor Wireless 1.0</span>
                                                <span class="right">This sis</span>
                                                <div class="clearfix"></div>
                                            </div>

                                            <div class="col-xs-2">
                                                <span>SSD</span>
                                            </div>
                                            <div class="col-xs-8">
                                                <div class="progress progress_sm">
                                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="89"></div>
                                                </div>
                                            </div>
                                            <div class="col-xs-2 more_info">
                                                <span>89%</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="progress_title">
                                                <span class="left">Mobile Access</span>
                                                <span class="right">Smart Phone</span>
                                                <div class="clearfix"></div>
                                            </div>

                                            <div class="col-xs-2">
                                                <span>App</span>
                                            </div>
                                            <div class="col-xs-8">
                                                <div class="progress progress_sm">
                                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="79"></div>
                                                </div>
                                            </div>
                                            <div class="col-xs-2 more_info">
                                                <span>79%</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="progress_title">
                                                <span class="left">WAN access users</span>
                                                <span class="right">Total 69%</span>
                                                <div class="clearfix"></div>
                                            </div>

                                            <div class="col-xs-2">
                                                <span>Usr</span>
                                            </div>
                                            <div class="col-xs-8">
                                                <div class="progress progress_sm">
                                                    <div class="progress-bar bg-green" role="progressbar" data-transitiongoal="69"></div>
                                                </div>
                                            </div>
                                            <div class="col-xs-2 more_info">
                                                <span>69%</span>
                                            </div>
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
    



    <!-- Document model remove end  -->
    <!-- Datatables --> 
        <script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script>

    <!-- ============================== User Admin  Date List api code ============================== -->
    <script type="text/template" id="managers_list_template">     
        <$$ _.each(characters, function(managerItems,index){ $$>
            
               <tr class="pointer">
                <td><$$= managerItems.name $$></td>
                <td><$$= managerItems.loginName $$></td>
                <td><$$= managerItems.role $$></td>
                <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View</button></td>
                <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-document" data-toggle="modal" data-target=".document-edit"> <i class="fa fa-edit"></i> Edit</button> <input type="text" value="<$$= managerItems.userId $$>" class="thisDocId hidden" /></td>
                <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-remove-sec" data-toggle="modal" data-target=".document-remove"> <i class="fa fa-remove"></i> Remove</button></td>
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

            getComManagerItems('managers').then(function (adata) {
                // body...

                injectTpl('#managers_list',adata).then(function () {
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
           $('#admin-user-add-form .btn[data-attr=submit-btn]').on('click', function(){
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
                        $.postJSON(API_ENDPOINT+'managers', dataString, postCompanyDocuCallback);
                }else{
                    alert('Validation fail ');
                }

           });
          // Form post code here end 
            var postCompanyDocuCallback = function(data){
                $('#admin-user-add-form input, #admin-user-add-form textarea').val('');
                $('#admin-user-add-form').addClass('hidden');
                $('#admin-user-add-form').next('#sucess-model').removeClass('hidden');
                //alert('Added new Admin User');
                $('.js-btn-verify').on('click', function(){
                    $('#admin-user-add-form').removeClass('hidden');
                    $('#admin-user-add-form').next('#sucess-model').addClass('hidden');
                });
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
            // ======================================== Form 2 here start
            // Edit Admin User form post code here 
                $(document).on('click', '.js-edit-document', function(){
                    var dataString = '';
                    var documentId = $(this).next('.thisDocId').val();
                    $.getJSON(API_ENDPOINT+'managers/'+documentId, dataString, getCompanyDocuCallback);
                });
                var getCompanyDocuCallback = function(data){
                    console.log(data.name);
                    $('#admin-user-edit-form input[data-api-attr=name]').val(data.name);
                    $('#admin-user-edit-form input[data-api-attr=loginName]').val(data.loginName);
                    $('#admin-user-edit-form input[data-api-attr=password]').val(data.password);
                    //$('#admin-user-edit-form input[data-api-attr=role]').val(data.role);
                    $('#admin-user-edit-form input[data-api-attr=userId]').val(data.userId);
                }
                $(document).on('click', '#admin-user-edit-form .btn[data-attr=update-btn]' , function(){
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
                        console.log('success');
                            $.putJSON(API_ENDPOINT+'managers/'+docListId, dataString, putUserAdminCallback);
                    }else{
                        alert('Validation fail ');
                    }
                });
                var putUserAdminCallback = function(data){
                    //console.log('data ===' + data.id);
                    $('#admin-user-edit-form').addClass('hidden');
                    $('#admin-user-edit-form').next('#sucess-model-update').removeClass('hidden');
                    $(document).on('click', '.js-btn-update', function(){
                        $('#admin-user-edit-form').removeClass('hidden');
                        $('#admin-user-edit-form').next('#sucess-model-update').addClass('hidden');
                    });

                    if(!_.isEmpty(menuTable)){
                    //menuTable.destroy();

                        menuTable.fnDestroy();
                        menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();
                    }
                        clearTpl('#managers_list');
                        populateAdminUserMenu();
                    }
            // Edit Admin User form post code here end 

            // ======================================== Form Remove code
            $('.js-remove-sec').on('click', function(){
                var currentExecutiveBtn = $(this);
                    $('.js-remove-executive').on('click', function(){
                    $(currentExecutiveBtn).parents('.pointer').remove();
                });
            });
            // Form Remove Function here end 
        });

        
    </script>       

    <script>
        $('document').ready(function () {
            $(".sparkline_one").sparkline([2, 4, 3, 4, 5, 4, 5, 4, 3, 4, 5, 6, 77, 5, 4, 3, 5, 6], {
                type: 'bar',
                height: '40',
                barWidth: 9,
                colorMap: {
                    '7': '#a1a1a1'
                },
                barSpacing: 2,
                barColor: '#26B99A'
            });

            $(".sparkline_two").sparkline([2, 4, 3, 4, 5, 4, 5, 4, 3, 4, 5, 6, 77, 5, 4, 3, 5, 6], {
                type: 'line',
                width: '200',
                height: '40',
                lineColor: '#26B99A',
                fillColor: 'rgba(223, 223, 223, 0.57)',
                lineWidth: 2,
                spotColor: '#26B99A',
                minSpotColor: '#26B99A'
            });
        })
    </script>


</body>
</html>
