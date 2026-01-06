package com.example.study_gather.post;

import com.example.study_gather.post.dto.CreatePostRequest;
import com.example.study_gather.post.dto.CreatePostResponse;
import com.example.study_gather.post.dto.FilterPostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public CreatePostResponse createPost(@RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }

    @GetMapping("/{postId}")
    public PostResponse getDetailPost(@PathVariable Long postId) {
        return postService.getDetailPost(postId);
    }

    @GetMapping
    public FilterPostResponse filterPost(@RequestParam List<Long> locationIds,
                                         @RequestParam List<Long> categoryIds,
                                         @RequestParam Integer minNumber,
                                         @RequestParam Integer maxNumber,
                                         @RequestParam Boolean isOnline) {
        return postService.filterPost(locationIds, categoryIds, minNumber, maxNumber, isOnline);
    }
}
