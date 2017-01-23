package app.service;

import app.model.Event;
import app.model.Insurance;
import app.model.Player;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smile.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static app.bayes.BayesDictionary.*;

@Service
public class BayesService implements InitializingBean {

    private Network network = new Network();
    private Random random = new Random();
    private List<String> evidences;
    private List<String> insurances;

    @Override
    public void afterPropertiesSet() throws Exception {
        network.readFile("SE_Network.xdsl");
        evidences = getEntryNodes(network);
        insurances = getExitNodes(network);
    }

    @Autowired
    private InsuranceRepository insuranceRepository;

    public void updatePlayerLocationAndWeather(Player player2) {
        //TODO: update network inputs based on provided data
        setWeatherAndLocationInBayesNetwork(player2.getWeather(), player2.getLocation());
        network.updateBeliefs();
    }

    public List<Event> getEventsBasedOnBayesOutputs(String playerName) {
        //TODO: iterate over insurances, check if event occurs, return list of events happened
        return insurances.stream()
                .filter(insurance -> insuranceRepository.getPlayerInsurances(playerName).contains(insuranceRepository.getInsuranceByName(insurance)))
                .filter(this::isInsuranceApplied)
                .map(this::createInsurancePaidEvent)
                .collect(Collectors.toList());
        //TODO: when event occurs check if player has this insurance, if not return money multiplied by -1
    }

    public String getNextWeather() {
        return evidences.get(Math.abs(random.nextInt()) % evidences.size());
    }

    private Event createInsurancePaidEvent(String insuranceName) {
        Insurance insurance = insuranceRepository.getInsuranceByName(insuranceName);
        return new Event("You get money from " + insurance.getPrettyName(), insurance.getPrice() * 2, insurance);
    }
    
    private boolean isInsuranceApplied(String insurance) {
        return random.nextDouble() < findProbability(insurance);
    }
    
    private void setWeatherAndLocationInBayesNetwork(String actualWeather, String location) {
        evidences.forEach(weather -> network.setEvidence(weather, weather.equals(actualWeather) || weather.equals(location) ? TAK : NIE));
    }

    private List<String> getEntryNodes(Network network) {
        List<String> nodes = new ArrayList<>();
        network.updateBeliefs();
        for (int id : network.getAllNodes()) {
            if (network.getParents(id).length < 1) {
                nodes.add(network.getNodeId(id));
            }
        }
        return nodes;
    }


    private List<String> getExitNodes(Network network) {
        List<String> nodes = new ArrayList<>();
        for (int id : network.getAllNodes()) {
            if (network.getChildren(id).length < 1) {
                nodes.add(network.getNodeId(id));
            }
        }

        return nodes;
    }


    private double findProbability(String insurance) {
        String[] aForecastOutcomeIds = network.getOutcomeIds(insurance);
        int outcomeIndex;
        for (outcomeIndex = 0; outcomeIndex < aForecastOutcomeIds.length; outcomeIndex++)
            if (TAK.equals(aForecastOutcomeIds[outcomeIndex]))
                break;
        System.out.println("Insurance: " + insurance + " has probability: " + network.getNodeValue(insurance)[outcomeIndex]);
        return network.getNodeValue(insurance)[outcomeIndex];
    }
}
