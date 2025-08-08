package org.example.myhomework2.repository;

import org.example.myhomework2.entity.Movie;
import org.example.myhomework2.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovie(Movie movie);
}
