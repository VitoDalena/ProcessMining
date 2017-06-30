<?php

use Pure\Controller;

class Cnet2ADController extends Controller {

    function process($name){

    	// pulisci la cartella di output
    	self::clear();

    	$log = "public/uploads/log/$name.mxml";
        
        $cmd = "java -jar Cnet2ADweb.jar -json $log -dir bin -o $name";
        $cmd .= " -sigma $_POST[sigma] -ff $_POST[ff] -rtb $_POST[rtb]";
        $cmd .= " -ontology bin/$name.owl";
        $cmd .= " -resources $_POST[level]";

        if( empty($_POST['constraints']) == false )
            $cmd .= " -constraints public/uploads/constraints/$_POST[constraints].xml";
//var_dump($cmd);
        $output = shell_exec($cmd);
		if (strpos($output, 'Cnet2ADRESULT=ERROR') !== false) {
			echo "Cnet2ADRESULT:ERROR";
			return;
		}		
		echo file_get_contents("bin/$name.uml");

    }

    function visualize($name){
    	$filename = "bin/$name.json";

    	if(file_exists($filename)){
    		echo ( file_get_contents($filename) );
    	}
    	else echo "Visualize:ERROR";
    }

    /*
		Questa funzione pulisce la cartella di output
    */
    public static function clear(){
        $directory = "bin";

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
