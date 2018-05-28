$(document).ready(function(){
    var url = "http://localhost:8080/reader/searchorders";
    var html = "";
    var recenttable = $("#recenttable");
    $.post(url,{},
        function(data){
            data.forEach(historyprint);
            recenttable.html(html);
        },"json");

    function historyprint(data) {
        html = html + "<tr>" +
            "                            <th>" + data.id + "</th>" +
            "                            <th>" + data.order_uuid + "</th>" +
            "                            <th>" + data.bookname + "</th>";
        if (data.status=="open"){
            html += "<th>预约完成，请速取</th></tr>";
        }else if (data.status=="ok"){
            html += "<th>借阅成功</th></tr>";
        }
    }
})