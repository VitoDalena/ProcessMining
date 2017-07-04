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
        $FileType = pathinfo($_FILES["file"]["name"],PATHINFO_EXTENSION);
        
        $filename = "public/uploads/log/$name.$FileType";
        

        if( move_uploaded_file($_FILES["file"]["tmp_name"], $filename) )
        	echo $name;
    	else 
        	echo "UPLOAD:ERROR";
    }

    /*
        Se l'upload avviene correttamente ritorna il nome del file,
        altrimenti ritorna la stringa UPLOAD:ERROR
    */
    function constraints($name){

        // pulisce la cartella di uploads
        self::clear("constraints");
        
        if( empty($_FILES) == true){
            echo "UPLOAD:ERROR";
            return;
        }        

        $filename = "public/uploads/constraints/$name.xml";

        if( move_uploaded_file($_FILES["constraints"]["tmp_name"], $filename) )
            echo $name;
        else 
            echo "UPLOAD:ERROR";
    }
    function ontology($name){

        // pulisce la cartella di uploads
        self::clear("ontology");
        
        if( empty($_FILES) == true){
            echo "UPLOAD:ERROR";
            return;
        }        

        $filename = "public/uploads/ontology/$name.business.owl";

        if( move_uploaded_file($_FILES["ontology"]["tmp_name"], $filename) )
            echo "$name.business";
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
