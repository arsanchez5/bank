package com.banquito.core.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banquito.core.bank.model.Role;

public interface RoleRepository extends JpaRepository<Role, String>{

}
