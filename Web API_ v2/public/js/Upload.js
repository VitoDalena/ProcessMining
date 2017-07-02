var perc,percent;
var Upload = function (file) {
    this.file = file;
};

Upload.prototype.getType = function() {
    return this.file.type;
};
Upload.prototype.getSize = function() {
    return this.file.size;
};
Upload.prototype.getName = function() {
    return this.file.name;
};
Upload.prototype.doUpload = function (e) {
    var that = this;
    var formData = new FormData();

    // add assoc key values, this will be posts values
    formData.append('file', this.file, this.getName());
    formData.append('upload_file', true);

    $.ajax({
        type: 'POST', //RICHIESTA DI TIPO POST
        url: 'log', //RICHIAMA LA ROTTA LOG DEFINITA IN INDEX.PHP
        xhr: function () { // GESTIONE DELLA BARRA DELL'AVANZAMENTO DELL'UPLOAD
            var myXhr = $.ajaxSettings.xhr();
            if (myXhr.upload) {
                myXhr.upload.addEventListener('progress', that.progressHandling, false);
            }
            return myXhr;
        },
        success: function (e) { //SE LA POST VA A BUON FINE
                //SE L'UPLOAD NON è ANDATO A BUON FINE 
                if( e.indexOf("UPLOAD:ERROR")!=-1 ){
                    //alert("Upload fallito!");
                    that.reset();
                    if( btn_process.className.includes("invisible") == false )
                        btn_process.className += ' invisible';
                    return;
                }
                //UPLOAD ANDATO A BUON FINE
                console.log( "Upload riuscito!");
                btn_process.className = btn_process.className.replace('invisible', '');
                logFilename = e; //NOME DEL FILE CARICATO SUL SERVER
        },
        async: true, //RICHIESTA ASINCRONA
        data: formData,
        cache: false,
        contentType: false,
        processData: false, //NON PROCESSARE
        timeout: 60000
    });
};

Upload.prototype.progressHandling = function (event) {
    //var percent = 0;
    var position = event.loaded || event.position;
    var total = event.total;
    var progress_bar_id = "#progress-wrp";
    if (event.lengthComputable) {
        perc=percent;
        percent = Math.ceil(position / total * 100);
        
    }
    // update progressbars classes so it fits your code
    $(progress_bar_id + " .progress-bar").css("width", +percent + "%");
    $(progress_bar_id + " .status").text(percent + "%");
};

Upload.prototype.reset = function () {
    
    var progress_bar_id = "#progress-wrp";
    $(progress_bar_id + " .progress-bar").css("width",+perc+ "%");
    $(progress_bar_id + " .progress-bar").css("background-color", "red");
    $(progress_bar_id + " .status").text(perc + "% - UPLOAD FALLITO");
};