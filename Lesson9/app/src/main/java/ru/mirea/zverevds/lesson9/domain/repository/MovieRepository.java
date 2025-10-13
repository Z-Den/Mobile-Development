package ru.mirea.zverevds.lesson9.domain.repository;

import ru.mirea.zverevds.lesson9.domain.models.Movie;

public interface MovieRepository {
    boolean saveMovie(Movie movie);
    Movie getMovie();
}