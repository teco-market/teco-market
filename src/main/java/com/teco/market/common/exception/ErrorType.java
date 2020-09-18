package com.teco.market.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
    BUSINESS("B100", "비즈니스 Exception이 발생하였습니다. 사용하는 곳을 확인하세요."),
    NOT_FOUND("N100", "값을 찾을 수 없습니다."),
    NOT_FOUND_MEMBER("N200", "존재하지 않는 유저입니다."),
    NOT_FOUND_GENERATION("N201", "존재하지 않는 세대입니다."),
    NOT_FOUND_CATEGORY("N202", "존재하지 않는 카테고리입니다."),
    NOT_FOUND_POST("N203", "존재하지 않는 포스트입니다."),
    NOT_FOUND_COMMENT("N204", "존재하지 않는 댓글입니다."),
    INVALID("I100", "유효하지 않은 값입니다."),
    INVALID_TOKEN("I101", "유효하지 않은 토큰입니다."),
    INVALID_IMAGE("I102", "유효하지 않은 이미지입니다."),
    INVALID_WRITER("I104", "글 작성자가 아닙니다."),
    INVALID_SLACK("I105", "적절하지 않은 슬랙 요청입니다."),
    INVALID_FILE("I106", "적절하지 않은 파일입니다. 업로드 할 수 없습니다."),
    VALID_TOKEN_BUT_NOT_FOUND_MEMBER("I103", "토큰은 유효하나, 존재하지 않는 사용자입니다.(회원 탈퇴 한 듯?"),

    UNAUTHORIZED_MEMBER("I103", "해당 회원은 접근할 권한이 없습니다.");

    private String code;
    private String message;
}
