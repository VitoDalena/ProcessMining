<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="img/brand.svg">

<title>ProcessMining</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/app.css">

</head>
<body>

<nav class="navbar navbar-light bg-faded" style="background-color: #444;">
    <a class="navbar-brand" href="#">
        <img src="img/brand.svg" width="32" height="32" alt="">
    </a>
</nav>

<div class = 'container'>

    <div class = 'row justify-content-center'>
        <div class = 'col-8 col-sm-8'>

            <div class = 'alert alert-info mt-4'>

                <form id = 'upload_form' method = 'POST' action = 'upload' class = 'form-inline'>
                    <div class = 'content' id = 'section_uploading'>
                        <div class = 'form-group'>
                            <input name = 'file' type = "file" id = 'file_upload' />
                        </div>
                    </div>
                </form>

            </div>

        </div>
    </div>

</div>

<script src='js/jquery-3.2.1.min.js'></script>
<script src='js/tether.min.js'></script>
<script src='js/bootstrap.min.js'></script>

<script>

document.getElementById('file_upload').onchange = function(e){
    //Retrieve the first (and only!) File from the FileList object
    var file = e.target.files[0];
    if (file /*&& file.type == "application/text"*/) {
        var data = new FormData();
        data.append( 'file', file, file.name );
        $.ajax( {
            url: 'upload',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            'success': function(e){

                console.log( "Upload riuscito!");
                console.log( e );

            }
        } );
    }
    //else alert("You can upload only application/text files");
}

</script>

</body>
</html>
