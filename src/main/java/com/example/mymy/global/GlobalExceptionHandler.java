package com.example.mymy.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    // 에러 유형 정의
    @Getter
    @AllArgsConstructor
    public enum ErrorType {
        // 회원 관련
        MEMBER_NOT_FOUND(404, "존재하지 않는 회원입니다."),
        DUPLICATE_EMAIL(400, "이미 사용중인 이메일입니다."),

        // 상품, 주문 관련
        PRODUCT_NOT_FOUND(404, "상품을 찾을 수 없습니다."),
        OUT_OF_STOCK(400, "재고가 부족합니다."),

        // 권한 관련
        ACCESS_DENIED(403, "접근 권한이 없습니다."),
        UNAUTHORIZED(401, "로그인이 필요합니다.");

        private final int status;
        private final String message;
    }

    // 에러 응답 형식
    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private final int status;
        private final String message;
    }

    // 통합 예외 클라스
    @Getter
    public static class BusinessException extends RuntimeException {
        private final ErrorType errorType;

        public BusinessException(ErrorType errorType) {
            super(errorType.getMessage());
            this.errorType = errorType;
        }
    }

    // 핸들러
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
                .status(500)
                .body(new ErrorResponse(500, "서버 내부 오류가 발생했습니다."));
    }
}
