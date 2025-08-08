package org.example.myhomework.controller;

import lombok.RequiredArgsConstructor;
import org.example.myhomework.dto.ReviewRequest;
import org.example.myhomework.dto.ReviewResponse;
import org.example.myhomework.entity.Review;
import org.example.myhomework.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

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
}
