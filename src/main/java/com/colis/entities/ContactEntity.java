package com.colis.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_contact")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ContactEntity extends AbstractEntity {

    @Column(nullable = false)
    private String contactId ;

    @ManyToOne
    @JoinColumn(name = "id_profile")
    private ProfileEntity profile;
}
