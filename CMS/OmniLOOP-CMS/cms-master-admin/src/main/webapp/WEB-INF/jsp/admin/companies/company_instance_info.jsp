<div class="clearfix">
    <div class="row">
        <div class="col-md-12 col-sm-12 col-xs-12">
            <div class="x_panel pos-rel js-form-spinner">
                <div class="loading-sec hidden"></div>
                <div class="x_title">
                    <h2 class="pull-left" id="instance-head-text"><span>Create  Instance Info</span> <small>* field are mandatory </small></h2>
                    <h3 class="pull-right" id="instance-head-state" ><p class="label label-success"></p></h3>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <br />
                    <div class="row">
						<div class="col-md-9 col-sm-9 col-xs-9 pull-left">
	                        <form id="instance-form" class="form-horizontal form-label-left">
	                            <div class="form-group">
	                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="instance-name">Name of the instance <span class="required">*</span>
	                                </label>
	                                <div class="col-md-6 col-sm-6 col-xs-12">
	                                    <input data-api-attr="instanceName" type="text" id="instance-name" data-parsley-type="alphanum" data-parsley-alphanum-message="Please enter a alphanumeric instance name without white spaces and special characters" data-parsley-required="true" placeholder="Please enter unique name for instance"  class="form-control col-md-7 col-xs-12">
	                                    <input type="hidden" id="instance-name-old" data-api-attr="instanceNameOld" value="">
	                                </div>
	                            </div>
	                            <div class="form-group hidden">
	                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span></label>
	                                <div class="col-md-6 col-sm-6 col-xs-12">
	                                
	                                    <div id="gender" class="btn-group" data-toggle="buttons">
	                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                                            <input data-api-attr="status" type="radio" name="status-instance" value="20"> &nbsp; Enabled &nbsp;
	                                        </label>
	                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                                            <input data-api-attr="status" type="radio" name="status-instance" value="30" checked=""> Disabled
	                                        </label>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="form-group">                        
	                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="instance-url">
	                                    Instance App URL <span class="required">*</span>
	                                </label>
	                                <div class="col-md-6 col-sm-6 col-xs-12">
	                                <input data-api-attr="instanceURL" type="text" id="instance-url" name="website" data-parsley-required="true" placeholder="www.website.com" class="form-control col-md-7 col-xs-12">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="instance-valid-until">Valid until <span class="required">*</span>
	                                </label>
	                                <div class="col-md-6 col-sm-6 col-xs-12">
	                                    <input data-api-attr="validUntil" readonly id="instance-valid-until" class="disabled date-picker form-control col-md-7 col-xs-12 new-date-add" placeholder="DD/MM/YYYY" data-parsley-required="true" type="text">
	                                </div>
	                            </div>
	                            <div class="form-group">                        
	                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="message">
	                                    Notes  
	                                </label>
	                                <div class="col-md-6 col-sm-6 col-xs-12">
	                                <textarea data-api-attr="notes" id="message" class="form-control" name="message"></textarea>
	                                </div>
	                            </div>
	                            
	                            <div class="form-group">
	                                <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
	                                    <button type="reset" class="btn btn-primary">Cancel</button>
	                                    <button data-attr="submit-btn" type="submit" class="btn btn-success">Submit</button>
	                                </div>
	                            </div>
	                        </form>
						</div>
						
                        <!-- div class="col-md-3 col-sm-3 col-xs-3 instance-status-controller" style="border-left: 1px solid;min-height: 330px;">
                    	
							 <div class="clearfix text-center">
			                      <h5 id="instance-status-activate-txt" class="" style="display: none;"> Activate company instance</h5>
			                      <h5 id="instance-status-deactivate-txt" class="bg-danger" style="display: none;padding: 5px;"> Deactivate company instance (Once deactivated access to instance admin will be blocked and company related data will not be served in the mobile app)</h5>
			                      <div class="ln_solid"></div>
			                 </div>
                    	
                    		<form id="instance-form-enable-disable"  method="post" class="form-horizontal form-label-left">

								<div class="form-group">
	                                <div class="col-md-12 col-sm-12 col-xs-12">
	                                	<input  id="instance-status-val" data-api-attr="cmp-status" type="hidden" name="status-ofinstance" value="" >
	                                    <button id="instance-deactivate-cmd" data-toggle="confirmation" data-placement="top" data-title="Are you absolutely sure?" type="button" class="btn btn-warning btn-block">Deactivate instance</button>
	                                    <button id="instance-activate-cmd" data-toggle="confirmation" data-placement="top" data-title="Are you absolutely sure?" data-attr="submit-btn" type="button" class="btn btn-success btn-block">Activate instance</button>
	                                </div>
	                            </div>

                    		</form>
                        </div -->
                    </div>
                    
                    <div class="ln_solid"></div>

                </div>
            </div>
        </div>
    
    </div>
</div>


<!-- Instance form Success update model   -->
<span data-toggle="modal" data-target=".instance-form-success-model" class="hidden js-instace-success-model"></span>
<div class="modal fade instance-form-success-model" tabindex="-1" role="dialog" aria-hidden="true">
   <div class="modal-dialog modal-md">
       <div class="modal-content">

           <div class="modal-header no-border">
               <button type="button" class="close js-instance-close-model" data-dismiss="modal"><span aria-hidden="true">X</span>
               </button>
               <!-- <h4 class="modal-title">form update</h4> -->
           </div>
              <div class="modal-body">
                  <div class="clearfix text-center">
                      <h4>The  Instance information has  been Created .</h4>
                      <div class="m-t20 text-center">
                           <button type="button" class="btn btn-default js-instance-close-model" data-dismiss="modal">Close</button>
                      </div>
                  </div>
              </div>
       </div>
   </div>
</div>
<!-- Instance form Success update model  -->

<!-- Instance form edit update model   -->
<span data-toggle="modal" data-target=".instance-form-edit-model" class="hidden js-instace-edit-model"></span>  
<div class="modal fade instance-form-edit-model" tabindex="-1" role="dialog" aria-hidden="true">
     <div class="modal-dialog modal-md">
         <div class="modal-content">

             <div class="modal-header no-border">
                 <button type="button" class="close js-instanceEdit-close-model" data-dismiss="modal"><span aria-hidden="true">X</span>
                 </button>
                 <!-- <h4 class="modal-title">form update</h4> -->
             </div>
                <div class="modal-body">
                    <div class="clearfix text-center">
                        <h4>The  Instance information has  been Updated .</h4>
                        <div class="m-t20 text-center">
                             <button type="button" class="btn btn-default js-instanceEdit-close-model" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
         </div>
     </div>
 </div>
<!-- Instance form edit update model  -->




<script type="text/javascript">
var companyId = '';
var instanceId;
var companyInstanceCreated = false;
var companyInstanceLaunched = false;


  var updateInstanceForm = function(){
    var instanceAttrs = ['instanceName','status','instanceURL','notes', 'validUntil'];
    var dataString = {};

       _.each(instanceAttrs, function(key){
            if(key == 'status'){
                //dataString[key] =  $('#instance-form  input[name=status-instance][data-api-attr='+key+']:checked').val(); 
                //dataString['status'] = 20;
                //dataString[key] = parseInt(dataString[key], 10);  
            }else if(key == 'validUntil' &&
                     moment($('#instance-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                dataString[key] =  moment(new Date($('#instance-form .form-control[data-api-attr='+key+']').val()),DATE_OUT_FORMAT).valueOf();
            }else{
                dataString[key] =  $('#instance-form .form-control[data-api-attr='+key+']').val();    
            }
       });
       if(!_.isUndefined(data['status'])){ delete object['status']; }
       $('#instance-form').closest('.js-form-spinner').find('.loading-sec').removeClass('hidden');
        $.putJSON(API_ENDPOINT+'companies/'+companyId+'/instances/'+instanceId, dataString, putInstanceFormCallback);
  };

  var putInstanceFormCallback = function(data){
        if(data){
            $('#instance-form').closest('.js-form-spinner').find('.loading-sec').addClass('hidden');
        }
        //alert('Put call show');
        $('.js-instace-edit-model').trigger("click");
        
   };

   var validateUserAddForm = function () {
        
        var validFrm = $('#instanceAccess-add-form').parsley().isValid();
        //var errorExists = $('#instanceAccess-add-form').find('.danger-text').length ? true : false;

        if (true === validFrm) {
            $('.bs-callout-info').removeClass('hidden');
            $('.bs-callout-warning').addClass('hidden');
        } else {
            $('.bs-callout-info').addClass('hidden');
            $('.bs-callout-warning').removeClass('hidden');
        }
        return validFrm;
    };

    var postInstaceAccessFormCallback = function(){
        //alert('Thanks your instance account has been created');
        $('#instanceAccess-add-form').closest('.x_panel').find('.loading-sec').addClass('hidden');
        $('.js-instncAccess-form-model').click();
        $('#instanceAccess-add-form #user-name').attr('disabled','disabled');
        $('#instanceAccess-add-form #user-pwd').attr('disabled','disabled');
        $('#instanceAccess-add-form #user-CnfPwd').attr('disabled','disabled');
        $('#instanceAccess-add-form .btn[data-attr=submit-btn]').attr('disabled','disabled');
        populateInstanceUserMenu(blockUnblockUsers);
    };

    var validateFrontInstanceForm = function () {
        if (true === $('#instance-form').parsley().isValid()) {
            $('.bs-callout-info').removeClass('hidden');
            $('.bs-callout-warning').addClass('hidden');
        } else {
            $('.bs-callout-info').addClass('hidden');
            $('.bs-callout-warning').removeClass('hidden');
        }
    };
    var postInstancesFormCallback = function(data){
        if(data){

            $('#instance-form').closest('.js-form-spinner').find('.loading-sec').addClass('hidden');
            if(data.id){
            	companyInstanceCreated = true;
                $('#instance-form').removeClass('instance-add-form').addClass('instance-edit-form');
                $('#instance-form.instance-edit-form').off('submit')
                 $('#instance-form.instance-edit-form').submit(onEditInstanceSubmit);
                $('li > a#instance-access').parent().removeClass('hidden');
            }
                comapnyInstaceIdFun(data.cpId);
        }
        //alert('Added new Instance');
        $('#instance-head-text >span').text('Update Instance Info');
        $('#instance-head-state').html("<p class=\"label label-success\">Active</p>");
        $('#instance-form .btn[data-attr=submit-btn]').text('Update');
        $('#instance-form .btn[data-attr=submit-btn]').attr('data-attr', 'update-btn');
        $('.js-instace-success-model').trigger("click");
        console.log(data); // show response 
         instanceId = data.id; // take Instace ID for Updation same page 
         getComaonyStatusForInstance();
    };


    var activateInstance = function (companyId,insId) {
        // body...
        $.putJSON(API_ENDPOINT+'companies/'+companyId+'/activate', {}, function (data) {
            // body...
            if(data && data.activated === true){
            	companyInstanceLaunched = true;
                $('.js-activated-instaceForm').attr('disabled',true).addClass( "disabled" ).attr('data-value','').html('Instance Launched');
                 $('.js-instace-access-form, #jsInstanceActivatedlist').show();
                  $('#instance-form #instance-name').prop('disabled', true);
                //$('.js-activated-instaceForm').html('Instance Activated');
                  getComaonyStatusForInstance();
            }else{
                $('.js-activated-instaceForm').removeAttr('disabled').removeClass( "disabled" ).attr('data-value',insId);
                $('.js-instace-access-form, #jsInstanceActivatedlist').hide();
            }

        });
    };

    var getCompanyInstanceFormCallback = function(data){
        $('#instance-head-text >span').text('Update Instance');
        $('#instance-form .btn[data-attr=submit-btn]').text('Update');
        $('#instance-form .btn[data-attr=submit-btn]').attr('data-attr', 'update-btn');  
        $('#instance-form input[data-api-attr=instanceCompanyName]').val(data.companyName);
        $('#instance-form input[data-api-attr=instanceName]').val(data.instanceName);
        $('#instance-form input[data-api-attr=instanceNameOld]').val(data.instanceName);
        $('#instance-form textarea[data-api-attr=notes]').val(data.notes);
        var instanceStatusVal = data.status;
        if(instanceStatusVal == 20){
            $('#instance-form input[name=status-instance][value=20]').prop('checked',true);
            $('#instance-form input[name=status-instance][value=30]').prop('checked',false);
            $('#instance-form input[name=status-instance]').closest('label').removeClass('active');
            $('#instance-form input[name=status-instance][value=20]').closest('label').addClass('active');
        }else if(instanceStatusVal == 30){
            $('#instance-form input[name=status-instance][value=30]').prop('checked',true);
            $('#instance-form input[name=status-instance][value=20]').prop('checked',false);
            $('#instance-form input[name=status-instance]').closest('label').removeClass('active');
            $('#instance-form input[name=status-instance][value=30]').closest('label').addClass('active');
        };
        

        if(data.instanceURL){
            $('#instance-form input[data-api-attr=instanceURL]').val(data.instanceURL);
        }

        if(data.validUntil){
            $('#instance-form input[data-api-attr=validUntil]').val(
                moment(data.validUntil).format(DATE_OUT_FORMAT)
            );
        }else{
            //TO BE REMOVED LATER
            $('#instance-form input[data-api-attr=validUntil]').val(
                moment(new Date().getTime()).format(DATE_OUT_FORMAT)
            );

        }
        $('#instance-form textarea[data-api-attr=notes]').val(data.notes);
        datePlugin();
    };

    var comapnyInstaceIdFun = function(companyId){
          // var url = API_ENDPOINT+'companies/'+companyId+'/instances';

          //INSTANCE RELATED FEATURES
          $.getJSON(API_ENDPOINT+'companies/'+companyId+'/instances')
          	.done(function(data){
              getCompanyInstanceFormCallback(data);

               if (!data) {
                   $('#instance-form').addClass('instance-add-form');
                   $('#instance-form.instance-add-form').off('submit');
                   $('#instance-form.instance-add-form').submit(onAddInstanceSubmit);
                  console.log('instance data show not show ====');
                  $('#instance-head-state').html("");
              }else{
            	  companyInstanceCreated = true;
                  $('#instance-form').addClass('instance-edit-form').removeClass('instance-add-form');
                  $('#instance-form.instance-edit-form').off('submit');
                  $('#instance-form.instance-edit-form').submit(onEditInstanceSubmit);
                  instanceId = data.id;
                  console.log('instance data show '+data.id);
                  if(!_.isUndefined(data.activated) && (data.activated === false)){
                	  companyInstanceLaunched = false;
                      $('.js-activated-instaceForm').removeAttr('disabled').removeClass( "disabled" ).attr('data-value',data.id);
	
                      $('.js-activated-instaceForm').on('click',function () {
                          var insId = $(this).attr('data-value');
                          if(insId){
                              $('.js-activated-instaceForm').html('Activation being Processed...');
                              activateInstance(companyId,insId);
                          }
                      });
                  }else{
                	  companyInstanceLaunched = true;
                      $('.js-activated-instaceForm').html('Instance Launched');
                      
                  }

                  if(instanceId){
                      $('li > a#instance-access').parent().removeClass('hidden');
                  }


                  if(instanceId && (data.activated && data.activated === true) ){
                      $('#instance-form #instance-name').prop('disabled', true);
                  }else{
                      $('.js-instace-access-form, #jsInstanceActivatedlist').hide();
                  }

              }
               
               getComaonyStatusForInstance();

          })
		 .fail(function( jqxhr, textStatus, error ) {
		    //var err = textStatus + ", " + error;
		    //console.log( "Request Failed: " + err );
			 console.log( jqxhr);
			 if(jqxhr.status == 404){
                 $('#instance-form').addClass('instance-add-form');
                 $('#instance-form.instance-add-form').off('submit');
                 $('#instance-form.instance-add-form').submit(onAddInstanceSubmit);
                console.log('instance data show not show ====');
                $('#instance-head-state').html("");				 
                $('.instance-status-controller').hide();
			 }
			 
		  });
          
          
   
          
          
    };

    
	var getComaonyStatusForInstance = function (data){
		
		var activationCmdFrom = function(data){
			
    		$('#instance-form-enable-disable #instance-status-val').val(data.status);
        	
            if(!_.isUndefined(data.status) && (data.status === 20)){   
              $('li > a#instance-access').parent().removeClass('hidden');  	
          	  $('#instance-form-enable-disable #instance-deactivate-cmd').prop('disabled', false).removeClass( "disabled" ).show();	
          	  $('#instance-form-enable-disable #instance-activate-cmd').prop('disabled', true).addClass( "disabled" ).hide();	
          	  $('#instance-status-activate-txt').hide();
          	  $('#instance-status-deactivate-txt').show();	
          	  $('#instance-head-state').html("<p class=\"label label-success\">Active</p>");
            }else{
                //$('.js-activated-instaceForm').html('Instance  Activated');
              $('li > a#instance-access').parent().addClass('hidden');  
          	  $('#instance-form-enable-disable #instance-activate-cmd').prop('disabled', false).removeClass( "disabled" ).show();
          	  $('#instance-form-enable-disable #instance-deactivate-cmd').prop('disabled', true).addClass( "disabled" ).hide();	
          	  $('#instance-status-activate-txt').show();
          	  $('#instance-status-deactivate-txt').hide();	
          	  $('#instance-head-state').html("<p class=\"label label-danger\">Inactive</p>");
            }	
		};
		

        //INSTANCE RELATED ACTIVATION 
        if(data){
        	activationCmdFrom(data);
        }else{
	        $.getJSON(API_ENDPOINT+'companies/'+companyId, function(data){
        		activationCmdFrom(data);
        		if(companyInstanceCreated && companyInstanceLaunched){
        			$('.instance-status-controller').show();
        		}else{
        			$('.instance-status-controller').hide();
        		}
        		
        	});
        }
		
		
	};
    
	var deactivateReactivate = function(){
		event.preventDefault();
		
		
		/*var apiEndPointInstanceCtrl = '';
		var currentInstanceStatusVal = $('#instance-form-enable-disable #instance-status-val').val();
		currentInstanceStatusVal = parseInt(currentInstanceStatusVal, 10);  

	    //$('#instance-form').closest('.js-form-spinner').find('.loading-sec').removeClass('hidden');
	    
		if(currentInstanceStatusVal == 20){
			apiEndPointInstanceCtrl = API_ENDPOINT+'companies/'+companyId+'/deactivate';
		}else if(currentInstanceStatusVal == 30){
			apiEndPointInstanceCtrl = API_ENDPOINT+'companies/'+companyId+'/reactivate';
		}
		
		if(apiEndPointInstanceCtrl){
			$('#instance-form').closest('.js-form-spinner').find('.loading-sec').removeClass('hidden');
			$.putJSON(apiEndPointInstanceCtrl, {}, function(data){
				$('#instance-form').closest('.js-form-spinner').find('.loading-sec').addClass('hidden');
				getComaonyStatusForInstance(data);
				window.location.href = window.location.pathname+'?cid='+companyId+'&stab=nstanceTab';
			});
		}*/
		
	};
	
	
	var deactivateReactivateCall = function(){
		//event.preventDefault();
		var apiEndPointInstanceCtrl = '';
		var currentInstanceStatusVal = $('#instance-form-enable-disable #instance-status-val').val();
		currentInstanceStatusVal = parseInt(currentInstanceStatusVal, 10);  

	    //$('#instance-form').closest('.js-form-spinner').find('.loading-sec').removeClass('hidden');
	    
		if(currentInstanceStatusVal == 20){
			apiEndPointInstanceCtrl = API_ENDPOINT+'companies/'+companyId+'/deactivate';
		}else if(currentInstanceStatusVal == 30){
			apiEndPointInstanceCtrl = API_ENDPOINT+'companies/'+companyId+'/reactivate';
		}
		
		if(apiEndPointInstanceCtrl){
			$('#instance-form').closest('.js-form-spinner').find('.loading-sec').removeClass('hidden');
			$.putJSON(apiEndPointInstanceCtrl, {}, function(data){
				$('#instance-form').closest('.js-form-spinner').find('.loading-sec').addClass('hidden');
				getComaonyStatusForInstance(data);
				window.location.href = window.location.pathname+'?cid='+companyId+'&stab=nstanceTab';
			});
		}		
	
	};

	
    var addInstanceStatusUpdateBtnConfirmation = function(){
        var ele = $('[data-toggle=confirmation]');    	
		
        var msg = "Are you sure you want to proceed?";
        ele.confirmation({
            title: msg,
            btnOkClass: 'btn-primary',
            btnCancelClass : 'btn-default',
            onConfirm : function(event){
                event.preventDefault();
                deactivateReactivateCall();
            },
            onCancel: function(event){
                event.preventDefault();
                //console.log('No you confirmation not this record');
            }
        });
   };	
	
	
    var populateDefaultInstanceURL = function () {
        // body...
           var currentHost = location.hostname;
           var instanceHost = '';
           if(currentHost.indexOf('sit-cmsmasteradmin-env.elasticbeanstalk.com') > -1){
                instanceHost = 'http://sit-cmscustadmin.elasticbeanstalk.com';
           }else if(currentHost.indexOf('cmsmasteradminprod-env.ap-southeast-2.elasticbeanstalk.com')  > -1 ){
                instanceHost = 'http://cmscustadminprod-env.ap-southeast-2.elasticbeanstalk.com';
           }else if(currentHost.indexOf('cms.omnimarkettide.com')  > -1 ){
                instanceHost = 'http://engage.omnimarkettide.com';
           }else{
                instanceHost = 'http://sit-cmscustadmin.elasticbeanstalk.com';
           }

           $('#instance-form input[data-api-attr=instanceURL]').val(instanceHost);

    };
    
    
</script>


    <script>
        
            var onAddInstanceSubmit = function (e){
                e.preventDefault();
                var formValidateInstance = $('#instance-form').parsley().validate();
                var errorExists = $('#instance-form').find('.danger-text').length ? true : false;

                //validateFrontInstanceForm();
               var instanceAttrs = ['instanceName','status','instanceURL','notes','validUntil'];
               var dataString = {};

               _.each(instanceAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('#instance-form  input[name=status-instance][data-api-attr='+key+']:checked').val();   
                        dataString[key] = parseInt(dataString[key], 10); 
                    }else if(key == 'validUntil'){
                            dataString[key] =  moment($('#instance-form .form-control[data-api-attr='+key+']').val(),DATE_OUT_FORMAT).valueOf();
                        }else{
                        dataString[key] =  $('#instance-form .form-control[data-api-attr='+key+']').val();    
                    }
               });
               if(formValidateInstance == true && !errorExists){
                    dataString['status'] = 20;
                    console.log('formValidateInstance is success');
                    //console.log(validUntil);
                    console.log(dataString);
                    $('#instance-form').closest('.js-form-spinner').find('.loading-sec').removeClass('hidden');
                    $.postJSON(API_ENDPOINT+'companies/'+companyId+'/instances', dataString, postInstancesFormCallback);
                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }
            };

            var onEditInstanceSubmit = function (e){
                e.preventDefault();
                var formValidateInstance = $('#instance-form').parsley().validate();
                var errorExists = $('#instance-form').find('.danger-text').length ? true : false;
                if(formValidateInstance == true && !errorExists){
                    updateInstanceForm();
                }else{
                  //  alert(UI_MESSAGES.form_validation_error);
                  toastNotifications(UI_MESSAGES.form_validation_error);
                }    
            };

        $(document).ready(function () { 
             companyId = getUrlParameter('cid');

            // check instanceNameExists
            $('#instance-name').on('blur', function(){
                var self = this;
                var thisInstanceName = $(this).val().trim();
                var thisOldInstanceName = $('#instance-name-old').val().trim();


                var dataString = '';
                if(thisInstanceName == ''){
                    $(this).addClass('parsley-error');
                    $(self).parent().find('div.danger-text').remove();
                    $(this).after('<div class="danger-text">' +UI_MESSAGES.error_instance_exists_blank+ '</div>');
                    return false;
                }else if(!thisInstanceName.isAlphaNumeric()){
                    $(this).addClass('parsley-error');
                    $(self).parent().find('div.danger-text').remove();
                    $(this).after('<div class="danger-text">' +UI_MESSAGES.error_instance_name_aplhanum+ '</div>');
                    return false;
                }else if(thisInstanceName != thisOldInstanceName){
                    $(this).removeClass('parsley-error');
                    $.getJSON(API_ENDPOINT+'companies/'+companyId+'/instances/instanceNameExists/'+thisInstanceName, dataString, function(data){
                        var instanceResult = data.result
                        if(instanceResult == true){
                            $('#instance-name').addClass('parsley-error');
                            $('#instance-name').after('<div class="danger-text">' +UI_MESSAGES.error_instance_exists_name+ '</div>');
                        }else{
                            $(self).removeClass('parsley-error');
                            $(self).parent().find('div.danger-text').remove();
                        }
                    });
                }
            });
            // var putChckInstanceNameCallback = function(data){
            //     var instanceResult = data.result
            //     if(instanceResult == true){
            //         $('#instance-name').addClass('parsley-error');
            //         $('#instance-name').after('<div class="danger-text">' +UI_MESSAGES.error_instance_exists_name+ '</div>');
            //     }else{

            //         $(self).parent().find('div.danger-text').remove();
            //     }
            // }
            $('#instance-name').on('focus', function(){
                $(this).next('.danger-text').remove();
            });

            if(companyId){
               populateDefaultInstanceURL();
               $('#instance-form').off('submit');
               $('#instance-form').addClass('instance-add-form').submit(onAddInstanceSubmit);
               comapnyInstaceIdFun(companyId);
            }else{
                $('#instance-form').off('submit');
                $('#instance-form').addClass('instance-add-form').submit(onAddInstanceSubmit);
            }
            $('#instanceAccess-add-form').parsley().isValid();
            $('#instanceAccess-add-form').submit(function (e){
                e.preventDefault();
            var formValidate = validateUserAddForm();
            var addUserAttrs = ['userPwd','userCnfPwd','userName', 'loginName'];
                var addUserDataString = {};

                _.each(addUserAttrs, function(key){
                    
                    addUserDataString[key] =  $('.form-control[data-api-attr='+key+']').val();    
                    
                });


                if(formValidate == true){
                    var usrPwd = $('#instanceAccess-add-form #user-pwd').val();
                    var usrCnfPwd = $('#instanceAccess-add-form #user-CnfPwd').val(); 
                    if(usrPwd == usrCnfPwd){
                        //console.log('User Name is ' + dataString.userName);
                       // console.log('Psw  is ' + dataString.userPwd);
                       $('#instanceAccess-add-form').closest('.x_panel').find('.loading-sec').removeClass('hidden');
                       var userCredentials = {"loginName":addUserDataString.loginName , 
                                              "password":addUserDataString.userPwd , 
                                              "name":addUserDataString.userName};
                        //postInstaceAccessFormCallback();
                       $.postJSON(API_ENDPOINT+'companies/'+companyId+'/managers', userCredentials, postInstaceAccessFormCallback);
                    }else{
                        //alert(UI_MESSAGES.error_pwd_match);
                        toastNotifications(UI_MESSAGES.error_pwd_match);
                        $('#instanceAccess-add-form #user-pwd, #instanceAccess-add-form #user-CnfPwd').addClass('parsley-error');
                    }
                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

                });
            /* ==================== Instance Form Update data script here ==================== */
             //$('#instance-form.instance-edit-form').submit(onEditInstanceSubmit);




            /* ==================== Instance Form Update data script here end ==================== */
            /*  ================ Instance Form Add data script here ================ */
            //$('#instance-form.instance-add-form').submit(onAddInstanceSubmit);

            addInstanceStatusUpdateBtnConfirmation();


        });


    </script>