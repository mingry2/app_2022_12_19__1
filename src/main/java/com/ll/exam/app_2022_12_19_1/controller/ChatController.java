package com.ll.exam.app_2022_12_19_1.controller;

import com.ll.exam.app_2022_12_19_1.domain.ChatMessage;
import com.ll.exam.app_2022_12_19_1.domain.RsData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/chat")
@Slf4j
public class ChatController {

    // 작성된 메세지를 저장하는 리스트
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
// ------------------------------------------------------------------------------
    @AllArgsConstructor
    @Getter
    public static class MessagesResponse {
        private final List<ChatMessage> messages;
    }

    @AllArgsConstructor
    @Getter
    public static class MessagesRequest {
        private final Long fromId;
    }

    // 작성한 메세지 조회
    // http://localhost:8020/chat/messages
    @GetMapping("/messages")
    @ResponseBody
    public RsData<MessagesResponse> messages(MessagesRequest req) {
//        log.debug("req : {}", req);
        List<ChatMessage> messages = chatMessages;

        // IntStream.range(0, messages.size()) 값 중에 req.fromId와 같은 id 값이 존재하면
        // 다음으로 넘어가고 아니면 -1을 반환해라
        if (req.fromId != null) {
            int index = IntStream.range(0, messages.size()) // 0 부터 messages.size() 까지 반복
                    .filter(i -> chatMessages.get(i).getId()  == req.fromId)
                    .findFirst()
                    .orElse(-1);
            // 내가 찾은 index 의 다음 숫자부터 끝까지
            if (index != -1) {
                messages = messages.subList(index+1, messages.size());
            }
        }

        return new RsData<>("S-1",
                "조회 성공",
                new MessagesResponse(messages));
    }
}
