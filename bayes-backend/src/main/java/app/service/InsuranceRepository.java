package app.service;

import app.model.Insurance;
import app.model.Player;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.*;

@Repository
public class InsuranceRepository {
    private static final List<Insurance> allPossibleInsurances = Collections.unmodifiableList(Arrays.asList(
            new Insurance("HealthInsurance", 100, "health insurance"),
            new Insurance("HouseInsurance", 30, "house insurance"),
            new Insurance("CarInsurance", 50, "car insurance"),
            new Insurance("DisasterInsurance", 60, "disaster insurance"),
            new Insurance("TheftInsurance", 20, "theft insurance"),
            new Insurance("HolidayInsurance", 90, "holiday insurance")
    ));
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

    public void removeAllPlayerInsurance(String playerName) {
        List<Insurance> insurances = insuranceMap.get(playerName);
        if(insurances != null){
            insurances.clear();
        }
    }

    public List<Insurance> getPlayerInsurances(String playerName) {
        return insuranceMap.get(playerName) == null ? Collections.emptyList() : insuranceMap.get(playerName);
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
