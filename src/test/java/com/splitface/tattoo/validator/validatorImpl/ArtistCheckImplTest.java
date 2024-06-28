package com.splitface.tattoo.validator.validatorImpl;

import com.splitface.tattoo.exception.NameValidatorException;
import com.splitface.tattoo.exception.PasswordValidatorException;
import com.splitface.tattoo.validator.ArtistCheck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ArtistCheckImplTest {

    private ArtistCheck artistCheck;


    @Test
    void checkEmail() {
        artistCheck = new ArtistCheckImpl();
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
        artistCheck = new ArtistCheckImpl();
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
        artistCheck = new ArtistCheckImpl();
        Exception exception = assertThrows(NameValidatorException.class,
                ()-> {artistCheck.checkName("  ");
                });
        String exMessage = "name can`t be blank";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exMessage));

        assertTrue(artistCheck.checkName("name"));
    }
}