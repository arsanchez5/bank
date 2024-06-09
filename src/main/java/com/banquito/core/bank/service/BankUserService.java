package com.banquito.core.bank.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.banquito.core.bank.model.BankUser;
import com.banquito.core.bank.repository.BankUserRepository;
@Service
public class BankUserService {
    private final BankUserRepository bankUserRepository;

    public BankUserService(BankUserRepository bankUserRepository) {
        this.bankUserRepository = bankUserRepository;
    }

    public BankUser obtainUserById(Integer id){
        Optional<BankUser> userOpt = this.bankUserRepository.findById(id);
        if(userOpt.isPresent()){
            return userOpt.get();
        }else{
            throw new RuntimeException("No existe el usuario con id: " + id);
        }
    }

    public BankUser obtainByUserName(String userName){
        BankUser user = this.bankUserRepository.findByUserName(userName);
        if(user != null){
            return user;
        }else{
            throw new RuntimeException("No existe el usuario con username: " + userName);
        }
    }

    public BankUser obtainByEmail(String email){
        BankUser user = this.bankUserRepository.findByEmail(email);
        if(user != null){
            return user;
        }else{
            throw new RuntimeException("No existe el usuario con email: " + email);
        }
    }
}
