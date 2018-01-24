<%@page pageEncoding="UTF-8"%>
<script type="text/javascript">
	var counts = ${countArrayString};
	var labels = ${labelArrayString}; 
</script>
<script type="text/javascript" src="${f:url('/js/comment.chart.js')}"></script>
<canvas id="comment-chart" width="900px" height="200px"></canvas>