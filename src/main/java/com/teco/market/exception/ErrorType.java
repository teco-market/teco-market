package com.teco.market.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
    BUSINESS("B100", "비즈니스 Exception이 발생하였습니다. 사용하는 곳을 확인하세요."),
    NOT_FOUND("N100", "값을 찾을 수 없습니다."),
    NOT_FOUND_MEMBER("N200", "존재하지 않는 유저입니다."),
    NOT_FOUND_GENERATION("N201", "존재하지 않는 세대입니다."),
    INVALID("I100", "유효하지 않은 값입니다."),
    INVALID_TOKEN("I101", "유효하지 않은 토큰입니다."),
    VALID_TOKEN_BUT_NOT_FOUND_MEMBER("I102", "토큰은 유효하나, 존재하지 않는 사용자입니다.(회원 탈퇴 한 듯?");

    private String code;
    private String message;
}
