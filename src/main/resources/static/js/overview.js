$(document).ready(function(){
    var url = "http://localhost:8080/admin/hotbook";
    var html = "";
    var result = $("#recenttable");
    $.post(url,{},
        function(data){
            data.forEach(uploadsprint);
            result.html(html);
    });

    function uploadsprint(data) {
        html = html + "<tr>" +
            "                            <th>" + data.isbn + "</th>" +
            "                            <th>" + data.bookname + "</th>" +
            "                            <th>" + data.writer + "</th>" +
            "                            <th>" + data.borrowacount + "</th>" +
            "                        </tr>";
    }

    var ctx = document.getElementById("myChart").getContext('2d');
    $.post("http://localhost:8080/admin/recentborrow",{},
        function(data){
            var myLineChart = new Chart(ctx, {
                type: 'line',
                data:{
                    labels: data.datelist,
                    datasets: [{
                        label: '借阅量',
                        data: data.countlist,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255,99,132,1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options:  {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero:true
                            }
                        }]
                    }
                }
            });
        });

    var pie = document.getElementById("pie").getContext('2d');
    $.post("http://localhost:8080/admin/bookcount",{},
        function(data){
            var myLineChart = new Chart(pie, {
                type: 'pie',
                data:{
                    labels: data.typelist,
                    datasets: [{
                        data: data.totallist,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255,99,132,1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options:  {
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero:true
                            }
                        }]
                    }
                }
            });
        });
})