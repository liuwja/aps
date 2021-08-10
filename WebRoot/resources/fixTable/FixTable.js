(function($){  
    $.fn.fixTable = function(options){ 
		var defaults = {  
			fixRow:0,
			fixColumn: 0,  
			width:0,
			height:0  
		};    
	    var opts = $.extend(defaults, options); 
			var _this = $(this);
			var _clone = _this.clone();
			var _columnClone = _this.clone();
			var _columnDataClone = _this.clone();
			_this.wrap(function() {
               return $("<div id='_fixTableMain'></div>", navTab.getCurrentPanel());
            });
			$("#_fixTableMain", navTab.getCurrentPanel()).css({
				"width":defaults["width"],
				"height":defaults["height"],
				"overflow":"scroll",
				"position":"relative"
			});
			$("#_fixTableMain", navTab.getCurrentPanel()).wrap(function() {
               return $("<div id='_fixTableBody'></div>", navTab.getCurrentPanel());
            });
			$("#_fixTableBody", navTab.getCurrentPanel()).css({
				"background-color":"#fff",
				"width":defaults["width"],
				"height":defaults["height"],
				"overflow":"hidden",
				"position":"relative"
			});
			$("#_fixTableBody", navTab.getCurrentPanel()).append("<div id='_fixTableHeader'></div>");
			$(_clone).height($(_clone).find("thead").height());
			$("#_fixTableHeader", navTab.getCurrentPanel()).append(_clone);
			$("#_fixTableHeader", navTab.getCurrentPanel()).css({
				"background-color":"#cae8ea",
				"overflow":"hidden",
				"width":defaults["width"]-17,
				"height":_clone.find("thead").find("tr").height()*defaults["fixRow"]+1,
				"position":"absolute",
				"z-index":"498",
				"top":"0"
			});
			
			$("#_fixTableBody", navTab.getCurrentPanel()).append("<div id='_fixTableColumn'></div>");
			
			var _fixColumnNum = defaults["fixColumn"];
			var _fixColumnWidth = 0;
			$($(_this).find("thead").find("tr").find("th")).each(function(index, element) {
               	if((index+1)<=_fixColumnNum){
					_fixColumnWidth += $(this).width()+4;
				}
            });
			
			$("#_fixTableColumn", navTab.getCurrentPanel()).css({
				"overflow":"hidden",
				"width":_fixColumnWidth,
				"height":defaults["height"]-17,
				"position":"absolute",
				"z-index":"898",
				"top":"0",
				"left":"0"
			});
			
			
			$("#_fixTableColumn", navTab.getCurrentPanel()).append("<div id='_fixTableColumnHeader'></div>");
			$("#_fixTableColumnHeader", navTab.getCurrentPanel()).css({
				"background-color":"#cae8ea",
				"width":$("#_fixTableColumn").width(),
				"height":_this.find("thead").find("tr").height()*defaults["fixRow"]+1,
				"overflow":"hidden",
				"position":"absolute",
				"z-index":"900",
			});
			$("#_fixTableColumnHeader", navTab.getCurrentPanel()).append(_columnClone);
			
			$("#_fixTableColumn", navTab.getCurrentPanel()).append("<div id='_fixTableColumnBody'></div>");
			$("#_fixTableColumnBody", navTab.getCurrentPanel()).css({
				"background-color":"#fff",
				"width":$("#_fixTableColumn").width()-4,
				"height":defaults["height"]-17,
				"overflow":"hidden",
				"position":"absolute",
				"z-index":"898",
				"top":"0"
			});
			$("#_fixTableColumnBody", navTab.getCurrentPanel()).append(_columnDataClone);
			$("#_fixTableMain", navTab.getCurrentPanel()).scroll(function(e) {
                $("#_fixTableHeader", navTab.getCurrentPanel()).scrollLeft($(this).scrollLeft());
				$("#_fixTableColumnBody", navTab.getCurrentPanel()).scrollTop($(this).scrollTop());
            });
		};
    
})(jQuery); 