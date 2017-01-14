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
        players.add(player.builder().age(5).location("Sea").money(1000).weather("Sunny").build());
        return player;
    }

    public Optional<Player> getPlayerByName(String name) {
        return players.stream().filter(player1 -> name.equals(player1.getName())).findAny();
    }

}
