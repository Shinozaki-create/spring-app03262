package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @GetMapping
    public String list(Model model) {
        List<TodoItem> todoList = List.of(
                new TodoItem(1L, "Spring Bootの学習", "進行中"),
                new TodoItem(2L, "ToDo一覧画面の作成", "未着手"),
                new TodoItem(3L, "Thymeleafテンプレート確認", "完了"));

        model.addAttribute("todoList", todoList);
        return "todo/list";
    }

    @GetMapping("/new")
    public String showNewForm() {
        return "todo/new";
    }

    @GetMapping("/confirm")
    public String showConfirm() {
        return "todo/confirm";
    }

    public record TodoItem(Long id, String title, String status) {
    }
}
