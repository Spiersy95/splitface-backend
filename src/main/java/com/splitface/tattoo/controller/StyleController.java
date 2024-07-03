package com.splitface.tattoo.controller;

import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/style")
public class StyleController {

    @Autowired
    StyleService styleService;

    @PostMapping
    public ResponseEntity<String> addStylesForTattoo(@RequestParam(name = "id") Long tattooId,
                                                     @RequestBody List<String> styleNames){

        styleService.addStylesForTattoo(tattooId,styleNames);
        return new ResponseEntity<>("styles added", HttpStatus.ACCEPTED);
    }
}
