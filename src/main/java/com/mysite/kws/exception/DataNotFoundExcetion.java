package com.mysite.kws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity Not Found")
public class DataNotFoundExcetion extends RuntimeException {
    // 직렬화 ID. 직렬화란 객체를 바이트 스트림으로 변환하는 것을 말한다.
    private static final long serialVersionUID = 1L;

    public DataNotFoundExcetion(String message) {
        super(message);
    }
}
