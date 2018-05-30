$("#logout").click(function () {
    var url = "http://localhost:8080/admin/logout";
    $.post(url,{},function(data){
        alert(data);
        window.location.href = "adminlogin.html";
    });
})