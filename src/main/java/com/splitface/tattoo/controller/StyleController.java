package com.splitface.tattoo.controller;

import com.splitface.tattoo.models.Style;
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

    @GetMapping("/styles")
    public ResponseEntity<List<Style>> getAllStyles(){
        List<Style> styles = styleService.getAllStylesFromDB();
        return new ResponseEntity<>(styles, HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addStylesForTattoo(@RequestParam(name = "id") Long tattooId,
                                                     @RequestBody List<String> styleNames){

        styleService.addStylesForTattoo(tattooId,styleNames);
        return new ResponseEntity<>("styles added", HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<String> addStyle(@RequestBody Style style){
        styleService.addStyle(style);
        return new ResponseEntity<>(String.format("The style %s has been added", styleName), HttpStatus.ACCEPTED);
    }

}
