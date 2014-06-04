$(function(){
	
	//var passrate = function(year){
	
	var content = $('#content');
	var fadeEffect = function(id){
		content.html("<p><img src=\"images/load.gif\" /></p>");
		content.load("../web/pages.html #"+id);
	}
	
	var randomizeColor = function(){
		var circle = $('circle');
		$.each(circle, function(){
			var color = '#'+(Math.random()*0xFFFFFF<<0).toString(16);    
			$(this).attr('fill', color);
		});	
	}
	$('dt').click(function(){
		$('dd').slideUp('fast');
		$(this).next('dd').slideDown('fast');
	});
	
	$('a.touse').click(function(e){
		e.preventDefault();
		fadeEffect("termsofuse");
	});
	
	$('a.pstatement').click(function(e){
		e.preventDefault();
		fadeEffect("pstatement");
	});
	
	$('a.faq').click(function(e){
		e.preventDefault();
		fadeEffect("faq");
	});
	
	$('a.feedback').click(function(e){
		e.preventDefault();
		fadeEffect("feedback");
	});
	
	$('a.about').click(function(e){
		e.preventDefault();
		fadeEffect("about");
	});
	
	setTimeout(function() {
	  var circle = $('circle');
		$.each(circle, function(){
			var color = '#'+(Math.random()*0xFFFFFF<<0).toString(16);    
			$(this).attr('fill', color);
		});	
	}, 2 * 1000);
	
	$('button.submit_btn').click(function(){
		/*$.ajax({
		    type : 'POST',
		    url : 'feedback.php',
		    data : $('form.feedback').serialize(),
		});*/
		fadeEffect("thanks");		
	});

});


