package com.MARS.QuizApplication.service;

import com.MARS.QuizApplication.dao.PlayerRepo;
import com.MARS.QuizApplication.models.Player;
import com.MARS.QuizApplication.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepo playerRepo;
    @Autowired
    QuestionService questionService;

    public Player registerPlayer(Player player){
        return playerRepo.save(player);
    }

    public Player login(String email,String password){
        Player player=playerRepo.findByEmail(email);
        if(player!=null){
            if(player.getPassword().equals(password)){
                return player;
            }
            else{
                return null;
            }
        }
        return null;
    }

    public Player getPlayerById(int id){
        return playerRepo.findById(id).get();
    }

    public Player validateAnswer(int player_id,int question_id,String answer){
        System.out.println("Into service-------------------------");
        Player player=getPlayerById(player_id);
        System.out.println("Player object by Id "+player+"-----------");
        Question question=questionService.getQuestionById(question_id).get();
        System.out.println("Question object--------------"+question);
        System.out.println("Answer: "+answer+"---------------------------");
        System.out.println(question.getAnswer().equals(answer));
        if(questionService.getQuestionById(question_id).get().getAnswer().equals(answer)){
            System.out.println("updating score -------------------------------");
            player.setCurrent_score(player.getCurrent_score()+1);
            playerRepo.save(player);

        }
        if(question_id==10){
            if(player.getCurrent_score()>player.getHighScore()){
                player.setHighScore(player.getCurrent_score());
            }
                player.setCurrent_score(0);
                playerRepo.save(player);
        }

     return player;
    }

    public List<Player> topPlayers(){
        List<Player> players=playerRepo.findAll();
        players.sort((p1, p2) -> Integer.compare(p2.getHighScore(), p1.getHighScore()));
        if(players.size()>3){
            List<Player> topPlayers=new ArrayList<>();
            for(int i=0;i<3;i++){
                topPlayers.add(players.get(i));
            }
            return topPlayers;
        }
        return players;
    }
}


