package com.colis.services;

import com.colis.config.security.JwtService;
import com.colis.dto.AuthenticationRequestDTO;
import com.colis.dto.AuthenticationResponseDTO;
import com.colis.dto.RegisterRequestDTO;
import com.colis.entities.UserEntity;
import com.colis.mappers.UserMapper;
import com.colis.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findUserEntityByEmail(request.email());
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new AuthenticationResponseDTO(jwtToken,refreshToken);
    }

    public AuthenticationResponseDTO register(RegisterRequestDTO request){
        UserEntity user = mapper.map(request) ;
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return new AuthenticationResponseDTO(jwtToken, refreshToken);
    }

}
