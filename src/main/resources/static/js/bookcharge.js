$("#addbook").click(function () {
    var url = "http://localhost:8080/book/addbook";
    var isbn = $("#isbn").val();
    var bookname = $("#bookname").val();
    var type = $("#type").val();
    var writer = $("#writer").val();
    var press = $("#press").val();
    var pressdate = $("#pressdate").val();
    var remark = $("#remark").val();
    var nowaccount = $("#nowaccount").val();
    var borrowacount = $("#borrowacount").val();
    var location = $("#location").val();
    var price = $("#price").val();
    var totalaccount = $("#totalaccount").val();
    $.post(url,{
            isbn:isbn,
            bookname:bookname,
            type:type,
            writer:writer,
            press:press,
            pressdate:pressdate,
            remark:remark,
            nowaccount:nowaccount,
            borrowacount:borrowacount,
            location:location,
            price:price,
            totalaccount:totalaccount
        },
        function(data){
            alert(data);
            if (data=="添加成功"){
                window.location.href="back_bookcharge.html";
            }
        },"text");
})

$("#querenxiugai").click(function () {
    var xiugai = "http://localhost:8080/book/changeinfo";
    var id = $("#hidval").val();
    var isbn1 = $("#isbn1");
    var bookname1 = $("#bookname1");
    var type1 = $("#type1");
    var writer1 = $("#writer1");
    var press1 = $("#press1");
    var pressdate1 = $("#pressdate1");
    var remark1 = $("#remark1");
    var nowaccount1 = $("#nowaccount1");
    var borrowacount1 = $("#borrowacount1");
    var location1 = $("#location1");
    var price1 = $("#price1");
    var totalaccount1 = $("#totalaccount1");
    $.post(xiugai,{
        isbn:isbn1.val(),
        bookname:bookname1.val(),
        type:type1.val(),
        writer:writer1.val(),
        press:press1.val(),
        pressdate:pressdate1.val(),
        remark:remark1.val(),
        nowaccount:nowaccount1.val(),
        borrowacount:borrowacount1.val(),
        location:location1.val(),
        price:price1.val(),
        totalaccount:totalaccount1.val(),
        id:id
    },function(data){
        alert(data);
        window.location.href = "back_bookcharge.html";
    },"text");
})

$("#querenborrow").click(function () {
    var xiugai = "http://localhost:8080/book/jieyue";
    var readerid2 = $("#readerid2").val();
    var bid = $("#hidval").val();
    $.post(xiugai,{
        bid:bid,
        readerid:readerid2
    },function(data){
        alert(data);
        window.location.href = "back_bookcharge.html";
    },"text");
})