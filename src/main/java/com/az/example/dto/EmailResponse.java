package com.az.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailResponse {

    private String message;
    private boolean status;
}
