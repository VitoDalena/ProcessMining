<?php

//credits:https://github.com/vitodtagliente/Pure
include_once "lib/Router.php";
include_once "lib/Controller.php";
Pure\Route::path( __DIR__ . '/controllers' );

// Verifica l'esistenza delle cartelle necessarie
// in caso negativo, creale

if( file_exists(__DIR__ . '/bin') == false )
	mkdir(__DIR__ . '/bin');
if( file_exists(__DIR__ . '/public/uploads') == false )
	mkdir(__DIR__ . '/public/uploads');
if( file_exists(__DIR__ . '/public/uploads/log') == false )
	mkdir(__DIR__ . '/public/uploads/log');
if( file_exists(__DIR__ . '/public/uploads/constraints') == false )
	mkdir(__DIR__ . '/public/uploads/constraints');

// Definizione delle rotte

$router = new Pure\Router();

$router->get("/", "HomeController@index");
$router->get("/modgen", "HomeController@modgen");
$router->get("/modver", "HomeController@modver");
// Pulizia forzata
$router->get("/reset", function(){
	array_map('unlink', glob(__DIR__."/bin/*.*"));
	array_map('unlink', glob(__DIR__."/public/uploads/log/*.*"));
	array_map('unlink', glob(__DIR__."/public/uploads/constraints/*.*"));

	// redireziona verso la home
	@header( "Location: /", true, 302 );
});

// POST ajax per l'upload dei file
$router->post("/log", "UploadController@log");
$router->post("/mod", "UploadController@mod");
$router->post("/ont", "UploadController@ont");
$router->post('/constraints/$name', "UploadController@constraints");

// API ajax per l'elaborazione RTTmining
$router->post('/process/$filename', "Cnet2ADController@process");
$router->post('/verify', "Cnet2ADController@verify"); //To be implemented
// GET ajax per ottenere i dati di visualizzazione
$router->get('/visualize/$filename', "Cnet2ADController@visualize");

if( !$router->dispatch() ){
	echo "Error 404";
}

?>
