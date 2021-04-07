<%-- 
    Document   : booking
    Created on : 8 Feb, 2020, 11:54:25 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking</title>
        <%@include file = "header2.jsp"%>
    </head>
    <body style="color:black">
        <div class="container">
            <div class="col-sm-8">
                <div class="card"  style="margin-top: 100px">
                    <div class="card-header bg-danger">Booking</div>
                    <div class="card-body">
                        <input type="hidden" value="${sessionScope.username }" id="hiddenUsername">
                        <input type="hidden" id="hiddenLawyer">
                        <form   id="BookingForm" >
                            <div class="form-group row">
                                <label for="date" class="col-sm-4 col-form-label">Booking Date</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" data-provide="datepicker" placeholder="Choose Date"  id="bookingdate"name="bookingdate" >
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="inputPassword" class="col-sm-4 col-form-label">Subject</label>
                                <div class="col-sm-6">
                                    <input type="text" class="form-control" name="title" id="title" placeholder="title">
                                </div>
                            </div>
                             <div class="form-group row">
                                <label for="inputPassword" class="col-sm-4 col-form-label">Message</label>
                                <div class="col-sm-6">
                                    <textarea class="form-control" id="description" name="description" rows="4"> </textarea>
                                </div>
                             </div>
                             <div class="col text-center">
                                 <div class="form-actions">
                                     <input type="hidden"  id="hiddenUserID">
                                     <input type="hidden"  id="hiddenLawyerID">
                                     <button type="button" id="booking"  class="btn block btn btn-success">Book Now</button>
                                 </div>
                             </div>
                        </form>   
                    </div>
                </div>
            </div>

<!--        </div>-->
       </div>
    </body>
    <script src="customjs/booking.js" type="text/javascript"></script>
  <%--<%@include file = "footer.jsp"%>--%>
</html>