package com.banquito.core.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.controller.dto.RoleDTO;
import com.banquito.core.bank.model.Role;
import com.banquito.core.bank.service.CommonBankService;
import com.banquito.core.bank.util.mapper.RoleMapper;

@RestController
@RequestMapping(path = "/roles")
public class RoleController {
    private final CommonBankService service;

    public RoleController(CommonBankService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.ok(this.service.obtainAllRoles());
    }

    @GetMapping("/{code}")
    public ResponseEntity<RoleDTO> getByCode(@PathVariable String code) {
        try {
            Role role = this.service.obtainRole(code);
            RoleDTO dto = RoleMapper.from(role);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }
}
