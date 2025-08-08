package org.example.myhomework.repository;

import org.example.myhomework.entity.Movie;
import org.example.myhomework.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {


    List<Review> findAllByMovie(Movie movie);
}
