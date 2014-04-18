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
                        <li><a href="/">Home</a></li>
                        <li class="active"><a href="index/upload.do">Upload Warc</a></li>
                        <li><a href="index/addDocument.do">Upload Document</a></li>
                        <li><a href="index/data.do">Library Data</a></li>
                    </ul>
                </div>
            </div>

            <div class="inner cover">
                <h3 class="cover-heading">Upload .warc file</h3>
                <div class="row">
                    <div class="col-lg-6">
                        <form id="addWarc" class="form-upload" method="post" action="addWarc.do" enctype="multipart/form-data">
                            <div class="input-group">
                                <span class="input-group-btn">
                                    <span class="btn btn-warning btn-file">
                                        Browse <input type="file" name="indexfile" />
                                    </span>
                                </span>
                                <input type="text" class="form-control" readonly="">
                            </div>
                            <br />
                            <div class="control-group">
                                <div class="controls">
                                    <input type="submit" id="upload" class="btn btn-warning" value="Upload" />
                                </div>
                            </div>
                        </form>
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
    $(document)
            .on('change', '.btn-file :file', function() {
                var input = $(this),
                        numFiles = input.get(0).files ? input.get(0).files.length : 1,
                        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
                input.trigger('fileselect', [numFiles, label]);
            });

    $(document).ready( function() {
        $('.btn-file :file').on('fileselect', function(event, numFiles, label) {

            var input = $(this).parents('.input-group').find(':text'),
                    log = numFiles > 1 ? numFiles + ' files selected' : label;

            if( input.length ) {
                input.val(log);
            } else {
                if( log ) alert(log);
            }

        });
    });
</script>

</body>

</html>

