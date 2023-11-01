package com.colis.repositories;

import com.colis.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
   List<PostEntity> findAllByDateRegionDepartLessThanAndActivityIsTrue(LocalDateTime dateTime);

   List<PostEntity> findAllByUser_IdOrderByCreatedDateDesc(String id);


   List<PostEntity> findAllByRegionDepartAndRegionDestinationAndActivityIsTrue(String regionDepart, String RegionDestination);

   List<PostEntity> findAllByRegionDepartAndActivityIsTrue(String regionDepart);

   List<PostEntity> findAllByRegionDestinationAndActivityIsTrue(String RegionDestination);

   List<PostEntity> findAllByActivityIsTrueOrderByCreatedDateDesc();
}
