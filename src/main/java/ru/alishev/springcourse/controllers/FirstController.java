package ru.alishev.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(/*HttpServletRequest request*/
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            Model model) {

//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");

//        System.out.println("Hello, " + name + " " + surname);

        model.addAttribute("message", "Hello, " + name + " " + surname);


        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage() {
        return "first/goodbye";
    }


    @GetMapping("/calculator")
    public String calculationPage(@RequestParam(value = "a") int a,
                         @RequestParam(value = "action") String action,
                         @RequestParam(value = "b") int b,
                         Model model) {
        double result;
        switch (action) {
            case ("+"):
                result = a + b;
                break;
            case ("-"):
                result = a - b;
                break;
            case ("*"):
                result = a * b;
                break;
            case ("/"):
                result = a / (double) b;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }

        model.addAttribute("result", result);
        return "first/calculator";
    }
}
