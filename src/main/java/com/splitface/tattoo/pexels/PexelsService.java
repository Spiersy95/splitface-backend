package com.splitface.tattoo.pexels;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


public class PexelsService {

    @Value("${pexels.api.key}")
    private String pexelsApiKey;
}
