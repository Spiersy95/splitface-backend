package com.splitface.tattoo.service;

import com.splitface.tattoo.models.Tattoo;
import com.splitface.tattoo.repository.TattooRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TattooService {

    List<Tattoo> getAllTattoos();
}
