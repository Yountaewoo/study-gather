package com.example.study_gather.post;

import com.example.study_gather.post.dto.CreatePostRequest;
import com.example.study_gather.post.dto.CreatePostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public CreatePostResponse createPost(@RequestBody CreatePostRequest request) {
        return postService.createPost(request);
    }
}
