package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
