<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>
<link href="<%= baseURL %>/assets/css/datatables/tools/css/dataTables.tableTools.css" rel="stylesheet">
<link href="<%= baseURL %>/assets/css/select2.min.css" rel="stylesheet">

<style type="text/css">
    #csv-table-sec .csvDrawTable tr.selected{
        background: #d0e9c6 !important;
        border-bottom: solid 2px #fff;
    }
    #csv-table-sec .csvDrawTable tr.non-selected{
        background: #ebcccc !important;
        border-bottom: solid 2px #fff;
    }
</style>
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
                            <h3>List of Users</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel pos-rel">
                                <div class="loading-sec hidden"></div>
                                <div class="x_content">
                                   <div class="clearfix">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label for="fileUpload" class="csv-file-load" style="float:left;">
                                                    <input type="file" id="fileUpload" class="hidden" />
                                                    <span class="btn btn-primary">
                                                        <i class="glyphicon glyphicon-folder-open"></i>
                                                         Upload Data
                                                    </span><!-- <input type="text" value="" class="form-control" /> -->
                                                    <span id="pathNameShow"></span>
                                                </label>
                                            </div>
                                            <div class="col-md-6">
                                                <a href="<%= baseURL %>/cust-admin/ui/powerusers" class="btn btn-default pull-right"> Back to users List</a>
                                                <button type="button" class="btn btn-default pull-right" id="clr-table">Clear table</button>
                                                
                                            </div>
                                        </div>
                                        
                                        <div class="row">
                                            <!-- <div class="col-md-6">
                                                <div class="form-group">
                                                    <label for="seprators-field" class="control-label col-md-1 col-sm-1 col-xs-1" style="margin-right: 9px; ">Separators:</label>
                                                    <div class="col-md-2 col-sm-2 col-xs-2">
                                                        <input type="text" class="form-control" maxlength="3" value="" id="seprators-field" style="line-height: 25px; height: 25px; width: 12px; padding-top: 0; padding-bottom: 0;" />
                                                    </div>
                                                </div> -->


                                                <form class="form-inline">
                                                  <div class="form-group" style="margin-left: 15px;">
                                                    <label class="" for="exampleInputAmount">&nbsp;Separators:</label>
                                                    <div class="input-group">
                                                      <div class="input-group-addon">Field:</div>
                                                      <input type="text" id="seprators-field-cells" class="form-control input-sm" style="font-size: 16px;width: 60px;font-weight: bold;" id="exampleInputAmount"  value="," >
                                                      <div class="input-group-addon">Roles:</div>
                                                      <input type="text" id="seprators-field-roles" class="form-control input-sm" style="font-size: 16px;width: 60px; font-weight: bold;" id="exampleInputAmount"  value="\;">
                                                      <!-- <div class="input-group-addon">.00</div> -->
                                                    </div>
                                                  </div>
                                                  
                                                </form>


                                            </div>
                                        </div>

                                        <hr style="margin-top:0;" />
                                        <div class="js-success-invite alert alert-success alert-dismissable hidden">
                                            <a href="#" class="close js-cose-alert">&times;</a>
                                          <strong>Success!</strong> Invite has been sent to following users
                                        </div>
                                        <div class="x_content" id="csv-table-sec"></div>
                                        <div class="clearfix">
                                            <div class="text-center">
                                                <a href="#" class="btn btn-primary jsInviteUniqueUser" id=""> <i class="fa fa-user"></i> Invite user</a>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- <div class="row">
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <button type="button" class="btn btn-default"> <i class="fa fa-file"></i> Create New User</button>
                                        </div>
                                        <div class="clearfix"></div>

                                          <div class="x_content" id="remove-search-tools">
                                            <table id="example" class="table table-striped responsive-utilities jambo_table">
                                                <thead>
                                                    <tr class="headings">
                                                        <th>User Name</th>
                                                        <th>User Login ID </th>
                                                        <th>Role</th>
                                                        <th class=" no-link last text-center" colspan="3"><span class="nobr">Action</span>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                  <tbody id="managers_list"></tbody>
                                                </table>
                                            </div>

                                    </div> -->
                                   

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
<!-- Invite Users model window  -->
    <div class="modal fade invite-users" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg">
             <div class="modal-content">
                <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Invite Users</h4>
                </div>
                <div id="sucess-model-update" class="text-center">
                    <h4 class="mtb50">User invite sent successfully </h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
         </div>
     </div>
    <!-- Invite Users model window end  -->

    <script src="<%= baseURL %>/assets/js/jquery.tabledit.min.js" type="text/javascript"></script>
    <script src="<%= baseURL %>/assets/js/select2.min.js" type="text/javascript"></script>
    <%@ include file="/WEB-INF/jsp/common/common_js_scripts.jspf"%>     
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
    
    
    <!-- Datatables --> 
        <script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script>

    <!-- ============================== User Admin  Date List api code ============================== -->
 <!--   <script type="text/template" id="managers_list_template">     
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
    </script> -->
    <script id ="main-list-render">

       /* var apiEnds = [] , apiRsps = {}, menuTable;
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
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                // body...

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

          $(document).ready(populateAdminUserMenu);*/

    </script>

    <!-- ============================== Admin User List api code ============================== -->
    <script type="text/javascript">
    // =================================================================
    

    var fakeId = 100;
    var fakeUserName = '';
    var fakeLoginName = '';
    var fakeRole = '';
    var blank_row = '<tr class="dynamicSec"><td>'+fakeId+ '</td><td>'+fakeUserName+'</td><td>'+fakeLoginName+'</td><td>'+fakeRole+'</td><td>'+ $('.csvDrawTable .new_blank').html() +'</td></tr>';


    var draw_table = function(){

        blank_row = blank_row ? blank_row  : "<tr>"+$('.csvDrawTable .new_blank').html()+"</tr>";
        $('.csvDrawTable .new_blank').hide();

        $('.csvDrawTable').Tabledit({
           // url: 'example.php',
            columns: {
                identifier: [1, 'loginname'],
                //editable: [[1, 'username'], [2, 'email'], [3, 'avatar', '{"1": "Black Widow"}']]
                //editable: [[1, 'loginname'], [2, 'userrole']]
                editable: [[0, 'username'] , [2, 'userrole']]
            },
            // onDraw: function() {
            //     console.log('onDraw()');
            // },
            // onSuccess: function(data, textStatus, jqXHR) {
            //     console.log('onSuccess(data, textStatus, jqXHR)');
            //     console.log(data);
            //     console.log(textStatus);
            //     console.log(jqXHR);
            // },
            // onFail: function(jqXHR, textStatus, errorThrown) {
            //     console.log('onFail(jqXHR, textStatus, errorThrown)');
            //     console.log(jqXHR);
            //     console.log(textStatus);
            //     console.log(errorThrown);
            // },
            // onAlways: function() {
            //     console.log('onAlways()');
            // },
            // onAjax: function(action, serialize) {
            //     console.log('onAjax(action, serialize)');
            //     console.log(action);
            //     console.log(serialize);
            // },
            editButton: false

        });

          $('tr .tabledit-edit-button.btn').click(function(){
            var drop_down_ctrl = $(this).closest('tr').find("select.tabledit-input.form-control");
            
            //drop_down_ctrl.show();
            drop_down_ctrl.addClass('js-states form-control');
            drop_down_ctrl.attr('multiple','multiple');
            drop_down_ctrl.select2({
              theme: "classic"
            });

            $(this).closest('tr').find(".select2-container").show();
          });

          $('tr .tabledit-save-button.btn.btn-sm').click(function(){

            var drop_down_ctrl = $(this).closest('tr').find(".select2-container").hide();
            //drop_down_ctrl.find("select.tabledit-input.form-control").hide();

          });

    };


    $('#add-row').click(function(){
        
        $('.csvDrawTable').find('tr th:nth-of-type(5)').remove();
        $('.csvDrawTable').find('tr td:nth-of-type(5)').remove();
        $(".csvDrawTable tbody tr:first-child").after($(blank_row));
       // var selectHtml = $('.csvDrawTable').find('tr td:nth-of-type(4) span.tabledit-span');
       // var thisText = '';
        /*$(selectHtml).each(function() {
             thisText = $(this).html();
          console.log(": " + thisText);
        });*/
        
        /*$.each(selectHtml, function (index, value) {
            value = $(this).html();
            thisText = value;
          console.log(index + value);
        });*/
        $('.csvDrawTable tr+tr').find('td:nth-of-type(5)').remove();
        $('.csvDrawTable').find('tr td:nth-of-type(4) span.tabledit-span').text('');

        draw_table();
        $('.csvDrawTable').find('tr:first-child td:nth-of-type(4) span.tabledit-span').html('Role');
        $('.csvDrawTable').find('tr:first-child th').html('Action');
        
       // $('.csvDrawTable').find('tr td:nth-of-type(4) span.tabledit-span').text(thisText);
    });
    $('#clr-table').on('click', function(){
        $('#fileUpload').val('');
        $('#pathNameShow').html('');
        $('#csv-table-sec>.csvDrawTable').remove();
        $('#add-row, #clr-table').attr('disabled', 'disabled');
        $('.js-success-invite').addClass('hidden');
    });
    // ==============================================================
        /* ========================== */
        $(document).ready(function(){
            var csv_field_separator = ',';
            var csv_row_separator = new RegExp('\r\n|\r|\n', 'g');//"\n";
            var role_separator = '\;';

            //csv_row_separator = $.trim($('#seprators-field-line').val()) ? $.trim($('#seprators-field-line').val()) : csv_row_separator;
            csv_field_separator = $.trim($('#seprators-field-cells').val()) ? $.trim($('#seprators-field-cells').val()) : csv_field_separator;
            role_separator = $.trim($('#seprators-field-roles').val()) ? $.trim($('#seprators-field-roles').val()) : role_separator;
            var csv_role_separator = new RegExp(role_separator, 'ig');



            $('#fileUpload').on('change', function(){
                $('.js-success-invite').addClass('hidden');
                
                // if($('#seprators-field').val() == ''){
                //     csv_field_separator = ',';
                // }else{
                //     csv_field_separator = $('#seprators-field').val();
                // }

                var regex = /^([a-zA-Z0-9\s_\\.\-:])+(.csv|.txt)$/;
                if (regex.test($("#fileUpload").val().toLowerCase())) {
                    if (typeof (FileReader) != "undefined") {

                        $('#add-row, #clr-table').removeAttr('disabled', 'disabled');
                        var filePath = $('#fileUpload').val();
                        $('#pathNameShow').text(filePath);

                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var table = $("<table class='csvDrawTable' />");
                            var rows = e.target.result.split(csv_row_separator);
                            for (var i = 0; i < rows.length; i++) {
                                var row = $("<tr />");
                                var cells = rows[i].split(csv_field_separator);
                                for (var j = 0; j < cells.length; j++) {
                                    var cell = $("<td />");
                                    cell.html(cells[j]);
                                    row.append(cell);
                                }
                                table.append(row);
                            }
                            $("#csv-table-sec").html('');
                            $("#csv-table-sec").append(table);
                            draw_table();
                            $("#csv-table-sec tr td:last-child").addClass('new_blank');
                            var emptyTr = $('td:first-child span:empty');
                            if(emptyTr){
                                $(emptyTr).parents('tr').remove();
                            }
                            checkUniqueUser();
                        }
                        reader.readAsText($("#fileUpload")[0].files[0]);
                    } else {
                        alert("This browser does not support HTML5.");
                    }
                } else {
                    alert("Please upload a valid CSV file.");
                }
            });
            
            var checkUniqueUser = function(){
                var dataString = {"users":[]};
                var recordRows = {};

                $('#csv-table-sec .csvDrawTable tr + tr').each(function(){
                    var thisSec = $(this);
                    var uniqueUser = $(thisSec).find('input[name=loginname]').val();
                    console.log('hello' + uniqueUser);
                    //var dataString = '';
                    // dataString = {"users":[
                    //     {"loginName":uniqueUser}
                    // ]}
                    dataString.users.push({"loginName":uniqueUser});
                    recordRows[uniqueUser] = thisSec;
                });


                $.putJSON(API_ENDPOINT+'local-users/login-names-inuse', dataString, 
                    function(data){
                       // console.log('data-->' +  data.users[0].loginName);
                        // var uniqueUserCheck = data.users[0].inUse;
                        // var uniqueUserName = data.users[0].loginName;
                        // if(uniqueUserCheck == false){
                        //     $(thisSec).addClass('selected');
                        //     $('.jsInviteUniqueUser').removeAttr('disabled','disabled');
                        // }else{
                        //     $(thisSec).addClass('non-selected');
                        // }
                        var numFreshUsers = 0;    
                        _.each(data.users, function(user){

                            var uniqueUserCheck = user.inUse;
                            var uniqueUserName  = user.loginName;
                            var thisRow = recordRows[uniqueUserName];

                            if(uniqueUserCheck == false){
                                thisRow.addClass('selected');
                                numFreshUsers++;
                            }else{
                                thisRow.addClass('non-selected');
                            }
                        });

                        if(numFreshUsers){
                            $('.jsInviteUniqueUser').removeAttr('disabled','disabled');
                        }

                    }

                );

            };
            $('#add-row').attr('disabled', 'disabled');
            $('#clr-table').attr('disabled', 'disabled');
            /*$('#fileUpload').on('click', function(){
                setTimeout(function(){
                    $('label.csv-file-load input.form-control').val($('#fileUpload').val());
                }, 2000);
            });*/
            $('.jsInviteUniqueUser').attr('disabled','disabled');
            $('.jsInviteUniqueUser').on('click', function(){
                var dataString = {};
                
                //var dataString

                $('#csv-table-sec .csvDrawTable tr.selected').each(function(){
                    var thisTr = $(this);
                    // var uniqueUserVal = $(thisTr).find('input[name=email]').val();
                    // var uniqueUserName = $(thisTr).find('input[name=username]').val();
                    // var uniqueUserRole = $(thisTr).find('select option:selected');
                    
                    var uniqueUserVal = $(thisTr).find('input[name=username]').val();
                    var uniqueUserName = $(thisTr).find('input[name=loginname]').val();
                    var uniqueUserRole = $(thisTr).find('input[name=userrole]').val();

                    
                    // var option_all = $(uniqueUserRole).map(function () {
                    //         return $(this).text();
                    //     }).get().join();

                   if(!dataString.users){
                        dataString.users = [];
                   }
                   dataString.users.push({
                          "userName": uniqueUserVal,
                          "loginName":uniqueUserName,
                          "userEmail":uniqueUserName,
                          "tagRole": uniqueUserRole.replace(csv_role_separator, ",")
                    });

                });
                console.log(dataString);
               // alert('done');
                $('.loading-sec').removeClass('hidden');
                $('.loading-sec').css({"background-position":"center bottom"});

                 $.postJSON(API_ENDPOINT+'local-users/mutli-users', dataString, 
                    function(data){
                    $('.jsInviteUniqueUser').attr('disabled','disabled');
                    $('.loading-sec').addClass('hidden');
                    $('.loading-sec').css({"background-position":"center top"});
                    console.log('success');
                    $('.invite-users').modal('show');
                    $('.js-success-invite').removeClass('hidden');
                });

            });
            $('.js-cose-alert').on('click', function(){
                $('.js-success-invite').addClass('hidden');
            });
        });
       /* $(document).ready(function () {
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
                    $('#admin-user-add-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                        $.postJSON(API_ENDPOINT+'managers', dataString, postCompanyDocuCallback);
                }else{
                    alert('Validation fail ');
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

            // ======================================== Form 2 here start
            // Edit Admin User form post code here 
                $(document).on('click', '.js-edit-document', function(){
                    var dataString = '';
                    var documentId = $(this).next('.thisDocId').val();
                    $('#admin-user-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                    $.getJSON(API_ENDPOINT+'managers/'+documentId, dataString, getCompanyDocuCallback);
                });
                var getCompanyDocuCallback = function(data){
                    if(data){
                        $('#admin-user-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    }
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
                        $('#admin-user-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                            $.putJSON(API_ENDPOINT+'managers/'+docListId, dataString, putUserAdminCallback);
                    }else{
                        alert('Validation fail ');
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
                    //menuTable.destroy();

                        menuTable.fnDestroy();
                        menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();
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
          
            });
            // Form Remove Function here end 
        });

        */
    </script>       
</body>
</html>