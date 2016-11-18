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
                            <h3>Dashboard <small> </small></h3>
                        </div>

                        <div class="title_right">
                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
					
					<div class="loading-sec hidden"></div>
					
                    <div class="row">
						<div id="company_info"></div>
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
    <script type="text/template" id="company_info_template">
	
                        <div class="col-md-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2><$$= company.companyName $$> - <$$= company.companyTicker $$></h2>
                                    
                                    <div class="clearfix"></div>
                                </div>

                                <div class="x_content">

                                    <div class="col-md-9 col-sm-9 col-xs-12">

                                        <ul class="stats-overview">
                                            <$$ if(company.companySize){ $$>
											<li>
                                                <span class="name"> Company capitalization</span>
                                                <span class="value text-success"> <$$= company.companySize $$> </span>
                                            </li>
                                            <$$ } $$>

                                            <$$ if(company.companyShareholders){ $$>
                                            <li>
                                                <span class="name"> No of Shareholders </span>
                                                <span class="value text-success"> <$$= company.companyShareholders $$> </span>
                                            </li>
                                            <$$ } $$>

                                            <$$ if(company.createdOn){ $$>
                                            <li class="hidden-phone">
                                                <span class="name"> Company on boarded </span>
                                                <span class="value text-success">
													<$$= company.createdOn $$>
												</span>
                                            </li>
                                            <$$ } $$>
                                        </ul>
                                        <br />

                                        <div id="mainb" style="height:350px;">
                    						<$$ if(!company.companyLogoBig){ $$>
                        						<img style="margin: auto;width: 60%;padding: 20px 80px;" src="<%= baseURL %>/assets/images/logo-placeholder.jpg" alt="" class="img-circle img-responsive">
                    						<$$ } else{$$>
                        						<img style="margin: auto;width: 60%;padding: 20px 80px;" src="<$$= company.companyLogoBig $$>" alt="Company Logo" class="img-circle img-responsive" />
                    						<$$ } $$>
                                        	 <p class="name" style="width: 100%;text-align: center;"><a target="_blank" href="<$$= company.companyURL $$>"><$$= company.companyURL $$></a></p>
                                        </div>
    										
                                    <div>

                                </div>


                           </div>

                            <!-- start project-detail sidebar -->
                            <div class="col-md-3 col-sm-3 col-xs-12">

                                <section class="panel">

                                    <div class="x_title">
                                        <h2 style="padding-bottom:12px">Other Details</h2>
                                        <div class="clearfix"></div>
                                    </div>

                                    <div class="panel-body">
                                        <$$ if(company.companyDescription){ $$>
										<h5 class="green">About:</h5>
                                            <p><$$= company.companyDescription $$></p>
                                        <br />
                                        <$$ } $$>

                                        <$$ if(company.notes){ $$>
                                        <h5 class="green">Notes:</h5>
                                            <p>
												<$$= company.notes $$>
                                            </p>
                                        <br />
                                        <$$ } $$>

                                        <div class="project_detail">

                                            <p class="title">Sector :</p>
                                            <p class="value"> <$$= company.sector $$></p>
                                            <span class="break">&nbsp;</span>
                                            
                                            <p class="title">Industry :</p>:
                                            <p class="value"> <$$= company.industry $$></p>
                                            <span class="break">&nbsp;</span>
                                            
                                            <p class="title">Sub Industry :</p>
                                            <p class="value"> <$$= company.subIndustry $$></p>                                        
                                            <span class="break">&nbsp;</span>
                                            
                                            <p class="title">Country :</p>
                                            <p class="value"> <$$= company.country $$></p>      
                                            <span class="break">&nbsp;</span>
                                            
                                            <p class="title">Type :</p>
                                            <p class="value"> <$$= company.companyType $$></p>                                                          
                                            <span class="break">&nbsp;</span>
                                            
                                            <p class="title">Instance Name :</p>
                                            <p class="value"> <$$= comInstanceName $$></p>  
                                            <span class="break">&nbsp;</span>
                                            </br>
                                        </div>

                                        <br />
                                        
                                    </div>

                                </section>

                            </div>
                            <!-- end project-detail sidebar -->

                        </div>
                    </div>
                </div>

    </script>  
	


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
                // populateEmailContent();
                populateComDashboards();
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
