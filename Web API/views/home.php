<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="public/img/brand.svg">

<title>RTTminingWeb</title>
<link rel="stylesheet" href="public/css/bootstrap.min.css">

<style>

#diagram {
    margin: 0;
    border: 0;
    padding: 0;
    width: 100%;
    height: 400px;
}

</style>

</head>
<body>

<nav class="navbar navbar-light bg-faded" style="background-color: whitesmoke;">
    <a class="navbar-brand" href="#">
        <img src="public/img/brand.svg" width="32" height="32" alt="">
        RTTminingWeb
    </a>
</nav>

<div class = 'container'>

    <div class = 'row justify-content-center'>
        <div class = 'col-8 col-sm-8'>

            <div class = 'alert alert-info mt-4'>

                <form id = 'upload_form' method = 'POST' action = 'log' class = 'form-inline'>
                    <div class = 'content' id = 'section_uploading'>
                        <div class = 'form-group'>
                            <input name = 'file' type = "file" accept=".mxml" id = 'log_upload' />
                        </div>
                    </div>
                </form>

            </div>
            <a href='#' id='btn_settings' class = 'btn btn-info btn-sm'>Settings</a>

        </div>
    </div>

    <div id = 'settings' style='display:none'>
        <div class = 'row justify-content-center pt-3'>
            <div class = 'col-8 col-sm-8'>

                <form id = 'settings_form' method = 'POST' action = '#' class = 'form'>
                    <div class="form-group">
                        <label>Sigma Log Noise</label>
                        <input id='sigma' type="number" class="form-control" name='sigma' step="0.1" min="0" max="1" value='0.5'>
                    </div>
                    <div class="form-group">
                        <label>Fall Factor</label>
                        <input id='ff' type="number" class="form-control" name='ff' step="0.1" min="0" max="1" value='0.9'>
                    </div>
                    <div class="form-group">
                        <label>Relative to best</label>
                        <input id='rtb' type="number" class="form-control" name='rtb' step="0.1" min="0" max="1" value='0.75'>

                    </div>
                </form>

            </div>
        </div>

        <div class = 'row justify-content-center'>
            <div class = 'col-8 col-sm-8'>

                <div class = 'alert alert-warning'>
                    </form>
                    <label>Constraints</label>
                    <form id = 'constraints_form' method = 'POST' action = 'constraints' class = 'form-inline'>
                        <div class = 'content' id = 'section_uploading'>
                            <div class = 'form-group'>
                                <input name='file' type="file" accept=".xml" id='constraints_upload' />
                            </div>
                        </div>
                    </form>

                </div>

            </div>
        </div>
    </div>

    <div class = 'row justify-content-center pt-3'>
        <div class = 'col-8 col-sm-8'>
            <button id = 'btn_process' class = 'btn btn-success invisible'>Process</button>
            <a id = 'btn_download' class = 'btn btn-danger invisible' href='#'>Download XMI</a>
            <a id = 'btn_ontology' class = 'btn btn-danger invisible' href='#'>Download Ontology</a>
            <a id = 'btn_visualize' class = 'btn btn-danger invisible' href='#'>Show Graph</a>
        </div>
    </div>

    <div class = 'row justify-content-center pt-3'>
        <div class = 'col-12 col-sm-12'>
            <div id = 'diagram' style = 'display:none'></div>
        </div>
    </div>

</div>

<script src='public/js/jquery-3.2.1.min.js'></script>
<script src='public/js/tether.min.js'></script>
<script src='public/js/bootstrap.min.js'></script>

<script src='public/js/go.js'></script>
<script src='public/js/rttmining.js'></script>
<script src='public/js/rttmining.graphics.js'></script>

<script>

var btn_process = document.getElementById('btn_process');
var btn_download = document.getElementById('btn_download');
var btn_visualize = document.getElementById( 'btn_visualize' );
var btn_ontology = document.getElementById('btn_ontology');
var btn_settings = document.getElementById('btn_settings');

var logFilename = null;
var constraintsFilename = null;

if( btn_process != null )
    btn_process.onclick = process;

if( btn_visualize != null )
    btn_visualize.onclick = visualize;

if( btn_settings != null )
    btn_settings.onclick = function(){
        $('#settings').toggle('slow');
    }

/*
    Esegui l'upload del file di log
*/
document.getElementById('log_upload').onchange = function(e){
    // Resetta tutto
    if( btn_download.className.includes("invisible") == false )
        btn_download.className += ' invisible';
    if( btn_visualize.className.includes("invisible") == false )
        btn_visualize.className += ' invisible';
    if( btn_ontology.className.includes("invisible") == false )
        btn_ontology.className += ' invisible';
    logFilename = constraintsFilename = null;
    //Retrieve the first (and only!) File from the FileList object
    var file = e.target.files[0];
    if (file) {
        var data = new FormData();
        data.append( 'file', file, file.name );
        $.ajax( {
            url: 'log',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            'success': function(e){

                if( e == "UPLOAD:ERROR" ){
                    alert("Upload fallito!");
                    if( btn_process.className.includes("invisible") == false )
                        btn_process.className += ' invisible';
                    return;
                }

                console.log( "Upload riuscito!");
                btn_process.className = btn_process.className.replace('invisible', '');
                logFilename = e;

            }
        } );
    }
}

document.getElementById('constraints_upload').onchange = function(e){
    //Retrieve the first (and only!) File from the FileList object
    var file = e.target.files[0];
    if (file) {
        var data = new FormData();
        data.append( 'file', file, file.name );
        $.ajax( {
            url: 'constraints',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            'success': function(e){

                if( e == "UPLOAD:ERROR" ){
                    alert("Upload fallito!");
                    return;
                }

                console.log( "Upload riuscito!");
                constraintsFilename = e;

            }
        } );
    }
}

function process(){

    $.post( 'process/' + logFilename,
            { 
                sigma: (document.getElementById('sigma').value),
                ff: (document.getElementById('ff').value),
                rtb: (document.getElementById('rtb').value),
                constraints: constraintsFilename 
            }
        )
        .done(function( e ) {

            if(e == "RTTminingRESULT:ERROR")
            {
                alert("RTTmining fallito!");
                if( btn_download.className.includes("invisible") == false )
                        btn_download.className += ' invisible';
                if( btn_visualize.className.includes("invisible") == false )
                    btn_visualize.className += ' invisible';
                if( btn_ontology.className.includes("invisible") == false )
                    btn_ontology.className += ' invisible';
                return;
            }

            btn_download.className = btn_download.className.replace('invisible', '');
            btn_ontology.className = btn_ontology.className.replace('invisible', '');
            btn_visualize.className = btn_visualize.className.replace('invisible', '');

            xmi = e;

            btn_download.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent( xmi ));
            btn_download.setAttribute('download', 'rttmining.xmi');

        }
    );

}

function visualize(){
    $.ajax( {
        url: 'visualize/' + logFilename,
        type: 'GET',
        data: null,
        processData: false,
        contentType: false,
        'success': function(e){

            if(e == "Visualize:ERROR")
            {
                alert("Cannot visualize the graph!");
                return;
            }

            var data = eval("[" + e + "]");

            RTTmining.show(data[0], data[1]);
            $('#diagram').show();

        }
    } );
}
            
RTTmining.init('diagram');

</script>

</body>
</html>
