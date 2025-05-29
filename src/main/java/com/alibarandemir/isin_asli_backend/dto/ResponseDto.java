package com.alibarandemir.isin_asli_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResponseDto<T> {

    private boolean success;
    private String message;
    private T data;
    private Map<String, List<String>> errors;



    public static <T> ResponseDto<T> success(T data, String message) {
        return new ResponseDto<>(true, message, data,null);
    }

    public static <T> ResponseDto<T> fail(String message) {
        return new ResponseDto<>(false, message, null,null);
    }
    public static <T> ResponseDto<T> fail(String message, Map<String, List<String>> errors) {
        return new ResponseDto<>(false, message, null, errors);
    }
}
