package glacialExpedition.repositories;

import glacialExpedition.models.states.State;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StateRepository implements Repository<State> {
    Collection<State> states;
    
    public StateRepository() {
        states = new ArrayList<>();
    }
    
    @Override
    public Collection<State> getCollection() {
        return Collections.unmodifiableCollection(this.states);
    }
    
    @Override
    public void add(State entity) {
        boolean isExist = this.states
                .stream()
                .anyMatch(state -> state.getName().equals(entity.getName()));
        
        if (!isExist) {
            this.states.add(entity);
        }
    }
    
    @Override
    public boolean remove(State entity) {
        return this.states.remove(entity);
    }
    
    @Override
    public State byName(String name) {
//        return this.states
//                .stream()
//                .filter(state -> state.getName().equals(name))
//                .findFirst()
//                .orElse(null);
        for (State state : states) {
            if (state.getName().equals(name)) {
                return state;
            }
        }
        return null;
    }
}
