package com.splitface.tattoo.validation.artistValidation;

import com.splitface.tattoo.exception.NameValidatorException;
import com.splitface.tattoo.exception.PasswordValidatorException;
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
        String email = "wagas.sehrh";
        assertFalse(artistCheck.checkEmail(email));
        email = "asdg@sdfg";
        assertFalse(artistCheck.checkEmail(email));
        email ="asdg@asdg.s";
        assertFalse(artistCheck.checkEmail(email));
        email="agasdg@sdfh.sd";
        assertTrue(artistCheck.checkEmail(email));

    }

    @Test
    void checkPassword() {
        Exception exception = assertThrows(PasswordValidatorException.class,
                ()-> {artistCheck.checkPassword("Fadsf34m6");
        });
        String exMessage = "no special symbol";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        exception = assertThrows(PasswordValidatorException.class,
                ()-> {artistCheck.checkPassword("asd7zd7&f");
        });
        exMessage = "no uppercase letter";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        exception = assertThrows(PasswordValidatorException.class,
                ()-> {artistCheck.checkPassword("TYIHGV8746YU&%");
                });
        exMessage = "no lowercase character";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        exception = assertThrows(PasswordValidatorException.class,
                ()-> {artistCheck.checkPassword("afe*YHsdfv");
                });
        exMessage = "no numbers in pass";
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        assertTrue(artistCheck.checkPassword("adfd4Kgi&"));
    }

    @Test
    void checkName() {
        Exception exception = assertThrows(NameValidatorException.class,
                ()-> {artistCheck.checkName("  ");
                });
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

        assertFalse(artistCheck.checkPostcode(incorrectInput1));
        assertFalse(artistCheck.checkPostcode(incorrectInput2));
        assertFalse(artistCheck.checkPostcode(incorrectInput3));


    }
}