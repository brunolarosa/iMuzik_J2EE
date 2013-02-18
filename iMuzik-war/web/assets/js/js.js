jQuery(document).ready(function() {

    container = $('.containerplayer');
    cover = $('.cover');
    play = $('#play');
    pause = $('#pause');
    togglemute = $('#mute');
    mutestate = false;
    muted = $('#muted');
    close = $('#close');
    //        sources = new Array();
    //        $('source').each(function(){
    //             sources.push($(this).attr("src"));
    //        });
    currentSongId = 1;
    song = new Audio();
    song.type= 'audio/mpeg';
    currentNode = document.getElementById('sourcelist').firstElementChild;
        

    song.preload = "metadata";
    song.addEventListener("loadedmetadata", function(_event) {
        //alert(song.duration);
        duration = song.duration;
    });
    next = $('#next');
    prev = $('#prev');
        
        
    

    play.click(function(e) {
        e.preventDefault();
        song.play();
        $('#seek').attr('max',song.duration);
    });

    pause.click(function(e) {
        e.preventDefault();
        song.pause();
    });

    togglemute.click(function(e) {
        e.preventDefault();
        if(mutestate){
            song.volume = 1;
            mutestate = false
            $(this).css("background-image","url(./../assets/img/unmuted.png)")
        }else{
            song.volume = 0;
            mutestate = true
            $(this).css("background-image","url(./../assets/img/muted.png)")
        }
		

    });

	
    next.click(function (e){
        suivante(e);
    });

    prev.click(function(e){
        prevsongId= currentSongId-1;
        e.preventDefault();
        //		song.src='./../resources/music/track'+prevsongId+".mp3";		
        //		currentSongId = prevsongId;
        if(currentNode.previousElementSibling){
            currentNode = currentNode.previousElementSibling;
            song.src=currentNode.src;
            song.play();
        }else{
            $("#seek").val(0);
            song.currentTime = 0
        }
    });

    $('#close').click(function(e) {
        e.preventDefault();
        container.removeClass('containerLarge');
        cover.removeClass('coverLarge');
        song.pause();
        song.currentTime = 0;
        $('#pause').replaceWith('<a class="button gradient" id="play" href="" title=""></a>');
        $('#close').fadeOut(300);
    });



    $("#seek").bind("change", function() {
        song.currentTime = $(this).val();
        $("#seek").attr("max", song.duration);

    });

    song.addEventListener('timeupdate',function (e){
        e.preventDefault();
        curtime = parseInt(song.currentTime, 10);
        $("#seek").val(curtime);
        if (song.ended){
            suivante(e);
        }
    });
	
	
});
function suivante(e) {
    e.preventDefault();
    //nextsongId= currentSongId+1;
    //		song.src='./../resources/music/track'+nextsongId+".mp3";
    //		currentSongId = nextsongId;
    if(currentNode.nextElementSibling){
        currentNode = currentNode.nextElementSibling;
        song.src= currentNode.src;
        song.play();
        $("#seek").val(0);
    }else{
        $("#seek").val(0);
        alert('Fin de la Playlist');
                    
    }
    console.log(song.src);

}
        
        
function changeMusic(id)
{ 
    var source=document.createElement("source");
    source.setAttribute('src','../musicServlet?m='+id);
    document.getElementById("sourcelist").appendChild(source);
    currentNode = document.getElementById("sourcelist").lastChild;
    song.src = currentNode.src;
    song.play();
              
}
            
function loadPlayList(listid)
{   
    console.log(listid);
    var listtab = listid.split(";");  
                
    while (document.getElementById("sourcelist").firstChild) {
        document.getElementById("sourcelist").removeChild(document.getElementById("sourcelist").firstChild);
    }
               
    for (var i = 1; i < listtab.length; i++) {
        var idlist = listtab[i];
        var source=document.createElement("source");
        source.setAttribute('src','../musicServlet?m='+idlist);
        document.getElementById("sourcelist").appendChild(source);
        currentNode = document.getElementById("sourcelist").lastChild;
    }
    currentNode = document.getElementById("sourcelist").firstElementChild;
    song.src = currentNode.src;
    song.play();
};

function addToPlayList(listid)
{   
    console.log(listid);
    var listtab = listid.split(";");  
    for (var i = 1; i < listtab.length; i++) {
        var idlist = listtab[i];
        var source=document.createElement("source");
        source.setAttribute('src','../musicServlet?m='+idlist);
        document.getElementById("sourcelist").appendChild(source);
        currentNode = document.getElementById("sourcelist").lastChild;
    }
    currentNode = document.getElementById("sourcelist").firstElementChild;
             
};
