// package com.example.cattalk.handler;

// import java.io.IOException;
// import java.util.*;

// import org.springframework.stereotype.Component;
// import org.springframework.web.socket.CloseStatus;
// import org.springframework.web.socket.TextMessage;
// import org.springframework.web.socket.WebSocketSession;
// import org.springframework.web.socket.handler.TextWebSocketHandler;

// import com.example.cattalk.dto.ChatMessageDTO;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.log4j.Log4j2;

// /*
// * WebSocket Handler 작성
// * 소켓 통신은 서버와 클라이언트가 1:n으로 관계를 맺는다. 따라서 한 서버에 여러 클라이언트 접속 가능
// * 서버에는 여러 클라이언트가 발송한 메세지를 받아 처리해줄 핸들러가 필요
// * TextWebSocketHandler를 상속받아 핸들러 작성
// * 클라이언트로 받은 메세지를 log로 출력하고 클라이언트로 환영 메세지를 보내줌
// * */
// @Log4j2
// @Component
// @RequiredArgsConstructor
// public class WebSocketChatHandler extends TextWebSocketHandler {
    
//     private final ObjectMapper mapper;

//     // 현재 연결된 세션들
//     private final Set<WebSocketSession> sessions = new HashSet<>();

//     // chatRoomId: {session1, session2}
//     private final Map<Long,Set<WebSocketSession>> chatRoomSessionMap = new HashMap<>();

//     // 소켓 연결 확인
//     @Override
//     public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        
//         log.info("{} : is connected", session.getId());
//         sessions.add(session);
//     }

//     // 소켓 통신 시 메세지의 전송을 다루는 부분
//     @Override
//     protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//         String payload = message.getPayload();
//         log.info("payload : {}", payload);

//         // 페이로드 -> chatMessageDTO로 변환
//         ChatMessageDTO chatMessageDTO = mapper.readValue(payload, ChatMessageDTO.class);

//         // 메모리 상에 채팅방이 없으면 만들어줌
//         Long chatRoomId = chatMessageDTO.getChatRoomId();

//         if(!chatRoomSessionMap.containsKey(chatRoomId)){
//             chatRoomSessionMap.put(chatRoomId,new HashSet<>());
//         }

//         Set<WebSocketSession> chatRoomSession = chatRoomSessionMap.get(chatRoomId);

//         // message 에 담긴 타입을 확인한다.

//         // 입장하면 채팅방에 세션을 추가한다.
//         if (chatMessageDTO.getMessageType().equals(ChatMessageDTO.MessageType.ENTER)) {
//             chatRoomSession.add(session);
//         }

//         // 채팅방에 3명 이상이면 현재 웹소켓에 접속 안한세션을 지속적으로 찾아 제거하게 함
//         if (chatRoomSession.size()>=3) {
//             removeClosedSession(chatRoomSession);
//         }

//         sendMessageToChatRoom(chatMessageDTO, chatRoomSession);

//     }

//     // 소켓 종료 확인
//     @Override
//     public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

//         log.info("{} : is disconnected", session.getId());
//         sessions.remove(session);
//     }






//     // ====== 그 외 채팅 관련 메소드 ======
//     private void removeClosedSession(Set<WebSocketSession> chatRoomSession) {
//         chatRoomSession.removeIf(sess -> !sessions.contains(sess));
//     }

//     private void sendMessageToChatRoom(ChatMessageDTO chatMessageDTO, Set<WebSocketSession> chatRoomSession) {
//         chatRoomSession.parallelStream().forEach(sess -> sendMessage(sess, chatMessageDTO));
//     }


//     public <T> void sendMessage(WebSocketSession session, T message) {
//         try{
//             session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
//         } catch (IOException e) {
//             log.error(e.getMessage(), e);
//         }
//     }
// }

// // 웹소켓 사용시