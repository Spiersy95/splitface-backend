package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.exceptions.EmptyArtistTableException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistRepository artistRepository;


    @Override
    public Artist createArtistInDb(Artist artist) {
        return null;
    }

    public List<Artist> getAllArtist() {
        List<Artist> artistList = new ArrayList<>();
        artistRepository.findAll().forEach(artistList::add);
        if (artistList.isEmpty()){
            throw new EmptyArtistTableException("There are no artist");
        }
        return artistList;
    }
}
