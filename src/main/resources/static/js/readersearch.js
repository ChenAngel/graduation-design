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
        var url = "http://localhost:8080/reader/";
        var result = $("#resulttable");
        if (input!=""){
            if (searchtype == "学生证号"){
                url += "readerid/" + input + "/fieldsearch"
            }else if (searchtype == "身份证号"){
                url += "identification/" + input + "/fieldsearch"
            }else if (searchtype == "学生名"){
                url += "readername/" + input + "/fieldsearch"
            }
            $.post(url,{},function(data){
                data.readerlist.forEach(warcforeachprint);
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
            "                            <th>" + data.id + "</th>" +
            "                            <th>" + data.readerid + "</th>" +
            "                            <th>" + data.readername + "</th>" +
            "                            <th>" + data.identification + "</th>" +
            "                            <th>" + data.readerclass + "</th>" +
            "                            <th><button type='button' class='btn btn-primary' id='" + data.id + "'>详情</button></th>" +
            "                        </tr>";
    }

    function yuyue() {
        var url = "http://localhost:8080/book/";
        $("#resulttable th button").on('click',function (e) {
            url = url + $(e.target).attr("id") + "/yuyue";
            $.post(url,{},function(data){
                if (data=="预约失败"){
                    alert(data);
                }else{
                    alert("预约成功，你的预约号是"+data);
                }
            },"text");
        });
    }


});