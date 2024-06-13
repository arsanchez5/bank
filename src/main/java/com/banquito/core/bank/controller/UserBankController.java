package com.banquito.core.bank.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banquito.core.bank.controller.dto.BankUserDTO;
import com.banquito.core.bank.controller.dto.UserPasswordDTO;
import com.banquito.core.bank.model.BankUser;
import com.banquito.core.bank.service.BankUserService;
import com.banquito.core.bank.util.mapper.BankUserMapper;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(path = "/users")
public class UserBankController {

    private final BankUserMapper bankUserMapper;
    private final BankUserService service;

    public UserBankController(BankUserService service, BankUserMapper bankUserMapper) {
        this.service = service;
        this.bankUserMapper = bankUserMapper;
    }

    @PostMapping
    public ResponseEntity<BankUserDTO> createUser(@RequestBody BankUserDTO dto) {
        try {
            BankUserDTO dtoBU = this.service.create(dto);
            return ResponseEntity.ok(dtoBU);
        } catch (RuntimeException rte) {
            rte.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankUserDTO> getById(@PathVariable Integer id) {
        try {
            System.out.println("Va a buscar usuario por id:" + id);
            return ResponseEntity.ok(this.bankUserMapper.toDTO(this.service.obtainUserById(id)));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<BankUserDTO> getByEmail(@PathVariable String email) {
        try {
            System.out.println("Va a buscar usuario por email: " + email);
            return ResponseEntity.ok(this.bankUserMapper.toDTO(this.service.obtainByEmail(email)));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{userName}")
    public ResponseEntity<BankUserDTO> getByUserName(@PathVariable String userName) {
        try {
            System.out.println("Va a buscar usuario por username: " + userName);
            return ResponseEntity.ok(this.bankUserMapper.toDTO(this.service.obtainByUserName(userName)));
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BankUserDTO>> getByLastName(@RequestParam("lastName") String lastName) {
        System.out.println("Va a buscar usuario por lastName like: " + lastName + "%");
        if (lastName != null) {
            List<BankUser> users = this.service.obtainByLastName(lastName + "%");
            return ResponseEntity.ok(
                    users.stream()
                            .map(bu -> this.bankUserMapper.toDTO(bu))
                            .collect(Collectors.toList()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<Void> changePassword(@RequestBody UserPasswordDTO dto) {
        System.out.println("Va a Cambiar clave para: " + dto.toString());
        try {
            this.service.changePassword(dto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException rte) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/generatePassword/{userName}")
    public ResponseEntity<Void> generatePassword(@PathVariable("userName") String userName) {
        System.out.println("Va a generar clave para: " + userName);
        try {
            this.service.generatePassword(userName);
            return ResponseEntity.ok().build();
        } catch (RuntimeException rte) {
            return ResponseEntity.notFound().build();
        }
    }

}