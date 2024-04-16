package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.*;

public class ExplorerRepository implements Repository<Explorer> {
    
    private Collection<Explorer> explorers;
    
    public ExplorerRepository() {
        this.explorers = new ArrayList<>();
    }
    
    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(explorers);
    }
    
    @Override
    public void add(Explorer entity) {
        this.explorers.add(entity);
    }
    
    @Override
    public boolean remove(Explorer entity) {
        return this.explorers.remove(entity);
    }
    
    @Override
    public Explorer byName(String name) {
//        return explorers
//                .stream()
//                .filter(ex -> ex.getName().equals(name))
//                .findFirst()
//                .orElse(null);
        
        for (Explorer explorer : explorers) {
            if (explorer.getName().equals(name)) {
                return explorer;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.explorers.forEach(explorer -> builder.append(explorer.toString()));
        return builder.toString().trim();
    }
    
}
