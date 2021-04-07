<%-- 
    Document   : viewReviews
    Created on : 19 May, 2020, 3:25:36 PM
    Author     : ASUS
--%>
<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Reviews</title>
        <%@include file = "header2.jsp"%>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="customcss/rateing.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/plugins/select2/select2.min.js"></script>
        <script src=""></script>
        <script src="customjs/viewreview.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-10" style="border: 1px solid" >
                    <h3>Reviews</h3> <hr>
                    <div class="col-sm-12" id="reviewContainer">
                    </div>
                </div>
            </div> 
        </div>
    </body>
    <%@include file = "footer.jsp"%>
</html>

