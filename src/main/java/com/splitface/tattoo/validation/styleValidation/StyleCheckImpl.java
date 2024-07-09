package com.splitface.tattoo.validation.styleValidation;


import com.splitface.tattoo.exception.styleCheck.styleCheckExceptions.StyleNotContainedInListException;
import com.splitface.tattoo.models.Style;

import java.util.HashSet;
import java.util.List;

public class StyleCheckImpl implements StyleCheck {
    @Override
    public boolean validateStylesAreInList(List<Style> smallerList, List<Style> largerList) {
        if (smallerList == null || largerList== null) {
            throw new IllegalArgumentException("Collections should not be null");
        }
        if(!new HashSet<>(largerList).containsAll(smallerList)){
            throw new StyleNotContainedInListException("sorry one of the styles you have inputted is incorrect");
        }
        return true;
    }
}
