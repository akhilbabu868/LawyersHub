<%-- 
    Document   : pakagemaster
    Created on : 14 Jan, 2020, 5:58:37 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Package Master</title>
        <%@include file = "header1.jsp"%>
        <script src="customjs/pakagemaster.js"></script>
    </head>
    <body style="color:black">
        <div class="container">
            <div class="col-sm-10">
                <div class="card"  style="margin-top: 100px">
                <div class="card-header bg-primary">Package Master</div>
                <div class="card-body">
                    <form class="form-horizontal" action="">
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="text">Package Name:</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="package" placeholder="Enter packageName">
                            </div>
                        </div>
                         <div class="form-group">
                            <label class="control-label col-sm-2" for="email">Description:</label>
                            <div class="col-sm-6">
                                <textarea class="form-control" id="Description" ></textarea>
                            </div>
                        </div>
                         <div class="form-group">
                            <label class="control-label col-sm-2" for="text">Package Rate:</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" id="rate" placeholder="Enter rate">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <input type="button" class="btn btn-success" value="save" id="btnsave" style="border-radius: 5px;">
                                <input type="hidden" value="0" id="HiddenID">
                                <input type="hidden" value="0" id="statusHidden">
                            </div>
                        </div>
                    </form>   
                </div>
            </div>
            </div>
            <div class="col-sm-10" style="margin-top: 20px">
                <div class="card">
                 <div class="card-header bg-primary">Package Master Table</div>
                 <div class="card-body">
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="box box-color box-bordered box-small">
                                <form>
                                    <div class="box-title">
                                        <h4>
                                            <i class="icon-table"></i> Package Master List
                                        </h4>
                                    </div>
                                    <div class="box-content nopadding">
                                        <div class="dataTables_scroll">
                                            <div class="dataTables_scrollBody" style="overflow:auto; width:100%;">
                                                <table class="table table-bordered " id="packageDataTable">
                                                    <thead>
                                                        <tr>
                                                            <th>S.No</th>
                                                            <th>package</th>
                                                            <th>Description</th>
                                                            <th>Rate</th>
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
        </div>
    </body>
  <%@include file = "footer.jsp"%>
</html>
