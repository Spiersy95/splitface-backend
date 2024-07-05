package com.splitface.tattoo.validation.styleValidation;

import com.splitface.tattoo.exception.service.serviceExceptions.InvalidStyleException;
import com.splitface.tattoo.models.Style;

import java.util.HashSet;
import java.util.List;

public class StyleCheckImpl implements StyleCheck {
    @Override
    public boolean validateStylesAreInList(List<Style> smallerList, List<Style> largerList) {
        if(!new HashSet<>(largerList).containsAll(smallerList)){
            throw new InvalidStyleException("sorry one of the styles you have inputted is incorrect");
        }
        return true;
    }
}
