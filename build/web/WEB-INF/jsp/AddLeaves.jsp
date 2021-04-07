<%-- 
    Document   : AddLeaves
    Created on : 11 Mar, 2020, 7:45:55 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Add Leaves </title>
        <%@include file = "header3.jsp"%>
     
    </head>
    <body>
        <div class="container">
            <div class="col-sm-10">
                <div class="card"  style="margin-top: 50px">
                    <div class="card-header bg-primary">Add Leaves</div>
                    <div class="card-body">
                        <form class="form-horizontal" method="post" action="">
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="frmdate">From Date:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="frmdate" placeholder="From Date" name="frmdate" >
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-2" for="todate">To Date:</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" id="todate" placeholder="To Date" name="todate" >
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <input type="button" class="btn btn-success" id="save" value="Save" style="border-radius: 5px;">
                                    <input type="hidden" value="0" id="HiddenID">
                                     <input type="hidden" value="${sessionScope.username }" id="hiddenUsername">
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
                                                    <table class="table table-bordered " id="leaveDataTable">
                                                        <thead>
                                                            <tr>
                                                                <th>S.No</th>
                                                                <th>From Date</th>
                                                                <th>To Date</th>
                                                                <th>Action</th>
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
        <script src="customjs/addleaves.js" type="text/javascript"></script>
        <%@include file = "footer.jsp"%>
    </body>
</html>

