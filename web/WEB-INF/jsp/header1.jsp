<%-- 
    Document   : header1
    Created on : 10 Feb, 2020, 9:22:10 PM
    Author     : ASUS
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="bootstrap4/bootstrap.min.css">
        <link rel="stylesheet" href="css/datatables/jquery.dataTables.css">
        <link href="css/plugins/select2/select2.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap4/popper.min.js" type="text/javascript"></script>
        <script src="bootstrap4/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/datatables/jquery.dataTables.js"></script>
        <script src="js/plugins/select2/select2.min.js" type="text/javascript"></script>
        <script src="customjs/login.js"></script>
    </head>
    <body>

        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <!-- Brand -->
            <a class="navbar-brand" href="#">Lawyers Hub</a>

            <!-- Links -->
            <ul class="navbar-nav">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        General Masters
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="district.do">District Master</a>
                        <a class="dropdown-item" href="town.do">Town Master</a>
                        <a class="dropdown-item" href="package.do">Package Master</a>
                        <a class="dropdown-item" href="qualificationmaster.do">Qualification Master</a>
                        <a class="dropdown-item" href="lawyertype.do">Lawyer Type Master</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                       View
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="viewuser.do">View Users</a>
                        <a class="dropdown-item" href="viewlawyer.do">View Lawyers</a>
                    </div>
                </li>  
                <li class="nav-item">
                    <a class="nav-link" href="login.do">Logout</a>
                </li>
                <!-- Dropdown -->

            </ul>
        </nav>
    </body>
</html>