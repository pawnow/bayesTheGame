package app.controller;

import app.model.Event;
import app.model.Insurance;
import app.model.Location;
import app.model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class MockController {

    private List<Player> players = new ArrayList<>();
    private Map<String, List<Insurance>> insuranceMap = new HashMap<>();

    @RequestMapping(value = "/player/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Player createPlayer(@RequestBody Player player) {
        players.add(player.withAge(5).withLocation("Sea").withMoney(1000).withWeather("Sunny"));
        return player;
    }

    @RequestMapping(value = "/player/nextTurn", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Event nextTurn(@RequestBody Player player) {
        players.stream().filter(player1 -> player.getName().equals(player1.getName())).findAny().ifPresent(player2 -> {
            player2.setAge(player2.getAge()+1);
        });
        return new Event("You have been seriously injured", 1);
    }

    @RequestMapping(value = "/player/{name}/buyInsurance", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<Insurance> buyInsurance(@PathVariable String name, @RequestBody Insurance insurance) {
        List<Insurance> insurances = insuranceMap.get(name);
        players.stream().filter(player -> name.equals(player.getName())).findAny().ifPresent(player1 -> {
            player1.setMoney(player1.getMoney() - insurance.getPrice());
        });
        if(insurances != null){
            insurances.add(insurance);
        }
        else{
            insuranceMap.put(name, new ArrayList(Arrays.asList(insurance)));
        }
        return insuranceMap.get(name);
    }

    @RequestMapping(value = "/player/{name}/travel", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Player travel(@PathVariable String name, @RequestBody Location location) {
        players.stream().filter(player -> name.equals(player.getName())).findAny().ifPresent(player1 -> {
            player1.setLocation(location.getName());
            player1.setMoney(player1.getMoney() - location.getPrice());
        });
        return getPlayerInfo(name);
    }

    @RequestMapping(value = "/player/{name}/info", method = RequestMethod.GET, produces = "application/json")
    public Player getPlayerInfo(@PathVariable String name) {
        return players.stream().filter(player -> name.equals(player.getName())).findAny().orElseGet(() -> null);
    }

    @RequestMapping(value = "/player/{name}/insurances", method = RequestMethod.GET, produces = "application/json")
    public List<Insurance> getPlayerInsurances(@PathVariable String name) {
        return insuranceMap.get(name);
    }

    @RequestMapping(value = "/insurances/{name}", method = RequestMethod.GET, produces = "application/json")
    public List<Insurance> getInsurances(@PathVariable String name) {
        return Arrays.asList(new Insurance("Health", 100), new Insurance("Car", 30), new Insurance("House", 50));
    }

    @RequestMapping(value = "/locations", method = RequestMethod.GET, produces = "application/json")
    public List<Location> getLocations() {
        return Arrays.asList(new Location("Sea", 100), new Location("Village", 150), new Location("City", 250), new Location("Mountains", 200));
    }

}
