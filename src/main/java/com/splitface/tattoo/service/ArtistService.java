package com.splitface.tattoo.service;

import com.splitface.tattoo.models.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtist();
    String addArtist(Artist artist);
    List<String> getListOfEmails();
    Artist getArtistByEmail(String email);
}
