package com.banquito.core.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import com.banquito.core.bank.controller.dto.BankUserDTO;
import com.banquito.core.bank.controller.dto.UserPasswordDTO;
import com.banquito.core.bank.model.Bank;
import com.banquito.core.bank.model.BankUser;
import com.banquito.core.bank.repository.BankRepository;
import com.banquito.core.bank.repository.BankUserRepository;
import com.banquito.core.bank.util.mapper.BankUserMapper;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BankUserService {

    private final BankUserRepository bankUserRepository;
    private final BankRepository bankRepository;
    private final BankUserMapper bankUserMapper;

    public BankUserService(BankUserRepository bankUserRepository, BankRepository bankRepository,
            BankUserMapper bankUserMapper) {
        this.bankUserRepository = bankUserRepository;
        this.bankRepository = bankRepository;
        this.bankUserMapper = bankUserMapper;
    }

    @Transactional(Transactional.TxType.NEVER)
    public BankUser obtainUserById(Integer id) {
        Optional<BankUser> userOpt = this.bankUserRepository.findById(id);
        if (userOpt.isPresent()) {
            return userOpt.get();
        } else {
            throw new RuntimeException("No existe el usuario con id: " + id);
        }
    }

    public BankUser obtainByUserName(String userName) {
        BankUser user = this.bankUserRepository.findByUserName(userName);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("No existe el usuario con username: " + userName);
        }
    }

    public BankUser obtainByEmail(String email) {
        BankUser user = this.bankUserRepository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException("No existe el usuario con email: " + email);
        }
    }

    public BankUserDTO create(BankUserDTO dto) {
        if (this.bankUserRepository.findByUserName(dto.getUserName()) != null) {
            throw new RuntimeException("usuario repetido");
        }
        if (this.bankUserRepository.findByEmail(dto.getEmail()) != null) {
            throw new RuntimeException("Email repetido");
        }
        BankUser user = this.bankUserMapper.toPersistence(dto);
        Bank bank = this.bankRepository.findAll().getFirst();
        user.setCodeBank(bank.getCode());
        user.setState("BLO");
        user.setTypeUser("TEL");
        user.setCreationDate(LocalDateTime.now());
        // TODO: Generacion de password
        user.setPassword("CambiarClave1");
        BankUser userCreated = this.bankUserRepository.save(user);
        return this.bankUserMapper.toDTO(userCreated);
    }

    public List<BankUser> obtainByLastName(String lastName) {
        return this.bankUserRepository.findTop100ByLastNameLikeOrderByLastNameAscFirstNameAsc(lastName);
    }

    public void changePassword(UserPasswordDTO userPassword) {
        BankUser user = this.bankUserRepository.findByUserName(userPassword.getUserName());
        if (user == null) {
            throw new RuntimeException("No existe el usuario: " + userPassword.getUserName());
        }
        user.setPassword(userPassword.getPassword());
        this.bankUserRepository.save(user);
    }

    public void generatePassword(String userName) {
        BankUser user = this.bankUserRepository.findByUserName(userName);
        if (user == null) {
            throw new RuntimeException("No existe el usuario: " + userName);
        }
        // TODO: Generar clave
        String password = "GenerarClave2";
        String md5Hex = DigestUtils.md5Hex(password);
        user.setPassword(md5Hex);
        this.bankUserRepository.save(user);
    }
}
