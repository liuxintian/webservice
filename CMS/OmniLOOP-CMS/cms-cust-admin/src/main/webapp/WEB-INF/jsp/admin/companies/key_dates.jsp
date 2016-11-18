<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>
<link href="<%= baseURL %>/assets/css/datatables/tools/css/dataTables.tableTools.css" rel="stylesheet">
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
                            <h3>List of Key Dates</h3>
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
                            <div class="x_panel pos-rel">
                                <div class="loading-sec hidden"></div>
                                <div class="x_content">

                                    <div class="row">
                                        <div class="col-md-6 col-sm-6 col-xs-6">
                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-key-date"> <i class="fa fa-plus"></i> Add Key Date</button>
                                            <button type="button" class="btn btn-default refresh-events-list"> <i class="fa fa-refresh"></i> Refresh List</button>
                                        </div>
                                        <div class="clearfix"></div>

                                          <div class="x_content" id="remove-search-tools">
                                            <table id="example" class="table table-striped responsive-utilities jambo_table">
                                                <thead>
                                                    <tr class="headings">
                                                        <th>Title</th>
                                                        <th>Event Location</th>
                                                        <th>Type</th>
                                                        <th>Status </th>
                                                        <th class="p-r20">Tags </th>
                                                        <th class="p-r20">Event&nbsp;Date </th>
                                                        <th class=" no-link last text-center" colspan="2"><span class="nobr">Action</span>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                  <tbody id="key_dates_list"></tbody>
                                                </table>
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
    
    <!-- Key Date edit model window  -->
    <div class="modal fade document-edit" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-update" data-dismiss="modal"><span aria-hidden="true">x</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Edit key dates </h4>
                 </div>
                 <form  id="key-date-edit-form" class="form-validation-notify form-horizontal form-label-left">
                 <input data-api-attr="id" type="text" value=""class="hidden">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-title2">Event Title <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="eventTitle" type="text" id="document-title2" maxlength="100" value="" placeholder="ex. Event title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-title-sub2">Event Sub Title <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="eventSubTitle" type="text" id="document-title-sub2" maxlength="100" value="" placeholder="ex. Event Sub Title"  class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="event-location2">Event Location <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="eventLocation" type="text" id="event-location2" maxlength="80" value="" placeholder="enter type" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="key-date-type2">Key Date Type <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="keydateType" type="text" id="key-date-type2" maxlength="80" value="" placeholder="enter type"  class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-date2">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="document-date2" value="" placeholder="DD/MM/YYYY" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">
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
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Full/Partial day</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div id="day-events2" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventFullDay" class="form-control"  type="radio" name="dayEvent" value="P" checked> Partial day event
                                        </label>

                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventFullDay" class="form-control" type="radio" name="dayEvent" value="F"> &nbsp; Full day event &nbsp;
                                        </label>
                                        
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Range/Single time:</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div id="range-events2" class="btn-group" data-toggle="buttons">
                                        <!-- <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventRange" class="form-control" type="radio" name="rangeEvent" value="R" checked> &nbsp; Day/Time range &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventRange" class="form-control"  type="radio" name="rangeEvent" value="S"> Single Day
                                        </label> -->

                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventRange" class="form-control"  type="radio" name="rangeEvent" value="R" checked> Range
                                        </label>

                                        <label class="btn btn-default primary-active " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventRange" class="form-control"  type="radio" name="rangeEvent" value="S"  > Single Day
                                        </label>

                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="event-date2">Event date/dates:<span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="event-date2" value="" data-api-attr="eventDateTime" placeholder="DD/MM/YYYY" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">

                                     <input type="text" id="event-date-single2" value="" data-api-attr="eventDateTimeSpot" required="required" placeholder="DD/MM/YYYY" class="date-picker form-control col-md-7 col-xs-12">

                                     <input data-api-attr="eventStartDateTime" type="text"  class="hidden form-control" value="" />
                                     <input data-api-attr="eventEndDateTime" type="text"  class="hidden form-control" value="" />
                                     <input data-api-attr="eventStartDateSingle" type="text"  class="hidden form-control" value="" />
                                 </div>
                            </div>

                            <!-- <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="event-date-end">Event date End <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="eventEndDateTime" type="text" id="event-date-end" value="" placeholder="DD/MM/YYYY" required="required" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div> -->

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="gender" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input class="form-control" data-api-attr="status" type="radio" name="key-date-status2" value="20"  checked=""> &nbsp; Active &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input class="form-control" data-api-attr="status" type="radio" name="key-date-status2" value="30"> Inactive
                                        </label>
                                    </div>
                                </div>
                            </div>



                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="event-details2">
                                     Event Details (40 chars min, 400 max) : <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="eventDetails" id="event-details2" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="40" data-parsley-maxlength="400" data-parsley-minlength-message="Come on! You need to enter at least a 40 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-note2">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="document-note2"  class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-confirmation-status pull-left" data-toggle="confirmation" data-placement="top">Deactivate</button>
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button data-attr="update-btn" type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>
                <div id="sucess-model-update" class="text-center hidden">
                    <h4 class="mtb50">Key Date status updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 
                <div id="sucess-model-status-update" class="text-center hidden ">
                    <h4 class="mtb50">Key Date status updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>

             </div>
         </div>
     </div>
    <!-- Key Date edit model window end  -->
    <!-- New Key Date model window  -->
    <div class="modal fade add-new-key-date" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-verify" data-dismiss="modal"><span aria-hidden="true">x</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add Key Date</h4>
                 </div>
                 <form id="add-key-date-form" class="form-horizontal form-label-left form-validation-notify">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-title">Event Title <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="eventTitle" type="text" id="document-title" maxlength="100" value="" placeholder="ex. Event title name" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-title-sub">Event Sub Title <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="eventSubTitle" type="text" id="document-title-sub" maxlength="100" value="" placeholder="ex. Event Sub Title"  class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="event-location">Event Location <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="eventLocation" type="text" id="event-location" maxlength="80" value="" placeholder="enter type" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="key-date-type">Key Date Type <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="keydateType" type="text" id="key-date-type" maxlength="80" value="" placeholder="enter type"  class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-date">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="document-date" value="" placeholder="DD/MM/YYYY" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12 new-date-add">
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
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Full/Partial day</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div id="day-events" class="btn-group" data-toggle="buttons">
                                        
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventFullDay" class="form-control"  type="radio" name="dayEvent" value="P" checked> Partial day event
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventFullDay" class="form-control" type="radio" name="dayEvent"  value="F"> &nbsp; Full day event &nbsp;
                                        </label>

                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Range/Single day</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div id="range-events" class="btn-group" data-toggle="buttons">
                                        
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventRange" class="form-control"  type="radio" name="rangeEvent" value="R" checked> Range
                                        </label>

                                        <label class="btn btn-default primary-active " data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="eventRange" class="form-control"  type="radio" name="rangeEvent" value="S"  > Single Day
                                        </label>

                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="event-date">Event date/dates: <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">

                                     <input type="text" id="event-date" value="" data-api-attr="eventDateTime" placeholder="DD/MM/YYYY" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">

                                     <input type="text" id="event-date-single" value="" data-api-attr="eventDateTimeSpot" required="required"  placeholder="DD/MM/YYYY" required class="date-picker form-control col-md-7 col-xs-12">
                                     
                                     <input data-api-attr="eventStartDateTime" type="text"  class="hidden form-control" value="" />
                                     <input data-api-attr="eventEndDateTime" type="text"  class="hidden form-control" value="" />
                                     <input data-api-attr="eventStartDateSingle" type="text"  class="hidden form-control" value="" />
                                 </div>
                            </div>

                            <!-- <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="event-date-end">Event date end <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="eventEndDateTime" type="text" id="event-date-end" value="" placeholder="DD/MM/YYYY" required="required" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div> -->

                            <!-- <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="gender2" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input class="form-control" data-api-attr="status" type="radio" name="key-date-status" value="20"  checked=""> &nbsp; Active &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input class="form-control" data-api-attr="status" type="radio" name="key-date-status" value="30"> Inactive
                                        </label>
                                    </div>
                                </div>
                            </div> -->
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="event-details">
                                     Event Details (40 chars min, 400 max) : <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="eventDetails" id="event-details"  class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="40" data-parsley-maxlength="400" data-parsley-minlength-message="Come on! You need to enter at least a 40 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-note">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="document-note" class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button data-attr="submit-btn" type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
                <div id="sucess-model" class="text-center hidden ">
                    <h4 class="mtb50">Added Date Successfully .</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 

             </div>
         </div>
     </div>
    <!-- New Date Key model window end  -->
    <!-- Document model remove end  -->
    <div class="modal fade document-remove" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-md">
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Key Date remove</h4>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4>Are you sure you want to remove this key date.</h4>
                            <div class="m-t20 text-center">
                                <button class="btn btn-default" data-dismiss="modal">No</button>
                                <button class="btn btn-danger js-remove-executive" data-dismiss="modal">Yes</button>
                            </div>
                        </div>
                    </div>
             </div>
         </div>
     </div>
    <!-- Document model remove end  -->
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
    <!-- Datatables --> 
        <script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script>

    <!-- ============================== Key  Date List api code ============================== -->
    <script type="text/template" id="key_dates_list_template">     
        <$$ _.each(characters, function(keyDates,index){ $$>
            
               <tr class="pointer">
                <td><$$= keyDates.eventTitle $$></td>
                <td><$$= keyDates.eventLocation $$></td>
                <td><$$= keyDates.keydateType $$></td>
                <td>
                    <$$ if(keyDates.status == 20){ $$>
                        <span class="success-text">Active</span>
                    <$$ } else if(keyDates.status == 30){$$>
                        <span class="danger-text">Inactive</span>
                    <$$ } else {$$>
                        <span class="danger-text">Inactive</span>
                    <$$ }$$>
                </td>
                <td  class="text-center"><$$= keyDates.tagRole $$></td>    
                <td>
                    <$$ if(keyDates.eventRange && !keyDates.eventFullDay){ $$>
                        <$$= moment(keyDates.eventStartDateTime).format(DATE_TIME_OUT_FORMAT) $$> - <$$= moment(keyDates.eventEndDateTime).format(DATE_TIME_OUT_FORMAT) $$> 
                    <$$ } else if(keyDates.eventRange && keyDates.eventFullDay){ $$>
                        <$$= moment(keyDates.eventStartDateTime).format(DATE_OUT_FORMAT) $$> - <$$= moment(keyDates.eventEndDateTime).format(DATE_OUT_FORMAT) $$> 
                    <$$ }else if(!keyDates.eventRange){ $$>
                        <$$= moment(keyDates.eventStartDateTime).format(DATE_OUT_FORMAT) $$>
                    <$$ }else { $$>
                        <$$= moment(keyDates.eventStartDateTime).format(DATE_OUT_FORMAT) $$>
                    <$$ } $$>
                </td>

                <td class="btn-sec text-center"><button type="button" class="btn btn-primary btn-xs js-edit-document" data-toggle="modal" data-target=".document-edit"> <i class="fa fa-edit"></i> Edit</button> <input type="text" value="<$$= keyDates.id $$>" class="thisDocId hidden" /></td>
              <!--  <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-remove-sec" data-toggle="modal" data-target=".document-remove"> <i class="fa fa-remove"></i> Remove</button></td> -->
            </tr>

        <$$ }); $$>
    </script>
    <script id ="main-list-render">

        var apiEnds = [] , apiRsps = {}, menuTable;
        var getComKeyDates = function(path) {
            // body...
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };

        var getKeyDates = function(path,id) {
            // body...
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: API_ENDPOINT+path+'/'+id
            });
        };

        var chainedStepTwo = function(inputResponse){
            apiRsps[_.last(apiEnds)] = inputResponse;
            var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
            return getKeyDates('key-dates',compId);
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
          

          var populateDayKeyMenu = function(){
            $('.x_panel').find('.loading-sec').removeClass('hidden');
            getComKeyDates('key-dates').then(function (adata) {
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                // body...

                injectTpl('#key_dates_list',adata).then(function () {
                    // Do something brilliant here!
                   //alert("Do something brilliant here!");
                    // here code data will be loaded than run this code 
                    $('.x_panel').find('.loading-sec').addClass('hidden');
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
                    
                    // here code data will be loaded 
                });
            },function(reason) {
                       $('.x_panel').find('.loading-sec').addClass('hidden');
                        //console.log(reason); // Error!
                    });

          };

          var clearTpl = function(id){
            $( id ).empty();
            //$( id ).detach();
          }

          $(document).ready(populateDayKeyMenu);

    </script>
    <!-- ============================== Key Date List api code ============================== -->
    <script type="text/javascript" id = "modal-popup-render-add">


        var onlyDatePicker = function (ele,cbForm) {

            dateTimePickerConfig = {
                singleDatePicker: true,
                showDropdowns: true,
                opens:'left',
                locale: {
                    format: DATE_OUT_FORMAT
                },
                calender_style: "picker_4"
            };
            dateFormat = DATE_OUT_FORMAT;

            // }else{

            //     dateTimePickerConfig = {
            //         timePicker: true,
            //         singleDatePicker:single
            //     };
            //     dateFormat = DATE_OUT_FORMAT;

            // }


            $(ele).daterangepicker(dateTimePickerConfig).on('apply.daterangepicker', function(ev, picker) {
                //console.log(moment(picker.startDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
                //console.log(moment(picker.endDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
                $(cbForm+' input[data-api-attr=eventStartDateSingle]').val(moment(picker.startDate.format(dateFormat)).valueOf()) ;
                //$(cbForm+' input[data-api-attr=eventEndDateTime]').val(moment(picker.endDate.format(dateFormat)).valueOf()) ;
            });

        };

        var dateControlCreator = function (ele,cbForm,fullDay,single) {
            single = single ? single : false;
            single =  !single;
            // body...
            
            var currentValue = $(ele).val().split('-');
            //var currentFormat = ($(ele).val().length > 25) : DATE_TIME_OUT_FORMAT ? DATE_OUT_FORMAT;
            //var currentStartDate = $.trim(currentValue[0]);
            //var currentEndDate = $.trim(currentValue[0]);

            $(ele).daterangepicker('destroy');
            var dateTimePickerConfig, dateFormat;

            // var dateTimePickerConfig = {
            //         timePicker: true,
            //         opens:'left',
            //         timePickerIncrement: 5,
            //         locale: {
            //             format: 'MM/DD/YYYY h:mm A'
            //         }
            //     };
            // var dateFormat = DATE_TIME_OUT_FORMAT;

            if(fullDay){

                dateTimePickerConfig = {
                        timePicker: false,
                        singleDatePicker:single,
                        showDropdowns: true,
                        opens:'left',
                        locale: {
                            format: DATE_OUT_FORMAT
                        }
                        /* locale: {
                            //format: 'MM/DD/YYYY'
                        } */
                };
                dateFormat = DATE_OUT_FORMAT;

            }else{

                dateTimePickerConfig = {
                    timePicker: true,
                    singleDatePicker:single,
                    showDropdowns: true,
                    opens:'left',
                    timePickerIncrement: 5,
                    locale: {
                        format: DATE_TIME_OUT_FORMAT
                    }
                	/* locale: {
                    	//format: 'MM/DD/YYYY h:mm A'
                	} */
                
                };
                dateFormat = DATE_TIME_OUT_FORMAT;

            }

            var eventStartDateTimeForm = parseInt($(cbForm+' input[data-api-attr=eventStartDateTime]').val(),10);
            var eventEndDateTimeForm = parseInt($(cbForm+' input[data-api-attr=eventEndDateTime]').val(),10);

            if(eventStartDateTimeForm && eventEndDateTimeForm){
                var currentStartDate = moment(moment(eventStartDateTimeForm).valueOf()).format(dateFormat);
                var currentEndDate = moment(moment(eventEndDateTimeForm).valueOf()).format(dateFormat);
                $(ele).val(currentStartDate+' - '+currentEndDate);    
            }
            

            $(ele).daterangepicker(dateTimePickerConfig).on('apply.daterangepicker', function(ev, picker) {
                $(cbForm+' input[data-api-attr=eventStartDateTime]').val(moment(picker.startDate.format(dateFormat)).valueOf()) ;
                $(cbForm+' input[data-api-attr=eventEndDateTime]').val(moment(picker.endDate.format(dateFormat)).valueOf()) ;
            });

        };

        $(document).ready(function () {

            var evenDateTime = '';
            var startDateTime   = '';
            var endDateTime  = '';
            
            // var dayEventFn = function(){
            //     if($('#day-events input[data-attr=nonFullDayEvent]').prop("checked")){
            //         console.log('none full day event');
            //         $('#event-date').daterangepicker({
            //             timePicker: true,
            //             opens:'left',
            //             timePickerIncrement: 5,
            //             locale: {
            //                 format: 'MM/DD/YYYY h:mm A'
            //             }
            //         }).on('apply.daterangepicker', function(ev, picker) {
            //             //console.log(moment(picker.startDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
            //             //console.log(moment(picker.endDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
            //             $('#add-key-date-form input[data-api-attr=eventStartDateTime]').val(moment(picker.startDate.format(DATE_TIME_OUT_FORMAT)).valueOf()) ;
            //             $('#add-key-date-form input[data-api-attr=eventEndDateTime]').val(moment(picker.endDate.format(DATE_TIME_OUT_FORMAT)).valueOf()) ;
            //         });     
            //     }else{
            //         console.log('Full day event');
            //         $('#event-date').daterangepicker({
            //             timePicker: false,
            //             opens:'left',
            //           //  timePickerIncrement: 5,
            //             locale: {
            //                 format: 'MM/DD/YYYY'
            //             }
            //         }).on('apply.daterangepicker', function(ev, picker) {
            //             //console.log(moment(picker.startDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
            //             //console.log(moment(picker.endDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
            //             $('#add-key-date-form input[data-api-attr=eventStartDateTime]').val(moment(picker.startDate.format(DATE_OUT_FORMAT)).valueOf()) ;
            //             $('#add-key-date-form input[data-api-attr=eventEndDateTime]').val(moment(picker.endDate.format(DATE_OUT_FORMAT)).valueOf()) ;
            //         });
            //     }
            // };
            //dayEventFn();
            dateControlCreator('#event-date','#add-key-date-form',false,false);
            onlyDatePicker('#event-date-single','#add-key-date-form',false,false);
            $('#event-date-single').hide().prop("required", false);

            $("#tagRole").select2({
              tags: true,
              tokenSeparators: [',', ' ']
            });
            $("#tagRole + .select2, #tagRole2 + .select2").css({'width':'100%'});


            $('#day-events input[data-api-attr=eventFullDay]').on('change', function(e){
                //dayEventFn();
                console.log(e);
                var fullDayEvent = $('#day-events input[data-api-attr=eventFullDay]:checked').val() == 'F' ? true : false;
                var rangeEvent = $('#range-events input[data-api-attr=eventRange]:checked').val() == 'R' ? true : false;
                //$('#event-date').daterangepicker('destroy');
                if(!rangeEvent){
                    $( "#day-events input[data-api-attr=eventFullDay]" ).prop("disabled", true);
                    $( "#day-events label").addClass( "disabled" );
                    $('#event-date').hide().prop("required", false);;
                    $('#event-date-single').show().prop("required", true);;
                    onlyDatePicker('#event-date-single','#add-key-date-form',fullDayEvent,rangeEvent);      
                }else{

                    $( "#day-events input[data-api-attr=eventFullDay]" ).prop("disabled", false);
                    $( "#day-events label").removeClass( "disabled" );
                    $('#event-date-single').hide().prop("required", false);
                    $('#event-date').show().prop("required", true);
                    dateControlCreator('#event-date','#add-key-date-form',fullDayEvent);
                }

            });


            $('#range-events input[data-api-attr=eventRange]').on('change', function(e){
                //dayEventFn();
                console.log(e);
                //var fullDayEvent = $('#day-events input[data-api-attr=eventFullDay]:checked').prop("checked") ? true : false;
                //var rangeEvent = $('#range-events input[data-api-attr=eventRange]:checked').prop("checked") ? true : false;

                var fullDayEvent = $('#day-events input[data-api-attr=eventFullDay]:checked').val() == 'F' ? true : false;
                var rangeEvent = $('#range-events input[data-api-attr=eventRange]:checked').val() == 'R' ? true : false;

                //$('#event-date').daterangepicker('destroy');
                if(!rangeEvent){
                    $( "#day-events input[data-api-attr=eventFullDay]" ).prop( "disabled", true );
                    $( "#day-events label").addClass( "disabled" );
                    $('#event-date').hide().prop("required", false);;
                    $('#event-date-single').show().prop("required", true);
                    onlyDatePicker('#event-date-single','#add-key-date-form',fullDayEvent,rangeEvent);
                }else{
                    $( "#day-events input[data-api-attr=eventFullDay]" ).prop( "disabled", false );
                    $( "#day-events label").removeClass( "disabled" );
                    $('#event-date-single').hide().prop("required", false);
                    $('#event-date').show().prop("required", true);
                    dateControlCreator('#event-date','#add-key-date-form',fullDayEvent);
                }
            });

            // var dayEventFn2 = function(){
            //     if($('#day-events2 input[data-attr=nonFullDayEvent]').prop("checked")){
            //         console.log('edit none full day event');
            //         $('#event-date2').daterangepicker({
            //             timePicker: true,
            //             opens:'left',
            //             timePickerIncrement: 5,
            //             locale: {
            //                 format: 'MM/DD/YYYY h:mm A'
            //             }
            //         }).on('apply.daterangepicker', function(ev, picker) {
            //             //console.log(moment(picker.startDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
            //             //console.log(moment(picker.endDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
            //             $('#key-date-edit-form input[data-api-attr=eventStartDateTime]').val(moment(picker.startDate.format(DATE_TIME_OUT_FORMAT)).valueOf()) ;
            //             $('#key-date-edit-form input[data-api-attr=eventEndDateTime]').val(moment(picker.endDate.format(DATE_TIME_OUT_FORMAT)).valueOf()) ;
            //         });    
            //     }else{
            //         console.log('edit Full day event');
            //         $('#event-date2').daterangepicker({
            //             timePicker: false,
            //             opens:'left',
            //          //   timePickerIncrement: 5,
            //             locale: {
            //                 format: 'MM/DD/YYYY'
            //             }
            //         }).on('apply.daterangepicker', function(ev, picker) {
            //             //console.log(moment(picker.startDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
            //             //console.log(moment(picker.endDate.format(DATE_TIME_OUT_FORMAT)).valueOf());
            //             $('#key-date-edit-form input[data-api-attr=eventStartDateTime]').val(moment(picker.startDate.format(DATE_OUT_FORMAT)).valueOf()) ;
            //             $('#key-date-edit-form input[data-api-attr=eventEndDateTime]').val(moment(picker.endDate.format(DATE_OUT_FORMAT)).valueOf()) ;
            //         });
            //     }
            // };

            dateControlCreator('#event-date2','#key-date-edit-form',false,false);
            onlyDatePicker('#event-date-single2','#add-key-date-form',false,false);
            $('#event-date-single2').hide().prop("required", false);;
            $('#day-events2 input[data-api-attr=eventFullDay]').on('change', function(e){
                //dayEventFn2();
                console.log(e);
                var fullDayEvent = $('#day-events2 input[data-api-attr=eventFullDay]').prop("checked") ? true : false;
                var rangeEvent = $('#range-events2 input[data-api-attr=eventRange]').prop("checked") ? true : false;

                //$('#event-date2').daterangepicker('destroy');
                dateControlCreator('#event-date2','#key-date-edit-form',fullDayEvent,rangeEvent);


                var fullDayEvent = $('#day-events2 input[data-api-attr=eventFullDay]:checked').val() == 'F' ? true : false;
                var rangeEvent = $('#range-events2 input[data-api-attr=eventRange]:checked').val() == 'R' ? true : false;
                //$('#event-date').daterangepicker('destroy');
                if(!rangeEvent){
                    $( "#day-events2 input[data-api-attr=eventFullDay]" ).prop("disabled", true);
                    $( "#day-events2 label").addClass( "disabled" );
                    $('#event-date2').hide().prop("required", false);
                    $('#event-date-single2').show().prop("required", true);
                    onlyDatePicker('#event-date-single2','#key-date-edit-form',fullDayEvent,rangeEvent);      
                }else{

                    $( "#day-events2 input[data-api-attr=eventFullDay]" ).prop("disabled", false);
                    $( "#day-events2 label").removeClass( "disabled" );
                    $('#event-date-single2').hide().prop("required", false);;
                    $('#event-date2').show().prop("required", true);
                    dateControlCreator('#event-date2','#key-date-edit-form',fullDayEvent,rangeEvent);
                }

            });

            $('#range-events2 input[data-api-attr=eventRange]').on('change', function(e){
                //dayEventFn();
                console.log(e);
                //var fullDayEvent = $('#day-events input[data-api-attr=eventFullDay]:checked').prop("checked") ? true : false;
                //var rangeEvent = $('#range-events input[data-api-attr=eventRange]:checked').prop("checked") ? true : false;

                var fullDayEvent = $('#day-events2 input[data-api-attr=eventFullDay]:checked').val() == 'F' ? true : false;
                var rangeEvent = $('#range-events2 input[data-api-attr=eventRange]:checked').val() == 'R' ? true : false;

                //$('#event-date').daterangepicker('destroy');
                if(!rangeEvent){
                    $( "#day-events2 input[data-api-attr=eventFullDay]" ).prop( "disabled", true );
                    $( "#day-events2 label").addClass( "disabled" );
                    $('#event-date2').hide().prop("required", false);;
                    $('#event-date-single2').show().prop("required", true);
                    onlyDatePicker('#event-date-single2','#key-date-edit-form',fullDayEvent,rangeEvent);
                }else{
                    $( "#day-events2 input[data-api-attr=eventFullDay]" ).prop( "disabled", false );
                    $( "#day-events2 label").removeClass( "disabled" );
                    $('#event-date-single2').hide().prop("required", false);;
                    $('#event-date2').show().prop("required", true);
                    dateControlCreator('#event-date2','#key-date-edit-form',fullDayEvent,rangeEvent);
                    

                }
            });

            $.listen('parsley:field:validate', function () {
                validateFront();
            });
            var validateFront = function () {
                
                if (true === $('#add-key-date-form').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            // ======================================== Form 1 here start
           // Form post code here  
           $('#add-key-date-form').submit(function (e) {
                    e.preventDefault();
                var formValidate = $('#add-key-date-form').parsley().validate();
                
                validateFront();

                var tagRoleVal = $('#tagRole').val();
                var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                $('#add-key-date-form input[data-api-attr=tagRole]').val(tagRoleVar);

               //var keyDateAttrs = ['eventTitle','eventSubTitle', 'eventLocation','keydateType','validUntil','eventDetails','notes'];
               var keyDateAttrs = ['eventTitle','eventSubTitle', 'eventLocation','keydateType','eventDetails','notes','validUntil','eventStartDateTime', 'eventEndDateTime','eventFullDay','eventRange','eventStartDateSingle','tagRole'];
               var dataString = {};

               _.each(keyDateAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('#add-key-date-form .form-control[data-api-attr='+key+']:checked').val(); 
                        dataString[key] = parseInt(dataString[key], 10);    
                    }else if((key == 'validUntil')  &&
                             moment($('#add-key-date-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                        dataString[key] =  moment($('#add-key-date-form .form-control[data-api-attr='+key+']').val(),DATE_OUT_FORMAT).valueOf();
                    }else if( (key == 'eventStartDateTime' || key == 'eventEndDateTime')  &&
                             moment($('#add-key-date-form .form-control[data-api-attr='+key+']').val(), DATE_TIME_OUT_FORMAT).isValid() ){

                        dataString[key] =  moment($('#add-key-date-form .form-control[data-api-attr='+key+']').val(),DATE_OUT_FORMAT).valueOf();

                        //dataString[key] = $('#add-key-date-form .form-control[data-api-attr='+key+']').val();

                    }else if(key == 'eventStartDateSingle'){

                         //dataString[key] =  moment($('#add-key-date-form .form-control[data-api-attr='+key+']').val()).valueOf();
                         dataString[key] =  moment(parseInt($('#add-key-date-form .form-control[data-api-attr='+key+']').val(),10),DATE_OUT_FORMAT).valueOf();
                    }else if(key == 'eventFullDay'){
                        dataString[key] =  $('#add-key-date-form .form-control[data-api-attr='+key+']:checked').val() ==  'F' ? true : false;
                    }else if(key == 'eventRange'){
                        dataString[key] =  $('#add-key-date-form .form-control[data-api-attr='+key+']:checked').val() ==  'R' ? true : false;
                    }else{
                        dataString[key] =  $('#add-key-date-form .form-control[data-api-attr='+key+']').val();    
                    }
               });


               //console.log('form 1'+dataString);

                if(formValidate == true){
                    dataString['status'] = 20;
                    //console.log('success');
                    $('#add-key-date-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                        //eventStartDateSingle
                        dataString['eventStartDateTime'] = (!dataString['eventRange']) ? dataString['eventStartDateSingle'] : dataString['eventStartDateTime'];
                        dataString['eventEndDateTime'] = (!dataString['eventRange']) ? dataString['eventStartDateTime'] : dataString['eventEndDateTime'];
                        dataString['eventDateTime'] = (!dataString['eventRange']) ? dataString['eventStartDateTime'] : dataString['eventStartDateTime'];
                        $.postJSON(API_ENDPOINT+'key-dates', dataString, postCompanyDocuCallback);
                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });
          // Form post code here end 
            var postCompanyDocuCallback = function(data){
                if(data){
                    $('#add-key-date-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                $('#add-key-date-form input[type=text], #add-key-date-form textarea').val('');
                $('#add-key-date-form').addClass('hidden');
                $('#add-key-date-form').next('#sucess-model').removeClass('hidden');
                //alert('Added new Key Date');
                $('input[data-api-attr=validUntil].new-date-add').val(defaultDateVal);
                    datePlugin();
                    onlyDatePicker('#event-date-single','#add-key-date-form',false,false);
                    dateControlCreator('#event-date','#add-key-date-form',false,false);
                $("select#tagRole").select2("val", "");
                //console.log(data); // show response 
                
                if(!_.isEmpty(menuTable)){
                    //menuTable.destroy();
                    menuTable.fnDestroy();
                    menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();
                }
                clearTpl('#key_dates_list');
                populateDayKeyMenu();
            };

            $('.js-btn-verify').on('click', function(){
                $('#add-key-date-form').removeClass('hidden');
                $('#add-key-date-form').next('#sucess-model').addClass('hidden');
            });

            $('.refresh-events-list').on('click', function(){
            	
                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }
            	
                clearTpl('#key_dates_list');
                populateDayKeyMenu();
            });
            
            var getKeyDatesCallback = function(data){
                console.log(data.eventTitle);
                var editDateFormat = '';
                if(data){
                    data.eventFullDay = data.eventFullDay ? data.eventFullDay : false;
                    data.eventRange = data.eventRange ? data.eventRange : false;
                    editDateFormat = (!data.eventRange || data.eventFullDay) ? DATE_OUT_FORMAT : DATE_TIME_OUT_FORMAT;
                    $('#key-date-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                }
                $('#key-date-edit-form input[data-api-attr=eventTitle]').val(data.eventTitle);
                $('#key-date-edit-form input[data-api-attr=eventSubTitle]').val(data.eventSubTitle);
                $('#key-date-edit-form input[data-api-attr=eventLocation]').val(data.eventLocation);
                $('#key-date-edit-form input[data-api-attr=keydateType]').val(data.keydateType);
                $('#key-date-edit-form input[data-api-attr=tagRole]').val(data.tagRole);
                //$('#key-date-edit-form input[data-api-attr=validUntil]').val(data.validUntil);
                
                var tagValueRole = $('#key-date-edit-form input[data-api-attr=tagRole]').val();
                var tagValueRole2 = tagValueRole.split(',');

                $("#tagRole2").select2({
                  tags: true,
                  tokenSeparators: [',', ' '],
                  data: tagValueRole2
                });

                $("#tagRole2").val(tagValueRole2);
                $("#tagRole2").trigger("change");
                $('#tagRole2 + span.select2-container').css({"width":"100%"});
                var tagRoleLength = $('#key-date-edit-form select#tagRole2').val();
                  if(tagRoleLength.length == 1){
                    if(tagRoleLength == ''){
                        $('#key-date-edit-form li.select2-selection__choice').remove();
                        $('#tagRole2').empty();
                    }
                }

                if(data.validUntil){

                    $('#key-date-edit-form input[data-api-attr=validUntil]').val(
                        moment(data.validUntil).format(DATE_OUT_FORMAT)
                    );

                }else{
                    $('#key-date-edit-form input[data-api-attr=validUntil]').val(
                        moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                    );
                }


                if(data.eventStartDateTime){

                    $('#key-date-edit-form input[data-api-attr=eventStartDateTime]').val(
                        data.eventStartDateTime
                        //moment(data.eventStartDateTime).format(DATE_TIME_OUT_FORMAT)
                    );


                    // $('#key-date-edit-form input[data-api-attr=eventStartDateTime]').val(
                    //     moment(data.eventStartDateTime).format(DATE_TIME_OUT_FORMAT)
                    // );

                    $('#key-date-edit-form input[data-api-attr=eventStartDateSingle]').val(
                        data.eventStartDateTime
                        //moment(data.eventStartDateTime).format(DATE_OUT_FORMAT)
                    );


                }else{
                    $('#key-date-edit-form input[data-api-attr=eventStartDateTime]').val(
                        new Date().getTime()
                        //moment(new Date().getTime()).format(DATE_TIME_OUT_FORMAT)
                    );

                    // $('#key-date-edit-form input[data-api-attr=eventStartDateSingle]').val(
                    //     new Date().getTime()
                    //     //moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                    // );


                    $('#key-date-edit-form input[data-api-attr=eventStartDateSingle]').val(
                        new Date().getTime()
                        //moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                    );
                }

                if(data.eventEndDateTime){

                    $('#key-date-edit-form input[data-api-attr=eventEndDateTime]').val(
                        data.eventEndDateTime
                        //moment(data.eventEndDateTime).format(DATE_TIME_OUT_FORMAT)
                    );

                }else{
                    $('#key-date-edit-form input[data-api-attr=eventEndDateTime]').val(
                        new Date().getTime()
                        //moment(new Date().getTime()).format(DATE_TIME_OUT_FORMAT)
                    );
                }

                var rangeEventHolder = $('#range-events2');
                var fullDayEventFlag = $('#day-events2');
                
                if(!data.eventRange){
                    $( "#day-events2 input[data-api-attr=eventFullDay]" ).prop( "disabled", true );
                    $( "#day-events2 label").addClass( "disabled" );
                    $('#event-date2').hide().prop("required", false);
                    $('#event-date-single2').show().prop("required", true);
                    onlyDatePicker('#event-date-single2','#key-date-edit-form',data.eventFullDay,data.eventRange);
                    
                    rangeEventHolder.find('input[data-api-attr=eventRange][value=S]').prop("checked", true)
                    rangeEventHolder.find('input[data-api-attr=eventRange][value=S]').closest( "label").addClass('active');
                    rangeEventHolder.find('input[data-api-attr=eventRange][value=R]').prop("checked", false)
                    rangeEventHolder.find('input[data-api-attr=eventRange][value=R]').closest( "label").removeClass('active');
                    //$('#range-events2 input[data-api-attr=eventDateTime][value=S]').prop("checked", true);
                }else{

                    $( "#day-events2 input[data-api-attr=eventFullDay]" ).prop("disabled", false);
                    $( "#day-events2 label").removeClass( "disabled" );
                    $('#event-date-single2').hide().prop("required",false);
                    $('#event-date2').show().prop("required", true);
                    dateControlCreator('#event-date2','#key-date-edit-form',data.eventFullDay,data.eventRange);
                    rangeEventHolder.find('input[data-api-attr=eventRange][value=S]').prop("checked", false);
                    rangeEventHolder.find('input[data-api-attr=eventRange][value=S]').closest( "label").removeClass('active');
                    rangeEventHolder.find('input[data-api-attr=eventRange][value=R]').prop("checked", true);
                    rangeEventHolder.find('input[data-api-attr=eventRange][value=R]').closest( "label").addClass('active');

                }

                if(data.eventFullDay){
                    fullDayEventFlag.find('input[data-api-attr=eventFullDay][value=F]').prop("checked", true);
                    fullDayEventFlag.find('input[data-api-attr=eventFullDay][value=F]').closest( "label").addClass('active');
                    fullDayEventFlag.find('input[data-api-attr=eventFullDay][value=P]').prop("checked", false);
                    fullDayEventFlag.find('input[data-api-attr=eventFullDay][value=P]').closest( "label").removeClass('active');
                }else{
                    fullDayEventFlag.find('input[data-api-attr=eventFullDay][value=F]').prop("checked", false);
                    fullDayEventFlag.find('input[data-api-attr=eventFullDay][value=F]').closest( "label").removeClass('active');
                    fullDayEventFlag.find('input[data-api-attr=eventFullDay][value=P]').prop("checked", true);
                    fullDayEventFlag.find('input[data-api-attr=eventFullDay][value=P]').closest( "label").addClass('active');
                }

                // ===============
                
                var startDateTimeEdit   = parseInt($('#key-date-edit-form input[data-api-attr=eventStartDateTime]').val(),10);
                var endDateTimeEdit = parseInt($('#key-date-edit-form input[data-api-attr=eventEndDateTime]').val(),10);
                var dateSpotVal = parseInt($('#key-date-edit-form input[data-api-attr=eventStartDateSingle]').val(),10);

                $('#key-date-edit-form #event-date2').val( moment(startDateTimeEdit).format(editDateFormat) +' - '+ moment(endDateTimeEdit).format(editDateFormat));
                $('#key-date-edit-form #event-date-single2').val(moment(dateSpotVal).format(editDateFormat));

                // ==============

                $('#key-date-edit-form textarea[data-api-attr=eventDetails]').val(data.eventDetails);
                $('#key-date-edit-form textarea[data-api-attr=notes]').val(data.notes);
                $('#key-date-edit-form input[data-api-attr=id]').val(data.id);
                datePlugin();


                if(data.status == 20){
                    $('#key-date-edit-form input[name=key-date-status2]').prop('checked',true);
                    $('#key-date-edit-form input[name=key-date-status2]').prop('checked',false);
                    $('#key-date-edit-form input[name=key-date-status2]').closest('label').removeClass('active');
                    $('#key-date-edit-form input[name=key-date-status2][value=20]').closest('label').addClass('active');
                }else if(!data.status || data.status == 30){
                    $('#key-date-edit-form input[name=key-date-status2][value=30]').prop('checked',true);
                    $('#key-date-edit-form input[name=key-date-status2][value=20]').prop('checked',false);
                    $('#key-date-edit-form input[name=key-date-status2]').closest('label').removeClass('active');
                    $('#key-date-edit-form input[name=key-date-status2][value=30]').closest('label').addClass('active');
                };

                $('#key-date-edit-form input[name=key-date-status2]').prop( "disabled", true);  
                $('#key-date-edit-form input[name=key-date-status2]').addClass('disabled');
                $('#key-date-edit-form input[name=key-date-status2]').closest('label').addClass('disabled');


                $('#key-date-edit-form').removeClass('hidden');
                $('#key-date-edit-form').closest('.modal-content').find('#sucess-model-status-update').addClass('hidden');
                var statusUpdatePayload = _.extend(_.clone(data), {status: data.status == 30 ? 20 : (data.status == 20 ? 30 : data.status) });

                if(_.contains([20, 30], data.status)){
                    addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'key-dates/'+data.id,statusUpdatePayload,statusUpdateCallback);    
                }else if(!data.status){
                    statusUpdatePayload['status'] = 20;
                    addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'key-dates/'+data.id,statusUpdatePayload,statusUpdateCallback);  
                }

                if(data.status != 20){
                    $( "#key-date-edit-form [type=submit]" ).prop( "disabled", true).addClass('disabled');
                    $( ".js-confirmation-status" ).html('Activate');
                }else{
                    $( "#key-date-edit-form [type=submit]" ).prop( "disabled", false).removeClass('disabled');
                    $( ".js-confirmation-status" ).html('Deactivate');
                }

            };

            var putKeyDateCallback = function(data){
                if(data){
                    $('#key-date-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                //console.log('data ===' + data.id);
                $('#key-date-edit-form').addClass('hidden');
                $('#key-date-edit-form').next('#sucess-model-update').removeClass('hidden');

                if(!_.isEmpty(menuTable)){
                //menuTable.destroy();
                    menuTable.fnDestroy();
                    menuTable = null;
                // empty in case the columns change
                //$('#example').empty();
                }

                clearTpl('#key_dates_list');
                populateDayKeyMenu();

            };

            // ======================================== Form 2 here start
            // Edit Key Date form post code here 
                $(document).on('click', '.js-edit-document', function(){
                    //dayEventFn2();
                    $('#tagRole2').empty();
                    var dataString = '';
                    var documentId = $(this).next('.thisDocId').val();
                    $('#key-date-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                    $.getJSON(API_ENDPOINT+'key-dates/'+documentId, dataString, getKeyDatesCallback);
                });

                $('#key-date-edit-form').submit(function (e) {
                    e.preventDefault();
                    var formValidate = $('#key-date-edit-form').parsley().validate();
                    validateFront();
                    var docListId = $('#key-date-edit-form input[data-api-attr=id]').val();

                    var tagRoleVal = $('#tagRole2').val();
                    var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                    $('#key-date-edit-form input[data-api-attr=tagRole]').val(tagRoleVar);


                    //var keyDateAttrs = ['eventTitle','eventSubTitle','eventLocation','keydateType','validUntil','eventDetails','notes'];
                    var keyDateAttrs = ['eventTitle','eventSubTitle','eventLocation','keydateType','eventDetails','notes','validUntil','status','eventStartDateTime', 'eventEndDateTime','eventFullDay','eventRange','eventStartDateSingle','tagRole'];
                   var dataString = {};

                       _.each(keyDateAttrs, function(key){
                            if(key == 'status'){
                                dataString[key] =  $('#key-date-edit-form .form-control[data-api-attr='+key+']:checked').val();  
                                dataString[key] = parseInt(dataString[key], 10);   
                            }else if((key == 'validUntil') &&
                                     moment($('#key-date-edit-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                                dataString[key] =  moment($('#key-date-edit-form .form-control[data-api-attr='+key+']').val(),DATE_OUT_FORMAT).valueOf();

                            }else if((key == 'eventStartDateTime' || key == 'eventEndDateTime')  &&
                                moment($('#key-date-edit-form .form-control[data-api-attr='+key+']').val(), DATE_TIME_OUT_FORMAT).isValid()){

                                dataString[key] =  moment($('#key-date-edit-form .form-control[data-api-attr='+key+']').val(),DATE_TIME_OUT_FORMAT).valueOf();
                                //dataString[key] =  $('#key-date-edit-form .form-control[data-api-attr='+key+']').val();
                            }else if(key == 'eventStartDateSingle'){

                                //dataString[key] =  moment($('#add-key-date-form .form-control[data-api-attr='+key+']').val()).valueOf();
                                dataString[key] =  moment(parseInt($('#key-date-edit-form .form-control[data-api-attr='+key+']').val(),10),DATE_OUT_FORMAT).valueOf();
                            }else if(key == 'eventFullDay'){
                                dataString[key] =  $('#key-date-edit-form .form-control[data-api-attr='+key+']:checked').val() ==  'F' ? true : false;
                            }else if(key == 'eventRange'){
                                dataString[key] =  $('#key-date-edit-form .form-control[data-api-attr='+key+']:checked').val() ==  'R' ? true : false;
                            }else{
                                dataString[key] =  $('#key-date-edit-form .form-control[data-api-attr='+key+']').val();    
                            }
                       });
                    if(formValidate == true){
                        console.log('success');
                        dataString = _.omit(dataString, 'status');
                        $('#key-date-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                            dataString['eventStartDateTime'] = (!dataString['eventRange']) ? dataString['eventStartDateSingle'] : dataString['eventStartDateTime'];
                            dataString['eventEndDateTime'] = (!dataString['eventRange']) ? dataString['eventStartDateTime'] : dataString['eventEndDateTime'];
                            dataString['eventDateTime'] = (!dataString['eventRange']) ? dataString['eventStartDateTime'] : dataString['eventStartDateTime'];
                            $.putJSON(API_ENDPOINT+'key-dates/'+docListId, dataString, putKeyDateCallback);
                    }else{
                       // alert(UI_MESSAGES.form_validation_error);
                       toastNotifications(UI_MESSAGES.form_validation_error);
                    }
                });


                $(document).on('click', '.js-btn-update', function(){
                    $('#key-date-edit-form').removeClass('hidden');
                    $('#key-date-edit-form').next('#sucess-model-update').addClass('hidden');
                });

            // Edit Key Date form post code here end 

            // ======================================== Form Remove code
            $('.js-remove-sec').on('click', function(){
                var currentExecutiveBtn = $(this);
               /*     $('.js-remove-executive').on('click', function(){
                    $(currentExecutiveBtn).parents('.pointer').remove();
                });*/
            });
            // Form Remove Function here end 

            
        });
        
        // ==================== GET_API_WRONG_RESPONSE code ====================
         $(document).ajaxError(function() {
            $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_wrong_response);
            if($('.loading-sec').hasClass("hidden")){
                $('.loading-sec').addClass('hidden');
                $('#add-key-date-form input, #add-key-date-form textarea').val('');
                $('[data-dismiss=modal]').trigger('click');
                $('#failed-model-update').modal('show');
            }
        });
        // ==================== GET_API_WRONG_RESPONSE code end ====================
        
        var statusUpdateCallback = function(data){

                if(data){
                    $('#key-date-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                console.log('data update');
                $('#key-date-edit-form').addClass('hidden');
                var success_msg = data.status == 30 ? "Key date record deactivated successfully" :  "Key date record activated successfully";
                $('#key-date-edit-form').closest('.modal-content').find('#sucess-model-status-update .mtb50').html(success_msg);
                $('#key-date-edit-form').closest('.modal-content').find('#sucess-model-status-update').removeClass('hidden');

                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }
                clearTpl('#key_dates_list');
                populateDayKeyMenu();

        };

    </script>       
</body>
</html>
