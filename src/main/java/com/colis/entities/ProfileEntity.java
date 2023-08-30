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
    private String fullName;

    private String photoProfile;

    @Column(nullable = false)
    private String NTelephone;

    @OneToMany(
            mappedBy = "profile",
            cascade = CascadeType.ALL
    )
    List<PostEntity> posts;

    @OneToMany(
            mappedBy = "profile",
            cascade = CascadeType.ALL
    )
    List<ContactEntity> contacts ;

}
