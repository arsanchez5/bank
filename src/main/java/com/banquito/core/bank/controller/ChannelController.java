package com.banquito.core.bank.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.controller.dto.ChannelDTO;
import com.banquito.core.bank.model.Channel;
import com.banquito.core.bank.service.CommonBankService;
import com.banquito.core.bank.util.mapper.ChannelMapper;

@RestController
@RequestMapping(path = "/channels")
public class ChannelController {

    private final CommonBankService service;
    private final ChannelMapper channelMapper;

    public ChannelController(CommonBankService service, ChannelMapper channelMapper) {
        this.service = service;
        this.channelMapper = channelMapper;
    }

    @GetMapping
    public ResponseEntity<List<ChannelDTO>> getAll() {
        List<Channel> channels = this.service.obtainAllChannels();
        return ResponseEntity.ok(
                channels.stream()
                        .map(c -> this.channelMapper.toDTO(c))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/{code}")
    public ResponseEntity<ChannelDTO> getByCode(@PathVariable String code) {
        try {
            return ResponseEntity.ok(this.channelMapper.toDTO(this.service.obtainChannel(code)));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }
}
