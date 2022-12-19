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

    // 게시글 작성 후 id만 보이게 하기
    @AllArgsConstructor
    @Getter
    public static class WriteMessageResponse {
        private final long id;
    }

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

        return new RsData("S-1", "메세지가 작성되었습니다.", new WriteMessageResponse(message.getId()));
    }


    // 작성한 메세지 조회 -> 가져오기
    // http://localhost:8020/chat/messages
    @GetMapping("/messages")
    @ResponseBody
    public RsData<List<ChatMessage>> messages() {

        return new RsData<>("S-1", "조회 성공", chatMessages);
    }
}
