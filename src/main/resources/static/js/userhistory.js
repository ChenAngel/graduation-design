$(document).ready(function(){
    var url = "http://localhost:8080/reader/borrowhistory";
    var html = "";
    $.post(url,{},
        function(data){
            data.forEach(historyprint)
        },"json");
    
    function historyprint(data) {
        html = html + "<tr>" +
            "                            <th>" + data.bookname + "</th>" +
            "                            <th>" + data.addtime + "</th>" +
            "                            <th>" + data.returntime + "</th><th>借阅中</th>";
    }
})