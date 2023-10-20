package com.colis.services;

import com.colis.dto.UserDTO;
import com.colis.entities.ProfileEntity;
import com.colis.entities.UserEntity;
import com.colis.mappers.UserMapper;
import com.colis.repositories.UserRepository;
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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserMapper mapper;

    private final UserRepository userRepository;

    @Value("${media.images.images-profile.directory}")
    private String UPLOAD_DIR ;

    @Value("${media.images.images-profile.url}")
    private String url ;

    public UserEntity saveUser(UserDTO user){
        return userRepository.save(mapper.map(user));
    }

    public void updateImageProfile(String id , MultipartFile imageFile) throws IOException {
        String fileName =   null;
        if(!imageFile.getOriginalFilename().equals("")){
            fileName =  UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            try (OutputStream os = Files.newOutputStream(filePath)) {
                os.write(imageFile.getBytes());
            }
        }
        if(fileName != null){
            Optional<UserEntity> user = userRepository.findById(id);
            user.get().setUrlPhoto(url+fileName);
            userRepository.save(user.get());
        }

    }


}
