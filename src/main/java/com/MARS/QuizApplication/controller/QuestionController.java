package com.MARS.QuizApplication.controller;

import com.MARS.QuizApplication.models.Question;
import com.MARS.QuizApplication.service.QuestionService;
import com.MARS.QuizApplication.utilities.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @PostMapping
    public ResponseEntity<BaseResponse<List<Question>>> addQuestions(@RequestBody List<Question> questions) {
        BaseResponse<List<Question>> response = new BaseResponse<>();
        response.setData(questionService.addQuestions(questions));
        response.setMessage("Questions added successfully");
        response.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Question>> getQuestionById(@PathVariable int id){
        BaseResponse<Question> response=new BaseResponse<>();
        response.setData(questionService.getQuestionById(id).get());
        response.setMessage("Question with id:"+id+" fetched successfully");
        response.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}
