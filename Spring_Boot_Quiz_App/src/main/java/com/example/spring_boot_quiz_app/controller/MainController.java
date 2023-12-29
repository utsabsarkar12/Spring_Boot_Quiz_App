package com.example.spring_boot_quiz_app.controller;

import com.example.spring_boot_quiz_app.model.QuestionForm;
import com.example.spring_boot_quiz_app.model.Result;
import com.example.spring_boot_quiz_app.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    Result result;
    @Autowired
    QuizService quizService;
    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult(){
        return result;
    }
    @GetMapping("/")
    public String home(){
        return "index.html";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, Model m, RedirectAttributes ra){
        if (username.equals("")){
            ra.addFlashAttribute("warning", "You must enter your name");
            return "redirect:/";
        }
        submitted = false;
        result.setUsername(username);

        QuestionForm questionForm = quizService.getQuestionForm();
        m.addAttribute("questionForm", questionForm);

        return "quiz.html";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm questionForm, Model m){
        if(!submitted){
            result.setTotalCorrect(quizService.getResult(questionForm));
            quizService.saveScore(result);
            submitted = true;
        }

        return "result.html";
    }

    @GetMapping("/score")
    public String score(Model m){
        List<Result> scoreList = quizService.getTopScore();
        m.addAttribute("scoreList", scoreList);
        return "scoreboard.html";
    }
}
