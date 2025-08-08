package org.example.myhomework2.controller;

import lombok.RequiredArgsConstructor;
import org.example.myhomework2.dto.MovieRequest;
import org.example.myhomework2.dto.MovieResponse;
import org.example.myhomework2.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> saveMovie(
            @RequestBody MovieRequest request
    ) {
        return ResponseEntity.ok(movieService.save(request));
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieResponse>> getMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }
    // 단건 조회
    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> findById(
            @PathVariable Long movieId
    ) {
        return ResponseEntity.ok(movieService.findById(movieId));
    }
    // 수정
    @PutMapping("/movies/{movieId}")
    public ResponseEntity<MovieResponse> update(
            @PathVariable Long movieId,
            @RequestBody MovieRequest request
    ) {
        return ResponseEntity.ok(movieService.update(movieId, request));
    }
}
