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
                            <h3>List of Announcements</h3>
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
                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-announcement"> <i class="fa fa-plus"></i> Add new Announcements</button>
                                            <button type="button" class="btn btn-default refresh-announcement-list"> <i class="fa fa-refresh"></i> Refresh Table</button>
                                        </div>
                                        <div class="clearfix"></div>

                                          <div class="x_content" id="remove-search-tools">
                                            <table id="example" class="table table-striped responsive-utilities jambo_table">
                                                <thead>
                                                    <tr class="headings">
                                                        <th>Title</th>
                                                        <th>Type</th>
                                                        <th>Status </th>
                                                        <th class="p-r20">Valid&nbsp;Until </th>
                                                        <th class="p-r20">Tags </th>
                                                        <th class=" no-link last text-center"><span class="nobr">Action</span>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                  <tbody id="announcement_list"></tbody>
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
    
    <!-- Document edit model window  -->
    <div class="modal fade document-edit" tabindex="-1" role="dialog" aria-hidden="true">
        
         <div class="modal-dialog modal-lg  pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-update" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Edit announcement </h4>
                 </div>
                 <form id="announcement-edit-form" class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                        <input data-api-attr="id" type="text" value=""  class="hidden">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-title2">Title of the Announcement<span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="documentTitle" type="text" id="document-title2" maxlength="100" value="" placeholder="" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-type2">Announcement  Type <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="announcementType" type="text" id="document-type2" maxlength="80" value="" placeholder="" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-url2">Announcement reference Url <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="documentLink" type="url" id="document-url2"  value="" placeholder="" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-date2">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="validUntil" type="text" id="document-date2" value="" placeholder="" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div>

                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="tagRole2">Tags 
                                 </label>
                                 <!-- <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="tagRole" type="text" id="tagRole2" maxlength="80" value="" placeholder="ex. HCL" required="required" class="form-control col-md-7 col-xs-12">
                                 </div> -->

                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <select id="tagRole2"  class="form-control col-md-7 col-xs-12 select2-hidden-accessible" multiple="" tabindex="-1" aria-hidden="true"></select>
                                     <input type="text"  data-api-attr="tagRole" class="hidden form-control" />
                                 </div>
                            </div>

                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="announcement-date2">Announcement Date <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="announcementDate" type="text" id="announcement-date2" value="" placeholder="" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="gender" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" class="form-control" type="radio" name="announcement-status2" value="20"  checked=""> &nbsp; Active &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" class="form-control" type="radio" name="announcement-status2" value="30"> Inactive
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Price sensitive <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="price-sensitive2" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="isPriceSensitive" type="radio" name="price-sensitive-status2" value="true"> &nbsp; Price sensitive &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="isPriceSensitive" type="radio" name="price-sensitive-status2" value="false"> Not Price sensitive
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-note2">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="document-note2"  class="form-control" name="message">Section for note .</textarea>
                                 </div>
                             </div>
                             <!-- <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-description2">
                                     Description (20 chars min, 400 max) : <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="docDesc" id="document-description2" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10">Some text about this description ...</textarea>
                                 </div>
                             </div>
                             <div class="form-group upload-box-sec">
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-0a">Document image:
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input data-api-attr="docThumbnail" id="file-0a" class="file" type="file"  data-min-file-count="1">
                                  </div>
                              </div> -->
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-confirmation-status pull-left" data-toggle="confirmation" data-placement="top">Deactivate</button>
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button data-attr="update-btn" type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>
                <div id="sucess-model-update" class="text-center hidden ">
                    <h4 class="mtb50">Announcement updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 
                <div id="sucess-model-status-update" class="text-center hidden ">
                    <h4 class="mtb50">Announcement status updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>



             </div>
         </div>
     </div>
    <!-- Document edit model window end  -->
    <!-- new Document model window  -->
    <div class="modal fade add-new-announcement" tabindex="-1" role="dialog" aria-hidden="true">

         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
            <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-verify" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add Announcement</h4>
                 </div>
                 <form id="add-announcement-form" class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-title">Title of the Announcement <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="documentTitle" type="text" id="document-title" maxlength="100" value="" placeholder="ex. profit report" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-type">Announcement type <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="announcementType" type="text" id="document-type" maxlength="80" value="" placeholder="enter type"  class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-url">Announcement reference Url <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="documentLink" type="url" id="document-url"  value="" placeholder="www.website.com" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
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
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="announcement-date">Announcement Date <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="announcementDate" type="text" id="announcement-date" value="" placeholder="" data-parsley-required="true" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            
                            
                            <!-- <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="gender2" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" class="form-control" type="radio" name="announcement-status" value="20"  checked=""> &nbsp; Active &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" class="form-control" type="radio" name="announcement-status" value="30"> Inactive
                                        </label>
                                    </div>
                                </div>
                            </div> -->

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Price sensitive <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="price-sensitive" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="isPriceSensitive" type="radio" name="price-sensitive-status" value="true"  checked=""> &nbsp; Price sensitive &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="isPriceSensitive" type="radio" name="price-sensitive-status" value="false"> Not Price sensitive
                                        </label>
                                    </div>
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
                             
                             <!-- <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-description">
                                     Description (20 chars min, 400 max) : <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="docDesc"  id="document-description" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                             <div class="form-group upload-box-sec">
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-0b">Document image:
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input data-api-attr="docThumbnail" id="file-0b" class="file" type="file"  data-min-file-count="1">
                                  </div>
                              </div> -->
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button data-attr="submit-btn" type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
                <div id="sucess-model" class="text-center hidden ">
                    <h4 class="mtb50">Announcement record added successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 

             </div>
         </div>
     </div>
    <!-- new Document model window end  -->
    <!-- Document model remove end  -->
    <div class="modal fade document-remove" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-md">
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Announcement remove</h4>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4>Are you sure you want to remove this announcement.</h4>
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

    <!-- ============================== Company Document List api code ============================== -->
    <script type="text/template" id="announcement_list_template">     
        <$$ _.each(characters, function(announcementItems,index){ $$>
            
               <tr class="pointer">
				<$$ if(/^http|https:\/\//i.test(announcementItems.documentLink)){ $$>
					<td><a target="_blank" href="<$$= announcementItems.documentLink $$>"><$$= announcementItems.documentTitle $$></a></td>
				<$$ } else {$$>
					<td><a target="_blank" href="http://<$$= announcementItems.documentLink $$>"><$$= announcementItems.documentTitle $$></a></td>
				<$$ }$$>
                <td><$$= announcementItems.announcementType $$></td>
                <td>
                    <$$ if(announcementItems.status == 20){ $$>
                        <span class="success-text">Active</span>
                    <$$ } else if(announcementItems.status == 30){$$>
                        <span class="danger-text">Inactive</span>
                    <$$ } else {$$>
                        <span class="danger-text">Inactive</span>
                    <$$ }$$>
                </td>
                <td><$$= moment(announcementItems.validUntil).format(DATE_OUT_FORMAT) $$></td>
                <td  class="text-center"><$$= announcementItems.tagRole $$></td>
                <td class="btn-sec text-center"><button type="button" class="btn btn-primary btn-xs js-edit-document" data-toggle="modal" data-target=".document-edit"> <i class="fa fa-edit"></i> Edit</button> <input type="text" value="<$$= announcementItems.id $$>" class="thisDocId hidden" /></td>
             <!--   <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-remove-sec" data-toggle="modal" data-target=".document-remove"> <i class="fa fa-remove"></i> Remove</button></td> -->
            </tr>

        <$$ }); $$>
    </script>
    <script id ="main-list-render">

        var apiEnds = [] , apiRsps = {}, menuTable;
        var getComAnnouncementItems = function(path) {
            // body...
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };

        var getAnnouncementItems = function(path,id) {
            // body...
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: API_ENDPOINT+path+'/'+id
            });
        };

        var chainedStepTwo = function(inputResponse){
            apiRsps[_.last(apiEnds)] = inputResponse;
            var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
            return getAnnouncementItems('announcements',compId);
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
          

          var populateIRMenu = function(){
            $('.x_panel').find('.loading-sec').removeClass('hidden');

            getComAnnouncementItems('announcements').then(function (adata) {
                // body...
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                injectTpl('#announcement_list',adata).then(function () {
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
            }, function(reason) {
                        $('.x_panel').find('.loading-sec').addClass('hidden');
                        console.log(reason + 'reason error'); // Error!
                    });

          };

          var clearTpl = function(id){
            $( id ).empty();
            //$( id ).detach();
          }

          $(document).ready(populateIRMenu);

    </script>
    <!-- ============================== Company Document List api code ============================== -->
    <script type="text/javascript" id = "modal-popup-render-add">

        $(document).ready(function () {
            $('#announcement-date').daterangepicker({
                singleDatePicker: true,
                showDropdowns: true,
                calender_style: "picker_4",
                locale: {
                    format: DATE_OUT_FORMAT
                }
            }, function (start, end, label) {
                console.log(start.toISOString(), end.toISOString(), label);
            });


            $.listen('parsley:field:validate', function () {
                validateFront();
            });

            $("#tagRole").select2({
              tags: true,
              tokenSeparators: [',', ' ']
            });
            $("#tagRole + .select2, #tagRole2 + .select2").css({'width':'100%'});

            var validateFront = function () {
                
                if (true === $('#add-announcement-form').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            // ======================================== Form 1 here start
           // Form post code here 
           $('#add-announcement-form').submit(function (e){
                e.preventDefault();
                var formValidate = $('#add-announcement-form').parsley().validate();
                validateFront();
               
                var tagRoleVal = $('#tagRole').val();
                var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                $('#add-announcement-form input[data-api-attr=tagRole]').val(tagRoleVar);

               //var announcementAttrs = ['documentTitle','announcementType', 'documentLink','validUntil','notes'];
               var announcementAttrs = ['documentTitle','announcementType','documentLink','notes','announcementDate','validUntil','isPriceSensitive','tagRole'];
               var dataString = {};

               _.each(announcementAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] =  $('#add-announcement-form input[name=announcement-status][data-api-attr='+key+']:checked').val();
                        dataString[key] = parseInt(dataString[key], 10);
                    }else if(key == 'isPriceSensitive'){
                        dataString[key] =  $('#add-announcement-form input[name=price-sensitive-status][data-api-attr='+key+']:checked').val();    
                    }else if((key == 'announcementDate')  &&  moment($('#add-announcement-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                        dataString[key] =  moment($('#add-announcement-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                    }else if((key == 'validUntil')  &&  moment($('#add-announcement-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                        dataString[key] =  moment($('#add-announcement-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                    }else{
                        dataString[key] =  $('#add-announcement-form .form-control[data-api-attr='+key+']').val();    
                    }
               });

               console.log('form 1'+dataString);

                if(formValidate == true){
                    console.log('success');
                    $('#add-announcement-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                        dataString['status'] = 20;
                        $.postJSON(API_ENDPOINT+'announcements', dataString, postCompanyDocuCallback);
                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });
          // Form post code here end 
            var postCompanyDocuCallback = function(data){
                if(data){
                    $('#add-announcement-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                $('#add-announcement-form input[type=text], #add-announcement-form input[type=url], #add-announcement-form textarea').val('');
                $('#add-announcement-form').addClass('hidden');
                $('#add-announcement-form').next('#sucess-model').removeClass('hidden');
                $('input[data-api-attr=validUntil].new-date-add').val(defaultDateVal);
                    datePlugin();

                $('#announcement-date').daterangepicker({
                    singleDatePicker: true,
                    showDropdowns: true,
                    calender_style: "picker_4",
                    locale: {
                        format: DATE_OUT_FORMAT
                    }
                }, function (start, end, label) {
                    console.log(start.toISOString(), end.toISOString(), label);
                });
                $("select#tagRole").select2("val", "");
                //alert('Added new Document');

                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }
                
                clearTpl('#announcement_list');
                populateIRMenu();

                console.log(data); // show response 
            };

            $('.js-btn-verify').on('click', function(){
                $('#add-announcement-form').removeClass('hidden');
                $('#add-announcement-form').next('#sucess-model').addClass('hidden');
            });

            $('.refresh-announcement-list').on('click', function(){
            	
                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }
            	
                clearTpl('#announcement_list');
                populateIRMenu();
            });
            
            // ======================================== Form 2 here start
            // Edit Document form post code here 
                $(document).on('click', '.js-edit-document', function(){
                    $("#tagRole2").empty();
                    var dataString = '';
                    var documentId = $(this).next('.thisDocId').val();
                    $('#announcement-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                    $.getJSON(API_ENDPOINT+'announcements/'+documentId, dataString, getCompanyDocuCallback);
                });
                var getCompanyDocuCallback = function(data){
                    console.log(data.documentTitle);
                    if(data){
                        $('#announcement-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    }
                    $('#announcement-edit-form input[data-api-attr=documentTitle]').val(data.documentTitle);
                    $('#announcement-edit-form input[data-api-attr=announcementType]').val(data.announcementType);
                    $('#announcement-edit-form input[data-api-attr=documentLink]').val(data.documentLink);
                    $('#announcement-edit-form input[data-api-attr=tagRole]').val(data.tagRole);

                    var tagValueRole = $('#announcement-edit-form input[data-api-attr=tagRole]').val();
                    var tagValueRole2 = tagValueRole.split(',');

                    $("#tagRole2").select2({
                      tags: true,
                      tokenSeparators: [',', ' '],
                      data: tagValueRole2
                    });
                    $("#tagRole2").val(tagValueRole2);
                    $("#tagRole2").trigger("change");
                    $('#tagRole2 + span.select2-container').css({"width":"100%"});
                    var tagRoleLength = $('#announcement-edit-form select#tagRole2').val();
                      if(tagRoleLength.length == 1){
                        if(tagRoleLength == ''){
                            $('#announcement-edit-form li.select2-selection__choice').remove();
                            $('#tagRole2').empty();
                        }
                    }

                    if(data.validUntil){

                        $('#announcement-edit-form input[data-api-attr=validUntil]').val(
                            moment(data.validUntil).format(DATE_OUT_FORMAT)
                        );

                    }else{
                        $('#announcement-edit-form input[data-api-attr=validUntil]').val(
                            moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                        );
                    }

                    if(data.announcementDate){

                        $('#announcement-edit-form input[data-api-attr=announcementDate]').val(
                            moment(data.announcementDate).format(DATE_OUT_FORMAT)
                        );

                    }else{
                        $('#announcement-edit-form input[data-api-attr=announcementDate]').val(
                            moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                        );
                    }

                     $('#announcement-date2').daterangepicker({
                        singleDatePicker: true,
                        showDropdowns: true,
                        calender_style: "picker_4",
                        locale: {
                            format: DATE_OUT_FORMAT
                        }
                    }, function (start, end, label) {
                        console.log(start.toISOString(), end.toISOString(), label);
                    });
                   // $('#announcement-edit-form input[data-api-attr=validUntil]').val(data.validUntil);
                    var menuStatusVal = data.status;
                    if(menuStatusVal == 20){
                        $('#announcement-edit-form input[name=announcement-status2][value=20]').prop('checked',true);
                        $('#announcement-edit-form input[name=announcement-status2][value=30]').prop('checked',false);
                        $('#announcement-edit-form input[name=announcement-status2]').closest('label').removeClass('active');
                        $('#announcement-edit-form input[name=announcement-status2][value=20]').closest('label').addClass('active');
                    }else if(!menuStatusVal || menuStatusVal == 30){
                        $('#announcement-edit-form input[name=announcement-status2][value=30]').prop('checked',true);
                        $('#announcement-edit-form input[name=announcement-status2][value=20]').prop('checked',false);
                        $('#announcement-edit-form input[name=announcement-status2]').closest('label').removeClass('active');
                        $('#announcement-edit-form input[name=announcement-status2][value=30]').closest('label').addClass('active');
                    };

                    var priceSensitiveVal = data.isPriceSensitive;
                    if(priceSensitiveVal == true){
                        $('#announcement-edit-form input[name=price-sensitive-status2][value=true]').prop('checked',true);
                        $('#announcement-edit-form input[name=price-sensitive-status2][value=false]').prop('checked',false);
                        $('#announcement-edit-form input[name=price-sensitive-status2]').closest('label').removeClass('active');
                        $('#announcement-edit-form input[name=price-sensitive-status2][value=true]').closest('label').addClass('active');
                    }else if(priceSensitiveVal == false){
                        $('#announcement-edit-form input[name=price-sensitive-status2][value=false]').prop('checked',true);
                        $('#announcement-edit-form input[name=price-sensitive-status2][value=true]').prop('checked',false);
                        $('#announcement-edit-form input[name=price-sensitive-status2]').closest('label').removeClass('active');
                        $('#announcement-edit-form input[name=price-sensitive-status2][value=false]').closest('label').addClass('active');
                    };
                    $('#announcement-edit-form textarea[data-api-attr=notes]').val(data.notes);
                    $('#announcement-edit-form input[data-api-attr=id]').val(data.id);
                    datePlugin();


                    $('#announcement-edit-form input[name=announcement-status2]').prop( "disabled", true);  
                    $('#announcement-edit-form input[name=announcement-status2]').addClass('disabled');
                    $('#announcement-edit-form input[name=announcement-status2]').closest('label').addClass('disabled');


                    $('#announcement-edit-form').removeClass('hidden');
                    $('#announcement-edit-form').closest('.modal-content').find('#sucess-model-status-update').addClass('hidden');
                    var statusUpdatePayload = _.extend(_.clone(data), {status: data.status == 30 ? 20 : (data.status == 20 ? 30 : data.status) });

                    $('.js-confirmation-status').removeClass('disabled').prop( "disabled", false); 
                    if(_.contains([20, 30], data.status)){
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'announcements/'+data.id,statusUpdatePayload,statusUpdateCallback);    
                    }else if(!data.status){
                        statusUpdatePayload['status'] = 20;
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'announcements/'+data.id,statusUpdatePayload,statusUpdateCallback);  
                    }else{
                        $('.js-confirmation-status').addClass('disabled').prop( "disabled", true);
                    }


                    if(data.status != 20){
                        $( "#announcement-edit-form [type=submit]" ).prop( "disabled", true).addClass('disabled');
                        $( ".js-confirmation-status" ).html('Activate');
                    }else{
                        $( "#announcement-edit-form [type=submit]" ).prop( "disabled", false).removeClass('disabled');
                        $( ".js-confirmation-status" ).html('Deactivate');
                    }


                }
                $('#announcement-edit-form').submit(function (e){
                    e.preventDefault();
                    var formValidate = $('#announcement-edit-form').parsley().validate();
                    validateFront();
                    var docListId = $('#announcement-edit-form input[data-api-attr=id]').val();
                    //var announcementAttrs = ['documentTitle','announcementType','documentLink','validUntil','notes'];

                    var tagRoleVal = $('#tagRole2').val();
                    var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                    $('#announcement-edit-form input[data-api-attr=tagRole]').val(tagRoleVar);

                    var announcementAttrs = ['documentTitle','announcementType','documentLink','notes','validUntil','announcementDate','status','isPriceSensitive','tagRole'];
                    var dataString = {};

                       _.each(announcementAttrs, function(key){
                            if(key == 'status'){
                                dataString[key] =  $('#announcement-edit-form input[name=announcement-status2][data-api-attr='+key+']:checked').val();  
                                dataString[key] = parseInt(dataString[key], 10);  
                            }else if(key == 'isPriceSensitive'){
                                dataString[key] =  $('#announcement-edit-form input[name=price-sensitive-status2][data-api-attr='+key+']:checked').val();    
                            }else if((key == 'announcementDate') &&  moment($('#announcement-edit-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                                dataString[key] =  moment($('#announcement-edit-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                            }else if((key == 'validUntil') &&  moment($('#announcement-edit-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                                dataString[key] =  moment($('#announcement-edit-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                            }else{
                                dataString[key] =  $('#announcement-edit-form .form-control[data-api-attr='+key+']').val();    
                            }
                       });
                    if(formValidate == true){
                        dataString = _.omit(dataString, 'status');
                        console.log('success');
                        $('#announcement-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                            $.putJSON(API_ENDPOINT+'announcements/'+docListId, dataString, putCompanyDocuCallback);
                    }else{
                       // alert(UI_MESSAGES.form_validation_error);
                       toastNotifications(UI_MESSAGES.form_validation_error);
                    }
                });
                var putCompanyDocuCallback = function(data){
                    if(data){
                        $('#announcement-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    };
                    //console.log('data ===' + data.id);
                    $('#announcement-edit-form').addClass('hidden');
                    $('#announcement-edit-form').next('#sucess-model-update').removeClass('hidden');

                    if(!_.isEmpty(menuTable)){
                        menuTable.fnDestroy();
                        menuTable = null;
                    }
                    clearTpl('#announcement_list');
                    populateIRMenu();
                }


                $(document).on('click', '.js-btn-update', function(){
                    $('#announcement-edit-form').removeClass('hidden').next('#sucess-model-update').addClass('hidden');

                });

            // Edit Document form post code here end 

            // ======================================== Form Remove code
            $('.js-remove-sec').on('click', function(){
                var currentExecutiveBtn = $(this);
                    $('.js-remove-executive').on('click', function(){
                    $(currentExecutiveBtn).parents('.pointer').remove();
                });
            });
            // Form Remove Function here end 
        });

        // ======================================== Form File Upload code

        // ==================== GET_API_WRONG_RESPONSE code ====================
         $(document).ajaxError(function() {
            $('#ajex-wrong-msg').html(UI_MESSAGES.get_api_wrong_response);
            if($('.loading-sec').hasClass("hidden")){
                $('.loading-sec').addClass('hidden');
                $('#add-announcement-form input, #add-announcement-form textarea').val('');
                $('[data-dismiss=modal]').trigger('click');
                $('#failed-model-update').modal('show');
            }
        });
        // ==================== GET_API_WRONG_RESPONSE code end ====================


        var statusUpdateCallback = function(data){

                if(data){
                    $('#announcement-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                console.log('data update');
                $('#announcement-edit-form').addClass('hidden');
                var success_msg = data.status == 30 ? "Announcement deactivated successfully" :  "Announcement activated successfully";
                $('#announcement-edit-form').closest('.modal-content').find('#sucess-model-status-update .mtb50').html(success_msg);
                $('#announcement-edit-form').closest('.modal-content').find('#sucess-model-status-update').removeClass('hidden');

                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }
                clearTpl('#announcement_list');
                populateIRMenu();

        };

    </script>       
</body>
</html>
