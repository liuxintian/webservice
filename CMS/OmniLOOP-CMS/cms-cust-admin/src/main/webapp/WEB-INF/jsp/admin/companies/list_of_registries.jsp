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
                    </div>
                    <div class="clearfix"></div>

                    <div class="row">
                        <div class="col-md-12">
                            <div class="x_panel">
                                <div class="x_content">
									<div class="row">
                 						<div class="col-md-6 col-sm-6 col-xs-6">
                                    		<button type="button" class="btn btn-default" data-toggle="modal" data-target=".add-new-registries-list"> <i class="fa fa-file"></i> New Share Registry</button>
                                    	</div>
                                    </div>
                                    <div class="clearfix"></div>
                                    <div class="row" id="remove-search-tools">
                                    <table id="example" class="table table-striped responsive-utilities jambo_table">
                                        <thead class="hidden">
                                            <tr class="headings">
                                                <th class="column-title">Registry Name </th>
                                                <th class="column-title">Valid Until </th>
                                                <th class="column-title">Country </th>
                                                <th class="column-title">Phone Number </th>
                                                <th class="column-title">Email ID</th>
                                                <th class="column-title">Registry Type</th>
                                                <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                                <th class="bulk-actions" colspan="7">
                                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                            	</th>
                                			</tr>
                            			</thead>
										<thead>
                                            <tr class="headings">
                                                <th class="column-title">Registry Name </th>
                                                <th class="column-title">Valid Until </th>
                                                <th class="column-title">Country </th>
                                                <th class="column-title">Phone Number </th>
                                                <th class="column-title">Email ID</th>
                                                <th class="column-title">Registry Type</th>
                                                <th class="column-title no-link last"><span class="nobr">Action</span></th>
                                                <th class="bulk-actions" colspan="7">
                                                    <a class="antoo" style="color:#fff; font-weight:500;">Bulk Actions ( <span class="action-cnt"> </span> ) <i class="fa fa-chevron-down"></i></a>
                                            	</th>
                                			</tr>
                            			</thead>
										<tbody>
			                                <tr class="even pointer">
			                                    <td class=" "><a href="javascript:void(0);">Hawaii</a></td>
			                                    <td class=" ">26/12/2015</td>
			                                    <td class=" ">USA</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">info@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
			                                 </tr>
                                            <tr class="odd pointer">
                                                <td class=" "><a href="javascript:void(0);">Nevada</a></td>
			                                    <td class=" ">28/12/2015</td>
			                                    <td class=" ">India</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">xyz@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                            </tr>
                                            <tr class="even pointer">
			                                    <td class=" "><a href="javascript:void(0);">Oregon</a></td>
			                                    <td class=" ">30/12/2015</td>
			                                    <td class=" ">China</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">qaz@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
			                                 </tr>
                                            <tr class="odd pointer">
                                                <td class=" "><a href="javascript:void(0);">Utah</a></td>
			                                    <td class=" ">02/01/2016</td>
			                                    <td class=" ">Canada</td>
			                                    <td class=" ">+91-9053XXXX27</td>
			                                    <td class=" ">xyz@edfr.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                            </tr>
                                            <tr class="even pointer">
			                                    <td class=" "><a href="javascript:void(0);">Hawaii</a></td>
			                                    <td class=" ">26/12/2015</td>
			                                    <td class=" ">USA</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">info@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
			                                 </tr>
                                            <tr class="odd pointer">
                                                <td class=" "><a href="javascript:void(0);">Nevada</a></td>
			                                    <td class=" ">28/12/2015</td>
			                                    <td class=" ">India</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">xyz@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                            </tr>
                                            <tr class="even pointer">
			                                    <td class=" "><a href="javascript:void(0);">Oregon</a></td>
			                                    <td class=" ">30/12/2015</td>
			                                    <td class=" ">China</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">qaz@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
			                                 </tr>
                                            <tr class="odd pointer">
                                                <td class=" "><a href="javascript:void(0);">Utah</a></td>
			                                    <td class=" ">02/01/2016</td>
			                                    <td class=" ">Canada</td>
			                                    <td class=" ">+91-9053XXXX27</td>
			                                    <td class=" ">xyz@edfr.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                            </tr>
                                            <tr class="even pointer">
			                                    <td class=" "><a href="javascript:void(0);">Hawaii</a></td>
			                                    <td class=" ">26/12/2015</td>
			                                    <td class=" ">USA</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">info@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
			                                 </tr>
                                            <tr class="odd pointer">
                                                <td class=" "><a href="javascript:void(0);">Nevada</a></td>
			                                    <td class=" ">28/12/2015</td>
			                                    <td class=" ">India</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">xyz@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                            </tr>
                                            <tr class="even pointer">
			                                    <td class=" "><a href="javascript:void(0);">Oregon</a></td>
			                                    <td class=" ">30/12/2015</td>
			                                    <td class=" ">China</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">qaz@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
			                                 </tr>
                                            <tr class="odd pointer">
                                                <td class=" "><a href="javascript:void(0);">Utah</a></td>
			                                    <td class=" ">02/01/2016</td>
			                                    <td class=" ">Canada</td>
			                                    <td class=" ">+91-9053XXXX27</td>
			                                    <td class=" ">xyz@edfr.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                            </tr>
                                            <tr class="even pointer">
			                                    <td class=" "><a href="javascript:void(0);">Hawaii</a></td>
			                                    <td class=" ">26/12/2015</td>
			                                    <td class=" ">USA</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">info@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
			                                 </tr>
                                            <tr class="odd pointer">
                                                <td class=" "><a href="javascript:void(0);">Nevada</a></td>
			                                    <td class=" ">28/12/2015</td>
			                                    <td class=" ">India</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">xyz@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                            </tr>
                                            <tr class="even pointer">
			                                    <td class=" "><a href="javascript:void(0);">Oregon</a></td>
			                                    <td class=" ">30/12/2015</td>
			                                    <td class=" ">China</td>
			                                    <td class=" ">+91-9953XXXX27</td>
			                                    <td class=" ">qaz@abx.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
			                                 </tr>
                                            <tr class="odd pointer">
                                                <td class=" "><a href="javascript:void(0);">Utah</a></td>
			                                    <td class=" ">02/01/2016</td>
			                                    <td class=" ">Canada</td>
			                                    <td class=" ">+91-9053XXXX27</td>
			                                    <td class=" ">xyz@edfr.com</td>
			                                    <td class=" ">Registry Type</td>
			                                    <td class="btn-sec"><button type="button" class="btn btn-primary btn-xs js-edit-row" data-toggle="modal" data-target=".menu-registries-edit"> <i class="fa fa-edit"></i> Edit</button></td>
                                            </tr>
                                        </tbody>
		                           </table>
                                    	
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
	<div class="modal fade add-new-registries-list" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg">
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Add Share Registry List</h4>
                 </div>
                 <form enctype="multipart/form-data" id="add-menu-list" data-parsley-validate class="form-horizontal form-label-left">
                    <div class="modal-body">
                     	<div class="clearfix">
                     		<div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title-name">Name of the Registry <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="title-name" maxlength="100" value="" placeholder="ex. title name" required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phone-number">Phone Number <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="phone-number" maxlength="10" value="" placeholder="ex. 9953XXXX27" required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email-id-feed">Email ID <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="email" id="email-id-feed" placeholder="xdef@xyz.com" class="form-control" name="email" data-parsley-trigger="change" required />
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="source-url">Source URL <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="url" id="source-url" name="website" required="required" placeholder="www.website.com" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-valid-until">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="menu-valid-until" value="" placeholder="DD/MM/YYYY" required="required" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="autocomplete-custom-append">Country of Operations <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" name="country" id="autocomplete-custom-append" class="form-control col-md-10" style="float: left;" />
                                    <div id="autocomplete-container" style="position: relative; float: left; width: 400px; margin: 10px;"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Share Registry <span class="required">*</span> 
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12 select-width">
                                     <select class="select2_single form-control" tabindex="-1">
                                        <option value="None">Select one</option>
                                        <option value="AK">Alaska</option>
                                        <option value="HI">Hawaii</option>
                                        <option value="CA">California</option>
                                        <option value="NV">Nevada</option>
                                        <option value="OR">Oregon</option>
                                        <option value="WA">Washington</option>
                                        <option value="AZ">Arizona</option>
                                        <option value="CO">Colorado</option>
                                        <option value="ID">Idaho</option>
                                        <option value="MT">Montana</option>
                                        <option value="NE">Nebraska</option>
                                        <option value="NM">New Mexico</option>
                                        <option value="ND">North Dakota</option>
                                        <option value="UT">Utah</option>
                                        <option value="WY">Wyoming</option>
                                        <option value="AR">Arkansas</option>
                                        <option value="IL">Illinois</option>
                                        <option value="IA">Iowa</option>
                                        <option value="KS">Kansas</option>
                                        <option value="KY">Kentucky</option>
                                        <option value="LA">Louisiana</option>
                                        <option value="MN">Minnesota</option>
                                        <option value="MS">Mississippi</option>
                                        <option value="MO">Missouri</option>
                                        <option value="OK">Oklahoma</option>
                                        <option value="SD">South Dakota</option>
                                        <option value="TX">Texas</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-note">
                                     Notes (20 chars min, 100 max)  <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea id="menu-note" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="message-2">
                                      Share Reg Address (20 chars min, 400 max)  <span class="required">*</span>
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
                                  <textarea id="message-2" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="400" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                  </div>
                              </div>
                             
                     	</div>
                    </div>
                    <div class="modal-footer">
	                     <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	                     <button type="submit" class="btn btn-primary m-b5">Submit</button>
                 	</div>
                </form>
                 

             </div>
         </div>
     </div>
	<!-- new Document model window end  -->
	<!-- edit Document model window  -->
	<div class="modal fade menu-registries-edit" tabindex="-1" role="dialog" aria-hidden="true">
         <div class="modal-dialog modal-lg">
             <div class="modal-content">

                 <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                     </button>
                     <h4 class="modal-title" id="myModalLabel">Edit Menu List</h4>
                 </div>
                 <form enctype="multipart/form-data" id="edit-menu-list" data-parsley-validate class="form-horizontal form-label-left">
                    <div class="modal-body">
                     	<div class="clearfix">
                     		<div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="title-name2">Name of the Registry <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="title-name2" maxlength="100" value="Hawaii" required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="phone-number2">Phone Number <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="phone-number2" maxlength="10" value="9953XXXX27"  required="required" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email-id-feed2">Email ID <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="email" id="email-id-feed2" value="info@abx.com" class="form-control" name="email" data-parsley-trigger="change" required />
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="source-url2">Source URL <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="url" id="source-url2" name="website" required="required" value="www.website.com" class="form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-valid-until2">Valid Until <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                     <input type="text" id="menu-valid-until2" value="26/12/2015"  required="required" class="date-picker form-control col-md-7 col-xs-12">
                                 </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="autocomplete-custom-append2">Country of Operations <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" name="country" id="autocomplete-custom-append2" class="form-control col-md-10" style="float: left;" />
                                    <div id="autocomplete-container2" style="position: relative; float: left; width: 400px; margin: 10px;"></div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Share Registry <span class="required">*</span> 
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12 select-width">
                                     <select class="select2_single form-control" tabindex="-1">
                                        <option value="None">Select one</option>
                                        <option value="AK">Alaska</option>
                                        <option value="HI">Hawaii</option>
                                        <option value="CA">California</option>
                                        <option value="NV">Nevada</option>
                                        <option value="OR">Oregon</option>
                                        <option value="WA">Washington</option>
                                        <option value="AZ">Arizona</option>
                                        <option value="CO">Colorado</option>
                                        <option value="ID">Idaho</option>
                                        <option value="MT">Montana</option>
                                        <option value="NE">Nebraska</option>
                                        <option value="NM">New Mexico</option>
                                        <option value="ND">North Dakota</option>
                                        <option value="UT">Utah</option>
                                        <option value="WY">Wyoming</option>
                                        <option value="AR">Arkansas</option>
                                        <option value="IL">Illinois</option>
                                        <option value="IA">Iowa</option>
                                        <option value="KS">Kansas</option>
                                        <option value="KY">Kentucky</option>
                                        <option value="LA">Louisiana</option>
                                        <option value="MN">Minnesota</option>
                                        <option value="MS">Mississippi</option>
                                        <option value="MO">Missouri</option>
                                        <option value="OK">Oklahoma</option>
                                        <option value="SD">South Dakota</option>
                                        <option value="TX">Texas</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">                        
                                 <label class="control-label col-md-3 col-sm-3 col-xs-12" for="menu-note2">
                                     Notes (20 chars min, 100 max)  <span class="required">*</span>
                                 </label>
                                 <div class="col-md-6 col-sm-6 col-xs-12">
                                 <textarea id="menu-note2" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                 </div>
                             </div>
                             <div class="form-group">                        
                                  <label class="control-label col-md-3 col-sm-3 col-xs-12" for="message-3">
                                      Share Reg Address (20 chars min, 400 max)  <span class="required">*</span>
                                  </label>
                                  <div class="col-md-6 col-sm-6 col-xs-12">
                                  <textarea id="message-3" required="required" class="form-control" name="message" data-parsley-trigger="keyup" data-parsley-minlength="20" data-parsley-maxlength="400" data-parsley-minlength-message="Come on! You need to enter at least a 20 caracters long comment.." data-parsley-validation-threshold="10"></textarea>
                                  </div>
                              </div>
                             
                     	</div>
                    </div>
                    <div class="modal-footer">
	                     <button type="button" class="btn btn-danger js-remove-row" data-dismiss="modal">Remove</button>
	                     <button type="submit" class="btn btn-primary m-b5">Save</button>
                 	</div>
                </form>
                 

             </div>
         </div>
     </div>
	<!-- edit Document model window end  -->
	<%@ include file="/WEB-INF/jsp/common/common_js_scripts.jspf"%>	
	<%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
	<!-- Datatables --> 
        <script src="<%= baseURL %>/assets/js/datatables/js/jquery.dataTables.js" type="text/javascript"></script>
        <script src="<%= baseURL %>/assets/js/datatables/tools/js/dataTables.tableTools.js" type="text/javascript"></script>
        <script type="text/javascript">
        $(function () {
            'use strict';
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
	            $('#autocomplete-custom-append2').autocomplete({
	                lookup: countriesArray,
	                appendTo: '#autocomplete-container2'
	            });
	        });
        
    	</script>
        <script>
            $(document).ready(function () {
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
                    /* Filter on the column based on the index of this element's parent <th> */
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
            });
        </script>	
	<script type="text/javascript">
        $(document).ready(function () {
        	$('.js-edit-row').on('click', function(){
        		row_sec = $(this).closest('tr');
        	});
        	$('.js-remove-row').on('click', function(){
        		$(row_sec).remove();
        	});
        	/* $('.js-add').on('click', function() {  
        		 return !$('#select1 option:selected').remove().appendTo('#select2');  
       		 });  
       		 $('.js-remove').on('click', function() {  
       		  return !$('#select2 option:selected').remove().appendTo('#select1');  
       		 }); */ 
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
        	
            $('#menu-valid-until2, #menu-valid-until').daterangepicker({
            	minDate: new Date(),
                singleDatePicker: true,
                locale: {
  				  format: DATE_OUT_FORMAT
  				},
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
            
           
            
        });
    </script>		
</body>
</html>