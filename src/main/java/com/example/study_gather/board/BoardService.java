package com.example.study_gather.board;

import com.example.study_gather.board.dto.CreateBoardRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createBoard(CreateBoardRequest request) {
        if (boardRepository.findByName(request.name()).isPresent()) {
            throw new IllegalArgumentException("해당하는 이름의 게시판이 존재합나디.");
        } else {
            boardRepository.save(new Board(request.name()));
        }
    }
}
