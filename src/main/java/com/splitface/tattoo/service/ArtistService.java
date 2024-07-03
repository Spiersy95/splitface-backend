package com.splitface.tattoo.service;

import com.splitface.tattoo.models.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtist();
    Artist createArtistInDb(Artist artist);
    Artist getArtistById(long id);

    String addArtist(Artist artist);
    List<String> getListOfEmails();
    Artist getArtistByEmail(String email);

    Artist editArtist(Long artistId, Artist newArtist);
}
