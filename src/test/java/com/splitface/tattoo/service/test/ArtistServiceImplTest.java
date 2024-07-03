package com.splitface.tattoo.service.test;


import com.splitface.tattoo.exception.exceptions.ArtistIdDoesNotExistException;
//>>>>>>> main
import com.splitface.tattoo.exception.exceptions.EmptyArtistTableException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.repository.ArtistRepository;
import com.splitface.tattoo.service.ArtistServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ArtistServiceImplTest {


    @Mock
    ArtistRepository artistRepository;

    @InjectMocks
    ArtistServiceImpl artistServiceImpl;

    Artist scott;
    Artist jackson;

    @BeforeEach
    void setup(){
        scott = new Artist(1L,"Scott Stirling" ,
                "location",
                "Scott@outlook.com", "1%sdfdsf", null);

        jackson = new Artist(4L,"Jackson" ,
                "location",
                "Jackson@Outlook.com", "145sdf", null);
    }

    @Test
    void getAllArtistNonEmpty() throws EmptyArtistTableException {
        List<Artist> artistList = new ArrayList<>();

        artistList.add(scott);
        artistList.add(jackson);

        when(artistRepository.findAll()).thenReturn(artistList);

        assertEquals(artistList, artistServiceImpl.getAllArtist());
    }

    @Test
    void getAllArtistEmpty() throws EmptyArtistTableException {
        List<Artist> artistList = new ArrayList<>();

        when(artistRepository.findAll()).thenReturn(artistList);

        assertThrows(EmptyArtistTableException.class,() -> artistServiceImpl.getAllArtist());
    }

    @Test
    void getArtistByIdNonEmpty(){
        when(artistRepository.findById(1L)).thenReturn(Optional.ofNullable(scott));
        when(artistRepository.findById(4L)).thenReturn(Optional.ofNullable(jackson));

        assertEquals(scott, artistServiceImpl.getArtistById(1L));
        assertEquals(jackson, artistServiceImpl.getArtistById(4L));
    }

    @Test
    void getArtistByIdEmpty(){
        when(artistRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ArtistIdDoesNotExistException.class, () -> artistServiceImpl.getArtistById(2L));
    }

    @Test
    void deleteArtistById(){
        Optional<Artist> empty = Optional.empty();

        when(artistRepository.findById(1L)).thenReturn(Optional.ofNullable(scott));
        when(artistRepository.findById(4L)).thenReturn(Optional.ofNullable(jackson));
        when(artistRepository.findById(2L)).thenReturn(empty);

        artistServiceImpl.deleteArtistById(1L);
        artistServiceImpl.deleteArtistById(4L);

        assertThrows(ArtistIdDoesNotExistException.class, () -> artistServiceImpl.deleteArtistById(2L));
        verify(artistRepository, times(1)).delete(scott);
        verify(artistRepository, times(1)).delete(jackson);










    }

}