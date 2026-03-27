package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.TodoMapper;
import com.example.demo.model.Todo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoMapper todoMapper;

    public List<Todo> findAll() {
        return todoMapper.findAll();
    }

    public void create(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(Boolean.FALSE);
        todoMapper.insert(todo);
    }
}
