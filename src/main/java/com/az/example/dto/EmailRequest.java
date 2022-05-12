package com.az.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    private String senderEmail;
    private String receiverEmail;
    private String subject;
    private String userName;
    private String message;
}
