package com.banquito.core.bank.service;

import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.banquito.core.bank.controller.dto.BankUserDTO;
import com.banquito.core.bank.controller.dto.UserPasswordDTO;
import com.banquito.core.bank.model.BankUser;
import com.banquito.core.bank.repository.BankUserRepository;
import com.banquito.core.bank.util.mapper.BankUserMapper;

@Service
public class AuthenticationService {

    private final BankUserRepository bankUserRepository;
    private final BankUserMapper bankUserMapper;

    public AuthenticationService(BankUserRepository bankUserRepository, BankUserMapper bankUserMapper) {
        this.bankUserRepository = bankUserRepository;
        this.bankUserMapper = bankUserMapper;
    }

    public BankUserDTO login(UserPasswordDTO dto) {
        String errorMessage = "Credenciales invalidas";
        if (dto.getUserName() != null && dto.getPassword() != null && dto.getUserName().length() > 3
                && dto.getPassword().length() == 32) {
            BankUser user = this.bankUserRepository.findByUserName(dto.getUserName());
            String md5 = DigestUtils.md5Hex(dto.getPassword());
            if (user.getPassword().equals(md5)) {
                user.setLastLogin(LocalDateTime.now());
                this.bankUserRepository.save(user);
                if ("ACT".equals(user.getState())) {
                    return this.bankUserMapper.toDTO(user);
                } else {
                    errorMessage = "Usuario no es activo";
                }
            }
        }
        throw new RuntimeException(errorMessage);
    }
}