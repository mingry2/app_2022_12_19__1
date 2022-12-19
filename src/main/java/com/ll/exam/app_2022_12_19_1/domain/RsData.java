package com.ll.exam.app_2022_12_19_1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RsData<T> {
    private String resultCode; // 결과 코드 -> s : 성공(-1,-2...:각각의 코드를 추가) f : 실패
    private String msg; // 출력할 메세지
    private T data;
}
