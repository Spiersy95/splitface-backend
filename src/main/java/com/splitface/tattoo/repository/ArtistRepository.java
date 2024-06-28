package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query(value = "SELECT email FROM artist", nativeQuery = true)
    List<String> getAllEmails();
}
