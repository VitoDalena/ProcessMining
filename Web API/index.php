<?php

//credits:https://github.com/vitodtagliente/Pure
include_once "lib/Router.php";
include_once "lib/Controller.php";
Pure\Route::path( __DIR__ . '/controllers' );

$router = new Pure\Router();

$router->get("/", "HomeController@index");

$router->post("/log", "UploadController@log");
$router->post("/ontology", "UploadController@ontology");
$router->post("/constraints", "UploadController@constraints");

$router->post('/process/$filename', "RTTminingController@process");
$router->get('/visualize/$filename', "RTTminingController@visualize");

if( !$router->dispatch() ){
	echo "Error 404";
}

?>