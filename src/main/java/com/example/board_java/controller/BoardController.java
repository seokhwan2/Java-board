package com.example.board_java.controller;

import com.example.board_java.dto.BoardDTO;
import com.example.board_java.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public Page<BoardDTO> getAllBoards(Pageable pageable) {
        try {
            // ...
        } catch (Exception e) {
            throw new RuntimeException("예외 메시지", e);
        }
        return boardService.getAllBoards(pageable);
    }

    /**
     * 상세조회
     * */
    @GetMapping("/{id}")
    public BoardDTO getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    /**
     * 글쓰기
     * */
    @PostMapping("/create")
    public BoardDTO createBoard(@RequestBody BoardDTO boardDTO) {
        return boardService.createBoard(boardDTO);
    }

    /**
     * 수정
     * */
    @PutMapping("/updated/{id}")
    public BoardDTO updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        return boardService.updateBoard(id, boardDTO);
    }

    /**
     * 삭제
     * */
    @DeleteMapping("/deleted/{id}")
    public void deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
    }
}