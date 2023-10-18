package com.colis.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateRegionDepart;

    @Column(nullable = false)
    private String regionDestination;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
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

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_profile")
    private ProfileEntity profile;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
