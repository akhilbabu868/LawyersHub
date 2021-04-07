<%-- 
    Document   : searchlawyers
    Created on : 8 Feb, 2020, 3:54:22 PM
    Author     : ASUS
--%>


<!DOCTYPE html >
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Search Lawyers </title>
        <%@include file = "header2.jsp"%>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href="customcss/rateing.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/plugins/select2/select2.min.js"></script>
        <script src="customjs/searchlawyer.js"></script>
        <script src="customjs/viewreview.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container h-100">
            <div class="d-flex justify-content-center h-20">
                <select class="js-example-placeholder-single form-control" style="width: 500px;height: 30px" id="lawyertype"></select>
                <div class="col-sm-3">
                    <button type="button" id="btnsearch" class="btn btn-success">Search</button>
                </div>
                <input type="hidden" value="${sessionScope.username }" id="hiddenUsername">
                <input type="hidden"  id="hiddenUserId">
            </div>
            <div class="row" style="margin-top: 100px">
                <div class="row" id="contentPanel">
                </div> 
                <div class="container" id="lawyerContainer">
                </div>
                <!-- Modal -->
                <div class="modal fade" id="RateingModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">Add Review</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <div class="form-group row">
                                    <label for="inputPassword" class="col-sm-2 col-form-label">Comment</label>
                                    <input type="hidden" value="0" id="lawyrId">
                                    <div class="col-sm-10">
                                        <textarea id="comment" class="form-control"  rows="4"></textarea>
                                    </div>
                                </div>
                                <br>
                                <div class="rating">
                                    <input type="radio" name="rating" value="5" id="rating-5">
                                    <label for="rating-5"></label>
                                    <input type="radio" name="rating" value="4"  id="rating-4">
                                    <label for="rating-4"></label>
                                    <input type="radio" name="rating" value="3"  id="rating-3">
                                    <label for="rating-3"></label>
                                    <input type="radio" name="rating" value="2"  id="rating-2">
                                    <label for="rating-2"></label>
                                    <input type="radio" name="rating" value="1"  id="rating-1">
                                    <label for="rating-1"></label>
                                </div>
                                <div>
                                    <br> <a href="#" onclick="showReview()" id="Idlawyr">Show Reviews</a>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                <button type="button" id="btnReview" class="btn btn-danger">Add Review</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <%@include file = "footer.jsp"%>
</html>

