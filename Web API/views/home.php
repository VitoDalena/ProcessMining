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

                <form id = 'modgen_form' method = 'GET' action = 'modgen' class = 'form-inline'>
                    <div class = 'content' id = 'section_modgen'>
                        <div class = 'form-group'>
                            <input name = 'modgen_submit' type = "submit" value="Genera un nuovo modello" />
                        </div>
                    </div>
                </form>
		<br>
                <form id = 'modver_form' method = 'GET' action = 'modver' class = 'form-inline'>
                    <div class = 'content' id = 'section_modver'>
                        <div class = 'form-group'>
                            <input name = 'modver_submit' type = "submit" value="Verifica un modello esistente" />
                        </div>
                    </div>
                </form>

            </div>
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

var btn_modgen = document.getElementById('btn_modgen');
var btn_modever = document.getElementById('btn_modver');



</script>

</body>
</html>
