package com.banquito.core.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.bank.model.BankUser;

public interface BankUserRepository extends JpaRepository<BankUser, Integer> {

    BankUser findByUserName(String userName);

    BankUser findByEmail(String email);
}
