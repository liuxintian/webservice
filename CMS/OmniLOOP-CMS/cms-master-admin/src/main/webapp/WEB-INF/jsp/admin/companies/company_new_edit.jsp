<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>
<link href="<%= baseURL %>/assets/js/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />

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
                    <div class="page-title over-hidden">
                        <div class="title_left">
                            <h3>Create New Company</h3>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel pos-rel">
                                <div class="loading-sec hidden"></div>
                                <div class="x_title">
                                    <h2 id="companyName">New Company details</h2>
                                    <div class="clearfix"></div>
                                </div>
                                <div class="x_content">
                                    <div class="col-md-12 col-sm-12 col-xs-12">
                                        <div class="" role="tabpanel" data-example-id="togglable-tabs">
                                            <ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
                                                <li role="presentation" class="active"><a href="#tab_content0" id="home-tab" role="tab" data-toggle="tab" aria-expanded="true">Basic Info</a>
                                                </li>
                                                <li role="presentation" class="disabled-tab" id="disabled-tab"><a href="javascript:void(0);" id="home-tab">Instance Info</a>
                                                </li>
                                                <li role="presentation" class="disabled-tab" id="disabled-tab2"><a href="javascript:void(0);" id="profile-tab">IR Menu </a>
                                                </li>
                                                <li role="presentation" class="hidden"><a href="#tab_content4" role="tab" id="instance-access" data-toggle="tab" aria-expanded="false">Instance Access</a>
                                                </li>
                                            </ul>
                                            <div id="myTabContent" class="tab-content">
                                                <!-- Basic Infor Info Tab here  -->
                                                <div role="tabpanel" class="tab-pane fade active in" id="tab_content0" aria-labelledby="home-tab">
                                                    <%@ include file="/WEB-INF/jsp/admin/companies/company_basic_info.jsp"%>
                                                </div>
                                                <!-- start instance Info activity -->
                                                <div role="tabpanel" class="tab-pane fade " id="tab_content1" aria-labelledby="home-tab">
                                                    <%@ include file="/WEB-INF/jsp/admin/companies/company_instance_info.jsp"%>
                                                </div>
                                                <!-- start Company IR MENU activity -->
                                                <div role="tabpanel" class="tab-pane fade" id="tab_content2" aria-labelledby="profile-tab">
                                                    <%@ include file="/WEB-INF/jsp/admin/companies/company_ir_menu.jsp"%>
                                                </div>
                                                <!-- Instance Access Info Tab here -->
                                                <div role="tabpanel" class="tab-pane fade" id="tab_content4" aria-labelledby="instance-access">
                                                    <%@ include file="/WEB-INF/jsp/admin/companies/company_instance_access.jsp"%>
                                                </div>
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
        <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group"></ul>
        <div class="clearfix"></div>
        <div id="notif-group" class="tabbed_notifications"></div>
    </div>
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
    <script src="<%= baseURL %>/assets/js/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>

    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
    
    <!-- /datepicker -->
    <!-- Datatables --> 
    <script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
    <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script>
    <!-- COMMON_STUFF -->
    <script>
        

        var instaceIrMenu = function(){
            var instansTab = $('#myTab #disabled-tab');
            $(instansTab).removeClass('disabled-tab');
            $(instansTab).find('a').attr('href', '#tab_content1');
            $(instansTab).find('a').attr('role', 'role');
            $(instansTab).find('a').attr('data-toggle', 'tab');
            $(instansTab).find('a').attr('aria-expanded', 'true');

            // IR Menu Disabled Code
            var irMenuDisabled = $('#myTab #disabled-tab2');
            $(irMenuDisabled).removeClass('disabled-tab');
            $(irMenuDisabled).find('a').attr('href', '#tab_content2');
            $(irMenuDisabled).find('a').attr('role', 'role');
            $(irMenuDisabled).find('a').attr('data-toggle', 'tab');
            $(irMenuDisabled).find('a').attr('aria-expanded', 'true');
            // IR Menu Disabled Code end
        };

        function initToolbarBootstrapBindings() {
            var fonts = ['Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
                         'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
                         'Times New Roman', 'Verdana'],
                fontTarget = $('[title=Font]').siblings('.dropdown-menu');

                $.each(fonts, function (idx, fontName) {
                    fontTarget.append($('<li><a data-edit="fontName ' + fontName + '" style="font-family:\'' + fontName + '\'">' + fontName + '</a></li>'));
                });

                $('a[title]').tooltip({
                    container: 'body'
                });
            $('.dropdown-menu input').click(function () {
                    return false;
                })
                .change(function () {
                    $(this).parent('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle');
                })
                .keydown('esc', function () {
                    this.value = '';
                    $(this).change();
                });

            $('[data-role=magic-overlay]').each(function () {
                var overlay = $(this),
                    target = $(overlay.data('target'));
                overlay.css('opacity', 0).css('position', 'absolute').offset(target.offset()).width(target.outerWidth()).height(target.outerHeight());
            });
        };

        function showErrorAlert(reason, detail) {
            var msg = '';
            if (reason === 'unsupported-file-type') {
                msg = "Unsupported format " + detail;
            } else {
                console.log("error uploading file", reason, detail);
            }
            $('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong>File upload error</strong> ' + msg + ' </div>').prependTo('#alerts');
        };
    </script>
    <!-- COMMON_STUFF_END -->
    <script type="text/javascript">
         $(document).ready(function () {
            try {
                hljs.initHighlightingOnLoad();
            } catch (err) {}


            /* ====== Instance Tab Disabled functionality here ====== */
            var companyFormIdShow = getUrlParameter ('cid');
            if(companyFormIdShow){
                instaceIrMenu();
            }
            /* ====== Instance Tab Disabled functionality here end ====== */
            var instanceTab = getUrlParameter('stab');
            var companyUpdateFormId = getUrlParameter ('cid');
            //var instanceFormId = getUrlParameter('cidnstace');
            //console.log(companyUpdateFormId);
            /* Url Get Parameter function here end */
            if(instanceTab == 'nstanceTab'){
                $('a[href=#tab_content1]').click();
            };
            if(instanceTab == 'irMenu'){
                $('a[href=#tab_content2]').click();
            };
            /* === Dynamic Update data show here === */
            if(companyUpdateFormId){
                dataId = companyUpdateFormId;
                console.log('Yes id is this companyUpdateFormId ' + companyUpdateFormId);
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                $.getJSON(API_ENDPOINT+'companies/'+companyUpdateFormId, function(data){
                    getCompanyFormCallback(data);
                });

            }else{
                console.log('not find companyUpdateFormId');
            }

            /* === Dynamic Update data show here end === */
        });
        
        // ==================== GET_API_WRONG_RESPONSE code ====================
        var ajexErrorFun = function(){
            $(document).ajaxError(function() {
               // console.log('errro');
                $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_wrong_response);
               // if($('.js-form-spinner .loading-sec').hasClass("hidden")){
                    $('.loading-sec').addClass('hidden');
                   // $('#add-feed-list input, #add-feed-list textarea, #add-feed-list select').val('');
                   // $('[data-dismiss=modal]').trigger('click');
                    $('#failed-model-update').modal('show');
               //}
            });
        }
        $(document).on('click', '.btn.btn-success, .js-add-instance-admin-user', function(){
            ajexErrorFun();
        });
        // ==================== GET_API_WRONG_RESPONSE code end ====================

    </script>
</body>
</html>
