package com.splitface.tattoo.repository;

import com.splitface.tattoo.models.Tattoo;
import org.springframework.data.repository.CrudRepository;

public interface TattooRepository extends CrudRepository<Tattoo, Long> {
}
