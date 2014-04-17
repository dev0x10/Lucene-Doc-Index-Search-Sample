<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Information Retrieval Project</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Information Retrieval Project NTUT 2013">
    <meta name="author" content="yauri, zaza">

    <link media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css"/>
    <link media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/stickyfooter.css" type="text/css"/>
    <style type="text/css">
        .jumbotron {
            margin: 60px 0;
            text-align: center;
            padding-top:15%;
        }
    </style>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="<c:url value="/resources/js/html5shiv.js"/>"></script>
    <![endif]-->
</head>

<body>
<div id="wrap">
    <div class="container">
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner" style="padding-left:25px;">
                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="nav-collapse collapse">
                    <ul class="nav">
                        <li><a href="../">Home</a></li>
                        <li><a href="upload.do">Upload Warc</a></li>
                        <li class="active"><a href="#">Upload Document</a></li>
                        <li><a href="data.do">Library Data</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>
        <div id="formDiv" class="span12 text-center">
            <div class="jumbotron">
                <h3>Add document *.doc(x), *.ppt(x), *.xls(x), *.pdf</h3>
                <form class="form-upload" method="post" action="saveDocument.do" enctype="multipart/form-data">
                    <div class="control-group">
                        <input type="file" name="document" class="input-xxlarge search-query"/>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <input type="submit" id="upload" class="btn-inverse" value="Upload" />
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="push"></div>
</div>

<div id="footer">
    <div class="container">
        <p class="muted credit">&copy;<script>var date = new Date;document.write(date.getFullYear().toString());</script> Powered by <a href="http://lucene.apache.org/core/">Apache Lucene</a></p>
    </div>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/spin.js"/>"></script>
<script>
    $(function(){
        $("#upload").click(function(){
            $(".jumbotron").html("<h3>Processing...</h3>" +
                    "<img src='<c:url value="/resources/img/loading.gif"/>'/>");
        });
    });
</script>
</body>
</html>
