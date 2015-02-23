;$(function(){
	var body = $(document.body);
	var form = $('form.ajax-form'),
		input = form.find('input[name="comment"]');
	
	body.find('.btn[data-role="comment-trigger"]')
		.on({
			'click': function(){
				if(input.val() === ''){
					return;
				}
				$.ajax({
					url: form.attr('action'),
					type: 'POST',
					data: form.serialize(),
					success: function(data){
						input.val('');
						return;
					},
					error: function(xhr, status, thrown){
						switch(xhr.status){
						case 401:
							location.href = url.loginUrl;
							break;
						default: 
							alert('発言に失敗したお(´；ω；`)');
							break;
						}
					},
					complete: function(){
						return;
					}
				});
			}
		});
	/** enterキーを押してもsubmitされない */
	form.find(':input').on('keypress', function(e){
		if(e.keyCode === 13) return false;
	});
	/** スタンプエリアを出現させる */
	var stampListToggle = $('.stamp-list-toggle').on('click', function(){
		$('#comment-stamp-area').animate({
			'height':'toggle',
			'opacity':'toggle'
		}, 'fast');
		return $(this);
	});
	/** コメント欄にプリセットのコメントをつける */
	$('.btn-auto-comment').on('click', function(){
		var btn = $(this);
		var comment = btn.attr('data-comment');
		input.val(input.val() + comment);
		
		stampListToggle.trigger('click');
		return btn;
	});
});