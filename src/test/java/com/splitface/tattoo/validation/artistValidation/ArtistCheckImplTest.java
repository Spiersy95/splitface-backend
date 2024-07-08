package com.splitface.tattoo.validation.artistValidation;

import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.EmailValidatorException;
import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.NameValidatorException;
import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.PasswordValidatorException;
import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.PostcodeValidatorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistCheckImplTest {

    private ArtistCheck artistCheck;

    @BeforeEach
    void setup(){
        artistCheck = new ArtistCheckImpl();

    }


    @Test
    void checkEmail() {
        String email1 = "wagas.sehrh";
        String email2 = "asdg@sdfg";
        String email3 = "asdg@asdg.s";
        String email4 = "agasdg@sdfh.sd";

        assertThrows(EmailValidatorException.class, () -> artistCheck.checkEmail(email1));
        assertThrows(EmailValidatorException.class, () -> artistCheck.checkEmail(email2));
        assertThrows(EmailValidatorException.class, () -> artistCheck.checkEmail(email3));

        assertTrue(artistCheck.checkEmail(email4));
    }

    @Test
    void checkPassword() {
        Exception exception = assertThrows(PasswordValidatorException.class,
                ()-> artistCheck.checkPassword("Fadsf34m6"));
        String exMessage = "no special symbol";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        exception = assertThrows(PasswordValidatorException.class,
                ()-> artistCheck.checkPassword("asd7zd7&f"));
        exMessage = "no uppercase letter";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        exception = assertThrows(PasswordValidatorException.class,
                ()-> artistCheck.checkPassword("TYIHGV8746YU&%"));
        exMessage = "no lowercase character";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        exception = assertThrows(PasswordValidatorException.class,
                ()-> artistCheck.checkPassword("afe*YHsdfv"));
        exMessage = "no numbers in pass";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        assertTrue(artistCheck.checkPassword("adfd4Kgi&"));
    }

    @Test
    void checkName() {
        Exception exception = assertThrows(NameValidatorException.class,
                ()-> artistCheck.checkName("  "));
        String exMessage = "name can`t be blank";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        assertTrue(artistCheck.checkName("name"));
    }

    @Test
    void checkPostcode(){
        String correctInput1 = "G69 6LQ";
        String correctInput2 = "EX4 6OJ";
        String correctInput3 =  "DH1 4JS";

        String incorrectInput1 = "GGE LKN";
        String incorrectInput2 =  "398 130";
        String incorrectInput3 =  "GP £ 40";

        assertTrue(artistCheck.checkPostcode(correctInput1));
        assertTrue(artistCheck.checkPostcode(correctInput2));
        assertTrue(artistCheck.checkPostcode(correctInput3));

        assertThrows(PostcodeValidatorException.class, () ->artistCheck.checkPostcode(incorrectInput1));
        assertThrows(PostcodeValidatorException.class, () ->artistCheck.checkPostcode(incorrectInput2));
        assertThrows(PostcodeValidatorException.class, () ->artistCheck.checkPostcode(incorrectInput3));




    }
}