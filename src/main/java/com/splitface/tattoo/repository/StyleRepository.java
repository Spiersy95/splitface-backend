package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Style;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StyleRepository extends CrudRepository<Style, Long> {
    Optional<Style> findByStyleName(String styleName);

}
