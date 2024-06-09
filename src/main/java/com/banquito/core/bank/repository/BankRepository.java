package com.banquito.core.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.bank.model.Bank;

public interface BankRepository extends JpaRepository<Bank, String>{


}
