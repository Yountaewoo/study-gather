package com.example.study_gather.auth.comment;

import com.example.study_gather.auth.comment.dto.CreateCommentRequest;
import com.example.study_gather.auth.comment.dto.CreateCommentResponse;
import com.example.study_gather.post.Post;
import com.example.study_gather.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CreateCommentResponse createComment(CreateCommentRequest request, Long memberId) {
        Post post = postRepository.findById(request.postId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 게시글이 없습니다."));
        Comment comment = commentRepository.save(new Comment(
                post.getId(),
                memberId,
                request.content()));
        return CreateCommentResponse.toCommentResponse(comment);
    }
}
