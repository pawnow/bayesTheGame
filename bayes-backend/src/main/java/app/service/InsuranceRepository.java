package app.service;

import app.model.Insurance;
import app.model.Player;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Repository
public class InsuranceRepository {
    private static final List<Insurance> allPossibleInsurances = Collections.unmodifiableList(Arrays.asList(new Insurance("HealthInsurance", 100),
            new Insurance("HouseInsurance", 30), new Insurance("CarInsurance", 50), new Insurance("DisasterInsurance", 60),
            new Insurance("TheftInsurance", 20), new Insurance("HolidayInsurance", 90)));
    private Map<String, List<Insurance>> insuranceMap = new HashMap<>();

    public void addInsuranceToPlayer(String playerName, Insurance insurance) {
        List<Insurance> insurances = insuranceMap.get(playerName);
        if(insurances != null){
            insurances.add(insurance);
        }
        else{
            insuranceMap.put(playerName, new ArrayList(Arrays.asList(insurance)));
        }
    }

    public void removePlayerInsurance(String playerName, Insurance insurance) {
        List<Insurance> insurances = insuranceMap.get(playerName);
        if(insurances != null){
            insurances.remove(insurance);
        }
    }

    public List<Insurance> getPlayerInsurances(String playerName) {
        return insuranceMap.get(playerName);
    }

    public boolean isPlayerInsured(String playerName, Insurance insurance) {
        return Optional.of(insuranceMap.get(playerName)).filter(insurances -> insurances.contains(insurance)).isPresent();
    }

    public List<Insurance> getAllPossibleInsurances() {
        return allPossibleInsurances;
    }

    public Insurance getInsuranceByName(String insuranceName) {
        return allPossibleInsurances.stream().filter(insurance -> insuranceName.equals(insurance.getName())).findAny().orElse(null);
    }

}
