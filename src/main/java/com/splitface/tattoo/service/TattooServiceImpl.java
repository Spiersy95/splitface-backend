package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.exceptions.*;

import com.splitface.tattoo.exception.exceptions.EmptyTattooTableException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.repository.ArtistRepository;
import com.splitface.tattoo.repository.TattooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TattooServiceImpl implements TattooService {

    @Autowired
    TattooRepository tattooRepository;
    @Autowired
    ArtistRepository artistRepository;

    @Override
    public List<Tattoo> getAllTattoos() {
        List<Tattoo> tattooList = new ArrayList<>();
        tattooRepository.findAll().forEach(tattooList::add);
        if (tattooList.isEmpty()){
            throw new EmptyTattooTableException("There are no tattoos");
        }
        return tattooList;
    }

    @Override
    public List<Tattoo> getTattoosByStyleId(Long id) {
        List<Tattoo> tattooList = new ArrayList<>();
        tattooRepository.getTattoosByStyleId(id).forEach(tattooList::add);
        if (tattooList.isEmpty()){
            throw new TattooMatchingStyleIdException("There are no tattoos matching that style in the database");
        }
        return tattooList;
    }


    @Override
    public List<Tattoo> getTattoosByArtist(Long artistId) {
        return new ArrayList<>(tattooRepository.getTattoosByArtistId(artistId));
    }

    @Override
    public Tattoo addTattooInDb(Tattoo tattoo, Long artistId){
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(()->new ArtistIdDoesNotExistException("no such id"));
        List<Tattoo> tattoos = new ArrayList<>(getTattoosByArtist(artistId));
        /// design field is using to hold a URL og image
        String tattooURL = tattoo.getDesign();
        boolean isPresent = false;
        for (Tattoo ta:tattoos) {
            if (ta.getDesign().equals(tattooURL)) {
                isPresent = true;
                break;
            }
        }
        if (!isPresent){
            tattoo.setArtist(artist);
            tattooRepository.save(tattoo);
            return tattoo;
        }else return null;
    }

    public void deleteTattooById(Long id){
        Optional<Tattoo> tattoo = tattooRepository.findById(id);
        if (tattoo.isEmpty()){
            throw new TattooIdDoesNotExistException("Tattoo id does not exist in database");
        }
        tattooRepository.delete(tattoo.get());
    }

    @Override
    public Artist getArtistByTattooId(Long tattooId) {
        Long artistId = tattooRepository.getArtistId(tattooId);
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(()->new ArtistIdDoesNotExistException("no id"));
        return artist;
    }

}
