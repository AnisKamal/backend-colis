package com.colis.entities;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name="user_email_unique",
                        columnNames = "email"
                )
        })
@Getter
@Setter
@ToString
public class UserEntity extends AbstractEntity {

    @Column(nullable = false)
    private String email ;

    @Column(nullable = false)
    private String name;

    private String urlPhoto;

}
