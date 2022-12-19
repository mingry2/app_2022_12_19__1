package com.ll.exam.app_2022_12_19_1.controller;

import com.ll.exam.app_2022_12_19_1.domain.ChatMessage;
import com.ll.exam.app_2022_12_19_1.domain.RsData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chat")
public class ChatController {

    // http://localhost:8080/chat/writeMessage
    @PostMapping("/writeMessage")
    @ResponseBody
    public RsData<ChatMessage> writeMessage() {
        ChatMessage message = new ChatMessage("홍길동", "안녕하세요.");

        return new RsData("S-1", "메세지가 작성되었습니다.", null);
    }
}
