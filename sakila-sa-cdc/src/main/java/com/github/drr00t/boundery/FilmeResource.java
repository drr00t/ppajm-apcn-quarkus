
/*
 * Copyright (C) Book Patterns and Practices for Modern Java Architecture Adriano dos Santos Ribeiro adribeiro@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.drr00t.boundery;

import com.github.drr00t.boundery.dto.AddFilm;
import com.github.drr00t.control.FilmService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * REST resource for managing films.
 */
@Path("/films")
public class FilmeResource {

    @Inject
    FilmService filmService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createFilm(AddFilm film) {
        filmService.createFilm(film);
        return Response.status(Response.Status.CREATED).entity(film).build();
    }
}
