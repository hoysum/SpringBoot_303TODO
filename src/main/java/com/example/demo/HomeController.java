package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    ToDoRepository toDoRepository;

    @RequestMapping("/")
    public String listToDos(Model model){
        model.addAttribute("todos", toDoRepository.findAll());
        return "list";
    }
    @GetMapping("/add")
    public String todoForm(Model model){
        model.addAttribute("todo", new ToDo());
        return "todoForm";
    }
    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("todo") ToDo toDo,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "todoForm";
        }
        toDoRepository.save(toDo);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showToDo(@PathVariable("id") long id, Model model)
    {
        model.addAttribute("toDo", toDoRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update{id}")
    public String updateToDo(@PathVariable("id") long id,
            Model model){
            model.addAttribute("todo", toDoRepository.findById(id).get());
            return "todoForm";
        }
        @RequestMapping("/delete/{id}")
                public String deleteToDo(@PathVariable("id") long id){
                toDoRepository.deleteById(id);
                return "redirect:/";
        }
    }






