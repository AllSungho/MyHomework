package org.example.myhomework.service;

import lombok.RequiredArgsConstructor;
import org.example.myhomework.dto.ReviewRequest;
import org.example.myhomework.dto.ReviewResponse;
import org.example.myhomework.entity.Movie;
import org.example.myhomework.entity.Review;
import org.example.myhomework.repository.MovieRepository;
import org.example.myhomework.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public ReviewResponse save(ReviewRequest request, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디 없습니다.")
        );
        Review review = new Review(
                request.getContent(),
                movie
        );
        Review savedReview = reviewRepository.save(review);
        return new ReviewResponse(
                savedReview.getId(),
                savedReview.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디 없습니다.")
        );
        List<Review> reviews = reviewRepository.findAllByMovie(movie);
        List<ReviewResponse> dtos = new ArrayList<>();

        for(Review review : reviews) {
            dtos.add(
                    new ReviewResponse(
                            review.getId(),
                            review.getContent()
                    )
            );
        }
        return dtos;
    }
    // 단건 조회
    @Transactional
    public ReviewResponse findById(Long movieId, Long reviewId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디는 없습니다.")
        );
        List<Review> reviews = reviewRepository.findAllByMovie(movie);
        Review review = reviews.stream()
                .filter(rev -> rev.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(
                () -> new IllegalArgumentException("그런 리뷰 아이디는 없습니다.")
                );
        return new ReviewResponse(
                review.getId(),
                review.getContent()
        );
    }
    // 수정
    @Transactional
    public ReviewResponse update(Long movieId, Long reviewId, ReviewRequest request) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(
                () -> new IllegalArgumentException("그런 아이디는 없습니다.")
        );
        List<Review> reviews = reviewRepository.findAllByMovie(movie);
        Review review = reviews.stream()
                .filter(rev -> rev.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("그런 리뷰 아이디는 없습니다.")
                );
        review.changeContent(request.getContent());
        return new ReviewResponse(
                review.getId(),
                review.getContent()
        );
    }
}
