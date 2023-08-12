package com.colis.controller;

import com.colis.dto.ProfileDTO;
import com.colis.entities.ProfileEntity;
import com.colis.repositories.ProfileReopsitory;
import com.colis.services.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/save")
    public ResponseEntity<String> save( @ModelAttribute ProfileDTO profileDTO, @RequestParam("photo") MultipartFile photoFile) throws IOException {
        log.info("....... save profile .....");
        profileService.saveProfile(profileDTO, photoFile);
        log.info("....... profile : profile save success ");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
