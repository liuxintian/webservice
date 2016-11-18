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
                            <h3>List of Share Registry</h3>
                        </div>

                        <div class="title_right">
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
                                    <!-- <div class="shoring-sec" style="margin-right:5px;">
                                        <select class="js-shorting-placeholder-single form-control">
                                            <option value="ValidUntil">Valid Until</option>
                                            <option value="Status">Status</option>
                                            <option value="Name">Name</option>
                                        </select>
                                    </div> -->
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




                                        <div class="clearfix pull-right col-md-6 col-sm-6 col-xs-6 pull-right">

                                            <div class="row">
                                                   <div class="text-right paginationSec">
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
                                           <div class="col-md-6 col-sm-6 col-xs-6 pull-left">
                                                <button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-shareRegistry-list"> <i class="fa fa-plus"></i> Add New Registry</button>
                                                <button type="button" class="btn btn-default refresh-registries-list" > <i class="fa fa-refresh"></i> Refresh List</button>
                                           </div>

                                        </div>
                                    </div>
                                    <div class="clearfix"></div>

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

                        <div class="row" id="modify-sec">
                            <table id="example" class="table table-striped responsive-utilities jambo_table">
                                <!--thead class="hidden">
                                    <tr class="headings">
                                        <th class="column-title">Name </th>
                                        <th class="column-title">Contact </th>
                                        <th class="column-title">Email id </th>
                                        <th class="column-title">Valid Until </th>
                                        <th class="column-title">Status </th>
                                        <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                        <th class="bulk-actions" colspan="7">
                                            <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                        </th>
                                    </tr>
                                </thead-->
                                <thead>
                                    <tr class="headings">
                                        <th class="column-title">Name </th>
                                        <th class="column-title">Contact </th>
                                        <th class="column-title">Email id </th>
                                        <th class="column-title">Valid Until </th>
                                        <th class="column-title">Status </th>
                                        <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                        <th class="bulk-actions" colspan="7">
                                            <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                        </th>
                                    </tr>
                                </thead>
                                    <tbody id="shareRegistries_list"></tbody>
                           </table>


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
    <!-- new Document model window  -->
    <div class="modal fade add-new-shareRegistry-list" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close  js-btn-verify" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add Share Registry</h4>
                 </div>
                 <form id="add-share-registries-list" class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title-name">Share Registry <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="shareRegistry"  type="text" id="title-name" maxlength="100" value="" placeholder="" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title-name">Share Registry Type 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="srType"  type="text" id="title-name" maxlength="100" value="" placeholder="ex. Share Registry" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="shre-cont">Contact No <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="srPhone" data-parsley-type="phone" type="text" id="shre-cont" maxlength="100" value="" placeholder="(XXX) XXXX XXX" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="autocomplete-custom-append">Share Registry Country <span class="required">*</span>
                                 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input data-api-attr="srCountry" type="text" name="country" id="autocomplete-custom-append" data-parsley-required="true" class="form-control col-md-10" placeholder="ex. Australia" style="float: left;" />
                                    <div id="autocomplete-container" style="position: relative; float: left; width: 400px; margin: 10px;"></div>
                                </div>
                                 <!-- <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="srCountry"  type="text" id="title-name" maxlength="100" value="" placeholder="ex. India" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div> -->
                            </div>
                            
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email-id-feed">Email ID <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="srEmail" type="email" id="email-id-feed" placeholder="john.doe@hotmail.com" data-parsley-type="email" data-parsley-required="true" class="form-control" name="email" />
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="source-url">Source URL <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input  data-api-attr="srURL" type="url" id="source-url" name="website" data-parsley-required="true" placeholder="www.website.com" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-valid-until">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="feed-valid-until" value="" placeholder="DD/MM/YYYY" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12 new-date-add">
                                 </div>
                            </div>
                            <!-- <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12">Status:</label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 
                                     <div id="" class="btn-group" data-toggle="buttons">
                                         <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-active">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-menu" value="20" checked="" id="status-active"> &nbsp; Active &nbsp;
                                         </label>
                                         <label class="btn btn-default primary-active " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-inactive">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-menu" value="30" id="status-inactive"> Inavtive
                                         </label>
                                     </div>
                                 </div>
                             </div> -->
                             <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-note">
                                     Notes
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="feed-note" class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-note">
                                     Address (20 chars min, 400 max)  <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="srAddress" id="feed-note" data-parsley-required="true" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="400" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="20"></textarea>
                                 </div>
                             </div>

                             
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button type="submit" data-attr="submit-btn" class="btn btn-primary m-b5">Submit</button>
                    </div>
                </form>
                <div id="sucess-model" class="text-center hidden ">
                    <h4 class="mtb50">Share Registry record added successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 

             </div>
         </div>
     </div>
    <!-- new Document model window end  -->
    <!-- edit Document model window  -->
    <div class="modal fade shareRegistry-list-edit" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-update-verify" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Edit Share Registry List</h4>
                 </div>
                 <form  id="edit-share-registries-list"  class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                            <input data-api-attr="id"  type="text" class="hidden">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title2-name">Share Registry <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="shareRegistry"  type="text" id="title2-name" maxlength="100" value="" placeholder="ex. title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title-name2">Share Registry Type 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="srType"  type="text" id="title-name2" maxlength="100" value="" placeholder="ex. title name" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="shre-cont2">Contact No <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="srPhone" data-parsley-type="phone" type="text" id="shre-cont2" maxlength="100" value="" placeholder="(XXX) XXXX XXX" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="autocomplete-custom-append2">Share Registry Country <span class="required">*</span>
                                 </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input data-api-attr="srCountry" type="text" name="country" id="autocomplete-custom-append2" data-parsley-required="true" class="form-control col-md-10" placeholder="ex. Australia" style="float: left;" />
                                    <div id="autocomplete-container2" style="position: relative; float: left; width: 400px; margin: 10px;"></div>
                                </div>
                                 <!-- <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="srCountry"  type="text" id="title-name2" maxlength="100" value="" placeholder="ex. title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div> -->
                            </div>
                            
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email-id-feed2">Email ID <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="srEmail" data-parsley-type="email" type="email" id="email-id-feed2" placeholder="ex. xyz@acb.com" class="form-control validateEmail" name="email" />
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="source-url2">Source URL <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input  data-api-attr="srURL" type="url" id="source-url2" name="website" data-parsley-required="true" placeholder="www.website.com" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-valid-until2">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="feed-valid-until2" value="" placeholder="DD/MM/YYYY" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12">Status:</label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 
                                     <div id="" class="btn-group" data-toggle="buttons">
                                         <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-active2">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-menu2" value="20" checked="" id="status-active2"> &nbsp; Active &nbsp;
                                         </label>
                                         <label class="btn btn-default primary-active " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default" for="status-inactive2">
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-menu2" value="30" id="status-inactive2"> Inavtive
                                         </label>
                                     </div>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-note2">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="feed-note2"  class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-note2">
                                     Address (20 chars min, 400 max)  <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="srAddress" id="feed-note2" data-parsley-required="true" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="400" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="20"></textarea>
                                 </div>
                             </div>

                             
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-confirmation-status pull-left" data-toggle="confirmation" data-placement="top">Deactivate</button>
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button type="submit" data-attr="update-btn" class="btn btn-primary m-b5">Update</button>
                    </div>
                </form>
                <div id="update-model" class="text-center hidden ">
                    <h4 class="mtb50">Share Registry record updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 
                <div id="update-model-registry-status" class="text-center hidden ">
                    <h4 class="mtb50">Share Registry record status updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>

             </div>
         </div>
     </div>
    <!-- edit Document model window end  -->
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

    <%@ include file="/WEB-INF/jsp/common/common_js_scripts.jspf"%> 
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
    <!-- Datatables --> 
        <!--script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script-->


    <script src="<%= baseURL %>/assets/js/dataTables-1.10.11/js/jquery.dataTables.min.js" type="text/javascript"></script>

        <!-- Autocomplete -->
    <script type="text/javascript" src="<%= baseURL %>/assets/js/autocomplete/countries.js"></script>
    <script src="<%= baseURL %>/assets/js/autocomplete/jquery.autocomplete.js"></script>
        <script>
            /*$(document).ready(function () {
                $('input.tableflat').iCheck({
                    checkboxClass: 'icheckbox_flat-green',
                    radioClass: 'iradio_flat-green'
                });
            });

            var asInitVals = new Array();
            $(document).ready(function () {
                var oTable = $('#example').dataTable({
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
                    //Filter on the column based on the index of this element's parent <th> 
                    oTable.fnFilter(this.value, $("tfoot th").index($(this).parent()));
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
            });*/
        </script>   
    <script type="text/javascript">
        /*$(document).ready(function () {
            $('.js-edit-row').on('click', function(){
                row_sec = $(this).closest('tr');
            });
            $('.js-remove-row').on('click', function(){
                $(row_sec).remove();
            });
              
            
            $('#menu-valid-until2, #menu-valid-until').daterangepicker({
                minDate: new Date(),
                singleDatePicker: true,
                calender_style: "picker_4"
            }, function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });
            
            $.listen('parsley:field:validate', function () {
                validateFront();
            });
            $('#add-menu-list .btn[type=submit]').on('click', function () {
                $('#add-menu-list').parsley().validate();
                validateFront();
            });
            
            
            var validateFront = function () {
                
                if (true === $('#add-menu-list').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            
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
            
        });*/
    </script>   
    <script type="text/template" id="shareRegistries_list_template">     
        <$$ _.each(characters, function(shareRegistryItems,index){ $$>

            <tr class="pointer">
                <td class=" "><$$= shareRegistryItems.shareRegistry $$></td>
                <td class=" "><$$= shareRegistryItems.srPhone $$></td>
                <td class=" "><$$= shareRegistryItems.srEmail $$></td>
                <td class=" "><$$= moment(shareRegistryItems.validUntil).format(DATE_OUT_FORMAT) $$></td>
                <td>
                    <$$ if(shareRegistryItems.status == 20){ $$>
                        <span class="success-text">Active</span>
                    <$$ } else if(shareRegistryItems.status == 30){$$>
                        <span class="danger-text">Inactive</span>
                    <$$ } else {$$>
                        <span class="danger-text">Inactive</span>
                    <$$ }$$>
                </td>
                <td class="btn-sec"><button type="button" class="btn js-edit-row btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".shareRegistry-list-edit"> <i class="fa fa-edit"></i> Edit <input type="text" value="<$$= shareRegistryItems.id $$>" class="hidden js-feed-id" /></button></td>
            </tr>    

        <$$ }); $$>
    </script>
    <script id = "main-list-render">

        var apiEnds = [] , apiRsps = {}, menuTable;
        var getShareRegistriesItems = function(path,params) {
            // body...
            apiEnds.push(path);
            return $.ajax({
              url: _.isNull(params) ? API_ENDPOINT+path :  API_ENDPOINT+path+'?'+params
              //url: API_ENDPOINT+path
            });
        };

        var getShareRegistryItems = function(path,id) {
            // body...
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: API_ENDPOINT+path+'/'+id
            });
        };

        var chainedStepTwo = function(inputResponse){
            apiRsps[_.last(apiEnds)] = inputResponse;
            var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
            return getShareRegistryItems('share-registries',compId);
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
          

          var populateShareRegistries = function(params,pagingCallback){
                var params_q = (params && params.length) ? params.join('&') : null;
                $('.x_panel').find('.loading-sec').removeClass('hidden');

                getShareRegistriesItems('share-registries',params_q).then(function (adata) {
                        $('.x_panel').find('.loading-sec').removeClass('hidden');
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


                    injectTpl('#shareRegistries_list',adata).then(function () {
                        // Do something brilliant here!
                        //alert("Do something brilliant here!");
                        // here code data will be loaded than run this code 
                        $('.x_panel').find('.loading-sec').addClass('hidden');
                        var asInitVals = new Array();
                        
                        menuTable = $('#example').dataTable({
                            "oLanguage": {
                                "sSearch": "Search all columns: &nbsp; "
                            },
                            searching: true,
                            "ordering": true,
                            // "aoColumnDefs": [
                            //     {
                            //         'bSortable': false,
                            //         'aTargets': [0]
                            //     } //disables sorting for column one
                            // ],
                            'paging': false,
                            "lengthMenu": [ [-1], ["All"] ],
                            "pageLength": -1,
                            //'iDisplayLength': 12,
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
                        
                        // here code data will be loaded 
                    });
                }, function(reason) {
                       $('.x_panel').find('.loading-sec').addClass('hidden');
                        //console.log(reason); // Error!
                });

          };

          var clearTpl = function(id){
            
            if(!_.isEmpty(menuTable)){
                //menuTable.destroy();
                menuTable.fnDestroy();
                menuTable = null;
                // empty in case the columns change
                //$('#example').empty();
            }

            $( id ).empty();
            //$( id ).detach();
          }

          $(document).ready(function(){
            populateShareRegistries(setUrlForPaging('first')); // GET list of ShareRegistries
            var countriesArray = $.map(countries, function (value, key) {
                return {
                    value: value,
                    data: key
                };
            });
            $('#autocomplete-custom-append').autocomplete({
                lookup: countriesArray,
                appendTo: '#autocomplete-container'
            });
            $('#autocomplete-custom-append2').autocomplete({
                lookup: countriesArray,
                appendTo: '#autocomplete-container2'
            });
            $.listen('parsley:field:validate', function () {
                validateFront();
            });
            var validateFront = function () {
                if (true === $('#add-share-registries-list').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            // =========== Form 1 POST ==================
            $('#add-share-registries-list').submit(function (e){
                e.preventDefault();
                var formValidate = $('#add-share-registries-list').parsley().validate();
                validateFront();
               
               //var registriesAttrs = ['srType','srCountry','shareRegistry','srEmail','srURL','validUntil','status','notes','srAddress','srPhone'];
               var registriesAttrs = ['srType','srCountry','shareRegistry','srEmail','srURL','notes','srAddress','srPhone','validUntil'];
               var dataString = {};

               _.each(registriesAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('#add-share-registries-list .form-control[data-api-attr='+key+']:checked').val(); 
                        dataString[key] = parseInt(dataString[key], 10);   
                    }else if(key == 'validUntil'){
                        dataString[key] =  moment($('#add-share-registries-list .form-control[data-api-attr='+key+']').val(),DATE_OUT_FORMAT).valueOf();
                    }else{
                        dataString[key] =  $('#add-share-registries-list .form-control[data-api-attr='+key+']').val();    
                    }
               });

               

               console.log(dataString);
                if(formValidate == true){
                    dataString['status'] = 20;
                    console.log('success');
                    $('#add-share-registries-list').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                   $.postJSON(API_ENDPOINT+'share-registries', dataString, postShareRegistriesCallback);
                }else{
                  //  alert(UI_MESSAGES.form_validation_error);
                  toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });
            var postShareRegistriesCallback = function(data){
                 if(data){
                    $('#add-share-registries-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                $('#add-share-registries-list input[type=text], #add-share-registries-list input[type=url], #add-share-registries-list input[type=email], #add-share-registries-list textarea').val('');
                $('#add-share-registries-list').addClass('hidden');
                $('#add-share-registries-list').next('#sucess-model').removeClass('hidden');
                $('input[data-api-attr=validUntil].new-date-add').val(defaultDateVal);
                datePlugin();
                
                //alert('Added new Menu');
                console.log(data); // show response 
                
                if(!_.isEmpty(menuTable)){
                    //menuTable.destroy();

                    menuTable.fnDestroy();
                    menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();

                }
                clearTpl('#shareRegistries_list');
                populateShareRegistries(setUrlForPaging('current'));
            };

            $('.js-btn-verify').on('click', function(){
                $('#add-share-registries-list').removeClass('hidden');
                $('#add-share-registries-list').next('#sucess-model').addClass('hidden');
            });
            $('.refresh-registries-list').on('click', function(){ 
                
                $('.search-query-msg-block').hide();
                PAGING_VARS.query = '';
                PAGING_VARS.sort_by = '';
                $('#search-list-frm-query').val('');
                clearTpl('#shareRegistries_list');
                var params = setUrlForPaging('first');
                populateShareRegistries(params,adjustPagingParams(-(PAGING_VARS.current_page)));                  
                
            });
            // =========== Form 1 Post End ==============
            // ======================== Form 2 Put here  ==========================
            $(document).on('click', '.js-edit-row' ,function(){
                var shareRegistryId = $(this).find('.js-feed-id').val();
                var dataString = '';
                 $('#edit-share-registries-list').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                $.getJSON(API_ENDPOINT+'share-registries/'+shareRegistryId, dataString, getShareRegistriesCallback);
            });
            
            var getShareRegistriesCallback = function(data){
                console.log(data.srType);
                if(data){
                    $('#edit-share-registries-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                $('#edit-share-registries-list input[data-api-attr=srType]').val(data.srType);
                $('#edit-share-registries-list input[data-api-attr=srCountry]').val(data.srCountry);
                $('#edit-share-registries-list input[data-api-attr=shareRegistry]').val(data.shareRegistry);
                $('#edit-share-registries-list input[data-api-attr=srEmail]').val(data.srEmail);
                $('#edit-share-registries-list input[data-api-attr=srURL]').val(data.srURL);
                $('#edit-share-registries-list input[data-api-attr=srPhone]').val(data.srPhone);
                $('#edit-share-registries-list textarea[data-api-attr=notes]').val(data.notes);
                $('#edit-share-registries-list textarea[data-api-attr=srAddress]').val(data.srAddress);
                var menuStatusVal = data.status;
                if(menuStatusVal == 20){
                    $('#edit-share-registries-list input[name=status-menu2][value=20]').prop('checked',true);
                    $('#edit-share-registries-list input[name=status-menu2][value=30]').prop('checked',false);
                    $('#edit-share-registries-list input[name=status-menu2]').closest('label').removeClass('active');
                    $('#edit-share-registries-list input[name=status-menu2][value=20]').closest('label').addClass('active');
                }else if(menuStatusVal == 30){
                    $('#edit-share-registries-list input[name=status-menu2][value=30]').prop('checked',true);
                    $('#edit-share-registries-list input[name=status-menu2][value=20]').prop('checked',false);
                    $('#edit-share-registries-list input[name=status-menu2]').closest('label').removeClass('active');
                    $('#edit-share-registries-list input[name=status-menu2][value=30]').closest('label').addClass('active');
                };
                $('#edit-share-registries-list input[data-api-attr=id]').val(data.id);
                if(data.validUntil){
                    $('#edit-share-registries-list .form-control[data-api-attr=validUntil]').val(
                        moment(data.validUntil).format(DATE_OUT_FORMAT)
                    );
                }else{
                    //TO BE REMOVED LATER
                    $('#edit-share-registries-list .form-control[data-api-attr=validUntil]').val(
                        moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                    );

                };
                datePlugin();


                $('#edit-share-registries-list input[name=status-menu2]').prop( "disabled", true);  
                $('#edit-share-registries-list input[name=status-menu2]').addClass('disabled');
                $('#edit-share-registries-list input[name=status-menu2]').closest('label').addClass('disabled');
                $('#edit-share-registries-list').removeClass('hidden');
                $('#edit-share-registries-list').closest('.modal-content').find('#update-model-registry-status').addClass('hidden');
                var statusUpdatePayload = _.extend(_.clone(data), {status: data.status == 30 ? 20 : (data.status == 20 ? 30 : data.status) });

                $('.js-confirmation-status').removeClass('disabled').prop( "disabled", false);  
                if(_.contains([20, 30], data.status)){
                    addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'share-registries/'+data.id,statusUpdatePayload,statusUpdateCallback);    
                }else if(!data.status){
                    statusUpdatePayload['status'] = 20;
                    addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'share-registries/'+data.id,statusUpdatePayload,statusUpdateCallback);  
                }else{
                    $('.js-confirmation-status').addClass('disabled').prop( "disabled", true);
                }


                if(data.status != 20){
                    $( "#edit-share-registries-list [type=submit]" ).prop( "disabled", true).addClass('disabled');
                    $( ".js-confirmation-status" ).html('Activate');
                }else{
                    $( "#edit-share-registries-list [type=submit]" ).prop( "disabled", false).removeClass('disabled');
                    $( ".js-confirmation-status" ).html('Deactivate');
                }


            }
            $('#edit-share-registries-list').submit(function (e){
                e.preventDefault();
                var formValidate2 = $('#edit-share-registries-list').parsley().validate();
                validateFront();
               var shareRegistryIdPut = $('#edit-share-registries-list input[data-api-attr=id]').val();
               //var registriesAttrs = ['srType','srCountry','shareRegistry','srEmail','srURL','validUntil','status','notes','srAddress','srPhone'];
               var registriesAttrs = ['srType','srCountry','shareRegistry','srEmail','srURL','notes','srAddress','srPhone','validUntil', 'status'];
               var dataString = {};

               _.each(registriesAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('#edit-share-registries-list .form-control[data-api-attr='+key+']:checked').val(); 
                        dataString[key] = parseInt(dataString[key], 10);   
                    }else if(key == 'validUntil' &&
                             moment($('#edit-share-registries-list .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                        dataString[key] =  moment(new Date($('#edit-share-registries-list .form-control[data-api-attr='+key+']').val()),DATE_OUT_FORMAT).valueOf();
                    }else{
                        dataString[key] =  $('#edit-share-registries-list .form-control[data-api-attr='+key+']').val();    
                    }
               });

               console.log(dataString);
                if(formValidate2 == true){
                    dataString = _.omit(dataString, 'status');
                    console.log('success');
                    $('#edit-share-registries-list').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                   $.putJSON(API_ENDPOINT+'share-registries/'+shareRegistryIdPut, dataString, putShareRegistriesCallback);
                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });


            $(".paging-first").click(function(event){
                event.preventDefault();
                
                if($(this).hasClass("disabled") || !PAGING_VARS.first ){
                    return false;
                }
                var params = setUrlForPaging('first');
                clearTpl('#shareRegistries_list');
                populateShareRegistries(params,adjustPagingParams(-(PAGING_VARS.current_page)));

            });


            $(".paging-prev").click(function(event){
                event.preventDefault();

                if($(this).hasClass("disabled") || !PAGING_VARS.prev ){
                    return false;
                }
                var params = setUrlForPaging('prev');
                clearTpl('#shareRegistries_list');
                populateShareRegistries(params,adjustPagingParams(-1));

            });


            $(".paging-next").click(function(event){
                event.preventDefault();
                
                if($(this).hasClass("disabled") || !PAGING_VARS.next ){
                    return false;
                }
                var params = setUrlForPaging('next');
                clearTpl('#shareRegistries_list');
                populateShareRegistries(params,adjustPagingParams(1));

            });


            $(".paging-last").click(function(event){
                event.preventDefault();

                if($(this).hasClass("disabled") || !PAGING_VARS.last){
                    return false;
                }
                var params = setUrlForPaging('last');
                clearTpl('#shareRegistries_list');
                populateShareRegistries(params,adjustPagingParams(Math.floor(PAGING_VARS.records/PAGING_VARS.size)- (PAGING_VARS.current_page) ));

            });


             $("#search-list-frm").submit(function(event){
                event.preventDefault();
                var squery = $('#search-list-frm-query').val();
                
                if($.trim(squery)){
                    PAGING_VARS.query = squery;
                    clearTpl('#shareRegistries_list');
                    
                    var parameters = setUrlForPaging('first');

                    populateShareRegistries(parameters,function(){
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
                        clearTpl('#shareRegistries_list');
                        var params = setUrlForPaging('first');
                        populateShareRegistries(params,adjustPagingParams(-(PAGING_VARS.current_page)));  
                });

                $('.search-query-msg-block').hide();


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
                        clearTpl('#shareRegistries_list');
                        populateShareRegistries(params,function(){
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
                    clearTpl('#shareRegistries_list');
                    populateShareRegistries(params,adjustPagingParams(-(PAGING_VARS.current_page)));  

                });


                $(".js-shorting-placeholder-single").select2("val", "");


                $(document).on('click','.js-btn-update-verify', function(){
                    $('#edit-share-registries-list').removeClass('hidden');
                    $('#edit-share-registries-list').next('#update-model').addClass('hidden');
                });

            // ======================== Form 2 Put here  ==========================
          });
        

        var putShareRegistriesCallback = function(data){
            if(data){
                $('#edit-share-registries-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
            };
            console.log('data update');
            $('#edit-share-registries-list').addClass('hidden');
            $('#edit-share-registries-list').next('#update-model').removeClass('hidden');

            if(!_.isEmpty(menuTable)){

                menuTable.fnDestroy();
                menuTable = null;

            }

            clearTpl('#shareRegistries_list');
            populateShareRegistries(setUrlForPaging('current'));

            // $(document).on('click','.js-btn-update-verify', function(){
            //     $('#edit-share-registries-list').removeClass('hidden');
            //     $('#edit-share-registries-list').next('#update-model').addClass('hidden');
            //         clearTpl('#shareRegistries_list');
            //         populateShareRegistries();
            // });
        };

        // ==================== GET_API_WRONG_RESPONSE code ====================
         $(document).ajaxError(function() {
            $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_wrong_response);
            if($('.loading-sec').hasClass("hidden")){
                $('.loading-sec').addClass('hidden');
                $('#add-share-registries-list input, #add-share-registries-list textarea').val('');
                $('[data-dismiss=modal]').trigger('click');
                $('#failed-model-update').modal('show');
            }
        });

        var statusUpdateCallback = function(data){

                if(data){
                    $('#edit-share-registries-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };

                console.log('data update');
                $('#edit-share-registries-list').addClass('hidden');//Registry record deactivated successfully
                var success_msg = data.status == 30 ? "Registry record deactivated successfully" :  "Registry record activated successfully";
                $('#edit-share-registries-list').closest('.modal-content').find('#update-model-registry-status .mtb50').html(success_msg);
                $('#edit-share-registries-list').closest('.modal-content').find('#update-model-registry-status').removeClass('hidden');


                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }

                clearTpl('#shareRegistries_list');
                populateShareRegistries(setUrlForPaging('current'));

        };


        // ==================== GET_API_WRONG_RESPONSE code end ====================
    </script>   
</body>
</html>
