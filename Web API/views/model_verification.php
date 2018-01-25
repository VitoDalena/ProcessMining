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
                            <input name = 'log' type = "file" accept=".mxml,.xes" id = 'log_upload'>Log File</input><br>
                            <input name = 'model' type = "file" accept=".xmi" id = 'model_upload'>Model File</input><br>
                            <input name = 'ontology' type = "file" accept=".owl" id = 'ontology_upload'>Ontology File</input>
                        </div>
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
</div>

<script src='public/js/jquery-3.2.1.min.js'></script>
<script src='public/js/tether.min.js'></script>
<script src='public/js/bootstrap.min.js'></script>

<script>

var btn_process = document.getElementById('btn_process');
var btn_download = document.getElementById('btn_download');

var logFilename = null;
var modFilename = null;
var ontFilename = null;
var constraintsFilename = null;

if( btn_process != null )
    btn_process.onclick = process;
    
/*
    Esegui l'upload dei file
*/
document.getElementById('log_upload').onchange = function(e){
    if( btn_download.className.includes("invisible") == false )
        btn_download.className += ' invisible';
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
    console.log( "Initializing");
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
            console.log("Done");
            if(e == "TBI")
            {
                alert("Funzione non ancora implementata!");
                return;
            }else{
                var countOOSAG = (e.match(/OOSAG:REPORT/g) || []).length;
                if(countOOSAG !== 0){
                    alert("More than 30% of the log does not follow the rules from the model. Is the model obsolete?")
                }else{
                var countOAG = (e.match(/OAG:REPORT/g) || []).length;
                if(countOAG !== 0){
                    alert("Many resources overlaps or activities are executed out of time-sequence. Please check the system, as it may have become faulty")
                }else{
                if(e == "ERROR"){
                    alert("An error as occurred, please contact the system's administrator");
                }
                }
                }
                report = e;
                report = report.replace(/OOSAG:REPORT\r/g,"");
                report = report.replace(/OAG:REPORT\r/g,"");
                btn_download.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent( report ));
                btn_download.setAttribute('download', 'report.txt');
                btn_download.className = btn_download.className.replace('invisible', '');
            }
        }
    );
}

</script>

</body>
</html>
