<%-- 
    Document   : townmaster
    Created on : 12 Feb, 2020, 1:18:02 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>District Master</title>
        <%@include file = "header1.jsp"%>
         <script src="customjs/townmaster.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="col-sm-10">
            <div class="card"  style="margin-top: 50px">
                <div class="card-header bg-primary">Town Master</div>
                <div class="card-body">
                    <form class="form-horizontal" method="post" action="">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="District">District:</label>
                            <div class="col-sm-6">
                                <select class="js-example-placeholder-single  form-control" id="District"></select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="town">Town:</label>
                            <div class="col-sm-6">
                                <input type="text" id="town" placeholder="Enter Town"  class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <input type="button" class="btn btn-success" id="save" value="Save" style="border-radius: 5px;">
                                <input type="hidden" value="0" id="HiddenID">
                                <input type="hidden"  id="statusHidden">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
            
              <div class="col-sm-10" style="margin-top: 20px">
                <div class="card">
                    <div class="card-header bg-primary">Table Master</div>
                        <div class="row-fluid">
                            <div class="span12">
                                <div class="box box-color box-bordered box-small">
                                    <form>
                                        <div class="box-content nopadding">
                                            <div class="dataTables_scroll">
                                                <div class="dataTables_scrollBody" style="overflow:auto; width:100%;">
                                                    <table class="table table-bordered " id="townDataTable">
                                                        <thead>
                                                            <tr>
                                                                <th>S.No</th>
                                                                <th>Town</th>
                                                                <th>District</th>
                                                                <th>Status</th>
                                                                <th>Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody >
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>   
                    </div>     
                 </div>
            </div>
        <%@include file = "footer.jsp"%>
    </body>
</html>
