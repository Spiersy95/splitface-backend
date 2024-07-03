package com.splitface.tattoo.service;

import com.splitface.tattoo.models.Style;

import java.util.List;

public interface StyleService {
    List<Style> getAllStylesFromDB();
    void addStylesForTattoo(Long tattooId, List<String> styleNames);
}
