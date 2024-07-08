package com.splitface.tattoo.validation.styleValidation;

import com.splitface.tattoo.exception.styleCheck.styleCheckExceptions.StyleNotContainedInListException;
import com.splitface.tattoo.models.Style;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StyleValidationTest {

    @Test
    void validateStylesAreInListTest(){
        StyleCheck styleCheck = new StyleCheckImpl();

        Style style1 = new Style(1L, "sdlfkjdsj", null);
        Style style2 = new Style(2L, "ssdfj", null);
        Style style3 = new Style(3L, "dsj", null);
        Style style4 = new Style(4L, "HELLOO", null);

        List<Style> smallerList1 = new ArrayList<>(List.of(style1, style2));

        List<Style> smallerList2 = new ArrayList<>(List.of(style2, style4));

        List<Style> smallerList3 = new ArrayList<>(List.of(style3));

        List<Style> largerList = new ArrayList<>(List.of(style1,style2,style4));
        System.out.println(smallerList1);
        System.out.println(largerList);

        assertTrue(styleCheck.validateStylesAreInList(smallerList1, largerList));
        assertTrue(styleCheck.validateStylesAreInList(smallerList2, largerList));



        assertThrows(StyleNotContainedInListException.class, () -> styleCheck.validateStylesAreInList(smallerList3, largerList));
    }
}
