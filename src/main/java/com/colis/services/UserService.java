package com.colis.services;

import com.colis.dto.UserDTO;
import com.colis.entities.UserEntity;
import com.colis.mappers.UserMapper;
import com.colis.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserMapper mapper;

    private final UserRepository userRepository;

    public UserEntity saveUser(UserDTO user){
        return userRepository.save(mapper.map(user));
    }

}
