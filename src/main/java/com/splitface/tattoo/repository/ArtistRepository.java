package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
