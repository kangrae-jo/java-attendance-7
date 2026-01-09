package attendance.domain;

import java.util.Map;

public class States {

    private final String name;
    private final Map<State, Integer> states;
    private final Policy policy;

    private States(String name, Map<State, Integer> states, Policy policy) {
        this.name = name;
        this.states = states;
        this.policy = policy;
    }

    public static States from(String name, Map<State, Integer> states) {
        return new States(name, states, Policy.from(states));
    }

    public String getName() {
        return name;
    }

    public Map<State, Integer> getStates() {
        return states;
    }

    public Policy getPolicy() {
        return policy;
    }
    
}
