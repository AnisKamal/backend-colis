package com.colis.repositories;

import com.colis.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileReopsitory extends JpaRepository<ProfileEntity, String> {

}
