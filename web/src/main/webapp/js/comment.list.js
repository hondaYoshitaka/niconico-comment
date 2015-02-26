;$(function(){
	var form = $('form.ajax-form'), searchUrl = form.attr('action');
	var commentList = $('#comment-list-table');
	var isProcessing = false;
	var obj = {
			"base":{
				color: "white",
				speed: 8000, interval: 3000,
				font_size: 45, loop: false
			},
			"comments":[]
		};
	nicoscreen.set(obj);
	nicoscreen.start();
	
	setInterval(function(){
		// ajax通信の返答待ちの場合、終了までskip
		if(isProcessing){ return; }
		isProcessing = true;
		
		$.getJSON(searchUrl, {
				'commentId' : $('#nicoscreen').attr('data-latest-comment-id')
			})
			.done(function(json){
				if(!json){ return; }
				
				for(var key in json){
					var newComment = json[key].comment,
						newId = json[key].id,
						newDate = json[key].date;
					nicoscreen.add(newComment);
					
					var tr = $('<tr/>'), td = $('<td/>');
					tr.append(td.clone().text(newId));
					tr.append(td.clone().text(newComment));
					tr.append(td.clone().text(newDate));
					commentList.append(tr);
					
					if( !!$('#auto-scroll').prop('checked') ){
						var height = commentList.height();
						commentList.parent().animate({ scrollTop: height }, 500);
					}
					$('#nicoscreen').attr('data-latest-comment-id', json[key].id);
				}
			})
			.fail(function(){
			})
			.always(function(){
				isProcessing = false;
			});
	}, 1000);
});