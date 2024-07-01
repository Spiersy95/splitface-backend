package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.exceptions.ArtistIdDoesNotExistException;
import com.splitface.tattoo.exception.exceptions.EmptyArtistTableException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.repository.ArtistRepository;
import com.splitface.tattoo.security.PasswordUtils;
import com.splitface.tattoo.validation.artistValidation.ArtistCheck;
import com.splitface.tattoo.validation.artistValidation.ArtistCheckImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@Service
public class ArtistServiceImpl implements ArtistService {


    private  final ArtistCheck artistCheck = new ArtistCheckImpl();
    @Autowired
    ArtistRepository artistRepository;


    @Override
    public Artist createArtistInDb(Artist artist) {
        return null;
    }

    @Override
    public Artist getArtistById(long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty()){
            throw new ArtistIdDoesNotExistException("Sorry this id does not match anyone we have in the database");
        }
        return artist.get();
    }

    public List<Artist> getAllArtist() {
        List<Artist> artistList = new ArrayList<>();
        artistRepository.findAll().forEach(artistList::add);
        if (artistList.isEmpty()){
            throw new EmptyArtistTableException("There are no artist");
        }
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
                artistCheck.checkPassword(password) &&
                artistCheck.checkPostcode(location)){
            artistRepository.save(artist);
        }
        return "Artist added";
    }



    @Override
    public Artist getArtistByEmail(String email) {
        Artist artist = new Artist();
        artist = artistRepository.findArtistByEmail(email);
        if (isNull(artist)) throw new ArtistIdDoesNotExistException("incorrect email");
        return artist;
    }


    /// helpful methods

    @Override
    public List<String> getListOfEmails() {
        return new ArrayList<>(artistRepository.getAllEmails());
    }
}
