package app.service;

import app.exception.NotEnoughMoneyException;
import app.model.Event;
import app.model.Insurance;
import app.model.Location;
import app.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BayesService bayesService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    public List<Event> nextTurn(String playerName) {
        playerRepository.getPlayerByName(playerName).ifPresent(player2 -> {
            player2.setAge(player2.getAge()+1);
            bayesService.updatePlayerLocationAndWeather(player2);
        });
        List<Event> eventsBasedOnBayesOutputs = bayesService.getEventsBasedOnBayesOutputs();
        eventsBasedOnBayesOutputs.forEach(event -> handleEvent(playerName, event));
        return eventsBasedOnBayesOutputs;
    }

    private void handleEvent(String playerName, Event event) {
        Insurance insurance = event.getInsurance();
        if(insuranceRepository.isPlayerInsured(playerName, insurance)){
            insuranceRepository.removePlayerInsurance(playerName, insurance);
        }
        else{
            event.setMoneyChange(-event.getMoneyChange());
        }
        changePlayerMoney(playerName, event.getMoneyChange());
    }

    public void travel(String playerName, Location location) {
        changePlayerMoney(playerName, -location.getPrice());
        playerRepository.getPlayerByName(playerName).ifPresent(player2 -> {
            player2.setLocation(location.getName());
        });
    }

    public void buyInsurance(String playerName, Insurance insurance) {
        changePlayerMoney(playerName, -insurance.getPrice());
        insuranceRepository.addInsuranceToPlayer(playerName, insurance);
    }

    private void changePlayerMoney(String playerName, int price) {
        playerRepository.getPlayerByName(playerName).ifPresent(player1 -> {
            changeMoneyIfPlayerIsRichEnough(player1, price);
        });
    }

    private void changeMoneyIfPlayerIsRichEnough(Player player, int moneyChange) {
        int playerMoney = player.getMoney();
        if(playerMoney + moneyChange < 0){
            throw new NotEnoughMoneyException();
        }
        player.setMoney(player.getMoney() + moneyChange);
    }

}
