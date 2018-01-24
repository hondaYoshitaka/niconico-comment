;$(function(){
	var canvas = $('#comment-chart').get(0).getContext('2d');
	var data = {
		labels : labels,
		datasets : [
			{
				fillColor : "rgba(151,187,205,0.5)",
				strokeColor : "rgba(151,187,205,1)",
				pointColor : "rgba(151,187,205,1)",
				pointStrokeColor : "#fff",
				data : counts
			}
		]
	};
	var options = {
		scaleStartValue : 0
	};
	var myDoughnut = new Chart(canvas).Line(data, options);
});
