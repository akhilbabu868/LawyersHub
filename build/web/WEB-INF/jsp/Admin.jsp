<%-- 
    Document   : districtmaster
    Created on : 21 Jan, 2020, 9:58:14 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <%@include file = "header1.jsp"%>
        
        <script src="js/Chart.min.js" type="text/javascript"></script>
        <script src="js/utils.js" type="text/javascript"></script>
        <script src="customjs/AdminDashboard.js" type="text/javascript"></script>
       
    </head>
    <body>
        <div class="container">
        <div style="width:75%;">
		<canvas id="canvas"></canvas>
	</div>
            
         <div style="width:75%;">
		<canvas id="canvasLawyer"></canvas>
	</div>   
        </div>	
    </body>
     <%@include file = "footer.jsp"%>
</html>

