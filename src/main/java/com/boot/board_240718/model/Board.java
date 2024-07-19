package com.boot.board_240718.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity //JPA가 관리하는 클래스
@Data
public class Board {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull //필수 값
    @Size(min=2, max=30) //제목 최소 2자이상 최대 30자
    private String title;
    private String content;
}