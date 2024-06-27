package com.splitface.tattoo.Controllers;

import com.splitface.tattoo.Service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tattooapi/v1/")
public class ArtistController {

    @Autowired
    ArtistService artistService;
    
    @Operation(summary ="gets all the tattoo artist accounts in the app",
            description = "Returns a list of all tattoo artist in json format")
    @GetMapping("/artist")
    public ResponseEntity<List<Artist>> getAllArtist(){
        List<Artist> artistList = artistService.getAllArtist();
        return new ResponseEntity<List<Artist>>(artistList, HttpStatus.FOUND);
    }
    
    
    
    


}