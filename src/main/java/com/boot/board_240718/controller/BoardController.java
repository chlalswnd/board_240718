package com.boot.board_240718.controller;

import com.boot.board_240718.model.Board;
import com.boot.board_240718.repository.BoardRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards",boards);

        return "board/list";
    }

    @GetMapping("/form")
//    public String form(Model model){
//    public String form(Model model, @RequestParam Long id){
    public String form(Model model, @RequestParam(required = false) Long id){
        log.info("@# GetMapping form()");
        log.info("@# id=>"+id);

//        id가 not null 일 경우 model에 board를 담는다
        if (id != null){
//            둘중 하나 가능
//            Optional<Board> board = boardRepository.findById(id);
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board",board);
            log.info("@# board=>"+board);
        } else {
//            id 가 null 일 경우
            model.addAttribute("board", new Board());
        }

        return "board/form";
    }

    @PostMapping("/form")
//    public String form(@ModelAttribute Board board, Model model) {
    public String checkPersonInfo(@Valid Board board, BindingResult bindingResult) {
//        오류가 날시
        if (bindingResult.hasErrors()) {
            return "board/form";
        }

//        insert 쿼리 없이 Repository로
        boardRepository.save(board);

        return "redirect:/board/list";
    }
}
