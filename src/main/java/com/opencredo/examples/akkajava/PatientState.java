package com.opencredo.examples.akkajava;

public class PatientState {
    public static enum ClinicalState {
        F, // Fever
        H, // Healthy
        D, // Diabetes
        T, // Tubercolosis  
        X // Dead        
    }

    public static final ClinicalState[] states = ClinicalState.values();

    public final ClinicalState state;

    private PatientState(final ClinicalState type) {
        this.state = type;
    }

    public static PatientState ofType(ClinicalState type) {
        return new PatientState(type);
    }

    @Override
    public String toString() {
        return state.name();
    }
}
