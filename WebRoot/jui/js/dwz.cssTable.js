/**
 * Theme Plugins
 * @author ZhangHuihua@msn.com
 */
(function($){
	$.fn.extend({
		cssTable: function(options){

			return this.each(function(){
				var $this = $(this);
				var $trs = $this.find('tbody>tr');
				var $grid = $this.parent(); // table
				var nowrap = $this.hasClass("nowrap");
				
				$trs.hoverClass("hover").each(function(index){
					var $tr = $(this);
					if (!nowrap && index % 2 == 1) $tr.addClass("trbg");
					
					$tr.click(function(e){
						$trs.filter(".selected").removeClass("selected");
						$tr.addClass("selected");
						
						//add by lin
						e = window.event || e; 
						var obj = $(e.srcElement || e.target);
						if(obj.attr("name") == undefined)
						{
							//add by lujiandi start
							//$tr.find(":radio").attr("checked","checked");
							//$tr.find("input").attr("checked","checked");
							$tr.find("input[type='radio']").attr("checked","checked");
							var checkboxObj = $tr.find("input[type='checkbox']");
							if(checkboxObj){
								checkboxObj.attr("checked")=="checked"?checkboxObj.attr("checked",false):checkboxObj.attr("checked","checked");
							}
							//end
						}
						// end
						var sTarget = $tr.attr("target");
						if (sTarget) {
							if ($("#"+sTarget, $grid).size() == 0) {
								$grid.prepend('<input id="'+sTarget+'" type="hidden" />');
							}
							$("#"+sTarget, $grid).val($tr.attr("rel"));
						}
					});
					
				});

				$this.find("thead [orderField]").orderBy({
					targetType: $this.attr("targetType"),
					rel:$this.attr("rel"),
					asc: $this.attr("asc") || "asc",
					desc:  $this.attr("desc") || "desc"
				});
			});
		}
	});
})(jQuery);
