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
                    <div class="page-title clearfix">
                        <div class="title_left">
                            <h3>List of Feeds</h3>
                        </div>
                        <div class="title_right">
                            <div class="col-md-10 m-t0 col-sm-10 col-xs-10 form-group pull-right">
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
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel pos-rel">
                                <div class="loading-sec hidden"></div>
                                <div class="x_content">
                                    <div class="row">


                                        <div  class="clearfix pull-right col-md-6 col-sm-6 col-xs-6 pull-right">
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
                                            <button type="button" class=" pull-left btn btn-default" data-toggle="modal" data-target=".add-new-feed-list"> <i class="fa fa-plus"></i> Add new feed</button>
                                            <button type="button" class="btn btn-default refresh-feeds-list" style="margin-left: 4px; margin-bottom: 15px;"> <i class="fa fa-refresh"></i> Refresh List</button>
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

                                   <!--  <div class="row" id="remove-search-tools">
                                    <table id="example" class="table table-striped responsive-utilities jambo_table">
                                        <thead class="hidden">
                                            <tr class="headings">
                                                <th class="column-title">Title </th>
                                                <th class="column-title">Email ID </th>
                                                <th class="column-title">Feed Type </th>
                                                <th class="column-title">Company</th>
                                                <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                                <th class="bulk-actions" colspan="7">
                                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                                </th>
                                            </tr>
                                        </thead>
                                        <thead>
                                            <tr class="headings">
                                                <th class="column-title">Title </th>
                                                <th class="column-title">Email ID </th>
                                                <th class="column-title">Feed Type </th>
                                                <th class="column-title">Company</th>
                                                <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                                <th class="bulk-actions" colspan="7">
                                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr class="even pointer">
                                                <td class=" "><a href="javascript:void(0);">Hr Report</a></td>
                                                <td class=" ">hr.repo@xyz.com</td>
                                                <td class=" ">Finance</td>
                                                <td>Hiring IT Team</td>
                                                <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".feed-list-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                             </tr>
                                        </tbody>
                                   </table>
                                        
                                    </div> -->

                        <!-- <div class="row">
                            <div class="col-md-3 col-sm-3 col-xs-3">    
                                    <ul class="list-group">
                                      <li class="list-group-item">
                                        <span class="badge">X</span>
                                        Keyword <span style="font-size: 100%;" class="label label-success">search string</span> matches 453 results
                                      </li>
                                    </ul>
                            </div>
                        </div> -->

                        <div class="row" id="modify-sec">



                            <table id="example" class="table table-striped responsive-utilities jambo_table">
                                <!-- <thead class="hidden">
                                    <tr class="headings">
                                        <th class="column-title">Title </th>
                                        <th class="column-title">Company </th>
                                        <th class="column-title">Contact </th>
                                        <th class="column-title">Tags </th>
                                        <th class="column-title">Valid Until </th>
                                        <th class="column-title">Status </th>
                                        <th class="column-title no-link last" colspan="2"><span class="nobr">Action</span></th>
                                        <th class="bulk-actions" colspan="6">
                                            <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                        </th>
                                    </tr>
                                </thead> -->
                                <thead>
                                    <tr class="headings">
                                        <th class="column-title">Title </th>
                                        <th class="column-title">Company </th>
                                        <th class="column-title">Contact </th>
                                        <th class="column-title">Tags </th>
                                        <th class="column-title">Valid Until </th>
                                        <th class="column-title">Status </th>
                                        <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                        <th class="bulk-actions" colspan="6">
                                            <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                        </th>
                                    </tr>
                                </thead>
                                    <tbody id="feeds_list"></tbody>
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
    <div class="modal fade add-new-feed-list" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close  js-btn-verify" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add Feed List</h4>
                 </div>
                 <form id="add-feed-list"  class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title-name">Title of the Feed <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="feedTitle"  type="text" id="title-name" maxlength="100" value="" placeholder="ex. title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email-id-feed">Email ID <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="feedContact" type="email" id="email-id-feed" placeholder="john.doe@hotmail.com" data-parsley-required="true" class="form-control" data-parsley-required="true" name="email" />
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="source-url">Source URL <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input  data-api-attr="feedURL" type="url" id="source-url" name="website" data-parsley-required="true" placeholder="www.website.com" class="form-control col-md-7 col-xs-12">
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
                                             <input class="form-control" data-api-attr="status" type="radio" name="status-menu" value="30" id="status-inactive"> Deavtive
                                         </label>
                                     </div>
                                 </div>
                             </div> -->
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-company">Feed Company <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="feedCompany" type="text" id="feed-company" maxlength="100" value="" placeholder="ex. feed company" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-type"> Feed Type 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12 select-width">
                                     <select data-api-attr="feedType" id="feed-type" class="select2_single form-control" tabindex="-1">
                                        <option value="">Select one</option>
                                        <option value="asx">ASX</option>
                                        <option value="othres">Othres</option>
                                    </select>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-note">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="feed-note"  class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-note">
                                     Description (20 chars min, 400 max)  <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="feedDesc" id="feed-note" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="400" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="20"></textarea>
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
                    <h4 class="mtb50">Feed added successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 

             </div>
         </div>
     </div>
    <!-- new Document model window end  -->
    <!-- edit Document model window  -->
    <div class="modal fade feed-list-edit" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-update-verify" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Edit Feed record</h4>
                 </div>
                 <form  id="edit-feed-list" class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <input data-api-attr="id"  type="text"  value="" class="hidden">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title-name2">Title of the Feed <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="feedTitle"  type="text" id="title-name2" maxlength="100" value="" placeholder="ex. title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email-id-feed2">Email ID <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="feedContact" type="email" id="email-id-feed2" placeholder="ex. xyz@acb.com" data-parsley-required="true" class="form-control" data-parsley-required="true" name="email"  />
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="source-url2">Source URL <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input  data-api-attr="feedURL" type="url" id="source-url2" name="website" data-parsley-required="true" placeholder="www.website.com" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="tagRole2">Tags </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <select id="tagRole2"  class="form-control col-md-7 col-xs-12 select2-hidden-accessible" multiple="" tabindex="-1" aria-hidden="true"></select>
                                     <input type="text"  data-api-attr="tagRole" class="hidden form-control" />
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
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-company2">Feed Company <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="feedCompany" type="text" id="feed-company2" maxlength="100" value="" placeholder="ex. feed company" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-type2"> Feed Type  
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12 select-width">
                                     <select data-api-attr="feedType" id="feed-type2" class="select2_single form-control" tabindex="-1">
                                        <option value="">Select one</option>
                                        <option value="asx">ASX</option>
                                        <option value="othres">Othres</option>
                                    </select>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-note2">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="feed-note2" class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="feed-note2">
                                     Description (20 chars min, 400 max)  <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="feedDesc" id="feed-note2" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="400" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="20"></textarea>
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
                    <h4 class="mtb50">Feed record updated successfully.</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 
                <div id="update-model-feed-status" class="text-center hidden ">
                    <h4 class="mtb50">Feed record status updated successfully.</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>


             </div>
         </div>
     </div>
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
    <!-- Feed model remove end  -->
    <div class="modal fade document-feed-del" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-md">
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Confirm delete</h4>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4>Are you sure you want to remove this feed?</h4>
                            <div class="m-t20 text-center">
                                <button class="btn btn-default" data-dismiss="modal">No</button>
                                <button class="btn btn-danger js-confirm-remove-feed" data-value="" data-dismiss="modal">Yes</button>
                            </div>
                        </div>
                    </div>
             </div>
         </div>
     </div>
    <!-- Document model remove end  -->

    <!-- edit Document model window end  -->
    <%@ include file="/WEB-INF/jsp/common/common_js_scripts.jspf"%> 
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
    <!-- Datatables --> 
   <!-- <script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
    <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script> -->

    <script src="<%= baseURL %>/assets/js/dataTables-1.10.11/js/jquery.dataTables.min.js" type="text/javascript"></script>


    <script type="text/template" id="feeds_list_template">     
        <$$ _.each(characters, function(feedItems,index){ $$>

            <tr class="pointer">
                <td class=" "><$$= feedItems.feedTitle $$></td>
                <td class=" "><$$= feedItems.feedCompany $$></td>
                <td class=" "><$$= feedItems.feedContact $$></td>
                <td class=" "><$$= feedItems.tagRole $$></td>
                <td class=" "><$$= moment(feedItems.validUntil).format(DATE_OUT_FORMAT) $$></td>
                

                <td>
                    <$$ if(feedItems.status == 20){ $$>
                        <span class="success-text">Active</span>
                    <$$ } else if(feedItems.status == 30){$$>
                        <span class="danger-text">Inactive</span>
                    <$$ } else {$$>
                        <span class="danger-text">Inactive</span>
                    <$$ }$$>
                </td>
                <td class="btn-sec">
                    <button type="button" class="btn js-edit-row btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".feed-list-edit"> <i class="fa fa-edit"></i> Edit <input type="text" value="<$$= feedItems.id $$>" class="hidden js-feed-id" /></button>
                </td>
            <!--    <td class="btn-sec">
                    <button type="button" data-value="<$$= feedItems.id $$>" class="btn btn-primary btn-xs js-remove-feed" data-toggle="modal" data-target=".document-feed-del"> <i class="fa fa-remove"></i> Remove</button>
                </td> -->
            </tr>    

        <$$ }); $$>
    </script>
    <script id = "main-list-render">

        var apiEnds = [] , apiRsps = {}, menuTable;
        var getFeedsItems = function(path,params) {
            // body...
            apiEnds.push(path);
            return $.ajax({
              url: _.isNull(params) ? API_ENDPOINT+path :  API_ENDPOINT+path+'?'+params
            });
        };

        // var getFeedItems = function(path,id) {
        //     // body...
        //     apiEnds.push(path+'/'+id);
        //     return $.ajax({
        //       url: API_ENDPOINT+path+'/'+id
        //     });
        // };

        // var chainedStepTwo = function(inputResponse){
        //     apiRsps[_.last(apiEnds)] = inputResponse;
        //     var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
        //     return getFeedItems('feeds',compId);
        // };

        var injectTpl = function (id,data) {
              var deferred = $.Deferred();

              deferred.done(function(val,data){
                var template_cmp = _.template($(val+"_template").html());
                $(val).html(template_cmp({'characters':data}));
              });

              deferred.resolve(id,data);
              return deferred.promise();
          };
          
          var populateFeeds = function(params,pagingCallback){
            var params_q = (params && params.length) ? params.join('&') : null;
            $('.x_panel').find('.loading-sec').removeClass('hidden');
            getFeedsItems('feeds',params_q).then(function (adata) {
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                // body...

                // adata = _.filter(adata, function(val){ 
                //             return (val.status == 20 || val.status == 30); 
                //         });

                
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

                injectTpl('#feeds_list',adata).then(function () {
                    $('.x_panel').find('.loading-sec').addClass('hidden');
                    // Do something brilliant here!
                    //alert("Do something brilliant here!");
                    // here code data will be loaded than run this code 
                    var asInitVals = new Array();
                    
                    menuTable = $('#example').dataTable({
                        "oLanguage": {
                            "sSearch": "Search all columns: &nbsp; "
                        },
                        searching: true,
                        "ordering": true,
                        /*"aoColumnDefs": [
                            {
                                'bSortable': false,
                                'aTargets': [0]
                            } //disables sorting for column one
                        ],*/
                        'paging': false,
                        "lengthMenu": [ [-1], ["All"] ],
                        "pageLength": -1,
                       // 'iDisplayLength': 12,
                        "sPaginationType": "numbers",
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
                    
                    // ======================================== Form Remove code
                    $('.js-remove-feed').on('click', function(){
                        var feedID = $(this).attr( "data-value" );
                        if(feedID){
                            $('.js-confirm-remove-feed').attr("data-value",feedID);
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
            //$('.js-confirmation-status').confirmation('destroy');
            //$( id ).detach();
          };


          var postFeedsCallback = function(data){
                 if(data){
                    $('#add-feed-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                $('#add-feed-list input[type=text], #add-feed-list input[type=email], #add-feed-list input[type=url], #add-feed-list select, #add-feed-list textarea').val('');
                $('#add-feed-list').addClass('hidden');
                $('#add-feed-list').next('#sucess-model').removeClass('hidden');
                $('input[data-api-attr=validUntil].new-date-add').val(defaultDateVal);
                datePlugin();
                //alert('Added new Menu');


                console.log(data); // show response 
                $("select#tagRole").select2("val", "");


                clearTpl('#feeds_list');
                populateFeeds(setUrlForPaging('current'));
          };

          var getFeedsCallback = function(data){
                if(data){
                    $('#edit-feed-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                }
                console.log(data.feedTitle);
                $('#edit-feed-list input[data-api-attr=feedTitle]').val(data.feedTitle);
                $('#edit-feed-list input[data-api-attr=feedContact]').val(data.feedContact);
                $('#edit-feed-list input[data-api-attr=feedURL]').val(data.feedURL);
                $('#edit-feed-list input[data-api-attr=tagRole]').val(data.tagRole);

                if(data.validUntil){
                    $('#edit-feed-list input[data-api-attr=validUntil]').val(
                        moment(data.validUntil).format(DATE_OUT_FORMAT)
                    );
                }else{
                    //TO BE REMOVED LATER
                    $('#edit-feed-list input[data-api-attr=validUntil]').val(
                        moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                    );

                }

                //$('#edit-feed-list input[data-api-attr=validUntil]').val(data.validUntil);
                var menuStatusVal = data.status;
                

                if(menuStatusVal == 20){
                    $('#edit-feed-list input[name=status-menu2][value=20]').prop('checked',true);
                    $('#edit-feed-list input[name=status-menu2][value=30]').prop('checked',false);
                    $('#edit-feed-list input[name=status-menu2]').closest('label').removeClass('active');
                    $('#edit-feed-list input[name=status-menu2][value=20]').closest('label').addClass('active');
                }else if(menuStatusVal == 30){
                    $('#edit-feed-list input[name=status-menu2][value=30]').prop('checked',true);
                    $('#edit-feed-list input[name=status-menu2][value=20]').prop('checked',false);
                    $('#edit-feed-list input[name=status-menu2]').closest('label').removeClass('active');
                    $('#edit-feed-list input[name=status-menu2][value=30]').closest('label').addClass('active');
                };
                //$('#edit-feed-list input[data-api-attr=status]').val(data.status);
                $('#edit-feed-list input[data-api-attr=feedCompany]').val(data.feedCompany);
                if(data.feedType == ''){
                    $('#edit-feed-list select[data-api-attr=feedType]').val('');
                }else{
                    $('#edit-feed-list select[data-api-attr=feedType]').val(data.feedType);
                }
                $('#edit-feed-list textarea[data-api-attr=notes]').val(data.notes);
                $('#edit-feed-list textarea[data-api-attr=feedDesc]').val(data.feedDesc);
                $('#edit-feed-list input[data-api-attr=id]').val(data.id);


                var tagValueRole = $('#edit-feed-list input[data-api-attr=tagRole]').val();
                var tagValueRole2 = tagValueRole.split(',');
                $("#tagRole2").select2({
                  tags: true,
                  tokenSeparators: [',', ' '],
                  data: tagValueRole2
                });
                $("#tagRole2").val(tagValueRole2);
                $("#tagRole2").trigger("change");
                $('#tagRole2 + span.select2-container').css({"width":"100%"});
                var tagRoleLength = $('#edit-feed-list select#tagRole2').val();
                  if(tagRoleLength.length == 1){
                    if(tagRoleLength == ''){
                        $('#edit-feed-list li.select2-selection__choice').remove();
                        $('#tagRole2').empty();
                    }
                }


                datePlugin();
                
                $('#edit-feed-list input[name=status-menu2]').prop( "disabled", true);  
                $('#edit-feed-list input[name=status-menu2]').addClass('disabled');
                $('#edit-feed-list input[name=status-menu2]').closest('label').addClass('disabled');

                $('#edit-feed-list').closest('.modal-content').find('#update-model-feed-status').addClass('hidden');
                var statusUpdatePayload = _.extend(_.clone(data), {status: data.status == 30 ? 20 : (data.status == 20 ? 30 : data.status) });
                
                $('.js-confirmation-status').removeClass('disabled').prop( "disabled", false);    
                if(_.contains([20, 30], data.status)){
                    addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'feeds/'+data.id,statusUpdatePayload,statusUpdateCallback);    
                }else if(!data.status){
                    statusUpdatePayload['status'] = 20;
                    addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'feeds/'+data.id,statusUpdatePayload,statusUpdateCallback);  
                }else{
                    $('.js-confirmation-status').addClass('disabled').prop( "disabled", true);
                }
                if(data.status != 20){
                    $( "#edit-feed-list [type=submit]" ).prop( "disabled", true).addClass('disabled');
                    $( ".js-confirmation-status" ).html('Activate');

                }else{
                    $( "#edit-feed-list [type=submit]" ).prop( "disabled", false).removeClass('disabled');
                    $( ".js-confirmation-status" ).html('Deactivate');
                }
                
          };

          var putFeedsCallback = function(data){
                if(data){
                    $('#edit-feed-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                console.log('data update');
                $('#edit-feed-list').addClass('hidden');
                $('#edit-feed-list').next('#update-model').removeClass('hidden');


                clearTpl('#feeds_list');
                populateFeeds(setUrlForPaging('current'));

           };

           var validateFront = function () {
                if (true === $('#add-feed-list').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
           };

          $(document).ready(function(){
                 populateFeeds(setUrlForPaging('first')); // GET list of Feeds

                $("#tagRole").select2({
                  tags: true,
                  tokenSeparators: [',', ' ']
                });

                $("#tagRole + .select2, #tagRole2 + .select2").css({'width':'100%'});


                $.listen('parsley:field:validate', function () {
                    validateFront();
                });

                // =========== Form 1 POST ==================
                $('#add-feed-list').submit(function (e){
                    e.preventDefault();
                    var formValidate = $('#add-feed-list').parsley().validate();
                    validateFront();
                   var tagRoleVal = $('#tagRole').val();
                   var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                   $('#add-feed-list input[data-api-attr=tagRole]').val(tagRoleVar);
                   //var feedAttrs = ['feedCompany','feedContact','feedDesc','feedTitle','feedType','feedURL','notes','status','tagRole', 'validUntil'];
                   var feedAttrs = ['feedCompany','feedContact','feedDesc','feedTitle','feedType','feedURL','notes','validUntil','tagRole'];
                   var dataString = {};

                   _.each(feedAttrs, function(key){
                        if(key == 'status'){
                            dataString[key] =  $('#add-feed-list .form-control[data-api-attr='+key+']:checked').val(); 
                            dataString[key] = parseInt(dataString[key], 10);   
                        }else if(key == 'validUntil'){
                            dataString[key] =  moment($('#add-feed-list .form-control[data-api-attr='+key+']').val(),DATE_OUT_FORMAT).valueOf();
                        }else{
                            dataString[key] =  $('#add-feed-list .form-control[data-api-attr='+key+']').val();    
                        }
                   });

                   console.log(dataString);
                    if(formValidate == true){
                        dataString['status'] = 20;
                        console.log('success');
                        $('#add-feed-list').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                       $.postJSON(API_ENDPOINT+'feeds', dataString, postFeedsCallback);
                    }else{
                        //alert(UI_MESSAGES.form_validation_error);
                        toastNotifications(UI_MESSAGES.form_validation_error);
                    }

               });


                $('.js-btn-verify').on('click', function(){
                    $('#add-feed-list').removeClass('hidden');
                    $('#add-feed-list').next('#sucess-model').addClass('hidden');
                });
                
                
                $(".refresh-feeds-list").click(function(){
                    $('.search-query-msg-block').hide();
                    PAGING_VARS.query = '';
                    PAGING_VARS.sort_by = '';
                    $('#search-list-frm-query').val('');
                    clearTpl('#feeds_list');
                    var params = setUrlForPaging('first');
                    populateFeeds(params,adjustPagingParams(-(PAGING_VARS.current_page)));  
               });
                // =========== Form 1 Post End ==============
                // ======================== Form 2 Put here  ==========================
                $(document).on('click', '.js-edit-row' ,function(){
                    $('#tagRole2').empty();
                    var feedId = $(this).find('.js-feed-id').val();
                    var dataString = '';
                    $('#edit-feed-list').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                    $.getJSON(API_ENDPOINT+'feeds/'+feedId, dataString, getFeedsCallback);
                });


                $('#edit-feed-list').submit(function (e){
                    e.preventDefault();
                    var formValidate2 = $('#edit-feed-list').parsley().validate();
                    validateFront();
                   var feedIdPut = $('#edit-feed-list input[data-api-attr=id]').val();

                    var tagRoleVal = $('#tagRole2').val();
                    var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                    $('#edit-feed-list input[data-api-attr=tagRole]').val(tagRoleVar);

                    var tagRoleVal = $('#tagRole2').val();
                    var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                    $('#edit-feed-list input[data-api-attr=tagRole]').val(tagRoleVar);


                   //var feedAttrs = ['feedCompany','feedContact','feedDesc','feedTitle','feedType','feedURL','notes','status','tagRole', 'validUntil'];
                   var feedAttrs = ['feedCompany','feedContact','feedDesc','feedTitle','feedType','feedURL','notes','validUntil', 'status','tagRole'];
                   var dataString = {};

                   _.each(feedAttrs, function(key){
                        if(key == 'status'){
                            dataString[key] =  $('#edit-feed-list .form-control[data-api-attr='+key+']:checked').val();  
                            dataString[key] = parseInt(dataString[key], 10);  
                        }else if(key == 'validUntil' &&
                                 moment($('#edit-feed-list .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                            dataString[key] =  moment(new Date($('#edit-feed-list .form-control[data-api-attr='+key+']').val()),DATE_OUT_FORMAT).valueOf();
                        }else{
                            dataString[key] =  $('#edit-feed-list .form-control[data-api-attr='+key+']').val();    
                        }
                   });

                   console.log(dataString);
                    if(formValidate2 == true){
                        dataString = _.omit(dataString, 'status');
                        console.log('success');
                        $('#edit-feed-list').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                       $.putJSON(API_ENDPOINT+'feeds/'+feedIdPut, dataString, putFeedsCallback);
                    }else{
                       // alert(UI_MESSAGES.form_validation_error);
                       toastNotifications(UI_MESSAGES.form_validation_error);
                    }

                });


                $(document).on('click','.js-btn-update-verify', function(){
                        $('#edit-feed-list').removeClass('hidden');
                        $('#edit-feed-list').next('#update-model').addClass('hidden');

                });


                $('.js-confirm-remove-feed').on('click', function(){
                    var feedID = $(this).attr( "data-value" );
                    if(feedID){
                        $.deleteResource(API_ENDPOINT+'feeds/'+feedID,putFeedsCallback);
                    }
                });




                $(".paging-first").click(function(event){
                    event.preventDefault();
                    
                    if($(this).hasClass("disabled") || !PAGING_VARS.first ){
                        return false;
                    }
                    var params = setUrlForPaging('first');
                    clearTpl('#feeds_list');
                    populateFeeds(params,adjustPagingParams(-(PAGING_VARS.current_page)));

                });


                $(".paging-prev").click(function(event){
                    event.preventDefault();

                    if($(this).hasClass("disabled") || !PAGING_VARS.prev ){
                        return false;
                    }
                    var params = setUrlForPaging('prev');
                    clearTpl('#feeds_list');
                    populateFeeds(params,adjustPagingParams(-1));

                });


                $(".paging-next").click(function(event){
                    event.preventDefault();
                    
                    if($(this).hasClass("disabled") || !PAGING_VARS.next ){
                        return false;
                    }
                    var params = setUrlForPaging('next');
                    clearTpl('#feeds_list');
                    populateFeeds(params,adjustPagingParams(1));

                });


                $(".paging-last").click(function(event){
                    event.preventDefault();

                    if($(this).hasClass("disabled") || !PAGING_VARS.last){
                        return false;
                    }
                    var params = setUrlForPaging('last');
                    clearTpl('#feeds_list');
                    populateFeeds(params,adjustPagingParams(Math.floor(PAGING_VARS.records/PAGING_VARS.size)- (PAGING_VARS.current_page) ));

                });

                 $("#search-list-frm").submit(function(event){
                    event.preventDefault();
                    var squery = $('#search-list-frm-query').val();
                    
                    if($.trim(squery)){
                        PAGING_VARS.query = squery;
                        clearTpl('#feeds_list');
                        
                        //var parameters = ['page=0'];
                        //parameters.push(['q='+squery]);
                        
                        var parameters = setUrlForPaging('first');

                        // if(PAGING_VARS.sort_by){
                        //     parameters.push(['sortBy='+PAGING_VARS.sort_by]);
                        // }
                        populateFeeds(parameters,function(){
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
                        clearTpl('#feeds_list');
                        var params = setUrlForPaging('first');
                        populateFeeds(params,adjustPagingParams(-(PAGING_VARS.current_page)));  
                });
                $('.search-query-msg-block').hide();
                     
                //adjustPagingParams(0);

                
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
                        clearTpl('#feeds_list');
                        populateFeeds(params,function(){
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
                    clearTpl('#feeds_list');
                    populateFeeds(params,adjustPagingParams(-(PAGING_VARS.current_page)));  

                });


                $(".js-shorting-placeholder-single").select2("val", "");


                //$(".js-shorting-placeholder-single2").select2("val", "");
                //$(".js-shorting-placeholder-single2").select2("open");
                //$(".js-shorting-placeholder-single2").select2("close");
                
                
            // ======================== Form 2 Put here  ==========================


            /*$('.js-confirmation-status').on('click', function(){
                $('[data-toggle=confirmation]').confirmation({

                });
            });*/
          });
            // ==================== GET_API_WRONG_RESPONSE code ====================
             $(document).ajaxError(function() {
                $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_wrong_response);
                if($('.loading-sec').hasClass("hidden")){
                    $('.loading-sec').addClass('hidden');
                    $('#add-feed-list input, #add-feed-list textarea, #add-feed-list select').val('');
                    $('[data-dismiss=modal]').trigger('click');
                    $('#failed-model-update').modal('show');
                }
            });


           var statusUpdateCallback = function(data){

                if(data){
                    $('#edit-feed-list').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                console.log('data update');
                $('#edit-feed-list').addClass('hidden');
                //$('#edit-feed-list').next('#update-model').removeClass('hidden');
                var success_msg = data.status == 30 ? "Feed record deactivated successfully" :  "Feed record has been activated successfully";
                //$('#update-model-feed-status .mtb50').html(success_msg);
                $('#edit-feed-list').closest('.modal-content').find('#update-model-feed-status .mtb50').html(success_msg);
                //$('#edit-feed-list').closest('.modal-content').find('#update-model-feed-status').html(success_msg);
                $('#edit-feed-list').closest('.modal-content').find('#update-model-feed-status').removeClass('hidden');
                //$('#update-model-feed-status').modal("show");


                clearTpl('#feeds_list');
                populateFeeds(setUrlForPaging('current'));

           };

           //addStatusUpdateBtn($('.js-confirmation-status'),statusUpdateCallback);

            // ==================== GET_API_WRONG_RESPONSE code end ====================
    </script>   
</body>
</html>
