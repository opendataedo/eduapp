
google.load('visualization', '1', {'packages': ['corechart', 'geochart']});
var this_google = google;

 function drawStatesMap() {
 	var statesArray;
        var options = {
        	title: 'State, Unemployment Rate and Income', 
        	hAxis: {
        		title: 'Country',  
        		titleTextStyle: {color: '#0000FF'}
        		}, 
        		vAxis: {
        			title: 'Income',  
        			titleTextStyle: {
        				color: '#0000FF'
        				}
        			} 
        		};
        var dimension = "population";
        $.ajax({
          url: "http://www.json-generator.com/j/bQBftSnWRe?indent=4",
          dataType: "JSON"
        }).done(function(data) {
                var statesArray = [["Country", "Uemployment Rate", "Income"]];
                $.each(data.states, function() {
                    stateitem = [this.name, this.population, this.income];
                    statesArray.push(stateitem);
                });
          var statesData = google.visualization.arrayToDataTable(statesArray);
          var chart = new google.visualization.LineChart(document.getElementById('visual2'));
          chart.draw(statesData, options);
          
        });
}
google.setOnLoadCallback(drawStatesMap);

function drawMarkersMap(){       
        var data = google.visualization.arrayToDataTable([         
          ['Country',   'Population', 'Area Percentage'],         
          ['France',  65700000, 50],         
          ['Germany', 81890000, 27],         
          ['Poland',  38540000, 23],       
        ]);        
        var options = {         
          sizeAxis: { minValue: 0, maxValue: 100 },         
          region: '155', // Western Europe         
          displayMode: 'markers',         
          colorAxis: {colors: ['#e7711c', '#4374e0']} // orange to blue       
        }; 
              
        var chart = new google.visualization.GeoChart(document.getElementById('visual1'));       
        chart.draw(data, options);     
      };     
google.setOnLoadCallback(drawMarkersMap);



$(document).ready(function(){
	var fadeEffect = function(id){
		$('div.hide').hide();
		$('div.loading').fadeIn('fast').delay(2000).fadeOut('fast');
		$('div#'+id).delay(3000).fadeIn('fast');
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
	
	$('dt.infra').click(function(){
		fadeEffect("content");	
	});

	$('dt.location').click(function(){
		fadeEffect("cont");		
	});

});


