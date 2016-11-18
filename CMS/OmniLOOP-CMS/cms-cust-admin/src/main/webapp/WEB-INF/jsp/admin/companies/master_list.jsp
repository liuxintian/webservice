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
                            <h3>List of Companies</h3>
                        </div>

                        <!-- <div class="title_right">
                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                            <button class="btn btn-default" type="button">Go!</button>
                        </span>
                                </div>
                            </div>
                        </div> -->
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel">
                                <div class="x_content">
									<div class="row">
										<div class="col-md-6 col-sm-6 col-xs-6">
                                    		<a href="company_new_edit.jsp?stab=nstanceTab" class="btn btn-default"> <i class="fa fa-user"></i> Add Company </a>
                                    	</div>
										 <div class="col-md-6 col-sm-6 col-xs-6">
                                            	<div class="form-group clearfix m-t20">
	                                                 <label class="control-label col-md-5 col-sm-5 col-xs-12 m-t10 text-right" for="search-company">Search company : <span class="required">*</span>
	                                                 </label>
	                                                 <div class="col-md-7 col-sm-7 col-xs-12">
	                                                      <select id="search-company" class="select2_single form-control" tabindex="-1">
	                                                         <option value="ba">Balaji</option>
	                                                         <option value="goog">Google</option>
	                                                         <option value="yh">Yahoo</option>
	                                                         <option value="MSN">Microsoft</option>
	                                                     </select>
	                                                 </div>
                                             	</div>
                                        </div>
									</div>
                                    <!--div class="row">

                                        <div class="col-md-12 col-sm-12 col-xs-12 text-center">
                                            <ul class="pagination pagination-split custom-pagination">
                                                <li><a href="#">A</a></li>
                                                <li><a href="#">B</a></li>
                                                <li><a href="#">C</a></li>
                                                <li><a href="#">D</a></li>
                                                <li><a href="#">E</a></li>
                                                <li><a href="#">F</a></li>
                                                <li><a href="#">G</a></li>
                                                <li><a href="#">H</a></li>
                                                <li><a href="#">I</a></li>
                                                <li><a href="#">J</a></li>
                                                <li><a href="#">K</a></li>
                                                <li><a href="#">L</a></li>
                                                <li><a href="#">M</a></li>
                                                <li><a href="#">N</a></li>
                                                <li><a href="#">O</a></li>
                                                <li><a href="#">P</a></li>
                                                <li><a href="#">Q</a></li>
                                                <li><a href="#">R</a></li>
                                                <li><a href="#">S</a></li>
                                                <li><a href="#">T</a></li>
                                                <li><a href="#">U</a></li>
                                                <li><a href="#">V</a></li>
                                                <li><a href="#">W</a></li>
                                                <li><a href="#">X</a></li>
                                                <li><a href="#">Y</a></li>
                                                <li><a href="#">Z</a></li>
                                              </ul>
                                        </div>
                                    </div-->
                                    
                                    <div class="clearfix"></div>
                                        <div id="company_tiles"></div>
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
	

		

    <script type="text/template" id="company_tiles_template">     


        <$$ _.each(characters, function(copmany,index){ $$>

                <div class="col-md-4 col-sm-4 col-xs-12 animated fadeInDown">
                    <div class="well profile_view">
                        <div class="col-sm-12">
                            <!-- <h4 class="brief"><a href="#"><i>Telecom Industry</i></a></h4> -->
                            <div class="left col-xs-7">
                                <$$ if(copmany.companyName){ $$>
                                <h2> <$$= copmany.companyName $$> </h2>
                                <$$ } $$>
                                <p><strong>Country : </strong> <$$= copmany.country $$> </p>
                                <p><strong>No of Shareholders : </strong> <$$= copmany.companyShareholders $$> </p>
                                <p><strong>OMT Client : </strong> <$$= copmany.isRegistered $$> </p>
                                <p><strong>Sector : </strong> <$$= copmany.sector $$> </p>
                                <p><strong>Industry  : </strong> <$$= copmany.industry $$> </p>
                                <p><strong>Sub-Industry  : </strong> <$$= copmany.subIndustry $$> </p>
                                <p><strong>Valid Until  : </strong> <$$= copmany.validUntil $$> </p>
                            </div>
                            <div class="right col-xs-5">
                                <div class="text-center"><img src="<%= baseURL %>/assets/images/user.png" alt="" class="img-circle img-responsive"></div>
                                <div class="m-t5 text-center"><a href="<$$= copmany.companyURL $$>" target="_blank" class="web-link"><$$= copmany.companyURL $$></a></div>
                            </div>
                        </div>
                        <div class="col-xs-12 bottom">
                            <div class="col-xs-12 col-sm-12 emphasis">
                                <p><strong>MC ($100)</strong></p>
                            </div>
                            <div class="col-xs-12 col-sm-12 emphasis">
                                <a class="btn btn-primary btn-xs" href="company_new_edit.jsp?cid=<$$= copmany.id $$>"> <i class="fa fa-edit"></i> Edit</a>
                                <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View</button>
                                <a href="company_new_edit.jsp?stab=nstanceTab&cid=<$$= copmany.id $$>" class="btn btn-primary btn-xs"> <i class="fa fa-pencil"></i> Instance</a>
                                <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-user"></i> </button>
                            </div>
                        </div>
                    </div>
                </div>

            <$$ }); $$>

    </script>



    <script>

        var apiEnds = [] , apiRsps = {};
        var getCompanies = function(path) {
            // body...
            //return $.ajax({ url: 'http://localhost:8080/cms-master-api/companies' });
            apiEnds.push(path);
            return $.ajax({
              url: "http://localhost:8080/cms-master-api/"+path
            });
        };

        var getCompany = function(path,id) {
            // body...
            //return $.ajax({ url: 'http://localhost:8080/cms-master-api/companies' });
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: "http://localhost:8080/cms-master-api/"+path+'/'+id
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
          

          $(document).ready(function () {

                //isolated single
                
                getCompanies('companies').then(function (adata) {
                    // body...

                    injectTpl('#company_tiles',adata).then(function () {
                        // Do something brilliant here!
                        //alert("Do something brilliant here!");
                        $(".select2_single").select2({
                            placeholder: "Select a state",
                            allowClear: true
                        });
                    });
                });

                //Parallel isolated
                // $.when(
                //     getCompanies('companies'),
                //     getCompany('companies',1)
                // ).done(function( ajaxResponse1,ajaxResponse2 ) {

                //     injectTpl('#company_tiles',{'node':'tiles','others':ajaxResponse2}).then(function () {
                //         // Do something brilliant here!
                //         //alert("Do something brilliant here!");
                //         $(".select2_single").select2({
                //             placeholder: "Select a state",
                //             allowClear: true
                //         });
                //     });
                // });

                //Sequential dependent
                // getCompanies('companies').then(chainedStepTwo).then(function( ajaxResponse1 ) {
                //     console.log(ajaxResponse1);//console.log(ajaxResponse2);
                //     apiRsps[_.last(apiEnds)] = ajaxResponse1;
                //     injectTpl('#company_tiles',{'node':'tiles','others':'none'}).then(function () {
                //         // Do something brilliant here!
                //         //alert("Do something brilliant here!");
                //         $(".select2_single").select2({
                //             placeholder: "Select a state",
                //             allowClear: true
                //         });
                //     });
                // });

          });


    </script>

        
</body>
</html>