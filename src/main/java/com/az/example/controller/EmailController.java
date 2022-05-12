package com.az.example.controller;

import com.az.example.component.EmailUtil;
import com.az.example.dto.EmailRequest;
import com.az.example.dto.EmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailUtil emailUtil;

    @GetMapping
    public String testing() {
        return "Welcome to Az-FreeMarker-Email Service";
    }

    @PostMapping
    public EmailResponse sendEmail(@RequestBody EmailRequest emailRequest) {
        return emailUtil.sendEmail(emailRequest);
    }

    @PostMapping("/simple")
    public EmailResponse sendSimpleEmail(@RequestBody EmailRequest emailRequest) {
        return emailUtil.sendSimpleEmail(emailRequest);
    }
}
