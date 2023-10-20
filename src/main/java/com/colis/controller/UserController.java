package com.colis.controller;

import com.colis.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/update-image/{id}")
    public ResponseEntity<String> updateImage(@PathVariable String id, @RequestParam("image-profile") MultipartFile image) throws IOException {
        log.info("update image profile ");
        userService.updateImageProfile(id, image);
        log.info("update image success");
        return new ResponseEntity<>("success", HttpStatus.OK) ;
    }
}
