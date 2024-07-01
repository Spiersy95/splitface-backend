package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Long> {

    @Query(value = "SELECT email FROM artist", nativeQuery = true)
    List<String> getAllEmails();
    Artist findArtistByEmail(String email);
}
