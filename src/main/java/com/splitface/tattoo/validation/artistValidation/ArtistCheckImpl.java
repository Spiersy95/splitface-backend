package com.splitface.tattoo.validation.artistValidation;
import com.splitface.tattoo.exception.exceptions.NameValidatorException;
import com.splitface.tattoo.exception.exceptions.PasswordValidatorException;

import java.util.regex.Pattern;

public class ArtistCheckImpl implements ArtistCheck {
    @Override
    public boolean checkEmail(String email) {
        String regExpression = ("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}");
        return Pattern.compile(regExpression)
                .matcher(email)
                .matches();
    }

    @Override
    public boolean checkPassword(String password) {

        if (password.length()<8) throw new PasswordValidatorException("too short password");
        if (!password.matches(".*\\d.*")) throw new PasswordValidatorException("no numbers in pass");
        Pattern pattern = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        if (!pattern.matcher(password).find()) throw new PasswordValidatorException("no special symbol");
        pattern = Pattern.compile("[a-z]");
        if (!pattern.matcher(password).find()) throw  new PasswordValidatorException("no lowercase character");
        pattern = Pattern.compile("[A-Z]");
        if (!pattern.matcher(password).find()) {
            throw new PasswordValidatorException("no uppercase letter");
        }
        return true;
    }

    @Override
    public boolean checkName(String name) {
        if (name.length()<2)throw new NameValidatorException("too short a name");
        if (name.isBlank())throw new NameValidatorException("name can`t be blank");
        return true;
    }

    @Override
    public boolean checkPostcode(String postcode) {
        String regExpression = ("([Gg][Ii][Rr] 0[Aa]{2})|((([A-Za-z][0-9]{1,2})|(([A-Za-z][A-Ha-hJ-Yj-y][0-9]{1,2})|(([A-Za-z][0-9][A-Za-z])|([A-Za-z][A-Ha-hJ-Yj-y][0-9][A-Za-z]?))))\\s?[0-9][A-Za-z]{2})");
        return Pattern.compile(regExpression)
                .matcher(postcode)
                .matches();
    }


}
