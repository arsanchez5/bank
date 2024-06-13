package com.banquito.core.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.banquito.core.bank.controller.dto.BankUserDTO;
import com.banquito.core.bank.controller.dto.UserPasswordDTO;

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
            return BankUser user = this.service.login(dto);
            
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

}
