<?php

use Pure\Controller;

class UploadController extends Controller {

	/*
		Se l'upload avviene correttamente ritorna il nome del file,
		altrimenti ritorna la stringa UPLOAD:ERROR
	*/
    function log(){

        // pulisce la cartella di uploads
        self::clear("log");
        
        if( empty($_FILES) == true){
        	echo "UPLOAD:ERROR";
        	return;
        }        

        $name = basename($_FILES["file"]["tmp_name"], '.tmp');

        $filename = "public/uploads/log/$name.mxml";

        if( move_uploaded_file($_FILES["file"]["tmp_name"], $filename) )
        	echo $name;
    	else 
        	echo "UPLOAD:ERROR";
    }

    function mod(){

        // pulisce la cartella di uploads
        self::clear("mod");
        
        if( empty($_FILES) == true){
        	echo "UPLOAD:ERROR";
        	return;
        }        

        $name = basename($_FILES["file"]["tmp_name"], '.tmp');

        $filename = "public/uploads/mod/$name.xmi";

        if( move_uploaded_file($_FILES["file"]["tmp_name"], $filename) )
        	echo $name;
    	else 
        	echo "UPLOAD:ERROR";
    }

    function ont(){

        // pulisce la cartella di uploads
        self::clear("ont");
        
        if( empty($_FILES) == true){
        	echo "UPLOAD:ERROR";
        	return;
        }        

        $name = basename($_FILES["file"]["tmp_name"], '.tmp');

        $filename = "public/uploads/ont/$name.owl";

        if( move_uploaded_file($_FILES["file"]["tmp_name"], $filename) )
        	echo $name;
    	else 
        	echo "UPLOAD:ERROR";
    }

    function constraints($name){

        // pulisce la cartella di uploads
        self::clear("constraints");
        
        if( empty($_FILES) == true){
            echo "UPLOAD:ERROR";
            return;
        }        

        $filename = "public/uploads/constraints/$name.xml";

        if( move_uploaded_file($_FILES["file"]["tmp_name"], $filename) )
            echo $name;
        else 
            echo "UPLOAD:ERROR";
    }

    /*
		Questa funzione pulisce la cartella di uploads
    */
    public static function clear($dir){
        $directory = "public/uploads/$dir";

        if(is_dir($directory)) {
            $scan = scandir($directory);
            unset($scan[0], $scan[1]); //unset . and ..
            foreach($scan as $file) {
                $filename = "$directory/$file";
                $filedate = date ("d/m/Y", filemtime($filename));

                if( $filedate < date("d/m/Y") )
                    unlink($filename);
            }
        }
    }

}

?>
