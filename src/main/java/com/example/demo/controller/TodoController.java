package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Todo;
import com.example.demo.service.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public String list(Model model) {
        List<TodoItem> todoList = todoService.findAll().stream()
                .map(this::toTodoItem)
                .toList();

        model.addAttribute("todoList", todoList);
        return "todo/list";
    }

    @GetMapping("/new")
    public String showNewForm() {
        return "todo/form";
    }

    @PostMapping("/confirm")
    public String confirm(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("priority") String priority,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("priority", priority);
        return "todo/confirm";
    }

    @PostMapping("/complete")
    public String complete(@RequestParam("title") String title) {
        todoService.create(title);
        return "redirect:/todo";
    }

    private TodoItem toTodoItem(Todo todo) {
        String status = Boolean.TRUE.equals(todo.getCompleted()) ? "\u5B8C\u4E86" : "\u672A\u5B8C\u4E86";
        return new TodoItem(todo.getId(), todo.getTitle(), status);
    }

    public record TodoItem(Long id, String title, String status) {
    }
}
