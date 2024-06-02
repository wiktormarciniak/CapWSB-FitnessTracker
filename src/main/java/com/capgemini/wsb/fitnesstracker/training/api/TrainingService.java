package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import java.util.List;

/**
 * Interfejs definiujący usługi zarządzania treningami.
 * Zawiera operacje umożliwiające tworzenie, aktualizację, wyszukiwanie i pobieranie treningów.
 */
public interface TrainingService {

    /**
     * Tworzy nowy trening i zapisuje go w bazie danych.
     *
     * @param training Obiekt treningu do zapisania.
     * @return Zapisany obiekt treningu.
     */
    Training createTraining(Training training);

    /**
     * Zwraca listę wszystkich treningów zarejestrowanych w systemie.
     *
     * @return Lista wszystkich treningów.
     */
    List<Training> findAllTrainings();

    /**
     * Wyszukuje wszystkie treningi przypisane do konkretnego użytkownika.
     *
     * @param userId Identyfikator użytkownika, dla którego mają być wyszukane treningi.
     * @return Lista treningów przypisanych do użytkownika.
     */
    List<Training> findTrainingsByUserId(Long userId);

    /**
     * Wyszukuje wszystkie treningi, które zostały zakończone po podanej dacie.
     *
     * @param date Data w formacie String, określająca minimalną datę zakończenia treningów do wyszukania.
     * @return Lista treningów zakończonych po podanej dacie.
     */
    List<Training> findCompletedTrainings(String date);

    /**
     * Wyszukuje treningi według określonego typu aktywności.
     *
     * @param activityType Typ aktywności, dla którego mają być wyszukane treningi.
     * @return Lista treningów odpowiadających podanemu typowi aktywności.
     */
    List<Training> findTrainingsByActivityType(String activityType);

    /**
     * Aktualizuje istniejący trening na podstawie dostarczonego obiektu treningu.
     *
     * @param trainingId Identyfikator treningu, który ma zostać zaktualizowany.
     * @param training Obiekt treningu z nowymi danymi.
     * @return Zaktualizowany obiekt treningu.
     */
    Training updateTraining(Long trainingId, Training training);
}