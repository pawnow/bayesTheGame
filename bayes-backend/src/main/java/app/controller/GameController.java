package app.controller;

import app.model.Event;
import app.model.Insurance;
import app.model.Location;
import app.model.Player;
import app.service.InsuranceRepository;
import app.service.PlayerRepository;
import app.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GameController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private PlayerService playerService;

    @RequestMapping(value = "/player/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Player createPlayer(@RequestBody Player player) {
        playerRepository.createPlayer(player);
        return player;
    }

    @RequestMapping(value = "/player/nextTurn", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Event nextTurn(@RequestBody Player player) {
        return playerService.nextTurn(player.getName());
    }

    @RequestMapping(value = "/player/{name}/buyInsurance", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public List<Insurance> buyInsurance(@PathVariable String name, @RequestBody Insurance insurance) {
        playerService.buyInsurance(name, insurance);
        return insuranceRepository.getPlayerInsurances(name);
    }

    @RequestMapping(value = "/player/{name}/travel", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Player travel(@PathVariable String name, @RequestBody Location location) {
        playerService.travel(name, location);
        return getPlayerInfo(name);
    }

    @RequestMapping(value = "/player/{name}/info", method = RequestMethod.GET, produces = "application/json")
    public Player getPlayerInfo(@PathVariable String name) {
        return playerRepository.getPlayerByName(name).orElse(null);
    }

    @RequestMapping(value = "/player/{name}/insurances", method = RequestMethod.GET, produces = "application/json")
    public List<Insurance> getPlayerInsurances(@PathVariable String name) {
        return insuranceRepository.getPlayerInsurances(name);
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
