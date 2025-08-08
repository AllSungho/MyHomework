package org.example.myhomework.controller;

import lombok.RequiredArgsConstructor;
import org.example.myhomework.dto.ReviewRequest;
import org.example.myhomework.dto.ReviewResponse;
import org.example.myhomework.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final PathMatcher pathMatcher;

    @PostMapping("/movies/{moveId}/reviews")
    public ResponseEntity<ReviewResponse> create(
            @RequestBody ReviewRequest request,
            @PathVariable Long moveId
    ) {
        return ResponseEntity.ok(reviewService.save(request,moveId));
    }

    @GetMapping("/movies/{moveId}/reviews")
    public ResponseEntity<List<ReviewResponse>> findAll(
            @PathVariable Long moveId
    ) {
        return ResponseEntity.ok(reviewService.findAll(moveId));
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
