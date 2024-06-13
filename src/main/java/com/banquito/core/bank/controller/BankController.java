package com.banquito.core.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.controller.dto.BankDTO;
import com.banquito.core.bank.model.Bank;
import com.banquito.core.bank.service.CommonBankService;

import com.banquito.core.bank.util.mapper.BankMapper;

@RestController
@RequestMapping(path = "/bank")
public class BankController {

    private final CommonBankService service;
    private final BankMapper bankMapper;

    public BankController(CommonBankService service, BankMapper bankMapper) {
        this.service = service;
        this.bankMapper = bankMapper;
    }

    @GetMapping
    public ResponseEntity<BankDTO> findBank() {
        try {
            Bank bank = this.service.obtainBankDefault();
            BankDTO dto = this.bankMapper.toDTO(bank);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

}
