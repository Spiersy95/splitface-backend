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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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


    private Artist scott;
    private Artist jackson;

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
                MockMvcRequestBuilders.get("/artist/artists"))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Scott Stirling"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value("location"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jackson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].location").value("location"));





    }

    @Test
    @DisplayName("GET artist by ID from artistTable")
    void getArtistByIdTest() throws Exception {


        when(mockArtistServiceImpl.getArtistById(1)).thenReturn(scott);
        when(mockArtistServiceImpl.getArtistById(4L)).thenReturn(jackson);

        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/artist/1"))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Scott Stirling"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("location"));
        this.mockMvcController.perform(
                MockMvcRequestBuilders.get("/artist/4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(4L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jackson"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("location"));




    }

    @Test
    @DisplayName("DELETE artist by ID")
    void deleteArtistById() throws Exception {

        String expectedString = "The artist with id: 1 has been deleted";


        MvcResult result = this.mockMvcController.perform(
                        MockMvcRequestBuilders.delete("/artist/1"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        assertEquals(expectedString, content);

        verify(mockArtistServiceImpl, times(1)).deleteArtistById(1L);


    }

}
