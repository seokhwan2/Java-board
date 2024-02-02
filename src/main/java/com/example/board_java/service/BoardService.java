package com.example.board_java.service;

import com.example.board_java.dto.BoardDTO;
import com.example.board_java.entity.Board;
import com.example.board_java.exception.BoardNotFoundException;
import com.example.board_java.repository.BoardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    /** 페이징처리 */
    public Page<BoardDTO> getAllBoards(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        return boards.map(board -> {
            BoardDTO boardDTO = new BoardDTO();
            BeanUtils.copyProperties(board, boardDTO);
            return boardDTO;
        });
    }

    /** 상세조회 */
    public BoardDTO getBoardById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            BoardDTO boardDTO = new BoardDTO();
            BeanUtils.copyProperties(optionalBoard.get(), boardDTO);
            return boardDTO;
        } else {
            throw new BoardNotFoundException(id);
        }
    }

    /** 글쓰기 */
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = new Board();
        BeanUtils.copyProperties(boardDTO, board);
        boardRepository.save(board);
        return boardDTO;
    }
    /** 글수정 */
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            BeanUtils.copyProperties(boardDTO, board);
            boardRepository.save(board);
            return boardDTO;
        } else {
            throw new BoardNotFoundException(id);
        }
    }

    /** 글삭제 */
    public void deleteBoard(Long id) {
        if (boardRepository.existsById(id)) {
            boardRepository.deleteById(id);
        } else {
            throw new BoardNotFoundException(id);
        }
    }
}