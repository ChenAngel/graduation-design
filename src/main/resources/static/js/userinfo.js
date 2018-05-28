$(document).ready(function(){
    var url = "http://localhost:8080/reader/myinfo";
    var html = "";
    var recenttable = $("#myinfo");
    $.post(url,{},
        function(data){
            historyprint(data);
            recenttable.html(html);
        },"json");

    function historyprint(data) {
        html = html + "<tr>" +
            "                            <th>ID：" + data.id + "</th>" +
            "                            <th>学生证号：" + data.readerid + "</th>" +
            "                            <th>密码：" + data.password + "</th>" +
            "                            <th>真实姓名：" + data.readername + "</th>" +
            "</tr>" +
            "<tr>" +
            "                            <th>所属班级：" + data.readerclass + "</th>" +
            "                            <th>性别：" + data.sex + "</th>" +
            "                            <th>出生日期：" + data.birthday + "</th>" +
            "                            <th>身份证号：" + data.identification + "</th>" +
            "</tr>" +
            "<tr>" +
            "                            <th>联系电话：" + data.tel + "</th>" +
            "</tr>"
    }
})