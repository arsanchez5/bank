package com.banquito.core.bank.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.banquito.core.bank.controller.dto.ChannelDTO;
import com.banquito.core.bank.model.Channel;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ChannelMapper {

    ChannelDTO toDTO(Channel channel);
}