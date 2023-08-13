package com.colis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(
            mappedBy = "profile",
            cascade = CascadeType.ALL
    )
    List<PostEntity> posts;

}
