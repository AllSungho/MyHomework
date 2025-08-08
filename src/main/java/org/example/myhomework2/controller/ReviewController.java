package org.example.myhomework2.controller;

import lombok.RequiredArgsConstructor;
import org.example.myhomework2.dto.ReviewRequest;
import org.example.myhomework2.dto.ReviewResponse;
import org.example.myhomework2.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/movies/{movieId}/reviews")
    public ResponseEntity<ReviewResponse> create(
            @RequestBody ReviewRequest request,
            @PathVariable Long movieId
    ) {
        return ResponseEntity.ok(reviewService.save(request, movieId));
    }

    @GetMapping("/movies/{movieId}/reviews")
    public ResponseEntity<List<ReviewResponse>> findAll(
            @PathVariable Long movieId
    ) {
        return ResponseEntity.ok(reviewService.findAll(movieId));
    }
    // 단건 조회
    @GetMapping("/movies/{movieId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> findById(
            @PathVariable Long movieId,
            @PathVariable Long reviewId
    ) {
        return ResponseEntity.ok(reviewService.findById(movieId, reviewId));
    }
    // 수정
    @PutMapping("/movies/{movieId}/reviews/{reviewId}")
    public ResponseEntity<ReviewResponse> update(
            @PathVariable Long movieId,
            @PathVariable Long reviewId,
            @RequestBody ReviewRequest request
    ) {
        return ResponseEntity.ok(reviewService.update(movieId, reviewId, request));
    }
}
