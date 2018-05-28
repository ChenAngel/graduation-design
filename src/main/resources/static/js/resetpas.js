$("#queren").click(function () {
    var password1 = $("#password1");
    var password2 = $("#password2");
    var url = "http://localhost:8080/reader/" + password1.val() + "/" + password2.val() + "/resetps";
    $.post(url,{},
        function(data){
            alert(data);
            if (data=="修改成功"){
                password2.val("");
                password1.val("");
            }
        },"text");
})