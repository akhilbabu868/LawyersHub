<%-- 
    Document   : viewlawyer
    Created on : 7 Feb, 2020, 8:47:16 PM
    Author     : ASUS
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Lawyer</title>
        <%@include file = "header1.jsp"%>
        <script src="customjs/viewlawyer.js"></script>
    </head>
    <body style="color:black">
        <div class="container">
           <div class="col-sm-10">
               <div class="card" style="margin-top: 50px">
                <div class="card-header bg-primary">Lawyer Details Table</div>
                <div class="card-body">
                    <div class="row-fluid">
                        <div class="span12">
                            <div class="box box-color box-bordered box-small">
                                <form>
                                    <div class="box-content nopadding">
                                        <div class="dataTables_scroll">
                                            <div class="dataTables_scrollBody" style="overflow:auto; width:100%;">
                                                <table class="table table-bordered " id="viewLawyerDataTable">
                                                    <thead>
                                                        <tr>
                                                            <th>S.No</th>
                                                            <th>First Name</th>
                                                            <th>Last Name</th>
                                                            <th>Email ID</th>
                                                            <th>Lawyer Type</th>
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
                <!-- Modal -->
                <div class="modal fade" id="lawyerModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Lawyer Details</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <img  class ='card-img-top img-responsive' style="width: 150px;height:150px" id="lawyerImg" > 
                                <div> Name: <p id="name"></p></div>
                                <div> Date Of Birth <p id="dob"></p></div>
                                <div> Gender  <p id="gen"></p></div>
                                <div> Address <p id="addr"></p></div>
                                <div> Qualification <p id="quali"></p></div>
                                <div> Type Of Lawyer <p id="type"></p></div>

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

