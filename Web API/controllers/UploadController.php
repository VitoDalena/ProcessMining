<?php

use Pure\Controller;

class UploadController extends Controller {

	/*
		Se l'upload avviene correttamente ritorna il nome del file,
		altrimenti ritorna la stringa UPLOAD:ERROR
	*/
    function upload(){

        // pulisce la cartella di uploads
        self::clear();
        
        if( empty($_FILES) == true){
        	echo "UPLOAD:ERROR";
        	return;
        }        

        $name = $_FILES["file"]["tmp_name"];
        $pieces = explode("/", $name);
        $name = $pieces[ count($pieces)-1 ];

        $filename = "public/uploads/$name.mxml";

        if( move_uploaded_file($_FILES["file"]["tmp_name"], $filename) )
        	echo $name;
    	else 
        	echo "UPLOAD:ERROR";
    }

    /*
		Questa funzione pulisce la cartella di uploads
    */
    public static function clear(){
        $directory = "public/uploads";

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
