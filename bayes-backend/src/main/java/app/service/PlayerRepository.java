package app.service;

import app.model.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerRepository {
    private List<Player> players = new ArrayList<>();

    public Player createPlayer(Player player) {
        players.add(player.withAge(5).withLocation("Sea").withMoney(1000).withWeather("Sunny"));
        return player;
    }

    public Optional<Player> getPlayerByName(String name) {
        return players.stream().filter(player1 -> name.equals(player1.getName())).findAny();
    }

}
