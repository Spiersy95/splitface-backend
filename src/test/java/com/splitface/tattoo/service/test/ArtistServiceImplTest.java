package com.splitface.tattoo.service.test;

//<<<<<<< Scott-Get-Tattoos-By-Style
//=======
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
import static org.mockito.Mockito.when;

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
    public void getAllArtistNonEmpty() throws EmptyArtistTableException {
        List<Artist> artistList = new ArrayList<>();

        artistList.add(scott);
        artistList.add(jackson);

        when(artistRepository.findAll()).thenReturn(artistList);

        assertEquals(artistList, artistServiceImpl.getAllArtist());
    }

    @Test
    public void getAllArtistEmpty() throws EmptyArtistTableException {
        List<Artist> artistList = new ArrayList<>();

        when(artistRepository.findAll()).thenReturn(artistList);

        assertThrows(EmptyArtistTableException.class,() -> artistServiceImpl.getAllArtist());
    }

    @Test
    public void getArtistByIdNonEmpty(){
        when(artistRepository.findById(1L)).thenReturn(Optional.ofNullable(scott));
        when(artistRepository.findById(4L)).thenReturn(Optional.ofNullable(jackson));

        assertEquals(scott, artistServiceImpl.getArtistById(1L));
        assertEquals(jackson, artistServiceImpl.getArtistById(4L));
    }

    @Test
    public void getArtistByIdEmpty(){
        when(artistRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(ArtistIdDoesNotExistException.class, () -> artistServiceImpl.getArtistById(2L));
    }

}