package com.splitface.tattoo.service;

import com.splitface.tattoo.exception.exceptions.*;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.models.Tattoo;
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
    @Autowired
    TattooRepository tattooRepository;


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
            throw new InvalidEmailException("Sorry this an account is already associated with this e-mail");
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
        newArtist.setId(artistFromDb.getId());
        newArtist.setTattoos(artistFromDb.getTattoos());

        if (isNull(newArtist.getName())){
            newArtist.setName(artistFromDb.getName());
        } else if (!artistCheck.checkName(newArtist.getName())) {
            throw new NameValidatorException("something wrong with name");
        }

        if (isNull(newArtist.getLocation())){
            newArtist.setLocation(artistFromDb.getLocation());
        } else if (!artistCheck.checkPostcode(newArtist.getLocation())) {
                System.out.println(newArtist.getLocation());
                throw new PostcodeValidatorException("PostCode validator in action");
            }



        if (isNull(newArtist.getEmail())){
            newArtist.setEmail(artistFromDb.getEmail());
            System.out.println("in if");
        } else if (!artistCheck.checkEmail(newArtist.getEmail()) || getListOfEmails().contains(newArtist.getEmail())) {
            System.out.println("in else");
            throw new EmailValidatorException("Something wrong with email");
        }

        if (isNull(newArtist.getPassword())){
            newArtist.setPassword(artistFromDb.getPassword());
        } else if (artistCheck.checkPassword(newArtist.getPassword())) {
            PasswordUtils passwordUtils = new PasswordUtils();
            newArtist.setPassword(passwordUtils.hashPassword(newArtist.getPassword()));
        }else
            {
                throw new PasswordValidatorException("password validator in action");
            }
        artistRepository.save(newArtist);
        return newArtist;
    }

//    @Override
//    public Artist getArtistByTattooId(Long tattooId) {
//        Tattoo tattoo = tattooRepository.findById(tattooId)
//                .orElseThrow(()->new TattooIdDoesNotExistException("no this ID"));
//        System.out.println(" HADFLHBD!!!! "+ (tattoo.getArtist().getId()).getClass().getName());
//        Long artistId = tattoo.getArtist().getId();
//
//        Artist artist = artistRepository.findById(artistId).orElseThrow(()->new RuntimeException("aeg"));
//        return artist;
//    }



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
