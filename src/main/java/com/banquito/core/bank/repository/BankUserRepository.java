package com.banquito.core.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.bank.model.BankUser;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {

    BankUser findByUsername(String username);

    BankUser findByEmail(String email);

    List<BankUser> findTop100ByLastNameLikeOrderByLastNameAscFirstNameAsc(String lastName);
}