package com.colis.services;

import com.colis.dto.PostDTO;
import com.colis.entities.PostEntity;
import com.colis.mappers.PostMapper;
import com.colis.repositories.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @Mock
    private PostMapper mapper;
    private AutoCloseable autoCloseable;
    private PostService underTest;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new PostService(mapper, postRepository);
    }

    @AfterEach
     void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void cansavePost() {
        String str = "2023-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        // given
        PostDTO postDTO = new PostDTO("Paris",
                dateTime,
                "Douala",
                dateTime,
                20,
                "EUR",
                10,
                10,
                true
                );

        PostEntity expectedPost = new PostEntity();
        expectedPost.setRegionDepart("Paris");
        expectedPost.setRegionDestination("Douala");
        expectedPost.setPrix(20);
        expectedPost.setDevise("EUR");
        expectedPost.setKiloInitial(10);
        expectedPost.setKiloRestant(10);
        expectedPost.setActivity(true);
        expectedPost.setDateRegionDepart(dateTime);
        expectedPost.setDateRegionDestination(dateTime);

        //when
        underTest.savePost(postDTO);

        when(mapper.map(postDTO)).thenReturn(expectedPost);

        //then

        ArgumentCaptor<PostEntity> postEntityArgumentCaptor =
                ArgumentCaptor.forClass(PostEntity.class);

        ArgumentCaptor<PostDTO> postDTOArgumentCaptor =
                ArgumentCaptor.forClass(PostDTO.class);

        verify(mapper).map(postDTOArgumentCaptor.capture());


        verify(postRepository).save(postEntityArgumentCaptor.capture());

       // Verifier le mapage des mockMappage

       PostEntity capturedPost = mapper.map(postDTOArgumentCaptor.getValue()) ;

        assertThat(capturedPost).isEqualTo(expectedPost);

    }

    @Test
    void desactivePost() {
        // given
        List<PostEntity> posts = new ArrayList<>();
        PostEntity post1 = new PostEntity();
        post1.setActivity(true);
        posts.add(post1);
        PostEntity post2 = new PostEntity();
        post2.setActivity(true);
        posts.add(post2);



        when(postRepository.findAllByDateRegionDepartLessThanAndActivityIsTrue(any(LocalDateTime.class))).thenReturn(posts);

        // when

        underTest.DesactivePost();

        //then

        verify(postRepository, times(1)).findAllByDateRegionDepartLessThanAndActivityIsTrue(any(LocalDateTime.class));
        verify(postRepository, times(2)).save(any(PostEntity.class));

        assertFalse(post1.isActivity());
        assertFalse(post2.isActivity());


    }
}