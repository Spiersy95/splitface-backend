package com.splitface.tattoo.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitface.tattoo.controller.ArtistController;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.service.ArtistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class ArtistControllerTest {

    @Mock
    private ArtistServiceImpl mockArtistServiceImpl;

    @InjectMocks
    private ArtistController artistController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;


    Artist scott;
    Artist jackson;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(artistController).build();
        mapper = new ObjectMapper();
         scott = new Artist(1L,"Scott Stirling" ,
                "location",
                "Scott@outlook.com", "1%sdfdsf", null);

         jackson = new Artist(4L,"Jackson" ,
                 "location",
                 "Jackson@Outlook.com", "145sdf", null);

    }

    @Test
    @DisplayName("GET all artist non-empty table")
    void getAllArtistNonEmptyDatabase() throws Exception{

        List<Artist> artists = new ArrayList<>();

        artists.add(scott);
        artists.add(jackson);

        when(mockArtistServiceImpl.getAllArtist()).thenReturn(artists);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/artist/"))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Scott Stirling"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value("location"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jackson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].location").value("location"));




    }



}
