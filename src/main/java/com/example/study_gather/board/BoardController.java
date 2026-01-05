package com.example.study_gather.board;

import com.example.study_gather.board.dto.CreateBoardRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("api/v1/board")
    public void createBoard(@RequestBody CreateBoardRequest request) {
        boardService.createBoard(request);
    }
}
