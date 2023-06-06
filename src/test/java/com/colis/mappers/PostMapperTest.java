package com.colis.mappers;

import com.colis.dto.PostDTO;
import com.colis.entities.PostEntity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

class PostMapperTest {
    private final PostMapper postMapper = PostMapper.INSTANCE;

    @Test
    public void map_PostEntityToPostDTO_ReturnsPostDTO() {
        String str = "2023-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
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
        assertThat(postDTO.dateRegionDepart()).isEqualTo(dateTime);
        assertThat(postDTO.dateRegionDestination()).isEqualTo(dateTime);

    }

    @Test
    void map_PostDTOToPostEntity_ReturnsPostEntity() {
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
        assertThat(postEntity.getDateRegionDepart()).isEqualTo(dateTime);
        assertThat(postEntity.getDateRegionDestination()).isEqualTo(dateTime);
    }
}