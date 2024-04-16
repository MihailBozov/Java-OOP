package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;
import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MissionImpl implements Mission {
    
    @Override
    public void explore(State state, Collection<Explorer> explorers) {
//        Suitcase carton = new Carton();
        
        Collection<String> exhibitsOnState = state.getExhibits();
        
        for (Explorer explorer : explorers) {
            while (explorer.canSearch() && exhibitsOnState.iterator().hasNext()) {
                explorer.search();
                String exhibit = exhibitsOnState.iterator().next();
                explorer.getSuitcase().getExhibits().add(exhibit);
                exhibitsOnState.remove(exhibit);
            }
        }
    }
}
