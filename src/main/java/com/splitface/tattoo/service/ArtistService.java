package com.splitface.tattoo.service;

import com.splitface.tattoo.models.Artist;

import java.util.List;

public interface ArtistService {

    Artist createArtistInDb(Artist artist);

    List<Artist> getAllArtist();
}
