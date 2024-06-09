package com.banquito.core.bank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.model.Role;
import com.banquito.core.bank.service.CommonBankService;

@RestController
@RequestMapping(path = "/roles")
public class RoleController {
    private final CommonBankService service;

    public RoleController(CommonBankService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
        return ResponseEntity.ok(this.service.obtainAllRoles());
    }

    @GetMapping("/{code}")
    public ResponseEntity<Role> getByCode(@PathVariable String code){
        try {
            return ResponseEntity.ok(this.service.obtainRole(code));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }
}
