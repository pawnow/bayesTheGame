package app.service;

import app.model.Event;
import app.model.Player;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smile.Network;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static app.bayes.BayesDictionary.*;

@Service
public class BayesService implements InitializingBean {

    private Network network = new Network();
    private Random random = new Random();

    @Override
    public void afterPropertiesSet() throws Exception {
        network.readFile("SE_Network.xdsl");
    }

    @Autowired
    private InsuranceRepository insuranceRepository;

    public void updatePlayerLocationAndWeather(Player player2) {
        //TODO: update network inputs based on provided data
    }

    public List<Event> getEventsBasedOnBayesOutputs() {
        //TODO: iterate over insurances, check if event occurs, return list of events happened
        //TODO: when event occurs check if player has this insurance, if not return money multiplied by -1
        return Arrays.asList(new Event("Event1", 10, insuranceRepository.getInsuranceByName(HEALTH_INSURANCE)),
                new Event("Event2", -5, insuranceRepository.getInsuranceByName(HOUSE_INSURANCE)));
    }
}
