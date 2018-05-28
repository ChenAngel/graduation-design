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