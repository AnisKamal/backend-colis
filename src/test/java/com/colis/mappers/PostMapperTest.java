package com.colis.mappers;

import com.colis.dto.PostDTO;
import com.colis.dto.UserDTO;
import com.colis.entities.PostEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostMapperTest {

    @Test
    public void map_PostEntityToPostDTO_ReturnsPostDTO() {
        //given
        PostEntity post = createTestPostEntity();

        //when
        PostDTO postDTO = PostMapper.INSTANCE.map(post);

        //then
        assertThat(postDTO).isNotNull();
        assertThat(postDTO.regionDepart()).isEqualTo("Paris");
        assertThat(postDTO.regionDestination()).isEqualTo("NewYork");
        assertThat(postDTO.prix()).isEqualTo(20);
        assertThat(postDTO.devise()).isEqualTo("EUR");
        assertThat(postDTO.kiloInitial()).isEqualTo(10);
        assertThat(postDTO.kiloRestant()).isEqualTo(10);
        assertThat(postDTO.activity()).isEqualTo(true);
        assertThat(postDTO.dateRegionDepart()).isEqualTo(createTestLocalDateTime());
        assertThat(postDTO.dateRegionDestination()).isEqualTo(createTestLocalDateTime());

    }

    @Test
    void map_PostDTOToPostEntity_ReturnsPostEntity() {
        // given
        PostDTO postDTO = createTestPostDTO();
        //when
        PostEntity postEntity = PostMapper.INSTANCE.map(postDTO);

        //then
        assertThat(postEntity).isNotNull();
        assertThat(postEntity.getRegionDepart()).isEqualTo("Paris");
        assertThat(postEntity.getRegionDestination()).isEqualTo("Douala");
        assertThat(postEntity.getPrix()).isEqualTo(20);
        assertThat(postEntity.getDevise()).isEqualTo("EUR");
        assertThat(postEntity.getKiloInitial()).isEqualTo(10);
        assertThat(postEntity.getKiloRestant()).isEqualTo(10);
        assertThat(postEntity.isActivity()).isEqualTo(true);
        assertThat(postEntity.getDateRegionDepart()).isEqualTo(createTestLocalDateTime());
        assertThat(postEntity.getDateRegionDestination()).isEqualTo(createTestLocalDateTime());
        assertThat(postEntity.getUser().getName()).isEqualTo("test");
        assertThat(postEntity.getUser().getEmail()).isEqualTo("test@email.com");
        assertThat(postEntity.getUser().getUrlPhoto()).isEqualTo("testUrl");
    }

    @Test
    public void map_ListPostEntityToListPostDTO_ReturnsListPostDTO() {
        //given
        List<PostEntity> postEntities = Arrays.asList(createTestPostEntity(), createTestPostEntity());

        //when
        List<PostDTO> postDTOs = PostMapper.INSTANCE.map(postEntities);

        //then
        assertThat(postEntities.size()).isEqualTo(postDTOs.size());
        assertThat(postDTOs.get(1).regionDepart()).isEqualTo("Paris");
        assertThat(postDTOs.get(1).regionDestination()).isEqualTo("NewYork");
        assertThat(postDTOs.get(1).prix()).isEqualTo(20);
        assertThat(postDTOs.get(1).devise()).isEqualTo("EUR");
        assertThat(postDTOs.get(1).kiloInitial()).isEqualTo(10);
        assertThat(postDTOs.get(1).kiloRestant()).isEqualTo(10);
        assertThat(postDTOs.get(1).activity()).isEqualTo(true);
        assertThat(postDTOs.get(1).dateRegionDepart()).isEqualTo(createTestLocalDateTime());
        assertThat(postDTOs.get(1).dateRegionDestination()).isEqualTo(createTestLocalDateTime());

        assertThat(postDTOs.get(0)).isEqualTo(postDTOs.get(1));
    }

    private PostEntity createTestPostEntity(){
        LocalDateTime dateTime = createTestLocalDateTime();

        //given
        PostEntity post = new PostEntity();
        post.setRegionDepart("Paris");
        post.setRegionDestination("NewYork");
        post.setPrix(20);
        post.setDevise("EUR");
        post.setKiloInitial(10);
        post.setKiloRestant(10);
        post.setActivity(true);
        post.setDateRegionDepart(dateTime);
        post.setDateRegionDestination(dateTime);

        return post;
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

    private LocalDateTime createTestLocalDateTime(){
        String str = "2023-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
    }
}
