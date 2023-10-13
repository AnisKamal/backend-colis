package com.colis.controller;

import com.colis.dto.UserDTO;
import com.colis.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> authenticate(@RequestBody UserDTO user){
        log.info("execution of authentication ");
        UserDTO userDTO = authenticationService.Authenticate(user);
        log.info("authentication success ");
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
