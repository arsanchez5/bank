package com.banquito.core.bank.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "BANK_USER")
public class BankUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANK_USER_ID", nullable = false)
    private Integer id;
    @Column(name = "CODE_BANK", length = 20, nullable = false)
    private String codeBank;
    @Column(name = "CODE_ROLE", length = 10, nullable = false)
    private String codeRole;
    @Column(name = "USER_NAME", length = 20, nullable = false)
    private String userName;
    @Column(name = "FIRST_NAME", length = 50, nullable = false)
    private String firstName;
    @Column(name = "LAST_NAME", length = 50, nullable = false)
    private String lastName;
    @Column(name = "FULL_NAME", length = 100, nullable = false)
    private String fullName;
    @Column(name = "TYPE_USER", length = 3, nullable = false)
    private String typeUser;
    @Column(name = "PASSWORD", length = 64, nullable = false)
    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;
    @Column(name = "STATE", length = 3, nullable = false)
    private String state;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_LOGIN", nullable = false)
    private LocalDateTime lastLogin;
    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "CODE_BANK", referencedColumnName = "CODE_BANK", insertable = false, updatable = false)
    private Bank bank;
    @ManyToOne
    @JoinColumn(name = "CODE_ROLE", referencedColumnName = "CODE_ROLE", insertable = false, updatable = false)
    private Role role;

    public BankUser(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BankUser other = (BankUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}