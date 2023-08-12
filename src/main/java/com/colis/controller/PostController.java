package com.colis.controller;

import com.colis.dto.PostDTO;
import com.colis.dto.ProfileDTO;
import com.colis.repositories.ProfileReopsitory;
import com.colis.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PostDTO post){

            log.info("save post ...");
            postService.savePost(post);
            log.info("---- Post : Post save success  ");
            return new ResponseEntity<>("Success", HttpStatus.OK);

    }


}
