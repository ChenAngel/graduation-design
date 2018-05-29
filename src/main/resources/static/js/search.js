$(document).ready(function(){
    var html = "";
    var total = document.getElementById("total");
    $("#a0").click(function(){
        var a0 = $("#a0").text();
        $("#searchtype").text(a0);
    });
    $("#a1").click(function(){
        var a1 = $("#a1").text();
        $("#searchtype").text(a1);
    });
    $("#a4").click(function(){
        var a4 = $("#a4").text();
        $("#searchtype").text(a4);
    });
    $("#a5").click(function(){
        var a5 = $("#a5").text();
        $("#searchtype").text(a5);
    });
    $("#a6").click(function(){
        var a6 = $("#a6").text();
        $("#searchtype").text(a6);
    });
    $("#a2").click(function(){
        var a2 = $("#a2").text();
        $("#searchtype").text(a2);
    });
    $("#a3").click(function(){
        var a3 = $("#a3").text();
        $("#searchtype").text(a3);
    });

    $("#go").click(function(){
        html = "";
        var searchtype = $("#searchtype").text();
        var input = $("#in").val();
        var url = "http://localhost:8080/book/";
        var result = $("#resulttable");
        if (input!=""){
            if (searchtype == "书本名"){
                url += "bookname/" + input + "/fieldsearch"
            }else if (searchtype == "ISBN"){
                url += "isbn/" + input + "/fieldsearch"
            }else if (searchtype == "出版社"){
                url += "press/" + input + "/fieldsearch"
            }else if (searchtype == "类别"){
                url += "type/" + input + "/fieldsearch"
            }else if (searchtype == "作者"){
                url += "writer/" + input + "/fieldsearch"
            }
            $.post(url,{},function(data){
                data.booklist.forEach(warcforeachprint);
                total.innerText = "共" + data.size + "条结果";
                result.html(html);
                yuyue();
            });
        }

    });
    function warcforeachprint(data) {
        var input = $("#in").val();
        if (input==""||input=="undefine"){
            input = "";
        }
        html = html + "<tr>" +
            "                            <th>" + data.isbn + "</th>" +
            "                            <th>" + data.bookname + "</th>" +
            "                            <th>" + data.writer + "</th>" +
            "                            <th>" + data.press + "</th>" +
            "                            <th>" + data.location + "</th>" +
            "                            <th>" + data.nowaccount + "</th>" +
            "                            <th>" + data.totalaccount + "</th>" +
            "                            <th><button type='button' class='btn btn-primary' id='" + data.id + "jieyue' data-toggle='modal' data-target='#borrow'>借阅</button>" +
            "                            <button type='button' class='btn btn-primary' id='" + data.id + "guihuan'>归还</button>" +
            "                            <button type='button' class='btn btn-primary' id='" + data.id + "xiugai' data-toggle='modal' data-target='#changeinfo'>修改</button>" +
            "                            <button type='button' class='btn btn-primary' id='" + data.id + "shanchu'>删除</button></th>" +
            "                        </tr>";
    }

    function yuyue() {
        var url = "http://localhost:8080/book/";
        $("#resulttable th button").on('click',function (e) {
            var id = $(e.target).attr("id");
            var value = id.replace(/[^0-9]/ig,"");
            if (id.indexOf("jieyue")!=-1){
                $("#hidval").val(value);
            }else if (id.indexOf("guihuan")!=-1){
                var guihuan = url + value + "/guihuan";
                $.post(guihuan,{},function(data){
                    alert(data);
                },"text");
            }else if(id.indexOf("xiugai")!=-1){
                var xiugai = url + value + "/searchbyid";
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
                $.post(xiugai,{},
                    function(data){
                        $("#isbn1").val(data.isbn);
                        $("#bookname1").val(data.bookname);
                        $("#type1").val(data.type);
                        $("#writer1").val(data.writer);
                        $("#press1").val(data.press);
                        $("#pressdate1").val(data.pressdate);
                        $("#remark1").val(data.remark);
                        $("#nowaccount1").val(data.nowaccount);
                        $("#borrowacount1").val(data.borrowacount);
                        $("#location1").val(data.location);
                        $("#price1").val(data.price);
                        $("#totalaccount1").val(data.totalaccount);
                        $("#hidval").val(value);
                });
            }else if (id.indexOf("shanchu"!=-1)){
                var shanchu = url + value + "/delbyid";
                $.post(shanchu,{},function(data){
                    alert(data);
                },"text");
            }


        });
    }


});