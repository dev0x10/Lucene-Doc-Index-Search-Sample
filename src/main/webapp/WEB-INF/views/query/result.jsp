<%--<!DOCTYPE html>--%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ page import="java.io.*" %>--%>
<%--<html lang="en">--%>
<%--<head>--%>
    <%--<meta charset="utf-8">--%>
    <%--<title>Information Retrieval Project</title>--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<meta name="description" content="Information Retrieval Project NTUT 2013">--%>
    <%--<meta name="author" content="yauri, zaza">--%>

    <%--<link media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css"/>--%>
    <%--<link media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/stickyfooter.css" type="text/css"/>--%>
    <%--<style type="text/css">--%>
        <%--body {--%>
            <%--padding-top: 50px;--%>
        <%--}--%>

            <%--/* Wrapper for page content to push down footer */--%>
        <%--#wrap {--%>
            <%--min-height: 100%;--%>
            <%--height: auto !important;--%>
            <%--height: 100%;--%>
            <%--/* Negative indent footer by it's height */--%>
            <%--margin: 0 auto -20px;--%>
            <%--padding-top:60px;--%>
        <%--}--%>

            <%--/* Set the fixed height of the footer here */--%>

        <%--#footer {--%>
            <%--height: 40px;--%>
            <%--padding-top:  20px;--%>
        <%--}--%>

        <%--#push{--%>
            <%--height: 40px;--%>
        <%--}--%>

        <%--#footer {--%>
            <%--background-color: #f5f5f5;--%>
        <%--}--%>

        <%--.top-nav{--%>
            <%--background-image: none;--%>
            <%--background-color:#F5F5F5;--%>
            <%--position: fixed;--%>
            <%--right: 0;--%>
            <%--left: 0;--%>
            <%--z-index: 1030;--%>
            <%--text-align: center;--%>
            <%--border-bottom:1px solid black;--%>
        <%--}--%>

        <%--.search-result{--%>
            <%--margin-top: 20px;--%>
        <%--}--%>

        <%--.result-title{--%>
            <%--color:#0054CC;--%>
            <%--font-size: 16px;--%>
        <%--}--%>

        <%--.highlight {--%>
            <%--background-color: #FFFF33;--%>
        <%--}--%>
    <%--</style>--%>

    <%--<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->--%>
    <%--<!--[if lt IE 9]>--%>
    <%--<script src="<c:url value="/resources/js/html5shiv.js"/>"></script>--%>
    <%--<![endif]-->--%>
<%--</head>--%>

<%--<body>--%>
<%--<div id="wrap">--%>
    <%--<div class="navbar navbar-inverse navbar-fixed-top navbar-fixed-top-extra">--%>
        <%--<div class="container-fluid">--%>
            <%--<div class="top-nav">--%>
                <%--<h4>Keyword(s):</h4>--%>
                <%--<form class="form-search" id="form-search">--%>
                    <%--<div class="input-append">--%>
                        <%--<input type="text" name="keyword" class="span6 search-query input-xxlarge" autofocus="true">--%>
                        <%--<button type="submit" class="btn btn-primary">Search</button>--%>
                    <%--</div>--%>
                <%--</form>--%>
                <%--<a href="../">Click here to go back home</a>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="container-fluid">--%>

        <%--<div class="row-fluid search-result">--%>
            <%--<div class="offset1 span7">--%>
                <%--<h4>Result(s) for: <span id="keyword">${keyword}</span></h4>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<c:choose>--%>
            <%--<c:when test="${totalResult > '0'}">--%>
                <%--<c:forEach items="${results}" var="result">--%>
                    <%--<div id="searchResult" class="offset1 span7">--%>
                        <%--<span><a class="result-title" data-toggle="tooltip" target="_blank" data-placement="right" href="file://${result.getKey()}" title="${result.getKey()}">${result.getKey()} </a></span>--%>
                        <%--<p>...${result.getValue()}...</p>--%>
                        <%--<hr>--%>
                    <%--</div>--%>
                <%--</c:forEach>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
             <%--<div class="offset1 span7">--%>
                <%--<p>Your search - ${keyword} - did not match any documents.</p>--%>
             <%--</div>--%>
            <%--</c:otherwise>--%>
        <%--</c:choose>--%>

    <%--</div><!--/.fluid-container-->--%>
    <%--<div id="push"></div>--%>
<%--</div>--%>
<%--<div id="footer">--%>
    <%--<div class="container">--%>
        <%--<p class="muted credit">&copy;<script>var date = new Date;document.write(date.getFullYear().toString());</script> Powered by <a href="http://lucene.apache.org/core/">Apache Lucene</a></p>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>--%>
<%--<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/js/highlighter.js"/>"></script>--%>
<%--<script>--%>
    <%--$(function(){--%>
        <%--$(".result-title").tooltip();--%>

        <%--var keyword = $("#keyword").text();--%>
        <%--keyword = keyword.replace(/[^a-zA-Z 0-9]+/g, '');--%>
        <%--$("p").highlight(keyword);--%>
    <%--})--%>
<%--</script>--%>
<%--</body>--%>
<%--</html>--%>


<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Apache Lucene Sample Application</title>

    <link media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
          type="text/css"/>
    <link media="screen" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mod.css"
          type="text/css"/>
    <style type="text/css">
        .search-result{
            margin-top: 20px;
        }

        .result-title{
            color:#0054CC;
            font-size: 16px;
        }

        .highlight {
            background-color: #f0ad4e;
        }
    </style>
</head>

<body>
<a href="https://github.com/dev0x10/Lucene-Doc-Index-Search-Sample.git"><img style="position: absolute; top: 0; left: 0; border: 0;" src="https://camo.githubusercontent.com/8b6b8ccc6da3aa5722903da7b58eb5ab1081adee/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f6c6566745f6f72616e67655f6666373630302e706e67" alt="Fork me on GitHub" data-canonical-src="https://s3.amazonaws.com/github/ribbons/forkme_left_orange_ff7600.png"></a>
<div class="site-wrapper">

    <div class="site-wrapper-inner">

        <div class="cover-container">
            <div class="masthead clearfix">
                <div class="inner">
                    <h3 class="masthead-brand"></h3>
                    <ul class="nav masthead-nav">
                        <li><a href="../">Home</a></li>
                        <li><a href="../index/upload.do">Upload Warc</a></li>
                        <li><a href="../index/addDocument.do">Upload Document</a></li>
                        <li><a href="../index/data.do">Library Data</a></li>
                    </ul>
                </div>
            </div>

            <div class="inner cover">
                <form class="form-search" id="form-search">
                    <div class="input-group">
                        <input type="text" name="keyword" autofocus="true" class="form-control" placeholder="Keyword">
                        <span class="input-group-btn">
                            <button class="btn btn-warning" type="submit">Search</button>
                        </span>
                    </div>
                </form>
                <div class="row search-result">
                    <div class="col-lg-offset-1 col-lg-10">
                        <h4>Result(s) for: <span id="keyword">${keyword}</span></h4>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${totalResult > '0'}">
                        <c:forEach items="${results}" var="result">
                            <div id="searchResult" class="col-lg-offset-1 col-lg-span10">
                                <span><a class="result-title" data-toggle="tooltip" target="_blank" data-placement="top" href="file://${result.getKey()}" title="${result.getKey()}">${result.getKey()} </a></span>
                                <p>...${result.getValue()}...</p>
                                <hr>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div class="col-lg-offset-1 col-lg-10">
                            <p>Your search - ${keyword} - did not match any documents.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="mastfoot">
                <div class="inner">
                    <p class="muted credit">&copy;<script>var date = new Date;document.write(date.getFullYear().toString());</script> Powered by <a href="http://lucene.apache.org/core/">Apache Lucene</a></p>
                </div>
            </div>

        </div>

    </div>

</div>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/resources/js/highlighter.js"/>"></script>
<script>
    $(function(){
        $(".result-title").tooltip();
        var keyword = $("#keyword").text();
        keyword = keyword.replace(/[^a-zA-Z 0-9]+/g, '');
        $("p").highlight(keyword);
    })
</script>

</body>

</html>
