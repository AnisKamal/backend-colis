package com.colis.services;

import com.colis.dto.PostDTO;
import com.colis.entities.PostEntity;
import com.colis.mappers.PostMapper;
import com.colis.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostMapper mapper;

    private final PostRepository postRepository;

    public PostEntity  savePost(PostDTO postDTO){
        return postRepository.save(mapper.map(postDTO));
    }

    public List<PostDTO> findPostByUser(String id){
        return mapper.map(postRepository.findAllByProfile_Id(id));
    }

    public void DesactivePost(){
        log.info(" ************** appele de la methode de desactivation **************");
        List<PostEntity> posts = (ArrayList<PostEntity>) postRepository.findAllByDateRegionDepartLessThanAndActivityIsTrue(LocalDateTime.now());
        posts.stream().forEach(post -> {
            post.setActivity(false);
            postRepository.save(post);
        });
        log.info("********** fin de l'appele de la methde de desactivation ********");
    }
}
