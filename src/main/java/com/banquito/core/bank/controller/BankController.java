package com.banquito.core.bank.controller;

import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.model.Bank;
import com.banquito.core.bank.service.CommonBankService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/bank")
public class BankController {

    private final CommonBankService service;

    public BankController(CommonBankService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Bank> findBank() {
        try {

            return ResponseEntity.ok(this.service.obtainBankDefault());

        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();

        }
    }

}
