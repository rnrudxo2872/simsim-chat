const StartBtn = document.getElementById("chatting__startBtn")

let ws;
let message;

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
        console.log("Now you write message.");
    }

    ws.onclose = (event) => {
        console.log("Chat disconnected.");
    }
}

const sendMsg = () => {
    const TEXT = 
}

StartBtn.addEventListener("click",openSocket)