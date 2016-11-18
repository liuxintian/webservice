<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/jsp/common/common_meta.jspf"%>
<%@ include file="/WEB-INF/jsp/common/html_head.jspf"%>
<link href="<%= baseURL %>/assets/js/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
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
                            <h3>List of Company Documents</h3>
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
                                            <button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-document"> <i class="fa fa-plus"></i> Add new Document</button>
                                        	<button type="button" class="btn btn-default refresh-document-list"> <i class="fa fa-refresh"></i> Refresh Table</button>
                                        </div>
                                        <!--  <div class="col-md-6 col-sm-6 col-xs-6">
                                                <div class="form-group clearfix">
                                                     <label class="control-label col-md-5 col-sm-5 col-xs-12 m-t10 text-right" for="search-company-document">Search company Document : 
                                                     </label>
                                                     <div class="col-md-7 col-sm-7 col-xs-12">
                                                          <select id="search-company-document" class="select2_single form-control" tabindex="-1">
                                                             <option value="rohit">Rohit</option>
                                                             <option value="suryya">Suryya</option>
                                                             <option value="shiva">Shiva</option>
                                                             <option value="ramt">Ram T</option>
                                                         </select>
                                                     </div>
                                             </div>
                                        </div> -->
                                        <div class="clearfix"></div>

                                          <div class="x_content" id="remove-search-tools">
                                            <table id="example" class="table table-striped responsive-utilities jambo_table">
                                                <thead>
                                                    <tr class="headings">
                                                        <th class="p-r20">Img </th>
                                                        <th>Title</th>
                                                        <th>Type</th>
                                                        <th>Status</th>
                                                        <th>Note </th>
                                                        <th class="p-r20">Tags </th>
                                                        <th class="p-r20">Valid&nbsp;Until </th>
                                                        <th class=" no-link last text-center"><span class="nobr">Action</span>
                                                        </th>
                                                    </tr>
                                                </thead>
        
                                                <!-- <tbody>
                                                    <tr class="even pointer">
                                                        <td class="table-pic"><img src="<%= baseURL %>/assets/images/pdf.jpg" alt="" class="img-circle img-responsive"></td>
                                                        <td class=" "><a href="#">Share Report</a></td>
                                                        <td class=" ">Pdf Doc</td>
                                                        <td class=" ">Section for note .</td>
                                                        <td class=" ">12/20/2015</td>
                                                        <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View</button></td>
                                                        <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target=".document-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                                        <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-remove-sec" data-toggle="modal" data-target=".document-remove"> <i class="fa fa-remove"></i> Remove</button></td>
                                                    </tr>
                                                    <tr class="odd pointer">
                                                        <td class="table-pic"><img src="<%= baseURL %>/assets/images/doc.jpg" alt="" class="img-circle img-responsive"></td>
                                                        <td class=" "><a href="#">Profit Report</a></td>
                                                        <td class=" ">Word Doc</td>
                                                        <td class=" ">Section for note .</td>
                                                        <td class=" ">12/24/2015</td>
                                                        <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View</button></td>
                                                        <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs" data-toggle="modal" data-target=".document-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                                        <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-remove-sec" data-toggle="modal" data-target=".document-remove"> <i class="fa fa-remove"></i> Remove</button></td>
                                                    </tr>
                                                    
                                                  </tbody> -->
                                                  <tbody id="document_list"></tbody>
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
    <script src="<%= baseURL %>/assets/js/bootstrap-fileinput/js/fileinput.min.js" type="text/javascript"></script>
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
    
    <!-- Document edit model window  -->
    <div class="modal fade document-edit " tabindex="-1" role="dialog" aria-hidden="true">
        
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
         <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-update" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Edit document </h4>
                 </div>
                 <form id="document-edit-form" class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                        <input data-api-attr="id" type="text" value=""  class="hidden">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-title2">Title of the Document <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="docTitle" type="text" id="document-title2" maxlength="100" value="" placeholder="" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-type2">Document Type <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="docType" type="text" id="document-type2" maxlength="80" value="" placeholder=""  class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-note2">Document Note 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="docNote" type="text" id="document-note2"  value="" placeholder="Type Doc note" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-url2">Document Url <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="docLinkURL" type="text" id="document-url2"  value="" placeholder="" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
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
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <select id="tagRole2"  class="form-control col-md-7 col-xs-12 select2-hidden-accessible" multiple="" tabindex="-1" aria-hidden="true"></select>
                                     <input type="text"  data-api-attr="tagRole" class="hidden form-control" />
                                 </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="gender" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" class="form-control" type="radio" name="document-status2" value="20"  checked=""> &nbsp; Active &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" class="form-control" type="radio" name="document-status2" value="30"> Inactive
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-note2">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="document-note2" class="form-control" name="message">Section for note .</textarea>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-description2">
                                     Description (20 chars min, 400 max) : <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="docDesc" id="document-description2" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10">Some text about this description ...</textarea>
                                 </div>
                             </div>
                             <div class="form-group upload-box-sec">
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-cePicEdit">Document image:
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input  id="file-cePicEdit" class="file form-control" type="file" name="file" data-min-file-count="1">
                                        <input type="hidden" class="form-control"  data-api-attr="docThumbnail" id="file-cePicEdit-url" value="">
                                  </div>
                                  <div class="col-md-3 col-sm-3 col-xs-12 hidden js-file-upload-error"></div>
                              </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-confirmation-status pull-left" data-toggle="confirmation" data-placement="top">Deactivate</button>
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button data-attr="update-btn" type="submit" class="btn btn-primary">Update</button>
                    </div>
                </form>
                <div id="sucess-model-update" class="text-center hidden ">
                    <h4 class="mtb50">Document updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update" data-dismiss="modal">Close</button>
                    </div>
                </div>
                 
                <div id="sucess-model-status-update" class="text-center hidden ">
                    <h4 class="mtb50">Document status updated successfully</h4>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default js-btn-update-verify" data-dismiss="modal">Close</button>
                    </div>
                </div>

             </div>
         </div>
     </div>
    <!-- Document edit model window end  -->
    <!-- new Document model window  -->
    <div class="modal fade add-new-document" tabindex="-1" role="dialog" aria-hidden="true">
        
         <div class="modal-dialog modal-lg pos-rel js-loading-bar">
         <div class="loading-sec hidden"></div>
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close js-btn-verify" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add Company Document</h4>
                 </div>
                 <form  id="add-document-form" class="form-validation-notify form-horizontal form-label-left">
                    <div class="modal-body">
                        <div class="clearfix">
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-title">Title of the Document <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="docTitle" type="text" id="document-title" maxlength="100" value="" placeholder="ex. profit report" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-type">Document Type <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="docType" type="text" id="document-type" maxlength="80" value="" placeholder="ex. doc, pdf"  class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-note">Document Note 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="docNote" type="text" id="document-note"  value="" placeholder="Type Doc note" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-url">Document Url <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input data-api-attr="docLinkURL" type="text" id="document-url"  value="" placeholder="www.website.com" data-parsley-required="true" class="form-control col-md-7 col-xs-12">
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

                            <!-- <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Status <span class="required">*</span></label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                
                                    <div id="gender2" class="btn-group" data-toggle="buttons">
                                        <label class="btn btn-default primary-active active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" class="form-control" type="radio" name="document-status" value="20"  checked=""> &nbsp; Active &nbsp;
                                        </label>
                                        <label class="btn btn-default primary-active" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                            <input data-api-attr="status" class="form-control" type="radio" name="document-status" value="30"> Inactive
                                        </label>
                                    </div>
                                </div>
                            </div> -->

                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-note">
                                     Notes 
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="notes" id="document-note" class="form-control" name="message"></textarea>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="document-description">
                                     Description (20 chars min, 400 max) : <span class="required"></span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea data-api-attr="docDesc"  id="document-description" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                             <div class="form-group upload-box-sec">
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="file-cePicNew">Document image:
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
                                        <input id="file-cePicNew" class="file form-control" type="file" name="file"  data-min-file-count="1">
                                        <input type="hidden" class="form-control"  data-api-attr="docThumbnail" id="file-cePicNew-url" value="">
                                  </div>
                                  <div class="col-md-3 col-sm-3 col-xs-12 hidden js-file-upload-error"></div>
                              </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                         <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                         <button data-attr="submit-btn" type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
                <div id="sucess-model" class="text-center hidden ">
                    <h4 class="mtb50">Added Company Document successfully</h4>
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
                     <h4 class="modal-title" id="myModalLabel">Document remove</h4>
                 </div>
                    <div class="modal-body">
                        <div class="clearfix text-center">
                            <h4>Are you sure you want to remove this document.</h4>
                            <div class="m-t20 text-center">
                                <button class="btn btn-default" data-dismiss="modal">No</button>
                                <button class="btn btn-danger js-remove-document" data-dismiss="modal">Yes</button>
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
    <script type="text/template" id="document_list_template">     
        <$$ _.each(characters, function(documentItems,index){ $$>
            
               <tr class="pointer">
                <td class="table-pic">
                    <$$ if(!documentItems.docThumbnail){ $$>
                                        <img src="<%= baseURL %>/assets/images/pdf.jpg" alt="" class="img-circle img-responsive">
                                    <$$ } else{$$>
                                        <img src="<$$= documentItems.docThumbnail $$>" alt="Company document pic" class="img-circle img-responsive">
                                    <$$ } $$>
                </td>

				<$$ if(/^http|https:\/\//i.test(documentItems.docLinkURL)){ $$>
				    <td><a target="_blank" href="<$$= documentItems.docLinkURL $$>"><$$= documentItems.docLinkURL $$></a></td>
				<$$ } else {$$>
				    <td><a target="_blank" href="http://<$$= documentItems.docLinkURL $$>"><$$= documentItems.docLinkURL $$></a></td>
				<$$ }$$>

                <td><$$= documentItems.docType $$></td>
                <td>
                    <$$ if(documentItems.status == 20){ $$>
                        <span class="success-text">Active</span>
                    <$$ } else if(documentItems.status == 30){$$>
                        <span class="danger-text">Inactive</span>
                    <$$ } else {$$>
                        <span class="danger-text">Inactive</span>
                    <$$ }$$>
                </td>
                <td><$$= documentItems.docNote $$></td>
                <td><$$= documentItems.tagRole $$></td>
                <td><$$= moment(documentItems.validUntil).format(DATE_OUT_FORMAT) $$></td>
                <td class="btn-sec text-center"><button type="button" class="btn btn-primary btn-xs js-edit-document" data-toggle="modal" data-target=".document-edit"> <i class="fa fa-edit"></i> Edit</button> <input type="text" value="<$$= documentItems.id $$>" class="thisDocId hidden" /></td>
             <!--   <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-remove-sec" data-toggle="modal" data-target=".document-remove"> <i class="fa fa-remove"></i> Remove</button></td> -->
            </tr>

        <$$ }); $$>
    </script>
    <script id ="main-list-render">

        var apiEnds = [] , apiRsps = {}, menuTable;
        var getComDocumentItems = function(path) {
            // body...
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };

        var getDocumentItems = function(path,id) {
            // body...
            apiEnds.push(path+'/'+id);
            return $.ajax({
              url: API_ENDPOINT+path+'/'+id
            });
        };

        var chainedStepTwo = function(inputResponse){
            apiRsps[_.last(apiEnds)] = inputResponse;
            var compId = (inputResponse && inputResponse.length && inputResponse[0] && inputResponse[0].id) ? inputResponse[0].id : '';
            return getDocumentItems('document-links',compId);
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
            getComDocumentItems('document-links').then(function (adata) {
                $('.x_panel').find('.loading-sec').removeClass('hidden');
                // body...

                injectTpl('#document_list',adata).then(function () {
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
                        //console.log(reason); // Error!
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
            $.listen('parsley:field:validate', function () {
                validateFront();
            });

            $("#tagRole").select2({
              tags: true,
              tokenSeparators: [',', ' ']
            });
            $("#tagRole + .select2, #tagRole2 + .select2").css({'width':'100%'});


            var validateFront = function () {
                
                if (true === $('#add-document-form').parsley().isValid()) {
                    $('.bs-callout-info').removeClass('hidden');
                    $('.bs-callout-warning').addClass('hidden');
                } else {
                    $('.bs-callout-info').addClass('hidden');
                    $('.bs-callout-warning').removeClass('hidden');
                }
            };
            // ======================================== Form 1 here start
           // Form post code here  
            $('#add-document-form').submit(function (e){
                e.preventDefault();
                var formValidate = $('#add-document-form').parsley().validate();
                validateFront();

                var tagRoleVal = $('#tagRole').val();
                var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                $('#add-document-form input[data-api-attr=tagRole]').val(tagRoleVar);

                var comPicUpload = $('#file-cePicNew').val();
                   if(comPicUpload == ''){
                        $('#add-document-form .js-file-upload-error').addClass('hidden');
                        $('#add-document-form .js-file-upload-error').removeClass('text-danger');
                        $('#add-document-form .js-file-upload-error').html(''); 
                    }else{
                      $('#add-document-form .js-file-upload-error').removeClass('hidden');
                      $('#add-document-form .js-file-upload-error').addClass('text-danger');
                      $('#add-document-form .js-file-upload-error').html(UI_MESSAGES.error_document_logo_upload);
                        return false;
                    }
               
               //var documentAttrs = ['docTitle','docType','docNote','docLinkURL','validUntil','notes','docDesc','docThumbnail'];
               var documentAttrs = ['docTitle','docType','docNote','docLinkURL','notes','docDesc','validUntil', 'docThumbnail','tagRole'];
               var dataString = {};

               _.each(documentAttrs, function(key){
                    if(key == 'status'){
                        dataString[key] = $('#add-document-form .form-control[data-api-attr='+key+']:checked').val();
                        dataString[key] = parseInt(dataString[key],10);
                    }else if((key == 'validUntil')  &&  moment($('#add-document-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                        dataString[key] =  moment($('#add-document-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                    }else{
                        dataString[key] =  $('#add-document-form .form-control[data-api-attr='+key+']').val();    
                    }
               });

               console.log('form 1'+dataString);

                if(formValidate == true){
                    dataString['status'] = 20;
                    console.log('success');
                    $('#add-document-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                        $.postJSON(API_ENDPOINT+'document-links', dataString, postCompanyDocuCallback);
                }else{
                   // alert(UI_MESSAGES.form_validation_error);
                   toastNotifications(UI_MESSAGES.form_validation_error);
                }

           });
          // Form post code here end 
            var postCompanyDocuCallback = function(data){
                if(data){
                    $('#add-document-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                $('#add-document-form input, #add-document-form textarea').val('');
                //$('#add-document-form .fileinput-remove.fileinput-remove-button').trigger('click');
                $('#add-document-form #file-cePicNew').fileinput('clear');
                $('#add-document-form').addClass('hidden');
                $('#add-document-form').next('#sucess-model').removeClass('hidden');
                $('input[data-api-attr=validUntil].new-date-add').val(defaultDateVal);
                    datePlugin();
                //alert('Added new Document');
                $("select#tagRole").select2("val", "");
                if(!_.isEmpty(menuTable)){
                    //menuTable.destroy();

                    menuTable.fnDestroy();
                    menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();

                }
                clearTpl('#document_list');
                populateIRMenu();

                console.log(data); // show response                
            };

            $('.js-btn-verify').on('click', function(){
                $('#add-document-form').removeClass('hidden');
                $('#add-document-form').next('#sucess-model').addClass('hidden');
            });

            $('.refresh-document-list').on('click', function(){
            	
                if(!_.isEmpty(menuTable)){

                    menuTable.fnDestroy();
                    menuTable = null;
                }
            	
                clearTpl('#document_list');
                populateIRMenu();
            });
            
            
            // ======================================== Form 2 here start
            // Edit Document form post code here 
                $(document).on('click', '.js-edit-document', function(){
                    //$('#document-edit-form .kv-file-remove').trigger('click');
                    $('#document-edit-form #file-cePicEdit').fileinput('clear');
                    $('#tagRole2').empty();
                    var dataString = '';
                    var documentId = $(this).next('.thisDocId').val();
                    $('#document-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                    $.getJSON(API_ENDPOINT+'document-links/'+documentId, dataString, getCompanyDocuCallback);
                });
                var getCompanyDocuCallback = function(data){
                    var fallback_img = 'http://loremflickr.com/150/150/brazil';
                    console.log(data.docDesc);
                    if(data){
                        $('#document-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    }
                    $('#document-edit-form input[data-api-attr=docTitle]').val(data.docTitle);
                    $('#document-edit-form input[data-api-attr=docType]').val(data.docType);
                    $('#document-edit-form input[data-api-attr=docLinkURL]').val(data.docLinkURL);
                    $('#document-edit-form [data-api-attr=docThumbnail]#file-cePicEdit-url').val(data.docThumbnail);
                    $('#document-edit-form input[data-api-attr=tagRole]').val(data.tagRole);

                    var docStatusVal = data.status;
                    if(docStatusVal == 20){
                        $('#document-edit-form input[name=document-status2][value=20]').prop('checked',true);
                        $('#document-edit-form input[name=document-status2][value=30]').prop('checked',false);
                        $('#document-edit-form input[name=document-status2]').closest('label').removeClass('active');
                        $('#document-edit-form input[name=document-status2][value=20]').closest('label').addClass('active');
                    }else if(!docStatusVal || docStatusVal == 30){
                        $('#document-edit-form input[name=document-status2][value=30]').prop('checked',true);
                        $('#document-edit-form input[name=document-status2][value=20]').prop('checked',false);
                        $('#document-edit-form input[name=document-status2]').closest('label').removeClass('active');
                        $('#document-edit-form input[name=document-status2][value=30]').closest('label').addClass('active');
                    };

                    var tagValueRole = $('#document-edit-form input[data-api-attr=tagRole]').val();
                    var tagValueRole2 = tagValueRole.split(',');


                    $("#tagRole2").select2({
                      tags: true,
                      tokenSeparators: [',', ' '],
                      data: tagValueRole2
                    });

                    $("#tagRole2").val(tagValueRole2);
                    $("#tagRole2").trigger("change");
                    $('#tagRole2 + span.select2-container').css({"width":"100%"});
                    var tagRoleLength = $('#document-edit-form select#tagRole2').val();
                      if(tagRoleLength.length == 1){
                        if(tagRoleLength == ''){
                            $('#document-edit-form li.select2-selection__choice').remove();
                            $('#tagRole2').empty();
                        }
                    }


                    if(data.validUntil){

                        $('#document-edit-form .form-control[data-api-attr=validUntil]').val(
                            moment(data.validUntil).format(DATE_OUT_FORMAT)
                        );

                    }else{
                        $('#document-edit-form .form-control[data-api-attr=validUntil]').val(
                            moment(new Date().getTime()).format(DATE_OUT_FORMAT)
                        );
                    }

                    if(data && data.docThumbnail){

                        createImageUploadContainer('#document-edit-form #file-cePicEdit',{
                                                    caption:'Company Document Pic',
                                                    url:data.docThumbnail||fallback_img});
                    }
                    //$('#document-edit-form input[data-api-attr=validUntil]').val(data.validUntil);
                    $('#document-edit-form textarea[data-api-attr=notes]').val(data.notes);
                    $('#document-edit-form textarea[data-api-attr=docDesc]').val(data.docDesc);
                   // $('#document-edit-form input[data-api-attr=docThumbnail]').val(data.docThumbnail);
                    $('#document-edit-form input[data-api-attr=docNote]').val(data.docNote);
                    $('#document-edit-form input[data-api-attr=id]').val(data.id);
                    datePlugin();

                    $('#document-edit-form input[name=document-status2]').prop( "disabled", true);  
                    $('#document-edit-form input[name=document-status2]').addClass('disabled');
                    $('#document-edit-form input[name=document-status2]').closest('label').addClass('disabled');


                    $('#document-edit-form').removeClass('hidden');
                    $('#document-edit-form').closest('.modal-content').find('#sucess-model-status-update').addClass('hidden');
                    var statusUpdatePayload = _.extend(_.clone(data), {status: data.status == 30 ? 20 : (data.status == 20 ? 30 : data.status) });

                    $('.js-confirmation-status').removeClass('disabled').prop( "disabled", false); 
                    if(_.contains([20, 30], data.status)){
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'document-links/'+data.id,statusUpdatePayload,statusUpdateCallback);    
                    }else if(!data.status){
                        statusUpdatePayload['status'] = 20;
                        addStatusUpdateBtn($('.js-confirmation-status'),API_ENDPOINT+'document-links/'+data.id,statusUpdatePayload,statusUpdateCallback);  
                    }else{
                        $('.js-confirmation-status').addClass('disabled').prop( "disabled", true);
                    }

                    if(data.status != 20){
                        $( "#document-edit-form [type=submit]" ).prop( "disabled", true).addClass('disabled');
                        $( ".js-confirmation-status" ).html('Activate');
                    }else{
                        $( "#document-edit-form [type=submit]" ).prop( "disabled", false).removeClass('disabled');
                        $( ".js-confirmation-status" ).html('Deactivate');
                    }
                }
                $('#document-edit-form').submit(function (e){
                    e.preventDefault();
                    var formValidate = $('#document-edit-form').parsley().validate();
                    validateFront();
                    var docListId = $('#document-edit-form input[data-api-attr=id]').val();

                    var comPicUpload = $('#file-cePicEdit').val();
                   if(comPicUpload == ''){
                        $('#document-edit-form .js-file-upload-error').addClass('hidden');
                        $('#document-edit-form .js-file-upload-error').removeClass('text-danger');
                        $('#document-edit-form .js-file-upload-error').html(''); 
                    }else{
                      $('#document-edit-form .js-file-upload-error').removeClass('hidden');
                     $('#document-edit-form .js-file-upload-error').addClass('text-danger');
                      $('#document-edit-form .js-file-upload-error').html(UI_MESSAGES.error_document_logo_upload);
                        return false;
                    }

                    var tagRoleVal = $('#tagRole2').val();
                    var tagRoleVar =  tagRoleVal ? tagRoleVal.join(",") : '';
                    $('#document-edit-form input[data-api-attr=tagRole]').val(tagRoleVar);

                    //var documentAttrs = ['docTitle','docType','docNote','docLinkURL','validUntil','notes','docDesc','docThumbnail'];
                   var documentAttrs = ['docTitle','docNote','docType','docLinkURL','notes','docDesc','validUntil','docThumbnail','tagRole','status'];
                   var dataString = {};

                       _.each(documentAttrs, function(key){
                            if(key == 'status'){
                                dataString[key] = $('#document-edit-form .form-control[data-api-attr='+key+']:checked').val();
                                dataString[key] = parseInt(dataString[key], 10);
                            }else if((key == 'validUntil') &&  moment($('#document-edit-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).isValid()){

                                dataString[key] =  moment($('#document-edit-form .form-control[data-api-attr='+key+']').val(), DATE_OUT_FORMAT).valueOf();
                            }else{
                                dataString[key] =  $('#document-edit-form .form-control[data-api-attr='+key+']').val();    
                            }
                       });
                    if(formValidate == true){
                        dataString = _.omit(dataString, 'status');
                        console.log('success');
                        $('#document-edit-form').closest('.js-loading-bar').find('.loading-sec').removeClass('hidden');
                            $.putJSON(API_ENDPOINT+'document-links/'+docListId, dataString, putCompanyDocuCallback);
                    }else{
                      //  alert(UI_MESSAGES.form_validation_error);
                      toastNotifications(UI_MESSAGES.form_validation_error);
                    }
                });
                var putCompanyDocuCallback = function(data){
                    if(data){
                        $('#document-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                    };
                    //console.log('data ===' + data.id);
                    $('#document-edit-form').addClass('hidden').next('#sucess-model-update').removeClass('hidden');

                    if(!_.isEmpty(menuTable)){
                    //menuTable.destroy();

                        menuTable.fnDestroy();
                        menuTable = null;
                    // empty in case the columns change
                    //$('#example').empty();
                    }
                    clearTpl('#document_list');
                    populateIRMenu();

                }

                $(document).on('click', '.js-btn-update', function(){
                    $('#document-edit-form').removeClass('hidden');
                    $('#document-edit-form').next('#sucess-model-update').addClass('hidden');

                });

            // Edit Document form post code here end 

            // ======================================== Form Remove code
            $('.js-remove-sec').on('click', function(){
                var currentExecutiveBtn = $(this);
                    /*$('.js-remove-document').on('click', function(){
                    $(currentExecutiveBtn).parents('.pointer').remove();
                });*/
            });
            // Form Remove Function here end 
        });

        // ======================================== Form File Upload code

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
                $('#add-document-form input, #add-document-form textarea').val('');
                $('[data-dismiss=modal]').trigger('click');
                $('#failed-model-update').modal('show');
            }
        });
        // ==================== GET_API_WRONG_RESPONSE code end ====================

        var statusUpdateCallback = function(data){

                if(data){
                    $('#document-edit-form').closest('.js-loading-bar').find('.loading-sec').addClass('hidden');
                };
                console.log('data update');
                $('#document-edit-form').addClass('hidden');
                var success_msg = data.status == 30 ? "Document deactivated successfully" :  "Document activated successfully";
                $('#document-edit-form').closest('.modal-content').find('#sucess-model-status-update .mtb50').html(success_msg);
                $('#document-edit-form').closest('.modal-content').find('#sucess-model-status-update').removeClass('hidden');

                if(!_.isEmpty(menuTable)){
                    menuTable.fnDestroy();
                    menuTable = null;
                }
                clearTpl('#document_list');
                populateIRMenu();

        };

    </script>       
</body>
</html>
