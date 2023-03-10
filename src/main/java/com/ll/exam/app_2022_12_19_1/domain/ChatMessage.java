package com.ll.exam.app_2022_12_19_1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ChatMessage {
    private long id;
    // 날짜 형식 바꾸기
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate;
    private String authorName;
    private String content;

    public ChatMessage(String authorName, String content) {
        this(ChatMessageIdGenerator.genNextId(), LocalDateTime.now(), authorName, content);
    }
}

// id 값을 자동으로 증가시켜주는 클래스
class ChatMessageIdGenerator {
    private static long id = 0;
    public static long genNextId(){
        return ++id;
    }
}
