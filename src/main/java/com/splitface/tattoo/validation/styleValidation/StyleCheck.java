package com.splitface.tattoo.validation.styleValidation;

import com.splitface.tattoo.models.Style;

import java.util.List;

public interface StyleCheck {

    boolean validateStylesAreInList(List<Style> smallerList, List<Style> largerList);


}
