package com.colis.services;

import com.colis.config.security.JwtService;
import com.colis.dto.AuthenticationRequestDTO;
import com.colis.dto.AuthenticationResponseDTO;
import com.colis.dto.RegisterRequestDTO;
import com.colis.entities.TokenEntity;
import com.colis.entities.TokenType;
import com.colis.entities.UserEntity;
import com.colis.mappers.UserMapper;
import com.colis.repositories.TokenRepository;
import com.colis.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );
        var user = userRepository.findByEmail(request.email()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponseDTO(jwtToken,refreshToken);

    }

    public AuthenticationResponseDTO register(RegisterRequestDTO request){
        UserEntity user = mapper.map(request) ;
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponseDTO(jwtToken, refreshToken);
    }

    private void saveUserToken(UserEntity user ,String jwtToken){
        TokenEntity token = new TokenEntity(
                jwtToken,
                TokenType.BEARER,
                false,
                false,
                user
        );
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserEntity user){
        List<TokenEntity> validUserTokens = tokenRepository.findAllByUser_IdAndExpiredIsFalseAndRevokedIsFalse(user.getId());
        if(validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });

        tokenRepository.saveAll(validUserTokens);
    }

}
