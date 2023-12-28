package com.example.spring_boot_quiz_app.service;

import com.example.spring_boot_quiz_app.model.Question;
import com.example.spring_boot_quiz_app.model.QuestionForm;
import com.example.spring_boot_quiz_app.model.Result;
import com.example.spring_boot_quiz_app.repository.QuestionRepository;
import com.example.spring_boot_quiz_app.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {
    @Autowired
    Question question;
    @Autowired
    QuestionForm questionForm;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    Result result;
    @Autowired
    ResultRepository resultRepository;

    public QuestionForm getQuestionForm(){
        List<Question> allQuestion = questionRepository.findAll();
        List<Question> questionsList = new ArrayList<Question>();

        Random random = new Random();

        for (int i=0; i<5; i++){
            int randomQuestion = random.nextInt(allQuestion.size());
            questionsList.add(allQuestion.get(randomQuestion));
            allQuestion.remove(randomQuestion);
        }
        questionForm.setQuestions(questionsList);

        return questionForm;

    }

}
