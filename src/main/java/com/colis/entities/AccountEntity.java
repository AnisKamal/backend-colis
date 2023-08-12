package com.colis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(
        name = "t_account",
        uniqueConstraints = {
                @UniqueConstraint(
                        name="user_email_unique",
                        columnNames = "email"
                )
        })
@Data
public class AccountEntity extends AbstractEntity{

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


}
