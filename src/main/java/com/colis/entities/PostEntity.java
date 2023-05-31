package com.colis.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_post")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PostEntity extends AbstractEntity {
    @Column(nullable = false)
    private String regionDepart;

    @Column(nullable = false)
    private LocalDateTime dateRegionDepart;

    @Column(nullable = false)
    private String regionDestination;

    @Column(nullable = false)
    private LocalDateTime dateRegionDestination;

    @Column(nullable = false)
    private double prix ;

    @Column(nullable = false  )
    private String devise;

    @Column(nullable = false)
    private int kiloInitial;

    @Column(nullable = false)
    private int kiloRestant;

    @Column(nullable = false)
    private boolean activity ;

}
