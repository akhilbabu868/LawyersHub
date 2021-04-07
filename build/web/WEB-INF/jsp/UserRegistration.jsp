<%-- 
    Document   : UserRegistration
    Created on : 21 Jan, 2020, 9:59:18 AM
    Author     : ASUS
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>  
<html>  
    <head>  
        <link rel="stylesheet" href="bootstrap4/bootstrap.min.css">
        <link rel="stylesheet" href="css/plugins/select2/select2.css">
        <link href="css/plugins/datepicker/datepicker.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap4/popper.min.js" type="text/javascript"></script>
        <script src="bootstrap4/bootstrap.min.js" type="text/javascript"></script>    
        <script src="js/plugins/fileupload/bootstrap-fileupload.min.js"></script>
        <script src="js/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
        <title>User Sign Up</title> 
        <style type="text/css">
            body{
                /*color: #fff;*/
                background: #63738a;
                font-family: 'Roboto', sans-serif;
            }


            *[role="form"] h2 {
                margin-left: 5em;
                margin-bottom: 1em;
            }
            #UserRegistrationForm{
                background-color: lightcoral;
                padding: 30px;
                margin-top: 20px;
                margin-bottom: 50px;
                border-radius: 5px;  

            }
        </style>
    </head>

    <body>  


        <div class="container">
            <div class="d-flex justify-content-center">
                <form action="javascript:submitForm();" method="POST"  id="UserRegistrationForm" enctype="multipart/form-data">
                    <div class="col text-center">
                    <h4>User Registration </h4>
                    </div>
                    <hr>
                <div class="form-group row">
                    <label for="firstname" class="col-sm-4 col-form-label">First Name</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control"  placeholder="Enter firstname" name="firstname"  id="firstname">
                    </div>
                </div> 
                <div class="form-group row">
                    <label for="lastname" class="col-sm-4 col-form-label">Last Name</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" placeholder="Enter lastname" name="lastname" id="lastname">
                    </div>
                </div> 
                <div class="form-group row">
                    <label for="address" class="col-sm-4 col-form-label">Address</label>
                    <div class="col-sm-8">
                        <textarea class="form-control" rows="3" name="address" id="address"></textarea>
                    </div>
                </div> 
                <div class="form-group row">
                    <label for="dob" class="col-sm-4 col-form-label">Date Of Birth</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="dob" placeholder="Date of Birth" name="dob" >
                    </div>
                </div> <!--
                -->                 <div class="form-group row">
                    <label for="address" class="col-sm-5 col-form-label">Gender</label>
                    <div class="col-sm-3">
                        <input type="radio" class="form-check-input" value="male" id="male" name="gender">Male
                    </div>
                    <div class="col-sm-4">
                        <input type="radio" class="form-check-input" value="female" id="female" name="gender">Female
                    </div>
                </div> 
                <div class="form-group row">
                    <label for="dob" class="col-sm-4 col-form-label">District</label>
                    <div class="col-sm-8">
                        <select class="js-example-placeholder-single  form-control" name="District" onchange="groupDropDown(this.value)" id="District"></select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="dob" class="col-sm-4 col-form-label">Town</label>
                    <div class="col-sm-8">
                        <select class="js-example-placeholder-single form-control" name="Town" id="Town"></select>
                    </div>
                </div><!--
                --> <div class="form-group row">
                    <label for="address" class="col-sm-4 col-form-label">Upload Image</label>
                    <div class="col-sm-8">
                        <input type="file" class="form-control" id="file"  name="file" >
                    </div>
                   </div>
                <div class="form-group row">
                    <label for="dob" class="col-sm-4 col-form-label">Email</label>
                    <div class="col-sm-8">
                        <input type="email" id="email" name="email" placeholder="Email" class="form-control">
                    </div>
                </div><!--<!--
                --> <div class="form-group row">
                    <label for="dob" class="col-sm-4 col-form-label">Password</label>
                    <div class="col-sm-8">
                        <input type="password" id="password" name="password" placeholder="Password" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="dob" class="col-sm-4 col-form-label">Mobile</label>
                    <div class="col-sm-8">
                        <input type="text" id="mobile" name="mobile" maxlength="14" placeholder="mobile" class="form-control">
                    </div>
                    <div class="d-block mx-auto text-center">
                    <div class="form-actions">
                        <input type="submit" id="Submit" style="width: 139px;margin-top: 20px;" class='btn btn-success ' value="Register"  >&nbsp;&nbsp;
                        <input type="button"  id="cancel" style="width: 139px;margin-top: 20px;"  onclick="window.location.href = 'login.do';"   class='btn btn-danger' value="Cancel"  >
                    </div>
                    </div>
                </div>
                 </form>
       </div>
        <script src="js/plugins/select2/select2.min.js"></script>   
        <script src="customjs/userregistration.js" type="text/javascript"></script>
    </body>  
</html> 
