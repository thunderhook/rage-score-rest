package com.thunderhook.ragescore.controller;

import com.thunderhook.ragescore.entity.Game;
import com.thunderhook.ragescore.entity.Player;
import com.thunderhook.ragescore.entity.Round;
import com.thunderhook.ragescore.entity.Score;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {

    private static final Player P1 = new Player("Player 1");
    private static final Player P2 = new Player("Player 2");
    private static final Player P3 = new Player("Player 3");

    @GetMapping("/samplegame")
    public Game samplegame()  {
        Game game = new Game();
        game.setRounds(initRounds());
        return game;
    }

    private List<Round> initRounds() {
        ArrayList<Round> rounds = new ArrayList<>();

        Round round1 = new Round();
        round1.setNumber(1);
        round1.setScores(initScores());
        rounds.add(round1);

        return rounds;
    }

    private List<Score> initScores() {
        ArrayList<Score> scores = new ArrayList<>();
        scores.add(new Score(P1, 10));
        scores.add(new Score(P2, 11));
        scores.add(new Score(P3, -5));
        return scores;
    }

}
