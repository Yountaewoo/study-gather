package com.example.study_gather.post;

import com.example.study_gather.board.Board;
import com.example.study_gather.board.BoardRepository;
import com.example.study_gather.category.Category;
import com.example.study_gather.category.CategoryRepository;
import com.example.study_gather.location.Location;
import com.example.study_gather.location.LocationRepository;
import com.example.study_gather.post.dto.CreatePostRequest;
import com.example.study_gather.post.dto.CreatePostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;
    private final BoardRepository boardRepository;

    public CreatePostResponse createPost(CreatePostRequest request) {
        Category category = categoryRepository.findById(request.categoryId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 카테고리가 없습니다."));
        Location location = locationRepository.findById(request.locationId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 지역이 없습니다."));
        Board board = boardRepository.findById(request.boardId()).orElseThrow(
                () -> new NoSuchElementException("해당하는 게시판이 없습니다."));

        Post post = postRepository.save(new Post(board.getId(),
                category.getId(),
                location.getId(),
                request.title(),
                request.maximumNumber(),
                request.content(),
                request.startDate(),
                request.endDate()));

        return CreatePostResponse.toCreatePostResponse(post);
    }
}
