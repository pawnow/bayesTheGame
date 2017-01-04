package app.bayes;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import smile.Network;

import java.util.Arrays;
import java.util.List;

import static app.bayes.BayesDictionary.*;

@Component
public class Polygon implements InitializingBean {

    private Network network = new Network();

    private List<String> ubezpieczenia = Arrays.asList(ZYCIE, MIESZKANIA, ZDROWOTNE, SAMOCHODU);

    @Override
    public void afterPropertiesSet() throws Exception {
        network.readFile("bayesTheGameNetwork3000.xdsl");
        network.setEvidence(ULEWA, TAK);
        network.setEvidence(BURZA, TAK);
        network.setEvidence(GRAD, TAK);
        network.setEvidence(SNIEG, NIE);
        network.setEvidence(ZIMNO, NIE);
        network.updateBeliefs();

        System.out.println("Szanse na wyplate odszkodowania na podstawie sieci bayesa wygladaja nastepujaco:");

        ubezpieczenia.forEach(ubezpieczenie -> {
            String[] aForecastOutcomeIds = network.getOutcomeIds(ubezpieczenie);
            int outcomeIndex;
            for (outcomeIndex = 0; outcomeIndex < aForecastOutcomeIds.length; outcomeIndex++)
                if (TAK.equals(aForecastOutcomeIds[outcomeIndex]))
                    break;
            System.out.println(ubezpieczenie + " " + network.getNodeValue(ubezpieczenie)[outcomeIndex]);
        });
    }
}
