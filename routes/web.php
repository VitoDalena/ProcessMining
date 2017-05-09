<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$app->get('/', function () use ($app) {
    // Show the view of application
    // located in resources/views
    return view('home');
});

// Manage the upload of the file
$app->post('/upload', "UploadController@upload");

//$app->get('/cnmining', 'CNMiningController@process');
