package com.example.study_gather.post;

import com.example.study_gather.common.security.JwtProvider;
import com.example.study_gather.post.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public CreatePostResponse createPost(@RequestBody CreatePostRequest request, @AuthenticationPrincipal JwtProvider.JwtUserPrincipal principal) {
        Long memberId = principal.getMemberId();
        return postService.createPost(request, memberId);
    }

    @GetMapping("/{postId}")
    public PostDetailResponse getDetailPost(@PathVariable Long postId, @AuthenticationPrincipal JwtProvider.JwtUserPrincipal principal) {
        Long memberId = (principal != null) ? principal.getMemberId() : null;
        return postService.getDetailPost(postId, memberId);
    }

    @GetMapping
    public FilterPostResponse filterPost(@RequestParam(required = false) List<Long> locationIds,
                                         @RequestParam(required = false) List<Long> categoryIds,
                                         @RequestParam(required = false) Integer minNumber,
                                         @RequestParam(required = false) Integer maxNumber,
                                         @RequestParam(required = false) Boolean isOnline,
                                         @RequestParam(required = false) String searchWord,
                                         @RequestParam(required = false) Boolean isActive) {
        return postService.filterPost(locationIds, categoryIds, minNumber, maxNumber, isOnline, searchWord, isActive);
    }

    @GetMapping("/myPosts")
    public PostListResponse getMyPost(@AuthenticationPrincipal JwtProvider.JwtUserPrincipal principal) {
        Long memberId = principal.getMemberId();
        return postService.getMyPost(memberId);
    }

    @PutMapping("/{postId}")
    public void closePost(@PathVariable Long postId, @AuthenticationPrincipal JwtProvider.JwtUserPrincipal principal) {
        Long memberId = principal.getMemberId();
        postService.closePost(memberId, postId);
    }

    @PutMapping("{postId}")
    public PostDetailResponse updatePost(@PathVariable Long postId,
                                         @RequestBody UpdatePostRequest updatePostRequest,
                                         @AuthenticationPrincipal JwtProvider.JwtUserPrincipal principal) {
        Long memberId = principal.getMemberId();
        return postService.updatePost(memberId, postId, updatePostRequest);
    }
}
