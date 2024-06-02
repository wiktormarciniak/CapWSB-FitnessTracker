package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Kontroler REST służący do zarządzania danymi treningowymi.
 * Umożliwia tworzenie, odczytywanie, aktualizację oraz usuwanie informacji o treningach.
 */
@RestController
@RequestMapping("/api/trainings")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private TrainingMapper trainingMapper;

    /**
     * Tworzy nowy trening na podstawie danych przekazanych w DTO.
     *
     * @param trainingDto DTO zawierające dane treningu.
     * @return ResponseEntity zawierający DTO utworzonego treningu.
     */
    @PostMapping
    public ResponseEntity<TrainingDto> createTraining(@RequestBody TrainingDto trainingDto) {
        Training createdTraining = trainingService.createTraining(trainingMapper.toEntity(trainingDto));
        return ResponseEntity.ok(trainingMapper.toDto(createdTraining));
    }

    /**
     * Pobiera wszystkie treningi z bazy danych.
     *
     * @return ResponseEntity z listą DTO wszystkich treningów.
     */
    @GetMapping
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        List<TrainingDto> dtos = trainingService.findAllTrainings().stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Pobiera treningi przypisane do konkretnego użytkownika.
     *
     * @param userId Identyfikator użytkownika.
     * @return ResponseEntity z listą DTO treningów danego użytkownika.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TrainingDto>> getTrainingsByUserId(@PathVariable Long userId) {
        List<TrainingDto> dtos = trainingService.findTrainingsByUserId(userId).stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Pobiera treningi, które zostały zakończone po określonej dacie.
     *
     * @param date Data, po której szukane są zakończone treningi.
     * @return ResponseEntity z listą DTO zakończonych treningów.
     */
    @GetMapping("/completed")
    public ResponseEntity<List<TrainingDto>> getCompletedTrainings(@RequestParam("date") String date) {
        List<TrainingDto> dtos = trainingService.findCompletedTrainings(date).stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Pobiera treningi według typu aktywności.
     *
     * @param activityType Typ aktywności, np. bieganie, pływanie.
     * @return ResponseEntity z listą DTO treningów tego typu.
     */
    @GetMapping("/activity/{activityType}")
    public ResponseEntity<List<TrainingDto>> getTrainingsByActivity(@PathVariable String activityType) {
        List<TrainingDto> dtos = trainingService.findTrainingsByActivityType(activityType).stream()
                .map(trainingMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Aktualizuje dane treningu na podstawie przekazanego DTO.
     *
     * @param trainingId Identyfikator treningu do zaktualizowania.
     * @param trainingDto DTO z nowymi danymi treningu.
     * @return ResponseEntity z DTO zaktualizowanego treningu.
     */
    @PutMapping("/{trainingId}")
    public ResponseEntity<TrainingDto> updateTraining(@PathVariable Long trainingId, @RequestBody TrainingDto trainingDto) {
        Training updatedTraining = trainingService.updateTraining(trainingId, trainingMapper.toEntity(trainingDto));
        return ResponseEntity.ok(trainingMapper.toDto(updatedTraining));
    }
}
