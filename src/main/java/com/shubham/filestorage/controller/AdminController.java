package com.shubham.filestorage.controller;

import com.shubham.filestorage.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final FileService fileService;

    @Value("${admin.secret:}")
    private String adminSecret;

    public AdminController(FileService fileService) {
        this.fileService = fileService;
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearAll(@RequestHeader(name = "X-ADMIN-SECRET", required = false) String secret) {
        if (adminSecret == null || adminSecret.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Admin clear is not enabled on this instance");
        }
        if (secret == null || !secret.equals(adminSecret)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid admin secret");
        }

        fileService.clearAll();
        return ResponseEntity.ok("All files and metadata cleared");
    }
}