package com.hola.holalandweb.handle;

import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    public static ResponseEntity<Object> build(ResponseError responseError) {
        return new ResponseEntity<>(responseError, responseError.getStatus());
    }
}
