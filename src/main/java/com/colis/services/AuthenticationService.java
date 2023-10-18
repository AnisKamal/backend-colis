package com.colis.services;

import com.colis.dto.UserDTO;
import com.colis.entities.UserEntity;
import com.colis.mappers.UserMapper;
import com.colis.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;

    private final UserService userService;

    private final UserMapper mapper;

    public UserDTO Authenticate(UserDTO user){
        Optional<UserEntity> userEntity= userRepository.findUserEntityByEmail(user.email());
        if(userEntity.isEmpty()){
            log.info("save user ");
            return mapper.map(userService.saveUser(user));
        }
        else{
            log.info("user exist ");
            return mapper.map(userEntity.get());
        }
    }

}
