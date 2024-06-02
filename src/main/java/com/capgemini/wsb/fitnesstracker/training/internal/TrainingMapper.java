package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.springframework.stereotype.Component;


/**
 * Komponent odpowiedzialny za mapowanie obiektów Training na TrainingDto i odwrotnie.
 * Umożliwia przekształcenie danych treningowych pomiędzy formatem używanym w warstwie persystencji a formatem przesyłania danych.
 */
@Component
public class TrainingMapper {

    /**
     * Konwertuje obiekt Training na obiekt TrainingDto.
     *
     * @param training Obiekt Training, który ma zostać przekonwertowany.
     * @return Obiekt TrainingDto zawierający dane z obiektu Training.
     */
    public TrainingDto toDto(Training training) {
        TrainingDto dto = new TrainingDto();
        dto.setId(training.getId());
        dto.setStartTime(training.getStartTime());
        dto.setEndTime(training.getEndTime());
        dto.setActivityType(training.getActivityType().name());
        dto.setDistance(training.getDistance());
        dto.setAverageSpeed(training.getAverageSpeed());
        return dto;
    }

    /**
     * Konwertuje obiekt TrainingDto na obiekt Training.
     *
     * @param dto Obiekt TrainingDto, który ma zostać przekonwertowany na encję.
     * @return Obiekt Training utworzony na podstawie danych z TrainingDto.
     */
    public Training toEntity(TrainingDto dto) {
        Training training = new Training();
        training.setId(dto.getId());
        training.setStartTime(dto.getStartTime());
        training.setEndTime(dto.getEndTime());
        training.setActivityType(ActivityType.valueOf(dto.getActivityType()));
        training.setDistance(dto.getDistance());
        training.setAverageSpeed(dto.getAverageSpeed());
        return training;
    }
}
