var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    // $("#greetings").html("");
}

// 该connect()函数使用SockJS和stomp.js打开到“/endpoint-websocket”的连接，这是我们的SockJS服务器等待连接的地方。
// 在成功连接后，客户端订阅“/chat/single/{from}”目的地，服务器将在该目的地发布问候消息。
// 当在该目的地上收到问候语时，它会将一个段落元素附加到DOM以显示问候语消息。
function connect() {
    var from = $("#from").val();
    var socket = new SockJS('/endpoint-websocket');//连接端点(基站)
    stompClient = Stomp.over(socket);//用Stomp包装,规范协议
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        //点对点推送接口
        stompClient.subscribe('/chat/single/' + from, function (result) {
            console.log(result);
            if ($("#to").val() == JSON.parse(result.body).from) {
                showContent(JSON.parse(result.body));
            } else {
                var count = $("#" + JSON.parse(result.body).from + " .count button").text();
                $("#" + JSON.parse(result.body).from + " .count button").text(++count);
            }
        });
        // 推送接口
        // stompClient.subscribe('/topic/chat', function (result) {
        //     console.log(result);
        //     showContent(JSON.parse(result.body));
        // });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

// 该sendName()函数检索用户输入的名称，并使用STOMP客户端将其发送到“/app/v6/chat”
// 目的地（V6UserChatController.topicChat()将在何处接收）
function sendName() {
    if ("" != $("#to").val()) {
        //当前用户消息显示
        showContent({fromName: $("#username").val(), content: $("#content").val(), time: new Date()});
        //接收用户消息显示
        stompClient.send("/app/chat", {}, JSON.stringify({
            'from': $("#from").val(),
            'to': $("#to").val(),
            'content': $("#content").val()
        }));
        $("#content").val("");
    }
}

function showContent(body) {
    $("#greetings").append("<tr><td>" + body.fromName + "</td><td>" + body.content + "</td><td>" + new Date(body.time).toLocaleString() + "</td></tr>");
}

function showMsg(body) {
    $("#greetings").append("<tr><td>" + body.uUsername + "</td><td>" + body.mContent + "</td><td>" + new Date(body.mTime).toLocaleString() + "</td></tr>");
}

function showUnread(body) {
    $("#user").html("<tr><td>" + body.content + "</td><td>" + new Date(body.time).toLocaleString() + "</td></tr>");
}

$(function () {
    connect();

    $("form").on('submit', function (e) {
        e.preventDefault();//阻止默认的事件发生
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });
    $(".list").click(function () {
        $("#toName").val($(this).text());
        $("#to").val($(this).val());
        getMsg();
    })
});

function getMsg() {
    $.ajax({
        type: "POST",//提交类型
        dataType: "json",//预期服务器返回的数据类型
        url: "/getMsgexList",//url
        data: {
            mFrom: $("#to").val(),
            mTo: $("#from").val()
        },
        success: function (data) {
            console.log(data);//打印服务端返回的数据(调试用)
            $("#greetings").html("");
            for (var i = 0; i < data.length; i++) {
                showMsg(data[i]);
            }
        }
    });
    $("#" + $('#to').val() + " .count").html("<button disabled=\"disabled\" class=\"btn btn-default list form-control\" type=\"button\">0</button>");
}