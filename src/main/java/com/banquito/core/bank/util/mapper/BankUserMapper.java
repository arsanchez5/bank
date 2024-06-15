
package com.banquito.core.bank.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.banquito.core.bank.controller.dto.BankUserDTO;
import com.banquito.core.bank.model.BankUser;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankUserMapper {

    @Mapping(source = "username", target = "userName")
    @Mapping(source = "role.name", target = "nameRole")
    BankUserDTO toDTO(BankUser bank);

    @Mapping(source = "userName", target = "username")
    BankUser toPersistence(BankUserDTO dto);

}
