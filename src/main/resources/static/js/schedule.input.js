;$(function(){
	$('.datepicker').each(function(){
		return $(this).datepicker(
			{ dateFormat: "yy/mm/dd" }
		);
	});
	$('.timepicker').each(function(){
		return $(this).timepicker(
			{ 'timeFormat': 'H:i' }
		);
	});
});