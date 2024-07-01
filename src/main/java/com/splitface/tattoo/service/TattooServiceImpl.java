package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.exceptions.EmptyTattooTableException;
import com.splitface.tattoo.exception.exceptions.TattooMatchingStyleIdException;
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

    @Override
    public List<Tattoo> getTattoosByStyleId(Long id) {
        List<Tattoo> tattooList = new ArrayList<>();
        tattooRepository.getTattoosByStyleId(id).forEach(tattooList::add);
        if (tattooList.isEmpty()){
            throw new TattooMatchingStyleIdException("There are no tattoos matching that style in the database");
        }
        return tattooList;
    }


}
