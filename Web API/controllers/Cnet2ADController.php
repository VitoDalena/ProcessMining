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
        if( $_POST['annotate_resources'] == 'true' )
            $cmd .= " -resources";

        if( empty($_POST['constraints']) == false )
            $cmd .= " -constraints public/uploads/constraints/$_POST[constraints].xml";

        $output = shell_exec($cmd);
		if (strpos($output, 'Cnet2ADRESULT=ERROR') !== false) {
			echo "Cnet2ADRESULT:ERROR";
			return;
		}		
		echo file_get_contents("bin/$name.uml");

    }

    function verify(){

    	// pulisci la cartella di output
    	self::clear();
        unlink("flink-1.3.2/log/report.txt");

        $name_log = $_POST['log_file'];
    	$log = "public/uploads/log/$name_log.mxml";
        $name_mod = $_POST['mod_file'];
    	$mod = "public/uploads/mod/$name_mod.xmi";
        $name_ont = $_POST['ont_file'];
    	$ont = "public/uploads/ont/$name_ont.owl";
        
        $cmd = "flink-1.3.2\\bin\\flink run -c com.pmverification.StreamingJob pmverification-0.9.jar \"$mod\" \"$log\" \"$ont\"";
        $output = shell_exec($cmd);
        $myfile = fopen("flink-1.3.2/log/report.txt", "r") or die("ERROR");	
        echo fread($myfile,filesize("flink-1.3.2/log/report.txt"));
        fclose($myfile);
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
