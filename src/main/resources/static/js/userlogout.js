$("#logout").click(function () {
    var url = "http://localhost:8080/reader/logout";
    $.post(url,{},function(data){
        alert(data);
        window.location.href = "userlogin.html";
    });
})