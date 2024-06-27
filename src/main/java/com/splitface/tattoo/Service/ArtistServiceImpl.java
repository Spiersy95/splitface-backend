package com.splitface.tattoo.Service;

import com.splitface.tattoo.Repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public List<Artist> getAllArtist() {
        List<Artist> artistList = new ArrayList<>();
        artistRepository.findAll().forEach(artistList::add);
        return artistList;
    }


}
