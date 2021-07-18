const StartBtn = document.getElementById("chatting__startBtn")

let ws;
let message;

const openSocket = () => {
    console.log("클릭했어?!?!?!?!");
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
        console.log("Now you write message.");
    }

    ws.onclose = (event) => {
        console.log("Chat disconnected.");
    }

}

StartBtn.addEventListener("click",openSocket)