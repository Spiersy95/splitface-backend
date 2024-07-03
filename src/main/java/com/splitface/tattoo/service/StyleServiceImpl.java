package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.exceptions.ArtistIdDoesNotExistException;
import com.splitface.tattoo.exception.exceptions.EmptyStyleTableException;
import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.repository.StyleRepository;
import com.splitface.tattoo.repository.TattooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

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
        addStyleInDbIfNotExist(styleNames);
        List<Style> styleList = new ArrayList<>();
        for (String st:styleNames) {
            Style style = new Style();
            style = styleRepository.getStyleByStyleName(st);
            if (isNull(style)){
                System.out.println("is null");
            }
            styleList.add(style);
        }
        tattoo.setStyles(styleList);
        tattooRepository.save(tattoo);
    }


    //additional functions
    private void addStyleInDbIfNotExist(List<String> styleNames) {
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


}
