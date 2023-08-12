package com.colis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_profile")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ProfileEntity extends AbstractEntity{

    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;

    private String photoProfile;

}
