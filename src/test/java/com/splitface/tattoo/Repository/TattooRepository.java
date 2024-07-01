package com.splitface.tattoo.Repository;

import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.models.Tattoo;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class TattooRepository {

    @Autowired
    private TattooRepository tattooRepository;

    Tattoo tattoo;
    Tattoo tattoo2;
    Style style;
    Style syle2;

    @BeforeEach
    void setup(){


    }
}
