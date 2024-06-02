package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enumeracja definiująca typy aktywności dostępne w systemie FitnessTracker.
 * Każdy typ aktywności posiada przypisany opis, który jest używany do reprezentacji tekstowej
 * danej aktywności w interfejsie użytkownika oraz w logice aplikacji.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tenis");

    private final String displayName;

    /**
     * Konstruktor dla typu aktywności, przypisujący ludzko-zrozumiałą nazwę dla każdego typu.
     *
     * @param displayName Tekstowa reprezentacja typu aktywności, używana na interfejsie użytkownika.
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Zwraca tekstową reprezentację typu aktywności.
     *
     * @return Nazwa aktywności odpowiednia do wyświetlania.
     */
    public String getDisplayName() {
        return displayName;
    }

}
