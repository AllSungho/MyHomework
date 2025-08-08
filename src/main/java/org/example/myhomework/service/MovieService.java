package org.example.myhomework.service;

import lombok.RequiredArgsConstructor;
import org.example.myhomework.dto.MovieRequest;
import org.example.myhomework.dto.MovieResponse;
import org.example.myhomework.entity.Movie;
import org.example.myhomework.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    @Transactional
    public MovieResponse save(MovieRequest request) {
        Movie movie = new Movie(request.getTitle());
        Movie savedMovie = movieRepository.save(movie);
        return new MovieResponse(
                savedMovie.getId(),
                savedMovie.getTitle()
        );
    }

    @Transactional(readOnly = true)
    public List<MovieResponse> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> new MovieResponse(
                        movie.getId(),
                        movie.getTitle()
                )).toList();
    }
    // 단건 조회
    @Transactional(readOnly = true)
    public MovieResponse findById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디는 없습니다.")
        );
        return new MovieResponse(movie.getId(), movie.getTitle());
    }

    @Transactional
    public MovieResponse update(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디는 없습니다.")
        );
        movie.changeTitle(request.getTitle());
        return new MovieResponse(movie.getId(), movie.getTitle());
    }
}
