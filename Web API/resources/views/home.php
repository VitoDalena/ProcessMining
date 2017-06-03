<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="img/brand.svg">

<title>RTTminingWeb</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/app.css">

</head>
<body>

<nav class="navbar navbar-light bg-faded" style="background-color: whitesmoke;">
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
                            <input name = 'file' type = "file" accept=".mxml" id = 'file_upload' />
                        </div>
                    </div>
                </form>

            </div>

        </div>
    </div>

    <div class = 'row justify-content-center pt-3'>
        <div class = 'col-8 col-sm-8'>
            <button id = 'btn_process' class = 'btn btn-success invisible'>Process</button>
            <a id = 'btn_download' class = 'btn btn-danger invisible' href='#'>Download XMI</a>
            <a id = 'btn_visualize' class = 'btn btn-danger invisible' href='#'>Show Graph</a>
        </div>
    </div>

</div>

<script src='js/jquery-3.2.1.min.js'></script>
<script src='js/tether.min.js'></script>
<script src='js/bootstrap.min.js'></script>

<script src='js/go.js'></script>
<script src='js/rttmining.js'></script>
<script src='js/rttmining.graphics.js'></script>

<script>

var btn_process = document.getElementById('btn_process');
var btn_download = document.getElementById('btn_download');
var btn_visualize = document.getElementById( 'btn_visualize' );

if( btn_process != null )
    btn_process.onclick = process;

if( btn_download != null)
    btn_visualize.onclick = visualize;

/*
    Esegui l'upload del file di log
*/
document.getElementById('file_upload').onchange = function(e){
    //Retrieve the first (and only!) File from the FileList object
    var file = e.target.files[0];
    if (file) {
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
                btn_process.className = btn_process.className.replace('invisible', '');

            }
        } );
    }
}

function process(){


    btn_download.className = btn_download.className.replace('invisible', '');
    btn_visualize.className = btn_visualize.className.replace('invisible', '');


    xmi = "provadownload";

    btn_download.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent( xmi ));
    btn_download.setAttribute('download', 'rttmining.xmi');
}

function visualize(){

}

</script>

</body>
</html>
