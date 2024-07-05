package com.splitface.tattoo.validation.tattooValidation;

import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.PostcodeValidatorException;
import com.splitface.tattoo.exception.tattooCheck.tattooCheckExceptions.InvalidHoursWorkedException;
import com.splitface.tattoo.exception.tattooCheck.tattooCheckExceptions.InvalidPriceException;

import java.util.regex.Pattern;

public class TattooCheckImpl implements TattooCheck {


    @Override
    public boolean checkWorkedHours(String hours) {
        String regExpression = ("\\d");
        if(!Pattern.compile(regExpression).matcher(hours).matches()){
            throw new InvalidHoursWorkedException("Invalid work hours exception pleas only input an integer");
        }
        return true;
    }

    @Override
    public boolean checkPrice(String price) {
        String regExpression = ("^[1-9]\\d*$");
        if(!Pattern.compile(regExpression).matcher(price).matches()){
            throw new InvalidPriceException("Please give an integer to represent the pric.");
        }
        return true;
    }
}
