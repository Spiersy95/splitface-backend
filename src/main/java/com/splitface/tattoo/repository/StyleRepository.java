package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Style;
import org.springframework.data.repository.CrudRepository;

public interface StyleRepository extends CrudRepository<Style, Long> {
    Style getStyleByStyleName(String styleName);
}
