package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrainingMapperTest {

    private TrainingMapper trainingMapper;
    private Training training;
    private TrainingDto trainingDto;

    @BeforeEach
    void setUp() {
        trainingMapper = new TrainingMapper();

        User user = new User("John", "Doe", LocalDate.of(1985, 5, 15), "john.doe@example.com");

        training = new Training();
        training.setId(1L);
        training.setUser(user);
        training.setStartTime(new Date());
        training.setEndTime(new Date());
        training.setActivityType(ActivityType.RUNNING);
        training.setDistance(5.0);
        training.setAverageSpeed(10.0);

        trainingDto = new TrainingDto();
        trainingDto.setId(1L);
        trainingDto.setStartTime(new Date());
        trainingDto.setEndTime(new Date());
        trainingDto.setActivityType("RUNNING");
        trainingDto.setDistance(5.0);
        trainingDto.setAverageSpeed(10.0);
    }

    @Test
    void toDto_convertsToDto() {
        TrainingDto dto = trainingMapper.toDto(training);

        assertEquals(training.getId(), dto.getId());
        assertEquals(training.getStartTime(), dto.getStartTime());
        assertEquals(training.getEndTime(), dto.getEndTime());
        assertEquals(training.getActivityType().name(), dto.getActivityType());
        assertEquals(training.getDistance(), dto.getDistance());
        assertEquals(training.getAverageSpeed(), dto.getAverageSpeed());
    }

    @Test
    void toEntity_convertsToEntity() {
        Training entity = trainingMapper.toEntity(trainingDto);

        assertEquals(trainingDto.getId(), entity.getId());
        assertEquals(trainingDto.getStartTime(), entity.getStartTime());
        assertEquals(trainingDto.getEndTime(), entity.getEndTime());
        assertEquals(trainingDto.getActivityType(), entity.getActivityType().name());
        assertEquals(trainingDto.getDistance(), entity.getDistance());
        assertEquals(trainingDto.getAverageSpeed(), entity.getAverageSpeed());
    }
}
