<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Simsim | Chat</title>
</head>

<body>
    <div>
        <button type="button" id="chatting__startBtn">채팅방 참여</button>
        <button type="button" id="chatting__endBtn">채팅방 나가기</button>
        <div>
            <input type="text" name="sender" id="chatting__sender">
            <input type="text" name="msg" id="chatting__msg">
            <button type="button" id="chatting__sendBtn">메세지 전송</button>
            <button type="button" id="chatting__clearBtn">대화내용 지우기</button>
        </div>
    </div>
    <div id="messagesContainer"></div>

    <script type="module" src="/publicRes/js/chatting.js"></script>
</body>

</html>