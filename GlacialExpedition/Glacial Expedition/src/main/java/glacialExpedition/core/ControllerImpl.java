package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    
    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;
    private int exploredStates;
    
    public ControllerImpl() {
        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
        this.exploredStates = 0;
    }
    
    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_INVALID_TYPE));
        }
        
        explorerRepository.add(explorer);
        return String.format(ConstantMessages.EXPLORER_ADDED, type, explorerName);
    }
    
    @Override
    public String addState(String stateName, String... exhibits) {
        
        State state = new StateImpl(stateName);

//        for (String exhibit : exhibits) {
//            state.getExhibits().add(exhibit);
//        }
        
        Collections.addAll(state.getExhibits(), exhibits);
        stateRepository.add(state);
        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }
    
    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        
        if (explorer == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST, explorerName));
        }
        
        explorerRepository.remove(explorer);
        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }
    
    @Override
    public String exploreState(String stateName) {
        List<Explorer> suitableExplorers = new ArrayList<>();
//        suitableExplorers = this.explorerRepository.getCollection().stream().filter(e -> e.getEnergy() > 50).collect(Collectors.toList());
        for (Explorer explorer : explorerRepository.getCollection()) {
            if (explorer.getEnergy() > 50) {
                suitableExplorers.add(explorer);
            }
        }
        
        if (suitableExplorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }
        
        State state = stateRepository.byName(stateName);
        
        Mission mission = new MissionImpl();
        mission.explore(state, suitableExplorers);
        
        long retiredExplorers = suitableExplorers
                .stream()
                .filter(e -> e.getEnergy() == 0)
                .count();
        this.exploredStates++;
        
        return String.format(ConstantMessages.STATE_EXPLORER, stateName, retiredExplorers);
    }
    
    @Override
    public String finalResult() {
        StringBuilder builder = new StringBuilder();
        
        builder.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED, this.exploredStates)).append(System.lineSeparator());
        builder.append(ConstantMessages.FINAL_EXPLORER_INFO).append(System.lineSeparator());
        
        builder.append(explorerRepository.toString());
        
        return builder.toString().trim();
    }
}
