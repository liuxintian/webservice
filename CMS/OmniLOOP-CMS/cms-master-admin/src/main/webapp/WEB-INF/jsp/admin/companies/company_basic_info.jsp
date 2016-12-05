<div class="clearfix"></div>
	<div class="row">
	    <div class="col-md-12 col-sm-12 col-xs-12">
	        <div class="x_panel pos-rel js-form-spinner">
	            <div class="loading-sec hidden"></div>
	            <div class="x_title">
	                <h2 class="form-head-text"><span>Fill the correct information</span> <small>* field are mandatory </small></h2>
	                <div class="clearfix"></div>
	            </div>
	            <div class="x_content">
	                <br />
	                <form id="company-add-form" class="form-horizontal form-label-left">
	                    <div class="form-group">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="company-name">Company Name <span class="required">*</span>
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                            <input data-api-attr="companyName" type="text" id="company-name" maxlength="80" placeholder="ex. xyz Telecome" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="autocomplete-custom-append">Operating country: <span class="required">*</span>
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                            <input data-api-attr="country" type="text" name="country" id="autocomplete-custom-append" data-parsley-required="true" class="form-control col-md-10" style="float: left;" />
	                            <div id="autocomplete-container" style="position: relative; float: left; width: 400px; margin: 10px;"></div>
	                        </div>
	                    </div>
	                    
	                    
	                    
	                    <div class="form-group">                        
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website-url">
	                            Website URL <span class="required">*</span>
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                        <input data-api-attr="companyURL"  type="text" id="website-url" name="website" data-parsley-required="true" placeholder="www.website.com" class="form-control col-md-7 col-xs-12">
	                        </div>
	                    </div>

	                    <div class="form-group">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="company-email">
	                            Company Email <span class="required">*</span>
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                        <input data-api-attr="companyEmail"  type="text" id="company-email" name="email" data-parsley-type="email" placeholder="info@company-name.com" class="form-control col-md-7 col-xs-12">
	                        </div>
	                    </div>

	                    <div class="form-group">                        
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="shareRegistry">
                                Share Registry 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select data-api-attr="shareRegistryId" id="shareRegistry" name="" class="form-control col-md-7 col-xs-12">
                                    <option>Select One</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">                        
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="companyTypeId">
                                Company Type 
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <select data-api-attr="companyType" id="companyTypeId" name="" class="form-control col-md-7 col-xs-12">
                                    <option>Select One</option>
                                </select>
                            </div>
                        </div>
	                    <div class="form-group">                        
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website-url">
	                            Company Ticker <span class="required">*</span>
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                        <input data-api-attr="companyTicker"  type="text" id="company-ticker" name="ticker" data-parsley-required="true" placeholder="" class="form-control col-md-7 col-xs-12">
	                        <input type="hidden" id="company-ticker-old" data-api-attr="companyTickerOld" value="">
	                        </div>
	                    </div> 
	                    <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="tagRole">Tags
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <select id="tagRole"  class="form-control col-md-7 col-xs-12 select2-hidden-accessible" multiple="" tabindex="-1" aria-hidden="true"></select>
                                     <input type="text"  data-api-attr="tagRole" class="hidden form-control" />
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
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="message-2">
	                            Description (20 chars min, 400 max) : <span class="required">*</span>
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                        <textarea data-api-attr="companyDescription" id="message-2" data-parsley-required="true" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="400" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
	                        </div>
	                    </div>
	                    <div class="ln_solid"></div>
	                    <div class="form-group">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12">Is Registered:</label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                        
	                            <div id="isRegistered" class="btn-group" data-toggle="buttons">
	                                <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                                    <input data-api-attr="isRegistered" type="radio" name="registered" value="true" checked=""> &nbsp; Registered &nbsp;
	                                </label>
	                                <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
	                                    <input data-api-attr="isRegistered" type="radio" name="registered" value="false" /> Unregistered
	                                </label>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="form-group">                 
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="no-of-shares">
	                            Number of shares: 
	                        </label>
	                        <div class="col-md-3 col-sm-3 col-xs-12">
	                        <input data-api-attr="companyShareholders" type="number" id="no-of-shares" name="number_share" onkeypress="return isNumeric(event)" oninput="maxLengthCheck(this)" maxlength = "11" min = "1" max = "10000000000" class="form-control col-md-7 col-xs-12">
	                        </div>
	                    </div>
	                    <div class="form-group">    
	                    <label class="control-label col-md-3 col-sm-3 col-xs-3" for="size-market">
	                            Size (market cap): 
	                        </label>
	                        <div class="col-md-3 col-sm-3 col-xs-12">
	                        <input data-api-attr="companySize" type="number" id="size-market" name="number_share" onkeypress="return isNumeric(event)" oninput="maxLengthCheck(this)" maxlength = "11" min = "1" max = "10000000000" class="form-control col-md-7 col-xs-12">
	                        </div>
	                    </div>
	                    <div class="ln_solid"></div>
	                    <div class="form-group">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="sector">Sector <span class="required">*</span>
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                            <input data-api-attr="sector" type="text" id="sector" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="industry">Industry  
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                            <input data-api-attr="industry" type="text" id="industry"  class="form-control col-md-7 col-xs-12">
	                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="sub-industry">Sub-Industry 
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                            <input data-api-attr="subIndustry" type="text" id="sub-industry" class="form-control col-md-7 col-xs-12">
	                        </div>
	                    </div>
	                    <div class="ln_solid"></div>
	                    <div class="form-group upload-box-sec">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-clogo-bg">Company Logo (Big):
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                            <input name="file"  id="file-clogo-bg" class="file" type="file" data-min-file-count="1">
	                            <br>
	                            <input type="hidden" class="form-control"  data-api-attr="companyLogoBig" id="file-clogo-bg-url" value="">
	                        </div>
	                    </div>
	                    <div class="ln_solid"></div>
	                    <div class="form-group upload-box-sec">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-clogo-sm">Company Logo (Small):
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                            <input name="file"  id="file-clogo-sm" class="file" type="file"  data-min-file-count="1">
	                            <br>
	                            <input type="hidden" class="form-control" data-api-attr="companyLogoSmall" id="file-clogo-bg-url" value="">
	                        </div>
	                    </div>
	                    <div class="ln_solid"></div>
	                    <div class="form-group upload-box-sec">
	                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-cteaser">Company Teaser Image:
	                        </label>
	                        <div class="col-md-6 col-sm-6 col-xs-12">
	                            <input name="file" id="file-cteaser" class="file" type="file" data-min-file-count="1">
	                            <br>
	                            <input type="hidden" class="form-control" data-api-attr="companyTeaser" id="file-clogo-bg-url" value="">
	                        </div>
	                    </div>

	                    <div class="ln_solid"></div>
	                    <div class="form-group">
	                        <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
	                            <button type="reset" class="btn btn-primary">Cancel</button>
	                            <button data-attr="submit-btn" type="submit" class="btn btn-success">Submit</button>
	                        </div>
	                    </div>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>

<!-- form success model   -->
<span data-toggle="modal" data-target=".form-success-model" class="hidden js-open-success-model"></span>
<div class="modal fade form-success-model" tabindex="-1" role="dialog" aria-hidden="true">
     <div class="modal-dialog modal-md">
         <div class="modal-content">
			<div class="modal-header no-border">
                 <button type="button" class="close js-btn-formsuccess" data-dismiss="modal"><span aria-hidden="true">X</span>
                 </button>
             </div>
                <div class="modal-body">
                    <div class="clearfix text-center">
                        <h4>The  Company has been created .</h4>
                        <div class="m-t20 text-center">
                             <button type="button" class="btn btn-default js-btn-formsuccess" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
         </div>
     </div>
 </div>
<!-- form success model  -->
<!-- form update model   -->
<span data-toggle="modal" data-target=".form-update-model" class="hidden js-open-update-model"></span>
<div class="modal fade form-update-model" tabindex="-1" role="dialog" aria-hidden="true">
     <div class="modal-dialog modal-md">
         <div class="modal-content">

             <div class="modal-header no-border">
                 <button type="button" class="close js-btn-formupdate" data-dismiss="modal"><span aria-hidden="true">X</span>
                 </button>
             </div>
                <div class="modal-body">
                    <div class="clearfix text-center">
                        <h4>The  Company information has  been updated .</h4>
                        <div class="m-t20 text-center">
                             <button type="button" class="btn btn-default js-btn-formupdate" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
         </div>
     </div>
 </div>
<!-- form update model  -->


<!-- new executive model window  Image Upload -->
<span class="hidden js-img-upload-comapny-exe" data-toggle="modal" data-target=".img-upload-comapny-exe"></span>
<div class="modal fade img-upload-comapny-exe" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-lg pos-rel js-loading-bar">
        <div class="loading-sec hidden"></div>
         <div class="modal-content">
			<div class="modal-header">
                 <button type="button" class="close js-cencel-img-upload" data-dismiss="modal"><span aria-hidden="true">X</span>
                 </button>
                 <h4 class="modal-title" id="myModalLabel">Company image upload</h4>
             </div>
            <div class="modal-body">
                <h4 class="mtb50 text-center js-error-text-dynamic"></h4>
            </div>
            <div class="modal-footer">
                 <button type="button" class="btn btn-default js-cencel-img-upload" data-dismiss="modal">Close</button>
            </div>
        </div>
     </div>
 </div>
  <!-- new executive model window end  Image Upload -->



  <script type="text/javascript">
  var getCompanyFormCallback = function(data){         
    var fallback_img = 'http://loremflickr.com/150/150/brazil';
    if(data){
        $('.x_panel').find('.loading-sec').addClass('hidden');
    }
    $('.title_left > h3').text('Edit Company details');
    $('h2#companyName').text('Details of '+data.companyName);
    $('#company-add-form .btn[data-attr=submit-btn]').text('Update');
    $('#company-add-form').removeClass('company-add-form');
    $('#company-add-form').addClass('company-edit-form');
    $('#tab_content0 .form-head-text >span').text('Update Company Information');
    $('#company-add-form .btn[data-attr=submit-btn]').attr('data-attr', 'update-btn');
    console.log(data.companyName + 'roht aad');
    $('#instance-form input[data-api-attr=instanceCompanyName]').val(data.companyName);
    $('#company-add-form input[data-api-attr=companyName]').val(data.companyName);
    $('#company-add-form input[data-api-attr=country]').val(data.country);
    $('#company-add-form input[data-api-attr=companyURL]').val(data.companyURL);
    $('#company-add-form input[data-api-attr=companyEmail]').val(data.companyEmail);
    $('#company-add-form input[data-api-attr=companyTicker]').val(data.companyTicker.toUpperCase());
    $('#company-add-form input[data-api-attr=companyTickerOld]').val(data.companyTicker.toUpperCase());
    $('#company-add-form textarea[data-api-attr=notes]').val(data.notes);
    $('#company-add-form textarea[data-api-attr=companyDescription]').val(data.companyDescription);
    $('#company-add-form input[data-api-attr=status]:checked').val(data.status);
    $('#company-add-form input[data-api-attr=companyShareholders]').val(data.companyShareholders);
    $('#company-add-form input[data-api-attr=companySize]').val(data.companySize);
    $('#company-add-form select[data-api-attr=shareRegistryId]').val(data.shareRegistryId);
    $('#company-add-form input[data-api-attr=sector]').val(data.sector);
    $('#company-add-form input[data-api-attr=industry]').val(data.industry);
    $('#company-add-form input[data-api-attr=subIndustry]').val(data.subIndustry);

    $('#company-add-form input[data-api-attr=companyLogoBig]').val(data.companyLogoBig);
    $('#company-add-form input[data-api-attr=companyLogoSmall]').val(data.companyLogoSmall);
    $('#company-add-form input[data-api-attr=companyTeaser]').val(data.companyTeaser);


    if(data.companyType){
		$('#company-add-form select[data-api-attr=companyType]').val(data.companyType);
    }else{
    	$('#company-add-form select[data-api-attr=companyType]').prop('selectedIndex', 0);
    }
    
	
    var companyRegisteredStatusVal = data.isRegistered;
    if(companyRegisteredStatusVal == true){
        $('#isRegistered input[name=registered][value=true]').prop('checked',true);
        $('#isRegistered input[name=registered][value=false]').prop('checked',false);
        $('#isRegistered input[name=registered]').closest('label').removeClass('active');
        $('#isRegistered input[name=registered][value=true]').closest('label').addClass('active');
    }else if(companyRegisteredStatusVal == false){
        $('#isRegistered input[name=registered][value=false]').prop('checked',true);
        $('#isRegistered input[name=registered][value=true]').prop('checked',false);
        $('#isRegistered input[name=registered]').closest('label').removeClass('active');
        $('#isRegistered input[name=registered][value=false]').closest('label').addClass('active');
    };
    //DISABLING NOW
    if(data && data.companyTeaser){

        createImageUploadContainer('#company-add-form #file-cteaser',{
                                    caption:'Company Teaser Image',
                                    url:data.companyTeaser||fallback_img});
    }
    if(data && data.companyLogoSmall){

        createImageUploadContainer('#company-add-form #file-clogo-sm',{
                                    caption:'Company Logo - Small',
                                    url:data.companyLogoSmall||fallback_img});
    }
    if(data && data.companyLogoBig){

        createImageUploadContainer('#company-add-form #file-clogo-bg',{
                                caption:'Company Logo - Big',
                                url:data.companyLogoBig||fallback_img});
    }
    	$('#company-add-form input[data-api-attr=tagRole]').val(data.tagRole);
	    var tagValueRole = $('#company-add-form input[data-api-attr=tagRole]').val();
	    var tagValueRole2 = tagValueRole.split(',');
		$("#tagRole").select2({
	      tags: true,
	      tokenSeparators: [',', ' '],
	      data: tagValueRole2
	    });
	    $("#tagRole").val(tagValueRole2);
        $("#tagRole").trigger("change");
        $('#tagRole + span.select2-container').css({"width":"100%"});
        var tagRoleLength = $('#company-add-form select#tagRole').val();
		  if(tagRoleLength.length == 1){
		  	if(tagRoleLength == ''){
		  		$('#company-add-form li.select2-selection__choice').remove();
		  		$('#tagRole').empty();
		  	}
		}
  };


  var allocateCompType = function (companyTypeAttr) {  
        if($('#companyTypeId').length){
            $.each(companyTypeAttr, function(index, value){
              //  console.log(index + '-'+ value);
                var optionText = value;
                var optionValue = index;
                $('#companyTypeId').append('<option value='+optionValue+'>'+optionText+'</option>');
            })
        };
  }

  var validateFront = function () {
  	  var addFrmValid = $('#company-add-form').parsley().isValid()
  	  //var errorExists = $('#company-add-form').find('.parsley-error').length ? true : false;

      if (addFrmValid) {
          $('.bs-callout-info').removeClass('hidden');
          $('.bs-callout-warning').addClass('hidden');
      } else {
          $('.bs-callout-info').addClass('hidden');
          $('.bs-callout-warning').removeClass('hidden');
      }
  };


var postCompanyFormCallback = function(data){
    if(data){
        $('.x_panel').find('.loading-sec').addClass('hidden');
    }
   // alert('Added new Company');
    $('.js-open-success-model').trigger("click");
    console.log(data); // show response 
    dataId = data.id;
    companyId = dataId;
    instaceIrMenu();
     populateIRMenu2(companyId);
    console.log('Data id is '+ dataId); 
};


$('.js-btn-formsuccess').on('click', function(){
  $('#company-add-form .btn[data-attr=submit-btn]').text('Update');
  $('#company-add-form .btn[data-attr=submit-btn]').attr('data-attr', 'update-btn');
  $('#company-add-form').removeClass('company-add-form');
  $('#company-add-form').addClass('company-edit-form');
  $('.form-head-text >span').text('Update Company Information');
  
  setTimeout(function(){
        window.location.href += '?cid='+companyId;
    }, 2000);
});

var updateCompanyForm = function(){

	var tagRoleVal = $('#tagRole').val();
    var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
    $('#company-add-form input[data-api-attr=tagRole]').val(tagRoleVar);

	var companyAttrs = ['companyName','country','companyURL','companyEmail','companyTicker','notes','companyDescription','status','companyShareholders','companySize','sector','industry','subIndustry','companyLogoBig','companyLogoSmall','companyTeaser','isRegistered','shareRegistryId','companyType','tagRole'];
    var dataString = {};

        
    var imgCompayLogoBig = $('input#file-clogo-bg').val();
    var imgCompanyLogoSmall = $('input#file-clogo-sm').val();
    var imgCompanyLogoTeaser = $('input#file-cteaser').val();
        if(imgCompayLogoBig != ''){
            $('.js-img-upload-comapny-exe').trigger('click');
            $('.js-error-text-dynamic').html(UI_MESSAGES.error_big_logo_upload);
            return false;
        }else if(imgCompanyLogoSmall != ''){
            $('.js-img-upload-comapny-exe').trigger('click');
            $('.js-error-text-dynamic').html(UI_MESSAGES.error_small_logo_upload);
            return false;
        }else if(imgCompanyLogoTeaser != ''){
            $('.js-img-upload-comapny-exe').trigger('click');
            $('.js-error-text-dynamic').html(UI_MESSAGES.error_teaser_logo_upload);
            return false;
        }
        else{
            //
        }
        
        _.each(companyAttrs, function(key){
            if(key == 'status'){
                dataString[key] =  $('.form-control[data-api-attr='+key+']:checked').val();    
            }else if(key == 'isRegistered'){
                dataString[key] =  $('#isRegistered [data-api-attr='+key+']:checked').val();    
            }else{
                dataString[key] =  $('.form-control[data-api-attr='+key+']').val();    
            }
       });


   var formValidate = $('#company-add-form').parsley().validate();
   var errorExists  = $('#company-add-form').find('.danger-text').length ? true : false;
    validateFront();
   if(formValidate == true && !errorExists){
        $('.x_panel').find('.loading-sec').removeClass('hidden');
        console.log('consle 2 success');

       $.putJSON(API_ENDPOINT+'companies/'+dataId, dataString, putCompanyFormCallback);
    }else{
       // alert(UI_MESSAGES.form_validation_error);
       toastNotifications(UI_MESSAGES.form_validation_error);
    }
};
// Update Company Inforamation end here

var putCompanyFormCallback = function(data){
    if(data){
        $('.x_panel').find('.loading-sec').addClass('hidden');
    }
    //alert('Updated new Company');
    $('.js-open-update-model').trigger("click");
   /* $('.js-btn-formupdate').on('click', function(){
        // here some code 
    });*/
    
};


//window.prettyPrint && prettyPrint();
</script>



 <!-- FIRST_FORM -->
    <script type="text/javascript">


        $(document).ready(function () {

        	$("#tagRole").select2({
              tags: true,
              tokenSeparators: [',', ' ']
            });
            $("#tagRole + .select2").css({'width':'100%'});
            
            allocateCompType(companyTypeAttr);


        	var companyId = getUrlParameter ('cid');
        	
        	if(!companyId){
        		$('#company-add-form').addClass('company-add-form');
        	}else if(parseInt(companyId) > 0){
        		$('#company-add-form').addClass('company-edit-form')
        		//$('#instance-form').addClass('instance-add-form');
        	}
			

            // check companyTickerExists
            $('#company-ticker').on('blur', function(){

            	var self = this;
                var thisCompanyTickerName = $(this).val().trim().toUpperCase();
                var thisCompanyOldTickerName = $('#company-ticker-old').val().trim().toUpperCase();

                var dataString = '';

                if(thisCompanyTickerName == ''){

                    $(this).addClass('parsley-error');
                    $(self).parent().find('div.danger-text').remove();
                    $(this).after('<div class="danger-text">' +UI_MESSAGES.error_ticker_blank+ '</div>');

                    return false;

                }else if(thisCompanyTickerName != thisCompanyOldTickerName){

                    $(this).removeClass('parsley-error');
                    ///master-admin/api/companies/asx-code-exists/{asxCode}
                    $.getJSON(API_ENDPOINT+'companies/asx-code-exists/'+thisCompanyTickerName, dataString, function(data){

		                var instanceResult = data.result

		                if(instanceResult == true){

		                    $('#company-ticker').addClass('parsley-error');

		                    $('#company-ticker').after('<div class="danger-text">' +UI_MESSAGES.error_ticker_exists+ '</div>');

		                }else{
		                	$(self).removeClass('parsley-error');
		                	$(self).parent().find('div.danger-text').remove();
		                }

            		});

                }

            });

            // var putCheckCompanyTickerCallback = function(data){
            //     var instanceResult = data.result
            //     if(instanceResult == true){
            //         $('#instance-name').addClass('parsley-error');
            //         $('#instance-name').after('<div class="danger-text">' +UI_MESSAGES.error_ticker_exists+ '</div>');
            //     }
            // }

            // $('#instance-name').on('focus', function(){

            //     $(this).next('.danger-text').remove();

            // });       


		    $.getJSON(API_ENDPOINT+'share-registries/', function(data){
		       $.each(data,function(i){
		            var registriesId = data[i].id;
		            var registriesName = data[i].shareRegistry;
		            var htmlText =  '<option value='+registriesId+'>'+registriesName+'</option>';
		            $('select#shareRegistry').append(htmlText);
		            console.log(htmlText);
		        });
		    });

            var comapnyInstaceIdFun;
            // $('.xcxc').click(function () {
            //     $('#descr').val($('#editor').html());
            // });    
        
            initToolbarBootstrapBindings();
            // $('#editor').wysiwyg({
            //     fileUploadError: showErrorAlert
            // });
        });

        $(document).ready(function () {

            $(".select2_single").select2({
                placeholder: "Select a state",
                allowClear: true
            });
            $(".select2_group").select2({});
            $(".select2_multiple").select2({
                maximumSelectionLength: 4,
                placeholder: "With Max Selection limit 4",
                allowClear: true
            });

            
            $.listen('parsley:field:validate', function () {
                validateFront();
            });



            /* New company add code here start  */
           $('#company-add-form.company-add-form').submit(function (e){
                e.preventDefault();
                var formValidate = $('#company-add-form').parsley().validate();
                var errorExists  = $('#company-add-form').find('.danger-text').length ? true : false;
                validateFront();
                var parentLoading = $(this).parents('.js-form-spinner').find('.loading-sec');

                var tagRoleVal = $('#tagRole').val();
                var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                $('#company-add-form input[data-api-attr=tagRole]').val(tagRoleVar);

                var imgCompayLogoBig = $('input#file-clogo-bg').val();
                var imgCompanyLogoSmall = $('input#file-clogo-sm').val();
                var imgCompanyLogoTeaser = $('input#file-cteaser').val();
                if(imgCompayLogoBig != ''){
                    $('.js-img-upload-comapny-exe').trigger('click');
                    $('.js-error-text-dynamic').html(UI_MESSAGES.error_big_logo_upload);
                    return false;
                }else if(imgCompanyLogoSmall != ''){
                    $('.js-img-upload-comapny-exe').trigger('click');
                    $('.js-error-text-dynamic').html(UI_MESSAGES.error_small_logo_upload);
                    return false;
                }else if(imgCompanyLogoTeaser != ''){
                    $('.js-img-upload-comapny-exe').trigger('click');
                    $('.js-error-text-dynamic').html(UI_MESSAGES.error_teaser_logo_upload);
                    return false;
                }
                else{
                    //
                }
               //var companyAttrs = ['companyName','country','companyURL','notes','companyDescription','status','companyShareholders','companySize','shareRegistryId','sector','industry','subIndustry','companyLogoBig','companyLogoSmall','companyTeaser','validUntil'];
               var companyAttrs = ['companyName','country','companyURL','companyEmail','companyTicker','notes','companyDescription','status','companyShareholders','companySize','sector','industry','subIndustry','companyLogoBig','companyLogoSmall','companyTeaser','isRegistered','shareRegistryId','companyType','tagRole'];
               var dataString = {};
			
               _.each(companyAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('.form-control[data-api-attr='+key+']:checked').val();    
                    }else if(key == 'isRegistered'){
                        dataString[key] =  $('#isRegistered [data-api-attr='+key+']:checked').val();    
                     }else{
                        dataString[key] =  $('.form-control[data-api-attr='+key+']').val();
                    }
               });

               console.log('consle 1 '+ dataString);
                if(formValidate == true && !errorExists){
                    $(parentLoading).removeClass('hidden');
                    console.log('consle 2 success');
                    $('.x_panel').find('.loading-sec').removeClass('hidden');
                    $.postJSON(API_ENDPOINT+'companies', dataString, postCompanyFormCallback);


                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });

            /* ======= Update company form function here ======= */
            $('#company-add-form.company-edit-form').submit(function (e){
               e.preventDefault();
               updateCompanyForm();
            });

            /* ======= Update company form function here end ======= */

           /* New company edit code here  */
            
           /* New company edit code here end  */
           
        });

            
        $(document).ready(function () {
        	var countriesArray = $.map(countries, function (value, key) {
			    return {
			        value: value,
			        data: key
			    };
			});
            // Initialize autocomplete with custom appendTo:
            $('#autocomplete-custom-append').autocomplete({
                lookup: countriesArray,
                appendTo: '#autocomplete-container'
            });
                
            $(function () {
                $('#tags_1').tagsInput({
                    width: 'auto'
                });
            });



	        $("#file-clogo-sm , #file-clogo-bg , #file-cteaser").fileinput(FILE_INPUT_STATIC_CONFIG);

	        $("#file-clogo-sm , #file-clogo-bg , #file-cteaser").on('fileselect', function(event, numFiles, label) {
	            console.log(event);
	            console.log("change");
	        }).on('filesuccessremove', function(event, id) {
	            console.log(event,id);
	            console.log("filesuccessremove");
	        }).on('filepredelete', function(event, key) {
	            console.log('Key = ' + key);
	        }).on('filedeleted', function(event, key) {
	            console.log(event);
	            console.log('Key = ' + key);
	        }).on('fileuploaded', function(event, data, previewId, index) {
	            console.log(event,data);
	            console.log(data);
	            console.log('File uploaded triggered');
	            if(data.response && data.response.fileURL){
	                $(this).closest("div.upload-box-sec").find('input[type=hidden]').val(data.response.fileURL);  
	            }
	        }).on('fileclear', function(event) {
	            console.log(event);
	            console.log("fileclear");
	        }).on('filecleared', function(event) {
	            console.log(event);
	            console.log("filecleared");
	        }).on('filepreupload', function(event, data, previewId, index) {
	            console.log(event,data);
	            console.log(data);
	            console.log('File pre upload triggered');
	        }).on('fileuploaderror', function(event, data, previewId, index) {
	            console.log(event,data);
	            var form = data.form, files = data.files, extra = data.extra,
	            response = data.response, reader = data.reader;
	            console.log('File upload error');
	        });



        });
		// Input type = number validation code
		function maxLengthCheck(object) {
	    if (object.value.length > object.maxLength)
	      object.value = object.value.slice(0, object.maxLength)
	  }
    
	  function isNumeric (evt) {
	    var theEvent = evt || window.event;
	    var key = theEvent.keyCode || theEvent.which;
	    key = String.fromCharCode (key);
	    var regex = /[0-9]|\./;
	    if ( !regex.test(key) ) {
	      theEvent.returnValue = false;
	      if(theEvent.preventDefault) theEvent.preventDefault();
	    }
	  }
    </script>
    <!-- FIRST_FORM_ENDS -->
