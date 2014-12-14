/*
 * NicoScreen, version: 0.1 (2011-07-11)
 *
 *
 * For usage and examples, visit:
 * http://nicoscreen.r9game.com
 *
 * Licensed under the MIT:
 * http://www.opensource.org/licenses/mit-license.php
 *
 * Copyright (c) 2011, Shikemoku.MK (shikemoku.mk -[at]- gmail [*dot*] com)
 */

function nicoscreenobj(o) {
  var f = nicoscreenobj.f, i, len, n, prop;
  f.prototype = o;
  n = new f;
  for (i=1, len=arguments.length; i<len; ++i)
    for (var prop in arguments[i]){
    	n[prop] = arguments[i][prop];
    }
  return n;
}
nicoscreenobj.f = function(){};

var r9 = {};
r9.NicoScreen = {
	env : {
		color:"white",
		interval:500,
		speed:6500,
		font_size: 24,
		loop:true,
		
		height:"",
		width:""
	},
	top_pos: 20,
	draw_index: 0,
	comments:[],
	set:function(o){
		this.comments = o.comments;
		
		if(o.base.color){
			this.env.color = o.base.color;
		}
		this.env.loop = o.base.loop;
		
		if(!!o.base.interval){
			this.env.interval=o.base.interval;
		}
		if(!!o.base.font_size){
			this.env.font_size = o.base.font_size;
		}
		if(!!o.base.speed){
			this.env.speed=o.base.speed;
		}
	},
	start : function(){
		var elm = $("#nicoscreen");
		elm.css({
				"position": "relative",
				"border": "solid 1px gray",
				"overflow": "hidden"
			})
			.on({
				"selectstart":	function(){return false;},
				"mousedown":	function(){return false;}
			});
		this.env.width = "" + elm.width();
		this.env.height = "" + elm.height();
		
		setInterval(function(){
			nicoscreen.draw(null);
		}, this.env.interval);
	},
	draw: function(str){
		var n = nicoscreen;
		var i = n.draw_index;
		var comment_str =  "";
		
		if(str){
			comment_str = str;
			i=parseInt((new Date)/1000);
		}else{
			if (n.draw_index >= n.comments.length) {
				if(n.env.loop) n.draw_index = 0;
				return false;
			}
			comment_str = n.comments[i];
			n.draw_index++;
		}
		n.top_pos =  Math.floor( Math.random() * parseInt(n.env.height) );
		
		var end_left = (parseInt(n.env.width)) * -2;
		
		var cmid = "cm" + i + "";
		var com_obj = $("<div />")
					.attr('id', cmid)
					.css({
						'top': n.top_pos + 'px',
						'left': n.env.width + 'px',
						'position': 'absolute',
						'color': n.env.color,
						'font-size': n.env.font_size + 'px',
						'font-weight':'bold',
						'text-shadow': 'black 1px 1px 1px',
						'width':'100%',
						'cursor': 'default',
						'z-index': '99999'
					})
					.text(comment_str);
		
		$("#nicoscreen").append(com_obj);
		
		(function(that){
			var tmp_cmid = cmid;
			com_obj.animate({
					'left': end_left
				}, 
				{
					'duration': n.env.speed,
					'easing': "linear",
					'complete': function(){
						var elm_id = "#" + tmp_cmid;
						var comment = $(this);
						$("#nicoscreen").remove(elm_id);
						that.top_pos = 10;
						
						return comment.remove();
				}
			});
		})(this);
	},
	add:function(str){
		this.draw(str);
	}
};
var nicoscreen = nicoscreenobj(r9.NicoScreen);