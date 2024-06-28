package com.splitface.tattoo.controller;

import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.service.TattooService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tattoo")
public class TattooController {

    @Autowired
    TattooService tattooService;

    @Operation(summary ="gets all the tattoos in the app",
            description = "Returns a list of all tattoos in json format")
    @GetMapping("/tattoos")
    ResponseEntity<List<Tattoo>> getAllTattoos(){
        List<Tattoo> tattoos = tattooService.getAllTattoos();
        return new ResponseEntity<>(tattoos, HttpStatus.FOUND);
    }


}
