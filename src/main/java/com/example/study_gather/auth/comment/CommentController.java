package com.example.study_gather.auth.comment;

import com.example.study_gather.auth.comment.dto.CreateCommentRequest;
import com.example.study_gather.auth.comment.dto.CreateCommentResponse;
import com.example.study_gather.common.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public CreateCommentResponse CreateComment(@RequestBody CreateCommentRequest request,
                                               @AuthenticationPrincipal JwtProvider.JwtUserPrincipal principal) {
        Long memberId = principal.getMemberId();
        return commentService.createComment(request, memberId);
    }
}
