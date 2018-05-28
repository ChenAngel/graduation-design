$(document).ready(function(){
    var url = "http://localhost:8080/reader/searchborrowing";
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
            "                            <th>" + data.bookname + "</th>" +
            "                            <th>" + data.addtime + "</th>" +
            "                            <th>" + data.returntime + "</th></tr>";
    }
})