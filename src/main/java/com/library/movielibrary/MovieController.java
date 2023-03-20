package com.library.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    //getting all records
    @GetMapping("/")
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    //getting records by id
    @GetMapping("/{id}")
    public Movie getById(@PathVariable("id") int id) {
        return movieRepository.getById(id);
    }

    //adding records
    @PostMapping("/")
    public int add(@RequestBody List<Movie> movies) {
        return movieRepository.save(movies);
    }

    //updating whole records
    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);

        if (movie != null) {
            movie.setTitle(updatedMovie.getTitle());
            movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);

            return 1;
        } else {
            return -1;
        }
    }

    //partially updating records
    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getById(id);

        if (movie != null) {
            if (updatedMovie.getTitle() != null) movie.setTitle(updatedMovie.getTitle());
            if (updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);

            return 1;
        }else {
            return -1;
        }
    }

    //delete record by id
    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        Movie movie = movieRepository.getById(id);
        return movieRepository.delete(id);
    }

}
