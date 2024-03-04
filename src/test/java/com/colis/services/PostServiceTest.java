package com.colis.services;

import com.colis.dto.PostDTO;
import com.colis.dto.UserDTO;
import com.colis.entities.PostEntity;
import com.colis.entities.UserEntity;
import com.colis.mappers.PostMapper;
import com.colis.repositories.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
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
    public void canSavePost() {
        //given
        PostDTO postDTO = createTestPostDTO();
        PostEntity expectedPost = createTestPostEntity();

        //when
        when(mapper.map(postDTO)).thenReturn(expectedPost);
        underTest.savePost(postDTO);

        //then
        ArgumentCaptor<PostEntity> postEntityArgumentCaptor = ArgumentCaptor.forClass(PostEntity.class);
        ArgumentCaptor<PostDTO> PostDTOArgumentCaptor = ArgumentCaptor.forClass(PostDTO.class);
        verify(mapper).map(PostDTOArgumentCaptor.capture());
        verify(postRepository).save(postEntityArgumentCaptor.capture());

        // Verifier le mapage des mockMappage
        assertThat(postEntityArgumentCaptor.getValue()).isEqualTo(expectedPost);
    }

    @Test
    public void canFindPostByUser(){
        //given
        String id = "1";
        List<PostDTO> expectedPostDTOs = Arrays.asList(createTestPostDTO(), createTestPostDTO());
        List<PostEntity> postEntities = Arrays.asList(createTestPostEntity(), createTestPostEntity());

        //when
        when(postRepository.findAllByUser_IdOrderByCreatedDateDesc(id)).thenReturn(postEntities);
        when(mapper.map(postEntities)).thenReturn(expectedPostDTOs);
        List<PostDTO> result = underTest.findPostByUser(id);

        //then
        ArgumentCaptor<String> userIdCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<List<PostEntity>> postEntitiesCaptor = ArgumentCaptor.forClass(List.class);

        verify(mapper).map(postEntitiesCaptor.capture());
        verify(postRepository).findAllByUser_IdOrderByCreatedDateDesc(userIdCaptor.capture());

        assertThat(result).isEqualTo(expectedPostDTOs);
    }

    @Test
    public void canFindLastPost(){
        // given
        List<PostDTO> expectedPostDTOs = Arrays.asList(createTestPostDTO(), createTestPostDTO());
        List<PostEntity> postEntities = Arrays.asList(createTestPostEntity(), createTestPostEntity());

        // when
        when(postRepository.findAllByActivityIsTrueOrderByCreatedDateDesc()).thenReturn(postEntities);
        when(mapper.map(postEntities)).thenReturn(expectedPostDTOs);
        List<PostDTO> result = underTest.findLastPosts();

        //then
        ArgumentCaptor<List<PostEntity>> postEntitiesCaptor = ArgumentCaptor.forClass(List.class);

        verify(mapper).map(postEntitiesCaptor.capture());
        verify(postRepository).findAllByActivityIsTrueOrderByCreatedDateDesc();
        assertThat(result).isEqualTo(expectedPostDTOs);

    }

    @Test
    public void canFindPostSearch_DepartAndDestination(){
        //given
        String depart = "testDepart";
        String destination = "testDestination";
        List<PostDTO> expectedPostDTOs = Arrays.asList(createTestPostDTO(), createTestPostDTO());
        List<PostEntity> postEntities = Arrays.asList(createTestPostEntity(), createTestPostEntity());

        //when
        when(postRepository.findAllByRegionDepartAndRegionDestinationAndActivityIsTrue(depart, destination)).thenReturn(postEntities);
        when(mapper.map(postEntities)).thenReturn(expectedPostDTOs);
        List<PostDTO> result = underTest.findPostSearch(depart,destination);

        //then
        ArgumentCaptor<List<PostEntity>> postEntitiesCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mapper).map(postEntitiesCaptor.capture());
        verify(postRepository).findAllByRegionDepartAndRegionDestinationAndActivityIsTrue(stringArgumentCaptor.capture(), stringArgumentCaptor.capture());

        assertThat(result).isEqualTo(expectedPostDTOs);
    }

    @Test
    public void canFindPostSearch_Depart() {
        //given
        String depart = "testDepart";
        String destination = "";
        List<PostDTO> expectedPostDTOs = Arrays.asList(createTestPostDTO(), createTestPostDTO());
        List<PostEntity> postEntities = Arrays.asList(createTestPostEntity(), createTestPostEntity());

        //when
        when(postRepository.findAllByRegionDepartAndActivityIsTrue(depart)).thenReturn(postEntities);
        when(mapper.map(postEntities)).thenReturn(expectedPostDTOs);
        List<PostDTO> result = underTest.findPostSearch(depart, destination);

        //then
        ArgumentCaptor<List<PostEntity>> postEntitiesCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mapper).map(postEntitiesCaptor.capture());
        verify(postRepository).findAllByRegionDepartAndActivityIsTrue(stringArgumentCaptor.capture());

        assertThat(result).isEqualTo(expectedPostDTOs);
    }

    @Test
    public void canFindPostSearch_Destination(){
        //given
        String depart = "";
        String destination = "testDestination";
        List<PostDTO> expectedPostDTOs = Arrays.asList(createTestPostDTO(), createTestPostDTO());
        List<PostEntity> postEntities = Arrays.asList(createTestPostEntity(), createTestPostEntity());

        //when
        when(postRepository.findAllByRegionDestinationAndActivityIsTrue(destination)).thenReturn(postEntities);
        when(mapper.map(postEntities)).thenReturn(expectedPostDTOs);
        List<PostDTO> result = underTest.findPostSearch(depart, destination);

        //then
        ArgumentCaptor<List<PostEntity>> postEntitiesCaptor = ArgumentCaptor.forClass(List.class);
        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mapper).map(postEntitiesCaptor.capture());
        verify(postRepository).findAllByRegionDestinationAndActivityIsTrue(stringArgumentCaptor.capture());

        assertThat(result).isEqualTo(expectedPostDTOs);
    }

    @Test
    public void canFindPostSearch_(){
        //given
        String depart = "";
        String destination = "";
        List<PostDTO> expectedPostDTOs = Arrays.asList(createTestPostDTO(), createTestPostDTO());
        List<PostEntity> postEntities = Arrays.asList(createTestPostEntity(), createTestPostEntity());

        //when
        when(postRepository.findAllByActivityIsTrueOrderByCreatedDateDesc()).thenReturn(postEntities);
        when(mapper.map(postEntities)).thenReturn(expectedPostDTOs);
        List<PostDTO> result = underTest.findPostSearch(depart, destination);

        //then
        ArgumentCaptor<List<PostEntity>> postEntitiesCaptor = ArgumentCaptor.forClass(List.class);
        verify(mapper).map(postEntitiesCaptor.capture());
        verify(postRepository).findAllByActivityIsTrueOrderByCreatedDateDesc();
        assertThat(result).isEqualTo(expectedPostDTOs);
    }

    @Test
    public void canDesactivePost(){
        //given
        List<PostEntity> posts = new ArrayList<>(Arrays.asList(createTestPostEntity(), createTestPostEntity())) ;
        when(postRepository.findAllByDateRegionDepartLessThanAndActivityIsTrue(any(LocalDateTime.class))).thenReturn(posts);
        //when

        underTest.DesactivePost();

        //then
        verify(postRepository,times(1)).findAllByDateRegionDepartLessThanAndActivityIsTrue(any(LocalDateTime.class));
        verify(postRepository, times(2)).save(any(PostEntity.class));
        assertFalse(posts.get(0).isActivity());
        assertFalse(posts.get(1).isActivity());
    }


    private LocalDateTime createTestLocalDateTime(){
        String str = "2023-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(str, formatter);
    }

    private PostDTO createTestPostDTO(){
        LocalDateTime dateTime = createTestLocalDateTime();
        UserDTO userDTO = new UserDTO("1",
                "test@email.com",
                "test",
                "testUrl");

        PostDTO postDTO = new PostDTO("1",
                "Paris",
                dateTime,
                "Douala",
                dateTime,
                20,
                "EUR",
                10,
                10,
                true,
                "desc",
                "06",
                userDTO
        );
        return postDTO;
    }

    private PostEntity createTestPostEntity(){
        LocalDateTime dateTime = createTestLocalDateTime();

        //given
        PostEntity post = new PostEntity();
        UserEntity user = new UserEntity();
        user.setId("1");
        user.setName("test");
        user.setEmail("test@email.com");
        user.setUrlPhoto("testUrl");
        post.setRegionDepart("Paris");
        post.setRegionDestination("Douala");
        post.setPrix(20);
        post.setDevise("EUR");
        post.setKiloInitial(10);
        post.setKiloRestant(10);
        post.setActivity(true);
        post.setDateRegionDepart(dateTime);
        post.setDateRegionDestination(dateTime);
        post.setId("1");
        post.setDescription("desc");
        post.setNTelephone("06");
        post.setUser(user);

        return post;
    }
}
