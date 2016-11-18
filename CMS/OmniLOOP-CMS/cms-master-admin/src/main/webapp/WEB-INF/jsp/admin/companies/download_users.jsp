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
                            <h3>User list download</h3>
                        </div>

                        <div class="title_right">
                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel  pos-rel">
                                <div class="x_content">

                                    
                                        <!-- div id="company_tiles"></div -->
                                        
                                        <p class="text-center">
                                        	<a target="_blank" href="<%= baseURL %>/master-admin/users/csv" class="btn btn-success btn-lg">Download Users in CSV file</a>
                                        </p>
                                        
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
    


    <script>

        var apiEnds = [] , apiRsps = {};
        var getCompanies = function(path) {
            // body...
            //return $.ajax({ url: 'http://localhost:8080/cms-master-api/companies' });
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };

        var getCompany = function(path,id) {
            // body...
            //return $.ajax({ url: 'http://localhost:8080/cms-master-api/companies' });
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: API_ENDPOINT+path+'/'+id
            });
        };

        var chainedStepTwo = function(inputResponse){
            apiRsps[_.last(apiEnds)] = inputResponse;
            var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
            return getCompany('companies',compId);
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
          

          


    </script>

        
</body>
</html>