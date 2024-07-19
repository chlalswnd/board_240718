package com.boot.board_240718.repository;

import com.boot.board_240718.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}