package com.colis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_profile")
public class ProfileEntity extends AbstractEntity{

    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;

    private String photoProfile;
}
