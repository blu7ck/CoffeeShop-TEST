package com.blu4ck.Palm_Coffee.zero.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TESTController {

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "Yönetici sayfası";
    }

    @GetMapping("/cash/dashboard")
    public String cashierDashboard() {
        return "Kasa sayfası";
    }

    @GetMapping("/feedback")
    public String guestFeedback() {
        return "Anonim geri bildirim";
    }
}
