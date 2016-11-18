<div class="row hidden js-update-alert">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="alert alert-success" role=""> <strong>  Well done!</strong> You successfully updated IR Menu. 
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="row hidden-field pos-rel" id="remove-search-tools2">
            <div class="loading-sec hidden"></div>
            <table id="example2" class="table table-striped responsive-utilities jambo_table js-static-table">
                <thead>
                    <tr>
                        <th style="text-align: center;" colspan="6">Selected IR Menus</th>
                     </tr>
                    <tr class="headings">
                        
                        <th class="text-center" style="width:95px;">Selected</th>
                        <th class="column-title">Title </th>
                        <th class="column-title" style="width:350px;">Menu Name </th>
                        <th class="column-title">Valid Until </th>
                        <th class="column-title">Status</th>
                    </tr>
                </thead>
                    <tbody id="menus_list2"></tbody>
           </table>
        </div>
        <script type="text/template" id="menus_list2_template">     
            <$$ _.each(characters, function(menuItems,index){ $$>

                <tr class="pointer <$$=menuItems.selected ? 'avtiveTable' : '' $$>  ">
                    
                    <td class="text-center">
						<label class="js-custom-checkbox icheckbox_flat-green custom-check-sec <$$=menuItems.selected ? 'checked' : '' $$> ">
						<input type="checkbox" <$$= (menuItems.status != 20 ? 'disabled="disabled"' : '') $$> class="<$$= (menuItems.status != 20 ? 'disabled' : '') $$>" value="<$$=menuItems.id $$>" <$$= (menuItems.selected ? 'checked' : '') $$> name="rowSelect" /></label>
                    
                        <span class="js-demo-value-insert hidden">
                            <$$=menuItems.selected ? '1' : '2' $$>
                        </span>

                    </td>
                    <td class=""><$$= menuItems.menuItemName $$></td>
                    <td class=""><$$= menuItems.menuItemDesc $$></td>
                    <td class=""><$$= moment(menuItems.validUntil).format(DATE_OUT_FORMAT) $$></td>
                    <td>
                        <$$ if(menuItems.status == 20){ $$>
                            <span class="success-text">Active</span>
                        <$$ } else if(menuItems.status == 30){$$>
                            <span class="danger-text">Inactive</span>
                        <$$ } else {$$>
                            <span class="danger-text">Inactive</span>
                        <$$ }$$>
                    </td>

                </tr>  
            <$$ }); $$>
        </script>
    </div>
    <div class="col-md-6 col-sm-6 col-xs-12">
        <script type="text/template" id="menus_list_template">     
            <$$ _.each(characters, function(menuItems,index){ $$>

                <tr class="pointer">
                    <td><label class="js-custom-checkbox icheckbox_flat-green custom-check-sec"><input type="checkbox" value="" name="rowSelect" class="check-select-table" /></label></td>
                    <td class=""><input type="text" class="hidden" value="<$$= menuItems.id $$>" /><$$= menuItems.menuItemName $$></td>
                    <td class=""><$$= menuItems.menuItemDesc $$></td>
                    <td class=""><$$= menuItems.validUntil $$></td>

                    <td>
                        <$$ if(menuItems.status == 20){ $$>
                            <span class="success-text">Active</span>
                        <$$ } else if(menuItems.status == 30){$$>
                            <span class="danger-text">Inactive</span>
                        <$$ } else {$$>
                            <span class="danger-text">Inactive</span>
                        <$$ }$$>
                    </td>
                </tr>  
            <$$ }); $$>
        </script>
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class=" text-center" style="margin-top:15px;">
            <button type="button" class="pl50 pr50 btn btn-primary js-update-server">Submit</button>      
        </div>
    </div>
</div>



 <!-- Document model remove end  -->
<div class="modal fade document-remove" tabindex="-1" role="dialog" aria-hidden="true">
     <div class="modal-dialog modal-md">
         <div class="modal-content">
            <div class="modal-header">
                 <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">X</span>
                 </button>
                 <h4 class="modal-title" id="myModalLabel">Menu remove</h4>
             </div>
                <div class="modal-body">
                    <div class="clearfix text-center">
                        <h4>Are you sure you want to remove this menu  list.</h4>
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


<script type="text/javascript">
var updateIrMenuAlertCallback= function(){
    //alert('updated');
    setTimeout(function () { 
        // ----------------
            $('.js-update-alert').removeClass('hidden');
            $('html, body').animate({
                  scrollTop: $('.js-update-alert').offset().top
             }, 100);

            setTimeout(function(){
                $('.js-update-alert').addClass('hidden');
            }, 8000);
        // -------------
    }, 5000);
    // ===============================
    
};

    function onAddTag(tag) {
        alert("Added a tag: " + tag);
    }

    function onRemoveTag(tag) {
        alert("Removed a tag: " + tag);
    }

    function onChangeTag(input, tag) {
        alert("Changed a tag: " + tag);
    }

    var clearTpl = function(id){
        $( id ).empty();
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
</script>



<!-- THIRD_FORM -->
    <script type="text/javascript">
    

        
        /* Table Data transfer code here   */
        $(document).ready(function () {
            var companyIrMenuIdUpdt = getUrlParameter('cid');

            $(document).on('click','.js-update-server', function(){
                var selectdRowIds = [];

                $('.js-static-table .js-custom-checkbox input').each(function(){
                    var checkListData = $(this).prop("checked");
                    if(checkListData == true){
                        selectdRowIds.push($(this).prop("value"));
                    }
                });
                selectdRowIds = _.uniq(selectdRowIds); 
                if(companyIrMenuIdUpdt == undefined){  
                   companyIrMenuIdUpdt=  companyId;  
                };  
                var newRowIds = _.difference(selectdRowIds,selectedMenuIds);
                if(newRowIds && newRowIds.length){
                    var menuUpdatePayLoad = {menuIds:selectdRowIds}
                    $('#remove-search-tools2').find('.loading-sec').removeClass('hidden');
                    $.putJSON(API_ENDPOINT+'companies/'+companyIrMenuIdUpdt+'/ir-menus', menuUpdatePayLoad, putCompanyIRMenuUpdateCallback);
                }
                

                console.log(_.difference(selectdRowIds,selectedMenuIds));
               

            });

            
            var putCompanyIRMenuUpdateCallback = function () {
                // body...
                $('#remove-search-tools2').find('.loading-sec').addClass('hidden');

                if(!_.isEmpty(menuTable2)){
                    //menuTable.destroy();
                    menuTable2.fnDestroy();
                    menuTable2 = null;
                    // empty in case the columns change
                    //$('#example').empty();
                }

                clearTpl('#menus_list2');

                populateIRMenu(companyIrMenuIdUpdt);
                
                updateIrMenuAlertCallback();

            };

            /* IR Menu Table Row Tr push functionality code here  */
            /* $('.js-push-row-data').on('click', function(){
                var check_row = $('.js-table-data').find('input[type=checkbox]:checked').val();
                var check_length = $('.js-table-data').find('input[type=checkbox]:checked').length;
                var check_html = $('.js-table-data').find('input[type=checkbox]:checked').closest('tr').html();
                console.log('show selected table' + check_row + ' length ' + check_length + 'html is ' + check_html);
            }); */  
            /* IR Menu Table Row Remove functionality code here  */
            $(document).on('click','.js-remove-sec', function(){
                currentExecutiveBtn = $(this);
            });
            $(document).on('click','.js-remove-executive', function(){
                $(currentExecutiveBtn).closest('tr').remove();
            });
            /* IR Menu Table Row Remove functionality code here end  */
            
            $(document).on('change','.js-custom-checkbox input', function(){
                var checkBoxValue = $(this).prop( "checked" );
                //console.log(some);
                if(checkBoxValue == true){
                    $(this).closest('.js-custom-checkbox').addClass('checked');
                    $(this).closest('tr').addClass('avtiveTable');
                  //  $(this).closest('td').find('.js-demo-value-insert').text('b');
                }else if( checkBoxValue == false ){
                    $(this).closest('.js-custom-checkbox').removeClass('checked');
                    $(this).closest('tr').removeClass('avtiveTable');
                   // $(this).closest('td').find('.js-demo-value-insert').text('a');
                }
            });

            // IR Menu Default Show 
            /*$.getJSON(API_ENDPOINT+'ir-menu-items/', function(data){
                getIrMenuDataCallback(data);
            });
            var getIrMenuDataCallback = function(data){
               // console.log(data + 'ir menu  data');
            }*/
        });
        /* Table Data transfer code here end  */
        

    </script>


    <script id = "main-list2-render">



        $(document).ready(function(){
            var companyIrMenuId = getUrlParameter ('cid');
            
            if(companyIrMenuId){
                //populateIRMenu2(companyIrMenuId);
                populateIRMenu(companyIrMenuId);
            }
                
        });

    </script>
    <!-- THIRD_FORM_ENDS -->



    <script id = "main-list-render">

        var apiEnds = [] , selectedMenuIds = [] ,apiRsps = {}, menuTable;
        var getIrMenuItems = function(path) {
            // body...
            apiEnds.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };



        var injectSelectedMenuList = function (sdata,gdata) {
                //var allMenuData = sdata.menuItems;
                var allMenuData = gdata;
                //sdata.menuIds = [1,2];
                selectedMenuIds = sdata.menuIds;
                if(sdata.menuIds && sdata.menuIds.length){
                    // allMenuData = _.filter(sdata.menuItems, function(val){ 
                    //     return _.contains(sdata.menuIds, val.id);
                    //     //return num % 2 == 0; 
                    // });

                    allMenuData = _.map(allMenuData, function(val){ 
                        val.selected = _.contains(sdata.menuIds, val.id);
                        return val;
                    });
                }

                injectTpl('#menus_list2',allMenuData).then(function () {
                    // Do something brilliant here!
                    //alert("Do something brilliant here!");
                    // here code data will be loaded than run this code 
                    var asInitVals = new Array();
                    
                    menuTable2 = $('#example2').dataTable({
                        //bFilter: false,
                        "oLanguage": {
                            "sSearch": "Search all columns:"
                        },
                        "aoColumnDefs": [
                            {
                                'bSortable': false,
                                'aTargets': [0]
                            } //disables sorting for column one
                        ],
                        'iDisplayLength': 10,
                        "sPaginationType": "full_numbers",
                        "dom": 'T<"clear">lfrtip',
                        "tableTools": {
                          //  "sSwfPath": "<?php echo base_url('assets2/js/Datatables/tools/swf/copy_csv_xls_pdf.swf'); ?>"
                        }
                    });
                    $("tfoot input").keyup(function () {
                        /* Filter on the column based on the index of this element's parent <th> */
                        menuTable2.fnFilter(this.value, $("tfoot th").index($(this).parent()));
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

        };

        var populateIRMenu = function(companyIrMenuId){

            //Parallel isolated
            //ADD SPINNER HERE - REQUEST STARTS
            $('#remove-search-tools2').find('.loading-sec').removeClass('hidden');
            $.when(
                getIrMenuItems('ir-menu-items'),
                getIrMenuItems2('companies/'+companyIrMenuId+'/ir-menus')
            ).done(function( ajaxResponse1,ajaxResponse2 ) {
                //REMOVE SPINNER HERE - REQUEST ENDS SUCCESSFULLY
                 $('#remove-search-tools2').find('.loading-sec').addClass('hidden');
                   /*setTimeout(function(){
                        $('.js-custom-checkbox').next('.js-demo-value-insert').each(function(){
                            var valueInsert = $(this).attr('data-attr');
                            $(this).text(valueInsert);
                         })
                    });*/

                //injectGlobalMenuList(ajaxResponse1[0],ajaxResponse2[0]);//Global,Selected
                injectSelectedMenuList(ajaxResponse2[0],ajaxResponse1[0]);//Selected,Global

            });

        };

        var apiEnds2 = [] , apiRsps2 = {}, menuTable2;
        var getIrMenuItems2 = function(path) {
            // body...
            apiEnds2.push(path);
            return $.ajax({
              url: API_ENDPOINT+path
            });
        };
        

        var populateIRMenu2 = function(companyIrMenuId){

            getIrMenuItems2('companies/'+companyIrMenuId+'/ir-menus').then(function (adata) {
                var allMenuData = adata.menuItems;
                // body...

                injectTpl('#menus_list2',allMenuData).then(function () {
                    // Do something brilliant here!
                    //alert("Do something brilliant here!");
                    // here code data will be loaded than run this code 
                    var asInitVals = new Array();
                    
                    menuTable2 = $('#example2').dataTable({
                        bFilter: false,
                        "oLanguage": {
                            "sSearch": "Search all columns:"
                        },
                        "aoColumnDefs": [
                            {
                                'bSortable': false,
                                'aTargets': [0]
                            } //disables sorting for column one
                        ],
                        'iDisplayLength': 10,
                        "sPaginationType": "full_numbers",
                        "dom": 'T<"clear">lfrtip',
                        "tableTools": {
                          //  "sSwfPath": "<?php echo base_url('assets2/js/Datatables/tools/swf/copy_csv_xls_pdf.swf'); ?>"
                        }
                    });
                    $("tfoot input").keyup(function () {
                        /* Filter on the column based on the index of this element's parent <th> */
                        menuTable2.fnFilter(this.value, $("tfoot th").index($(this).parent()));
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
            });

        };

    </script>