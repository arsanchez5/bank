package com.banquito.core.bank.util.mapper;

import org.mapstruct.Mapper;

import com.banquito.core.bank.controller.dto.RoleDTO;
import com.banquito.core.bank.model.Role;

@Mapper
public interface RoleMapper {

    RoleDTO from(Role role);

}