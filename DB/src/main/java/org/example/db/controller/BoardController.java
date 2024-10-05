package org.example.db.controller;

import org.example.db.dto.BoardDTO;
import org.example.db.service.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private final BoardServiceImpl boardService;

    public BoardController(BoardServiceImpl boardService) {
        this.boardService = boardService;
    }

    @GetMapping
    public String board() {
        return "board";
    }

    @PostMapping
    public String CreateBoard(@ModelAttribute BoardDTO dto, Model model) {
        boardService.insert(dto);
        model.addAttribute("result", dto);
        return "board";
    }

    @GetMapping("/{id}")
    public String select(@PathVariable("id") int id, Model model) {
        BoardDTO result = boardService.select(id);
        model.addAttribute("result", result);
        return "index";
    }

    @GetMapping("/list")
    public String listAll(Model model) {
        List<BoardDTO> result = boardService.listAll();
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id){
        boardService.delete(id);
        return "index";
    }
}