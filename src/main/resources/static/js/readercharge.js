$("#querenxiugai").click(function () {
    var url = "http://localhost:8080/reader/changeinfo";
    var readerid = $("#readerid").val();
    var password = $("#password").val();
    var readername = $("#readername").val();
    var readerclass = $("#readerclass").val();
    var tel = $("#tel").val();
    var sex = $("#sex").val();
    var birthday = $("#birthday").val();
    var identification = $("#identification").val();
    var id = $("#hidval").val();
    $.post(url,{
        readerid:readerid,
        password:password,
        readername:readername,
        readerclass:readerclass,
        tel:tel,
        sex:sex,
        birthday:birthday,
        identification:identification,
        id:id
    },function(data){
        alert(data);
    });

})