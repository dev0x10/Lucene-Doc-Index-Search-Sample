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
                        <li><a href="upload.do">Upload Warc</a></li>
                        <li><a href="addDocument.do">Upload Document</a></li>
                        <li class="active"><a href="#">Library Data</a></li>
                    </ul>
                </div>
            </div>

            <div class="inner cover">
                <div class="row">
                    <div class="col-lg-6">
                        <c:choose>
                        <c:when test="${totalKeywords > 0}">
                            <h3>Total Keywords : ${totalKeywords}</h3>
                            <h3>Total Docs : ${totalDocs}</h3>
                        </c:when>
                        <c:otherwise>
                            <h3>Cannot find index file~</h3>
                        </c:otherwise>
                    </c:choose>
                    </div>
                </div>
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
<script>
    $(function(){

    })
</script>

</body>

</html>


