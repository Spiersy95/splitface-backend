package com.splitface.tattoo.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.splitface.tattoo.controller.TattooController;

//<<<<<<< Scott-Get-Tattoos-By-Style
import com.splitface.tattoo.models.Style;
//=======
import com.splitface.tattoo.models.Artist;
//>>>>>>> main
import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.service.TattooServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class TattooControllerTest {


    @Mock
    private TattooServiceImpl mockTattooServiceImpl;

    @InjectMocks
    private TattooController tattooController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;


    private Tattoo tattoo1;
    private Tattoo tattoo2;


    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(tattooController).build();
        mapper = new ObjectMapper();
        tattoo1 = new Tattoo(2L, "dsfdsf", "£50", null, null);
        tattoo2 = new Tattoo(6L, "7", "£50", null, null);


        }



    @Test
    @DisplayName("GET all tattoo non-empty table")
    void getAllArtistNonEmptyDatabase() throws Exception{

        List<Tattoo> tattoos = new ArrayList<>();

        tattoos.add(tattoo1);
        tattoos.add(tattoo2);


        when(mockTattooServiceImpl.getAllTattoos()).thenReturn(tattoos);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/tattoo/tattoos"))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].design").value("dsfdsf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("£50"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(6L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].design").value("7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value("£50"));

    }

//<<<<<<< Scott-Get-Tattoos-By-Style
    @Test
    void getTattoosByStyle() throws Exception {

        List<Tattoo> tattoos = new ArrayList<>();
        tattoos.add(tattoo1);
        tattoos.add(tattoo2);

        when(mockTattooServiceImpl.getTattoosByStyleId(1L)).thenReturn(tattoos);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/tattoo/tattoos/style/1"))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].design").value("dsfdsf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value("£50"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(6L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].design").value("7"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value("£50"));


    }

    @Test
    void createTattooRecord() throws Exception{

        Artist artist = new Artist(4L,"name","location","email","password",null);

        tattoo1.setArtist(artist);
        when(mockTattooServiceImpl.addTattooInDb(tattoo1, 4L)).thenReturn(tattoo1);

        String content = mapper.writeValueAsString(tattoo1);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/tattoo/artist?id=4")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        mockMvcController.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.design").value("dsfdsf"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value("£50"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.artist.id").value(4L));

    }
}
