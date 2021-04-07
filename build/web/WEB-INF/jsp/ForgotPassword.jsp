<%-- 
    Document   : ForgotPassword
    Created on : 16 Feb, 2020, 10:18:20 PM
    Author     : ASUS
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="bootstrap4/bootstrap.min.css">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap4/popper.min.js" type="text/javascript"></script>
        <script src="bootstrap4/bootstrap.min.js" type="text/javascript"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <div class="container">
            <div class="col-md-10">
                <div class="card"  style="margin-top: 50px">
                    <div class="card-header bg-danger">Forgot Password</div>
                    <form method="post" action="sendEmail.do">
                        <div class="card-body">
                            <div class="form-group row">
                                <label for="lastname" class="col-sm-4 col-form-label">Enter Registred Email ID</label>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" placeholder="Enter Email ID Here.." name="email" id="email">
                                </div>
                            </div> 
                            <div class="col text-center">
                                <input type="submit" class="btn btn-success" value="Send E-mail" />
                                <a href='login.do'  class='btn btn-warning'>Cancel</a>
                            </div>

                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
