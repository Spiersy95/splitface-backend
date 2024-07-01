package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.exceptions.EmptyStyleTableException;
import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StyleServiceImpl implements StyleService{
    @Autowired
    StyleRepository styleRepository;

    @Override
    public List<Style> getAllStylesFromDB() {
        List<Style> styleList = new ArrayList<>();
        styleRepository.findAll().forEach(styleList::add);
        return styleList;
    }
}
