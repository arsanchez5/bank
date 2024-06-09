package com.banquito.core.bank.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "CHANNEL")
public class Channel implements Serializable {

    @Id
    @Column(name = "CODE_CHANNEL", length = 10, nullable = false)
    private String code;
    @Column(name = "CODE_BANK", length = 20, nullable = false)
    private String codeBank;
    @Column(name = "NAME", length = 20, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "CODE_BANK", referencedColumnName = "CODE_BANK", insertable = false, updatable = false)
    private Bank bank;

}
