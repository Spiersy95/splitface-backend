package com.splitface.tattoo.service.test;



import com.splitface.tattoo.exception.exceptions.EmptyTattooTableException;
import com.splitface.tattoo.models.Tattoo;

import com.splitface.tattoo.repository.TattooRepository;

import com.splitface.tattoo.service.TattooServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TattooServiceImplTest {

    @Mock
    TattooRepository tattooRepository;

    @InjectMocks
    TattooServiceImpl tattooServiceimpl;

    Tattoo tattoo1;
    Tattoo tattoo2;

    @BeforeEach
    public void setup(){

        tattoo1 = new Tattoo(2L, "dsfdsf", "£50", null, null);
        tattoo2 = new Tattoo(6L, "7", "£50", null, null);

    }

    @Test
    public void getAllArtistNonEmpty() throws EmptyTattooTableException {
        List<Tattoo> tattooList = new ArrayList<>();

        tattooList.add(tattoo1);
        tattooList.add(tattoo2);

        when(tattooRepository.findAll()).thenReturn(tattooList);

        assertEquals(tattooList, tattooServiceimpl.getAllTattoos());
        verify(tattooRepository, times(1)).findAll();
    }

    @Test
    public void getAllArtistEmpty() throws EmptyTattooTableException {
        List<Tattoo> tattooList = new ArrayList<>();

        when(tattooRepository.findAll()).thenReturn(tattooList);

        assertThrows( EmptyTattooTableException.class,() -> tattooServiceimpl.getAllTattoos());
        verify(tattooRepository, times(1)).findAll();
    }

    @Test
    public void getTattoosByStyleIdTestNonEmpty(){
        List<Tattoo> tattoosList = new ArrayList();
        tattoosList.add(tattoo1);

        when(tattooRepository.getTattoosByStyleId(1L)).thenReturn(tattoosList);


        assertEquals(tattoosList, tattooServiceimpl.getTattoosByStyleId(1L));
        verify(tattooRepository, times(1)).getTattoosByStyleId(1L);

    }

}
