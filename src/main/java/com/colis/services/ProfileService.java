package com.colis.services;

import com.colis.dto.ProfileDTO;
import com.colis.entities.ProfileEntity;
import com.colis.mappers.ProfileMapper;
import com.colis.repositories.ProfileReopsitory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    @Value("${media.images.imagesProfile.directory}")
    private String UPLOAD_DIR ;

    private final ProfileReopsitory profileReopsitory;

    private final ProfileMapper mapper;

    public void saveProfile(ProfileDTO profile, MultipartFile imageFile)  throws IOException {

        String fileName =   null;

        if(!imageFile.getOriginalFilename().equals("")){
            fileName =  imageFile.getOriginalFilename();


        Path filePath = Paths.get(UPLOAD_DIR, fileName);

        try (OutputStream os = Files.newOutputStream(filePath)) {
            os.write(imageFile.getBytes());
        }
        }
        // profile.setPhotoProfile(fileName);
        // profileReopsitory.save(mapper.map(profile));

        ProfileEntity profileEntity = mapper.map(profile);
        profileEntity.setPhotoProfile(fileName);
        profileReopsitory.save(profileEntity);
    }
}
