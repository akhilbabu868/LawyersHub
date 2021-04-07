/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

getUserRegistrationCount();
getLawyerRegistrationCount();
  
});



	
        
 function getUserRegistrationCount()
 {
     
    var xlables = [];
    var ytemps = [];
    $.post("getUserWiseChart.do", function (result, status) {
        for (var i = 0; i < result.data.length; i++) {
            xlables.push(result.data[i].createddate);
            ytemps.push(result.data[i].usercount);




            console.log("ytemps ____________" + ytemps[i]);
            console.log("xlables ____________" + xlables);



            var ctx = document.getElementById('canvas').getContext('2d');
            window.myLine = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: xlables,
                    datasets: [{
                            label: 'UserCount',
                            backgroundColor: window.chartColors.red,
                            borderColor: window.chartColors.red,
                            data:ytemps ,
                            fill: true,
                        }]
                },
                options: {
                    responsive: true,
                    title: {
                        display: true,
                        text: 'User Registration Count Line Chart'
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: false,
                    },
                    hover: {
                        mode: 'nearest',
                        intersect: true
                    },
                    scales: {
                        xAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Date'
                                }
                            }],
                        yAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: true,
                                    labelString: 'UserRegistrationCount'
                                }
                            }]
                    }
                }
            });
        }
    });  
     
 }
 
 
 
        
 function getLawyerRegistrationCount()
 {
     
    var xlables = [];
    var ytemps = [];
    $.post("getLawyerWiseChart.do", function (result, status) {
        for (var i = 0; i < result.data.length; i++) {
            xlables.push(result.data[i].createddate);
            ytemps.push(result.data[i].lawyercount);

            var ctx = document.getElementById('canvasLawyer').getContext('2d');
            window.myLine = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: xlables,
                    datasets: [{
                            label: 'LawyerCount',
                            backgroundColor: window.chartColors.blue,
                            borderColor: window.chartColors.blue,
                            data: ytemps,
                            fill: true,
                        }]
                },
                options: {
                    responsive: true,
                    title: {
                        display: true,
                        text: 'Lawyer Registration Count Line Chart'
                    },
                    tooltips: {
                        mode: 'index',
                        intersect: false,
                    },
                    hover: {
                        mode: 'nearest',
                        intersect: true
                    },
                    scales: {
                        xAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: true,
                                    labelString: 'Date'
                                }
                            }],
                        yAxes: [{
                                display: true,
                                scaleLabel: {
                                    display: true,
                                    labelString: 'LawyerRegistrationCount'
                                }
                            }]
                    }
                }
            });
        }
    });  
     
 }