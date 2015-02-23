;$(function(){
	var form = $('form.ajax-form');
	var chartArea = $('#chart-area') ,listArea = $('#comment-list-area');
	var selectSchedule = $('#select-schedule'), selectEachSecond = $('#select-each-second');
	
	var getChart = function(){
		if(!selectSchedule.val() || !selectEachSecond.val()){
			return ;
		}
		chartArea.empty();
		$.ajax({
			url: form.attr('data-chart-action'),
			data: form.serialize(),
			type: form.attr('method'),
			success: function(html){
				chartArea.append(html);
			}
		});
	};
	var getList = function(){
		if(!selectSchedule.val()){
			return ;
		}
		listArea.empty();
		$.ajax({
			url: form.attr('data-list-action'),
			data: form.serialize(),
			type: form.attr('method'),
			success: function(html){
				listArea.append(html);
			}
		});
	};
	selectSchedule.on({
		'change': function(){
			var select = $(this);
			getChart();
			getList();
			return select;
		}
	});
	selectEachSecond.on({'change': getChart});
});
