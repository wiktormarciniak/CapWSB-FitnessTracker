package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TrainingServiceImplTest {

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingServiceImpl trainingService;

    private Training training;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        training = new Training();
        training.setId(1L);
        training.setStartTime(new Date());
        training.setEndTime(new Date());
        training.setActivityType(ActivityType.RUNNING);
        training.setDistance(5.0);
        training.setAverageSpeed(10.0);
    }

    @Test
    void createTraining_createsAndReturnsTraining() {
        when(trainingRepository.save(any(Training.class))).thenReturn(training);

        Training createdTraining = trainingService.createTraining(training);

        assertEquals(training.getId(), createdTraining.getId());
        verify(trainingRepository, times(1)).save(training);
    }

    @Test
    void findAllTrainings_returnsAllTrainings() {
        List<Training> trainings = Arrays.asList(training);
        when(trainingRepository.findAll()).thenReturn(trainings);

        List<Training> foundTrainings = trainingService.findAllTrainings();

        assertEquals(1, foundTrainings.size());
        verify(trainingRepository, times(1)).findAll();
    }

    @Test
    void findTrainingsByUserId_returnsTrainings() {
        List<Training> trainings = Arrays.asList(training);
        when(trainingRepository.findByUserId(anyLong())).thenReturn(trainings);

        List<Training> foundTrainings = trainingService.findTrainingsByUserId(1L);

        assertEquals(1, foundTrainings.size());
        verify(trainingRepository, times(1)).findByUserId(1L);
    }

    @Test
    void findCompletedTrainings_returnsTrainings() {
        List<Training> trainings = Arrays.asList(training);
        when(trainingRepository.findByEndTimeAfter(any(Date.class))).thenReturn(trainings);

        List<Training> foundTrainings = trainingService.findCompletedTrainings("2023-06-01");

        assertEquals(1, foundTrainings.size());
        verify(trainingRepository, times(1)).findByEndTimeAfter(any(Date.class));
    }

    @Test
    void findTrainingsByActivityType_returnsTrainings() {
        List<Training> trainings = Arrays.asList(training);
        when(trainingRepository.findByActivityType(any(String.class))).thenReturn(trainings);

        List<Training> foundTrainings = trainingService.findTrainingsByActivityType("RUNNING");

        assertEquals(1, foundTrainings.size());
        verify(trainingRepository, times(1)).findByActivityType("RUNNING");
    }

    @Test
    void updateTraining_updatesAndReturnsTraining() {
        when(trainingRepository.findById(anyLong())).thenReturn(Optional.of(training));
        when(trainingRepository.save(any(Training.class))).thenReturn(training);

        Training updatedTraining = trainingService.updateTraining(1L, training);

        assertEquals(training.getId(), updatedTraining.getId());
        verify(trainingRepository, times(1)).findById(1L);
        verify(trainingRepository, times(1)).save(training);
    }

    @Test
    void updateTraining_throwsExceptionWhenTrainingNotFound() {
        when(trainingRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> trainingService.updateTraining(1L, training));
        verify(trainingRepository, times(1)).findById(1L);
    }
}
