package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Tattoo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TattooRepository extends CrudRepository<Tattoo, Long> {

    @Query(value = "SELECT * FROM tattoo WHERE tattoo.artist_id=:artistId", nativeQuery = true)
    List<Tattoo> getTattoosByArtistId( @Param("artistId") Long artistId);

}
