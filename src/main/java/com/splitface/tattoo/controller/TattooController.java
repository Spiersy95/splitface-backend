package com.splitface.tattoo.controller;

import com.splitface.tattoo.service.TattooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TattooController {


    @Autowired
    TattooService tattooService;


    @PostMapping
    public ResponseEntity<String> addTattooForArtist(@RequestParam(name = "artistEmail") String artistEmail){

        return new ResponseEntity<>("Work have been added", HttpStatus.CREATED);

    }
}
