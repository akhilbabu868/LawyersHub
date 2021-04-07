 <%-- 
    Document   : lawyerprofile
    Created on : 7 Feb, 2020, 9:47:28 PM
    Author     : ASUS
--%>


<!DOCTYPE html >
<%--<%@page pageEncoding="UTF-8"%>
<%@ page session="true"%>
--%><%@taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>User Profile </title>
        <%@include file = "header3.jsp"%>
        <link rel="stylesheet" href="customcss/userprofile.css">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="customjs/lawyerdashboard.js" type="text/javascript"></script>
    </head>

    <body>
        <div class="container main-secction">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12 image-section">
                    <img src="images/demo/backcover1.jpg"</div>
                <div class="row user-left-part">
                    <div class="col-md-3 col-sm-3 col-xs-12 user-profil-part pull-left">
                        <div class="row ">
                            <div class="col-md-12 col-md-12-sm-12 col-xs-12 user-image text-center">
                                <input type="hidden"  id="hiddenImg">
                                <img id="LogoA" src="images/demo/login.png" class="rounded-circle" alt="user"/>
                            </div>
                            <div class="col-md-12 col-sm-12 col-xs-12 user-detail-section1 text-center">
                                <button id="btnEdit"  data-toggle="modal" data-target="#contact" class="btn btn-warning btn-block follow">Edit Profile</button> 
                                <!--<button clas s="btn btn-warning btn-block">Descargar Curriculum</button>-->                               
                            </div>
                        </div>
                    </div>
                    <input type="hidden" value="${sessionScope.username }" id="hiddenUsername">
                    <input type="hidden" value="${sessionScope.username }" id="hiddenUserID">
                    <div class="col-md-9 col-sm-9 col-xs-12 pull-right profile-right-section">
                        <div class="row profile-right-section-row">
                            <div class="col-md-12 profile-header">
                                <div class="row">
                                    <div class="col-md-8 col-sm-6 col-xs-6 profile-header-section1 pull-left">
                                        <h1>${sessionScope.username }</h1>
                                    </div>
                                    <div class="col-md-4 col-sm-6 col-xs-6 profile-header-section1 text-right pull-rigth">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="row">
                                    <div class="col-md-8">
                                        <ul class="nav nav-tabs" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" href="#profile" role="tab" data-toggle="tab"><i class="fas fa-user-circle"></i>Profile</a>
                                            </li>
                                        </ul>
                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane fade show active" id="profile">
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <label>Name</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <p id="fullname"></p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <label>Birth Date</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <p id="dob"></p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <label>Address</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <p id="addres"></p>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <label>Contact</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <p id="mob"></p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="contact" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <form action="javascript:submitmodalForm();" method="POST"  id="UpdateForm" enctype="multipart/form-data">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" >Edit Profile</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">X</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="form-group row">
                            <label for="inputAddress" class="col-sm-4 col-form-label">Address</label>
                            <div class="col-sm-6">
                                <textarea id="modaladdress" class="form-control" name="modaladdress" cols="28" rows="3" placeholder="address"></textarea>
                            </div>
                        </div> 
                        <div class="form-group row">
                            <label for="inputMobile" class="col-sm-4 col-form-label">Mobile</label>
                            <div class="col-sm-6">
                                <input type="number" class="form-control" id="modalmobile" name="modalmobile" placeholder="mobile">
                            </div>
                        </div> 
                        <div class="form-group row">
                            <label for="image" class="col-sm-4 col-form-label">Upload Image</label>
                            <div class="col-sm-8">
                                <input type="file" class="form-control" id="file"  name="file" >
                            </div>
                        </div> 
                         <div class="form-actions">
                        <input type="submit" id="Submit" style="width: 139px;margin-top: 20px;" class='btn btn-success  btn-block'
                               value="Update"  >
                        <input type="hidden" id="UserID">
                    </div>
                    </div>
                </div>
                <div class="modal-footer">
                     
                </div>
                </form>
            </div>
        </div>
    </div>   
    <%@include file = "footer.jsp"%>
</body>
</html>
