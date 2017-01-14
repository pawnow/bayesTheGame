package app.service;

import app.exception.NotEnoughMoneyException;
import app.model.Event;
import app.model.Insurance;
import app.model.Location;
import app.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    public Event nextTurn(String playerName) {
        playerRepository.getPlayerByName(playerName).ifPresent(player2 -> {
            player2.setAge(player2.getAge()+1);
        });
        return new Event("You have been seriously injured");
    }

    public void travel(String playerName, Location location) {
        decreasePlayerMoney(playerName, location.getPrice());
        playerRepository.getPlayerByName(playerName).ifPresent(player2 -> {
            player2.setLocation(location.getName());
        });
    }

    public void buyInsurance(String playerName, Insurance insurance) {
        decreasePlayerMoney(playerName, insurance.getPrice());
        insuranceRepository.addInsuranceToPlayer(playerName, insurance);
    }

    private void decreasePlayerMoney(String playerName, int price) {
        playerRepository.getPlayerByName(playerName).ifPresent(player1 -> {
            decreaseMoneyIfPlayerIsRichEnough(player1, price);
        });
    }

    private void decreaseMoneyIfPlayerIsRichEnough(Player player, int price) {
        int playerMoney = player.getMoney();
        if(playerMoney < price){
            throw new NotEnoughMoneyException();
        }
        player.setMoney(player.getMoney() - price);
    }

}
