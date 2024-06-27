package com.splitface.tattoo.validator.validatorImpl;

import com.splitface.tattoo.validator.ArtistCheck;
import org.junit.jupiter.api.Test;

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
        String pass = "Fadsf34m6";
        assertEquals("no special symbol", artistCheck.checkPassword(pass));
        pass = "asd7zd7&";
        assertEquals("no uppercase letter", artistCheck.checkPassword(pass));
        pass = "TYIHGV8746YU&%";
        assertEquals("no lowercase character", artistCheck.checkPassword(pass));
        pass = "sfg*^j8hkK";
        assertEquals("OK", artistCheck.checkPassword(pass));

    }
}