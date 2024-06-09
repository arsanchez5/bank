package com.banquito.core.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.model.Channel;
import com.banquito.core.bank.service.CommonBankService;

@RestController
@RequestMapping(path = "/channels")
public class ChannelController {

    private final CommonBankService service;

    public ChannelController(CommonBankService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Channel>> getAll() {
        return ResponseEntity.ok(this.service.obtainAllChannels());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Channel> getByCode(@PathVariable String code) {
        try {
            return ResponseEntity.ok(this.service.obtainChannel(code));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }
}
