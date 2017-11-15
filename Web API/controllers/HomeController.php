<?php

use Pure\Controller;

class HomeController extends Controller {

    function index(){
        
    	include __DIR__ . '/../views/home.php';
    
    }

    function modgen(){
        
    	include __DIR__ . '/../views/model_generation.php';
    
    }

    function modver(){
        
    	include __DIR__ . '/../views/model_verification.php';
    
    }

}

?>
