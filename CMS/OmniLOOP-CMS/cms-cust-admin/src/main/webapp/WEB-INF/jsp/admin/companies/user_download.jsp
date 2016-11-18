<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>

<style>
	.row .seg-sec h4 {float:left;}
	
	.seg-sec2.clearfix p {margin: 0 0 12px;padding-left:6px;}
	.seg-sec2 h2{    border-bottom: 1px solid #DFDFDF; padding-bottom: 8px;}
	.row {margin-top: 8px;}
	.row .seg-sec h5 {float: left;}
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
                
                <div class="landing_content">
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
					
					<div class="loading-sec hidden"></div>
					
                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel  pos-rel">
                                <div class="x_content">

                                    
                                        <!-- div id="company_tiles"></div -->
                                        
                                        <p id="download_link" class="text-center">
                                        	<a target="_blank" href="#" class="btn btn-success btn-lg">Download Users in CSV file</a>
                                        </p>
                                        
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
        //var fakeJson = 'http://localhost:8080/cms-cust-admin/assets/js/';

        var apiEnds = [] , apiRsps = {};
        var getComDashboards = function() {
            apiEnds.push(API_ENDPOINT_CO);
            return $.ajax({
            	url:   API_ENDPOINT_CO
            	//url: fakeJson+path
            });
        };

        var injectTpl = function (id,data) {
              var deferred = $.Deferred();

              deferred.done(function(val,data){
                var template_cmp = _.template($(val+"_template").html());
                if(data.createdOn){
                	data.createdOn = moment(new Date(data.createdOn)).format("Do MMM YYYY");
                }
                
                if(data.companyTicker){
                	data.companyTicker = data.companyTicker.toUpperCase();
                }
                
				if(data.companyType && $.trim(data.companyType) == 'BDR'){
					data.companyType = 'Boardroom';
				}else if(data.companyType && $.trim(data.companyType) == 'OML'){
					data.companyType = 'Omni Loop';
				}else if(data.companyType && $.trim(data.companyType) == 'WHL'){
					data.companyType = 'White Labelled';
				}
                $(val).html(template_cmp({'company':data}));
              });

              deferred.resolve(id,data);
              return deferred.promise();
          };
          

            var populateComDashboards = function(){
                $('.landing_content').find('.loading-sec').removeClass('hidden');
                getComDashboards().then(function (adata) {
                   $('.landing_content').find('.loading-sec').removeClass('hidden');
                    // body...
                    injectTpl('#company_info',adata).then(function () {

                        $('.landing_content').find('.loading-sec').addClass('hidden');
                        
                    });
                }, function(reason) {
                    $('.landing_content').find('.loading-sec').addClass('hidden');
                    //console.log(reason); // Error!
                });
            };

            $(document).ready(function(){
            	$('#download_link a').attr('href',API_ENDPOINT_DW+'local-users/csv');
                // populateEmailContent();
                //populateComDashboards();
             });
            
        /*var injectTpl = function (id,data) {
          var deferred = $.Deferred();
          deferred.done(function(val,data){
            var template_cmp = _.template($(val+"_template").html());
            $(val).html(template_cmp({'characters':data}));
          });
          deferred.resolve(id,data);
          return deferred.promise();
        };
        var populateEmailContent = function(){
            injectTpl('#company_dashboard_tiles',{}).then(function () {});
        };*/

        
        /* var getCompany = function(path,id) {
	        // body...
	        //return $.ajax({ url: 'http://localhost:8080/cms-master-api/executives' });
	        apiEnds.push(path+'/'+id);
	        return $.ajax({
	          url: fakeJson+path+'/'+id
	        });
    	}; */

    	/* var chainedStepTwo = function(inputResponse){
        	apiRsps[_.last(apiEnds)] = inputResponse;
        	var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
        	return getCompany('fake.json',compId);
    	}; */
        

    </script>
</body>
</html>
