package com.banquito.core.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.controller.dto.BankUserDTO;
import com.banquito.core.bank.service.BankUserService;

@RestController
@RequestMapping(path = "/users")
public class UserBankController {

    private final BankUserService service;

    public UserBankController(BankUserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BankUserDTO> createUser(@RequestBody BankUserDTO dto) {
        this.service.create(dto);
    }

}