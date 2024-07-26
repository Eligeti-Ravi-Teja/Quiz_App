package com.MARS.QuizApplication.service;

import com.MARS.QuizApplication.dao.QuestionRepo;
import com.MARS.QuizApplication.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;

    public List<Question> addQuestions(List<Question> questions){
        return questionRepo.saveAll(questions);
    }

    public Optional<Question> getQuestionById(int id){
        return questionRepo.findById(id);
    }
}
