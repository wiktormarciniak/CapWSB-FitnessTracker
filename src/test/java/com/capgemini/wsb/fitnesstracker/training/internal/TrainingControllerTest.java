package com.capgemini.wsb.fitnesstracker.training.internal;

import com.capgemini.wsb.fitnesstracker.training.api.Training;
import com.capgemini.wsb.fitnesstracker.training.api.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TrainingController.class)
public class TrainingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingService trainingService;

    @MockBean
    private TrainingMapper trainingMapper;

    private Training training;
    private TrainingDto trainingDto;

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

        trainingDto = new TrainingDto();
        trainingDto.setId(1L);
        trainingDto.setStartTime(new Date());
        trainingDto.setEndTime(new Date());
        trainingDto.setActivityType("RUNNING");
        trainingDto.setDistance(5.0);
        trainingDto.setAverageSpeed(10.0);
    }

    @Test
    void createTraining_createsNewTraining() throws Exception {
        given(trainingService.createTraining(any(Training.class))).willReturn(training);
        given(trainingMapper.toEntity(any(TrainingDto.class))).willReturn(training);
        given(trainingMapper.toDto(any(Training.class))).willReturn(trainingDto);

        mockMvc.perform(post("/api/trainings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"startTime\":\"2023-06-01T10:15:30\",\"endTime\":\"2023-06-01T11:15:30\",\"activityType\":\"RUNNING\",\"distance\":5.0,\"averageSpeed\":10.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.activityType", is("RUNNING")));
    }

    @Test
    void getAllTrainings_returnsTrainings() throws Exception {
        List<Training> trainings = Arrays.asList(training);
        List<TrainingDto> trainingDtos = Arrays.asList(trainingDto);

        given(trainingService.findAllTrainings()).willReturn(trainings);
        given(trainingMapper.toDto(any(Training.class))).willReturn(trainingDto);

        mockMvc.perform(get("/api/trainings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].activityType", is("RUNNING")));
    }

    @Test
    void getTrainingsByUserId_returnsTrainings() throws Exception {
        List<Training> trainings = Arrays.asList(training);
        List<TrainingDto> trainingDtos = Arrays.asList(trainingDto);

        given(trainingService.findTrainingsByUserId(anyLong())).willReturn(trainings);
        given(trainingMapper.toDto(any(Training.class))).willReturn(trainingDto);

        mockMvc.perform(get("/api/trainings/user/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].activityType", is("RUNNING")));
    }

    @Test
    void getCompletedTrainings_returnsTrainings() throws Exception {
        List<Training> trainings = Arrays.asList(training);
        List<TrainingDto> trainingDtos = Arrays.asList(trainingDto);

        given(trainingService.findCompletedTrainings(any(String.class))).willReturn(trainings);
        given(trainingMapper.toDto(any(Training.class))).willReturn(trainingDto);

        mockMvc.perform(get("/api/trainings/completed?date=2023-06-01T10:15:30")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].activityType", is("RUNNING")));
    }

    @Test
    void getTrainingsByActivity_returnsTrainings() throws Exception {
        List<Training> trainings = Arrays.asList(training);
        List<TrainingDto> trainingDtos = Arrays.asList(trainingDto);

        given(trainingService.findTrainingsByActivityType(any(String.class))).willReturn(trainings);
        given(trainingMapper.toDto(any(Training.class))).willReturn(trainingDto);

        mockMvc.perform(get("/api/trainings/activity/RUNNING")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].activityType", is("RUNNING")));
    }

    @Test
    void updateTraining_updatesTraining() throws Exception {
        given(trainingService.updateTraining(anyLong(), any(Training.class))).willReturn(training);
        given(trainingMapper.toEntity(any(TrainingDto.class))).willReturn(training);
        given(trainingMapper.toDto(any(Training.class))).willReturn(trainingDto);

        mockMvc.perform(put("/api/trainings/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"startTime\":\"2023-06-01T10:15:30\",\"endTime\":\"2023-06-01T11:15:30\",\"activityType\":\"RUNNING\",\"distance\":5.0,\"averageSpeed\":10.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.activityType", is("RUNNING")));
    }
}
