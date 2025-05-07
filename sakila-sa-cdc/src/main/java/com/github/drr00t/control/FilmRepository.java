package com.github.drr00t.control;

import com.github.drr00t.entity.Film;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FilmRepository implements PanacheRepository<Film> {

}
