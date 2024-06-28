package com.splitface.tattoo.controller;

import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Operation(summary ="gets all the tattoo artist accounts in the app",
            description = "Returns a list of all tattoo artist in json format")
    @GetMapping("/")
    public ResponseEntity<List<Artist>> getAllArtist(){
        List<Artist> artistList = artistService.getAllArtist();
        return new ResponseEntity<List<Artist>>(artistList, HttpStatus.FOUND);
    }


}
