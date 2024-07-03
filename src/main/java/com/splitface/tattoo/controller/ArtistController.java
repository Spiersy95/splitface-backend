package com.splitface.tattoo.controller;

import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    ArtistService artistService;



    @Operation(summary ="gets all the tattoo artist accounts in the app",
            description = "Returns a list of all tattoo artist in json format")
    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> getAllArtist(){
        List<Artist> artistList = artistService.getAllArtist();
        return new ResponseEntity<>(artistList, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id){
        Artist artist = artistService.getArtistById(id);
        return new ResponseEntity<>(artist, HttpStatus.FOUND);
    }



    @PostMapping
    public ResponseEntity<String> addArtist(@RequestBody Artist artist){
        String name = artist.getName();
        artistService.addArtist(artist);
        return new ResponseEntity<>("artist created "+name, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Artist> passwordMatchWithEmail(@RequestParam("email") String email,
                                                         @RequestParam("password") String password){
        Artist artist = artistService.getArtistByEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(password, artist.getPassword())){
            return new ResponseEntity<>(artist,HttpStatus.ACCEPTED);
        }else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cant be found");
    }

    @PutMapping
    public ResponseEntity<Artist> editArtistDetails(@RequestParam(name = "id") Long artistId, @RequestBody Artist newArtist){
        artistService.editArtist(artistId, newArtist);
        return new ResponseEntity<>(artistService.getArtistById(artistId),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArtistById(@PathVariable("id") Long id){
        artistService.deleteArtistById(id);
        return new ResponseEntity<>(String.format("The artist with id: %d has been deleted", id), HttpStatus.OK);
    }

}
