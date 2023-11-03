package com.colis.controller;

import com.colis.dto.PostDTO;
import com.colis.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping("")
    public ResponseEntity<List<PostDTO>> findLastPosts(){

        log.info("fetch last posts active ");

        List<PostDTO> listPost =  postService.findLastPosts();

        log.info("Post : fetch  last posts success ");

        return new ResponseEntity<>(listPost, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PostDTO post){
            log.info(" save post ");
            postService.savePost(post);
            log.info(" Post : Post save success  ");
            return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PostDTO>> findByUser(@PathVariable String id){
        log.info("find Post by user ");
        List<PostDTO> list = postService.findPostByUser(id);
        log.info("Post : fetch post by user success ");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

/*    @GetMapping("/{regionDepart}/{regionDestination}")
    public ResponseEntity<List<PostDTO>> findPostSearch(@PathVariable String regionDepart, @PathVariable String regionDestination){
        log.info("search post ");
      List<PostDTO> list = postService.findPostSearch(regionDepart, regionDestination);
      log.info("post : post search success ");
      return new ResponseEntity<>(list, HttpStatus.OK);
    }*/

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> findPostSearch(@RequestParam("regionDepart")String depart, @RequestParam("regionDestination") String destination){
        log.info("post search ");
        List<PostDTO> list = postService.findPostSearch(depart,destination);
        log.info("post : post search success ");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
