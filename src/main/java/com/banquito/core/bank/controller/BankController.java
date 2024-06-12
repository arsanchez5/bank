package com.banquito.core.bank.controller;

import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.controller.dto.BankDTO;
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
    public ResponseEntity<BankDTO> findBank() {
        try {
            Bank bank = this.service.obtainBankDefault();
            BankDTO dto = new BankDTO();
            dto.setCode(bank.getCode());
            dto.setName(bank.getName());
            dto.setStartDate(bank.getStartDate());
            return ResponseEntity.ok(dto);
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

}
