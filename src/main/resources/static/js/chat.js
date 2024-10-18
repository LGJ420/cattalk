// new EventSource()는 Server-Sent Events (SSE)를 활용하여
// 서버로부터 실시간 데이터를 수신하기 위한 객체를 생성하는 데 사용됨
const eventSource = new EventSource("/api/chat?sender=" + senderNickname + "&receiver=" + receiverNickname);

// 수신받은 데이터의 처리
eventSource.onmessage = (e) => {

    let data = JSON.parse(e.data);

    const time = getFormatTime((data.createdAt));


    if (data.sender === senderNickname) {
        addMsg(data.msg, time, senderMsg);
    }
    else {
        addMsg(data.msg, time, recevierMsg);
    }

}




// 전송 버튼 클릭시
document.getElementById("chat-send-btn").addEventListener("click", () => {

    transmitMsg();
});


// 단축키 설정
document.getElementById("chat-send-msg").addEventListener("keydown", (e) => {

    // ENTER키 입력시
    if (e.key === 'Enter') {
        transmitMsg();
    }
});



// 서버로 메시지 전송하기
const transmitMsg = () => {

    let inputMsg = document.getElementById("chat-send-msg");

    let chat = {
        sender: senderNickname,
        receiver: receiverNickname,
        msg: inputMsg.value
    }

    fetch("/api/chat", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify(chat)
    }).then(() => {
        console.log("메시지 전송에 성공하였습니다.");
        inputMsg.value = "";
    }).catch((error) => {
        console.error("메시지 전송에 실패하였습니다. : " + error);
    });

}




// 왼쪽/오른쪽 배치 함수와 메시지를 이용해서 메시지를 화면에 추가
const addMsg = (msg, time, callbackFn) => {

    let chatBox = document.getElementById("chat-box");
    let chatInnerBox = document.createElement("div");
    chatInnerBox.innerHTML = callbackFn(msg, time);
    chatBox.append(chatInnerBox);

    // 스크롤을 항상 밑으로 내린다
    chatBox.scrollTop = chatBox.scrollHeight;
}




// 상대방 메시지는 왼쪽에 배치
const recevierMsg = (msg, time) => {

    return `<div class="overflow-hidden mb-4">
                <div class="w-3/5 pl-2">
                    <p class="bg-[#ebebeb] rounded-md text-[#646464] text-sm m-0 px-3 py-2 w-full">
                        ${msg}
                    </p>

                    <span class="text-[#747474] block text-xs mt-2">${time}</span>
                </div>
            </div>`
}



// 본인 메시지는 오른쪽 배치
const senderMsg = (msg, time) => {

    return `<div class="overflow-hidden my-6">
                <div class="float-right pr-2 w-3/5">
                    <p class="bg-amber-700 rounded-md text-sm m-0 text-white px-3 py-2 w-full">
                        ${msg}
                    </p>
                    <span class="text-[#747474] block text-xs mt-2">${time}</span>
                </div>
            </div>`
}



// 시간 포맷 함수
const getFormatTime = (time) => {

    const dateObj = new Date(time);

    // 시간과 분 추출
    let hours = dateObj.getHours();
    let minutes = ("0" + dateObj.getMinutes()).slice(-2);

    // 오전/오후 설정
    let ampm = hours >= 12 ? '오후' : '오전';
    hours = hours % 12;
    hours = hours ? hours : 12;

    const formattedTime = ampm + " " + hours + ":" + minutes;

    return formattedTime;
}