package com.colis.repositories;

import com.colis.entities.ContactEntity;
import com.colis.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, String> {
    List<ContactEntity> findAllByProfile_Id(String id);

}
