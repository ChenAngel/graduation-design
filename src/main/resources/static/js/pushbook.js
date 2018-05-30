$(document).ready(function(){
    var url = "http://localhost:8080/book/push";
    var result = $("#pushresult");
    $.post(url,{},
        function(data){
            data.forEach(printpush);
        });

    function printpush(data) {
        var html = "<tr>" +
            "<th>" + data.id + "</th>" +
            "<th>" + data.bookname + "</th>" +
            "<th>" + data.type + "</th>" +
            "<th>" + data.nowaccount + "</th>" +
            "</tr>";
        result.append(html);
    }
})