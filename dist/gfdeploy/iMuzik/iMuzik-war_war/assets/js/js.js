jQuery(document).ready(function() {

	container = $('.containerplayer');
	cover = $('.cover');
	play = $('#play');
	pause = $('#pause');
	togglemute = $('#mute');
        mutestate = false;
	muted = $('#muted');
	close = $('#close');
	currentSongId = 1;
	song = new Audio();
    song.type= 'audio/mpeg';
    song.src= '../musicServlet?m=2';
    song.load();
    duration = song.duration;
    next = $('#next');
    prev = $('#prev');

	play.click(function(e) {
		e.preventDefault();
		song.play();

		//$(this).replaceWith('<a class="button gradient" id="pause" href="" title=""></a>');
		//container.addClass('containerLarge');
		//cover.addClass('coverLarge');
		//$('#close').fadeIn(300);
		$('#seek').attr('max',song.duration);
	});

	pause.click(function(e) {
		e.preventDefault();
		song.pause();
		//$(this).replaceWith('<a class="button gradient" id="play" href="" title=""></a>');

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

	muted.click(function(e) {
		e.preventDefault();
		song.volume+=1;
		$(this).replaceWith('<a class="button gradient" id="mute" href="" title=""></a>');
	});

	next.click(function (e){
		suivante(e);
	});

	prev.click(function(e){
		prevsongId= currentSongId-1;
		e.preventDefault();
           
		song.src='./../assets/music/track'+prevsongId+".mp3";		
		currentSongId = prevsongId;	
		song.play();
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
		curtime = parseInt(song.currentTime, 10);
		$("#seek").val(curtime);
		if (song.ended){
		suivante(e);
		}
	});
	
	
});
function suivante(e) {
		e.preventDefault();
		nextsongId= currentSongId+1;
		song.src='./../assets/music/track'+nextsongId+".mp3";
		currentSongId = nextsongId;		
		song.play();
		$("#seek").val(0);


	}
