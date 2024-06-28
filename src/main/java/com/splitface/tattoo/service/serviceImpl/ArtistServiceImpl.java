package com.splitface.tattoo.service.serviceImpl;

import com.splitface.tattoo.exception.ArtistNotFoundException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.repository.ArtistRepository;
import com.splitface.tattoo.security.PasswordUtils;
import com.splitface.tattoo.service.ArtistService;
import com.splitface.tattoo.validator.ArtistCheck;
import com.splitface.tattoo.validator.validatorImpl.ArtistCheckImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistCheck artistCheck = new ArtistCheckImpl();

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }
    @Override
    public List<Artist> getAllArtist() {
        List<Artist> artistList = new ArrayList<>();
        artistRepository.findAll().forEach(artistList::add);
        return artistList;
    }

    @Override
    public String addArtist(Artist artist) {
        String name = artist.getName();
        String location = artist.getLocation();
        String email = artist.getEmail();
        String password = artist.getPassword();
        PasswordUtils passwordUtils = new PasswordUtils();
        artist.setPassword(passwordUtils.hashPassword(artist.getPassword()));
        if (artistCheck.checkName(name) &&
                artistCheck.checkEmail(email) &&
                !getListOfEmails().contains(email) &&
                artistCheck.checkPassword(password)){
            artistRepository.save(artist);
        }
        return "Artist added";
    }

    @Override
    public Artist getArtistByEmail(String email) {
        Artist artist = new Artist();
        artist = artistRepository.findArtistByEmail(email);
        if (isNull(artist)) throw new ArtistNotFoundException("incorrect email");
        return artist;
    }

    /// helpful methods

    @Override
    public List<String> getListOfEmails() {
        return new ArrayList<>(artistRepository.getAllEmails());
    }
}
