package com.splitface.tattoo.service.serviceImpl;

import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.repository.ArtistRepository;
import com.splitface.tattoo.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist createArtistInDb(Artist artist) {
        return null;
    }

    public List<Artist> getAllArtist() {
        List<Artist> artistList = new ArrayList<>();
        artistRepository.findAll().forEach(artistList::add);
        return artistList;
    }
}
