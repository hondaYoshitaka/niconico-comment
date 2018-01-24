/* 全画面共通のjs */

var AURORA = AURORA || {};

(function($) {
	$.extend({
		getScrollTop : function() {
			return $('body').scrollTop() || $('html').scrollTop();
		},
		toStorage : function(key, obj) {
			var string = JSON.stringify(obj);
			window.localStorage.setItem(key, string);
		},
		fromStorage : function(key) {
			var string = window.localStorage.getItem(key);
			return JSON.parse(string);
		},
		removeStorageItem : function(key) {
			if(!key) {
				return;
			}
			window.localStorage.removeItem(key);
		},
		alertToMessageArea : function(msg, area) {
			var icon = $('<i>').addClass('icon-ok');
			var deleteBtn = $('<button/>').attr({
				type : 'button',
				'data-dismiss' : 'alert'
			}).addClass('close').text('×');
			var messageArea = $(area + '> .span12');
			// 表示されているAlertメッセージを消す
			messageArea.find('.close').trigger('click');

			return $('<div/>').addClass('alert').append(icon).append('　' + msg)
					.append(deleteBtn).appendTo(messageArea);
		},
		successAlert : function(msg) {
			return $.alertToMessageArea(msg, ".success-area").addClass(
					'alert-success');
		},
		errorAlert : function(msg) {
			return $.alertToMessageArea(msg, ".error-area").addClass(
					'alert-error');
		},
		infoAlert : function(msg) {
			return $.alertToMessageArea(msg, ".info-area").addClass(
					'alert-info');
		}
	});
	$.fn.extend({
		/**
		 * formの2重サブミットを防止する。
		 */
		preventDoubleSubmit : function(){
			return $(this).each(function(){
				$(this)
					.on('submit',function(e){
						var form = $(this);
						if(form.is('.lock-submit')){
							e.preventDefault();
							e.stopPropagation();
							return false;
						}
						form.trigger('lock');
						return true;
					})
					.on('lock',function(){
						var form = $(this), btns = form.find(':submit');
						form.addClass('lock-submit');
						btns.addClass('disabled')
							.attr('disabled','disabled');
					})
					.on('unlock',function(){
						var form = $(this), btns = form.find(':submit');
						form.removeClass('lock-submit');
						btns.removeClass('disabled')
							.removeAttr('disabled');
					});
			});
		},
		/**
		 * リンク付きのページャー要素を生成。
		 *
		 * @param {Object} data (paginationDtoをJSON化したもの)
		 */
		linkPagination : function(data){
			return $(this).each(function(){
				var pagination = $(this);
				pagination
					.pagination(data.currentPage, data.pageCount)
					.find('li')
					.each(function(){
						var li = $(this),
							index = li.data('index');
						if(index<0) return ;
						if(index >= data.pageCount) return;
						if(index === data.currentPage) return;
						var query = "page=" + index;
						if(data.queryParam){
							query += "&" + data.queryParam;
						}
						$('a', li).attr('href', "?" + query);
					});
			});
		},
		// command -> 'show' or 'hide'
		popupDialog: function(command){
			var dialog = $(this),
				cover = $('body').find('.rs-cover'),
				body = $('body').addClass('rs-body');

			if (cover.size() === 0) {
				cover = $('<div/>')
							.addClass('rs-cover')
							.appendTo(body)
							.on('click',function(){
								dialog.popupDialog('hide');
							});
			}
			dialog
				.addClass('rs-dialog')
				.css({
					top: $.getScrollTop() + 100,
					left: (cover.width() - dialog.width()) / 2
				});
			if(body.find('.rs-dialog').size() === 0){
				dialog.appendTo(body);
			}
			switch (command || 'show') {
				case 'show':
					cover.show();
					dialog.show();
					break;
				case 'hide':
					cover.hide();
					dialog
						.trigger('popup-dialog-did-hide')
						.hide();
					body.removeClass('rs-body');
					break;
			}
			return dialog;
		},
		/**
		 * ページャーの要素の生成
		 *
		 * @param {Object} currentPage
		 * @param {Object} pageCount
		 * @param {Object} count
		 */
		pagination : function(currentPage, pageCount, count) {
			var itemCount = 3, // ページアイテムの表示数
				tmpl = Handlebars.templates['pagination'];
			if(!tmpl){
				console.error('Handlebars Template \'pagination\' Not Found.');
				return false;
			}
			return $(this).each(
				function() {
					var pagination = $(this), ul = pagination.html('<ul/>')
							.find('ul');
					var html = (function() {
						var data = (function(index) {
							if (index > (pageCount - itemCount)) {
								index = pageCount - itemCount;
							}
							if (index < 0)
								index = 0;
							var data = [];
							function makeStyle(index) {
								var tooBig = index >= pageCount;
								if (tooBig)
									return "hidden";
								var active = index == currentPage;
								if (active)
									return "active";
							}
							for ( var i = 0; i < itemCount; i++) {
								data.push({
									index : index,
									display : (index + 1),
									"class" : makeStyle(index)
								});
								index++;
							}
							(function(prev) {
								data.unshift({
									index : prev,
									display : "<<",
									"class" : prev < 0 ? 'disabled' : ''
								});
							})(currentPage - 1);
							(function(next) {
								data.push({
									index : next,
									display : ">>",
									"class" : next < pageCount ? ''
											: 'disabled'
								});
							})(currentPage + 1);

							return data;
						})(currentPage - Math.floor(itemCount / 2));
						var html = "";
						data.forEach(function(data) {
							html += tmpl(data);
						});
						return html;
					})();
					ul.html(html).find('li').on('click', function() {
						var li = $(this);
						var active = li.hasClass('active');
						if (active)
							return;
						var disabled = li.hasClass('disabled');
						if (disabled)
							return;
						var index = li.data('index');
						li.trigger('pagination-item-select', [ index ]);
					});
			});
		},
		dragScroll : function() {
			var area = $(this).addClass('grabbable');
			area.on('mousedown', function (event) {
					if(event.which !== 1){
						return true;
					}
					// つかんだ瞬間の初期位置を記憶する
					area.addClass('grabbing')
						.data('x', event.clientX)
						.data('y', event.clientY)
						.data('scrollLeft', area.scrollLeft())
						.data('scrollTop', area.scrollTop());
					return false;
				});
			$(document)
				.on({
					'mousemove': function (event) {
						if (!area.is('.grabbing')) {
							return true;
						}
						var cursorX = area.data('scrollLeft') + area.data('x') - event.clientX,
							cursorY = area.data('scrollTop') + area.data('y') - event.clientY;
						area.scrollLeft(cursorX)
							.scrollTop(cursorY);
						return false;
					},
					'mouseup': function (event) {
						area.removeClass('grabbing');
					}
				});
			return area;
		}
	});
	$(function() {
		var body = $(document.body);
		// 2重サブミット防止
		$('form').preventDoubleSubmit();
	});
})(jQuery);