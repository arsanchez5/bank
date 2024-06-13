package com.banquito.core.bank.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.banquito.core.bank.controller.dto.BankDTO;
import com.banquito.core.bank.model.Bank;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankMapper {

    BankDTO toDTO(Bank bank);

    Bank toPersistence(BankDTO dto);
}