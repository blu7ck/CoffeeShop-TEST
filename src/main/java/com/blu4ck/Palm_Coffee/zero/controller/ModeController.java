package com.blu4ck.Palm_Coffee.zero.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModeController {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @GetMapping("/mode")
    public String getCurrentMode() {
        return "Current mode: " + activeProfile;
    }
}