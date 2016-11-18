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
                            <h3>List of Companies </h3>
                        </div>

						

                        <div class="title_right">
                            <!-- div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                            			<button class="btn btn-default" type="button">Go!</button>
                        			</span>
                                </div>
                            </div-->

							<div class="col-md-9 m-t0 col-sm-9 col-xs-9 form-group pull-right">
                                    <form id="search-list-frm"  class=" form-horizontal form-label-left form-inline">

                                        <div class="input-group pull-right">
                                            <select class="js-shorting-placeholder-single form-control" style="width:170px">
                                                <!-- <option value="">Default sort</option> -->
                                                <option value="createdOn">Created On &nbsp;</option>
                                                <option value="lastUpdated">Last Updated &nbsp;</option>
                                                <option value="validUntil">Valid Untill &nbsp;</option>
                                            </select>
                                        </div>

                                        <div class="input-group pull-right">
                                            <input  id="search-list-frm-query" type="text" class="form-control" placeholder="Search for..." style="height: 40px;">
                                            <span class="input-group-btn">
                                                <input type="submit" class="btn btn-default" value="Go!" type="button" style="height: 40px;"/>
                                            </span>
                                        </div>
                                    </form>
                                </div>                            
                            
                        </div>
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel  pos-rel">
                                <div class="loading-sec hidden"></div>
                                <div class="x_content">
                                
                                
                                    <div class="row">
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <a href="<%= baseURL %>/master-admin/ui/company" class="btn btn-default" style="margin-left: 11px; margin-bottom: 15px;"> <i class="fa fa-plus"></i> Add Company </a>
                                            <button type="button" class="btn btn-default refresh-company-list" style="margin-left: 4px; margin-bottom: 15px;"> <i class="fa fa-refresh"></i> Refresh List</button>
                                        </div>
                                        
                                        
                                        <div class="col-md-6 col-sm-6 col-xs-6 ">
		                                   <div class=" text-right paginationSec pull-right">
		                                       <div class="btn-group">
		                                            <a href="#" class="btn btn-success disabled paging-first"><i class="fa fa-angle-double-left"></i> First</a>
		                                            <a href="#" class="btn btn-success br-tr br-br disabled paging-prev"><i class="fa fa-angle-left"></i> Previous</a>
		                                       </div>
		                                       <div class="page-no">
		                                           <span class="paging-info-count"> Page 1 of 4</span>
		                                       </div>
		                                       <div class="btn-group">
		                                            <a href="#" class="btn btn-success br-tl br-bl paging-next">Next <i class="fa fa-angle-right"></i></a>
		                                            <a href="#" class="btn btn-success  paging-last">Last <i class="fa fa-angle-double-right"></i></a>
		                                       </div>
		                                   </div>
		                               </div>
                                        
                                         <!-- <div class="col-md-6 col-sm-6 col-xs-6">
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
                                        </div> -->
                                    </div>
                                    
									<div class="row" style="height:auto;">
                                        
                                        <div class="search-query-msg-block col-md-3 col-sm-3 col-xs-3  pull-left">
                                            <ul class="list-group">
                                              <li class="list-group-item">
                                                <a href="#" id="search-query-clear" class="badge">X</a>
                                                <span id="search-query-msg" style="font-size: 100%;" class="label label-success">search string</span> matches 
                                                <span id="search-query-matches">453</span> records
                                              </li>
                                            </ul>
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


		                            <div  class="clearfix pull-right col-md-6 col-sm-6 col-xs-6" style="margin-top: -25px;">
		                               <div class="row">
		                                   <div class=" text-right paginationSec">
		                                       <div class="btn-group">
		                                            <a href="#" class="btn btn-success disabled paging-first"><i class="fa fa-angle-double-left"></i> First</a>
		                                            <a href="#" class="btn btn-success br-tr br-br disabled paging-prev"><i class="fa fa-angle-left"></i> Previous</a>
		                                       </div>
		                                       <div class="page-no">
		                                           <span class="paging-info-count"> Page 1 of 4</span>
		                                       </div>
		                                       <div class="btn-group">
		                                            <a href="#" class="btn btn-success br-tl br-bl paging-next">Next <i class="fa fa-angle-right"></i></a>
		                                            <a href="#" class="btn btn-success  paging-last">Last <i class="fa fa-angle-double-right"></i></a>
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
                    <div class="well profile_view pos-rel min-he325">
                        <div class="col-sm-12 m-b75">
                            <div class="clearfix">
                                <$$ if(copmany.companyName){ $$>
                                <h2 class="m-l10"> <span class="pos-heading"><$$= copmany.companyName $$></span> </h2>
                                <$$ } $$>
                            </div>
                            <div class="left col-xs-7 m-t0">
                                <p><strong>Country : </strong> <$$= copmany.country $$> </p>
                                <p><strong>No of Shareholders : </strong> <$$= copmany.companyShareholders $$> </p>
                                <p><strong>Sector : </strong> <$$= copmany.sector $$> </p>
                                <p><strong>Industry  : </strong> <$$= copmany.industry $$> </p>
                                <p><strong>Sub-Industry  : </strong> <$$= copmany.subIndustry $$> </p>
                                <$$ if(copmany.companyEmail){ $$>
                                   <p><strong>Email : </strong> <$$= copmany.companyEmail $$> </p>
                                <$$ } $$>
                           </div>
                            <div class="right col-xs-5">
                                <div class="text-center">
                                    <$$ if(!copmany.companyLogoSmall){ $$>
                                        <img src="<%= baseURL %>/assets/images/logo-placeholder.jpg" alt="Company logo" class="img-circle img-responsive">
                                    <$$ } else{$$>
                                        <img src="<%= baseURL %><$$= copmany.companyLogoSmall $$>" alt="Company logo" class="img-circle img-responsive">
                                    <$$ } $$>
                                </div>

                                <$$ if(/^http|https:\/\//i.test(copmany.companyURL)){ $$>
                                    <div class="m-t5 text-center"><a href="<$$= copmany.companyURL $$>" target="_blank" class="web-link"><$$= copmany.companyURL $$></a></div>
                                <$$ } else {$$>
                                    <div class="m-t5 text-center"><a href="http://<$$= copmany.companyURL $$>" target="_blank" class="web-link"><$$= copmany.companyURL $$></a></div>
                                <$$ }$$>

                            </div>
                        </div>
                        <div class="col-xs-12  pos-ablrb <$$= (copmany.status == 20) ? 'bg-success' : 'bg-danger' $$> " style="padding: 9px 0 9px 10px;">
                        	<div class="col-xs-12 col-sm-12  emphasis <$$= (copmany.status == 20) ? 'bg-success' : 'bg-danger' $$> ">	
								<p class="pull-left">
                                <a class="btn btn-primary btn-xs" href="<%= baseURL %>/master-admin/ui/company?cid=<$$= copmany.id $$>"> <i class="fa fa-edit"></i> Edit</a>
                                <a href="<%= baseURL %>/master-admin/ui/company?stab=nstanceTab&cid=<$$= copmany.id $$>" class="btn btn-primary btn-xs"> <i class="fa fa-pencil"></i> Instance</a>
                                <a href="<%= baseURL %>/master-admin/ui/company?stab=irMenu&cid=<$$= copmany.id $$>" class="btn btn-primary btn-xs"> <i class="fa fa-pencil"></i> IR Menu</a>
								</p>
								<$$ if(copmany.status == 20){ $$>
									<p class="pull-right active" style="color: #333;">Active</p>
								<$$ } else {$$>
									<p class="pull-right danger" active" style="color: #333;">Inactive</p>
								<$$ }$$>
                            </div>
                        </div>
                    </div>
                </div>

            <$$ }); $$>

    </script>



    <script>

        var apiEnds = [] , apiRsps = {}, menuTable;
        var getCompanies = function(path,params) {
            // body...
            //return $.ajax({ url: 'http://localhost:8080/cms-master-api/companies' });
            apiEnds.push(path);
            return $.ajax({
            	url: _.isNull(params) ? API_ENDPOINT+path :  API_ENDPOINT+path+'?'+params
            	//url: API_ENDPOINT+path
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
        
	    var populateCompanyList = function(params,pagingCallback){
	            var params_q = (params && params.length) ? params.join('&') : null;
	            $('.x_panel').find('.loading-sec').removeClass('hidden');
	            
                getCompanies('companies',params_q).then(function (adata) {
                    $('.loading-sec').removeClass('hidden');
                    // body...
                    

                    if(adata && adata[0] && (adata[0].total) ){
                        PAGING_VARS.records = (adata[0].total && adata[0].total > 0) ? adata[0].total : PAGING_VARS.records;
                    }else if(_.isArray(adata) && _.isEmpty(adata)){
                        PAGING_VARS.records = 0;
                    }

                    if(_.contains(params, 'page=0')){
                        adjustPagingParams(0);
                    }
                    
                    if(pagingCallback && typeof pagingCallback === 'function'){
                        pagingCallback();
                    }
                    
                    injectTpl('#company_tiles',adata).then(function () {
                        $('.loading-sec').addClass('hidden'); 
                        // Do something brilliant here!
                        //alert("Do something brilliant here!");
                        $(".select2_single").select2({
                            placeholder: "Select a state",
                            allowClear: true
                        });
                    });
                }, function(reason) {
                        $('.loading-sec').addClass('hidden');
                        //console.log(reason); // Error!
                });
	            
	    };
        
		var clearTpl = function(id){
            
            if(!_.isEmpty(menuTable)){
                menuTable.fnDestroy();
                menuTable = null;
            }

            $( id ).empty();
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

        	  populateCompanyList(setUrlForPaging('first'));
        	  
        	  
        	  $(".paging-first").click(function(event){
                  event.preventDefault();
                  
                  if($(this).hasClass("disabled") || !PAGING_VARS.first ){
                      return false;
                  }
                  var params = setUrlForPaging('first');
                  clearTpl('#company_tiles');
                  populateCompanyList(params,adjustPagingParams(-(PAGING_VARS.current_page)));

              });


              $(".paging-prev").click(function(event){
                  event.preventDefault();

                  if($(this).hasClass("disabled") || !PAGING_VARS.prev ){
                      return false;
                  }
                  var params = setUrlForPaging('prev');
                  clearTpl('#company_tiles');
                  populateCompanyList(params,adjustPagingParams(-1));

              });


              $(".paging-next").click(function(event){
                  event.preventDefault();
                  
                  if($(this).hasClass("disabled") || !PAGING_VARS.next ){
                      return false;
                  }
                  var params = setUrlForPaging('next');
                  clearTpl('#company_tiles');
                  populateCompanyList(params,adjustPagingParams(1));

              });


              $(".paging-last").click(function(event){
                  event.preventDefault();

                  if($(this).hasClass("disabled") || !PAGING_VARS.last){
                      return false;
                  }
                  var params = setUrlForPaging('last');
                  clearTpl('#company_tiles');
                  populateCompanyList(params,adjustPagingParams(Math.floor(PAGING_VARS.records/PAGING_VARS.size)- (PAGING_VARS.current_page) ));

              });
        	  
              
              
              $("#search-list-frm").submit(function(event){
                  event.preventDefault();
                  var squery = $('#search-list-frm-query').val();
                  
                  if($.trim(squery)){
                      PAGING_VARS.query = squery;
                      clearTpl('#company_tiles');

                      var parameters = setUrlForPaging('first');

                      populateCompanyList(parameters,function(){
                          adjustPagingParams(-(PAGING_VARS.current_page));
                          if(PAGING_VARS.query){
                              $('.search-query-msg-block').show();
                              $('#search-query-msg').html(PAGING_VARS.query);
                              $('#search-query-matches').html(PAGING_VARS.records);
                          }
                      });                        
                  }
               });
              
              
              $("#search-query-clear").click(function(){
                  $(this).closest('.search-query-msg-block').hide();
                  PAGING_VARS.query = '';
                  PAGING_VARS.sort_by = '';
                  $('#search-list-frm-query').val('');
                  clearTpl('#company_tiles');
                  var params = setUrlForPaging('first');
                  populateCompanyList(params,adjustPagingParams(-(PAGING_VARS.current_page)));  
          	  });

          $('.search-query-msg-block').hide();
              
          $(".refresh-company-list").click(function(){
              $('.search-query-msg-block').hide();
              PAGING_VARS.query = '';
              PAGING_VARS.sort_by = '';
              $('#search-list-frm-query').val('');
              clearTpl('#company_tiles');
              var params = setUrlForPaging('first');
              populateCompanyList(params,adjustPagingParams(-(PAGING_VARS.current_page)));  
      	  });          

          $(".js-shorting-placeholder-single").select2({
              allowClear: true,
              minimumResultsForSearch: Infinity,
              width: 'resolve',
              dropdownAutoWidth : true,                    
              //containerCssClass: 'tpx-select2-container select2-container-sm',
              //dropdownCssClass: 'tpx-select2-drop',
              placeholder: "Select sort criteria "
          }).on("change", function(e) {
              // mostly used event, fired to the original element when the value changes
              //log("change val=" + e.val);
              var selected = $(this).val();
              var squery = $('#search-list-frm-query').val();
              //var parameters = [];

              if($.trim(squery)){
                  PAGING_VARS.query = squery;
              }
              
              var params = setUrlForPaging('first');
              if(selected && PAGING_VARS.sort_by != selected){
                  
                  params.push(['sortBy='+selected]);
                  PAGING_VARS.sort_by = selected;
                  clearTpl('#company_tiles');
                  populateCompanyList(params,function(){
                      if(PAGING_VARS.query){
                          $('.search-query-msg-block').show();
                          $('#search-query-msg').html(PAGING_VARS.query);
                          $('#search-query-matches').html(PAGING_VARS.records);
                      }
                  });
              }

          }).on("select2:unselect", function(e) {
              //alert('select2:unselect');
              PAGING_VARS.sort_by = '';
              var params = setUrlForPaging('first');
              clearTpl('#company_tiles');
              populateCompanyList(params,adjustPagingParams(-(PAGING_VARS.current_page)));  

          });


          $(".js-shorting-placeholder-single").select2("val", "");
          
        	  /*
                //isolated single
                $('.loading-sec').removeClass('hidden');
                getCompanies('companies').then(function (adata) {
                    $('.loading-sec').removeClass('hidden');
                    // body...

                    injectTpl('#company_tiles',adata).then(function () {
                        $('.loading-sec').addClass('hidden'); 
                        // Do something brilliant here!
                        //alert("Do something brilliant here!");
                        $(".select2_single").select2({
                            placeholder: "Select a state",
                            allowClear: true
                        });
                    });
                }, function(reason) {
                        $('.loading-sec').addClass('hidden');
                        //console.log(reason); // Error!
                });
				*/
				
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