package com.example.study_gather.post;

import com.example.study_gather.auth.comment.Comment;
import com.example.study_gather.auth.comment.CommentQueryRepository;
import com.example.study_gather.auth.comment.CommentRepository;
import com.example.study_gather.category.Category;
import com.example.study_gather.category.CategoryRepository;
import com.example.study_gather.location.Location;
import com.example.study_gather.location.LocationRepository;
import com.example.study_gather.member.Member;
import com.example.study_gather.member.MemberRepository;
import com.example.study_gather.post.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final PostQueryRepository postQueryRepository;
    private final MemberRepository memberRepository;
    private final CommentQueryRepository commentQueryRepository;

    @Transactional
    public CreatePostResponse createPost(CreatePostRequest request, Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new NoSuchElementException("해당하는 사용자가 없습니다."));
        member.validateAdminPermission();
        Category category = categoryRepository.findById(request.categoryId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 카테고리가 없습니다."));
        Location location = locationRepository.findById(request.locationId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 지역이 없습니다."));

        Post post = postRepository.save(new Post(
                category.getId(),
                member.getId(),
                location.getId(),
                request.title(),
                request.maxNumber(),
                request.minNumber(),
                request.isOnline(),
                request.content(),
                request.startDate(),
                request.endDate()));

        return CreatePostResponse.toCreatePostResponse(post);
    }

    public PostDetailResponse getDetailPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException("해당하는 게시글이 없습니다."));
        List<Comment> comments = commentQueryRepository.findByPostId(postId);
        return PostDetailResponse.toPostDetailResponse(post, comments);
    }

    public FilterPostResponse filterPost(List<Long> locationIds,
                                         List<Long> categoryIds,
                                         Integer minNumber,
                                         Integer maxNumber,
                                         Boolean isOnline) {
        List<Post> posts = postQueryRepository.searchPost(locationIds, categoryIds, minNumber, maxNumber, isOnline);
        List<PostResponse> postResponseList = posts.stream()
                .map(post -> {
                    PostResponse postResponse = PostResponse.toPostResponse(post);
                    return postResponse;
                })
                .toList();
        return new FilterPostResponse(postResponseList);
    }
}
