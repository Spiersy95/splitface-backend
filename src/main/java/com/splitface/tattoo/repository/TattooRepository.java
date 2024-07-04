package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Tattoo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface TattooRepository extends CrudRepository<Tattoo, Long> {


    @Query(value = """
        SELECT tattoo.id, tattoo.design, tattoo.price, tattoo.hours_worked, tattoo.artist_id, tattoo.time_posted\s
        FROM tattoo
        INNER JOIN style_tattoo_mapping stm ON tattoo.id = stm.tattoo_id
        INNER JOIN style ON style.id = stm.style_id
        WHERE style.id = :val
       \s""", nativeQuery = true)
    Iterable<Tattoo> getTattoosByStyleId(@Param("val") long id);
  
    @Query(value = "SELECT * FROM tattoo WHERE artist_id=:artistId", nativeQuery = true)
    List<Tattoo> getTattoosByArtistId( @Param("artistId") Long artistId);


    @Query(value = "SELECT artist_id from tattoo WHERE id=:tattooId", nativeQuery = true)
    Long getArtistId(@Param("tattooId") Long tattooId);

}
