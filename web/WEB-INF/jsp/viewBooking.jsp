<%-- 
    Document   : viewBooking
    Created on : 15 Mar, 2020, 9:13:53 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--View booking-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Booking</title>
        <%@include file = "header3.jsp"%>
        <script src="customjs/viewbooking.js"></script>
    </head>
    <body style="color:black">
        <div class="container">
            <div class="col-sm-10">
                <div class="card" style="margin-top: 50px">
                    <div class="card-header bg-primary">View Booking Details </div>
                    <div class="card-body">
                        <div class="row-fluid">
                            <div class="span12">
                                <div class="box box-color box-bordered box-small">
                                    <form>
                                        <input type="hidden" value="${sessionScope.lawyerID }" id="sessionlawyerID">
                                        <div class="box-content nopadding">
                                            <div class="dataTables_scroll">
                                                <div class="dataTables_scrollBody" style="overflow:auto; width:100%;">
                                                    <table class="table table-bordered " id="viewBookingDataTable">
                                                        <thead>
                                                            <tr>
                                                                <th>S.No</th>
                                                                <th>Booking Date</th>
                                                                <th>Case Title</th>
                                                                <th>Case</th>
                                                                <th>Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
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
<!--                     Modal 
-->                    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLongTitle">User Details</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <img  class ='card-img-top img-responsive' style="width: 150px;height:150px" id="userImg" > 
                                    <div><b>Name:</b> <p id="name"></p></div>
                                    <div><b>Address</b><p id="addr"></p></div>
                                    <div><b>District</b> <p id="dist"></p></div>
                                    <div><b>Town</b>  <p id="tow"></p></div>
                                    <div><b>Mobile</b>  <p id="mob"></p></div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
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


