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

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostMapper mapper;

    private final PostRepository postRepository;

/*    @Value("${media.images.imagesProfile.url}")
    private String urlImage ;*/

    public PostEntity  savePost(PostDTO postDTO){
        return postRepository.save(mapper.map(postDTO));
    }

    public List<PostDTO> findPostByUser(String id){
        return mapper.map(postRepository.findAllByUser_IdOrderByCreatedDateDesc(id));
    }

    public List<PostDTO> findLastPosts(){
        List<PostEntity> listPost = postRepository.findAllByActivityIsTrueOrderByCreatedDateDesc();
      //  listPost.stream().forEach(post -> post.getProfile().setPhotoProfile("http://192.168.11.104:8080/images/" + post.getProfile().getPhotoProfile()));
//        listPost.stream().filter(post -> post != null) ;
/*        listPost.stream().forEach(post -> {
            if(post.getUser().getUrlPhoto() != null )
                post.getUser().setUrlPhoto( urlImage + post.getUser().getUrlPhoto());
        });*/

      //  log.info("ma liste " + listPost.toString());

         return mapper.map(listPost);
       // return null ;
    }

    public List<PostDTO> findPostSearch(String depart, String destination){
        if(!depart.isEmpty() && !destination.isEmpty())
            return mapper.map(postRepository.findAllByRegionDepartAndRegionDestinationAndActivityIsTrue(depart, destination));
        else if (!depart.isEmpty() && destination.isEmpty())
            return mapper.map(postRepository.findAllByRegionDepartAndActivityIsTrue(depart));
        else if (depart.isEmpty() && !destination.isEmpty())
            return mapper.map(postRepository.findAllByRegionDestinationAndActivityIsTrue(destination));
        else
            return mapper.map(postRepository.findAllByActivityIsTrueOrderByCreatedDateDesc());
    }

    public void DesactivePost(){
        log.info(" ************** appele de la methode de desactivation des posts **************");
        List<PostEntity> posts = (ArrayList<PostEntity>) postRepository.findAllByDateRegionDepartLessThanAndActivityIsTrue(LocalDateTime.now());
        posts.stream().forEach(post -> {
            post.setActivity(false);
            postRepository.save(post);
        });
        log.info("********** fin de l'appele de la methde de desactivation posts ********");
    }
}
