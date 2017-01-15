package app.bayes;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import smile.Network;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static app.bayes.BayesDictionary.*;

@Component
public class Polygon implements InitializingBean {

    private Network network = new Network();
    private Random random = new Random();
    private List<String> ubezpieczenia;
    private List<Evidence> evidences;

    @Override
    public void afterPropertiesSet() throws Exception {
        network.readFile("SE_Network.xdsl");
        evidences = getEntryNodes(network);
        ubezpieczenia = getExitNodes(network);

        exampleRun();
    }

    private void exampleRun() {

        getEvidences(network);
        network.updateBeliefs();

        System.out.println("Wydarzyly sie nastepujace rzeczy:");
        evidences.forEach(e ->
                System.out.println(e.name + " " + findProbability(e.name)));

        System.out.println("Szanse na wyplate odszkodowania na podstawie sieci bayesa wygladaja nastepujaco:");

        ubezpieczenia.forEach(ubezpieczenie ->
            System.out.println(ubezpieczenie + " " + findProbability(ubezpieczenie)));
    }

    private double findProbability(String ubezpieczenie) {
        String[] aForecastOutcomeIds = network.getOutcomeIds(ubezpieczenie);
        int outcomeIndex;
        for (outcomeIndex = 0; outcomeIndex < aForecastOutcomeIds.length; outcomeIndex++)
            if (TAK.equals(aForecastOutcomeIds[outcomeIndex]))
                break;

        return network.getNodeValue(ubezpieczenie)[outcomeIndex];
    }

    private List<Evidence> getEntryNodes(Network network) {
        List<Evidence> nodes = new ArrayList<>();
        network.updateBeliefs();
        for (int id : network.getAllNodes()) {
            if (network.getParents(id).length < 1) {
                Evidence e = new Evidence();
                String name = network.getNodeId(id);
                e.name = name;
                e.probability = findProbability(name);
                nodes.add(e);
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

    private void getEvidences(Network network) {
        evidences.forEach(evidence ->
            network.setEvidence(evidence.name, evidence.probability > random.nextDouble() ? TAK : NIE));
    }

    private class Evidence {
        String name;
        double probability;
    }
}
