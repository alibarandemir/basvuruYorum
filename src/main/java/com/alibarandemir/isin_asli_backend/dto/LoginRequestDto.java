package com.alibarandemir.isin_asli_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
public class LoginRequestDto {
    private String email;
    private String password;

}




