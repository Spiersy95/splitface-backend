package com.splitface.tattoo.service;

import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.models.Tattoo;

import java.util.List;

public interface TattooService {

    List<Tattoo> getAllTattoos();

    List<Tattoo> getTattoosByStyleId(Long id);

    List<Tattoo> getTattoosByArtist(Long artistId);

    Tattoo addTattooInDb(Tattoo tattoo, Long artistId);

    void deleteTattooById(Long id);

    Artist getArtistByTattooId(Long tattooId);

    void updateTattoo(Tattoo tattoo, Long tattooId);


}