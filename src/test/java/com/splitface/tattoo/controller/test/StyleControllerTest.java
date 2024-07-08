package com.splitface.tattoo.controller.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.splitface.tattoo.controller.ArtistController;
import com.splitface.tattoo.controller.StyleController;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.service.ArtistServiceImpl;
import com.splitface.tattoo.service.StyleServiceImpl;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class StyleControllerTest {

    @Mock
    private StyleServiceImpl mockStyleServiceImpl;

    @InjectMocks
    private StyleController styleController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    private Style realism;

    private Style waterColour;

    private Tattoo tattoo;

    @BeforeEach
    public void setup() {
        mockMvcController = MockMvcBuilders.standaloneSetup(styleController).build();
        mapper = new ObjectMapper();
        realism = new Style(1L, "realism", null);
        waterColour = new Style(2L, "watercolour", null);
        tattoo = new Tattoo(1L, "fsf", "5", null, "5", new ArrayList<>(), null);
    }

    @Test
    @DisplayName("GET all styles non-empty table")
    void getAllArtistNonEmptyDatabase() throws Exception {

        List<Style> styles = new ArrayList<>();

        styles.add(realism);
        styles.add(waterColour);

        when(mockStyleServiceImpl.getAllStylesFromDB()).thenReturn(styles);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/style/styles"))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].styleName").value("realism"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].styleName").value("watercolour"));
    }

    @Test
    void addStyleInDBTEST() throws Exception{
        this.mockMvcController.perform(MockMvcRequestBuilders.post("/style")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(realism)))
                .andExpect(status().isAccepted())
                .andExpect(content().string("The style realism has been added"))
                .andDo(print());
    }


}
