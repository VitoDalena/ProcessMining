<?php

namespace App\Http\Controllers;
use Illuminate\Http\Request;

class UploadController extends Controller
{
    private static $path = 'uploads';
    private $filename;

    /**
     * Create a new controller instance.
     *
     * @return void
     */
    public function __construct()
    {
        //
    }

    //
    public function upload(Request $request){
        $file = $request->file('file');
        $this->filename = self::$path . '/' . $file->getClientOriginalName();
        $file->move( self::$path, $file->getClientOriginalName() );

        echo $this->filename;
        // Process file
    }

    public function __destruct(){
        // Delete file
        if( isset( $this->filename ) && file_exists( $this->filename ) )
            unlink( $this->filename );
    }
}
