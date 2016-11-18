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
                            <h3>Disable user by email/login</h3>
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
                            	<div class="loading-sec hidden"></div>
                                <div class="x_content">

                                    	<div class="row"> 
                                        <!-- div id="company_tiles"></div -->
                                        
                                        <!-- p class="text-center">
                                        	<a target="_blank" href="<%= baseURL %>/master-admin/users/csv" class="btn btn-success btn-lg">Download Users in CSV file</a>
                                        </p-->
                                        
                                       
                                       <div class="col-sm-6 user-search-forms">
										<form class="form-horizontal" id="user-search-update-frm">
										  <div class="form-group form-group-lg">
										    <!-- label class="col-sm-2 control-label" for="userEmailSearch">Large label</label -->
										    <div class="col-sm-6 user-search-by-email">
										      <input class="form-control" type="email"  data-parsley-required="true" data-parsley-type="email" id="userEmailSearch" placeholder="Enter User email">
										    </div>
										    										    
										  </div>
										  
										  <div class="form-group form-group-lg">
										    <div class="col-lg-6">
										      <button type="button" class="btn btn-primary btn-lg" id="search-user-email">Search user</button>
										    </div>
										    
									    
										    
										  </div>
										</form>
                                        </div>
                                        
                                        
										<div class="col-md-6 user-search-results hidden">


											    <div class="table-responsive" style="padding-bottom:4px;">
											    	<h3 id="user-search-results-msg">User id <mark id="active-user-to-disable" class="active-user-found"></mark> is active</h3>
											        <h3 id="user-disabled-results-msg" class="hidden success">User id <mark class="active-user-found"></mark> has been disabled</h3>
											    </div>

	 											<div class="col-lg-12" >
											      <button id="disable-user-record" type="button" class="btn btn-primary btn-lg">Disable User</button>
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
    
        <script type="text/template" id="user_attributes_template">     
            <$$ _.each(characters, function(menuItems,index){ $$>

					<tr>
						<th scope="row"><code>.warning</code></th>
						<td>Indicates a warning that might need attention</td>
					</tr>

            <$$ }); $$>
        </script>

    <script>

        var apiEnds = [] , apiRsps = {};
        var getUserDetails = function(path) {
            // body...
            //return $.ajax({ url: 'http://localhost:8080/cms-master-api/companies' });
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };

        var clearTpl = function(id){
            $( id ).empty();
            //$( id ).detach();
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
          
          var populateUserData = function(userEmail){
              $('.x_panel').find('.loading-sec').removeClass('hidden');
              
              getUserDetails('users/'+encodeURIComponent(userEmail)+'/login-name-inuse').then(function (adata) {
                  $('.x_panel').find('.loading-sec').addClass('hidden');
                  // body...

                  /* injectTpl('#user_attributes',adata).then(function () {
                      $('.x_panel').find('.loading-sec').addClass('hidden');
                      // Do something brilliant here!
                      //alert("Do something brilliant here!");
                      // here code data will be loaded than run this code 
                      
                      // here code data will be loaded 
                  }); */
                  if(adata.inUse){
                	  $('.active-user-found').html(adata.loginName);
                	  $('.user-search-results').removeClass('hidden');
                	  $('#user-search-results-msg').removeClass('hidden');
                  }else{
                	  $('.user-search-results').addClass('hidden');
                	  alert('No user found with this email');
                  }
                  
                  
              }, function(reason) {
                     $('.x_panel').find('.loading-sec').addClass('hidden');
                      //console.log(reason); // Error!
                  });

            };          
          
            var deactiveUserData = function(userEmail){
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                
                	$.putJSON(API_ENDPOINT+'users/'+encodeURIComponent(userEmail)+'/update',null, function(adata){
                	    $('.x_panel').find('.loading-sec').addClass('hidden');
                		$('#user-search-results-msg').addClass('hidden');
                		$('#user-disabled-results-msg').removeClass('hidden');
                		$('#disable-user-record').addClass('hidden');
                	});
                
              };             
          
          //clearTpl('#user_attributes');
          //populateIRMenu(companyIrMenuIdUpdt);
          $('#search-user-email').click(function(e){
        	  e.preventDefault();
        	  //clearTpl('#user_attributes');
        	  $('#disable-user-record').removeClass('hidden');
        	  $('#user-disabled-results-msg').addClass('hidden');
        	  $('.user-search-results').addClass('hidden');
        	  var formValidate = $("#user-search-update-frm").parsley().validate();
        	  if(formValidate){
        		  populateUserData($('#userEmailSearch').val());
        	  }else{
            	  alert('Not a valid email format');
              }
        	  
          });

          $('#disable-user-record').click(function(e){
        	  e.preventDefault();
        	  //clearTpl('#user_attributes');
        	  //var formValidate = $("#user-search-update-frm").parsley().validate();
        	  if($('#active-user-to-disable').html() != ''){
        		  //populateUserData($('#userEmailSearch').val());
        		  deactiveUserData($('#active-user-to-disable').html());
        		  
        	  }else{
            	  alert('Error ');
              }
        	  
          });
          
    </script>

        
</body>
</html>