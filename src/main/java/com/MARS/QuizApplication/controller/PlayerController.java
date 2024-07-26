package com.MARS.QuizApplication.controller;

import com.MARS.QuizApplication.models.Player;
import com.MARS.QuizApplication.service.PlayerService;
import com.MARS.QuizApplication.utilities.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @PostMapping
    public ResponseEntity<BaseResponse<Player>> registerPlayer(@RequestBody Player player){
        BaseResponse<Player> baseResponse=new BaseResponse<>();
        baseResponse.setData(playerService.registerPlayer(player));
        baseResponse.setMessage("Player registartion successfull");
        baseResponse.setStatus(HttpStatus.CREATED.value());
        return new ResponseEntity<>(baseResponse,HttpStatus.CREATED);
    }
    @GetMapping("/login")
    public ResponseEntity<BaseResponse<Player>> login(@RequestParam String email,@RequestParam String password){
        Player player=playerService.login(email,password);
        BaseResponse<Player> baseResponse=new BaseResponse<>();
        baseResponse.setData(player);
        if(player==null){
            baseResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            baseResponse.setMessage("Incorrect credentials");
        }
        else{
            baseResponse.setStatus(HttpStatus.OK.value());
            baseResponse.setMessage("Login successfull");
        }
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
    @PostMapping("/play")
    public ResponseEntity<BaseResponse<Player>> validateAnswer(@RequestParam int player_id,@RequestParam int question_id,@RequestParam String answer){
        Player player=playerService.validateAnswer(player_id,question_id,answer);
        BaseResponse<Player> baseResponse=new BaseResponse<>();
        baseResponse.setData(player);
        if(player_id==10){
            baseResponse.setMessage("Thank you for participating!!! score: "+player.getCurrent_score());
            baseResponse.setStatus(HttpStatus.OK.value());
        }
        else{
            baseResponse.setMessage("Update score: "+player.getCurrent_score());
            baseResponse.setStatus(HttpStatus.OK.value());
        }
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }
    @PostMapping("/dashboard")
    public ResponseEntity<BaseResponse<List<Player>>> topPlayers(){
        BaseResponse<List<Player>> baseResponse=new BaseResponse<>();
        baseResponse.setData(playerService.topPlayers());
        baseResponse.setMessage("List of top players");
        baseResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }
}
