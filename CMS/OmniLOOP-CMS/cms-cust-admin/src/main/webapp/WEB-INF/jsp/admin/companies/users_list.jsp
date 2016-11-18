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
                            <h3>List of Users</h3>
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
                            <div class="x_panel">
                                <div class="x_content">
									<div class="row">
										 <div class="col-md-6 col-sm-6 col-xs-6 col-md-offset-6">
                                            	<div class="form-group clearfix m-t20">
	                                                 <label class="control-label col-md-5 col-sm-5 col-xs-12 m-t10 text-right" for="search-company">Search users : <span class="required">*</span>
	                                                 </label>
	                                                 <div class="col-md-7 col-sm-7 col-xs-12">
	                                                      <select id="search-company" class="select2_single form-control" tabindex="-1">
	                                                         <option value="ba">Balaji</option>
	                                                         <option value="goog">Google</option>
	                                                         <option value="yh">Yahoo</option>
	                                                         <option value="MSN">Microsoft</option>
	                                                     </select>
	                                                 </div>
                                             	</div>
                                        </div>
									</div>
                                    <div class="clearfix"></div>


                                        <div class="col-md-4 col-sm-4 col-xs-12 animated fadeInDown">
                                            <div class="well profile_view">
                                                <div class="col-sm-12">
                                                    <!-- <h4 class="brief"><a href="#"><i>Telecom Industry</i></a></h4> -->
                                                    <div class="left col-xs-7">
                                                        <h2>john Doe12</h2>
                                                        <p><strong>Email ID : </strong> info@johnDoe.com </p>
                                                        <p><strong>Phone : </strong> +91-99XXX78727 </p>
                                                        <p><strong>Status : </strong> Active </p>
                                                        <p><strong>Password : </strong> johnDoe123@ </p>
                                                        <p><strong>Valid Until  : </strong> 12/25/2015 </p>
                                                        <p><strong>Note  : </strong> I create this account for profit... </p>   
                                                    </div>
                                                    <div class="right col-xs-5">
                                                        <div class="text-center"><img src="<%= baseURL %>/assets/images/user.png" alt="" class="img-circle img-responsive"></div>
                                                    	<div class="m-t5"><h4 class="brief text-center"><a href="#"><i>john Doe</i></a></h4></div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 bottom">
                                                    <div class="col-xs-12 col-sm-12 emphasis">
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View Profile</button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-envelope-o"></i> </button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-user"></i> </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-md-4 col-sm-4 col-xs-12 animated fadeInDown">
                                            <div class="well profile_view">
                                                <div class="col-sm-12">
                                                    <!-- <h4 class="brief"><a href="#"><i>Telecom Industry</i></a></h4> -->
                                                    <div class="left col-xs-7">
                                                        <h2>john Doe12</h2>
                                                        <p><strong>Email ID : </strong> info@johnDoe.com </p>
                                                        <p><strong>Phone : </strong> +91-99XXX78727 </p>
                                                        <p><strong>Status : </strong> Active </p>
                                                        <p><strong>Password : </strong> johnDoe123@ </p>
                                                        <p><strong>Valid Until  : </strong> 12/25/2015 </p>
                                                        <p><strong>Note  : </strong> I create this account for profit... </p>   
                                                    </div>
                                                    <div class="right col-xs-5">
                                                        <div class="text-center"><img src="<%= baseURL %>/assets/images/user.png" alt="" class="img-circle img-responsive"></div>
                                                    	<div class="m-t5"><h4 class="brief text-center"><a href="#"><i>john Doe</i></a></h4></div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 bottom">
                                                    <div class="col-xs-12 col-sm-12 emphasis">
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View Profile</button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-envelope-o"></i> </button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-user"></i> </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-4 col-sm-4 col-xs-12 animated fadeInDown">
                                            <div class="well profile_view">
                                                <div class="col-sm-12">
                                                    <!-- <h4 class="brief"><a href="#"><i>Telecom Industry</i></a></h4> -->
                                                    <div class="left col-xs-7">
                                                        <h2>john Doe12</h2>
                                                        <p><strong>Email ID : </strong> info@johnDoe.com </p>
                                                        <p><strong>Phone : </strong> +91-99XXX78727 </p>
                                                        <p><strong>Status : </strong> Active </p>
                                                        <p><strong>Password : </strong> johnDoe123@ </p>
                                                        <p><strong>Valid Until  : </strong> 12/25/2015 </p>
                                                        <p><strong>Note  : </strong> I create this account for profit... </p>   
                                                    </div>
                                                    <div class="right col-xs-5">
                                                        <div class="text-center"><img src="<%= baseURL %>/assets/images/user.png" alt="" class="img-circle img-responsive"></div>
                                                    	<div class="m-t5"><h4 class="brief text-center"><a href="#"><i>john Doe</i></a></h4></div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 bottom">
                                                    <div class="col-xs-12 col-sm-12 emphasis">
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View Profile</button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-envelope-o"></i> </button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-user"></i> </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-4 col-sm-4 col-xs-12 animated fadeInDown">
                                            <div class="well profile_view">
                                                <div class="col-sm-12">
                                                    <!-- <h4 class="brief"><a href="#"><i>Telecom Industry</i></a></h4> -->
                                                    <div class="left col-xs-7">
                                                        <h2>john Doe12</h2>
                                                        <p><strong>Email ID : </strong> info@johnDoe.com </p>
                                                        <p><strong>Phone : </strong> +91-99XXX78727 </p>
                                                        <p><strong>Status : </strong> Active </p>
                                                        <p><strong>Password : </strong> johnDoe123@ </p>
                                                        <p><strong>Valid Until  : </strong> 12/25/2015 </p>
                                                        <p><strong>Note  : </strong> I create this account for profit... </p>   
                                                    </div>
                                                    <div class="right col-xs-5">
                                                        <div class="text-center"><img src="<%= baseURL %>/assets/images/user.png" alt="" class="img-circle img-responsive"></div>
                                                    	<div class="m-t5"><h4 class="brief text-center"><a href="#"><i>john Doe</i></a></h4></div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 bottom">
                                                    <div class="col-xs-12 col-sm-12 emphasis">
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View Profile</button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-envelope-o"></i> </button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-user"></i> </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-4 col-sm-4 col-xs-12 animated fadeInDown">
                                            <div class="well profile_view">
                                                <div class="col-sm-12">
                                                    <!-- <h4 class="brief"><a href="#"><i>Telecom Industry</i></a></h4> -->
                                                    <div class="left col-xs-7">
                                                        <h2>johnDoe12</h2>
                                                        <p><strong>Email ID : </strong> info@johnDoe.com </p>
                                                        <p><strong>Phone : </strong> +91-99XXX78727 </p>
                                                        <p><strong>Status : </strong> Active </p>
                                                        <p><strong>Password : </strong> johnDoe123@ </p>
                                                        <p><strong>Valid Until  : </strong> 12/25/2015 </p>
                                                        <p><strong>Note  : </strong> I create this account for profit... </p>   
                                                    </div>
                                                    <div class="right col-xs-5">
                                                        <div class="text-center"><img src="<%= baseURL %>/assets/images/user.png" alt="" class="img-circle img-responsive"></div>
                                                    	<div class="m-t5"><h4 class="brief text-center"><a href="#"><i>john Doe</i></a></h4></div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 bottom">
                                                    <div class="col-xs-12 col-sm-12 emphasis">
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View Profile</button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-envelope-o"></i> </button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-user"></i> </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <div class="col-md-4 col-sm-4 col-xs-12 animated fadeInDown">
                                            <div class="well profile_view">
                                                <div class="col-sm-12">
                                                    <!-- <h4 class="brief"><a href="#"><i>Telecom Industry</i></a></h4> -->
                                                    <div class="left col-xs-7">
                                                        <h2>john Doe12</h2>
                                                        <p><strong>Email ID : </strong> info@johnDoe.com </p>
                                                        <p><strong>Phone : </strong> +91-99XXX78727 </p>
                                                        <p><strong>Status : </strong> Active </p>
                                                        <p><strong>Password : </strong> johnDoe123@ </p>
                                                        <p><strong>Valid Until  : </strong> 12/25/2015 </p>
                                                        <p><strong>Note  : </strong> I create this account for profit... </p>   
                                                    </div>
                                                    <div class="right col-xs-5">
                                                        <div class="text-center"><img src="<%= baseURL %>/assets/images/user.png" alt="" class="img-circle img-responsive"></div>
                                                    	<div class="m-t5"><h4 class="brief text-center"><a href="#"><i>john Doe</i></a></h4></div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-12 bottom">
                                                    <div class="col-xs-12 col-sm-12 emphasis">
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-eye"></i> View Profile</button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-envelope-o"></i> </button>
                                                        <button type="button" class="btn btn-primary btn-xs"> <i class="fa fa-user"></i> </button>
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
	<%@ include file="/WEB-INF/jsp/common/common_js_scripts.jspf"%>		
    <%@ include file="/WEB-INF/jsp/common/common_js_vars.jspf"%>
	
	
	<!-- select2 -->
    <script>
        $(document).ready(function () {
            $(".select2_single").select2({
                placeholder: "Select a state",
                allowClear: true
            });
            
        });
    </script>
    <!-- /select2 -->		
</body>
</html>