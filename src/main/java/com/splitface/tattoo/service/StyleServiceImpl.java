package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.service.serviceExceptions.ArtistIdDoesNotExistException;
import com.splitface.tattoo.exception.service.serviceExceptions.StyleAlreadyInDbException;
import com.splitface.tattoo.exception.service.serviceExceptions.StyleNotInDatabaseException;
import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.repository.StyleRepository;
import com.splitface.tattoo.repository.TattooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class StyleServiceImpl implements StyleService{
    @Autowired
    StyleRepository styleRepository;
    @Autowired
    TattooRepository tattooRepository;

    @Override
    public List<Style> getAllStylesFromDB() {
        List<Style> styleList = new ArrayList<>();
        styleRepository.findAll().forEach(styleList::add);
        return styleList;
    }

    @Override
    public void addStylesForTattoo(Long tattooId, List<String> styleNames) {
        Tattoo tattoo = tattooRepository.findById(tattooId)
                .orElseThrow(()->new ArtistIdDoesNotExistException("no tattoo under this id"));
        addStylesInDbIfNotExist(styleNames);
        List<Style> styleList = new ArrayList<>();
        for (String st:styleNames) {
            Optional<Style> optionalStyle = styleRepository.getStyleByStyleName(st);
            if (optionalStyle.isEmpty()){
                throw new StyleNotInDatabaseException(String.format("Sorry the style named %s is not in the database", st));
            } else {
                styleList.add(optionalStyle.get());
            }
        }
        tattoo.setStyles(styleList);
        tattooRepository.save(tattoo);
    }


    //additional functions
    public void addStylesInDbIfNotExist(List<String> styleNames) {
        List<Style> styleListFromDb = getAllStylesFromDB(); //styles from DB
        List<String> nameStylesFromDb = new ArrayList<>();  //name of styles from DB
        for (Style st:styleListFromDb){
            nameStylesFromDb.add(st.getStyleName());
        }
        for (String st: styleNames){
            if (!nameStylesFromDb.contains(st)){
                Style style = new Style();
                style.setStyleName(st);
                styleRepository.save(style);
            }
        }
    }

    public void addStyleToDbIfNotExist(Style style){
        Optional<Style> optStyle = styleRepository.getStyleByStyleName(style.getStyleName());
        if(optStyle.isPresent()){
            throw new StyleAlreadyInDbException("This style already exists in the db");
        }
        styleRepository.save(style);
    }



}
