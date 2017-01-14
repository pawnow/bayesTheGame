package app.service;

import app.model.Insurance;
import app.model.Player;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InsuranceRepository {
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

    public List<Insurance> getPlayerInsurances(String playerName) {
        return insuranceMap.get(playerName);
    }

}
