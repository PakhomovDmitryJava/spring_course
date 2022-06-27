package ru.alishev.springcourse.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class SecondController {

    public String exit(){
        return "second/exit";
    }
}
