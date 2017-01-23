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
        Player newPlayer = Player.builder().name(player.getName()).age(5).location("Sea").money(80).weather("Sunny").build();
        players.add(newPlayer);
        return newPlayer;
    }

    public Optional<Player> getPlayerByName(String name) {
        return players.stream().filter(player1 -> name.equals(player1.getName())).findAny();
    }

    public void removePlayer(String name) {
        getPlayerByName(name).ifPresent(player -> players.remove(player));
    }


}
