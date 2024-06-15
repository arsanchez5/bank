package com.banquito.core.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.controller.dto.BankUserDTO;
import com.banquito.core.bank.controller.dto.UserPasswordDTO;
import com.banquito.core.bank.service.AuthenticationService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PutMapping
    public ResponseEntity<BankUserDTO> login(@RequestBody UserPasswordDTO dto) {
        try {
            return ResponseEntity.ok(this.service.login(dto));
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}