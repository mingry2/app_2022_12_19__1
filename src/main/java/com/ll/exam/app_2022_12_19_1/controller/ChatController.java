package com.ll.exam.app_2022_12_19_1.controller;

import com.ll.exam.app_2022_12_19_1.domain.ChatMessage;
import com.ll.exam.app_2022_12_19_1.domain.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private List<ChatMessage> chatMessages = new ArrayList<>();

    // 게시글 작성 후 조회 시 id만 응답
    @AllArgsConstructor
    @Getter
    public static class WriteMessageResponse {
        private final long id;
    }

    // RequestBody로 받은 클라이언트의 요청
    @AllArgsConstructor
    @Getter
    public static class WriteMessageRequest {
        private final String authorName;
        private final String content;
    }

    // 메세지 작성
    // http://localhost:8020/chat/writeMessage
    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage(@RequestBody WriteMessageRequest req) {
        ChatMessage message = new ChatMessage(req.authorName,req.content);
        chatMessages.add(message);
        return new RsData("S-1",
                "메세지가 작성되었습니다.",
                new WriteMessageResponse(message.getId()));
    }

    @AllArgsConstructor
    @Getter
    public static class MessagesResponse {
        private final List<ChatMessage> messages;
    }

    // 작성한 메세지 조회
    // http://localhost:8020/chat/messages
    @GetMapping("/messages")
    @ResponseBody
    public RsData<MessagesResponse> messages() {
        return new RsData<>("S-1",
                "조회 성공",
                new MessagesResponse(chatMessages));
    }
}
