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
            padding-top:18%;
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
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="index/upload.do">Upload Warc</a></li>
                        <li><a href="index/addDocument.do">Upload Document</a></li>
                        <li><a href="index/data.do">Library Data</a></li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>
        <div class="span12">
            <div class="jumbotron">
                <img src="resources/img/logo.png" />
                <form class="form-search" action="<c:url value="/query/result.do" />">
                    <div class="control-group">
                        <div class="input-append">
                            <input type="text" name="keyword" class="span6 search-query input-xxlarge" autofocus="true">
                            <button type="submit" class="btn btn-primary">Search</button>
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
<script src="<c:url value="/resources/js/jquery1.9.min.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

</body>
</html>
