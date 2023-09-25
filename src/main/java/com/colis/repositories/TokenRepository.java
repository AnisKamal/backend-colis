package com.colis.repositories;

import com.colis.entities.TokenEntity;
import com.colis.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, String> {

    List<TokenEntity> findAllByUser_IdAndExpiredIsFalseAndRevokedIsFalse(String id);
    Optional<TokenEntity> findByToken(String token);
}
