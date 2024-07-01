package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.exceptions.ArtistIdDoesNotExistException;
import com.splitface.tattoo.exception.exceptions.EmptyArtistTableException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistRepository artistRepository;


    @Override
    public Artist createArtistInDb(Artist artist) {
        return null;
    }

    @Override
    public Artist getArtistById(long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty()){
            throw new ArtistIdDoesNotExistException("Sorry this id does not match anyone we have in the database");
        }
        return artist.get();
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
