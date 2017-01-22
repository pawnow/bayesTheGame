package app.service;

import app.exception.NotEnoughMoneyException;
import app.exception.TooManyInsurancesException;
import app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private BayesService bayesService;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Event> nextTurn(String playerName) {
        Optional<Player> player = playerRepository.getPlayerByName(playerName);
        player.ifPresent(player2 -> {
            player2.setAge(player2.getAge()+1);
            player2.setWeather(bayesService.getNextWeather());
            bayesService.updatePlayerLocationAndWeather(player2);
        });
        List<Event> eventsBasedOnBayesOutputs = bayesService.getEventsBasedOnBayesOutputs(playerName);
        eventsBasedOnBayesOutputs.forEach(event -> handleEvent(playerName, event));
        if(player.map(Player::getAge).orElse(0) > 50){
            eventsBasedOnBayesOutputs.add(new Event("You die", 0, null));
            scoreRepository.saveScore(new Score(playerName, player.map(Player::getMoney).orElse(0)));
            playerRepository.removePlayer(playerName);
            insuranceRepository.removeAllPlayerInsurance(playerName);
        }
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
        if(insuranceRepository.getPlayerInsurances(playerName).size() >= 3){
            throw new TooManyInsurancesException();
        }
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
