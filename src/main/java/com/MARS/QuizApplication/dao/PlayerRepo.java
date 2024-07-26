package com.MARS.QuizApplication.dao;

import com.MARS.QuizApplication.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepo extends JpaRepository<Player,Integer> {
    Player findByEmail(String email);
}
