package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.EmptyArtistTableException;
import com.splitface.tattoo.exception.EmptyTattooTableException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.repository.TattooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TattooServiceImpl implements TattooService {

    @Autowired
    TattooRepository tattooRepository;

    @Override
    public List<Tattoo> getAllTattoos() {
        List<Tattoo> tattooList = new ArrayList<>();
        tattooRepository.findAll().forEach(tattooList::add);
        if (tattooList.isEmpty()){
            throw new EmptyTattooTableException("There are no tattoos");
        }
        return tattooList;
    }
}
