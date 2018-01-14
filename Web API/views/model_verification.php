<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="public/img/brand.svg">

<title>Cnet2ADweb</title>
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
        Cnet2ADweb
    </a>
</nav>

<div class = 'container'>

    <div class = 'row justify-content-center'>
        <div class = 'col-8 col-sm-8'>

            <div class = 'alert alert-info mt-4'>

                <form id = 'upload_form'>
                    <div class = 'content' id = 'section_uploading'>
                        <div class = 'form-group'>
                            <input name = 'log' type = "file" accept=".mxml,.xes" id = 'log_upload'>Log File</input>
			    <br>
                            <input name = 'model' type = "file" accept=".xmi" id = 'model_upload'>Model File</input>
			    <br>
                            <input name = 'ontology' type = "file" accept=".owl" id = 'ontology_upload'>Ontology File</input>
                        </div>
                    </div>
                </form>

            </div>
            <a href='#' id='btn_settings' class = 'btn btn-info btn-sm'>Settings</a>
            <a href='#' id='btn_annotate' class = 'btn btn-info btn-sm'>Layers</a>

        </div>
    </div>

    <div id = 'settings' style='display:none'>
        <div class = 'row justify-content-center pt-3'>
            <div class = 'col-8 col-sm-8'>

                <form id = 'settings_form' method = 'POST' action = '#' class = 'form'>
                    <div class="form-group">
                        <label>Sigma Log Noise</label>
                        <input id='sigma' type="number" class="form-control" name='sigma' step="0.1" min="0" max="1" value='0.05'>
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

    <div id = 'annotate' style='display:none'>
        <div class = 'row justify-content-center pt-3'>
            <div class = 'col-8 col-sm-8'>

                <form id = 'settings_form' method = 'POST' action = '#' class = 'form'>
                    <div class="checkbox">
                        <label>
                            <input id='annotate_resources' type="checkbox" checked> Annotate Resources
                        </label>
                    </div>
                </form>

            </div>
        </div>
    </div>

    <div class = 'row justify-content-center pt-3'>
        <div class = 'col-8 col-sm-8'>
            <button id = 'btn_process' class = 'btn btn-success invisible'>Verify</button>
            <a id = 'btn_download' class = 'btn btn-danger invisible' href='#'>Download Report</a>
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
<script src='public/js/cnet2ad.js'></script>
<script src='public/js/cnet2ad.graphics.js'></script>

<script>

var btn_process = document.getElementById('btn_process');
var btn_download = document.getElementById('btn_download');
var btn_settings = document.getElementById('btn_settings');
var btn_annotate = document.getElementById('btn_annotate');

var logFilename = null;
var modFilename = null;
var ontFilename = null;
var constraintsFilename = null;

if( btn_process != null )
    btn_process.onclick = process;

if( btn_settings != null )
    btn_settings.onclick = function(){
        $('#settings').toggle('slow');
    }

if( btn_annotate != null )
    btn_annotate.onclick = function(){
        $('#annotate').toggle('slow');
    }

/*
    Esegui l'upload dei file
*/
document.getElementById('log_upload').onchange = function(e){
    if( btn_download.className.includes("invisible") == false )
        btn_download.className += ' invisible';
    $('#diagram').hide();
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
                    alert("Upload failed!");
                    if( btn_process.className.includes("invisible") == false )
                        btn_process.className += ' invisible';
                    return;
                }

                console.log( "Upload riuscito!");
                logFilename = e;

            }
        } );
    }
}

document.getElementById('model_upload').onchange = function(e){
    if( btn_download.className.includes("invisible") == false )
        btn_download.className += ' invisible';
    $('#diagram').hide();
    //Retrieve the first (and only!) File from the FileList object
    var file = e.target.files[0];
    if (file) {
        var data = new FormData();
        data.append( 'file', file, file.name );
        $.ajax( {
            url: 'mod',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            'success': function(e){

                if( e == "UPLOAD:ERROR" ){
                    alert("Upload failed!");
                    if( btn_process.className.includes("invisible") == false )
                        btn_process.className += ' invisible';
                    return;
                }

                console.log( "Upload riuscito!");
                btn_process.className = btn_process.className.replace('invisible', '');
                modFilename = e;

            }
        } );
    }
}

document.getElementById('ontology_upload').onchange = function(e){
    if( btn_download.className.includes("invisible") == false )
        btn_download.className += ' invisible';
    $('#diagram').hide();
    //Retrieve the first (and only!) File from the FileList object
    var file = e.target.files[0];
    if (file) {
        var data = new FormData();
        data.append( 'file', file, file.name );
        $.ajax( {
            url: 'ont',
            type: 'POST',
            data: data,
            processData: false,
            contentType: false,
            'success': function(e){

                if( e == "UPLOAD:ERROR" ){
                    alert("Upload failed!");
                    if( btn_process.className.includes("invisible") == false )
                        btn_process.className += ' invisible';
                    return;
                }

                console.log( "Upload riuscito!");
                ontFilename = e;

            }
        } );
    }
}

function process(){
    console.log( "Parto");
    if( btn_download.className.includes("invisible") == false )
        btn_download.className += ' invisible';
    $.post( 'verify',
            { 
                log_file: logFilename,
                mod_file: modFilename,
                ont_file: ontFilename,
            }
        )
        .done(function( e ) {
            console.log("Fatto");
            if(e == "TBI")
            {
                alert("Funzione non ancora implementata!");
                return;
            }else{
                if(strpos(e, 'OOSAG:REPORT') !== false){
                    alert("More than 30% of the log does not follow the rules from the model. Is the model obsolete?")
                }else{
                if(strpos(e, 'OAG:REPORT') !== false){
                    alert("Many resources overlaps or activities are executed out of time-sequence. Please check the system, as it may have become faulty")
                }else{
                    alert(e);
                }
                }
                report = e;
                btn_download.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent( report ));
                btn_download.setAttribute('download', 'flink-1.3.2\log\report.txt');
                btn_download.className = btn_download.className.replace('invisible', '');
            }
        }
    );

}
            
Cnet2AD.init('diagram');

</script>

</body>
</html>
