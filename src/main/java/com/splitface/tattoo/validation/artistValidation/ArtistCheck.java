package com.splitface.tattoo.validation.artistValidation;

public interface ArtistCheck {
    boolean checkEmail(String email);
    boolean checkPassword(String password);
    boolean checkName(String name);
    boolean checkPostcode(String postcode);
}
