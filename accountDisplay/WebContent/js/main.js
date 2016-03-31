function setHeightWidth(){
	var filterh = $('#filter').height();
	var bluenavh = $('.blue-nav').height();
	var dbluenavh = $('.darkblue-nav').height();
	var th = window.screen.availHeight-(200+filterh);	
	$("#tbody").css('height',th+'px');
	$("#tbody1").css('height',(th-100)+'px');
	$(".fa-spinner").css('margin-top',(($("html").height()/2)-100)+'px');
	
}

$(document).ready(function()
{
	$(window).resize(function()
	{
		setHeightWidth();
	});
	setHeightWidth();
});


function notifyMe(message,intype,zindex){
	if(!zindex){
		zindex=1031;
	}
	$.notify('<b>'+message+'</b>',
		  {
		  	placement: {
				from: 'top',
				align: 'right'
			},
			z_index: zindex,
			newest_on_top:true,
			type:intype
	  });
}