package com.colis.dto;
import java.time.LocalDateTime;

public record PostDTO(String regionDepart,
                      LocalDateTime dateRegionDepart,
                      String regionDestination,
                      LocalDateTime dateRegionDestination,
                      double prix,
                      String devise,
                      int kiloInitial,
                      int kiloRestant,
                      boolean activity
) {

}

