package com.splitface.tattoo.service.test;

import com.splitface.tattoo.exception.service.serviceExceptions.StyleAlreadyInDbException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.repository.ArtistRepository;
import com.splitface.tattoo.repository.StyleRepository;
import com.splitface.tattoo.service.ArtistServiceImpl;
import com.splitface.tattoo.service.StyleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@SpringBootTest
public class StyleServiceImplTest {


    @Mock
    StyleRepository styleRepository;

    @InjectMocks
    StyleServiceImpl styleServiceImpl;

    Style realism;
    Style watercolour;

    @BeforeEach
    void setup(){
        realism = new Style(1L,"realism", null);
        watercolour = new Style(2L,"watercolour", null);
    }

    @Test
    void getAllStylesFromDB(){
        List<Style> styles = new ArrayList<>();
        styles.add(realism);
        styles.add(watercolour);

        when(styleRepository.findAll()).thenReturn(styles);

        styleServiceImpl.getAllStylesFromDB();

        verify(styleRepository, times(1)).findAll();
    }

    @Test
    void addStyleToDbIfNotExistTest(){
        when(styleRepository.getStyleByStyleName(realism.getStyleName())).thenReturn(Optional.empty());
        styleServiceImpl.addStyleToDbIfNotExist(realism);
        verify(styleRepository, times(1)).save(realism);
    }

    @Test
    void addStyleToDbIfNotExistTestThrows(){
        when(styleRepository.getStyleByStyleName(realism.getStyleName())).thenReturn(Optional.of(realism));

        assertThrows(StyleAlreadyInDbException.class,() ->styleServiceImpl.addStyleToDbIfNotExist(realism));


    }
}
