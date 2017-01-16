package app.service;

import app.model.Score;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreRepository {
    private List<Score> scores = new ArrayList<>();

    public void saveScore(Score score) {
        scores.add(score);
    }

    public List<Score> getHighscores() {
        scores.sort((o1, o2) -> o1.getMoney() - o2.getMoney());
        return scores.stream().limit(20).collect(Collectors.toList());
    }

}
