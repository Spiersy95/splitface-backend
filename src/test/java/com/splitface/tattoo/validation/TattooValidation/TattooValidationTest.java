package com.splitface.tattoo.validation.TattooValidation;

import com.splitface.tattoo.exception.tattooCheck.tattooCheckExceptions.InvalidHoursWorkedException;
import com.splitface.tattoo.exception.tattooCheck.tattooCheckExceptions.InvalidPriceException;
import com.splitface.tattoo.validation.tattooValidation.TattooCheck;
import com.splitface.tattoo.validation.tattooValidation.TattooCheckImpl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TattooValidationTest {

    TattooCheck tattooCheck = new TattooCheckImpl();

    @Test
    void checkWorkedHoursTest(){
        String correctInput1 = "5";
        String correctInput2 = "50";
        String correctInput3 = "210";
        String correctInput4 = "406";

        String incorrectInput1 = "fsdklfjdslkfj";
        String incorrectInput2 = "£50";
        String incorrectInput3 = "dsjlskdjf43t34";
        String incorrectInput4 = "4435,435";

        assertTrue(tattooCheck.checkWorkedHours(correctInput1));
        assertTrue(tattooCheck.checkWorkedHours(correctInput2));
        assertTrue(tattooCheck.checkWorkedHours(correctInput3));
        assertTrue(tattooCheck.checkWorkedHours(correctInput4));

        assertThrows(InvalidHoursWorkedException.class, () -> tattooCheck.checkWorkedHours(incorrectInput1));
        assertThrows(InvalidHoursWorkedException.class, () -> tattooCheck.checkWorkedHours(incorrectInput2));
        assertThrows(InvalidHoursWorkedException.class, () -> tattooCheck.checkWorkedHours(incorrectInput3));
        assertThrows(InvalidHoursWorkedException.class, () -> tattooCheck.checkWorkedHours(incorrectInput4));


    }

    @Test
    void checkPriceTest(){
        String correctInput1 = "5";
        String correctInput2 = "50";
        String correctInput3 = "210";
        String correctInput4 = "406";

        String incorrectInput1 = "£5";
        String incorrectInput2 = "dsfkdsjflk";
        String incorrectInput3 = "dslkfjds(dsklj";
        String incorrectInput4 = "£400";

        assertTrue(tattooCheck.checkPrice(correctInput1));
        assertTrue(tattooCheck.checkPrice(correctInput2));
        assertTrue(tattooCheck.checkPrice(correctInput3));
        assertTrue(tattooCheck.checkPrice(correctInput4));

        assertThrows(InvalidPriceException.class, () -> tattooCheck.checkPrice(incorrectInput1));
        assertThrows(InvalidPriceException.class, () -> tattooCheck.checkPrice(incorrectInput2));
        assertThrows(InvalidPriceException.class, () -> tattooCheck.checkPrice(incorrectInput3));
        assertThrows(InvalidPriceException.class, () -> tattooCheck.checkPrice(incorrectInput4));
    }
}
