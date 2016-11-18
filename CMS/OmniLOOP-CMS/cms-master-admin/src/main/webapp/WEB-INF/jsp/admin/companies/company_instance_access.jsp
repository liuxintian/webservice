
    <div class="clearfix">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel pos-rel js-form-spinner">
                    <div class="loading-sec hidden"></div>
                    <div class="x_title">
                        <h2 id="instance-head-access"><span>Instance Access</span></h2>
                        <div class="clearfix" style="margin: 20px 0;">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                <button disabled data-attr="submit-btn" data-value="" type="button" class="btn btn-success pl50 pr50 js-activated-instaceForm disabled">Launch Instance</button>
                            </div>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="row" id="jsInstanceActivatedlist">
                        <div class="col-md-12">
                             <div class="x_content">
                                <div class="clearfix js-instace-access-form">
                                    <form enctype="" id="instanceAccess-add-form"  class="form-validation-notify form-horizontal form-label-left">
                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="fullName">Name <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input data-api-attr="userName" type="text" id="fullName" maxlength="100" placeholder="Enter your name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-name">User Name <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input data-api-attr="loginName" type="text" id="user-name" maxlength="80" placeholder="john.doe@hotmail.com" class="form-control col-md-7 col-xs-12" data-parsley-required="true" data-parsley-type="email">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-pwd">Password <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input data-api-attr="userPwd" type="password" id="user-pwd" maxlength="80" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="user-CnfPwd">Confirm Password <span class="required">*</span>
                                            </label>
                                            <div class="col-md-6 col-sm-6 col-xs-12">
                                                <input data-api-attr="userCnfPwd" type="password" id="user-CnfPwd" maxlength="80"  data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                            </div>
                                        </div>
                                        <div class="ln_solid"></div>
                                        <div class="form-group">
                                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                                                <button data-attr="submit-btn" type="submit" class="btn pl50 pr50 js-add-instance-admin-user">Add user</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5 hidden">
                            <h2 id="instance-head-access" style="background: #efefef;padding: 8px;text-align: center;"> <span>Instance  Activated User list</span> </h2>
                            <ol id="instanceActivated_user"></ol>
                            <script type="text/template" id="instanceActivated_user_template">     

                                         <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>Name</th>
                                                    <th>Login Name</th>
                                                    <th>Disable/Enable</th>
                                                </tr>
                                            </thead>
                                            <tbody>

                                			<$$ _.each(characters, function(instanceActivatedItems,index){ $$>
												
												<$$ if(instanceActivatedItems.status === 20){  $$>
                                                	<tr class="success">
												<$$ }else{ $$>
													<tr class="danger">
												<$$ }  $$>

                                                    <th scope="row"><$$= (index+1) $$></th>
                                                    <td><$$= instanceActivatedItems.name $$></td>
                                                    <td><$$= instanceActivatedItems.loginName $$></td>
													<$$ if(instanceActivatedItems.status === 20){  $$>
                                                    	<td><a href="#" class="btn btn-default btn-xs btn-danger inst-admin-block" data-status="<$$= instanceActivatedItems.status $$>" data-uid="<$$= instanceActivatedItems.userId $$>">Disable</a></td>
													<$$ }else{ $$>
														<td><a href="#" class="btn btn-default btn-xs btn-success inst-admin-block" data-status="<$$= instanceActivatedItems.status $$>" data-uid="<$$= instanceActivatedItems.userId $$>">Enable</a></td>
													<$$ }  $$>
                                                </tr>

                                			<$$ }); $$>

                                            </tbody>
                                        </table>
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!-- INSTANCE TAB ACCESS CREATE USER ACCOUNT -->
<span data-toggle="modal" data-target=".instncAccess-form-model" class="hidden js-instncAccess-form-model"></span>
<div class="modal fade instncAccess-form-model" tabindex="-1" role="dialog" aria-hidden="true">
     <div class="modal-dialog modal-md">
         <div class="modal-content">

             <div class="modal-header no-border">
                 <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                 </button>
                 <!-- <h4 class="modal-title">form update</h4> -->
             </div>
                <div class="modal-body">
                    <div class="clearfix text-center">
                        <h4>The  Instance Access has  been created .</h4>
                        <div class="m-t20 text-center">
                             <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
         </div>
     </div>
 </div>
<!-- INSTANCE TAB ACCESS CREATE USER ACCOUNT -->



<!-- FOURTH_FORM_START -->
    <script id ="main-list-render2">
    var companyManagerId = '';
        $(document).ready(function(){
            companyManagerId = getUrlParameter ('cid');
        });
        //alert(companyManagerId + '===' + instab);
            var apiEnds = [] , apiRsps = {}, menuTable;
            var getInstanceActiveUserItems  = function(path) {
                // body...
                apiEnds.push(path);
                return $.ajax({
                  url: API_ENDPOINT+path
                });
            };

            var getInstanceUserItems  = function(path,id) {
                // body...
                apiEnds.push(path+'/'+id);
                return $.ajax({
                  url: API_ENDPOINT+path+'/'+id
                });
            };

            var chainedStepTwo = function(inputResponse){
                apiRsps[_.last(apiEnds)] = inputResponse;
                var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
                return getInstanceUserItems ('companies/'+companyManagerId+'/managers',compId);
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
              

            var populateInstanceUserMenu = function(callBack){
               // $('.x_panel').find('.loading-sec').removeClass('hidden');
                getInstanceActiveUserItems ('companies/'+companyManagerId+'/managers').then(function (adata) {
                    // body...
                    //var checkLength = (adata[0].loginName);
                    //console.log(checkLength + '- checkLength');
                    
                    $('#jsInstanceActivatedlist > .col-md-12').addClass('col-md-7');
                    $('#jsInstanceActivatedlist > .col-md-7').removeClass('col-md-12');
                    $('#jsInstanceActivatedlist > .col-md-5').removeClass('hidden');
                  //  $('.x_panel').find('.loading-sec').removeClass('hidden');
                    injectTpl('#instanceActivated_user',adata).then(function () {
                        // Do something brilliant here!
                       //alert("Do something brilliant here!");
                        // here code data will be loaded than run this code 
                       // $('.x_panel').find('.loading-sec').addClass('hidden');
                        var asInitVals = new Array();
                        menuTable = $('#example').dataTable({
                            "oLanguage": {
                                "sSearch": "Search all columns:"
                            },
                            "aoColumnDefs": [
                                {
                                    'bSortable': false,
                                    'aTargets': [0]
                                } //disables sorting for column one
                            ],
                            'iDisplayLength': 12,
                            "sPaginationType": "full_numbers",
                            "dom": 'T<"clear">lfrtip',
                            "tableTools": {
                              //  "sSwfPath": "<?php echo base_url('assets2/js/Datatables/tools/swf/copy_csv_xls_pdf.swf'); ?>"
                            }
                        });
                        $("tfoot input").keyup(function () {
                            /* Filter on the column based on the index of this element's parent <th> */
                            menuTable.fnFilter(this.value, $("tfoot th").index($(this).parent()));
                        });
                        $("tfoot input").each(function (i) {
                            asInitVals[i] = this.value;
                        });
                        $("tfoot input").focus(function () {
                            if (this.className == "search_init") {
                                this.className = "";
                                this.value = "";
                            }
                        });
                        $("tfoot input").blur(function (i) {
                            if (this.value == "") {
                                this.className = "search_init";
                                this.value = asInitVals[$("tfoot input").index(this)];
                            }
                        });
                        if(callBack){
                        	callBack();
                        }
                        // here code data will be loaded 
                    });
                    }, function(reason) {
                      // $('.x_panel').find('.loading-sec').addClass('hidden');
                        //console.log(reason); // Error!
                    });

            };

            var clearTpl = function(id){
                $( id ).empty();
                //$( id ).detach();
            }
            
            var blockUnblockUsers = function (){
            	
            	$('.inst-admin-block').click(function(evt){
            		evt.preventDefault();
            		var ele = $(evt.target);
            		var dstatus = ele.attr('data-status');
            		var uid = ele.attr('data-uid');
            		
            		if(!ele.hasClass("disabled")){

                		ele.addClass( "disabled" ).html('Processing..');
                		
                		var apiPath = API_ENDPOINT+'companies/'+companyManagerId+'/managers/disable';
                		
                		if(dstatus.toString() == '30'){
                    		apiPath = API_ENDPOINT+'companies/'+companyManagerId+'/managers/enable';
                		}
                		
                        $.putJSON(apiPath, {"userId": uid}, function(){
                            clearTpl('instanceActivated_user');
                            populateInstanceUserMenu(blockUnblockUsers);                    	                    	
                        });            			
            			
            		}
            		

            		
            	});
            	
            };

            $(document).ready(function(){
                if(companyManagerId){
                    populateInstanceUserMenu(blockUnblockUsers); 
                }
                
                
            });
    </script>
    <!-- FOURTH_FORM_END -->