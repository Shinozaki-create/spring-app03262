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

    public Todo findById(Long id) {
        return todoMapper.findById(id);
    }

    public void create(String title) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setCompleted(Boolean.FALSE);
        todoMapper.insert(todo);
    }

    public boolean update(Long id, String title) {
        Todo todo = todoMapper.findById(id);
        if (todo == null) {
            return false;
        }
        todo.setTitle(title);
        return todoMapper.update(todo) > 0;
    }

    public boolean toggleCompleted(Long id) {
        Todo todo = todoMapper.findById(id);
        if (todo == null) {
            return false;
        }
        boolean nextCompleted = !Boolean.TRUE.equals(todo.getCompleted());
        todo.setCompleted(nextCompleted);
        return todoMapper.update(todo) > 0;
    }

    public boolean deleteById(Long id) {
        return todoMapper.deleteById(id) > 0;
    }
}
