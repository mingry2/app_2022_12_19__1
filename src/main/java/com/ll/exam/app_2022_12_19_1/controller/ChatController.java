package com.ll.exam.app_2022_12_19_1.controller;

import com.ll.exam.app_2022_12_19_1.domain.ChatMessage;
import com.ll.exam.app_2022_12_19_1.domain.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // http://localhost:8080/chat/writeMessage
    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<WriteMessageResponse> writeMessage() {
        ChatMessage message = new ChatMessage("홍길동", "안녕하세요.");

        chatMessages.add(message);

        return new RsData("S-1", "메세지가 작성되었습니다.", new WriteMessageResponse(message.getId()));
    }
}
