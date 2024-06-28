package com.splitface.tattoo.controller;

import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.service.ArtistService;
import com.splitface.tattoo.service.serviceImpl.ArtistServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistServiceImpl artistService;
    @Autowired
    public ArtistController(ArtistServiceImpl artistService) {
        this.artistService = artistService;
    }



    @Operation(summary ="gets all the tattoo artist accounts in the app",
            description = "Returns a list of all tattoo artist in json format")
    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> getAllArtist(){
        List<Artist> artistList = artistService.getAllArtist();
        return new ResponseEntity<>(artistList, HttpStatus.FOUND);
    }



    @PostMapping
    public ResponseEntity<String> addArtist(@RequestBody Artist artist){
        artistService.addArtist(artist);
        return new ResponseEntity<>("have ben added ", HttpStatus.CREATED);
    }


}
