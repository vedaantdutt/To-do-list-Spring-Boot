package com.code.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {


    public TodoControllerJpa(
//            TodoService todoService
            TodoRepository todoRepository
    ) {
        super();
//        this.todoService = todoService;
        this.todoRepository=todoRepository;
    }

//    private TodoService todoService;

    private TodoRepository todoRepository;

    //list-todos

    private String getLoggedInUserName(ModelMap model) {
        org.springframework.security.core.Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();

    }

    @RequestMapping("/list-todos")
    public String listAllTodos(ModelMap model) {
        String username= getLoggedInUserName(model);
        System.out.println("username"+username);
//        List<Todo> todos = todoService.findByUsername(username);

        List<Todo> todos = todoRepository.findByUsername(username);
        System.out.println("todos"+todos);

        model.addAttribute("todos", todos);
        return "listTodos";
    }


    @RequestMapping(value="/add-todo",
            method= RequestMethod.GET)
    public String showNewTodoPage(
            ModelMap model
    ) {
        String username= getLoggedInUserName(model);
        Todo todo=new Todo(
                0,
                username,
                "",
                LocalDate.now().plusYears(1),
                false
        );
        model.put("todo",todo);
        return "todo";
    }

    @RequestMapping(value="/add-todo",
            method= RequestMethod.POST)
    public String addNewTodo(
            ModelMap model,
            @Valid Todo todo,
            BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username= getLoggedInUserName(model);
        todo.setUsername(username);
        todoRepository.save(todo);
//        todoService.addTodo(
//        username,
//        todo.getDescription(),
//        todo.getTargetDate(),
//                todo.isDone());
        return "redirect:list-todos";
    }

    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {

        //Delete todo

//todoService.deleteById(id);
        todoRepository.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value="update-todo",
    method= RequestMethod.GET)
    public String showUpdateTodoPage(
            @RequestParam int id,
            ModelMap model) {

        //Update todo
//Todo todo=todoService.findById(id);
Todo todo=todoRepository.findById(id).get();
        model.addAttribute("todo",todo);

//todoService.updateById(id);
        return "todo";

    }

    @RequestMapping(value="/update-todo",
    method= RequestMethod.POST)
    public String updateTodo(
            ModelMap model,
            @Valid Todo todo,
            BindingResult result) {
        if (result.hasErrors()) {
            return "todo";
        }
        String username= getLoggedInUserName(model);
        todo.setUsername(username);
        todoRepository.save(todo);
//        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }



}
