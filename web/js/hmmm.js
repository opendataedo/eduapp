google.load('visualization', '1', {
    'packages': ['corechart', 'geochart']
});

function drawStatesMap() {
    var lgaArray;
    var options = {
        sizeAxis: { minValue: 0, maxValue: 100 },
        region: 'NG', 
        displayMode: 'markers',
        colorAxis: {colors: ['#e7711c', '#4374e0']}, // orange to blue
        magnifyingGlass: {enable: true, zoomFactor: 100}
      };

    $.ajax({
        url: "http://www.json-generator.com/j/csFVuBztSG?indent=4",
        dataType: "JSON"
    }).done(function (data) {
        var lgaArray = [
            ["latitude", "longitude", "LGA", "value", "Teacher Pupils Ratio"]
        ];
        $.each(data, function () {
            rows = [this.latitude, this.longitude, this.lgaName, 0, this.teachersToPupilsRatio];
            lgaArray.push(rows);
        });
        var lgaData = google.visualization.arrayToDataTable(lgaArray);
        var chart = new google.visualization.GeoChart(document.getElementById('visual2'));
        chart.draw(lgaData, options);

    });
}
google.setOnLoadCallback(drawStatesMap);




















$(function () {
    var lgaArray = [];
    var unesco = [];

    $.ajax({
        url: "http://www.json-generator.com/j/csFVuBztSG?indent=4",
        dataType: "JSON"
    }).done(function (data) {
        $.each(data, function () {
            row = [this.schoolName, 50];
            rows = [this.schoolName, this.teachersToPupilsRatio, this.teachersToPupilsRatio];
            lgaArray.push(rows);
            unesco.push(row);
        });
        alert(unesco.join(" "));

        $('#container').highcharts({

            chart: {
                zoomType: 'xy'
            },
            xAxis: {
                //categories: unesco
            },
            yAxis: [{ // Primary yAxis
                gridLineWidth: 0,
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[2]
                    }
                },
                title: {
                    text: 'Teacher Pupils Ratio',
                    style: {
                        color: Highcharts.getOptions().colors[2]
                    }
                },
                opposite: true

            }, { // Secondary yAxis
                gridLineWidth: 0,
                title: {
                    text: 'UNESCO Standard',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                },
                labels: {
                    format: '{value}',
                    style: {
                        color: Highcharts.getOptions().colors[0]
                    }
                }

            }],

            title: {
                text: 'Teacher Pupils Ratio Compared to UNESCO Standard'
            },

            series: [{
                name: 'UNESCO Standard',
                type: 'spline',
                data: unesco
            },
                     {
                name: 'Teachers Pupils Ratio',
                type: 'bubble',                
                data: lgaArray,
                tooltip: {
                    valueSuffix: ' mm'
                }

            }, ]

        });
    });



});