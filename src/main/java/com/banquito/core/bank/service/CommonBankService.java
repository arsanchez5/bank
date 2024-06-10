package com.banquito.core.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.bank.model.Bank;
import com.banquito.core.bank.model.Channel;
import com.banquito.core.bank.model.Role;
import com.banquito.core.bank.repository.BankRepository;
import com.banquito.core.bank.repository.ChannelRepository;
import com.banquito.core.bank.repository.RoleRepository;

@Service
public class CommonBankService {

    private final BankRepository bankRepository;
    private final RoleRepository roleRepository;
    private final ChannelRepository channelRepository;

    public CommonBankService(BankRepository bankRepository, RoleRepository roleRepository,
            ChannelRepository channelRepository) {
        this.bankRepository = bankRepository;
        this.roleRepository = roleRepository;
        this.channelRepository = channelRepository;
    }

    public Bank obtainBankDefault() {
        List<Bank> banks = this.bankRepository.findAll();
        if (!banks.isEmpty()) {
            return banks.getFirst();
        } else {
            throw new RuntimeException("No se encuentra ningun banco");
        }
    }

    public List<Role> obtainAllRoles() {
        return this.roleRepository.findAll();
    }

    public Role obtainRole(String code) {
        Optional<Role> roleOpt = this.roleRepository.findById(code);
        if (roleOpt.isPresent()) {
            return roleOpt.get();
        } else {
            throw new RuntimeException("No existe el rol con code: " + code);
        }
    }

    public List<Channel> obtainAllChannels() {
        return this.channelRepository.findAllByOrderByNameAsc();
    }

    public Channel obtainChannel(String code) {
        Optional<Channel> channelOpt = this.channelRepository.findById(code);
        if (channelOpt.isPresent()) {
            return channelOpt.get();
        } else {
            throw new RuntimeException("No existe el canal con codigo: " + code);
        }
    }
}