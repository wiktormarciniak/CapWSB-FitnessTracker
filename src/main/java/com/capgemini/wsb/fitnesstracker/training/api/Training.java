package com.capgemini.wsb.fitnesstracker.training.api;

import com.capgemini.wsb.fitnesstracker.training.internal.ActivityType;
import com.capgemini.wsb.fitnesstracker.user.api.User;
import jakarta.persistence.*;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Klasa encji reprezentująca trening w systemie FitnessTracker.
 * Zawiera informacje o użytkowniku, czasie rozpoczęcia i zakończenia treningu,
 * typie aktywności, przebytej odległości oraz średniej prędkości.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_time", nullable = false)
    private Date startTime;

    @Column(name = "end_time", nullable = false)
    private Date endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type", nullable = false)
    private ActivityType activityType;

    @Column(name = "distance")
    private double distance;

    @Column(name = "average_speed")
    private double averageSpeed;

    /**
     * Konstruktor dla klasy Training, inicjalizujący wszystkie potrzebne pola.
     *
     * @param user Użytkownik przypisany do treningu.
     * @param startTime Czas rozpoczęcia treningu.
     * @param endTime Czas zakończenia treningu.
     * @param activityType Typ aktywności (np. bieganie, pływanie).
     * @param distance Dystans przebyty podczas treningu.
     * @param averageSpeed Średnia prędkość osiągnięta podczas treningu.
     */
    public Training(User user, Date startTime, Date endTime, ActivityType activityType, double distance, double averageSpeed) {
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activityType = activityType;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
    }
}
