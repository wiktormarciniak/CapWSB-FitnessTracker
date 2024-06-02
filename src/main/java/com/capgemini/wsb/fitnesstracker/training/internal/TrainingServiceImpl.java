package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Serwis zapewniający implementację operacji CRUD oraz wyszukiwanie dla encji Training.
 * Umożliwia zarządzanie danymi treningowymi w aplikacji.
 */
@Service
public class TrainingServiceImpl implements TrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    /**
     * Zapisuje nowy trening w bazie danych.
     *
     * @param training obiekt treningu do zapisania
     * @return zapisany obiekt treningu
     */
    @Override
    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    /**
     * Zwraca listę wszystkich treningów zarejestrowanych w systemie.
     *
     * @return lista treningów
     */
    @Override
    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    /**
     * Wyszukuje treningi przypisane do użytkownika o określonym ID.
     *
     * @param userId identyfikator użytkownika, dla którego mają być wyszukane treningi
     * @return lista treningów użytkownika
     */
    @Override
    public List<Training> findTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    /**
     * Wyszukuje treningi, które zostały zakończone po określonej dacie.
     *
     * @param date data, po której mają być wyszukane zakończone treningi
     * @return lista zakończonych treningów
     */
    @Override
    public List<Training> findCompletedTrainings(String date) {
        // Assuming the date is passed as a String and needs to be parsed
        Date parsedDate = parseDate(date); // Implement this method to parse the date
        return trainingRepository.findByEndTimeAfter(parsedDate);
    }

    /**
     * Wyszukuje treningi według typu aktywności.
     *
     * @param activityType typ aktywności, dla którego mają być wyszukane treningi
     * @return lista treningów danego typu
     */
    @Override
    public List<Training> findTrainingsByActivityType(String activityType) {
        return trainingRepository.findByActivityType(activityType);
    }

    /**
     * Aktualizuje dane istniejącego treningu na podstawie przekazanego obiektu.
     *
     * @param trainingId identyfikator treningu do zaktualizowania
     * @param training nowe dane treningu
     * @return zaktualizowany obiekt treningu
     * @throws RuntimeException jeśli trening nie zostanie znaleziony
     */
    @Override
    public Training updateTraining(Long trainingId, Training training) {
        return trainingRepository.findById(trainingId)
                .map(existingTraining -> {
                    existingTraining.setUser(training.getUser());
                    existingTraining.setStartTime(training.getStartTime());
                    existingTraining.setEndTime(training.getEndTime());
                    existingTraining.setActivityType(training.getActivityType());
                    existingTraining.setDistance(training.getDistance());
                    existingTraining.setAverageSpeed(training.getAverageSpeed());
                    return trainingRepository.save(existingTraining);
                })
                .orElseThrow(() -> new RuntimeException("Training not found"));
    }

    private Date parseDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use 'yyyy-MM-dd'.", e);
        }
    }
}