<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>
<link href="<%= baseURL %>/assets/js/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
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
                            <h3>List of Company executives</h3>
                        </div>

                        <div class="title_right">
                            <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                                <div class="input-group">
                                    <input type="text" class="form-control" placeholder="Search for...">
                                    <span class="input-group-btn">
                            <button class="btn btn-default" type="button">Go!</button>
                        </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel pos-rel">
                                <div class="loading-sec hidden"></div>
                                <div class="x_content">

                                    <div class="row">
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-executive"> <i class="fa fa-plus"></i> Add executive</button>
                                            <button type="button" class="btn btn-default refresh-executive-list"> <i class="fa fa-refresh"></i> Refresh List</button>
                                        </div>
                                         <!-- <div class="col-md-6 col-sm-6 col-xs-6">
                                                <div class="form-group clearfix">
                                                     <label class="control-label col-md-5 col-sm-5 col-xs-12 m-t10 text-right" for="search-company-executive">Search company executives : <span class="required">*</span>
                                                     </label>
                                                     <div class="col-md-7 col-sm-7 col-xs-12">
                                                          <select id="search-company-executive" class="select2_single form-control" tabindex="-1">
                                                             <option value="rohit">Rohit</option>
                                                             <option value="suryya">Suryya</option>
                                                             <option value="shiva">Shiva</option>
                                                             <option value="ramt">Ram T</option>
                                                         </select>
                                                     </div>
                                             </div>
                                        </div> -->
                                        <div style="margin-bottom:15px;" class="clearfix"></div>
                                            <div id="executives_tiles"></div>
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
    <script src="<%= baseURL %>/assets/js/bootstrap-fileinput/js/fileinput.min.js" type="text/javascript"></script>    
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
    
    <!-- executive model window  -->
    <div class="modal fade executive-profile-sec" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg  pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-update" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Executive profile edit</h4>
                 </div>
                 <form  id="executive-edit-form" class="form-validation-notify form-horizontal form-label-left ">
                 <input data-api-attr="id" type="text" value="" class="hidden">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-name">Name of the Executive <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execName" type="text"  id="executive-name" maxlength="80" value="" placeholder="Enter your name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-job">Job Title <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execJobTitle" type="text" id="executive-job" maxlength="80" value="" placeholder="ex. ceo" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-email">Email Address 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execEmail" type="text" id="executive-email" maxlength="80" value="" placeholder="ex. xyz@abc.com"  data-parsley-type="email" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-phone">Phone Number 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execPhone" type="text" id="executive-phone" maxlength="80" value="" placeholder="(XXX) XXXX XXX"  data-parsley-type="phone" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-type">Executive Type 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execType" type="text" id="executive-type" maxlength="80" value="" placeholder="" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="tagRole2">Tags 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <select id="tagRole2"  class="form-control col-md-7 col-xs-12 select2-hidden-accessible" multiple="" tabindex="-1" aria-hidden="true"></select>
                                     <input type="text"  data-api-attr="tagRole" class="hidden form-control" />
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12">Status:</label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 
                                     <div id="" class="btn-group" data-toggle="buttons">
                                         <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-active2">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-executive2" value="20" checked="" id="status-active2"> &nbsp; Active &nbsp;
                                         </label>
                                         <label class="btn btn-default primary-active " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-inactive2">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-executive2" value="30" id="status-inactive2"> Inavtive
                                         </label>
                                     </div>
                                 </div>
                             </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-date">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="executive-date" value="" placeholder="DD/MM/YYYY" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-message">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="executive-message" class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-description">
                                     Description (20 chars min, 400 max) : 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="execDesc" id="executive-description"  class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                             <div class="form-group upload-box-sec">
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-cePicEdit">Profile imageE:
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input  id="file-cePicEdit" class="file form-control" type="file" name="file" data-min-file-count="1">
                                        <input type="hidden" class="form-control"  data-api-attr="execImageURL" id="file-cePicEdit-url" value="">
                                  </div>
                                  <div class="col-md-3 col-sm-3 col-xs-12 hidden js-file-upload-error"></div>
                                   
                              </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-confirmation-status pull-left" data-toggle="confirmation" data-placement="top">Deactivate</button>
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button type="submit" data-attr="update-btn" class="btn btn-primary">Update</button>
                    </div>
                </form>
                <div id="sucess-model-update" class="text-center hidden ">
                    <h4 class="mtb50">Executive updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 
                <div id="sucess-model-status-update" class="text-center hidden ">
                    <h4 class="mtb50">Executive status updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>


             </div>
         </div>
     </div>
    <!-- executive model window end  -->
    <!-- new executive model window  -->
    <div class="modal fade add-new-executive" tabindex="-1" role="dialog" aria-hidden="true">
    
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-verify" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add Executive profile</h4>
                 </div>
                 <form id="add-executive-form" class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-name2">Name of the Executive <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execName" type="text"  id="executive-name2" maxlength="80" value="" placeholder="Enter your name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-job2">Job Title <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execJobTitle" type="text" id="executive-job2" maxlength="80" value="" placeholder="ex. ceo" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-email2">Email Address
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execEmail" type="text" id="executive-email2" maxlength="80" value="" placeholder="john.doe@hotmail.com" data-parsley-type="email" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-phone2">Phone Number 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execPhone" type="text" id="executive-phone2" maxlength="80" value="" placeholder="(XXX) XXXX XXX"  data-parsley-type="phone" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-type2">Executive Type 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="execType" type="text" id="executive-type2" maxlength="80" value="" placeholder="" class="form-control col-md-7 col-xs-12">
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
                            <!-- <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12">Status:</label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 
                                     <div id="" class="btn-group" data-toggle="buttons">
                                         <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-active">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-executive" value="20" checked="" id="status-active"> &nbsp; Active &nbsp;
                                         </label>
                                         <label class="btn btn-default primary-active " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-inactive">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-executive" value="30" id="status-inactive"> Inavtive
                                         </label>
                                     </div>
                                 </div>
                             </div> -->
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-date2">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="executive-date2" value="" data-parsley-required="true" placeholder="DD/MM/YYYY" required="required" class="date-picker form-control col-md-7 col-xs-12 new-date-add">
                                 </div>
                            </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-message2">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="executive-message2" class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="executive-description2">
                                     Description (20 chars min, 400 max) : 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="execDesc" id="executive-description2"  class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                             <!-- <div class="form-group upload-box-sec">
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-cePic">Profile image:
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input data-api-attr="execImageURL" id="file-cePic" class="file" type="file"  data-min-file-count="1">
                                  </div>
                                   <input type="hidden" class="form-control"  data-api-attr="execImageURL" id="file-cePic" value="">
                              </div> -->

                              <div class="form-group upload-box-sec">
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-cePicNew">Profile imageA:
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="file-cePicNew" class="file form-control" type="file" name="file"  data-min-file-count="1">
                                        <input type="hidden" class="form-control"  data-api-attr="execImageURL" id="file-cePicNew-url" value="">
                                  </div>
                                  <div class="col-md-3 col-sm-3 col-xs-12 hidden js-file-upload-error"></div>
                              </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <input type="submit" data-attr="submit-btn" class="btn btn-primary" value="Submit" />
                    </div>
                </form>
                <div id="sucess-model" class="text-center hidden ">
                    <h4 class="mtb50">Executive record added successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 

             </div>
         </div>
     </div>
    <!-- new executive model window end  -->
    <!-- new executive model window  Image Upload -->
    <span class="hidden js-img-upload-comapny-exe" data-toggle="modal" data-target=".img-upload-comapny-exe"></span>
    <div class="modal fade img-upload-comapny-exe" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-cencel-img-upload" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Comany executives image</h4>
                 </div>
                    <div class="modal-body">
                        <h4 class="mtb50 text-center"><span id="comapny-pic-erorr-text">Opps! something went wrong</span></h4>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-cencel-img-upload" data-dismiss="modal">Cancel</button>
                         <button type="button" data-attr="submit-btn" class="btn btn-primary">Yes</button>
                    </div>
            </div>
         </div>
     </div>
    <!-- new executive model window end  Image Upload -->
    <!-- executive model remove end  -->
    <div class="modal fade executive-remove" tabindex="-1" role="dialog" aria-hidden="true">
        
         <div class="modal-dialog modal-md pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Executive profile remove</h4>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4>Are you sure you want to remove this company executive.</h4>
                            <div class="m-t20 text-center">
                                <button class="btn btn-default js-remove-executive" data-dismiss="modal">No</button>
                                <button class="btn btn-danger" data-dismiss="modal">Yes</button>
                            </div>
                        </div>
                    </div>
             </div>
         </div>
     </div>
    <!-- executive model remove end  -->
    <!-- Feed Ajex Error Model -->
     <div class="modal fade ajex-error-modal" id="failed-model-update" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-md">
             <div class="modal-content">

                 <div class="modal-header no-border">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4 class="mtb50"><span id="ajex-wrong-msg">Opps! something went wrong</span></h4>
                            <div class="m-t20 text-right">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            </div>
                        </div>
                    </div>
             </div>
         </div>
     </div>
     <!-- Feed Ajex Error Model -->
    <script type="text/javascript">
        /*$(document).ready(function () {
            $(".select2_single").select2({
                placeholder: "Select a state",
                allowClear: true
            });
            
            
            
            $('.js-remove-sec').on('click', function(){
                currentExecutiveBtn = $(this);
            });
            $('.js-remove-executive').on('click', function(){
                $(currentExecutiveBtn).closest('.well.profile_view').parent().remove();
            });
            
        });*/
        
        
    </script>
    <script type="text/template" id="executives_tiles_template">     


        <$$ _.each(characters, function(executive,index){ $$>

                <div class="col-md-4 col-sm-4 col-xs-12 animated fadeInDown">
                    <div class="well profile_view pos-rel min-height280">
                        <div class="col-sm-12">
                            <!-- <h4 class="brief"><a href="#"><i>Telecom Industry</i></a></h4> -->
                            <div class="left col-xs-7">
                                <$$ if(executive.execName){ $$>
                                <h2> <$$= executive.execName $$> </h2>
                                <$$ } $$>
                                <p><strong>Job Title  : </strong> <$$= executive.execJobTitle $$> </p>
                                <p><strong>Email ID : </strong> <$$= executive.execEmail $$> </p>
                                <p><strong>Phone  : </strong> <$$= executive.execPhone $$> </p>
                                <p><strong>Note : </strong> <$$= executive.notes $$> </p>
                                <p><strong>Valid Until  : </strong> <$$= moment(executive.validUntil).format(DATE_OUT_FORMAT) $$></p>
                                <div class="textWrapSec">
                                    <p><strong>Tags  : </strong> <$$= executive.tagRole $$></p>
                                </div>
                                
                                <div class="textWrapSec">
                                    <p><strong>Status  : </strong> 

                                        <$$ if(executive.status == 20){ $$>
                                            <span class="success-text">Active</span>
                                        <$$ } else if(executive.status == 30){$$>
                                            <span class="danger-text">Inactive</span>
                                        <$$ } else {$$>
                                            <span class="danger-text">Inactive</span>
                                        <$$ }$$>

                                    </p>
                                </div>                               

                            </div>
                            <div class="right col-xs-4">
                                <div class="text-center">
                                    <$$ if(!executive.execImageURL){ $$>
                                        <img src="<%= baseURL %>/assets/images/user.png" alt="" class="img-circle img-responsive">
                                    <$$ } else{$$>
                                        <img src="<%= baseURL %><$$= executive.execImageURL $$>" alt="Company executive" class="img-circle img-responsive" style="width:180px;height:135px;">
                                    <$$ } $$>
                                </div>
                                <div class="m-t5 text-center"><$$= executive.execName $$></div>
                            </div>
                        </div>
                        <div class="col-xs-12 bottom pos-ab-lrb">
                            <div class="col-xs-12 col-sm-12 emphasis">
                                <button type="button" class="btn btn-primary btn-xs js-edit-executive" data-toggle="modal" data-target=".executive-profile-sec"> <i class="fa fa-edit"></i> Edit Profile <input type="text" value="<$$= executive.id $$>" class="hidden thisExevId" /></button>
                            <!-- <button type="button" class="btn btn-primary btn-xs js-remove-sec" data-toggle="modal" data-target=".executive-remove"> <i class="fa fa-remove"></i> Remove</button> -->
                            </div>
                        </div>
                    </div>
                </div>

            <$$ }); $$>

    </script>
    <script>
        //var apiUrlLocal = "http://localhost:8080/cms-cust-admin/cust-admin/api/company-instances/8/";

        var apiEnds = [] , apiRsps = {};
        var getExecutives = function(path) {
            // body...
            //return $.ajax({ url: 'http://localhost:8080/cms-master-api/executives' });
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };

        var getCompany = function(path,id) {
            // body...
            //return $.ajax({ url: 'http://localhost:8080/cms-master-api/executives' });
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: API_ENDPOINT+path+'/'+id
            });
        };

        var chainedStepTwo = function(inputResponse){
            apiRsps[_.last(apiEnds)] = inputResponse;
            var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
            return getCompany('executives',compId);
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
          

            var populateExecutives = function(){
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                getExecutives('executives').then(function (adata) {
                   $('.x_panel').find('.loading-sec').removeClass('hidden');
                    // body...
                    injectTpl('#executives_tiles',adata).then(function () {

                        $('.x_panel').find('.loading-sec').addClass('hidden');
                        // Do something brilliant here!
                        //alert("Do something brilliant here!");
                        $(".select2_single").select2({
                            placeholder: "Select a state",
                            allowClear: true
                        });
                    });
                }, function(reason) {
                    $('.x_panel').find('.loading-sec').addClass('hidden');
                    //console.log(reason); // Error!
                });
            };

          $(document).ready(function () {

            $("#tagRole").select2({
              tags: true,
              tokenSeparators: [',', ' ']
            });
            $("#tagRole + .select2, #tagRole2 + .select2").css({'width':'100%'});

            $('#comapny-pic-erorr-text').text(UI_MESSAGES.error_profile_logo_upload);
                //isolated single



                populateExecutives();
                
                

                // ============== Normal Script 
                $.listen('parsley:field:validate', function () {
                    validateFront();
                });
                var validateFront = function () {
                    if (true === $('#add-executive-form').parsley().isValid()) {
                        $('.bs-callout-info').removeClass('hidden');
                        $('.bs-callout-warning').addClass('hidden');
                    } else {
                        $('.bs-callout-info').addClass('hidden');
                        $('.bs-callout-warning').removeClass('hidden');
                    }
                };
                // ========================= Form script 1 start
                var imgUploadError = "You have selected an image for profile, but have not uploaded it yet, please upload it beforing submitting.";
                $('#add-executive-form').submit(function (e) {
                    console.log('form submited');
                    e.preventDefault();
                    var tagRoleVal = $('#tagRole').val();
                    var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                    $('#add-executive-form input[data-api-attr=tagRole]').val(tagRoleVar);
                    var formValidate = $('#add-executive-form').parsley().validate();
                    validateFront();
                   
                   var comPicUpload = $('#file-cePicNew').val();
                   if(comPicUpload == ''){
                        $('#add-executive-form .js-file-upload-error').addClass('hidden');
                        $('#add-executive-form .js-file-upload-error').removeClass('text-danger');
                        $('#add-executive-form .js-file-upload-error').html(''); 
                    }else{
                      //  $('.js-img-upload-comapny-exe').trigger("click");
                      $('#add-executive-form .js-file-upload-error').removeClass('hidden');
                      $('#add-executive-form .js-file-upload-error').addClass('text-danger');
                      $('#add-executive-form .js-file-upload-error').html(imgUploadError);
                        return false;
                    }
                   //var executiveAttrs = ['notes','validUntil','tagRole','status','execName','execJobTitle','execDesc','execEmail','execPhone','execImageURL','execType'];
                   var executiveAttrs = ['notes','execName','execJobTitle','execDesc','execEmail','execPhone','execType','validUntil', 'tagRole','execImageURL'];
                   var dataString = {};

                   _.each(executiveAttrs, function(key){
                        if(key == 'status'){
                            dataString[key] =  $('#add-executive-form .form-control[data-api-attr='+key+']:checked').val();
                            dataString[key] = parseInt(dataString[key], 10);    
                        }else if((key == 'validUntil')  &&  moment($('#add-executive-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                        dataString[key] =  moment($('#add-executive-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                        }else{
                            dataString[key] =  $('#add-executive-form .form-control[data-api-attr='+key+']').val();    
                        }
                   });

                   console.log(dataString);

                    if(formValidate == true){
                        dataString['status'] = 20;
                        console.log('success');
                        $('#add-executive-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                        $.postJSON(API_ENDPOINT+'executives', dataString, postExecutivesCallback);
                    }else{
                       // alert(UI_MESSAGES.form_validation_error);
                       toastNotifications(UI_MESSAGES.form_validation_error);
                    }

                });
                var postExecutivesCallback = function(data){
                    //alert('Added new Executive');  $('.loading-sec').addClass('hidden');
                    
                    if(data){
                        $('#add-executive-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    };
                    $('#add-executive-form input[type=text], #add-executive-form input[type=hidden], #add-executive-form textarea').val('');
                    //$('#add-executive-form .fileinput-remove.fileinput-remove-button').trigger('click');
                    $('#add-executive-form #file-cePicNew').fileinput('clear');
                    $('#add-executive-form').addClass('hidden');
                    $('#add-executive-form').next('#sucess-model').removeClass('hidden');
                    $('input[data-api-attr=validUntil].new-date-add').val(defaultDateVal);
                    datePlugin();
                    console.log(data); // show response
                    $("select#tagRole").select2("val", "");
                    
                };

                $('.js-btn-verify').on('click', function(){
                    $('#add-executive-form').removeClass('hidden');
                    $('#add-executive-form').next('#sucess-model').addClass('hidden');
                    populateExecutives();
                });
                $('.js-cencel-img-upload').on('click', function(){
                    $('#file-cePicNew').val('');
                });
                // ========================= Form script 1 end     
	            $('.refresh-executive-list').on('click', function(){
	            		                
	                populateExecutives();
	            });
                
                // ========================= Form script 2
                $(document).on('click', '.js-edit-executive', function(){
                    $('#tagRole2').empty();
                    //$('#executive-edit-form .kv-file-remove').trigger('click');
                    $('#executive-edit-form #file-cePicEdit').fileinput('clear');
                    var dataString = '';
                    var executiveId = $(this).find('.thisExevId').val();
                    $('#executive-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                    $.getJSON(API_ENDPOINT+'executives/'+executiveId, dataString, getExecutiveCallback);
                });
                var getExecutiveCallback = function(data){
                    var fallback_img = 'http://loremflickr.com/150/150/brazil';
                    console.log(data.execName);
                    if(data){
                        $('#executive-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    }
                    $('#executive-edit-form input[data-api-attr=execName]').val(data.execName);
                    $('#executive-edit-form input[data-api-attr=execJobTitle]').val(data.execJobTitle);
                    $('#executive-edit-form input[data-api-attr=execEmail]').val(data.execEmail);
                    $('#executive-edit-form input[data-api-attr=execPhone]').val(data.execPhone);
                    $('#executive-edit-form input[data-api-attr=execType]').val(data.execType);
                    $('#executive-edit-form [data-api-attr=execImageURL]#file-cePicEdit-url').val(data.execImageURL)
                    $('#executive-edit-form input[data-api-attr=tagRole]').val(data.tagRole);

                    var menuStatusVal = data.status;
                    if(menuStatusVal == 20){
                        $('#executive-edit-form input[name=status-executive2][value=20]').prop('checked',true);
                        $('#executive-edit-form input[name=status-executive2][value=30]').prop('checked',false);
                        $('#executive-edit-form input[name=status-executive2]').closest('label').removeClass('active');
                        $('#executive-edit-form input[name=status-executive2][value=20]').closest('label').addClass('active');
                    }else if(menuStatusVal == 30){
                        $('#executive-edit-form input[name=status-executive2][value=30]').prop('checked',true);
                        $('#edit-menu-list input[name=status-menu2][value=20]').prop('checked',false);
                        $('#executive-edit-form input[name=status-executive2]').closest('label').removeClass('active');
                        $('#executive-edit-form input[name=status-executive2][value=30]').closest('label').addClass('active');
                    };
                    //$('#executive-edit-form input[data-api-attr=status]').val(data.status);
                    if(data.validUntil){

                        $('#executive-edit-form .form-control[data-api-attr=validUntil]').val(
                            moment(data.validUntil).format(DATE_OUT_FORMAT)
                        );

                    }else{
                        $('#executive-edit-form .form-control[data-api-attr=validUntil]').val(
                            moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                        );
                    }
                   // $('#executive-edit-form input[data-api-attr=validUntil]').val(data.validUntil);
                    $('#executive-edit-form textarea[data-api-attr=notes]').val(data.notes);
                    $('#executive-edit-form textarea[data-api-attr=execDesc]').val(data.execDesc);
                   // $('#executive-edit-form input[data-api-attr=execImageURL]').val(data.execImageURL);
                    $('#executive-edit-form input[data-api-attr=id]').val(data.id);

                    if(data && data.execImageURL){

                        createImageUploadContainer('#executive-edit-form #file-cePicEdit',{
                                                    caption:'Company Executive Pic',
                                                    url:data.execImageURL||fallback_img});
                    }
                    var tagValueRole = $('#executive-edit-form input[data-api-attr=tagRole]').val();
                    var tagValueRole2 = tagValueRole.split(',');
                    $("#tagRole2").select2({
                      tags: true,
                      tokenSeparators: [',', ' '],
                      data: tagValueRole2
                    });

                    $("#tagRole2").val(tagValueRole2);
                    $("#tagRole2").trigger("change");
                    $('#tagRole2 + span.select2-container').css({"width":"100%"});
                    var tagRoleLength = $('#executive-edit-form select#tagRole2').val();
                      if(tagRoleLength.length == 1){
                        if(tagRoleLength == ''){
                            $('#executive-edit-form li.select2-selection__choice').remove();
                            $('#tagRole2').empty();
                        }
                    }
                    datePlugin();

                    $('#executive-edit-form input[name=status-executive2]').prop( "disabled", true);  
                    $('#executive-edit-form input[name=status-executive2]').addClass('disabled');
                    $('#executive-edit-form input[name=status-executive2]').closest('label').addClass('disabled');


                    $('#executive-edit-form').removeClass('hidden');
                    $('#executive-edit-form').closest('.modal-content').find('#sucess-model-status-update').addClass('hidden');
                    var statusUpdatePayload = _.extend(_.clone(data), {status: data.status == 30 ? 20 : (data.status == 20 ? 30 : data.status) });

                    $('.js-confirmation-status').removeClass('disabled').prop( "disabled", false); 
                    if(_.contains([20, 30], data.status)){
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'executives/'+data.id,statusUpdatePayload,statusUpdateCallback);    
                    }else if(!data.status){
                        statusUpdatePayload['status'] = 20;
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'executives/'+data.id,statusUpdatePayload,statusUpdateCallback);  
                    }else{
                        $('.js-confirmation-status').addClass('disabled').prop( "disabled", true);
                    }

                    if(data.status != 20){
                        $( "#executive-edit-form [type=submit]" ).prop( "disabled", true).addClass('disabled');
                        $( ".js-confirmation-status" ).html('Activate');
                    }else{
                        $( "#executive-edit-form [type=submit]" ).prop( "disabled", false).removeClass('disabled');
                        $( ".js-confirmation-status" ).html('Deactivate');
                    }

                 };
                 $('#executive-edit-form').submit(function(e){
                    e.preventDefault();
                    var formValidate = $('#executive-edit-form').parsley().validate();
                    validateFront();
                    var excevId = $('#executive-edit-form input[data-api-attr=id]').val();

                    
                    var comPicUpload = $('#file-cePicEdit').val();
                   if(comPicUpload == ''){
                        $('#executive-edit-form .js-file-upload-error').addClass('hidden');
                        $('#executive-edit-form .js-file-upload-error').removeClass('text-danger');
                        $('#executive-edit-form .js-file-upload-error').html(''); 
                    }else{
                      $('#executive-edit-form .js-file-upload-error').removeClass('hidden');
                     $('#executive-edit-form .js-file-upload-error').addClass('text-danger');
                      $('#executive-edit-form .js-file-upload-error').html(imgUploadError);
                        return false;
                    }
                    var tagRoleVal = $('#tagRole2').val();
                    var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                    $('#executive-edit-form input[data-api-attr=tagRole]').val(tagRoleVar);
                    //var executiveAttrs = ['notes','validUntil','tagRole','status','execName','execJobTitle','execDesc','execEmail','execPhone','execImageURL','execType'];
                   var executiveAttrs = ['notes','status','execName','execJobTitle','execDesc','execEmail','execPhone','execType','validUntil', 'tagRole','execImageURL'];
                   var dataString = {};

                       _.each(executiveAttrs, function(key){
                            if(key == 'status'){
                                dataString[key] =  $('#executive-edit-form .form-control[data-api-attr='+key+']:checked').val();
                                dataString[key] = parseInt(dataString[key], 10);     
                            }else if((key == 'validUntil') &&  moment($('#executive-edit-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                                dataString[key] =  moment($('#executive-edit-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                            }else{
                                dataString[key] =  $('#executive-edit-form .form-control[data-api-attr='+key+']').val();    
                            }
                       });
                    if(formValidate == true){
                        dataString = _.omit(dataString, 'status');
                        console.log('success');
                        $('#executive-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                            $.putJSON(API_ENDPOINT+'executives/'+excevId, dataString, putExecutiveCallback);
                    }else{
                       // alert(UI_MESSAGES.form_validation_error);
                       toastNotifications(UI_MESSAGES.form_validation_error);
                    }
                });
                var putExecutiveCallback = function(data){
                    if(data){
                        $('#executive-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    };
                   // alert('update');
                    $('#executive-edit-form').addClass('hidden');
                    $('#executive-edit-form').next('#sucess-model-update').removeClass('hidden');
                }
                // ========================= Form script 2 end            
                $(document).on('click', '.js-btn-update', function(){
                    $('#executive-edit-form').removeClass('hidden');
                    $('#executive-edit-form').next('#sucess-model-update').addClass('hidden');
                     populateExecutives();
                });
          });
        

        // File Upload function
        $("#file-cePicEdit , #file-cePicNew").fileinput(FILE_INPUT_STATIC_CONFIG);

            $("#file-cePicEdit , #file-cePicNew").on('fileselect', function(event, numFiles, label) {
                console.log(event);
                console.log("change");
            }).on('filesuccessremove', function(event, id) {
                console.log(event,id);
                console.log("filesuccessremove");
                //$(this).closest("div.upload-box-sec").find('input[type=hidden]').val(data.response.fileURL);
            }).on('filepredelete', function(event, key) {
                console.log('Key = ' + key);
            }).on('filedeleted', function(event, key) {
                console.log(event);
                console.log('Key = ' + key);
                $('#file-cePicEdit-url').val(''); // for edit senerio if user image remove 
            }).on('fileuploaded', function(event, data, previewId, index) {
                console.log(event,data);
                console.log(data);
                //var form = data.form, files = data.files, extra = data.extra,
                //response = data.response, reader = data.reader;
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
                //var form = data.form, files = data.files, extra = data.extra,
                //response = data.response, reader = data.reader;
                console.log('File pre upload triggered');
                //$('.file-error-message').hide();
                //$('.file-upload-indicator').hide();
                //$('.file-preview-frame').removeClass('file-preview-error');
                // icon-ok-sign
                //glyphicon glyphicon-exclamation-sign text-danger
            }).on('fileuploaderror', function(event, data, previewId, index) {
                console.log(event,data);
                var form = data.form, files = data.files, extra = data.extra,
                response = data.response, reader = data.reader;
                console.log('File upload error');
                //{readyState: 4, responseText: "http://cms-master-storage.s3.amazonaws.com/1454706536515_481.png", status: 200, statusText: "OK"}
                // $('.file-error-message').hide();
                // $('.file-preview-frame').removeClass('file-preview-error');
                // //$('#company-add-form input[data-api-attr=sector]').val(data.sector);
                
                // if(data.jqXHR.readyState == 4 && data.jqXHR.status == 200 && data.jqXHR.statusText == 'OK'){
                //     //$(this).closest("div").find('input[type=hidden]').val(data.jqXHR.responseText);    
                //     $(this).closest("div.upload-box-sec").find('input[type=hidden]').val(data.jqXHR.responseText);  
                // }

            });

        // ==================== GET_API_WRONG_RESPONSE code ====================
         $(document).ajaxError(function() {
            $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_wrong_response);
            if($('.loading-sec').hasClass("hidden")){
                $('.loading-sec').addClass('hidden');
                $('#add-executive-form input, #add-executive-form textarea').val('');
                $('[data-dismiss=modal]').trigger('click');
                $('#failed-model-update').modal('show');
            }
        });
        // ==================== GET_API_WRONG_RESPONSE code end ====================
        // $(document).ready(function () {
        //     $('#executive-edit-form, #add-executive-form').parsley().on('form:error', function() {
        //       // This global callback will be called for any field that fails validation.
        //       //console.log('Validation failed for: ', this.$element);
        //       toastNotifications(UI_MESSAGES.form_validation_error);
        //     });
        // });

        var statusUpdateCallback = function(data){

                if(data){
                    $('#executive-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                console.log('data update');
                $('#executive-edit-form').addClass('hidden');
                var success_msg = data.status == 30 ? "Executive deactivated successfully" :  "Executive activated successfully";
                $('#executive-edit-form').closest('.modal-content').find('#sucess-model-status-update .mtb50').html(success_msg);
                $('#executive-edit-form').closest('.modal-content').find('#sucess-model-status-update').removeClass('hidden');

                // if(!_.isEmpty(menuTable)){
                //     menuTable.fnDestroy();
                //     menuTable = null;
                // }
                //clearTpl('#executives_tiles');
                populateExecutives();

        };

    </script>       
</body>
</html>
