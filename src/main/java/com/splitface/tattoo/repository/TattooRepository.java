package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Style;
import com.splitface.tattoo.models.Tattoo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TattooRepository extends CrudRepository<Tattoo, Long> {


    @Query(value = """
        SELECT tattoo.id, tattoo.design, tattoo.price, tattoo.artist_id\s
        FROM tattoo
        INNER JOIN style_tattoo_mapping stm ON tattoo.id = stm.tattoo_id
        INNER JOIN style ON style.id = stm.style_id
        WHERE style.id = :val
       \s""", nativeQuery = true)
    Iterable<Tattoo> getTattoosByStyleId(@Param("val") long id);
}
