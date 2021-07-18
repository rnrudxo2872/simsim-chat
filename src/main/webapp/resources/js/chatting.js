const StartBtn = document.getElementById("chatting__startBtn")
const MessagesContainer = document.getElementById("messagesContainer");
const CurSender = document.getElementById("chatting__sender");
const CurMsg = document.getElementById("chatting__msg");
const SendBtn = document.getElementById("chatting__sendBtn");
const ClearBtn = document.getElementById("chatting__clearBtn");

let ws;

const openSocket = () => {
    console.log("Now you click open socket button.");
    if(ws !== undefined && ws.readyState !== WebSocket.CLOSED){
        console.log("already exists WebSocket");
        return;
    }

    ws = new WebSocket("ws://localhost:8088/SimChat")
    ws.onopen = (event) => {
        if(event.data === undefined){
            return ;
        }
        console.log("connect Socket!");
    }

    ws.onmessage = (event) => {
        console.log(`Now you write message.${event.data}`);
    }

    ws.onclose = (event) => {
        console.log("Chat disconnected.");
    }
}

const sendMsg = () => {
    if(ws === undefined && ws.readyState === WebSocket.CLOSED){
        console.warn("잘못된 접근!");
        return;
    }
    const TEXT = `${CurSender.value},${CurMsg.value}`
    ws.send(TEXT)
    CurMsg.value = "";
}

StartBtn.addEventListener("click",openSocket);
SendBtn.addEventListener("click",sendMsg);