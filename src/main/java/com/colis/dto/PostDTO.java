package com.colis.dto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record PostDTO(
                    String id ,

                    String regionDepart,

                    LocalDateTime dateRegionDepart,
                      String regionDestination,

                      LocalDateTime dateRegionDestination,
                      double prix,
                      String devise,
                      int kiloInitial,
                      int kiloRestant,
                      boolean activity,
                      String description,

                      UserDTO user
) {

}

