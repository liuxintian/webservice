<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>

        <style type="text/css">

        code {
          font-size: 70%;
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
                            <h3>Account recovery</h3>
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


                    										<!--form class="form-horizontal" id="user-search-update-frm">
                    										  <div class="form-group form-group-lg">
                    										    <div class="col-sm-6 user-search-by-email">
                    										      <input class="form-control" type="email"  data-parsley-required="true" data-parsley-type="email" id="userEmailSearch" placeholder="Enter User email">
                    										    </div>
                    										    										    
                    										  </div>
                    										  
                    										  <div class="form-group form-group-lg">
                    										    <div class="col-lg-6">
                    										      <button type="button" class="btn btn-primary btn-lg" id="search-user-email">Search user</button>
                    										    </div>
                    										    
                    									    
                    										    
                    										  </div>
                    										</form-->




                                         <div id="login " class="animate form">


                                              <div class="separator hidden" id="recovery-sent-success">
                                                  <div>
                                                      <h5> Recovery instruction has been sent to <mark id="userLoginEmail"></mark> </h5>
                                                  </div>
                                                  <div class="separator">&nbsp;</div>
                                              </div>

                                              <section class="login_content">
                                                  <form method="post" id="recovery-init">
                                                      <!--h1>Account recovery</h1-->
                                                      <h1>Recover account</h1>
                                                      
                                                      <div>
                                                          <input type="text" id="recoveryLoginName" name="loginName" class="form-control" placeholder="User login / email" required="" />
                                                      </div>

                                                      <div>
                                                          <input class="btn btn-default submit" value="Initiate account recovery" name="submit" type="submit"/>
                                                      </div>
                                                      <div class="clearfix"></div>
                                                      
                                                  </form>

                                                  
                                                  <!-- form -->
                                              </section>
                                              <!-- content -->
                                          </div>

                                      </div>
                                        
                                        
                  										<div class="col-md-6 user-search-results ">

                                      <!--
                  											    <div class="table-responsive" style="padding-bottom:4px;">
                  											    	<h3 id="user-search-results-msg">User id <mark id="active-user-to-disable" class="active-user-found"></mark> is active</h3>
                  											        <h3 id="user-disabled-results-msg" class="hidden success">User id <mark class="active-user-found"></mark> has been disabled</h3>
                  											    </div>

                  	 											<div class="col-lg-12" >
                  											      <button id="disable-user-record" type="button" class="btn btn-primary btn-lg">Disable User</button>
                  											    </div>	
                                      -->



                                         <div id="fetch-token" class="animate form hidden">


                                              <div class="separator hidden" id="fetch-token-success">
                                                  <div>
                                                      <h5> Recovery URL <mark id="userAcctRecoveryURL"></mark> </h5>
                                                  </div>
                                                  <div class="separator">&nbsp;</div>
                                              </div>

                                              <section class="login_content">
                                                  <form method="post" id="token-fetch-init">
                                                      <!--h1>Account recovery</h1-->
                                                      <h1>Recovery URL:</h1>
                                                      
                                                      <!--div>
                                                          <input type="text" id="userLoginName" name="loginName" class="form-control" placeholder="User login / email" required="" />
                                                      </div-->

                                                      <div>
                                                          <input id="token-fetch-btn" class="btn btn-default submit success" value="Fetch recovery url" name="submit" type="submit"/>
                                                      </div>
                                                      <div class="clearfix"></div>
                                                      
                                                  </form>

                                                  
                                                  <!-- form -->
                                              </section>
                                              <!-- content -->
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
        var RECOVERY_API_ENDPOINT = webBaseUrl+'/master/oapi/';
        var FETCH_TOKEN_ENDPOINT = webBaseUrl+'/master/oapi/';

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
          

        var emailMailTockenCallback  = function(data){
           // alert('Email Tockan Received');
           // console.log(data);
            if(data && data.loginName){
                $('#recovery-sent-success').removeClass('hidden');
                $('#userLoginEmail').html(data.loginName);
                $('#fetch-token').removeClass('hidden');
                //$('#recovery-init').addClass('hidden');
            };

            if(data && !_.isUndefined(data.result)  && data.result === false){
              alert('Account not found or invalid');
            }
        };


        var readTockenCallback  = function(data,accountLogin){
           // alert('Email Tockan Received');
            console.log(data);
            $('#fetch-token-success').removeClass('hidden');
            $('#userAcctRecoveryURL').html(populateRecoveryURL(data,accountLogin));

            if(data && !_.isUndefined(data.result)  && data.result === false){
              alert('Account not found or invalid');
            }
        };


        var validateEmail = function (email) { 
            var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        };


    var populateRecoveryURL = function (token,email) {
        // body...
           var currentHost = location.hostname;
           var instanceHost = '';
           if(currentHost.indexOf('sit-cmsmasteradmin-env.elasticbeanstalk.com') > -1){
                instanceHost = 'http://sit-cmsmasteradmin-env.elasticbeanstalk.com/recovery';
           }else if(currentHost.indexOf('cmsmasteradminprod-env.ap-southeast-2.elasticbeanstalk.com')  > -1 ){
                instanceHost = 'http://cmsmasteradminprod-env.ap-southeast-2.elasticbeanstalk.com/recovery';
           }else if(currentHost.indexOf('cms.omnimarkettide.com')  > -1 ){
                instanceHost = 'http://cms.omnimarkettide.com/recovery';
           }else{
                instanceHost = 'http://localhost:8080/cms-master-admin/recovery';
           }
           return instanceHost+'?recovery='+encodeURIComponent(email)+'&emailTocken='+token;
           //$('#instance-form input[data-api-attr=instanceURL]').val(instanceHost);

    };

        $(document).ready(function(){    
         
          $('#recovery-init').submit(function (e){
              e.preventDefault();
              var accountLogin = $('#recoveryLoginName').val();
              if(accountLogin && validateEmail(accountLogin)){

                  $.putJSONErr(RECOVERY_API_ENDPOINT+'users/forgot-password', 
                      {"loginName": accountLogin,"useEmail": true}, function(data){
                          emailMailTockenCallback(data);
                  },function(jqXHR, textStatus, errorThrown){
                    console.log(jqXHR, textStatus, errorThrown);

                    if(jqXHR.status === 400 && !_.isUndefined(jqXHR.responseJSON.result) && jqXHR.responseJSON.result === false ){
                      alert('No Account found or invalid account');
                    }
                  });
              }
          });


          $('#token-fetch-btn').click(function (e){
              e.preventDefault();
              var accountLogin = $.trim($('#userLoginEmail').html());
              if(accountLogin && validateEmail(accountLogin)){

                  $.getJSONErr(API_ENDPOINT+'users/'+encodeURIComponent(accountLogin)+'/fp-token', function(data){
                          readTockenCallback(data,accountLogin);
                  },function(jqXHR, textStatus, errorThrown){
                    console.log(jqXHR, textStatus, errorThrown);

                    if(jqXHR.status === 400 && !_.isUndefined(jqXHR.responseJSON.result) && jqXHR.responseJSON.result === false ){
                      alert('No token found or invalid account');
                    }
                  });
              }
          });


        });
    </script>

        
</body>
</html>