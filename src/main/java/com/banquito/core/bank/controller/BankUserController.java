package com.banquito.core.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.model.BankUser;
import com.banquito.core.bank.service.BankUserService;

@RestController
@RequestMapping(path = "/bankusers")
public class BankUserController {
    private final BankUserService service;

    public BankUserController(BankUserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankUser> getByCode(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(this.service.obtainUserById(id));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<BankUser> getByUsername(@PathVariable String userName) {
        try {
            return ResponseEntity.ok(this.service.obtainByUserName(userName));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<BankUser> getByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(this.service.obtainByEmail(email));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }
}
