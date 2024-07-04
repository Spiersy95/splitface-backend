package com.splitface.tattoo.controller;

import com.splitface.tattoo.exception.exceptions.ArtistIdDoesNotExistException;
import com.splitface.tattoo.models.Artist;
import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.service.ArtistService;
import com.splitface.tattoo.service.TattooService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/tattoo")
public class TattooController {

    @Autowired
    TattooService tattooService;
    @Autowired
    ArtistService artistService;

    @Operation(summary ="gets all the tattoos in the app",
            description = "Returns a list of all tattoos in json format")
    @GetMapping("/tattoos")
    ResponseEntity<List<Tattoo>> getAllTattoos(){
        List<Tattoo> tattoos = tattooService.getAllTattoos();
        return new ResponseEntity<>(tattoos, HttpStatus.FOUND);
    }

    @GetMapping("/tattoos/style/{id}")
    ResponseEntity<List<Tattoo>> getTattoosByStyleId(@PathVariable Long id){
        List<Tattoo> tattoos = tattooService.getTattoosByStyleId(id);
        return new ResponseEntity<>(tattoos, HttpStatus.FOUND);
    }


    @PostMapping("/artist")
    public ResponseEntity<String> addTattooForArtistInDB(@RequestBody Tattoo tattoo,
                                                         @RequestParam (name = "id") Long artistId){
        tattooService.addTattooInDb(tattoo,artistId);
        return new ResponseEntity<>("tattoo added",HttpStatus.CREATED);
    }

    @GetMapping("/artist")
    public ResponseEntity<List<Tattoo>> getAllTattoosByArtistId(@RequestParam(name = "id") Long artistId){
        return new ResponseEntity<>(tattooService.getTattoosByArtist(artistId),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTattooById(@PathVariable("id") Long id){
        tattooService.deleteTattooById(id);
        return new ResponseEntity<>(String.format("The tattoo with id: %d has been deleted", id), HttpStatus.OK);
    }

    @GetMapping("/tattoo")
    public ResponseEntity<Artist> getArtistByTattooIdController(@RequestParam(name = "id") Long tattooId){
        Artist artist = tattooService.getArtistByTattooId(tattooId);
        return new ResponseEntity<>(artist, HttpStatus.FOUND);
    }

}
