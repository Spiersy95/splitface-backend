package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.models.Tattoo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

import java.util.List;

@Repository
public interface TattooRepository extends CrudRepository<Tattoo, Long> {


    @Query(value = "SELECT * FROM tattoo \n"+
        "INNER JOIN style_tattoo_mapping stm ON tattoo.id = stm.tattoo_id \n"+
        "INNER JOIN style ON style.id = stm.style_id \n"+
        "WHERE style.id = :val", nativeQuery = true)
    Iterable<Tattoo> getTattoosByStyleId(@Param("val") long id);
  
    @Query(value = "SELECT * FROM tattoo WHERE arist_id=:artistId", nativeQuery = true)
    List<Tattoo> getTattoosByArtistId( @Param("artistId") Long artistId);

}
