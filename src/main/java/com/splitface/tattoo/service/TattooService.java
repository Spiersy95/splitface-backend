package com.splitface.tattoo.service;

import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.repository.TattooRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TattooService {

    List<Tattoo> getAllTattoos();
    List<Tattoo> getTattoosByStyleId(Long id);

    List<Tattoo> getTattoosByArtist(Long artistId);
    Tattoo addTattooInDb(Tattoo tattoo, Long artistId);
    void deleteTattooById(Long id);
}
