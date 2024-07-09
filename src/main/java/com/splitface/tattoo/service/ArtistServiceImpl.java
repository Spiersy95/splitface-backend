package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.artistCheck.artistCheckExceptions.*;
import com.splitface.tattoo.exception.service.serviceExceptions.ArtistIdDoesNotExistException;
import com.splitface.tattoo.exception.service.serviceExceptions.EmptyArtistTableException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.repository.ArtistRepository;
import com.splitface.tattoo.repository.TattooRepository;
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
        artist.setEmail(artist.getEmail().toLowerCase());
        String email = artist.getEmail();
        String password = artist.getPassword();
        PasswordUtils passwordUtils = new PasswordUtils();
        artist.setPassword(passwordUtils.hashPassword(artist.getPassword()));
        artistCheck.checkName(name);
        artistCheck.checkEmail(email);
        artistCheck.checkPassword(password);
        artistCheck.checkPostcode(location);
        if(!getListOfEmails().contains(email)) {
            artistRepository.save(artist);
            return "artist added";
        } else {
            throw new EmailValidatorException("Sorry this an account is already associated with this e-mail");
        }
    }



    @Override
    public Artist getArtistByEmail(String email) {
        Artist artist = new Artist();
        artist = artistRepository.findArtistByEmail(email);
        if (isNull(artist)) throw new ArtistIdDoesNotExistException("incorrect email");
        return artist;
    }

    @Override
    public Artist editArtist(Long artistId, Artist newArtist) {
        Artist artistFromDb = artistRepository.findById(artistId)
                .orElseThrow(()->new ArtistIdDoesNotExistException("no artist with such id"));

        if (!isNull(newArtist.getName())){
            if (artistCheck.checkName(newArtist.getName())){
                artistFromDb.setName(newArtist.getName());
            }
        }
        if (!isNull(newArtist.getLocation())){
            if(artistCheck.checkPostcode(newArtist.getLocation())){
                artistFromDb.setLocation(newArtist.getLocation());
            }
        }
        if(!isNull(newArtist.getEmail())){
            if(artistCheck.checkEmail(newArtist.getEmail())){
                artistFromDb.setEmail(newArtist.getEmail());
            }
        }
        if(!isNull(newArtist.getPassword())){
            if(artistCheck.checkPassword(newArtist.getPassword())){
                PasswordUtils passwordUtils = new PasswordUtils();
                artistFromDb.setPassword(passwordUtils.hashPassword(newArtist.getPassword()));
            }
        }

        artistRepository.save(artistFromDb);
        return artistFromDb;
    }

    @Override
    public List<String> getListOfEmails() {
        return new ArrayList<>(artistRepository.getAllEmails());
    }

    @Override
    public void deleteArtistById(Long id){
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty()){
            throw new ArtistIdDoesNotExistException("Sorry no artist with this id exists in the database");
        }
        artistRepository.delete(artist.get());
    }
}
